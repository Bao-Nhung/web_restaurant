/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author asus
 */
public class MyConnection {
    
public MyConnection() {
        
    
    
    }
    
    public Connection DBConnect() throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost\\SQLEXPRESS01:1433;databaseName=DuAnMau;trustServerCertificate=true";
        String user = "sa";
        String pass = "123456789"; // Tự Đổi Mật Khẩu Nếu Muốn Chạy DBConnect 
        return DriverManager.getConnection(url, user, pass);
    }
    
    
    public static void main(String[] args) {
        MyConnection myConn = new MyConnection();
        try {
            Connection conn = myConn.DBConnect();
            if (conn != null && !conn.isClosed()) {
                System.out.println("Kết nối cơ sở dữ liệu thành công!");
            } else {
                System.out.println("Kết nối thất bại: Connection là null hoặc đã đóng.");
            }
            conn.close(); // Đóng kết nối sau khi kiểm tra
        } catch (SQLException e) {
            System.out.println("Lỗi SQLException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Lỗi ClassNotFoundException: " + e.getMessage());
        }
    }
    
    
    
}


