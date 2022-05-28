package demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Pattern;

/**
 * <p>
 * 企业信息
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Qiyexinxi implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    @TableId(value = "id", type = IdType.AUTO)
    private BigInteger id;

    @ApiModelProperty(hidden = true)
    private String addtime;

    /**
     * 企业编号
     */
    @ApiModelProperty(value = "企业编号",example = "A000")
    @Pattern(regexp = "\\w{4}")
    private String qiyebianhao;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码",example = "88381089a")
    @Pattern(regexp = "\\w{6,15}",message = "请输入正确的密码格式")
    private String mima;

    @ApiModelProperty(hidden = true)
    private BigInteger userid;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称",example = "白鲸")
    private String qiyemingcheng;

    /**
     * 负责人 默认为创建企业信息的用户姓名
     */
    @ApiModelProperty(value = "负责人")
    private String fuzeren;

    /**
     * 联系电话 默认为创建企业信息的用户电话
     */
    @Pattern(regexp = "\\d{11}",message = "请输入正确的电话格式")
    @ApiModelProperty(value = "联系电话")
    private String lianxidianhua;

    /**
     * 企业邮箱
     */
    @ApiModelProperty(value = "企业邮箱",example = "A001@163.cn")
    private String qiyeyouxiang;

    /**
     * 企业介绍
     */
    @ApiModelProperty(value = "企业介绍",example = "黑厂")
    private String qiyejieshao;

    @ApiModelProperty(hidden = true)
    @TableLogic
    private Integer del;


}
