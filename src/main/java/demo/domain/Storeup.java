package demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 收藏表
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Storeup implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    @TableId(value = "id", type = IdType.AUTO)
    private BigInteger id;

    @ApiModelProperty(hidden = true)
    private String addtime;

    /**
     * 用户id
     */
    @ApiModelProperty(hidden = true)
    private BigInteger userid;

    /**
     * 收藏id
     */
    @ApiModelProperty(value = "收藏新闻的id")
    @NotNull(message = "收藏id不能为空，请重新输入")
    private BigInteger refid;

    /**
     * 收藏名称
     */
    @ApiModelProperty(value = "收藏名称",example = "哔哩哔哩")
    @NotBlank(message = "收藏名称不能为空，请重新输入")
    private String name;

    /**
     * 收藏图片
     */
    @ApiModelProperty(value = "收藏图片")
    @NotBlank(message = "收藏图片不能为空，请重新输入")
    private String picture;

    @ApiModelProperty(value = "新闻",hidden = true)
    private News news;


    @ApiModelProperty(hidden = true)
    @TableLogic
    private Integer del;
}
