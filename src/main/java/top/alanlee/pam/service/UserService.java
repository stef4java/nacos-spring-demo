package top.alanlee.pam.service;

import top.alanlee.pam.entity.User;

import java.util.List;

public interface UserService {
    //获取所有用户
    List<User> getAllUsers();
}
