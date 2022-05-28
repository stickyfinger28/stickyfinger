package demo.service.impl;

import demo.domain.Config;
import demo.mapper.ConfigMapper;
import demo.service.IConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 配置文件 服务实现类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

}
