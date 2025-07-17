/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_Salad;

/**
 *
 * @author ADMIN
 */
public class Salad_Topping {
    private String Ma_Salad;
    private String Ma_Topping;
    private String So_Luong_ST;
    private String Ghi_Chu_ST;

    public Salad_Topping() {
    }

    public Salad_Topping(String Ma_Salad, String Ma_Topping, String So_Luong_ST, String Ghi_Chu_ST) {
        this.Ma_Salad = Ma_Salad;
        this.Ma_Topping = Ma_Topping;
        this.So_Luong_ST = So_Luong_ST;
        this.Ghi_Chu_ST = Ghi_Chu_ST;
    }

    public String getMa_Salad() {
        return Ma_Salad;
    }

    public void setMa_Salad(String Ma_Salad) {
        this.Ma_Salad = Ma_Salad;
    }

    public String getMa_Topping() {
        return Ma_Topping;
    }

    public void setMa_Topping(String Ma_Topping) {
        this.Ma_Topping = Ma_Topping;
    }

    public String getSo_Luong_ST() {
        return So_Luong_ST;
    }

    public void setSo_Luong_ST(String So_Luong_ST) {
        this.So_Luong_ST = So_Luong_ST;
    }

    public String getGhi_Chu_ST() {
        return Ghi_Chu_ST;
    }

    public void setGhi_Chu_ST(String Ghi_Chu_ST) {
        this.Ghi_Chu_ST = Ghi_Chu_ST;
    }
    
    
}
