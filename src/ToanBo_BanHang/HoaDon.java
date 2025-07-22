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
public class HoaDon {
    private String Ma_HD;
    private String Ma_TK;
    private Date NgayLap_HD;
    private float TongTien;
    private String HinhThuc_HD;
    private String TrangThai;
    private String Ma_KM;
    private float SoTienKhachTra_HD;
    private String Ma_KH;

    public HoaDon() {
    }

    public HoaDon(String Ma_HD, String Ma_TK, Date NgayLap_HD, float TongTien, String HinhThuc_HD, String TrangThai, String Ma_KM, float SoTienKhachTra_HD, String Ma_KH) {
        this.Ma_HD = Ma_HD;
        this.Ma_TK = Ma_TK;
        this.NgayLap_HD = NgayLap_HD;
        this.TongTien = TongTien;
        this.HinhThuc_HD = HinhThuc_HD;
        this.TrangThai = TrangThai;
        this.Ma_KM = Ma_KM;
        this.SoTienKhachTra_HD = SoTienKhachTra_HD;
        this.Ma_KH = Ma_KH;
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

    public String getMa_KM() {
        return Ma_KM;
    }

    public void setMa_KM(String Ma_KM) {
        this.Ma_KM = Ma_KM;
    }

    public float getSoTienKhachTra_HD() {
        return SoTienKhachTra_HD;
    }

    public void setSoTienKhachTra_HD(float SoTienKhachTra_HD) {
        this.SoTienKhachTra_HD = SoTienKhachTra_HD;
    }

    public String getMa_KH() {
        return Ma_KH;
    }

    public void setMa_KH(String Ma_KH) {
        this.Ma_KH = Ma_KH;
    }
    
}
