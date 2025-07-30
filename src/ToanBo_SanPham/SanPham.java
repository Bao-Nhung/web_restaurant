/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_SanPham;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class SanPham {
    private String Ma_SP;
    private String Ten_SP;
    private String MoTa_SP;
    private float DonGia_SP;
    private String Ma_LSP;
    private String HinhAnh_SP;
    private Date NgayTao_SP;
    private String TrangThai_SP;
    public SanPham() {
    }

    public SanPham(String Ma_SP, String Ten_SP, String MoTa_SP, float DonGia_SP, String Ma_LSP, String HinhAnh_SP, Date NgayTao_SP, String TrangThai_SP) {
        this.Ma_SP = Ma_SP;
        this.Ten_SP = Ten_SP;
        this.MoTa_SP = MoTa_SP;
        this.DonGia_SP = DonGia_SP;
        this.Ma_LSP = Ma_LSP;
        this.HinhAnh_SP = HinhAnh_SP;
        this.NgayTao_SP = NgayTao_SP;
        this.TrangThai_SP = TrangThai_SP;
    }

    public String getMa_SP() {
        return Ma_SP;
    }

    public void setMa_SP(String Ma_SP) {
        this.Ma_SP = Ma_SP;
    }

    public String getTen_SP() {
        return Ten_SP;
    }

    public void setTen_SP(String Ten_SP) {
        this.Ten_SP = Ten_SP;
    }

    public String getMoTa_SP() {
        return MoTa_SP;
    }

    public void setMoTa_SP(String MoTa_SP) {
        this.MoTa_SP = MoTa_SP;
    }

    public float getDonGia_SP() {
        return DonGia_SP;
    }

    public void setDonGia_SP(float DonGia_SP) {
        this.DonGia_SP = DonGia_SP;
    }

    public String getMa_LSP() {
        return Ma_LSP;
    }

    public void setMa_LSP(String Ma_LSP) {
        this.Ma_LSP = Ma_LSP;
    }

    public String getHinhAnh_SP() {
        return HinhAnh_SP;
    }

    public void setHinhAnh_SP(String HinhAnh_SP) {
        this.HinhAnh_SP = HinhAnh_SP;
    }

    public Date getNgayTao_SP() {
        return NgayTao_SP;
    }

    public void setNgayTao_SP(Date NgayTao_SP) {
        this.NgayTao_SP = NgayTao_SP;
    }

    public String getTrangThai_SP() {
        return TrangThai_SP;
    }

    public void setTrangThai_SP(String TrangThai_SP) {
        this.TrangThai_SP = TrangThai_SP;
    }
    
}
