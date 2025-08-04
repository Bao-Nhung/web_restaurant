/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_ThongKe;

import DBConnect.MyConnection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class QL_DoanhThu {

    MyConnection conn;

    public QL_DoanhThu() {
        conn = new MyConnection();
    }

    public float layTongDoanhThuHomNay() {
        float tongDoanhThu = 0f;

        String sql = "SELECT SUM(THANHTIEN) AS DOANHTU_HOMNAY "
                + "FROM THANHTOAN "
                + "WHERE CAST(NGAYTT AS DATE) = CAST(GETDATE() AS DATE)";

        try {
            Connection conect = conn.DBConnect();
            PreparedStatement ps = conect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tongDoanhThu = rs.getFloat("DOANHTU_HOMNAY");
            }

        } catch (SQLException e) {
            System.err.println("❌ Lỗi lấy doanh thu hôm nay: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QL_DoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tongDoanhThu;
    }

    public int layTongSanPhamTheoNgay(Date NgayChon) {
        int Tong_SoLuong = 0;
        Connection conect = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conect = conn.DBConnect(); // Lấy kết nối từ helper

            String sql = "SELECT SUM(SOLUONG) FROM CTHOADON  \n"
                    + "             JOIN HOADON ON CTHOADON.MA_HD = HOADON.MA_HD \n"
                    + "             WHERE CAST(HOADON.NGAYLAP AS DATE) = ?";
            stmt = conect.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(NgayChon.getTime()));

            rs = stmt.executeQuery();
            if (rs.next()) {
                Tong_SoLuong = rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Log lỗi hoặc xử lý exception riêng
        } finally {
        }
        return Tong_SoLuong;
    }
}
