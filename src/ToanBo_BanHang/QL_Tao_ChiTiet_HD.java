/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_BanHang;

import DBConnect.MyConnection;
import java.util.*;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class QL_Tao_ChiTiet_HD {

    MyConnection conn;

    public QL_Tao_ChiTiet_HD() {
        conn = new MyConnection();

    }

    public int Them_CTHD(ChiTietHoaDon CTHD) {
        String SQL = "INSERT INTO CTHOADON (MA_HD, MA_SP, SOLUONG, DONGIA, GHICHU) VALUES "
                + "(?, ?, ?, ?, ?)";

        try {
            Connection connect = conn.DBConnect();
            PreparedStatement pstm = connect.prepareStatement(SQL);
            pstm.setString(1, CTHD.getMa_HD());
            pstm.setString(2, CTHD.getMa_SP());
            pstm.setInt(3, CTHD.getSoLuong_CTHD());
            pstm.setFloat(4, CTHD.getDonGia_CTHD());
            pstm.setString(5, CTHD.getGhiChu());

            if (pstm.executeUpdate() >= 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<ChiTiet_SP_5O> Get_ALL_SPDC() {
        List<ChiTiet_SP_5O> List_CTHD = new ArrayList<>();
        String SQL = "SELECT \n"
                + "    cthd.MA_SP,\n"
                + "    sp.TENSP,\n"
                + "    cthd.SOLUONG,\n"
                + "    cthd.DONGIA,\n"
                + "    cthd.GHICHU\n"
                + "FROM CTHOADON cthd\n"
                + "JOIN SANPHAM sp ON cthd.MA_SP = sp.MA_SP\n"
                + "WHERE cthd.MA_HD = 'HD001';";
        try {
            Connection connect = conn.DBConnect();
            Statement stm = connect.createStatement();
            ResultSet rs = stm.executeQuery(SQL);
            while (rs.next()) {
                String Ma_SP = rs.getString(1);
                String Ten_SP = rs.getString(2);
                int SoLuong_SP = rs.getInt(3);
                float DonGia_SP = rs.getFloat(4);
                String GhiChu_SP = rs.getString(5);
                ChiTiet_SP_5O CTSP_5O = new ChiTiet_SP_5O(Ma_SP, Ten_SP, SoLuong_SP, DonGia_SP, GhiChu_SP);
                List_CTHD.add(CTSP_5O);
            }
        } catch (Exception e) {
        }
        return List_CTHD;
    }

    public Object[] Get_Row(ChiTiet_SP_5O CTHD_5O) {
        String Ma_SP = CTHD_5O.getMa_SP();
        String Ten_SP = CTHD_5O.getTen_SP();
        int SoLuong_SP = CTHD_5O.getSoLuong_SP();
        float DonGia_SP = CTHD_5O.getGiaTine_SP();
        String GhiChu_SP = CTHD_5O.getGhiChu_SP();

        Object[] obj = new Object[]{Ma_SP, Ten_SP, SoLuong_SP, DonGia_SP, GhiChu_SP};
        return obj;
    }

    public ChiTiet_SP_5O GetSPTheoHD(String maHD, String maSP) {
        ChiTiet_SP_5O result = null;
        String SQL = "SELECT cthd.MA_SP, sp.TENSP, cthd.SOLUONG, cthd.DONGIA, cthd.GHICHU "
                + "FROM CTHOADON cthd "
                + "JOIN SANPHAM sp ON cthd.MA_SP = sp.MA_SP "
                + "WHERE cthd.MA_HD = ? AND cthd.MA_SP = ?";
        try (Connection connect = conn.DBConnect(); PreparedStatement pst = connect.prepareStatement(SQL)) {
            pst.setString(1, maHD);
            pst.setString(2, maSP);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = new ChiTiet_SP_5O(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getFloat(4),
                        rs.getString(5)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    

    // 👉 Kiểm tra sản phẩm đã tồn tại chưa
    public boolean DaTonTaiCTHD(String maHD, String maSP) {
        String SQL = "SELECT * FROM CTHOADON WHERE MA_HD = ? AND MA_SP = ?";
        try {
            Connection conect = conn.DBConnect();
            PreparedStatement pstm = conect.prepareStatement(SQL);
            pstm.setString(1, maHD);
            pstm.setString(2, maSP);
            ResultSet rs = pstm.executeQuery();
            return rs.next(); // Có kết quả → tồn tại
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public int CapNhat_CTHD(ChiTietHoaDon CTHD) {
        String SQL = "UPDATE CTHOADON SET SOLUONG = ?, DONGIA = ? WHERE MA_HD = ? AND MA_SP = ?";
        try {
            Connection conect = conn.DBConnect();
            PreparedStatement pstm = conect.prepareStatement(SQL);
            pstm.setInt(1, CTHD.getSoLuong_CTHD());
            pstm.setFloat(2, CTHD.getDonGia_CTHD());
            pstm.setString(3, CTHD.getMa_HD());
            pstm.setString(4, CTHD.getMa_SP());

            return pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
