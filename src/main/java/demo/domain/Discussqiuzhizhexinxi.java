package demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 求职者信息评论表
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Discussqiuzhizhexinxi implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    @TableId(value = "d_id", type = IdType.AUTO)
    private BigInteger d_id;

    @ApiModelProperty(hidden = true)
    private String addtime;

    /**
     * 关联表id 关联求职者信息id
     */
    @ApiModelProperty(value = "对应求职者评论信息",example = "1")
    private BigInteger refid;

    
    @ApiModelProperty(value = "评论内容")
    @NotBlank(message = "评论内容不能为空，请重新输入")
    private String content;

    /**
     * 用户id 留下评论的用户Id
     */
    @ApiModelProperty(hidden = true)
    private BigInteger userid;

    @ApiModelProperty(hidden = true)
    @TableField(exist = false)
    private Qiuzhizhexinxi qiuzhizhexinxi;


}
