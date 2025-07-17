/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_Pizza;

/**
 *
 * @author ADMIN
 */
public class Pizza_DatHang {
    private String Ma_Pizza_DH; // Mã Pizza Đặt Hàng
    private String Ma_HD; // Mã Hoá Đơn
    private String Ma_SP;
    private String Ma_Size_KT; // Mã  Kích Thước 
    private int SoLuong; // Số Lượng Sản Phẩm Pizza Đặt Hàng
    private String GhiChu_PDH;

    public Pizza_DatHang() {
    }

    public Pizza_DatHang(String Ma_Pizza_DH, String Ma_HD, String Ma_SP, String Ma_Size_KT, int SoLuong, String GhiChu_PDH) {
        this.Ma_Pizza_DH = Ma_Pizza_DH;
        this.Ma_HD = Ma_HD;
        this.Ma_SP = Ma_SP;
        this.Ma_Size_KT = Ma_Size_KT;
        this.SoLuong = SoLuong;
        this.GhiChu_PDH = GhiChu_PDH;
    }

    public String getMa_Pizza_DH() {
        return Ma_Pizza_DH;
    }

    public void setMa_Pizza_DH(String Ma_Pizza_DH) {
        this.Ma_Pizza_DH = Ma_Pizza_DH;
    }

    public String getMa_HD() {
        return Ma_HD;
    }

    public void setMa_HD(String Ma_HD) {
        this.Ma_HD = Ma_HD;
    }

    public String getMa_SP() {
        return Ma_SP;
    }

    public void setMa_SP(String Ma_SP) {
        this.Ma_SP = Ma_SP;
    }

    public String getMa_Size_KT() {
        return Ma_Size_KT;
    }

    public void setMa_Size_KT(String Ma_Size_KT) {
        this.Ma_Size_KT = Ma_Size_KT;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getGhiChu_PDH() {
        return GhiChu_PDH;
    }

    public void setGhiChu_PDH(String GhiChu_PDH) {
        this.GhiChu_PDH = GhiChu_PDH;
    }
    
    
}
