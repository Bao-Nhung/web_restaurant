/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_Pizza;

/**
 *
 * @author ADMIN
 */
public class Topping {
    private String Ma_TP;
    private String Ten_TP;
    private float GiaThem_TP;

    public Topping() {
    }

    public Topping(String Ma_TP, String Ten_TP, float GiaThem_TP) {
        this.Ma_TP = Ma_TP;
        this.Ten_TP = Ten_TP;
        this.GiaThem_TP = GiaThem_TP;
    }

    public String getMa_TP() {
        return Ma_TP;
    }

    public void setMa_TP(String Ma_TP) {
        this.Ma_TP = Ma_TP;
    }

    public String getTen_TP() {
        return Ten_TP;
    }

    public void setTen_TP(String Ten_TP) {
        this.Ten_TP = Ten_TP;
    }

    public float getGiaThem_TP() {
        return GiaThem_TP;
    }

    public void setGiaThem_TP(float GiaThem_TP) {
        this.GiaThem_TP = GiaThem_TP;
    }
    
    
}
