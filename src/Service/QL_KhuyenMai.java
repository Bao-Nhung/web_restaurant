/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DBConnect.MyConnection;
import Model.KhuyenMai;
import Model.SanPham;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class QL_KhuyenMai {

    MyConnection conn;

    public QL_KhuyenMai() {
        conn = new MyConnection();
    }

    public List<KhuyenMai> Get_All() {
        List<KhuyenMai> List_KM = new ArrayList<>(); //  Tạo một danh sách rỗng kiểu Nguyên Liệu để chứa tất cả tài khoản đọc từ database.
        String SQL = "SELECT * FROM KHUYENMAI"; //  Lấy toàn bộ dòng dữ liệu từ bảng NGUYENLIEU
        try {
            Connection connect = conn.DBConnect(); // 
            Statement stm = connect.createStatement();
            ResultSet rs = stm.executeQuery(SQL);
            while (rs.next()) {
                String Ma_KM = rs.getString(1);
                String Ten_KM = rs.getString(2);
                String HinhThuc_KM = rs.getString(3);
                String MoTa_KM = rs.getString(4);
                int DiemYeuCau_KM = rs.getInt(5);
                float GiaTri_KM = rs.getFloat(6);
                Date Ngay_BD_KM = rs.getDate(7);
                Date Ngay_KT_KM = rs.getDate(8);
                String NgayTrongThang_KM = rs.getString(9);
                String DieuKien_KM = rs.getString(10);
                boolean TrangThai_KM = rs.getBoolean(11);
                KhuyenMai km = new KhuyenMai(Ma_KM, Ten_KM, HinhThuc_KM, MoTa_KM, DiemYeuCau_KM, GiaTri_KM, Ngay_BD_KM, Ngay_KT_KM, NgayTrongThang_KM, DieuKien_KM, TrangThai_KM);
                List_KM.add(km);
            }
        } catch (Exception e) {
            e.printStackTrace(); // hoặc log ra file/log view
        }
        return List_KM;
    }

    public Object[] GetRow(KhuyenMai km) {
        String Ma_KM = km.getMa_KM();
        String Ten_KM = km.getTen_KM();
        String MoTa_KM = km.getMoTa_KM();
        String HinhThuc_KM = km.getHinhThuc_KM();
        int DiemYeuCau_KM = km.getDiemYeuCau_KM();
        float GiaTri_KM = km.getGiaTri_KM();
        Date Ngay_BD_KM = km.getNgay_BD();
        Date Ngay_KT_KM = km.getNgay_KT();
        String NgayTrongThang_KM = km.getNgayTrongThang_KM();
        String DieuKien_KM = km.getNgayTrongThang_KM();
        boolean TrangThai_KM = km.getTrangThai();

        Object[] obj = new Object[]{Ma_KM, Ten_KM, HinhThuc_KM, MoTa_KM, DiemYeuCau_KM,  GiaTri_KM, Ngay_BD_KM, Ngay_KT_KM, NgayTrongThang_KM, DieuKien_KM, TrangThai_KM};
        return obj;
    }

    // Hàm Thêm Dữ Liệu Vào Tài Khoản
    public int Them_KM(KhuyenMai km) {
        String SQL = "INSERT INTO KHUYENMAI (MA_GIAM, TENKM, MOTA, HINHTHUC, DIEM_YEUCAU, GIATRI, NGAYBATDAU, NGAYKETTHUC, NGAYTRONGTHANG, DIEUKIEN, TRANGTHAI)   VALUES \n"
                + "(  ? ,  ?  ,  ?  ,   ?   ,   ?  ,   ?  ,  ?  , ?  ,   ?  , ?  , ?),"; // Có Hai Cách Giải Quyết Vấn Đề Về Thời Gian Tạo Này
        // Thứ Nhất Là Dùng Luôn Câu Lệnh SQL Là GETDATE() Còn Cái Này Thì Khả Năng Là Không Nhìn Thấy
        // Hai Là Dùng Code Java Thì Dài Ròng Hơn Nhưng Lại Có Lợi Là Nhìn Thấy Được Ở Ô Thời Gian
        try {
            Connection Connect = conn.DBConnect();
            PreparedStatement pstm = Connect.prepareStatement(SQL);
            pstm.setString(1, km.getMa_KM());
            pstm.setString(2, km.getTen_KM());
            pstm.setString(3, km.getMoTa_KM());
            pstm.setString(4, km.getHinhThuc_KM());
            pstm.setInt(5, km.getDiemYeuCau_KM());
            pstm.setFloat(6, km.getGiaTri_KM());
            pstm.setDate(7, km.getNgay_BD());
            pstm.setDate(8, km.getNgay_KT());
            pstm.setString(9, km.getNgayTrongThang_KM());
            pstm.setString(10, km.getDieuKien_KM());
            pstm.setBoolean(11, km.getTrangThai());
            if (pstm.executeUpdate() > 0) {
                System.out.println("Them Khuyen Mai. Connect");
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Hàm Xoá Tài Khoản
    public int Xoa_KM(String TheoMa) {
        String SQL = "DELETE FROM KHUYENMAI WHERE MA_GIAM = ? ";
        try {
            Connection Connect = conn.DBConnect();
            PreparedStatement pstm = Connect.prepareStatement(SQL);
            pstm.setString(1, TheoMa);
            if (pstm.executeUpdate() > 0) {
                System.out.println("Xoa Khuyen Mai. Connect");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    // Hàm Sửa Dữ Liệu Tài Khoản
    public int Sua_KM(KhuyenMai km, String TheoMa) {
        String SQL = "UPDATE KHUYENMAI SET MA_GIAM =  ?  ,\n"
                + "                        TENKM =  ? ,\n"
                + "                        MOTA = ? ,\n"
                + "			   HINHTHUC =  ? ,\n"
                + "			   DIEM_YEUCAU = ? ,\n"
                + "			   GIATRI = ? ,\n"
                + "			   NGAYBATDAU =  ? ,\n"
                + "			   NGAYKETTHUC =  ? ,\n"
                + "			   NGAYTRONGTHANG = ? ,\n"
                + "			   DIEUKIEN =   ? ,\n"
                + "			   TRANGTHAI =  ? \n"
                + "			   WHERE MA_GIAM =  ? ";
        try {
            Connection Connect = conn.DBConnect();
            PreparedStatement pstm = Connect.prepareStatement(SQL);
            pstm.setString(1, km.getMa_KM());
            pstm.setString(2, km.getTen_KM());
            pstm.setString(3, km.getMoTa_KM());
            pstm.setString(4, km.getHinhThuc_KM());
            pstm.setInt(5, km.getDiemYeuCau_KM());
            pstm.setFloat(6, km.getGiaTri_KM());
            pstm.setDate(7, km.getNgay_BD());
            pstm.setDate(8, km.getNgay_KT());
            pstm.setString(9, km.getNgayTrongThang_KM());
            pstm.setString(10, km.getDieuKien_KM());
            pstm.setBoolean(11, km.getTrangThai());
            pstm.setString(12, TheoMa);
            if (pstm.executeUpdate() > 0) {
                System.out.println("Sua Du Lieu Khuyen Mai. Connect");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }
}
