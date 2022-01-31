package com.epam.onlinestore.dao.connection;

/**
* Constants for storing query texts
*/
public abstract class DbConst {

    public static final String SQL_LIST_PRODUCTS = "SELECT * FROM product WHERE is_deleted=?";
    public static final String FIND_PRODUCT_BY_NAME_QUERY = "SELECT * FROM product WHERE name=?";
    public static final String SAVE_PRODUCT_QUERY = "INSERT INTO product " +
            " (name, description, price, count) VALUES (?, ?, ?, ?)";
    public static final String UPDATE_PRODUCT_QUERY = "UPDATE product SET name=?, " +
            "description=?, price=?, count=? WHERE id=?";
    public static final String FIND_PRODUCTS_BY_ID_QUERY = "SELECT * FROM product WHERE id=?";

    public static final String INSERT_ACCOUNT = "INSERT INTO account (login, password, role_id) VALUES (?,?,?)";
    public static final String GET_ALL_USERS = "SELECT * FROM account ORDER BY id;";
    public static final String FIND_USER_BY_ID = "SELECT * FROM account WHERE id=?";
    public static final String FIND_USER_BY_LOGIN = "SELECT * FROM account WHERE login=?";
    public static final String UPDATE_ACCOUNT = "UPDATE account" +
            " SET " +
            " login=?," +
            " password=?" +
            " WHERE id=?";

    private DbConst() {
    }

}
