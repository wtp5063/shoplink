package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.BaseDatabase;

public class EmailCheckDao
{
    public static String selectCustomerByEmail(String value) throws SQLException
    {
        String result = null;
        Connection con = BaseDatabase.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM customer WHERE email = ?");
        stmt.setString(1, value);
        ResultSet rs = stmt.executeQuery();
        try (con; stmt; rs;)
        {
            if (rs.next())
            {
                result = rs.getString("password");
            }
        }
        return result;
    }

}
