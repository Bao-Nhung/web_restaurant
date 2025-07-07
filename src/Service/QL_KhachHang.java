/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DBConnect.MyConnection;
import Model.KhachHang;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ADMIN
 */
public class QL_KhachHang {

    MyConnection conn;

    public QL_KhachHang() {
        conn = new MyConnection();
    }

//    private String Ma_KH;
//    private String Ten_KH;
//    private String SDT_KH;
//    private String Email_KH;
//    private Date NgayTao_KH;
//    private String Loai_KH;
    public List<KhachHang> Get_All() {
        List<KhachHang> List_KH = new ArrayList<>();
        String SQL = "SELECT * FROM KHACHHANG";

        try {
            Connection connect = conn.DBConnect();
            Statement stm = connect.createStatement();
            ResultSet rs = stm.executeQuery(SQL);

            DateTimeFormatter DinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // dùng định dạng Việt Nam

            while (rs.next()) {
                String Ma_KH = rs.getString(1);
                String Ten_KH = rs.getString(2);
                String SDT_KH = rs.getString(3);
                String Email_KH = rs.getString(4);

                // Đọc ngày dưới dạng chuỗi
                String ngayTaoStr = rs.getString(5);
                Date NgayTao_KH = null;
                try {
                    LocalDate localDate = LocalDate.parse(ngayTaoStr, DinhDang);
                    NgayTao_KH = Date.valueOf(localDate);
                } catch (Exception ex) {
                    System.out.println("⚠️ Ngày sai định dạng tại mã KH = " + Ma_KH + ": " + ngayTaoStr);
                    NgayTao_KH = Date.valueOf(LocalDate.now());
                }

                String Loai_KH = rs.getString(6);
                int DiemTichLuy = rs.getInt(6);

                KhachHang kh = new KhachHang(Ma_KH, Ten_KH, SDT_KH, Email_KH, NgayTao_KH, Loai_KH, DiemTichLuy);
                List_KH.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return List_KH;
    }

    public Object[] GetRow(KhachHang kh) {
        String Ma_KH = kh.getMa_KH();
        String Ten_KH = kh.getTen_KH();
        String SDT_KH = kh.getSDT_KH();
        String Email_KH = kh.getEmail_KH();
        Date NgayTao_KH = kh.getNgayTao_KH();
        String Loai_KH = kh.getLoai_KH();
        int DiemTichLuy = kh.getDiemTichLuy();

        Object[] obj = new Object[]{Ma_KH, Ten_KH, SDT_KH, Email_KH, NgayTao_KH, Loai_KH, DiemTichLuy};
        return obj;
    }

    // Hàm Thêm Dữ Liệu Vào Tài Khoản
    public int Them_KH(KhachHang kh) {
        String SQL = "INSERT INTO KHACHHANG  VALUES\n"
                + "(  ?  ,   ?   ,   ?   ,  ?  ,  ?  , ?)";
        try {
            Connection Connect = conn.DBConnect();
            PreparedStatement pstm = Connect.prepareStatement(SQL);
            pstm.setString(1, kh.getMa_KH());
            pstm.setString(2, kh.getTen_KH());
            pstm.setString(3, kh.getSDT_KH());
            pstm.setString(4, kh.getEmail_KH());
            pstm.setDate(5, kh.getNgayTao_KH());
            pstm.setString(6, kh.getLoai_KH());
            pstm.setInt(7, kh.getDiemTichLuy());
            if (pstm.executeUpdate() > 0) {
                System.out.println("Them Khach Hang. Connect");
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Hàm Xoá Tài Khoản
    public int Xoa_KH(String TheoMa) {
        String SQL = "DELETE FROM KHACHHANG WHERE MA_KH = ? ";
        try {
            Connection Connect = conn.DBConnect();
            PreparedStatement pstm = Connect.prepareStatement(SQL);
            pstm.setString(1, TheoMa);
            if (pstm.executeUpdate() > 0) {
                System.out.println("Xoa Khach Hang. Connect");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    // Hàm Sửa Dữ Liệu Tài Khoản
    public int Sua_KH(KhachHang kh, String TheoMa) {
        String SQL = "UPDATE KHACHHANG SET MA_KH =  ? ,\n"
                + "                        HOTEN =  ? ,\n"
                + "			   SDT =  ? ,\n"
                + "			   NGAYTAO = ? ,\n"
                + "			   LOAI_KH =  ? ,\n"
                + "	     	           DIEM_TICHLUY = ? \n"
                + "			   WHERE MA_KH = ? ";
        try {
            Connection Connect = conn.DBConnect();
            PreparedStatement pstm = Connect.prepareStatement(SQL);
            pstm.setString(1, kh.getMa_KH());
            pstm.setString(2, kh.getTen_KH());
            pstm.setString(3, kh.getSDT_KH());
            pstm.setString(4, kh.getEmail_KH());
            pstm.setDate(5, kh.getNgayTao_KH());
            pstm.setString(6, kh.getLoai_KH());
            pstm.setInt(7, kh.getDiemTichLuy());
            pstm.setString(8, kh.getMa_KH());
            if (pstm.executeUpdate() > 0) {
                System.out.println("Sua Du Lieu Khach Hang. Connect");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }
}
