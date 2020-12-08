package database.dao.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.BaseDatabase;

public class CustomerSelectDao
{
    public static CustomerEntity selectCustomer(String id) throws SQLException
    {
        Connection con = BaseDatabase.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM customer WHERE id = ?");
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        CustomerEntity entity = new CustomerEntity();

        try (con; stmt; rs;)
        {
            if (rs.next())
            {
                entity.setId(rs.getInt("id"));
                entity.setName(rs.getString("name"));
                entity.setEmail(rs.getString("email"));
                entity.setPassword(rs.getString("password"));
                entity.setAddress(rs.getString("address"));
                entity.setTel(rs.getString("tel"));
            }
        }
        return entity;
    }

}
