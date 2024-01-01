package com.example.backend.mapper;

import com.example.backend.Entities.Receivedmessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.Entities.Sentmessage;

import java.util.List;

/**
* @author Atul
* @description 针对表【receivedmessage】的数据库操作Mapper
* @createDate 2024-01-01 10:20:51
* @Entity com.example.backend.Entities.Receivedmessage
*/
public interface ReceivedmessageMapper extends BaseMapper<Receivedmessage> {

    List<Receivedmessage> selectAll(Integer id);

}




