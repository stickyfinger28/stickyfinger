package demo.domain.L;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;

@Data
public class LQiuzhizhexinxi {

    @ApiModelProperty(value = "id")
    @NotNull(message = "id不能为空")
    private BigInteger id;

    /**
     * 照片
     */
    @ApiModelProperty(value = "照片")
    private String zhaopian;

    /**
     * 岗位类别
     */
    @ApiModelProperty(value = "岗位类别")
    private String gangweileibie;

    /**
     * 简历
     */
    @ApiModelProperty(value = "简历")
    private String jianli;

    /**
     * 工作经历
     */
    @ApiModelProperty(value = "工作经历")
    private String gongzuojingli;

    /**
     * 个人基本情况
     */
    @ApiModelProperty(value = "个人基本情况")
    private String gerenjibenqingkuang;

}
