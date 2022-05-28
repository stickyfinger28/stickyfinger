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
public class LZhaopinxinxi {


    @ApiModelProperty(value = "id")
    @NotNull(message = "id不能为空")
    private BigInteger id;

    @ApiModelProperty(value = "招聘岗位",example = "后端")
    private String zhaopingangwei;


    @ApiModelProperty(value = "招聘人数",example = "80")
    @Pattern(regexp = "\\d{1,10000}",message = "请输入正确的招聘人数")
    private String zhaopinrenshu;


    @ApiModelProperty(value = "岗位类别",example = "Java后端开发")

    private String gangweileibie;


    @ApiModelProperty(value = "学历",example = "本科")
    private String xueli;


    @ApiModelProperty(value = "图片")
    private String tupian;


    @ApiModelProperty(value = "岗位要求",example = "三年开发经验")
    private String gangweiyaoqiu;


    @ApiModelProperty(value = "工作职责",example = "摆")
    private String gongzuozhize;
}
