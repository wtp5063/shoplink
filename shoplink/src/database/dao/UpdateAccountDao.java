package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.BaseDatabase;
import database.dao.entity.CustomerEntity;

public class UpdateAccountDao
{
    public static CustomerEntity updateAccountByName(String name, String email, String password, String address, String tel,
            int id) throws SQLException
    {
        Connection con = BaseDatabase.getConnection();
        PreparedStatement stmt = con
                .prepareStatement("UPDATE customer SET name=?,email=?,password=?,address=?,tel=? WHERE id = ?");
        stmt.setString(1, name);
        stmt.setString(2, email);
        stmt.setString(3, password);
        stmt.setString(4, address);
        stmt.setString(5, tel);
        stmt.setInt(6, id);
        stmt.executeUpdate();
        try (con; stmt;)
        {

        }

        CustomerEntity dto = new CustomerEntity();
        Connection con2 = BaseDatabase.getConnection();
        PreparedStatement stmt2 = con2.prepareStatement("SELECT * FROM customer WHERE id = ?");
        stmt2.setInt(1, id);
        ResultSet rs = stmt2.executeQuery();
        try (con2; stmt2; rs;)
        {
            if (rs.next())
            {
                dto.setName(rs.getString("name"));
                dto.setEmail(rs.getString("email"));
                dto.setAddress(rs.getString("address"));
                dto.setTel(rs.getString("tel"));
            }
        }
        return dto;
    }
}
