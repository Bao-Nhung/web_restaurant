/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.Date;
/**
 *
 * @author ADMIN
 */
public class NhatKy {
    private String Ma_NK;
    private String Ma_TK;
    private String HanhDong_NK;
    private Date ThoiGian_NK;

    public NhatKy() {
    }

    public NhatKy(String Ma_NK, String Ma_TK, String HanhDong_NK, Date ThoiGian_NK) {
        this.Ma_NK = Ma_NK;
        this.Ma_TK = Ma_TK;
        this.HanhDong_NK = HanhDong_NK;
        this.ThoiGian_NK = ThoiGian_NK;
    }

    public String getMa_NK() {
        return Ma_NK;
    }

    public void setMa_NK(String Ma_NK) {
        this.Ma_NK = Ma_NK;
    }

    public String getMa_TK() {
        return Ma_TK;
    }

    public void setMa_TK(String Ma_TK) {
        this.Ma_TK = Ma_TK;
    }

    public String getHanhDong_NK() {
        return HanhDong_NK;
    }

    public void setHanhDong_NK(String HanhDong_NK) {
        this.HanhDong_NK = HanhDong_NK;
    }

    public Date getThoiGian_NK() {
        return ThoiGian_NK;
    }

    public void setThoiGian_NK(Date ThoiGian_NK) {
        this.ThoiGian_NK = ThoiGian_NK;
    }
    
    
}
