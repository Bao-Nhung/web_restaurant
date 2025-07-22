/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_BanHang;

import DBConnect.MyConnection;
import ToanBo_BanHang.HoaDon;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class QL_Tao_HoaDon {
    
    MyConnection conn;

    public QL_Tao_HoaDon() {
        conn = new MyConnection();
        
    }
//    private String Ma_HD;
//    private String Ma_TK;
//    private Date NgayLap_HD;
//    private float TongTien;
//    private String HinhThuc_HD;
//    private String TrangThai;
//    private String Ma_KM;
//    private float SoTienKhachTra_HD;
//    private String Ma_KH;
    public List<HoaDon> Get_All_HoaDon(){
        List<HoaDon> List_HD = new ArrayList<>();
        String SQL = "";
        try {
            Connection connect = conn.DBConnect();
            Statement stm = connect.createStatement();
            ResultSet rs = stm.executeQuery(SQL);
            while (rs.next()) {                
                String Ma_HD = rs.getString(1);
                String Ma_TK = rs.getString(2);
                Date NgayLap_HD = rs.getDate(3);
                float TongTien = rs.getFloat(4);
                String HinhThuc_HD = rs.getString(5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List_HD;
    }
}
