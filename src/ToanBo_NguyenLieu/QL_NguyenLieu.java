/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_NguyenLieu;

import DBConnect.MyConnection;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author ADMIN
 */
public class QL_NguyenLieu {

    MyConnection conn;

    public QL_NguyenLieu() {
        conn = new MyConnection();
    }

    public List<NguyenLieu> Get_All() {
        List<NguyenLieu> List_NL = new ArrayList<>(); //  Tạo một danh sách rỗng kiểu Nguyên Liệu để chứa tất cả tài khoản đọc từ database.
        String SQL = "SELECT * FROM NGUYENLIEU"; //  Lấy toàn bộ dòng dữ liệu từ bảng NGUYENLIEU
        try {
            Connection connect = conn.DBConnect(); // 
            Statement stm = connect.createStatement();
            ResultSet rs = stm.executeQuery(SQL);
            while (rs.next()) {
                String Ma_NL = rs.getString(1);
                String Ten_NL = rs.getString(2);
                String DonViTinh_NL = rs.getString(3);
                int SoLuongTon_NL = rs.getInt(4);
                float GiaNhap_NL = rs.getFloat(5);
                Date Ngay_Nhap_NL = rs.getDate(6);
                String Anh_NL = rs.getString(7);
                NguyenLieu nl = new NguyenLieu(Ma_NL, Ten_NL, DonViTinh_NL, SoLuongTon_NL, GiaNhap_NL, Ngay_Nhap_NL, Anh_NL);
                List_NL.add(nl);
            }
        } catch (Exception e) {
            e.printStackTrace(); // hoặc log ra file/log view
        }
        return List_NL;
    }

    public Object[] GetRow(NguyenLieu nl) {
        String Ma_NL = nl.getMa_NL();
        String Ten_NL = nl.getTen_NL();
        String DonViTinh_NL = nl.getDonViTinh_NL();
        int SoLuongTon_NL = nl.getSoLuongTon_NL();
        float GiaNhap_NL = nl.getGiaNhap_NL();
        Date Ngay_Nhap_NL = nl.getNgayNhap_NL();
        String Anh_NL = nl.getAnh_NL();

        Object[] obj = new Object[]{Ma_NL, Ten_NL, DonViTinh_NL, SoLuongTon_NL, GiaNhap_NL, Ngay_Nhap_NL, Anh_NL};
        return obj;
    }

    // Hàm Thêm Dữ Liệu Vào Tài Khoản
    public int Them_NL(NguyenLieu nl) {
        String SQL = "INSERT INTO NGUYENLIEU VALUES \n"
                + "( ?  ,  ?  ,  ?  ,  ?  , ? , ? , ? )"; // Có Hai Cách Giải Quyết Vấn Đề Về Thời Gian Tạo Này
        // Thứ Nhất Là Dùng Luôn Câu Lệnh SQL Là GETDATE() Còn Cái Này Thì Khả Năng Là Không Nhìn Thấy
        // Hai Là Dùng Code Java Thì Dài Ròng Hơn Nhưng Lại Có Lợi Là Nhìn Thấy Được Ở Ô Thời Gian
        try {
            Connection Connect = conn.DBConnect();
            PreparedStatement pstm = Connect.prepareStatement(SQL);
            pstm.setString(1, nl.getMa_NL());
            pstm.setString(2, nl.getTen_NL());
            pstm.setString(3, nl.getDonViTinh_NL());
            pstm.setInt(4, nl.getSoLuongTon_NL());
            pstm.setFloat(5, nl.getGiaNhap_NL());
            pstm.setDate(6, nl.getNgayNhap_NL());
            pstm.setString(7, nl.getAnh_NL());
            if (pstm.executeUpdate() > 0) {
                System.out.println("Them Nguyen Lieu. Connect");
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Hàm Xoá Tài Khoản
    public int Xoa_NL(String TheoMa) {
        String SQL = "DELETE FROM NGUYENLIEU WHERE MA_NL =  ? ";
        try {
            Connection Connect = conn.DBConnect();
            PreparedStatement pstm = Connect.prepareStatement(SQL);
            pstm.setString(1, TheoMa);
            if (pstm.executeUpdate() > 0) {
                System.out.println("Xoa Nguyen Lieu. Connect");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    // Hàm Sửa Dữ Liệu Tài Khoản
    public int Sua_NL(NguyenLieu nl, String TheoMa) {
        String SQL = "UPDATE NGUYENLIEU SET MA_NL =  ?  ,\n"
                + "                         TENNL =   ?  ,\n"
                + "			    DONVITINH =  ?  ,\n"
                + "		            SOLUONGTON = ?  ,\n"
                + "			    GIANHAP =   ? ,\n"
                + "	     		    NGAYNHAP =   ?  ,\n"
                + "			    ANH_NL =   ?  \n"
                + "			    WHERE MA_NL = ? ";
        try {
            Connection Connect = conn.DBConnect();
            PreparedStatement pstm = Connect.prepareStatement(SQL);
            pstm.setString(1, nl.getMa_NL());
            pstm.setString(2, nl.getTen_NL());
            pstm.setString(3, nl.getDonViTinh_NL());
            pstm.setInt(4, nl.getSoLuongTon_NL());
            pstm.setFloat(5, nl.getGiaNhap_NL());
            pstm.setDate(6, nl.getNgayNhap_NL());
            pstm.setString(7, nl.getAnh_NL());
            pstm.setString(8, TheoMa);
            if (pstm.executeUpdate() > 0) {
                System.out.println("Sua Du Lieu Nguyen Lieu. Connect");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<NguyenLieu> importTuExcel(File fileExcel) {
        List<NguyenLieu> danhSach = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(fileExcel); Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() <= 1) {
                    continue; // Bỏ dòng tiêu đề
                }
                try {
                    // ✅ Đọc dữ liệu từng ô
                    String ma = row.getCell(0).getStringCellValue().trim();
                    String ten = row.getCell(1).getStringCellValue().trim();
                    int sl = (int) row.getCell(2).getNumericCellValue();
                    String dvt = row.getCell(3).getStringCellValue().trim();
                    float gia = (float) row.getCell(4).getNumericCellValue();
                    String anh = row.getCell(6).getStringCellValue().trim();

                    // ✅ Gán ngày nhập là ngày hệ thống
                    Date ngayNhap = new java.sql.Date(System.currentTimeMillis());

                    // ✅ Tạo đối tượng & thêm vào CSDL
                    NguyenLieu nl = new NguyenLieu(ma, ten, dvt, sl, gia, ngayNhap, anh);
                    Them_NL(nl);
                    danhSach.add(nl);

                } catch (Exception dongLoi) {
                    System.out.println("⚠️ Dòng " + (row.getRowNum() + 1) + " lỗi, bỏ qua: " + dongLoi.getMessage());
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "❌ Lỗi khi đọc Excel: " + e.getMessage(),
                    "Lỗi nhập", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return danhSach;
    }
}
