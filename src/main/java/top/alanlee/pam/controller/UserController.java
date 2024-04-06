package top.alanlee.pam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alanlee.pam.dto.ApiJson;
import top.alanlee.pam.entity.User;
import top.alanlee.pam.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户，返回json数据
     * @return 返回json数据
     */
    @RequestMapping("/get/all")
    public ApiJson getAllUsers(){
        List<User> allUsers = userService.getAllUsers();
        return allUsers != null ? ApiJson.ok(allUsers) : ApiJson.error();
    }
}
