package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.BaseDatabase;
import database.dao.entity.CustomerEntity;

public class InsertCustomerDao
{
    public static boolean insertProfile(CustomerEntity entity) throws SQLException
    {
        Connection con = BaseDatabase.getConnection();
        PreparedStatement stmt = con
                .prepareStatement("INSERT INTO customer (name,email,password,address,tel) VALUES (?,?,?,?,?)");
        stmt.setString(1, entity.getName());
        stmt.setString(2, entity.getEmail());
        stmt.setString(3, entity.getPassword());
        stmt.setString(4, entity.getAddress());
        stmt.setString(5, entity.getTel());
        stmt.executeUpdate();
        try (con; stmt;)
        {
        }
        return true;
    }

}
