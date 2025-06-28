/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class VaiTro {
    private String Ma_VT;
    private String Ten_VT;
    private String MoTa_TV;

    public VaiTro() {
    }

    public VaiTro(String Ma_VT, String Ten_VT, String MoTa_TV) {
        this.Ma_VT = Ma_VT;
        this.Ten_VT = Ten_VT;
        this.MoTa_TV = MoTa_TV;
    }

    public String getMa_VT() {
        return Ma_VT;
    }

    public void setMa_VT(String Ma_VT) {
        this.Ma_VT = Ma_VT;
    }

    public String getTen_VT() {
        return Ten_VT;
    }

    public void setTen_VT(String Ten_VT) {
        this.Ten_VT = Ten_VT;
    }

    public String getMoTa_TV() {
        return MoTa_TV;
    }

    public void setMoTa_TV(String MoTa_TV) {
        this.MoTa_TV = MoTa_TV;
    }
    
    
}
