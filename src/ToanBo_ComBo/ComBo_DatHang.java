/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_ComBo;

/**
 *
 * @author ADMIN
 */
public class ComBo_DatHang {
    private String Ma_HD;
    private String Ma_ComBo;
    private int SoLuong;

    public ComBo_DatHang() {
    }

    public ComBo_DatHang(String Ma_HD, String Ma_ComBo, int SoLuong) {
        this.Ma_HD = Ma_HD;
        this.Ma_ComBo = Ma_ComBo;
        this.SoLuong = SoLuong;
    }

    public String getMa_HD() {
        return Ma_HD;
    }

    public void setMa_HD(String Ma_HD) {
        this.Ma_HD = Ma_HD;
    }

    public String getMa_ComBo() {
        return Ma_ComBo;
    }

    public void setMa_ComBo(String Ma_ComBo) {
        this.Ma_ComBo = Ma_ComBo;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }
    
    
}
