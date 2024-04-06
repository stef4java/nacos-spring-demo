package top.alanlee.pam.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.alanlee.pam.entity.User;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    //获取所有用户
    List<User> getAllUsers();
}
