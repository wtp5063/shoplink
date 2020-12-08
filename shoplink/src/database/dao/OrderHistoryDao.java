package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import database.BaseDatabase;
import database.dao.entity.DetailsEntity;
import entry.HistoryDTO;

public class OrderHistoryDao
{
    public static List<HistoryDTO> selectOrderHistory(int user_id) throws SQLException
    {
        List<HistoryDTO> history_list = new ArrayList<>();

        Connection con = BaseDatabase.getConnection();
        PreparedStatement stmt_order = con.prepareStatement("SELECT * FROM new_order WHERE customer_id = ?");
        stmt_order.setInt(1, user_id);
        ResultSet rs_order = stmt_order.executeQuery();

        try (con; stmt_order; rs_order;)
        {

            while (rs_order.next())
            {
                HistoryDTO history = new HistoryDTO();
                history.setId(rs_order.getInt("id"));
                Timestamp ts = rs_order.getTimestamp("datetime");
                LocalDateTime ldt = ts.toLocalDateTime();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd hh:mm:ss");
                history.setDatetime(dtf.format(ldt));
                history.setTotalAmount(rs_order.getInt("total_amount"));
                history_list.add(history);
            }
        }

        for (HistoryDTO hist : history_list)
        {
            List<DetailsEntity> details = new ArrayList<>();
            Connection con2 = BaseDatabase.getConnection();
            PreparedStatement stmt_details = con2.prepareStatement("SELECT * FROM details WHERE order_id = ?");
            stmt_details.setInt(1, hist.getId());
            ResultSet rs_details = stmt_details.executeQuery();

            try (con2; stmt_details; rs_details;)
            {
                while (rs_details.next())
                {
                    DetailsEntity entity = new DetailsEntity();
                    entity.setOrder_id(rs_details.getInt("order_id"));
                    entity.setProduct_id(rs_details.getString("product_id"));
                    entity.setPrice(rs_details.getInt("price"));
                    entity.setQuantity(rs_details.getInt("quantity"));
                    details.add(entity);
                }
            }
            hist.setList(details);
        }

        return history_list;

    }
}
