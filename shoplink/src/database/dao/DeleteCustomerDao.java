package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.BaseDatabase;

public class DeleteCustomerDao
{
    public static boolean deleteCustomerById(String id) throws SQLException
    {
        Connection con = BaseDatabase.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM customer WHERE id = ?");
        stmt.setString(1, id);
        stmt.executeUpdate();

        try (con; stmt;)
        {

        }
        return true;
    }

}
