package com.njust.controller.system;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.njust.aspectj.annotation.Log;
import com.njust.aspectj.enums.BusinessType;
import com.njust.domain.User;
import com.njust.dto.UpdatePwdDto;
import com.njust.dto.UserDto;
import com.njust.entity.UserEntity;
import com.njust.service.ResourceService;
import com.njust.service.UserService;
import com.njust.utils.ShiroSecurityUtils;
import com.njust.utils.importExcel.UserExcelListener;
import com.njust.vo.AjaxResult;
import com.njust.vo.DataGridView;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;


@RestController
@RequestMapping("system/user")
public class UserController {

    @Reference
    private ResourceService resourceService;

    @Autowired
    private UserService userService;

    /**
     * 分页查询
     */
    @GetMapping("listUserForPage")
    public AjaxResult listUserForPage(UserDto userDto) {
        DataGridView gridView = this.userService.listUserForPage(userDto);
        return AjaxResult.success("查询成功", gridView.getData(), gridView.getTotal());
    }

    /**
     * 添加
     */
    @PostMapping("addUser")
    @Log(title = "添加用户", businessType = BusinessType.INSERT)
    public AjaxResult addUser(@Validated UserDto userDto) {
        userDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        int i = this.userService.addUser(userDto);
        if (i == 520) {
            return AjaxResult.error("手机号已存在");

        } else {
            return AjaxResult.toAjax(i);
        }
    }

    /**
     * 修改
     */
    @PutMapping("updateUser")
    @Log(title = "修改用户", businessType = BusinessType.UPDATE)
    public AjaxResult updateUser(@Validated UserDto userDto) {
        userDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.userService.updateUser(userDto));
    }


    /**
     * 根据ID查询一个用户信息
     */
    @GetMapping("getUserById/{userId}")
    public AjaxResult getUserById(@PathVariable @Validated @NotNull(message = "用户ID不能为空") Long userId) {
        return AjaxResult.success(this.userService.getOne(userId));
    }

    /**
     * 删除
     */
    @DeleteMapping("deleteUserByIds/{userIds}")
    @Log(title = "删除用户", businessType = BusinessType.DELETE)
    public AjaxResult deleteUserByIds(@PathVariable @Validated @NotEmpty(message = "要删除的ID不能为空") Long[] userIds) {
        return AjaxResult.toAjax(this.userService.deleteUserByIds(userIds));
    }

    /**
     * 查询所有可用的用户
     */
    @GetMapping("selectAllUser")
    public AjaxResult selectAllUser() {
        return AjaxResult.success(this.userService.getAllUsers());
    }

    /**
     * 重置密码
     */
    @PostMapping("resetPwd/{userIds}")
    public AjaxResult resetPwd(@PathVariable Long[] userIds) {
        if (userIds.length > 0) {
            this.userService.resetPassWord(userIds);
            return AjaxResult.success("重置成功");
        }
        return AjaxResult.fail("重置失败,没有选择用户");

    }

    /*
     * 查询当前用户
     * */
    @GetMapping("getCurrentUser")
    public AjaxResult getCurrentUser() {
        User currentUser = ShiroSecurityUtils.getCurrentUser();
        currentUser.setPassword("");
        return AjaxResult.success("成功", currentUser);
    }

    /*
     * 修改密码
     * */
    @PostMapping("updatePwd")
    @Log(title = "修改密码", businessType = BusinessType.UPDATE)
    public AjaxResult updatePwd(UpdatePwdDto updatePwdDto, HttpServletRequest request) throws Exception {
        if (updatePwdDto.getPassword().equals(updatePwdDto.getNewpassword1())) {
            return AjaxResult.success("新密码和旧密码不能一致", 0);
        }
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        User currentUser = ShiroSecurityUtils.getCurrentUser();
        //构造用户名和密码的token
        UsernamePasswordToken token = new UsernamePasswordToken(currentUser.getPhone(), updatePwdDto.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            //得到会话的token==也就是redis里面存的
            Serializable webToken = subject.getSession().getId();
            //没有异常代表验证成功
            this.userService.updatePwd(currentUser.getUserId(), updatePwdDto.getNewpassword1());

        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.success("密码错误",2);
        }
            if (exceptionClassName != null) {
                if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                    //最终会抛给异常处理器
                    return AjaxResult.error(1, "账号不存在或已作废");
                } else if (AuthenticationException.class.getName().equals(exceptionClassName)) {
                    //最终会抛给异常处理器
                    return AjaxResult.error(1, "账号不存在或已作废");
                } else if (IncorrectCredentialsException.class.getName().equals(
                        exceptionClassName)) {
                    return AjaxResult.error(2, "密码错误");
                } else {
                    return AjaxResult.error(4, "未知错误，请联系管理员");
                }
        }else{
                return AjaxResult.success("修改成功",1);
            }
    }

    /*
     *导入Excel
     * */
    @PostMapping("upload")
    public AjaxResult upload(@RequestParam("file") MultipartFile file)throws IOException {
        boolean error=false;
        //获取文件名
        String uploadFileName = file.getOriginalFilename();
        //数据库中已经存在的数据集合
        List<UserEntity> existUserList=null;
        //导入的数据集合
        List<UserEntity> list=null;
        //首先判断是不是空的文件
        if (!file.isEmpty()) {
            //对文文件的全名进行截取然后在后缀名进行删选。
            int begin = file.getOriginalFilename().indexOf(".");
            int last = file.getOriginalFilename().length();
            //获得文件后缀名
            String a = file.getOriginalFilename().substring(begin, last);
            if (a.endsWith(".xlsx")||a.endsWith(".xls")) {
                ExcelReader excelReader = null;
                String resutl = null;
                InputStream in = null;
                try {
                    in = file.getInputStream();
                    try{
                        EasyExcel.read(in, UserEntity.class, new UserExcelListener()).doReadAll();
                    }catch (Exception e){
                        e.printStackTrace();
                        error=true;
                    }
                    //excel里面的数据集合
                    list = UserExcelListener.list;
                    //是否重复集合
                    Set<String> set=new HashSet<>();
                    for (UserEntity entity: list ) {
                        String phone = entity.getPhone();
                        set.add(phone);
                    }
                    if (set.size()==list.size()) {
                        existUserList=this.userService.addUserExcel(list);
                    }else {
                        error=true;
                    }
                    if (existUserList!=null){
                        error=true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    in.close();
                    // 这里一定别忘记关闭，读的时候会创建临时文件，到时磁盘会崩
                    if (excelReader != null) {
                        excelReader.finish();
                    }
                    list.clear();
                }
            } else {
                return AjaxResult.success("文件格式错误");
            }
        }else {
            return AjaxResult.success("文件不能为空");
        }
        if (!error){
            return AjaxResult.success("导入成功",existUserList) ;
        }else if (existUserList!=null){
            LOGGER.info("重复数据有"+existUserList.size()+"条");
            return AjaxResult.success("数据库中已存在的号码数量有"+existUserList.size()+"条");

        } else {
            return AjaxResult.success("导入异常,请注意查看导入文件是否存在相同号码",existUserList) ;
        }

    }


    /*
     * 下载导入用户模板
     * */
    @GetMapping(value = "/downloadExcelModel")
    public ResponseEntity<byte[]> download(HttpServletRequest response) throws Exception{
        String fileName="导入用户模板";
        //String modelUrl=this.resourceService.selectByUserExcelName(fileName);
        String modelUrl=null;
        modelUrl="http://115.159.69.180:8848/group1/M00/00/00/rBEQD2AtHbiAKvlZAAAjNKqZjGc93.xlsx";
        if (StringUtils.isNotBlank(modelUrl)) {
            HttpHeaders headers = new HttpHeaders();
            //处理IE
            String userAgent = response.getHeader("user-agent").toLowerCase();

            if (userAgent.contains("msie") || userAgent.contains("like gecko") ||
                    userAgent.contains("Trident")) {
                // win10 ie edge 浏览器 和其他系统的ie
                fileName = URLEncoder.encode(fileName, "UTF-8");
                //解决下载时，空格变加号
                fileName = org.apache.commons.lang3.StringUtils.replace(fileName, "+", "%20");
            } else {
                // fe
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");

                //解决下载时，空格变加号
                fileName = org.apache.commons.lang3.StringUtils.replace(fileName, "+", "%20");
            }

            //通知浏览器以attachment（下载方式）打开图片
            headers.setContentDispositionFormData("attachment", fileName); //解决原始文件名中有中文出现乱码);
            //application/octet-stream ： 二进制流数据（最常见的文件下载）。
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(IOUtils.toByteArray(getFileInputStream(modelUrl)),
                    headers, HttpStatus.CREATED);
        }else {
            return null;
        }

    }
    //输入流私有方法
    public InputStream getFileInputStream(String urlString) {
        InputStream is = null;
        try {
            // 构造URL
            URL url = new URL(urlString);
            // 打开连接
            URLConnection con = url.openConnection();
            // 输入流
            is = con.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }
}
