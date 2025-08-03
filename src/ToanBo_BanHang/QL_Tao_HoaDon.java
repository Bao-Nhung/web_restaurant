/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_BanHang;

import DBConnect.MyConnection;
import ToanBo_BanHang.HoaDon;
import ToanBo_SanPham.SanPham_5_O;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author ADMIN
 */
public class QL_Tao_HoaDon {

    MyConnection conn;

    public QL_Tao_HoaDon() {
        conn = new MyConnection();

    }

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

    public String taoMaHoaDonMoi() {
        int soThuTu = Get_All_HoaDon().size() + 1;
        String soTiepTheo = String.format("%02d", soThuTu); // "01", "02", ...
        return "HD" + soTiepTheo;
    }

    public List<HoaDon_6_O> Get_ALL_HoaDon_6_O() {
        List<HoaDon_6_O> List_HD = new ArrayList<>();
        String SQL = "SELECT MA_HD ,MA_TK ,NGAYLAP, TONGTIEN, HINHTHUC_HD, TRANGTHAI FROM HOADON";
        try {
            Connection connect = conn.DBConnect();
            Statement stm = connect.createStatement();
            ResultSet rs = stm.executeQuery(SQL);
            while (rs.next()) {
                String Ma_HD = rs.getString("MA_HD");
                String Ma_TK = rs.getString("MA_TK");
                Date NgayLap_HD = rs.getDate("NGAYLAP");
                float TongTien = rs.getFloat("TONGTIEN");
                String HinhThuc_HD = rs.getString("HINHTHUC_HD");
                String TrangThai = rs.getString("TRANGTHAI");
                HoaDon_6_O hd = new HoaDon_6_O(Ma_HD, Ma_TK, NgayLap_HD, TongTien, HinhThuc_HD, TrangThai);
                List_HD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List_HD;
    }

    public Object[] Get_Row_HD_6_O(HoaDon_6_O hd_6_o) {
        String Ma_HD = hd_6_o.getMa_HD();
        String Ma_TK = hd_6_o.getMa_TK();
        Date NgayLap_HD = hd_6_o.getNgayLap_HD();
        float TongTien = hd_6_o.getTongTien();
        String HinhThuc_HD = hd_6_o.getHinhThuc_HD();
        String TrangThai = hd_6_o.getTrangThai();
        Object[] obj = new Object[]{Ma_HD, Ma_TK, NgayLap_HD, TongTien, HinhThuc_HD, TrangThai};
        return obj;
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

    public List<ShowDetail_BanHang> Get_SHOWCT_ByMaHD(String maHD) {
        List<ShowDetail_BanHang> List_ShowDetail = new ArrayList<>();
        String SQL = "SELECT hd.MA_HD, \n"
                + " hd.MA_TK, \n"
                + " hd.NGAYLAP, \n"
                + " hd.TONGTIEN, \n"
                + " hd.HINHTHUC_HD, \n "
                + " hd.TRANGTHAI, \n"
                + " hd.MA_KM, \n"
                + " hd.SOTIEN_KHACHTRA, \n"
                + " hd.MA_KH, \n"
                + " kh.HOTEN, \n"
                + " kh.DIEM_TICHLUY, \n"
                + " hd.TICHDIEM \n"
                + "FROM HOADON hd JOIN KHACHHANG kh ON hd.MA_KH = kh.MA_KH WHERE hd.MA_HD = ? \n";
        try {
            Connection connect = conn.DBConnect();
            PreparedStatement pst = connect.prepareStatement(SQL);
            pst.setString(1, maHD);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                // Lấy dữ liệu như trước
                String Ma_HD = rs.getString(1);
                String Ma_TK = rs.getString(2);
                Date NgayLap_HD = rs.getDate(3);
                float TongTien = rs.getFloat(4);
                String HinhThuc_HD = rs.getString(5);
                String TrangThai = rs.getString(6);
                String Ma_KM = rs.getString(7);
                float SoTienKhachTra_HD = rs.getFloat(8);
                String Ma_KH = rs.getString(9);
                String Ten_KH = rs.getString(10);
                int DiemTichLuy = rs.getInt(11);
                int TichDiem = rs.getInt(12);
                ShowDetail_BanHang shbh = new ShowDetail_BanHang(Ma_HD, Ma_TK, NgayLap_HD, TongTien, HinhThuc_HD, TrangThai, Ma_KM, SoTienKhachTra_HD, Ma_KH, Ten_KH, DiemTichLuy, TichDiem);
                List_ShowDetail.add(shbh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List_ShowDetail;
    }

    public List<SanPham_5_O> getSanPhamTheoMaHD(String maHD) {
        List<SanPham_5_O> danhSach = new ArrayList<>();

        String sql = "SELECT sp.MaSP, sp.TenSP, cthd.SoLuong, cthd.DonGia "
                + "FROM ChiTietHoaDon cthd "
                + "JOIN SanPham sp ON cthd.MaSP = sp.MaSP "
                + "WHERE cthd.MaHD = ?";

        try {
            Connection conect = conn.DBConnect();
            PreparedStatement stmt = conect.prepareStatement(sql);

            stmt.setString(1, maHD);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ChiTiet_SP_5O cthd = new ChiTiet_SP_5O();
                SanPham_5_O sp = new SanPham_5_O();
                sp.setMa_SP(rs.getString("MaSP"));
                sp.setTen_SP(rs.getString("TenSP"));
                cthd.setSoLuong_SP(rs.getInt("SoLuong"));

                float tongGia = rs.getFloat("DonGia"); // DonGia đã là tổng            // hiển thị như bạn muốn
                cthd.setGiaTine_SP(tongGia);              // trùng đơn giá vì là tổng

                danhSach.add(sp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QL_Tao_HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }

        return danhSach;
    }

}
