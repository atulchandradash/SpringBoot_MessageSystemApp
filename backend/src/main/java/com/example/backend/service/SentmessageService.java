package com.example.backend.service;

import com.example.backend.Entities.Sentmessage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Atul
* @description 针对表【sentmessage】的数据库操作Service
* @createDate 2024-01-01 09:11:10
*/
public interface SentmessageService extends IService<Sentmessage> {

    Boolean createMessage(Sentmessage sentmessage);


    List<Sentmessage> getAllSentMessage(Integer userid);
}
