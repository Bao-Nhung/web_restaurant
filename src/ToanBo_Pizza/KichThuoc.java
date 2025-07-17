/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_Pizza;

/**
 *
 * @author ADMIN
 */
public class KichThuoc {
    private String Ma_KT; // Mã Size Cho Món Pizza
    private String Ten_KT;
    private float HeSoGia; // Mỗi Kích Thước Có Giá Tiền Khác Nhau

    public KichThuoc() {
    }

    public KichThuoc(String Ma_KT, String Ten_KT, float HeSoGia) {
        this.Ma_KT = Ma_KT;
        this.Ten_KT = Ten_KT;
        this.HeSoGia = HeSoGia;
    }

    public String getMa_KT() {
        return Ma_KT;
    }

    public void setMa_KT(String Ma_KT) {
        this.Ma_KT = Ma_KT;
    }

    public String getTen_KT() {
        return Ten_KT;
    }

    public void setTen_KT(String Ten_KT) {
        this.Ten_KT = Ten_KT;
    }

    public float getHeSoGia() {
        return HeSoGia;
    }

    public void setHeSoGia(float HeSoGia) {
        this.HeSoGia = HeSoGia;
    }
    
    
}
