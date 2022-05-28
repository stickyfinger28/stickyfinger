package demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 新闻资讯
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "news",description = "新闻资讯")
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private BigInteger id;

    @ApiModelProperty(hidden = true)
    private String addtime;

    @ApiModelProperty(value = "标题")
    @NotBlank(message = "标题不能为空，请重新输入")
    private String title;


    @ApiModelProperty(value = "图片")
    @NotBlank(message = "图片不能为空，请重新输入")
    private String picture;

    @ApiModelProperty(value = "内容")
    @NotBlank(message = "内容不能为空，请重新输入")
    private String content;

    @ApiModelProperty(hidden = true)
    @TableLogic
    private Integer del;



}
