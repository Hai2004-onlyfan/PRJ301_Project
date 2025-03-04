package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Dummy;

/**
 *
 * @author ADMIN
 */
public class DummyDBContext extends DBContext<Dummy> {

    @Override
    public ArrayList<Dummy> list() {
        ArrayList<Dummy> dummys = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            // Kiểm tra kết nối trước khi sử dụng
            if (connection == null) {
                throw new SQLException("Database connection is not established.");
            }

            String sql = "SELECT d.id, d.name FROM Dummy d";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");  // Đảm bảo đúng tên cột trong câu truy vấn
                String name = rs.getString("name"); // Đảm bảo đúng tên cột trong câu truy vấn
                
                Dummy d = new Dummy();
                d.setId(id);
                d.setName(name);
                
                dummys.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DummyDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đảm bảo đóng tài nguyên PreparedStatement và ResultSet
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                // Không đóng connection nếu nó được quản lý ở lớp cha DBContext
            } catch (SQLException ex) {
                Logger.getLogger(DummyDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dummys;
    }

    @Override
    public Dummy get(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void insert(Dummy model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Dummy model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Dummy model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
