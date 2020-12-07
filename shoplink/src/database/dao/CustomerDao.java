package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.BaseDatabase;
import database.dao.entity.CustomerEntity;

public class CustomerDao
{
    public static CustomerEntity selectByLogInData(String password, String email) throws SQLException
    {
        CustomerEntity dto = null;
        String sql = "SELECT * FROM customer WHERE email = ? AND password = ?";
        Connection con = BaseDatabase.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        try (con; stmt; rs;)
        {
            if (rs.next())
            {
                dto = new CustomerEntity();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setEmail(rs.getString("email"));
                dto.setPassword(rs.getString("password"));
                dto.setAddress(rs.getString("address"));
                dto.setTel(rs.getString("tel"));
                dto.setAdmin(rs.getInt("admin"));
            }
        }
        return dto;
    }
}
