/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_NguyenLieu;

/**
 *
 * @author ADMIN
 */
public class ChiTietNguyenLieu {
    private String Ma_SP;
    private String Ma_NL;
    private float SoLuongCanDung_CTNL;

    public ChiTietNguyenLieu() {
    }

    public ChiTietNguyenLieu(String Ma_SP, String Ma_NL, float SoLuongCanDung_CTNL) {
        this.Ma_SP = Ma_SP;
        this.Ma_NL = Ma_NL;
        this.SoLuongCanDung_CTNL = SoLuongCanDung_CTNL;
    }

    public String getMa_SP() {
        return Ma_SP;
    }

    public void setMa_SP(String Ma_SP) {
        this.Ma_SP = Ma_SP;
    }

    public String getMa_NL() {
        return Ma_NL;
    }

    public void setMa_NL(String Ma_NL) {
        this.Ma_NL = Ma_NL;
    }

    public float getSoLuongCanDung_CTNL() {
        return SoLuongCanDung_CTNL;
    }

    public void setSoLuongCanDung_CTNL(float SoLuongCanDung_CTNL) {
        this.SoLuongCanDung_CTNL = SoLuongCanDung_CTNL;
    }
    
    
}
