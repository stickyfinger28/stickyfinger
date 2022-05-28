package demo.domain.L;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

@Data
public class LYingpinxinxi {


    @ApiModelProperty()
    private BigInteger id;

    @ApiModelProperty(value = "企业编号")
    private String qiyebianhao;


    @ApiModelProperty(value = "招聘岗位")
    private String zhaopingangwei;

    @ApiModelProperty(value = "简历信息")
    private String jianli;


}
