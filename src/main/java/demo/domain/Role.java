package demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Data
@ApiIgnore
@ApiModel(value = "role",description = "角色")
@EqualsAndHashCode(callSuper = false)
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private BigInteger id;

    /**
     * 角色名称
     */
    private String role_name;

    /**
     * 角色状态
     */
    private String status;


}
