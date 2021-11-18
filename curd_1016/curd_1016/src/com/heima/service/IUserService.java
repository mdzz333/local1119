package com.heima.service;

import com.heima.pojo.Client;
import com.heima.pojo.PageBean;
import com.heima.pojo.User;

import java.util.List;

public interface IUserService {
    /**
     * 查询所有用户信息
     * @return List<User>
     */
    List<User> findAllUsers();

    /**
     * 根据用户的id删除用户的信息
     * @param id
     * @return List<User>
     */
    List<User> deleteUserById(Integer id);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    User findUserById(String id);

    /**
     * 修改用户的信息
     * @param user
     * @return 修改后数据库所有信息
     */
    List<User> updateUser(User user);

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    List<User> addUser(User user);

    /**
     * 根据name查询是否重复
     * @param name
     * @return
     */
    String checkUserByName(String name);

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean findUserByPage(int currentPage,int pageSize);

    /**
     * 用户登录
      * @param userName
     * @param password
     * @return
     */
    List<Client> findClient(String userName,String password);
}
