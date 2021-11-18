package com.heima.service.impl;

import com.alibaba.fastjson.JSON;
import com.heima.dao.IUserDao;
import com.heima.dao.impl.UserDaoImpl;
import com.heima.pojo.Client;
import com.heima.pojo.PageBean;
import com.heima.pojo.User;
import com.heima.service.IUserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements IUserService {
    private IUserDao userDao = new UserDaoImpl();

    @Override
    public List<User> findAllUsers() {
        List<User> userList = userDao.findAllUser();
        return userList;
    }

    @Override
    public List<User> deleteUserById(Integer id) {
        userDao.deleteUserById(id);
        List<User> allUser = userDao.findAllUser();
        return allUser;
    }

    @Override
    public User findUserById(String id) {
        User user = userDao.findUserById(id);
        return user;
    }

    @Override
    public List<User> updateUser(User user) {
        //1.调用dao层方法更新数据库数据
        userDao.update(user);
        //2.再次查询数据库信息
        List<User> userList = userDao.findAllUser();
        return userList;
    }

    @Override
    public List<User> addUser(User user) {
        userDao.insert(user);
        List<User> userList = userDao.findAllUser();
        return userList;
    }

    @Override
    public String checkUserByName(String name) {
        //调用dao层的方法去数据库比较
        User user = userDao.checkUserByName(name);
        //声明map集合接受数据，方便json的转换
        Map<String, String> map = new HashMap<>();
        if (user == null) {
            map.put("isExist", "no");
        } else {
            map.put("isExist", "yes");
        }
        String json = JSON.toJSONString(map);
        return json;
    }

    /**
     * 分页查询
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean findUserByPage(int currentPage, int pageSize) {
        PageBean pageBean = new PageBean();
        //3.当前页码数量   currentPage
        pageBean.setCurrentPage(currentPage);
        //1.查询当前页需要的用户信息  userList
        int start = (currentPage - 1) * pageSize;
        List<User> userList = userDao.findUserByPage(start, pageSize);
        pageBean.setUserList(userList);
        //2.每页显示的用户数   pageSize
        pageBean.setPageSize(pageSize);
        //4.上一页  prePage
        pageBean.setPrePage(currentPage - 1);
        //5.下一页  nextPage
        pageBean.setNextPage(currentPage + 1);
        //7.用户的总数量
        int totalCount = userDao.findAllUserCount();
        pageBean.setTotalCount(totalCount);
        //6.总页码数 totalPage 1.0* ???
        int totalPage = (int) Math.ceil(1.0 * totalCount / pageSize);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    @Override
    public List<Client> findClient(String userName, String password) {
        List<Client> clientList = userDao.findClient(userName, password);
        return clientList;
    }
}