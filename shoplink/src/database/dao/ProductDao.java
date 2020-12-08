package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.BaseDatabase;
import database.dao.entity.ProductEntity;

public class ProductDao
{
    public static List<ProductEntity> selectAllProduct() throws SQLException
    {
        List<ProductEntity> list = new ArrayList<>();

        String sql = "SELECT * FROM products";
        Connection con = BaseDatabase.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        try(con; stmt; rs;)
        {
            while (rs.next())
            {
                ProductEntity entity = new ProductEntity();
                entity.setId(rs.getInt("id"));
                entity.setTitle(rs.getString("title"));
                entity.setArtist(rs.getString("artist"));
                entity.setPrice(rs.getInt("price"));
                entity.setImages(rs.getString("images"));
                list.add(entity);
            }
        }
        return list;
    }
}
