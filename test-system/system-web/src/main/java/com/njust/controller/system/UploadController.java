package com.njust.controller.system;

import com.njust.config.upload.UploadService;
import com.njust.controller.BaseController;
import com.njust.vo.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("system/upload")
public class UploadController extends BaseController {

    @Autowired
    private UploadService uploadService;

    /**
     * 文件图片 ---资源图片
     */
    @PostMapping("doUploadImage")
    public AjaxResult uploadFile(MultipartFile mf){
        Map<String,Object> map=new HashMap<>();
        if(null!=mf){
            String contentType = mf.getContentType();

                map.put("name", mf.getOriginalFilename());
                String path = this.uploadService.uploadImage(mf);
                map.put("url", path);
                System.out.println(map);
                return AjaxResult.success(map);
        }else{
            return AjaxResult.error("上传文件失败，文件为空");
        }
    }

    /*
    * 用户头像 和资源图片
    * */
    @PostMapping("doUpload")
    public AjaxResult uploadFileEe(MultipartFile mf){
        if(null!=mf){
            String contentType = mf.getContentType();
            String path = this.uploadService.uploadImage(mf);
            return AjaxResult.success(path);
        }else{
            return AjaxResult.error("上传文件失败，文件为空");
        }
    }

    /*
     * 资源文件
     * */
    @PostMapping("resourceUpload")
    public AjaxResult uploadResourceFile(MultipartFile mf){
        if(null!=mf){
            String contentType = mf.getContentType();
            String originalFilename = mf.getOriginalFilename();
            String path = this.uploadService.uploadImage(mf);
            //msg 存放路径 data 存放名字
            return AjaxResult.success(path,originalFilename);
        }else{
            return AjaxResult.error("上传文件失败，文件为空");
        }
    }
}
