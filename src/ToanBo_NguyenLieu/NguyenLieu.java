/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_NguyenLieu;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class NguyenLieu {

    private String Ma_NL;
    private String Ten_NL;
    private String DonViTinh_NL;
    private int SoLuongTon_NL;
    private float GiaNhap_NL;
    private Date NgayNhap_NL;
    private String Anh_NL;

    public NguyenLieu() {
    }

    public NguyenLieu(String Ma_NL, String Ten_NL, String DonViTinh_NL, int SoLuongTon_NL, float GiaNhap_NL, Date NgayNhap_NL, String Anh_NL) {
        this.Ma_NL = Ma_NL;
        this.Ten_NL = Ten_NL;
        this.DonViTinh_NL = DonViTinh_NL;
        this.SoLuongTon_NL = SoLuongTon_NL;
        this.GiaNhap_NL = GiaNhap_NL;
        this.NgayNhap_NL = NgayNhap_NL;
        this.Anh_NL = Anh_NL;
    }

    public String getMa_NL() {
        return Ma_NL;
    }

    public void setMa_NL(String Ma_NL) {
        this.Ma_NL = Ma_NL;
    }

    public String getTen_NL() {
        return Ten_NL;
    }

    public void setTen_NL(String Ten_NL) {
        this.Ten_NL = Ten_NL;
    }

    public String getDonViTinh_NL() {
        return DonViTinh_NL;
    }

    public void setDonViTinh_NL(String DonViTinh_NL) {
        this.DonViTinh_NL = DonViTinh_NL;
    }

    public int getSoLuongTon_NL() {
        return SoLuongTon_NL;
    }

    public void setSoLuongTon_NL(int SoLuongTon_NL) {
        this.SoLuongTon_NL = SoLuongTon_NL;
    }

    public float getGiaNhap_NL() {
        return GiaNhap_NL;
    }

    public void setGiaNhap_NL(float GiaNhap_NL) {
        this.GiaNhap_NL = GiaNhap_NL;
    }

    public Date getNgayNhap_NL() {
        return NgayNhap_NL;
    }

    public void setNgayNhap_NL(Date NgayNhap_NL) {
        this.NgayNhap_NL = NgayNhap_NL;
    }

    public String getAnh_NL() {
        return Anh_NL;
    }

    public void setAnh_NL(String Anh_NL) {
        this.Anh_NL = Anh_NL;
    }

}
