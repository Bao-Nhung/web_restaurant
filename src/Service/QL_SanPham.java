/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DBConnect.MyConnection;
import Model.SanPham;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class QL_SanPham {

    MyConnection conn;

    public QL_SanPham() {
        conn = new MyConnection();
    }

    public List<SanPham> Get_All() {
        List<SanPham> List_SP = new ArrayList<>(); //  Tạo một danh sách rỗng kiểu Sản Phẩm để chứa tất cả tài khoản đọc từ database.
        String SQL = "SELECT * FROM SANPHAM"; //  Lấy toàn bộ dòng dữ liệu từ bảng SANPHAM
        try {
            Connection connect = conn.DBConnect(); // 
            Statement stm = connect.createStatement();
            ResultSet rs = stm.executeQuery(SQL);
            while (rs.next()) {
                String Ma_SP = rs.getString(1);
                String Ten_SP = rs.getString(2);
                String MoTa_SP = rs.getString(3);
                float DonGia_SP = rs.getFloat(5);
                String Ma_LSP = rs.getString(6);
                String HinhAnh_SP = rs.getString(7);
                SanPham nl = new SanPham(Ma_SP, Ten_SP, MoTa_SP, DonGia_SP, Ma_LSP, HinhAnh_SP);
                List_SP.add(nl);
            }
        } catch (Exception e) {
            e.printStackTrace(); // hoặc log ra file/log view
        }
        return List_SP;
    }

    public Object[] GetRow(SanPham sp) {
        String Ma_SP = sp.getMa_SP();
        String Ten_SP = sp.getTen_SP();
        String MoTa_SP = sp.getMoTa_SP();
        float DonGia_SP = sp.getDonGia_SP();
        String Ma_LSP = sp.getMa_LSP();
        String HinhAnh_SP = sp.getHinhAnh_SP();

        Object[] obj = new Object[]{Ma_SP, Ten_SP, MoTa_SP, DonGia_SP, Ma_LSP, HinhAnh_SP};
        return obj;
    }

    // Hàm Thêm Dữ Liệu Vào Tài Khoản
    public int Them_TK(SanPham sp) {
        String SQL = "INSERT INTO SANPHAM  VALUES\n"
                + "(  ?  ,   ?  ,  ?  ,  ?  ,  ?  , ? )"; // Có Hai Cách Giải Quyết Vấn Đề Về Thời Gian Tạo Này
        // Thứ Nhất Là Dùng Luôn Câu Lệnh SQL Là GETDATE() Còn Cái Này Thì Khả Năng Là Không Nhìn Thấy
        // Hai Là Dùng Code Java Thì Dài Ròng Hơn Nhưng Lại Có Lợi Là Nhìn Thấy Được Ở Ô Thời Gian
        try {
            Connection Connect = conn.DBConnect();
            PreparedStatement pstm = Connect.prepareStatement(SQL);
            pstm.setString(1, sp.getMa_SP());
            pstm.setString(2, sp.getTen_SP());
            pstm.setString(3, sp.getMoTa_SP());
            pstm.setFloat(4, sp.getDonGia_SP());
            pstm.setString(5, sp.getMa_LSP());
            pstm.setString(6, sp.getHinhAnh_SP());
            if (pstm.executeUpdate() > 0) {
                System.out.println("Them San Pham. Connect");
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Hàm Xoá Tài Khoản
    public int Xoa_TK(String TheoMa) {
        String SQL = "DELETE FROM SANPHAM WHERE MA_SP = ? ";
        try {
            Connection Connect = conn.DBConnect();
            PreparedStatement pstm = Connect.prepareStatement(SQL);
            pstm.setString(1, TheoMa);
            if (pstm.executeUpdate() > 0) {
                System.out.println("Xoa San Pham. Connect");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    // Hàm Sửa Dữ Liệu Tài Khoản
    public int Sua_TK(SanPham sp, String TheoMa) {
        String SQL = "UPDATE SANPHAM SET MA_SP = ? ,\n"
                + "                      TENSP =  ? ,\n"
                + "		         MOTA = ? ,\n"
                + "			 DONGIA =  ? ,\n"
                + "			 MA_LOAI =  ? ,\n"
                + "			 HINHANH =  ? \n"
                + "			 WHERE MA_SP = ? ";
        try {
            Connection Connect = conn.DBConnect();
            PreparedStatement pstm = Connect.prepareStatement(SQL);
            pstm.setString(1, sp.getMa_SP());
            pstm.setString(2, sp.getTen_SP());
            pstm.setString(3, sp.getMoTa_SP());
            pstm.setFloat(4, sp.getDonGia_SP());
            pstm.setString(5, sp.getMa_LSP());
            pstm.setString(6, sp.getHinhAnh_SP());
            pstm.setString(7, TheoMa);
            if (pstm.executeUpdate() > 0) {
                System.out.println("Sua Du Lieu San Pham. Connect");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }
}
