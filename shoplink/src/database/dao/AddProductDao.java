package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.BaseDatabase;

public class AddProductDao
{
    public static boolean insertProduct(String title, String artist, String price, String fileName) throws SQLException
    {
        Connection con = BaseDatabase.getConnection();
        PreparedStatement stmt = con
                .prepareStatement("INSERT INTO products (title,artist,price,images) VALUE (?,?,?,?)");
        stmt.setString(1, title);
        stmt.setString(2, artist);
        stmt.setString(3, price);
        stmt.setString(4, fileName);
        stmt.executeUpdate();
        try (con; stmt;)
        {

        }
        return true;
    }

}
