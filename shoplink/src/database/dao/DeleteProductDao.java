package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.BaseDatabase;

public class DeleteProductDao
{
    public static boolean deleteProductById(String id) throws SQLException
    {
        Connection con = BaseDatabase.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM products WHERE id = ?");
        stmt.setString(1, id);
        stmt.executeUpdate();

        try (con; stmt;)
        {

        }
        return true;
    }
}
