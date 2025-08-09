/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBConnect;

/**
 *
 * @author ADMIN
 */
public class Chua_Bien {
    private String Ma_TK;
    private boolean TrangThai;
    private String Anh_TK;

    public Chua_Bien() {
    }

    public Chua_Bien(String Ma_TK, boolean TrangThai, String Anh_TK) {
        this.Ma_TK = Ma_TK;
        this.TrangThai = TrangThai;
        this.Anh_TK = Anh_TK;
    }
    
    public String getMa_TK() {
        return Ma_TK;
    }

    public void setMa_TK(String Ma_TK) {
        this.Ma_TK = Ma_TK;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getAnh_TK() {
        return Anh_TK;
    }

    public void setAnh_TK(String Anh_TK) {
        this.Anh_TK = Anh_TK;
    }
    
    
}
