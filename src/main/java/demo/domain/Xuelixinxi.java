package demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 学历信息
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Xuelixinxi implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    @ApiModelProperty(hidden = true)
    private BigInteger id;

    @ApiModelProperty(hidden = true)
    private String addtime;

    /**
     * 学历
     */
    @ApiModelProperty(value = "学历")
    private String xueli;


}
