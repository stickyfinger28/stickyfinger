package demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 留言板
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "messages",description = "留言板")
public class Messages implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "留言id")
    private BigInteger id;

    @ApiModelProperty(value = "留言时间",hidden = true)
    private String addtime;


    @ApiModelProperty(value = "留言内容")
    @NotBlank(message = "留言内容不能为空，请重新输入")
    private String content;


    @ApiModelProperty(value = "留言人id",hidden = true)
    private BigInteger userid;


}
