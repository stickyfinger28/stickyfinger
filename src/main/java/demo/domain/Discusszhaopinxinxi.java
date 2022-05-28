package demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * 招聘信息评论表
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Discusszhaopinxinxi implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    @TableId(value = "d_id", type = IdType.AUTO)
    private BigInteger d_id;

    @ApiModelProperty(hidden = true)
    private String addtime;

    /**
     * 关联表id
     */
    @ApiModelProperty(value = "对应招聘信息的id",example = "1")
    private BigInteger refid;

    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容",example = "烂中烂")
    private String content;

    /**
     * 用户id
     */
    @ApiModelProperty(hidden = true)
    private BigInteger userid;

    @ApiModelProperty(hidden = true)
    @TableField(exist = false)
    private Zhaopinxinxi zhaopinxinxi;


}
