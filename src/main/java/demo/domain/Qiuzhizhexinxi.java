package demo.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 求职者信息
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class Qiuzhizhexinxi implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true,value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private BigInteger id;

    @ApiModelProperty(hidden = true,value = "添加时间")
    private String addtime;


    @ApiModelProperty(hidden = true,value = "用户名")
    private String yonghuming;

    @ApiModelProperty(hidden = true)
    private String xingming;


    @ApiModelProperty(hidden = true)
    private String xingbie;

    /**
     * 电话
     */
    @ApiModelProperty(hidden = true)
    private String dianhua;

    /**
     * 照片
     */
    @ApiModelProperty(value = "照片")
    @NotBlank(message = "照片不能为空，请输入")
    private String zhaopian;

    /**
     * 学历
     */
    @ApiModelProperty(value = "学历",hidden = true)
    private String xueli;

    /**
     * 岗位类别
     */
    @ApiModelProperty(value = "岗位类别")
    private String gangweileibie;

    /**
     * 简历
     */
    @ApiModelProperty(value = "简历")
    private String jianli;

    /**
     * 工作经历
     */
    @ApiModelProperty(value = "工作经历")
    private String gongzuojingli;

    /**
     * 个人基本情况
     */
    @ApiModelProperty(value = "个人基本情况")
    private String gerenjibenqingkuang;

    /**
     * 赞
     */
    @ApiModelProperty(hidden = true)
    private Integer thumbsupnum;

    /**
     * 踩
     */
    @ApiModelProperty(hidden = true)
    private Integer crazilynum;

    @ApiModelProperty(hidden = true)
    @TableLogic
    private Integer del;

    @ApiModelProperty(hidden = true)
    @TableField(exist = false)
    private List<Discussqiuzhizhexinxi> discussqiuzhizhexinxiList;



}
