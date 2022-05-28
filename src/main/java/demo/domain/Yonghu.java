package demo.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author kim
 * @since 2022-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@ApiModel(value = "Yonghu",description = "用户")
public class Yonghu implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    @TableId(value = "id", type = IdType.AUTO)
    private BigInteger id;

    @ApiModelProperty(value = "注册时间",hidden = true)
    private String addtime;

    @ApiModelProperty(value = "用户名",example = "simi")
    @Pattern(regexp = "[\\u4e00-\\u9fa5\\w]{3,9}",message = "请输入正确的用户名形式")
    @NotBlank(message = "用户名不能为空，请输入")
    private String yonghuming;


    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "姓名不能为空，请重新输入")
    @Pattern(regexp = "[\\u4e00-\\u9fa5]{2,5}",message = "请输入正确的姓名形式")
    private String xingming;

    @ApiModelProperty(value = "密码",example = "88381089a")
    @Pattern(regexp = "\\w{6,15}",message = "请输入正确的密码格式")
    @NotBlank(message = "密码不能为空，请重新输入")
    private String mima;

    @ApiModelProperty(value = "性别")
    @Pattern(regexp = "^[男|女]$")
    private String xingbie;

    @Pattern(regexp = "\\d{11}",message = "请输入正确的电话格式")
    @ApiModelProperty(value = "电话")
    private String dianhua;

    @ApiModelProperty(value = "邮箱")
    @Email(message = "请输入正确的邮箱格式")
    @NotBlank(message = "邮箱不能为空，请重新输入")
    private String youxiang;

    @ApiModelProperty(value = "认证状态",hidden = true)
    private Integer state;

    @ApiModelProperty(value = "学历信息",hidden = true)
    @TableField(exist = false)
    private Xuelixinxi xuelixinxi;

    @ApiModelProperty(value = "留言信息",hidden = true)
    @TableField(exist = false)
    private List<Messages> messagesList;

    @ApiModelProperty(hidden = true)
    @TableLogic
    private Integer del;

}
