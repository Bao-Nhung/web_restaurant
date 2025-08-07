/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_ThongKe;

/**
 *
 * @author ADMIN
 */
public class SanPhamBanChay {
    private String Ten_SP;
    private int SoLuong;
    private float ThanhTien;
    private String Anh_SP;

    public SanPhamBanChay() {
    }

    public SanPhamBanChay(String Ten_SP, int SoLuong, float ThanhTien, String Anh_SP) {
        this.Ten_SP = Ten_SP;
        this.SoLuong = SoLuong;
        this.ThanhTien = ThanhTien;
        this.Anh_SP = Anh_SP;
    }

    public String getTen_SP() {
        return Ten_SP;
    }

    public void setTen_SP(String Ten_SP) {
        this.Ten_SP = Ten_SP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public float getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(float ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public String getAnh_SP() {
        return Anh_SP;
    }

    public void setAnh_SP(String Anh_SP) {
        this.Anh_SP = Anh_SP;
    }
    
}
