/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_BanHang;

/**
 *
 * @author ADMIN
 */
public class ChiTietHoaDon {
    private String Ma_HD;
    private String Ma_SP;
    private int SoLuong_CTHD;
    private float DonGia_CTHD;
    private String GhiChu;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(String Ma_HD, String Ma_SP, int SoLuong_CTHD, float DonGia_CTHD, String GhiChu) {
        this.Ma_HD = Ma_HD;
        this.Ma_SP = Ma_SP;
        this.SoLuong_CTHD = SoLuong_CTHD;
        this.DonGia_CTHD = DonGia_CTHD;
        this.GhiChu = GhiChu;
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

    public int getSoLuong_CTHD() {
        return SoLuong_CTHD;
    }

    public void setSoLuong_CTHD(int SoLuong_CTHD) {
        this.SoLuong_CTHD = SoLuong_CTHD;
    }

    public float getDonGia_CTHD() {
        return DonGia_CTHD;
    }

    public void setDonGia_CTHD(float DonGia_CTHD) {
        this.DonGia_CTHD = DonGia_CTHD;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
}
