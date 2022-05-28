package demo.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.math.BigInteger;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 招聘信息
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Zhaopinxinxi implements Serializable {

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
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称",example = "白鲸")
    private String qiyemingcheng;

    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人",example = "张三")
    private String fuzeren;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话",example = "19832233398")
    @Pattern(regexp = "\\d{11}",message = "请输入正确的电话格式")
    private String lianxidianhua;

    /**
     * 招聘岗位
     */
    @ApiModelProperty(value = "招聘岗位",example = "后端")
    @NotBlank(message = "招聘岗位信息不能为空")
    private String zhaopingangwei;

    /**
     * 招聘人数
     */
    @ApiModelProperty(value = "招聘人数",example = "80")
    @NotBlank(message = "招聘人数不能为空")
    @Pattern(regexp = "\\d{1,10000}",message = "请输入正确的招聘人数")
    private String zhaopinrenshu;

    /**
     * 岗位类别
     */
    @ApiModelProperty(value = "岗位类别",example = "Java后端开发")
    @NotBlank(message = "岗位类别不能为空")
    private String gangweileibie;

    /**
     * 学历
     */
    @ApiModelProperty(value = "学历",example = "本科")
    @NotBlank(message = "学历不能为空")
    private String xueli;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
    @NotBlank(message = "图片不能为空")
    private String tupian;

    /**
     * 岗位要求
     */
    @ApiModelProperty(value = "岗位要求",example = "三年开发经验")
    @NotBlank(message = "岗位要求不能为空")
    private String gangweiyaoqiu;

    /**
     * 工作职责
     */
    @ApiModelProperty(value = "工作职责",example = "摆")
    @NotBlank(message = "工作职责不能为空")
    private String gongzuozhize;

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
    @TableField(exist = false)
    private Qiyexinxi qiyexinxi;

    @ApiModelProperty(hidden = true)
    @TableField(exist = false)
    private List<Discusszhaopinxinxi> discusszhaopinxinxiList;

    @ApiModelProperty(hidden = true)
    @TableLogic
    private Integer del;


}
