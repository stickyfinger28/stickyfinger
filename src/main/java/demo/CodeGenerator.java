package demo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;

public class CodeGenerator {
   public static void main(String[] args) {
       //1.获取代码生成器的对象
       AutoGenerator autoGenerator = new AutoGenerator();

       //设置数据库相关配置
       DataSourceConfig dataSource = new DataSourceConfig();
       dataSource.setDriverName("com.mysql.jdbc.Driver");
       dataSource.setUrl("jdbc:mysql://localhost:3306/springbootjlvpc?&useUnicode=true&characterEncoding=utf8&useSSL=false");
       dataSource.setUsername("root");
       dataSource.setPassword("kim");
       autoGenerator.setDataSource(dataSource);

       //设置全局配置
       GlobalConfig globalConfig = new GlobalConfig();
       globalConfig.setOutputDir("C:\\Users\\Kim\\Desktop\\demo\\src\\main\\java\\demo");    //设置代码生成位置
       globalConfig.setOpen(false);    //设置生成完毕后是否打开生成代码所在的目录
       globalConfig.setAuthor("kim");    //设置作者
       globalConfig.setFileOverride(true);     //设置是否覆盖原始生成的文件
       globalConfig.setMapperName("%sMapper");    //设置数据层接口名，%s为占位符，指代模块名称
       globalConfig.setIdType(IdType.AUTO);   //设置Id生成策略
       autoGenerator.setGlobalConfig(globalConfig);

       //设置包名相关配置
       PackageConfig packageInfo = new PackageConfig();
       packageInfo.setParent("demo");   //设置生成的包名，与代码所在位置不冲突，二者叠加组成完整路径
       packageInfo.setEntity("domain");    //设置实体类包名
       packageInfo.setMapper("mapperXml");   //设置数据层包名
       autoGenerator.setPackageInfo(packageInfo);

       //策略设置
       StrategyConfig strategyConfig = new StrategyConfig();
//        strategyConfig.setInclude("tbl_user");  //设置当前参与生成的表名，参数为可变参数
//        strategyConfig.setTablePrefix("tbl_");  //设置数据库表的前缀名称，模块名 = 数据库表名 - 前缀名  例如： User = tbl_user - tbl_
       strategyConfig.setRestControllerStyle(true);    //设置是否启用Rest风格
//        strategyConfig.setVersionFieldName("version");  //设置乐观锁字段名
//        strategyConfig.setLogicDeleteFieldName("deleted");  //设置逻辑删除字段名
       strategyConfig.setEntityLombokModel(true);  //设置是否启用lombok
       autoGenerator.setStrategy(strategyConfig);
       //2.执行生成操作
       autoGenerator.execute();
   }
}
