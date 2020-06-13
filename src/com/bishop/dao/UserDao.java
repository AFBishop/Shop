package com.bishop.dao;

import com.bishop.domain.User;
import com.bishop.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

/**
 * @ClassName UserDao
 * @Author Bishop
 */
public class UserDao {
    QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
    public int regist(User user) throws SQLException {
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
        return queryRunner.update(sql, user.getUid(),user.getUsername(),user.getPassword(),
                user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),
                user.getSex(),user.getState(),user.getCode());
    }

    public Long checkUserName(String username) throws SQLException {
        String sql = "select count(*) from user where username=?";
        return (Long) queryRunner.query(sql, new ScalarHandler(), username);
    }
}
