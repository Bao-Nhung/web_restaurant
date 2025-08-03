/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_KhuyenMai;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class DateUtils {

    // Chuyển từ java.util.Date sang LocalDate
    public static LocalDate convertToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    // Chuyển từ LocalDate sang java.sql.Date (để insert/update DB)
    public static java.sql.Date convertToSqlDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

    // Chuyển từ java.util.Date sang java.sql.Date
    public static java.sql.Date convertUtilToSqlDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    // Chuyển từ java.sql.Date sang LocalDate
    public static LocalDate convertSqlDateToLocalDate(java.sql.Date sqlDate) {
        return sqlDate.toLocalDate();
    }
}
