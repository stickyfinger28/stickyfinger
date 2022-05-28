package demo.domain.L;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;

@Data
public class LQiyexinxi {


    @ApiModelProperty(value = "企业编号",example = "A000")
    @Pattern(regexp = "\\w{4}")
    private String qiyebianhao;


    @ApiModelProperty(value = "密码",example = "88381089a")
    @Pattern(regexp = "\\w{6,15}",message = "请输入正确的密码格式")
    private String mima;


    @ApiModelProperty(value = "企业名称",example = "白鲸")
    private String qiyemingcheng;


    @ApiModelProperty(value = "负责人")
    private String fuzeren;


    @Pattern(regexp = "\\d{11}",message = "请输入正确的电话格式")
    @ApiModelProperty(value = "联系电话",example = "18942427821")
    private String lianxidianhua;


    @ApiModelProperty(value = "企业邮箱",example = "A001@163.cn")
    private String qiyeyouxiang;


    @ApiModelProperty(value = "企业介绍",example = "黑厂")
    private String qiyejieshao;



    @ApiModelProperty(value = "企业编号",example = "A000")
    @Pattern(regexp = "\\w{4}",message = "请输入正确的企业编号")
    @NotBlank(message = "企业编号不能为空")
    private String Uqiyebianhao;

    @ApiModelProperty(value = "密码",example = "88381089a")
    @Pattern(regexp = "\\w{6,15}",message = "请输入正确的密码格式")
    @NotBlank(message = "密码不能为空")
    private String Umima;

    @ApiModelProperty(hidden = true)
    private BigInteger userid;
}
