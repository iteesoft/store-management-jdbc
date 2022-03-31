package com.iteesoft.servlet.dao;

import com.iteesoft.servlet.JdbcUtil;
import com.iteesoft.servlet.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private final JdbcUtil jdbc;
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product (name, description, price, quantity) VALUES (?, ?, ?, ?)";
    private static final String SELECT_PRODUCT_SQL = "SELECT * FROM product";
    private static final String SELECT_PRODUCT_BY_ID_SQL = "SELECT * FROM product WHERE product_id = ?";
    private static final String DELETE_PRODUCT_SQL = "DELETE FROM product where product_id = ?";
    private static final String UPDATE_PRODUCT_SQL = "UPDATE product SET name = ?, description = ?, price = ?, quantity = ? WHERE product_id = ?";


    public ProductDao() {
        jdbc = new JdbcUtil();
    }

    public boolean insertProduct(Product product) {
        boolean rowInserted = false;
        try(Connection connection = jdbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_PRODUCT_SQL);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setFloat(3, product.getPrice());
            statement.setInt(4, product.getQuantity());

            rowInserted = statement.executeUpdate() > 0;
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInserted;
    }

    public List<Product> listAllProducts() throws SQLException {
        List<Product> listProduct = new ArrayList<>();
        try(Connection connection = jdbc.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_PRODUCT_SQL);

            while (resultSet.next()) {
                int id = resultSet.getInt("product_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                float price = resultSet.getFloat("price");
                int quantity = resultSet.getInt("quantity");

                Product product = new Product(id, name, description, price, quantity);
                listProduct.add(product);
            }

            resultSet.close();
            statement.close();
        }

        return listProduct;
    }

    public boolean deleteProduct(Product product) {
        boolean rowDeleted = false;
        try (Connection connection = jdbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL);
            statement.setInt(1, product.getId());

            rowDeleted = statement.executeUpdate() > 0;
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowDeleted;
    }

    public boolean updateProduct(Product product) {
        boolean rowUpdated = false;
        try (Connection connection = jdbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setFloat(3, product.getPrice());
            statement.setInt(4, product.getQuantity());
            statement.setInt(4, product.getId());

            rowUpdated = statement.executeUpdate() > 0;
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowUpdated;
    }

    public Product getProduct(int id) throws SQLException {
        Product product = null;

        Connection connection = jdbc.getConnection();

        PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCT_BY_ID_SQL);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            float price = resultSet.getFloat("price");

            product = new Product(id, name, description, price);
        }

        resultSet.close();
        statement.close();

        return product;
    }
}
