/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class Salad_DatHang {
    private String Ma_Salad;
    private String Ma_HD;
    private String Ma_SP;
    private String Ghi_Chu_SDH;

    public Salad_DatHang() {
    }

    public Salad_DatHang(String Ma_Salad, String Ma_HD, String Ma_SP, String Ghi_Chu_SDH) {
        this.Ma_Salad = Ma_Salad;
        this.Ma_HD = Ma_HD;
        this.Ma_SP = Ma_SP;
        this.Ghi_Chu_SDH = Ghi_Chu_SDH;
    }

    public String getMa_Salad() {
        return Ma_Salad;
    }

    public void setMa_Salad(String Ma_Salad) {
        this.Ma_Salad = Ma_Salad;
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

    public String getGhi_Chu_SDH() {
        return Ghi_Chu_SDH;
    }

    public void setGhi_Chu_SDH(String Ghi_Chu_SDH) {
        this.Ghi_Chu_SDH = Ghi_Chu_SDH;
    }
    
    
}
