/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_SanPham;

import DBConnect.MyConnection;
import ToanBo_SanPham.SanPham;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;

/**
 *
 * @author ADMIN
 */
public class QL_Tao_SanPham {

    MyConnection conn;

    public QL_Tao_SanPham() {
        conn = new MyConnection();
    }

    public List<SanPham> GetAll_SP() {
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
                float DonGia_SP = rs.getFloat(4);
                String Ma_LSP = rs.getString(5);
                String HinhAnh_SP = rs.getString(6);
                Date NgayTao_SP = rs.getDate(7);
                String TrangThai_SP = rs.getString(8);
                SanPham sp = new SanPham(Ma_SP, Ten_SP, MoTa_SP, DonGia_SP, Ma_LSP, HinhAnh_SP, NgayTao_SP , TrangThai_SP);
                List_SP.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace(); // hoặc log ra file/log view
        }
        return List_SP;
    }

    public Object[] GetRow_SP(SanPham sp) {
        String Ma_SP = sp.getMa_SP();
        String Ten_SP = sp.getTen_SP();
        String MoTa_SP = sp.getMoTa_SP();
        float DonGia_SP = sp.getDonGia_SP();
        String Ma_LSP = sp.getMa_LSP();
        String HinhAnh_SP = sp.getHinhAnh_SP();

        Date NgayTao_SP = sp.getNgayTao_SP();
        String NgayTaoStr = "NULL";

        // ✅ Định dạng ngày tạo nếu có
        if (NgayTao_SP != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            NgayTaoStr = sdf.format(NgayTao_SP);
        }
        String TrangThai_SP = sp.getTrangThai_SP();
        // ✅ Trả về Object[] gọn gàng
        return new Object[]{Ma_SP, Ten_SP, MoTa_SP, DonGia_SP, Ma_LSP, HinhAnh_SP, NgayTaoStr , TrangThai_SP};
    }

    public ImageIcon resizeImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    public Object[] GetRow_SP_Anh(SanPham sp) {
        String Ma_SP = sp.getMa_SP();
        String Ten_SP = sp.getTen_SP();
        String MoTa_SP = sp.getMoTa_SP();
        float DonGia_SP = sp.getDonGia_SP();
        String Ma_LSP = sp.getMa_LSP();
        String HinhAnh_SP = sp.getHinhAnh_SP();

        Date NgayTao_SP = sp.getNgayTao_SP();
        String NgayTaoStr = "NULL";

        // ✅ Định dạng ngày tạo nếu có
        if (NgayTao_SP != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            NgayTaoStr = sdf.format(NgayTao_SP);
        }
        ImageIcon icon = resizeImage(sp.getHinhAnh_SP());
        
        String TrangThai_SP = sp.getTrangThai_SP();
        // ✅ Trả về Object[] gọn gàng
        return new Object[]{Ma_SP, Ten_SP, MoTa_SP, DonGia_SP, Ma_LSP, icon, NgayTaoStr , TrangThai_SP};
    }

    // Hàm Thêm Dữ Liệu Vào Tài Khoản
    public int Them_TK(SanPham sp) {
        String SQL = "INSERT INTO SANPHAM  VALUES\n"
                + "(  ?  ,   ?  ,  ?  ,  ?  ,  ?  , ?  , ? , ? , ? )";
        try {
            Connection Connect = conn.DBConnect();
            PreparedStatement pstm = Connect.prepareStatement(SQL);
            pstm.setString(1, sp.getMa_SP());
            pstm.setString(2, sp.getTen_SP());
            pstm.setString(3, sp.getMoTa_SP());
            pstm.setFloat(4, sp.getDonGia_SP());
            pstm.setString(5, sp.getMa_LSP());
            pstm.setString(6, sp.getHinhAnh_SP());
            // ✅ Chuyển java.util.Date ➜ java.sql.Date
            java.sql.Date sqlNgayTao = new java.sql.Date(sp.getNgayTao_SP().getTime());
            pstm.setDate(7, sqlNgayTao);
            pstm.setString(8, sp.getTrangThai_SP());
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
                + "			 HINHANH =  ? ,\n"
                + "			 NGAYTAO =  ? ,\n"
                + "                      TRANGTHAI = ? \n"
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
            java.sql.Date sqlNgayTao = new java.sql.Date(sp.getNgayTao_SP().getTime());
            pstm.setDate(7, sqlNgayTao);
            pstm.setString(8, sp.getTrangThai_SP());
            pstm.setString(9, TheoMa);
            
            if (pstm.executeUpdate() > 0) {
                System.out.println("Sua Du Lieu San Pham. Connect");
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<SanPham> getSanPhamTheoLoai(String TheoMa_LSP) {
        List<SanPham> List_SP = new ArrayList<>();
        String sql = "SELECT * FROM SANPHAM WHERE MA_LOAI = ?";

        try {
            Connection con = conn.DBConnect();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, TheoMa_LSP); // 🔥 Gán tham số tại đây
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String Ma_SP = rs.getString(1);
                String Ten_SP = rs.getString(2);
                String MoTa_SP = rs.getString(3);
                float DonGia_SP = rs.getFloat(5);
                String Ma_LSP = rs.getString(6);
                String HinhAnh_SP = rs.getString(7);
                Date NgayTao_SP = rs.getDate(8);
                String TrangThai_SP = rs.getString(9);
                SanPham sp = new SanPham(Ma_SP, Ten_SP, MoTa_SP, DonGia_SP, Ma_LSP, HinhAnh_SP, NgayTao_SP , TrangThai_SP);
                List_SP.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return List_SP;
    }
}
