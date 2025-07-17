/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_SanPham;

import DBConnect.MyConnection;
import java.util.List;
import ToanBo_SanPham.LoaiSanPham;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author ADMIN
 */
public class QL_ChiTiet_SanPham {
    MyConnection conn;

    public QL_ChiTiet_SanPham() {
        conn = new MyConnection();
    }
    
    public List<LoaiSanPham> getALL_LSP(){
        List<LoaiSanPham> List_LSP = new ArrayList<>();
        String SQL = "SELECT * FROM LOAISANPHAM";
        try {
            Connection connect = conn.DBConnect();
            Statement stm = connect.createStatement();
            ResultSet rs = stm.executeQuery(SQL);
            while (rs.next()) {                
                String Ma_LSP = rs.getString(1);
                String Ten_LSP = rs.getString(2);
                LoaiSanPham lsp = new LoaiSanPham(Ma_LSP, Ten_LSP);
                List_LSP.add(lsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List_LSP;
    }
    
    public Object[] GetRow_LSP(LoaiSanPham lsp){
        String Ma_LSP = lsp.getMa_LSP();
        String Ten_LSP = lsp.getTen_LSP();
        
        Object[] obj = new Object[] {Ma_LSP , Ten_LSP};
        return obj;
    }
}
