package demo.service;

import demo.domain.Gangweifenlei;
import com.baomidou.mybatisplus.extension.service.IService;
import demo.util.Result;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 * 岗位分类 服务类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
public interface IGangweifenleiService extends IService<Gangweifenlei> {


    Result saveByList(List<Gangweifenlei> gangweifenleiList);

    Result update(Gangweifenlei gangweifenlei, BigInteger id);

    Result select();

    Result updateSelf(Gangweifenlei gangweifenlei);
}
