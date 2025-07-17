/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_ComBo;
import java.sql.Date;
/**
 *
 * @author ADMIN
 */
public class ComBo {
    private String Ma_ComBo;
    private String Ten_CB;
    private String Mo_Ta;
    private float Gia_Uu_Dai_CB;
    private Date Ngay_BD;
    private Date Ngay_KT;

    public ComBo() {
    }

    public ComBo(String Ma_ComBo, String Ten_CB, String Mo_Ta, float Gia_Uu_Dai_CB, Date Ngay_BD, Date Ngay_KT) {
        this.Ma_ComBo = Ma_ComBo;
        this.Ten_CB = Ten_CB;
        this.Mo_Ta = Mo_Ta;
        this.Gia_Uu_Dai_CB = Gia_Uu_Dai_CB;
        this.Ngay_BD = Ngay_BD;
        this.Ngay_KT = Ngay_KT;
    }

    public String getMa_ComBo() {
        return Ma_ComBo;
    }

    public void setMa_ComBo(String Ma_ComBo) {
        this.Ma_ComBo = Ma_ComBo;
    }

    public String getTen_CB() {
        return Ten_CB;
    }

    public void setTen_CB(String Ten_CB) {
        this.Ten_CB = Ten_CB;
    }

    public String getMo_Ta() {
        return Mo_Ta;
    }

    public void setMo_Ta(String Mo_Ta) {
        this.Mo_Ta = Mo_Ta;
    }

    public float getGia_Uu_Dai_CB() {
        return Gia_Uu_Dai_CB;
    }

    public void setGia_Uu_Dai_CB(float Gia_Uu_Dai_CB) {
        this.Gia_Uu_Dai_CB = Gia_Uu_Dai_CB;
    }

    public Date getNgay_BD() {
        return Ngay_BD;
    }

    public void setNgay_BD(Date Ngay_BD) {
        this.Ngay_BD = Ngay_BD;
    }

    public Date getNgay_KT() {
        return Ngay_KT;
    }

    public void setNgay_KT(Date Ngay_KT) {
        this.Ngay_KT = Ngay_KT;
    }
    
    
}
