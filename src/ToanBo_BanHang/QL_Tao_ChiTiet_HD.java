/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_BanHang;

import DBConnect.MyConnection;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public List<ChiTiet_SP_6_O> Get_ALL_SPDC(String maHD) {
        List<ChiTiet_SP_6_O> List_CTHD = new ArrayList<>();
        String SQL = "SELECT "
                + "cthd.MA_SP, "
                + "sp.TENSP, "
                + "sp.DONGIA, "
                + "cthd.SOLUONG, "
                + "cthd.THANHTIEN, "
                + "cthd.GHICHU "
                + // 🟢 bỏ dấu phẩy sau cùng
                "FROM CTHOADON cthd "
                + "JOIN SANPHAM sp ON cthd.MA_SP = sp.MA_SP "
                + "WHERE cthd.MA_HD = ?";

        try {
            Connection connect = conn.DBConnect();
            PreparedStatement pstmt = connect.prepareStatement(SQL);
            pstmt.setString(1, maHD); // 🟢 Gán giá trị cho dấu ?
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String Ma_SP = rs.getString("MA_SP");
                String Ten_SP = rs.getString("TENSP");
                float DonGia_SP = rs.getFloat("DONGIA");
                int SoLuong_SP = rs.getInt("SOLUONG");
                float ThanhTien = rs.getFloat("THANHTIEN");
                String GhiChu_SP = rs.getString("GHICHU");

                ChiTiet_SP_6_O CTSP_5O = new ChiTiet_SP_6_O(Ma_SP, Ten_SP, DonGia_SP, SoLuong_SP, ThanhTien, GhiChu_SP);
                List_CTHD.add(CTSP_5O);
            }

            rs.close();
            pstmt.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace(); // ✅ log lỗi ra console
        }

        return List_CTHD;
    }

    public Object[] Get_Row(ChiTiet_SP_6_O CTHD_5O) {
        String Ma_SP = CTHD_5O.getMa_SP();
        String Ten_SP = CTHD_5O.getTen_SP();
        float DonGia_SP = CTHD_5O.getDonGia();
        int SoLuong_SP = CTHD_5O.getSoLuong();
        float ThanhTien = CTHD_5O.getThanhTien();
        String GhiChu_SP = CTHD_5O.getGhiChu();

        Object[] obj = new Object[]{Ma_SP, Ten_SP, DonGia_SP, SoLuong_SP, ThanhTien, GhiChu_SP};
        return obj;
    }

    public ChiTiet_SP_6_O GetSPTheoHD(String maHD, String maSP) {
        ChiTiet_SP_6_O result = null;
        String SQL = "SELECT cthd.MA_SP, sp.TENSP, sp.DONGIA, cthd.SOLUONG, cthd.THANHTIEN, cthd.GHICHU "
                + "FROM CTHOADON cthd "
                + "JOIN SANPHAM sp ON cthd.MA_SP = sp.MA_SP "
                + "WHERE cthd.MA_HD = ? AND cthd.MA_SP = ?";

        Connection connect = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            connect = conn.DBConnect();
            pst = connect.prepareStatement(SQL);
            pst.setString(1, maHD);
            pst.setString(2, maSP);

            rs = pst.executeQuery();
            if (rs.next()) {
                result = new ChiTiet_SP_6_O(
                        rs.getString("MA_SP"),
                        rs.getString("TENSP"),
                        rs.getFloat("DONGIA"),
                        rs.getInt("SOLUONG"),
                        rs.getFloat("THANHTIEN"),
                        rs.getString("GHICHU")
                );
            }
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi truy vấn sản phẩm theo hóa đơn: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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

    // Chỉnh Sửa
    public int CapNhat_CTHD(ChiTietHoaDon CTHD) {
        String SQL = "UPDATE CTHOADON SET SOLUONG = ?, THANHTIEN = ? WHERE MA_HD = ? AND MA_SP = ?";
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

    public float layDonGiaSanPham(String Ma_SP) {
        String sql = "SELECT DONGIA FROM SANPHAM WHERE MA_SP = ?";

        try  {
            Connection conect = conn.DBConnect();
            PreparedStatement stmt = conect.prepareStatement(sql);
            stmt.setString(1, Ma_SP);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getFloat("DONGIA");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0f; // fallback nếu không tìm thấy hoặc lỗi
    }

    public boolean capNhatSoLuongSanPham(String Ma_HD, String Ma_SP, int SoLuong_SP_Moi) {
        String sql = "UPDATE CTHOADON SET SOLUONG = ?, THANHTIEN = ? WHERE MA_HD = ? AND MA_SP = ?";
        try {
            Connection conect = conn.DBConnect();
            PreparedStatement stmt = conect.prepareStatement(sql);
            float DonGia = layDonGiaSanPham(Ma_SP); // có thể gọi DAO khác hoặc truyền sẵn
            float ThanhTien = DonGia * SoLuong_SP_Moi;

            stmt.setInt(1, SoLuong_SP_Moi);
            stmt.setFloat(2, ThanhTien);
            stmt.setString(3, Ma_HD);
            stmt.setString(4, Ma_SP);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ShowDetail CTHD Khi Mình Click Vào Phầm Hoá Đươn
    public List<ChiTiet_SP_6_O> layTheoMaHD(String Ma_HD) {
        List<ChiTiet_SP_6_O> danhSach = new ArrayList<>();
        String SQL = "SELECT CTHD.MA_SP, SP.TENSP, SP.DONGIA, CTHD.SOLUONG, CTHD.THANHTIEN, CTHD.GHICHU "
                + "FROM CTHOADON CTHD "
                + "JOIN SANPHAM SP ON CTHD.MA_SP = SP.MA_SP "
                + "WHERE CTHD.MA_HD = ?";

        Connection connect = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connect = conn.DBConnect();
            pstm = connect.prepareStatement(SQL);
            pstm.setString(1, Ma_HD);
            rs = pstm.executeQuery();

            while (rs.next()) {
                ChiTiet_SP_6_O ct = new ChiTiet_SP_6_O();
                ct.setMa_SP(rs.getString("MA_SP"));
                ct.setTen_SP(rs.getString("TENSP"));
                ct.setDonGia(rs.getFloat("DONGIA"));
                ct.setSoLuong(rs.getInt("SOLUONG"));
                ct.setThanhTien(rs.getFloat("THANHTIEN"));
                ct.setGhiChu(rs.getString("GHICHU"));
                danhSach.add(ct);
            }

        } catch (Exception e) {
            System.err.println("❌ Lỗi khi truy vấn danh sách SP theo mã hóa đơn: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return danhSach;
    }

    public void xoaChiTietHoaDon(String Ma_HD, String Ma_SP) {
        String sql = "DELETE FROM CTHOADON WHERE MA_HD = ? AND MA_SP = ?";

        Connection connect = null;
        PreparedStatement pstmt = null;

        try {
            connect = conn.DBConnect();
            pstmt = connect.prepareStatement(sql);
            pstmt.setString(1, Ma_HD);
            pstmt.setString(2, Ma_SP);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("❌ Lỗi SQL khi xoá chi tiết hoá đơn: " + ex.getMessage());
            ex.printStackTrace();

        } catch (ClassNotFoundException ex) {
            System.err.println("❌ Không tìm thấy class kết nối DB: " + ex.getMessage());
            Logger.getLogger(QL_Tao_ChiTiet_HD.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
