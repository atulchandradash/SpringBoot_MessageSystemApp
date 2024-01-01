package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.Entities.Receivedmessage;
import com.example.backend.Entities.Sentmessage;
import com.example.backend.mapper.ReceivedmessageMapper;
import com.example.backend.service.SentmessageService;
import com.example.backend.mapper.SentmessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author Atul
* @description 针对表【sentmessage】的数据库操作Service实现
* @createDate 2024-01-01 09:11:10
*/
@Service
public class SentmessageServiceImpl extends ServiceImpl<SentmessageMapper, Sentmessage>
    implements SentmessageService{

    @Autowired
    private SentmessageMapper sentmessageMapper;

    @Autowired
    private ReceivedmessageMapper receivedmessageMapper;

    @Override
    public Boolean createMessage(Sentmessage sentmessage) {

        try {
            sentmessage.setCreateat(LocalDateTime.now());
            sentmessage.setUpdateat(LocalDateTime.now());

            sentmessageMapper.insert(sentmessage);

            Receivedmessage receivedmessage = new Receivedmessage();

            receivedmessage.setReceiverid(sentmessage.getUserid());
            receivedmessage.setMessage(sentmessage.getMessage());
            receivedmessage.setUserid(sentmessage.getSenderid());
            receivedmessage.setCreateat(sentmessage.getCreateat());
            receivedmessage.setUpdateat(sentmessage.getUpdateat());

            receivedmessageMapper.insert(receivedmessage);

            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Sentmessage> getAllSentMessage(Integer userid) {

       List<Sentmessage> sentmessagess =   sentmessageMapper.selectAll(userid);

        return sentmessagess;
    }


}




