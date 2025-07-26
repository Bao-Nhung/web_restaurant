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

    public List<HoaDon> Get_All_HoaDon() {
        List<HoaDon> List_HD = new ArrayList<>();
        String SQL = "SELECT * FROM HOADON";
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
                String TrangThai = rs.getString(6);
                String Ma_KM = rs.getString(7);
                float SoTienKhachTra_HD = rs.getFloat(8);
                String Ma_KH = rs.getString(9);
                int TichDiem = rs.getInt(10);
                HoaDon hd = new HoaDon(Ma_HD, Ma_TK, NgayLap_HD, TongTien, HinhThuc_HD, TrangThai, Ma_KM, SoTienKhachTra_HD, Ma_KH, TichDiem);
                List_HD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List_HD;
    }

    public Object[] Get_Row_HD(HoaDon hd) {
        String Ma_HD = hd.getMa_HD();
        String Ma_TK = hd.getMa_TK();
        Date NgayLap_HD = hd.getNgayLap_HD();
        float TongTien = hd.getTongTien();
        String HinhThuc_HD = hd.getHinhThuc_HD();
        String TrangThai = hd.getTrangThai();
        String Ma_KM = hd.getMa_KM();
        float SoTienKhachTra_HD = hd.getSoTienKhachTra_HD();
        String Ma_KH = hd.getMa_KH();
        int TichDiem = hd.getTichDiem();
        Object[] obj = new Object[]{Ma_HD, Ma_TK, NgayLap_HD, TongTien, HinhThuc_HD, TrangThai, Ma_KM, SoTienKhachTra_HD, Ma_KH, TichDiem};
        return obj;
    }

    public int Tao_HD(HoaDon hd) {
        String SQL = "INSERT INTO HoaDon (MA_HD, MA_TK, NGAYLAP, TONGTIEN, HINHTHUC_HD, TRANGTHAI, MA_KM, SOTIEN_KHACHTRA, MA_KH, TICHDIEM) "
                   + "VALUES             (  ?  ,   ?  ,    ?   ,     ?   ,     ?      ,     ?    ,   ?  ,       ?        ,    ? ,    ?     )";
        try {
            Connection connect = conn.DBConnect();
            PreparedStatement pstm = connect.prepareStatement(SQL);

            pstm.setString(1, hd.getMa_HD());
            pstm.setString(2, hd.getMa_TK());
            pstm.setDate(3, hd.getNgayLap_HD());
            pstm.setFloat(4, hd.getTongTien());
            pstm.setString(5, hd.getHinhThuc_HD());
            pstm.setString(6, hd.getTrangThai());
            pstm.setString(7, hd.getMa_KM());
            pstm.setFloat(8, hd.getSoTienKhachTra_HD());
            pstm.setString(9, hd.getMa_KH());
            pstm.setInt(10, hd.getTichDiem());

            if (pstm.executeUpdate() > 0) {
                return 1; // Thành công
            }
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra để dễ debug
        }
        return 0; // Thất bại
    }

    public int Sua_HD(HoaDon hd, String TheoMa_HD) {
        String SQL = "UPDATE HOADON SET MA_HD =  ? ,\n"
                + "                     MA_TK =  ? ,\n"
                + "			NGAYLAP =  ?  ,\n"
                + "			TONGTIEN  =  ? ,\n"
                + "			HINHTHUC =  ? ,\n"
                + "			TRANGTHAI  =  ? ,\n"
                + "			MA_GIAM =  ? ,\n"
                + "			SOTIENCATRA =  ? , \n"
                + "			MA_KH  =  ? ,\n"
                + "			TICHDIEM =  ? \n"
                + "			WHERE MA_HD =  ? "; // Điều kiện cập nhật

        try {
            Connection connect = conn.DBConnect();
            PreparedStatement pstm = connect.prepareStatement(SQL);// Gán giá trị vào các dấu ? theo thứ tự
            pstm.setString(1, hd.getMa_HD());
            pstm.setString(2, hd.getMa_TK());
            pstm.setDate(3, hd.getNgayLap_HD());
            pstm.setFloat(4, hd.getTongTien());
            pstm.setString(5, hd.getHinhThuc_HD());
            pstm.setString(6, hd.getTrangThai());
            pstm.setString(7, hd.getMa_KM());
            pstm.setFloat(8, hd.getSoTienKhachTra_HD());
            pstm.setString(9, hd.getMa_KH());
            pstm.setInt(10, hd.getTichDiem());
            pstm.setString(11, TheoMa_HD); // điều kiện WHERE

            if (pstm.executeUpdate() >= 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
