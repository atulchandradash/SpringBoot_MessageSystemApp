package com.example.backend.Controllers;

import com.example.backend.Entities.Receivedmessage;
import com.example.backend.Entities.Sentmessage;
import com.example.backend.Entities.Users;
import com.example.backend.Enum.Enums;
import com.example.backend.Util.JwtUtil;
import com.example.backend.Util.MD5Utils;
import com.example.backend.Util.Result;
import com.example.backend.service.ReceivedmessageService;
import com.example.backend.service.SentmessageService;
import com.example.backend.service.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AppController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private SentmessageService sentmessageService;

    @Autowired
    private ReceivedmessageService receivedmessageService;


//    注册
    @PostMapping("/register")
    public Result registerUser(@RequestBody Users user) {
        String username = user.getUsername();
        String password = user.getUserpassword();
        Enums.departmentName departmentname = user.getDepartmentname();

        System.out.println(username);
        System.out.println(password);
        System.out.println(departmentname);

        if (username != null && password != null) {

            Users finduser = usersService.findUserByUsername(username);

            if (finduser != null) {
                return Result.error("这个名字已经选了。");
            } else {
                usersService.addUser(user);

                return Result.success();
            }

        } else {
            return Result.error("名和密码不要控");
        }

    }


//    登录

    @PostMapping("/login")
    public Result loginUser(@RequestBody Users user) {

        String username = user.getUsername();
        String password = user.getUserpassword();

        if (username != null && password != null) {
            Users finduser = usersService.findUserByUsername(username);

            if (finduser == null) {

                return Result.error("这名账户没有");

            }
            else
            {
                String  mdPassword  = MD5Utils.string2MD5(password);

                boolean passwordedIsTrue =  usersService.userValidition(username,mdPassword);

                if (passwordedIsTrue) {

                    Map<String, Object> claims = new HashMap<>();
                    claims.put("id", finduser.getUserid());
                    claims.put("username", finduser.getUsername());

                    String token = JwtUtil.genToken(claims);

                    return Result.success(token);

                } else {

                    return Result.error("密码错了");
                }

            }


        } else {
            return Result.error("名和密码不要控");
        }


    }



//    收部门的人
    @PostMapping("/department")
    public Result getDepartmentPeople(@RequestBody Users user)
    {
        Enums.departmentName departmentname = user.getDepartmentname();
        System.out.println(departmentname);

        List<Users> users =  usersService.getDepartmentPeople(departmentname);

        return  Result.success(users);
    }


//    创建消息
    @PostMapping("/message")
    public Result createMessage(@RequestBody Sentmessage sentmessage)
    {
        Integer senderid = sentmessage.getSenderid();
        String message = sentmessage.getMessage();
        Integer userid = sentmessage.getUserid();

        Boolean isCreated = sentmessageService.createMessage(sentmessage);

        return  Result.success();
    }




//    getsentMessage
   @PostMapping("/sentMessage")
   public Result getAllSentMessage(@RequestBody Users user)
   {
       Integer userid = user.getUserid();

       System.out.println(userid);

        if (userid != null)
        {
            List<Sentmessage> sentmessages = sentmessageService.getAllSentMessage(userid);
            return Result.success(sentmessages);
        }

        else
        {
            return  Result.error("操作失败");
        }


   }

//   getReciviedMessage

    @PostMapping("/receivedMessage")
    public Result getRecivedMessage(@RequestBody Users user)
    {
        Integer userid = user.getUserid();

        System.out.println(userid);

        if (userid != null)
        {
            List<Receivedmessage> receivedmessages = receivedmessageService.getAllSentMessage(userid);
            return Result.success(receivedmessages);
        }

        else
        {
            return  Result.error("操作失败");
        }
    }







}
