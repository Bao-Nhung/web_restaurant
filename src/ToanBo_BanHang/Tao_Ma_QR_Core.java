/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ToanBo_BanHang;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 *
 * @author ADMIN
 */
public class Tao_Ma_QR_Core {

    // 🏦 Cố định số tài khoản và tên tài khoản
    private static final String ACCOUNT_NAME = "NGUYEN DUC NGHIEP";
    private static final String ACCOUNT_NUMBER = "2802200816";

    public static void generateQR(double amount, String memo)
            throws WriterException, IOException {

        // Chuẩn VietQR (Techcombank)
        String bankQR = String.format(
                "vietqr://techcombank?acc=%s&name=%s&amount=%.0f&memo=%s",
                ACCOUNT_NUMBER,
                ACCOUNT_NAME.replace(" ", "+"),
                amount,
                memo.replace(" ", "+")
        );

        String filePath = "payment_qr.png";

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(bankQR, BarcodeFormat.QR_CODE, 250, 250);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        System.out.println("✅ QR code created: " + filePath);
    }

    public static BufferedImage generateQRImage(double amount, String memo)
            throws WriterException {

//        String bankQR = String.format(
//                "vietqr://techcombank?acc=%s&name=%s&amount=%.0f&memo=%s",
//                ACCOUNT_NUMBER,
//                ACCOUNT_NAME.replace(" ", "+"),
//                amount,
//                memo.replace(" ", "+")
//        );
        String bankQR = String.format(
                "https://img.vietqr.io/image/techcombank-%s-qr_only.png?amount=%.0f&addInfo=%s",
                ACCOUNT_NUMBER,
                amount,
                URLEncoder.encode(memo, StandardCharsets.UTF_8)
        );

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(bankQR, BarcodeFormat.QR_CODE, 250, 250);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

}
