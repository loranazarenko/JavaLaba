package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.EntityMapper;
import com.epam.onlinestore.dao.Fields;
import com.epam.onlinestore.dao.connection.DBManager;
import com.epam.onlinestore.dao.connection.DbConst;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * a Layer class that contains methods for working with a database with a Product
 */
@Slf4j
public class ProductDAOImpl {

    private static ProductDAOImpl productDAO;
    Connection connection = DBManager.getConnection();

    public ProductDAOImpl() throws SQLException {
    }

    public static ProductDAOImpl getInstance() throws IOException, SQLException {
        if (productDAO == null) {
            productDAO = new ProductDAOImpl();
        }
        return productDAO;
    }

    public Optional<Product> findByName(String name) throws DaoException {
        List<Product> entities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(DbConst.FIND_PRODUCT_BY_NAME_QUERY, Statement.RETURN_GENERATED_KEYS);
             ResultSet resultSet = statement.executeQuery()) {
            ProductMapper mapper = new ProductMapper();
            if (resultSet.next()) {
                Product product = mapper.mapRow(resultSet);
                entities.add(product);
            }
        } catch (SQLException e) {
            log.error("Unable to execute query", e);
            throw new DaoException(e.getMessage(), e);
        }
        return Optional.of(entities.get(0));
    }

    public Product getProductsById(Long productId) throws DaoException {
        Product product = null;
        try {
            PreparedStatement statement = connection.prepareStatement(DbConst.FIND_PRODUCTS_BY_ID_QUERY);
            statement.setLong(1, productId);
            ResultSet resultSet = statement.executeQuery();
            ProductMapper mapper = new ProductMapper();
            while (resultSet.next()) {
                product = mapper.mapRow(resultSet);
            }
        } catch (SQLException e) {
            log.error("Unable to execute query", e);
            throw new DaoException(e.getMessage(), e);
        }
        return product;
    }

    public void updateById(long id, Object... params) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbConst.UPDATE_PRODUCT_QUERY)) {
            for (int i = 1; i <= params.length; i++) {
                preparedStatement.setObject(i, params[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Unable to execute query", e);
            throw new DaoException(e.getMessage(), e);
        }
    }

    public Product save(Product product) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DbConst.SAVE_PRODUCT_QUERY
                , Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getQuantity());
            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                long id = generatedKey.getLong(1);
                product.setId(id);
            }
        } catch (SQLException e) {
            log.error("Unable to execute insert query", e);
        }
        return product;

    }

    public void updateProduct(Product product) {
        try (PreparedStatement st = connection.prepareStatement(DbConst.UPDATE_PRODUCT_QUERY)) {
            st.setString(1, product.getName());
            st.setString(2, product.getDescription());
            st.setDouble(3, product.getPrice());
            st.setInt(4, product.getQuantity());
            st.setLong(5, product.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            log.error("Unable to execute query", e);
        }
    }

    public List<Product> findAll() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DbConst.SQL_LIST_PRODUCTS);
            preparedStatement.setInt(1, 0);
            ResultSet resultSet = preparedStatement.executeQuery();
            ProductMapper mapper = new ProductMapper();
            while (resultSet.next()) {
                products.add(mapper.mapRow(resultSet));
            }
        } catch (Exception ex) {
            log.error("Unable to find all products!", ex);
        }
        return products;
    }

    public void deleteProduct(Product product) {
        try {
            String REMOVE_PRODUCT = "DELETE FROM product where id = ?;";
            Connection connection = DBManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(REMOVE_PRODUCT);
            statement.setLong(1, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error while removing product: " + product, e);
        }
    }

    /**
     * Extracts a product from the result set row.
     */
    private static class ProductMapper implements EntityMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs) {
            try {
                Product product = new Product();
                product.setId(rs.getLong(Fields.PRODUCT__ID));
                product.setName(rs.getString(Fields.PRODUCT__NAME));
                product.setDescription(rs.getString(Fields.PRODUCT__DESCRIPTION));
                product.setPrice(rs.getDouble(Fields.PRODUCT__PRICE));
                product.setQuantity(rs.getInt(Fields.PRODUCT__COUNT));
                return product;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }


}
