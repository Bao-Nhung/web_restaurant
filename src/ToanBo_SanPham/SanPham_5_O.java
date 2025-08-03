/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_SanPham;

/**
 *
 * @author ADMIN
 */
public class SanPham_5_O {
    private String Ma_SP;
    private String Ten_SP;
    private String MoTa_SP;
    private float DonGia_SP;
    private String HinhAnh_SP;

    public SanPham_5_O() {
    }

    public SanPham_5_O(String Ma_SP, String Ten_SP, String MoTa_SP, float DonGia_SP, String HinhAnh_SP) {
        this.Ma_SP = Ma_SP;
        this.Ten_SP = Ten_SP;
        this.MoTa_SP = MoTa_SP;
        this.DonGia_SP = DonGia_SP;
        this.HinhAnh_SP = HinhAnh_SP;
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

    public String getHinhAnh_SP() {
        return HinhAnh_SP;
    }

    public void setHinhAnh_SP(String HinhAnh_SP) {
        this.HinhAnh_SP = HinhAnh_SP;
    }
    
}
