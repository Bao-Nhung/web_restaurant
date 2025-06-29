/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class Pizza_Topping {
    private String Ma_Pizza;
    private String Ma_Topping;

    public Pizza_Topping() {
    }

    public Pizza_Topping(String Ma_Pizza, String Ma_Topping) {
        this.Ma_Pizza = Ma_Pizza;
        this.Ma_Topping = Ma_Topping;
    }

    public String getMa_Pizza() {
        return Ma_Pizza;
    }

    public void setMa_Pizza(String Ma_Pizza) {
        this.Ma_Pizza = Ma_Pizza;
    }

    public String getMa_Topping() {
        return Ma_Topping;
    }

    public void setMa_Topping(String Ma_Topping) {
        this.Ma_Topping = Ma_Topping;
    }
    
    
}
