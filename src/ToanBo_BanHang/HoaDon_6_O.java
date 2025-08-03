/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_BanHang;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class HoaDon_6_O {
    private String Ma_HD;
    private String Ma_TK;
    private Date NgayLap_HD;
    private float TongTien;
    private String HinhThuc_HD;
    private String TrangThai;

    public HoaDon_6_O() {
    }

    public HoaDon_6_O(String Ma_HD, String Ma_TK, Date NgayLap_HD, float TongTien, String HinhThuc_HD, String TrangThai) {
        this.Ma_HD = Ma_HD;
        this.Ma_TK = Ma_TK;
        this.NgayLap_HD = NgayLap_HD;
        this.TongTien = TongTien;
        this.HinhThuc_HD = HinhThuc_HD;
        this.TrangThai = TrangThai;
    }

    public String getMa_HD() {
        return Ma_HD;
    }

    public void setMa_HD(String Ma_HD) {
        this.Ma_HD = Ma_HD;
    }

    public String getMa_TK() {
        return Ma_TK;
    }

    public void setMa_TK(String Ma_TK) {
        this.Ma_TK = Ma_TK;
    }

    public Date getNgayLap_HD() {
        return NgayLap_HD;
    }

    public void setNgayLap_HD(Date NgayLap_HD) {
        this.NgayLap_HD = NgayLap_HD;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }

    public String getHinhThuc_HD() {
        return HinhThuc_HD;
    }

    public void setHinhThuc_HD(String HinhThuc_HD) {
        this.HinhThuc_HD = HinhThuc_HD;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
