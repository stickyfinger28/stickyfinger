package demo.domain.L;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
public class SYingpinxinxi {

    @ApiModelProperty(value = "应聘信息ID")
    @NotNull(message = "应聘信息id不能为空")
    private BigInteger id;


    @ApiModelProperty(value = "审核回复")
    @NotBlank(message = "审核回复不能为空")
    private String shhf;

    @ApiModelProperty(hidden = true)
    private String sfsh;

}
