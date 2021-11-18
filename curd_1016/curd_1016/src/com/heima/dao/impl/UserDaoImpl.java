package com.heima.dao.impl;

import com.heima.dao.IUserDao;
import com.heima.pojo.Client;
import com.heima.pojo.User;
import com.heima.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements IUserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAllUser() {
        String sql = "select * from t_user";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        return userList;
    }

    @Override
    public void deleteUserById(Integer id) {
        String sql = "delete from t_user where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public User findUserById(String id) {
        String sql = "select * from t_user where id = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        } catch (DataAccessException e) {
            System.out.println("id = " + id + "用户不存在,一切正常");
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(User user) {
        String sql = "update t_user set name =?,sex=?,age=?,address=?,qq=?,email=? where id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getSex(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    @Override
    public void insert(User user) {
        //null 是什么,id自增长？
        String sql = "insert into t_user values(null,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getName(), user.getSex(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());

    }

    @Override
    public User checkUserByName(String name) {
        String sql = "select * from t_user where name = ?";
        User user =null;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), name);
        } catch (DataAccessException e) {
            System.out.println("找不到数据，正常");
            e.printStackTrace();
        }
        return user;
    }

    /**
     * @param start  起始位置
     * @param length 查询数量
     * @return
     */
    @Override
    public List<User> findUserByPage(int start, int length) {
        String sql = "select * from t_user limit ?,?";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class),start,length);
        return userList;
    }

    /**
     * 查询 用户总数量
     *
     * @return
     */
    @Override
    public int findAllUserCount() {
        String sql = "select count(*) from t_user";
        int totalCount=jdbcTemplate.queryForObject(sql,Integer.class);
        return totalCount;
    }

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    @Override
    public List<Client> findClient(String userName, String password) {
        String sql = "select * from login where userName = ? and password = ?";
        List<Client> clientList = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Client.class),userName,password);
        return clientList;
    }
}