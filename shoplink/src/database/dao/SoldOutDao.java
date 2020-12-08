package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.BaseDatabase;

public class SoldOutDao
{
    public static boolean updateProductById(int isSold, String id) throws SQLException
    {
        Connection con = BaseDatabase.getConnection();
        PreparedStatement stmt = con.prepareStatement("UPDATE products SET price = ? WHERE id = ?");
        stmt.setInt(1, isSold);
        stmt.setString(2, id);
        stmt.executeUpdate();
        try (con; stmt;)
        {

        }
        return true;
    }

}
