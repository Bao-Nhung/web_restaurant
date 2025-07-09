/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DBConnect.MyConnection;
import Model.Tai_Khoan;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class QL_Login {

    MyConnection conn;

    public QL_Login() {
        conn = new MyConnection();
    }

    public String login(String TheoEmail, String TheoMa_TK) {
        String sql = "SELECT * FROM TAIKHOAN WHERE EMAIL = ? AND MA_TK = ?";

        try {
            Connection connect = conn.DBConnect();
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, TheoEmail);
            ps.setString(2, TheoMa_TK);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String vaiTro = rs.getString("VAITRO");
                return vaiTro; // Trả về vai trò nếu đăng nhập thành công
            } else {
                return null; // Sai email hoặc mã tài khoản
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Lỗi kết nối
        }
    }
}
