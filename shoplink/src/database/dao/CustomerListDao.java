package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.BaseDatabase;
import database.dao.entity.CustomerEntity;

public class CustomerListDao
{
    public static List<CustomerEntity> selectCustomerList() throws SQLException
    {

        List<CustomerEntity> list = new ArrayList<CustomerEntity>();

        Connection con = BaseDatabase.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM customer WHERE admin = 2");
        ResultSet rs = stmt.executeQuery();

        try (con; stmt; rs;)
        {
            while (rs.next())
            {
                CustomerEntity entity = new CustomerEntity();
                entity.setId(rs.getInt("id"));
                entity.setName(rs.getString("name"));
                entity.setEmail(rs.getString("email"));
                entity.setPassword(rs.getString("password"));
                entity.setAddress(rs.getString("address"));
                entity.setTel(rs.getString("tel"));
                entity.setAdmin(rs.getInt("admin"));
                list.add(entity);
            }
        }
        return list;
    }

}
