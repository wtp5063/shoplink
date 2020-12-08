package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.BaseDatabase;

public class EditProductDao
{
    public static boolean updateProductById(String id, String title, String artist, String price) throws SQLException
    {
        Connection con = BaseDatabase.getConnection();
        PreparedStatement stmt = con.prepareStatement("UPDATE products SET title=?, artist=?, price=? WHERE id = ?");
        stmt.setString(1, title);
        stmt.setString(2, artist);
        stmt.setString(3, price);
        stmt.setString(4, id);
        stmt.executeUpdate();
        try (con; stmt;)
        {

        }
        return true;
    }

}
