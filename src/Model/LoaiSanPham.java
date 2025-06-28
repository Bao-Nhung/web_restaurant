/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class LoaiSanPham {
    private String Ma_LSP;
    private String Ten_LSP;

    public LoaiSanPham() {
    }

    public LoaiSanPham(String Ma_LSP, String Ten_LSP) {
        this.Ma_LSP = Ma_LSP;
        this.Ten_LSP = Ten_LSP;
    }

    public String getMa_LSP() {
        return Ma_LSP;
    }

    public void setMa_LSP(String Ma_LSP) {
        this.Ma_LSP = Ma_LSP;
    }

    public String getTen_LSP() {
        return Ten_LSP;
    }

    public void setTen_LSP(String Ten_LSP) {
        this.Ten_LSP = Ten_LSP;
    }
    
    
}
