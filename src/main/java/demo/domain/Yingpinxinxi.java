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

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 应聘信息
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Yingpinxinxi implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    @TableId(value = "id", type = IdType.AUTO)
    private BigInteger id;

    @ApiModelProperty(hidden = true)
    private String addtime;

    /**
     * 企业编号
     */
    @ApiModelProperty(value = "企业编号")
    private String qiyebianhao;

    /**
     * 企业名称
     */
    @ApiModelProperty(hidden = true)
    private String qiyemingcheng;

    /**
     * 负责人
     */
    @ApiModelProperty(hidden = true)
    private String fuzeren;

    /**
     * 联系电话
     */
    @ApiModelProperty(hidden = true)
    private String lianxidianhua;

    /**
     * 招聘岗位
     */
    @ApiModelProperty(value = "招聘岗位")
    @NotBlank(message = "招聘岗位不能为空，请重新输入")
    private String zhaopingangwei;

    /**
     * 用户名
     */
    @ApiModelProperty(hidden = true)
    private String yonghuming;

    /**
     * 姓名
     */
    @ApiModelProperty(hidden = true)
    private String xingming;

    /**
     * 电话
     */
    @ApiModelProperty(hidden = true)
    private String dianhua;

    /**
     * 简历
     */
    @ApiModelProperty(value = "简历信息")
    @NotBlank(message = "简历信息不能为空，请重新输入")
    private String jianli;

    /**
     * 是否审核
     */
    @ApiModelProperty(hidden = true)
    private String sfsh;

    /**
     * 审核回复
     */
    @ApiModelProperty(hidden = true)
    private String shhf;


    @ApiModelProperty(hidden = true)
    @TableLogic
    private Integer del;
}
