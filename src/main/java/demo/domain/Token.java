package demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * <p>
 * token表
 * </p>
 *
 * @author kim
 * @since 2022-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Token implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private BigInteger id;

    /**
     * 用户id
     */
    private BigInteger userid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 表名
     */
    private String tablename;

    /**
     * 角色
     */
    private String role;

    /**
     * 密码
     */
    private String token;

    /**
     * 新增时间
     */
    private String addtime;

    /**
     * 过期时间
     */
    private String expiratedtime;


}
