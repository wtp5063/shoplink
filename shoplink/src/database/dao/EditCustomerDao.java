package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.BaseDatabase;
import database.dao.entity.CustomerEntity;

public class EditCustomerDao
{
    public static boolean updateCustomerById(CustomerEntity entity) throws SQLException
    {
        Connection con = BaseDatabase.getConnection();
        PreparedStatement stmt = con.prepareStatement("UPDATE customer SET name = ?, address = ?, tel = ? WHERE id = ?");
        stmt.setString(1, entity.getName());
        stmt.setString(2, entity.getAddress());
        stmt.setString(3, entity.getTel());
        stmt.setInt(4, entity.getId());
        stmt.executeUpdate();
        try (con; stmt;)
        {

        }
        return true;
    }

}
