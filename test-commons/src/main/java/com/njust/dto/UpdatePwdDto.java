package com.njust.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Author qufeng
 * @Date 2021/2/12 19:24
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePwdDto {
    //  旧密码
    @NotNull(message = "旧密码不能为空")
    private String password;
    //  密码
    @NotNull(message = "新密码不能为空")
    private String newpassword1;

    //  密码
    @NotNull(message = "新密码不能为空")
    private String newpwd;


}
