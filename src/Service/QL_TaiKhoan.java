/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DBConnect.MyConnection;
import Model.Tai_Khoan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class QL_TaiKhoan {

    MyConnection conn;

    public QL_TaiKhoan() {
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
                String VaiTro_TK = rs.getString(6);
                Date Ngay_DK_TK = rs.getDate(7);
                String Anh_TK = rs.getString(8);
                    boolean TrangThai_TK = rs.getBoolean(9);
                Tai_Khoan tk = new Tai_Khoan(Ma_TK, Ten_TK, SDT_TK, Email_TK, DiaChi_TK, VaiTro_TK, Ngay_DK_TK,Anh_TK, TrangThai_TK);
                List_TK.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace(); // hoặc log ra file/log view
        }
        return List_TK;
    }

    public Object[] GetRow(Tai_Khoan tk) { // Hàm GetRow Dùng Để Đẩy Dữ Liệu Lên Bảng Trong Giao Diện
        String Ma_TK = tk.getMa_TK();
        String Ten_TK = tk.getTen_TK();
        String SDT_TK = tk.getSDT_TK();
        String Email_TK = tk.getEmail_TK();
        String DiaChi_TK = tk.getDiaChi_TK();
        String VaiTro_TK = tk.getVaiTro_TK();
        Date Ngay_DK_TK = tk.getNgay_DK_TK();
        String Anh_TK = tk.getAnh_TK();
        boolean TrangThai_TK = tk.isTrangThai_TK();
        Object[] obj = new Object[] {Ma_TK , Ten_TK , SDT_TK , Email_TK , DiaChi_TK , VaiTro_TK , Ngay_DK_TK , Anh_TK , TrangThai_TK};
        return obj;
    }
    
    // Hàm Thêm Dữ Liệu Vào Tài Khoản
    public int Them_TK(Tai_Khoan tk){
        String SQL = "    INSERT INTO TAIKHOAN VALUES \n" +
                     "(' ? ', N' ? ' , ' ? ', ' ? ',N' ? ' , N' ? ', ? ,  '?'  , ? ),"; 
        // Có Hai Cách Giải Quyết Vấn Đề Về Thời Gian Tạo Này
        // Thứ Nhất Là Dùng Luôn Câu Lệnh SQL Là GETDATE() Còn Cái Này Thì Khả Năng Là Không Nhìn Thấy
        // Hai Là Dùng Code Java Thì Dài Ròng Hơn Nhưng Lại Có Lợi Là Nhìn Thấy Được Ở Ô Thời Gian
        try {
            Connection Connect = conn.DBConnect();
            PreparedStatement pstm = Connect.prepareStatement(SQL);
            pstm.setString(1, tk.getMa_TK());
            pstm.setString(2, tk.getTen_TK());
            pstm.setString(3, tk.getSDT_TK());
            pstm.setString(4, tk.getEmail_TK());
            pstm.setString(5, tk.getDiaChi_TK());
            pstm.setString(6, tk.getVaiTro_TK());
            pstm.setDate(7, tk.getNgay_DK_TK());
            pstm.setString(8, tk.getAnh_TK());
            pstm.setBoolean(9, tk.isTrangThai_TK());
            if (pstm.executeUpdate() > 0) {
                System.out.println("Them Tai Khoan. Connect");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }
    // Hàm Xoá Tài Khoản
    public int Xoa_TK(String TheoMa){
        String SQL = "DELETE FROM TAIKHOAN WHERE MA_TK = '?'";
        try {
            Connection Connect = conn.DBConnect();
            PreparedStatement pstm = Connect.prepareStatement(SQL);
            pstm.setString(1, TheoMa);
            if(pstm.executeUpdate() > 0) {
                System.out.println("Xoa Tai Khoan. Connect");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }
    // Hàm Sửa Dữ Liệu Tài Khoản
    public int Sua_TK(Tai_Khoan tk , String TheoMa){ 
        String SQL = "UPDATE TAIKHOAN SET MA_TK = '  ?  ',\n" +
"                                       TENTAIKHOAN = N'  ?  ',\n" +
"					SDT = '  ?  ',\n" +
"					EMAIL = '  ? ',\n" +
"					DIACHI = N'  ?  ',\n" +
"					VAITRO = N'  ?  ',\n" +
"					NGAYDANGKY = '  ?  ',\n" +
"					ANH_TK = N'  ?  ',\n" +
"					TRANGTHAI =  ?  \n" +
"					WHERE MA_TK = '  ?  '";
        try {
            Connection Connect = conn.DBConnect();
            PreparedStatement pstm = Connect.prepareStatement(SQL);
            pstm.setString(1, tk.getMa_TK());
            pstm.setString(2, tk.getTen_TK());
            pstm.setString(3, tk.getSDT_TK());
            pstm.setString(4, tk.getEmail_TK());
            pstm.setString(5, tk.getDiaChi_TK());
            pstm.setString(6, tk.getVaiTro_TK());
            pstm.setDate(7, tk.getNgay_DK_TK());
            pstm.setString(8, tk.getAnh_TK());
            pstm.setBoolean(9, tk.isTrangThai_TK());
            pstm.setString(10, TheoMa);
            if (pstm.executeUpdate() > 0) {
                System.out.println("Sua Du Lieu Tai Khoan. Connect");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    // Hàm Tìm Kiếm Tài Khoản Theo SDT
    public List<Tai_Khoan> TimKiem_Theo_SDT(String Theo_SDT) {
        List<Tai_Khoan> List_TK = new ArrayList<>(); 
        String SQL = "SELECT * FROM TAIKHOAN";
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
                Tai_Khoan tk = new Tai_Khoan(Ma_TK, Ten_TK, SDT_TK, Email_TK, DiaChi_TK, VaiTro_TK, Ngay_DK_TK,Anh_TK, TrangThai_TK);
                if (SDT_TK.equalsIgnoreCase(Theo_SDT)) {
                    System.out.println("Tim Kiem Tai Khoan Theo SDT. Connect");
                    List_TK.add(tk);
                }
            }
        } catch (Exception e) {
        }
        return List_TK;
    }
    
    // Hàm Tìm Kiếm Tài KHoản Theo  Tên
    public List<Tai_Khoan> TimKiem_Theo_Ten(String TheoTen) {
        List<Tai_Khoan> List_TK = new ArrayList<>(); 
        String SQL = "SELECT * FROM TAIKHOAN"; 
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
                Tai_Khoan tk = new Tai_Khoan(Ma_TK, Ten_TK, SDT_TK, Email_TK, DiaChi_TK, VaiTro_TK, Ngay_DK_TK,Anh_TK, TrangThai_TK);
                if (Ten_TK.equalsIgnoreCase(TheoTen)) {
                    System.out.println("Tim Kiem Tai Khoan Theo Ten Tai Khoan. Connect");
                    List_TK.add(tk);
                }              
            }
        } catch (Exception e) {
        }
        return List_TK;
    }
    
    // Hàm Tìm Kiếm Số Điện Thoại Theo Mã Tài Khoản
    public List<Tai_Khoan> TimKiem_Theo_MaTK(String TheoMa) {
        List<Tai_Khoan> List_TK = new ArrayList<>();
        String SQL = "SELECT * FROM TAIKHOAN"; 
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
                Tai_Khoan tk = new Tai_Khoan(Ma_TK, Ten_TK, SDT_TK, Email_TK, DiaChi_TK, VaiTro_TK, Ngay_DK_TK,Anh_TK, TrangThai_TK);
                if (Ma_TK.equals(TheoMa)) {
                    System.out.println("Tim Kiem Tai Khoan Theo Ma Tai Khoan. Connect");
                    List_TK.add(tk);
                }  
            }
        } catch (Exception e) {
        }
        return List_TK;
    }
}
