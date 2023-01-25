package com.qxy.bytejump.service.impl;

import com.qxy.bytejump.entity.Message;
import com.qxy.bytejump.mapper.MessageMapper;
import com.qxy.bytejump.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author leeqi10
 * @since 2023-01-25
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
