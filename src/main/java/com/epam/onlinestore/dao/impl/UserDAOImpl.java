package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.EntityMapper;
import com.epam.onlinestore.dao.Fields;
import com.epam.onlinestore.dao.UserDAO;
import com.epam.onlinestore.dao.connection.DBManager;
import com.epam.onlinestore.dao.connection.DbConst;
import com.epam.onlinestore.entity.User;
import com.epam.onlinestore.exception.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class UserDAOImpl implements UserDAO {

    @Override
    public User createUser(String login, String password) throws SQLException {
        Connection connection = DBManager.getConnection();
        ResultSet keys;
        User account = new User();
        try {
            PreparedStatement pstm = connection.prepareStatement(DbConst.INSERT_ACCOUNT, PreparedStatement.RETURN_GENERATED_KEYS);
            int i = 0;
            pstm.setString(++i, escapeForInjection(login));
            pstm.setString(++i, escapeForInjection(password));
            if (pstm.executeUpdate() > 0) {
                keys = pstm.getGeneratedKeys();
                if (keys.next()) {
                    int id = keys.getInt(1);
                    account.setId(id);
                    account.setLogin(login);
                    account.setPassword(password);
                }
            }
            connection.commit();
        } catch (SQLException e) {
            log.error("Cant insert user to Database, try later", e);
        }
        return account;
    }

    @Override
    public List<User> getAll() throws SQLException {
        ArrayList<User> accounts = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(DbConst.GET_ALL_USERS);
            UserMapper mapper = new UserMapper();
            while (resultSet.next()) {
                User account = mapper.mapRow(resultSet);
                accounts.add(account);
            }
        } catch (Exception ex) {
            log.error("Unable to find all accounts!", ex);
        }
        return accounts;
    }

    @Override
    public User getByLogin(String login) throws DaoException {
        DBManager dbManager = DBManager.getInstance();
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;
        try {
            log.error("In getByLogin");
            connection = DBManager.getConnection();
            pstm = connection.prepareStatement(DbConst.FIND_USER_BY_LOGIN);
            pstm.setString(1, login);
            rs = pstm.executeQuery();
            UserMapper mapper = new UserMapper();
            while (rs.next()) {
                user = mapper.mapRow(rs);
            }
            connection.commit();
        } catch (SQLException e) {
            log.error("Cant get user from Database, try later");
        }
        return user;
    }

    @Override
    public User getById(long id) {
        DBManager dbManager = DBManager.getInstance();
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;
        try {
            log.error("In getByLogin");
            connection = DBManager.getConnection();
            pstm = connection.prepareStatement(DbConst.FIND_USER_BY_ID);
            pstm.setLong(1, id);
            rs = pstm.executeQuery();
            UserMapper mapper = new UserMapper();
            while (rs.next()) {
                user = mapper.mapRow(rs);
            }
            connection.commit();
        } catch (SQLException e) {
            log.error("Cant get user from Database, try later");
        }
        return user;
    }

    @Override
    public Optional<User> updateUser(User account) {
        Connection con;
        PreparedStatement pstm;
        log.error("Username: " + account.getLogin());
        try {
            con = DBManager.getConnection();
            pstm = con.prepareStatement(DbConst.UPDATE_ACCOUNT);
            pstm.setString(1, account.getLogin());
            pstm.setString(2, account.getPassword());
            pstm.setLong(3, account.getId());
            pstm.executeUpdate();
            log.trace("Update done");
        } catch (SQLException e) {
            log.error(String.valueOf(e));
        }
        return Optional.of(account);
    }

    @Override
    public User updateUser(String login, User account) {
        Connection con;
        PreparedStatement pstm;
        log.error("Username: " + account.getLogin());
        try {
            con = DBManager.getConnection();
            pstm = con.prepareStatement(DbConst.UPDATE_ACCOUNT);
            pstm.setString(1, account.getLogin());
            pstm.setString(2, account.getPassword());
            pstm.setLong(3, account.getId());
            pstm.executeUpdate();
            log.trace("Update done");
        } catch (SQLException e) {
            log.error(String.valueOf(e));
        }
        return account;
    }


    /**
     * Extracts an account from the result set row.
     */
    private static class UserMapper implements EntityMapper<User> {
        @Override
        public User mapRow(ResultSet rs) {
            try {
                User account = new User();
                account.setId(rs.getLong(Fields.ACCOUNT__ID));
                account.setLogin(rs.getString(Fields.ACCOUNT__LOGIN));
                account.setPassword(rs.getString(Fields.ACCOUNT__PASSWORD));
                return account;
            } catch (SQLException e) {
                log.error(e.getMessage());
                return null;
            }
        }
    }

    static String escapeForInjection(String param) {
        return param.replace("!", "!!").
                replace("<", "!<").
                replace(">", "!").
                replace("%", "!%").
                replace("_", "!_").
                replace("[", "![");
    }

}
