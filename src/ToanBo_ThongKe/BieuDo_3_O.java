/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_ThongKe;

/**
 *
 * @author ADMIN
 */
public class BieuDo_3_O {
    private String Ngay; // định dạng: "dd-MM"
    private double Doanh_Thu;
    private double Loi_Nhuan;

    public BieuDo_3_O() {
    }

    public BieuDo_3_O(String Ngay, double Doanh_Thu, double Loi_Nhuan) {
        this.Ngay = Ngay;
        this.Doanh_Thu = Doanh_Thu;
        this.Loi_Nhuan = Loi_Nhuan;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String Ngay) {
        this.Ngay = Ngay;
    }

    public double getDoanh_Thu() {
        return Doanh_Thu;
    }

    public void setDoanh_Thu(double Doanh_Thu) {
        this.Doanh_Thu = Doanh_Thu;
    }

    public double getLoi_Nhuan() {
        return Loi_Nhuan;
    }

    public void setLoi_Nhuan(double Loi_Nhuan) {
        this.Loi_Nhuan = Loi_Nhuan;
    }

}
