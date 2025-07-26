/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_KhuyenMai;

import DBConnect.MyConnection;
import ToanBo_KhuyenMai.KhuyenMai;
import ToanBo_SanPham.SanPham;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String DieuKien_KM = km.getDieuKien_KM(); // Sửa lỗi gán sai biến như ảnh
        boolean TrangThai_KM = km.getTrangThai();

        // Chuyển đổi trạng thái từ boolean sang chuỗi mô tả
        String TrangThaiText = TrangThai_KM ? "Đang Hoạt Động" : "Không Hoạt Động";

        Object[] obj = new Object[]{
            Ma_KM, Ten_KM, HinhThuc_KM, MoTa_KM, DiemYeuCau_KM, GiaTri_KM,
            Ngay_BD_KM, Ngay_KT_KM, NgayTrongThang_KM, DieuKien_KM, TrangThaiText
        };

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

    // Lọc Sản Phẩm Theo Thời Gian Bắt Đầu Thời Gian Kết Thúc
    public List<KhuyenMai> Loc_KM(Date ThoiGianBT, Date ThoiGianKT) {
        List<KhuyenMai> List_KM = new ArrayList<>();
        String SQL = "SELECT * FROM KHUYENMAI WHERE NGAYBATDAU >= ? AND NGAYKETTHUC <= ? ";
        try {
            Connection connect = conn.DBConnect(); // 
            PreparedStatement pstm = connect.prepareStatement(SQL);
            pstm.setDate(1, ThoiGianBT);
            pstm.setDate(2, ThoiGianKT);

            ResultSet rs = pstm.executeQuery();
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

    // Tìm Kiếm Theo Mã Khuyến Mãi
//    public List<KhuyenMai> TimKiem_TheoMa(String TheoMa) {
//        List<KhuyenMai> List_TheoMa_KM = new ArrayList<>(); //  
//        String SQL = "SELECT * FROM KHUYENMAI WHERE MA_GIAM LIKE ? ";
//        try {
//            Connection connect = conn.DBConnect(); // 
//            PreparedStatement pstm = connect.prepareStatement(SQL);
//            pstm.setString(1, "%" + TheoMa + "%");
//            ResultSet rs = pstm.executeQuery();
//            while (rs.next()) {
//                String Ma_KM = rs.getString(1);
//                String Ten_KM = rs.getString(2);
//                String HinhThuc_KM = rs.getString(3);
//                String MoTa_KM = rs.getString(4);
//                int DiemYeuCau_KM = rs.getInt(5);
//                float GiaTri_KM = rs.getFloat(6);
//                Date Ngay_BD_KM = rs.getDate(7);
//                Date Ngay_KT_KM = rs.getDate(8);
//                String NgayTrongThang_KM = rs.getString(9);
//                String DieuKien_KM = rs.getString(10);
//                boolean TrangThai_KM = rs.getBoolean(11);
//                KhuyenMai km = new KhuyenMai(Ma_KM, Ten_KM, HinhThuc_KM, MoTa_KM, DiemYeuCau_KM, GiaTri_KM, Ngay_BD_KM, Ngay_KT_KM, NgayTrongThang_KM, DieuKien_KM, TrangThai_KM);
//                List_TheoMa_KM.add(km);
//            }
//        } catch (Exception e) {
//            e.printStackTrace(); // hoặc log ra file/log view
//        }
//        return List_TheoMa_KM;
//    }
//
//    // Tìm Kiếm Theo Tên Khuyến Mãi
//    public List<KhuyenMai> TimKiem_TheoTen(String TheoTen) {
//        List<KhuyenMai> List_TheoTen_KM = new ArrayList<>(); //  
//        String SQL = "SELECT * FROM KHUYENMAI WHERE TENKM LIKE UPPER(  ?  )"; //  
//        try {
//            Connection connect = conn.DBConnect(); // 
//            PreparedStatement pstm = connect.prepareStatement(SQL);
//            pstm.setString(1, "%" + TheoTen + "%");
//            ResultSet rs = pstm.executeQuery();
//            while (rs.next()) {
//                String Ma_KM = rs.getString(1);
//                String Ten_KM = rs.getString(2);
//                String HinhThuc_KM = rs.getString(3);
//                String MoTa_KM = rs.getString(4);
//                int DiemYeuCau_KM = rs.getInt(5);
//                float GiaTri_KM = rs.getFloat(6);
//                Date Ngay_BD_KM = rs.getDate(7);
//                Date Ngay_KT_KM = rs.getDate(8);
//                String NgayTrongThang_KM = rs.getString(9);
//                String DieuKien_KM = rs.getString(10);
//                boolean TrangThai_KM = rs.getBoolean(11);
//                KhuyenMai km = new KhuyenMai(Ma_KM, Ten_KM, HinhThuc_KM, MoTa_KM, DiemYeuCau_KM, GiaTri_KM, Ngay_BD_KM, Ngay_KT_KM, NgayTrongThang_KM, DieuKien_KM, TrangThai_KM);
//                List_TheoTen_KM.add(km);
//            }
//        } catch (Exception e) {
//            e.printStackTrace(); // hoặc log ra file/log view
//        }
//        return List_TheoTen_KM;
//    }
    public List<KhuyenMai_2_O> TimKiem_KM(String tuKhoa) {
        List<KhuyenMai_2_O> list = new ArrayList<>();
        String sql = "SELECT MA_KM, TENKM FROM KHUYENMAI WHERE MA_KM LIKE ? OR TENKM LIKE ?";
        try {
            Connection conect = conn.DBConnect();
            PreparedStatement ps = conect.prepareStatement(sql);
            ps.setString(1, "%" + tuKhoa + "%");
            ps.setString(2, "%" + tuKhoa + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new KhuyenMai_2_O(
                        rs.getString("MA_KM"),
                        rs.getString("TENKM")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QL_KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public KhuyenMai LayChiTietKM(String TheoMa_KM) {
        String sql = "SELECT * FROM KHUYENMAI WHERE MA_KM = ?";
        try {
            Connection conect = conn.DBConnect();
            PreparedStatement ps = conect.prepareStatement(sql);
            ps.setString(1, TheoMa_KM);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new KhuyenMai(
                        rs.getString("MA_KM"),
                        rs.getString("TENKM"),
                        rs.getString("MOTA"),
                        rs.getString("HINHTHUC_KM"),
                        rs.getInt("DIEM_YEUCAU"),
                        rs.getFloat("GIATRI"),
                        rs.getDate("NGAYBATDAU"),
                        rs.getDate("NGAYKETTHUC"),
                        rs.getString("NGAYTRONGTHANG"),
                        rs.getString("DIEUKIEN"),
                        rs.getBoolean("TRANGTHAI")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QL_KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
