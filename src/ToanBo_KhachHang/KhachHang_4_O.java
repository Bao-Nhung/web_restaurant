/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_KhachHang;

/**
 *
 * @author ADMIN
 */
public class KhachHang_4_O {
    private String Ma_KH;
    private String Ten_KH;
    private String SDT;
    private int DiemTichLuy;

    public KhachHang_4_O() {
    }

    public KhachHang_4_O(String Ma_KH, String Ten_KH, String SDT, int DiemTichLuy) {
        this.Ma_KH = Ma_KH;
        this.Ten_KH = Ten_KH;
        this.SDT = SDT;
        this.DiemTichLuy = DiemTichLuy;
    }

    public String getMa_KH() {
        return Ma_KH;
    }

    public void setMa_KH(String Ma_KH) {
        this.Ma_KH = Ma_KH;
    }

    public String getTen_KH() {
        return Ten_KH;
    }

    public void setTen_KH(String Ten_KH) {
        this.Ten_KH = Ten_KH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public int getDiemTichLuy() {
        return DiemTichLuy;
    }

    public void setDiemTichLuy(int DiemTichLuy) {
        this.DiemTichLuy = DiemTichLuy;
    }

    
}
