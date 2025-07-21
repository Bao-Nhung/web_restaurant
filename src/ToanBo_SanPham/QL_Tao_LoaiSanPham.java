/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_SanPham;

import DBConnect.MyConnection;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class QL_Tao_LoaiSanPham {

    MyConnection conn;

    public QL_Tao_LoaiSanPham() {
        conn = new MyConnection();
    }

    public List<LoaiSanPham> Get_All_LSP() {
        List<LoaiSanPham> List_LSP = new ArrayList<>();
        String SQL = "SELECT * FROM LOAISANPHAM";
        try {
            Connection connect = conn.DBConnect();
            Statement stm = connect.createStatement();
            ResultSet rs = stm.executeQuery(SQL);
            while (rs.next()) {
                String Ma_LSP = rs.getString(1);
                String Ten_LSP = rs.getString(2);
                String MoTa_LSP = rs.getString(3);
                LoaiSanPham lsp = new LoaiSanPham(Ma_LSP, Ten_LSP, MoTa_LSP);
                List_LSP.add(lsp);
            }
        } catch (Exception e) {
        }
        return List_LSP;
    }

    public Object[] Get_Row_LSP(LoaiSanPham lsp) {
        String Ma_LSP = lsp.getMa_LSP();
        String Ten_LSP = lsp.getTen_LSP();
        String MoTa_LSP = lsp.getMoTa_LSP();
        Object[] obj = new Object[]{Ma_LSP, Ten_LSP, MoTa_LSP};
        return obj;
    }

    public int Them_LSP(LoaiSanPham lsp) {
        String SQL = "INSERT INTO LOAISANPHAM (MA_LOAI , TENLOAI , MOTA ) VALUES \n"
                + "(   ?  ,  ?  , ?)";
        try {
            Connection connect = conn.DBConnect();
            PreparedStatement pstm = connect.prepareCall(SQL);
            pstm.setString(1, lsp.getMa_LSP());
            pstm.setString(2, lsp.getTen_LSP());
            pstm.setString(3, lsp.getMoTa_LSP());
            if (pstm.executeUpdate() >= 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int Xoa_LSP(String TheoMa) {
        String SQL = "DELETE FROM LOAISANPHAM WHERE MA_LOAI =   ?  ";
        try {
            Connection connect = conn.DBConnect();
            PreparedStatement pstm = connect.prepareStatement(SQL);
            pstm.setString(1, TheoMa);
            if (pstm.executeUpdate() >= 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int Sua_LSP(LoaiSanPham lsp, String TheoMa) {
        String SQL = "UPDATE LOAISANPHAM SET MA_LOAI =  ?  ,\n"
                + "                          TENLOAI =  ?  ,\n"
                + "			     MOTA =  ?  \n"
                + "		             WHERE MA_LOAI =  ?  ";
        try {
            Connection connect = conn.DBConnect();
            PreparedStatement pstm = connect.prepareStatement(SQL);
            pstm.setString(1, lsp.getMa_LSP());
            pstm.setString(2, lsp.getTen_LSP());
            pstm.setString(3, lsp.getMoTa_LSP());
            pstm.setString(4, TheoMa);
            if (pstm.executeUpdate() >= 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
