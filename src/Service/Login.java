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

/**
 *
 * @author ADMIN
 */
public class Login {
    MyConnection conn;
    
    public Login(){
        conn = new MyConnection();
    }
    
    public List<Tai_Khoan> Get_All() {
        List<Tai_Khoan> List_TK = new ArrayList<>(); //  Tạo một danh sách rỗng kiểu Tai_Khoan để chứa tất cả tài khoản đọc từ database.
        String SQL = "SELECT * FROM TAIKHOAN"; //  Lấy toàn bộ dòng dữ liệu từ bảng TAIKHOAN.
        try {
            Connection connect = conn.DBConnect(); // 
            Statement stm = connect.createStatement();
            ResultSet rs = stm.executeQuery(SQL);
            while (rs.next()) {
                String Ma_TK = rs.getString(1);
                String Ten_TK = rs.getString(2);
                String SDT_TK = rs.getString(3);
                String Email_TK = rs.getString(4);
                String DiaChi_TK = rs.getString(5);
                String VaiTro_TK = rs.getString(5);
                Date Ngay_DK_TK = rs.getDate(6);
                String Anh_TK = rs.getString(7);
                boolean TrangThai_TK = rs.getBoolean(8);
                Tai_Khoan tk = new Tai_Khoan(Ma_TK, Ten_TK, SDT_TK, Email_TK, DiaChi_TK, VaiTro_TK, Ngay_DK_TK, Anh_TK, TrangThai_TK);
                List_TK.add(tk);
            }
        } catch (Exception e) {
        }
        return List_TK;
    }
}
