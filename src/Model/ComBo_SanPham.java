/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class ComBo_SanPham {
    private String Ma_ComBo;
    private String Ma_SP;
    private int SoLuong;

    public ComBo_SanPham() {
    }

    public ComBo_SanPham(String Ma_ComBo, String Ma_SP, int SoLuong) {
        this.Ma_ComBo = Ma_ComBo;
        this.Ma_SP = Ma_SP;
        this.SoLuong = SoLuong;
    }

    public String getMa_ComBo() {
        return Ma_ComBo;
    }

    public void setMa_ComBo(String Ma_ComBo) {
        this.Ma_ComBo = Ma_ComBo;
    }

    public String getMa_SP() {
        return Ma_SP;
    }

    public void setMa_SP(String Ma_SP) {
        this.Ma_SP = Ma_SP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }
    
    
}
