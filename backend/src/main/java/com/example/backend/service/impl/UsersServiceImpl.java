package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.Entities.Users;
import com.example.backend.Enum.Enums;
import com.example.backend.Util.MD5Utils;
import com.example.backend.service.UsersService;
import com.example.backend.mapper.UsersMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author Atul
* @description 针对表【users】的数据库操作Service实现
* @createDate 2024-01-01 08:13:44
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{


    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Users findUserByUsername(String username) {

        QueryWrapper<Users> user = new QueryWrapper<>();
        user.eq("username",username);

        return usersMapper.selectOne(user) ;
    }

    @Override
    public void addUser(Users user) {

        String md5Pass = MD5Utils.string2MD5(user.getUserpassword());
        user.setUserpassword(md5Pass);
        user.setCreateat(LocalDateTime.now());
        user.setUpdateat(LocalDateTime.now());

        usersMapper.insert(user);
    }

    @Override
    public boolean userValidition(String username, String mdPassword) {
        QueryWrapper<Users> user = new QueryWrapper<>();

        user.eq("username",username);
        user.eq("userPassword",mdPassword);

        Users findUser = usersMapper.selectOne(user);

        if ( findUser !=  null)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    @Override
    public List<Users> getDepartmentPeople(Enums.departmentName departmentname) {

        QueryWrapper<Users> users = new QueryWrapper<>();
        users.eq("departmentName",departmentname);

        List<Users> allUsers = usersMapper.selectList(users);

        return allUsers;
    }


}




