package com.example.backend.service;

import com.example.backend.Entities.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.Enum.Enums;

import java.util.List;

/**
* @author Atul
* @description 针对表【users】的数据库操作Service
* @createDate 2024-01-01 08:13:44
*/
public interface UsersService extends IService<Users> {

    Users findUserByUsername(String username);

    void addUser(Users user);

    boolean userValidition(String username, String mdPassword);


    List<Users> getDepartmentPeople(Enums.departmentName departmentname);
}
