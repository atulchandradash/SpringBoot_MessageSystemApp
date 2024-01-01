package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.Entities.Receivedmessage;
import com.example.backend.service.ReceivedmessageService;
import com.example.backend.mapper.ReceivedmessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Atul
* @description 针对表【receivedmessage】的数据库操作Service实现
* @createDate 2024-01-01 10:20:51
*/
@Service
public class ReceivedmessageServiceImpl extends ServiceImpl<ReceivedmessageMapper, Receivedmessage>
    implements ReceivedmessageService{

    @Autowired
    private ReceivedmessageMapper receivedmessageMapper;

    @Override
    public List<Receivedmessage> getAllSentMessage(Integer userid) {

       List<Receivedmessage> receivedmessages =  receivedmessageMapper.selectAll(userid);

        return receivedmessages;
    }
}




