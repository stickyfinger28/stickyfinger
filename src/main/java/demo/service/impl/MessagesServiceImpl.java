package demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import demo.domain.Messages;
import demo.mapper.MessagesMapper;
import demo.service.IMessagesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.util.Code;
import demo.util.Result;
import demo.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 留言板 服务实现类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Service
public class MessagesServiceImpl extends ServiceImpl<MessagesMapper, Messages> implements IMessagesService {

    @Autowired
    private MessagesMapper messagesMapper;
    @Override
    public Result add(Messages messages, BigInteger yonghuId) {
        messages.setId(null);
        messages.setAddtime(TimeUtils.getTime());
        messages.setUserid(yonghuId);
        messagesMapper.insert(messages);
        return new Result(Code.SAVE_OK,messages,"发布留言成功");
    }
    @Override
    public Result delete(BigInteger id,BigInteger yonghuId) {
        if (!check(id, yonghuId)){
            return new Result(Code.DELETE_ERR,"删除留言失败");
        }
        messagesMapper.deleteById(id);
        return new Result(Code.DELETE_OK,"删除留言成功");
    }
    @Override
    public Result updateSelf(Messages messages, BigInteger yonghuId) {
        LambdaUpdateWrapper<Messages> uw = new LambdaUpdateWrapper<>();
        uw.eq(Messages::getUserid,yonghuId);
        uw.eq(Messages::getId,messages.getId());
        int update = messagesMapper.update(messages, uw);
        if (update==0){
            return new Result(Code.UPDATE_ERR,"修改留言失败");
        }
        return new Result(Code.UPDATE_OK,messages,"修改留言成功");
    }

    @Override
    public Result selectSelf(BigInteger yonghuId) {
        LambdaQueryWrapper<Messages> qw = new LambdaQueryWrapper<>();
        qw.eq(Messages::getUserid,yonghuId);
        List<Messages> messagesList = messagesMapper.selectList(qw);

        return new Result(Code.GET_OK,messagesList,"查询留言成功");
    }

    public Boolean check(BigInteger id,BigInteger yonghuId){
        //判断是否为自己的留言
        LambdaQueryWrapper<Messages> qw = new LambdaQueryWrapper<>();
        qw.eq(Messages::getId,id);qw.eq(Messages::getUserid,yonghuId);
        Messages messages = messagesMapper.selectOne(qw);
        return Objects.nonNull(messages);
    }


}
