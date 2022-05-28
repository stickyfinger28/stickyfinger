package demo.domain.L;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ApiModel(value = "登录用户")
public class Lyonghu {

    @ApiModelProperty(value = "用户名",example = "simi")
    @Pattern(regexp = "[\\u4e00-\\u9fa5\\w]{3,9}")
    @NotBlank(message = "用户名不能为空，请输入")
    private String yonghuming;


    @ApiModelProperty(value = "密码",example = "88381089a")
    @Pattern(regexp = "\\w{6,15}",message = "请输入正确的密码格式")
    @NotBlank(message = "密码不能为空，请重新输入")
    private String mima;
}
