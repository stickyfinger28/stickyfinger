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

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 岗位分类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Gangweifenlei implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "岗位分类id")
    @TableId(value = "id", type = IdType.AUTO)
    private BigInteger id;

    @ApiModelProperty(hidden = true)
    private String addtime;

    /**
     * 岗位类别
     */
    @ApiModelProperty(value = "岗位类别",example = "前端")
    @NotBlank(message = "岗位不能为空，请重新输入")
    private String gangweileibie;

    @ApiModelProperty(hidden = true)
    @TableLogic
    private Integer del;


}
