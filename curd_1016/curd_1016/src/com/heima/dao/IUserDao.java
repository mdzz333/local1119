package com.heima.dao;

import com.heima.pojo.Client;
import com.heima.pojo.User;

import java.util.List;

public interface IUserDao {
    List<User> findAllUser();
    void deleteUserById(Integer id);
    User findUserById(String id);
    void update(User user);
    void insert(User user);
    User checkUserByName(String name);
    List<User> findUserByPage(int start,int length);
    int findAllUserCount();
    List<Client> findClient(String userName,String password);
}
