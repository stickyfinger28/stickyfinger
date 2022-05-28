package demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigInteger;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 配置文件
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Data
@ApiModel(value = "config",description = "配置文件")
@EqualsAndHashCode(callSuper = false)
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private BigInteger id;

    /**
     * 配置参数名称
     */
    private String name;

    /**
     * 配置参数值
     */
    private String value;


}
