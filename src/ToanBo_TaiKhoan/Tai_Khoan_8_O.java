/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_TaiKhoan;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class Tai_Khoan_8_O {
    private String Ma_TK;
    private String Ten_TK;
    private String SDT_TK;
    private String Email_TK;
    private String DiaChi_TK;
    private Date Ngay_DK_TK;
    private String Anh_TK;
    private boolean TrangThai_TK;

    public Tai_Khoan_8_O() {
    }

    public Tai_Khoan_8_O(String Ma_TK, String Ten_TK, String SDT_TK, String Email_TK, String DiaChi_TK, Date Ngay_DK_TK, String Anh_TK, boolean TrangThai_TK) {
        this.Ma_TK = Ma_TK;
        this.Ten_TK = Ten_TK;
        this.SDT_TK = SDT_TK;
        this.Email_TK = Email_TK;
        this.DiaChi_TK = DiaChi_TK;
        this.Ngay_DK_TK = Ngay_DK_TK;
        this.Anh_TK = Anh_TK;
        this.TrangThai_TK = TrangThai_TK;
    }

    public String getMa_TK() {
        return Ma_TK;
    }

    public void setMa_TK(String Ma_TK) {
        this.Ma_TK = Ma_TK;
    }

    public String getTen_TK() {
        return Ten_TK;
    }

    public void setTen_TK(String Ten_TK) {
        this.Ten_TK = Ten_TK;
    }

    public String getSDT_TK() {
        return SDT_TK;
    }

    public void setSDT_TK(String SDT_TK) {
        this.SDT_TK = SDT_TK;
    }

    public String getEmail_TK() {
        return Email_TK;
    }

    public void setEmail_TK(String Email_TK) {
        this.Email_TK = Email_TK;
    }

    public String getDiaChi_TK() {
        return DiaChi_TK;
    }

    public void setDiaChi_TK(String DiaChi_TK) {
        this.DiaChi_TK = DiaChi_TK;
    }

    public Date getNgay_DK_TK() {
        return Ngay_DK_TK;
    }

    public void setNgay_DK_TK(Date Ngay_DK_TK) {
        this.Ngay_DK_TK = Ngay_DK_TK;
    }

    public String getAnh_TK() {
        return Anh_TK;
    }

    public void setAnh_TK(String Anh_TK) {
        this.Anh_TK = Anh_TK;
    }

    public boolean isTrangThai_TK() {
        return TrangThai_TK;
    }

    public void setTrangThai_TK(boolean TrangThai_TK) {
        this.TrangThai_TK = TrangThai_TK;
    }
    
    
}
