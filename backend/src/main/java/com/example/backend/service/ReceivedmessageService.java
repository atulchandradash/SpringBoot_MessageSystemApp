package com.example.backend.service;

import com.example.backend.Entities.Receivedmessage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Atul
* @description 针对表【receivedmessage】的数据库操作Service
* @createDate 2024-01-01 10:20:51
*/
public interface ReceivedmessageService extends IService<Receivedmessage> {

     List<Receivedmessage> getAllSentMessage(Integer userid);
}
