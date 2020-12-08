package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.BaseDatabase;
import database.dao.entity.ProductEntity;

public class SelectProductByIdDao
{
    public static ProductEntity selectProductById(String id) throws SQLException
    {
        ProductEntity entity = new ProductEntity();
        Connection con = BaseDatabase.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM products WHERE id = ?");
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        try (con; stmt; rs;)
        {
            if (rs.next())
            {
                entity.setId(rs.getInt("id"));
                entity.setTitle(rs.getString("title"));
                entity.setArtist(rs.getString("artist"));
                entity.setPrice(rs.getInt("price"));
                entity.setImages(rs.getString("images"));
            }
        }
        return entity;
    }

}
