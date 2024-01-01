package com.example.backend.mapper;

import com.example.backend.Entities.Sentmessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Atul
* @description 针对表【sentmessage】的数据库操作Mapper
* @createDate 2024-01-01 09:11:10
* @Entity com.example.backend.Entities.Sentmessage
*/
public interface SentmessageMapper extends BaseMapper<Sentmessage> {

   List<Sentmessage> selectAll(Integer id);
}




