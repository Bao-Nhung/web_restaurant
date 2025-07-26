/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_TaiKhoan;

import DBConnect.MyConnection;
import ToanBo_TaiKhoan.Tai_Khoan;
import ToanBo_TaiKhoan.TaiKhoan_4_O;
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
    
    public TaiKhoan_4_O getTaiKhoanTheoEmail(String Email) {
        String sql = "SELECT EMAIL , MA_TK , VAITRO , ANH_TK FROM TAIKHOAN WHERE EMAIL =   ?  ";
        TaiKhoan_4_O tk = null;
        try {
            Connection conect = conn.DBConnect();
            PreparedStatement ps = conect.prepareStatement(sql);
            ps.setString(1, Email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tk = new TaiKhoan_4_O();
                tk.setEmail_TK(rs.getString("EMAIL"));
                tk.setMa_TK(rs.getString("MA_TK"));
                tk.setVaiTro_TK(rs.getString("VAITRO"));
                tk.setAnh_TK(rs.getString("ANH_TK"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tk;
    }

}
