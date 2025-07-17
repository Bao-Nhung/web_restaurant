/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_KhachHang;
import java.sql.Date;
/**
 *
 * @author ADMIN
 */
public class KhachHang {
    private String Ma_KH;
    private String Ten_KH;
    private String SDT_KH;
    private String Email_KH;
    private Date NgayTao_KH;
    private String Loai_KH;
    private int DiemTichLuy;
    
    public KhachHang() {
    }

    public KhachHang(String Ma_KH, String Ten_KH, String SDT_KH, String Email_KH, Date NgayTao_KH, String Loai_KH, int DiemTichLuy) {
        this.Ma_KH = Ma_KH;
        this.Ten_KH = Ten_KH;
        this.SDT_KH = SDT_KH;
        this.Email_KH = Email_KH;
        this.NgayTao_KH = NgayTao_KH;
        this.Loai_KH = Loai_KH;
        this.DiemTichLuy = DiemTichLuy;
    }

    

    public String getMa_KH() {
        return Ma_KH;
    }

    public void setMa_KH(String Ma_KH) {
        this.Ma_KH = Ma_KH;
    }

    public String getTen_KH() {
        return Ten_KH;
    }

    public void setTen_KH(String Ten_KH) {
        this.Ten_KH = Ten_KH;
    }

    public String getSDT_KH() {
        return SDT_KH;
    }

    public void setSDT_KH(String SDT_KH) {
        this.SDT_KH = SDT_KH;
    }

    public String getEmail_KH() {
        return Email_KH;
    }

    public void setEmail_KH(String Email_KH) {
        this.Email_KH = Email_KH;
    }

    public Date getNgayTao_KH() {
        return NgayTao_KH;
    }

    public void setNgayTao_KH(Date NgayTao_KH) {
        this.NgayTao_KH = NgayTao_KH;
    }

    public String getLoai_KH() {
        return Loai_KH;
    }

    public void setLoai_KH(String Loai_KH) {
        this.Loai_KH = Loai_KH;
    }

    public int getDiemTichLuy() {
        return DiemTichLuy;
    }

    public void setDiemTichLuy(int DiemTichLuy) {
        this.DiemTichLuy = DiemTichLuy;
    }
      
}
