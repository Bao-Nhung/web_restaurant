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

/**
 *
 * @author ADMIN
 */
public class Tao_Ma_QR_Core_01 {

    // 🏦 Thông tin tài khoản ngân hàng
    private static final String ACCOUNT_NUMBER = "0899076903"; // Số tài khoản
    private static final String BANK_BIN = "280228";               // BIN Techcombank
    private static final String ACCOUNT_NAME = "NGUYEN DUC NGHIEP";     // Tên chủ TK

    /**
     * ✅ Sinh mã QR VietQR chuẩn EMV
     */
    public static BufferedImage generateVietQR(double amount, String memo) throws WriterException {
        String emvQR = generateEMV(ACCOUNT_NUMBER, BANK_BIN, amount, memo);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(emvQR, BarcodeFormat.QR_CODE, 300, 300);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    /**
     * ✅ Build dữ liệu EMV VietQR
     */
    private static String generateEMV(String accountNumber, String bankBin, double amount, String memo) {
        String payloadFormat = formatTLV("00", "01");
        String initiationMethod = formatTLV("01", "12"); // 12 = dynamic QR

        // 👉 Merchant Account Information (Tag 38)
        String guiId = formatTLV("00", "A000000727"); // Fixed ID cho VietQR
        String bankId = formatTLV("01", bankBin);     // BIN ngân hàng
        String accInfo = formatTLV("02", accountNumber);
        String merchantInfo = formatTLV("38", guiId + bankId + accInfo);

        // 👉 Currency (VND = 704)
        String currency = formatTLV("53", "704");

        // 👉 Amount
        String amountStr = String.format("%.0f", amount);
        String amountField = formatTLV("54", amountStr);

        // 👉 Country
        String country = formatTLV("58", "VN");

        // 👉 Memo (Tag 62)
        String memoField = formatTLV("01", memo);
        String additionalData = formatTLV("62", memoField);

        // 👉 Build raw string
        String rawData = payloadFormat + initiationMethod + merchantInfo
                + currency + amountField + country + additionalData;

        // 👉 CRC
        String crcInput = rawData + "6304";
        String crc = calculateCRC16(crcInput);
        String crcField = formatTLV("63", crc);

        return rawData + crcField;
    }

    /**
     * ✅ Format Tag-Length-Value
     */
    private static String formatTLV(String tag, String value) {
        return String.format("%s%02d%s", tag, value.length(), value);
    }

    /**
     * ✅ Tính CRC16/CCITT-FALSE
     */
    private static String calculateCRC16(String data) {
        int crc = 0xFFFF;
        for (int i = 0; i < data.length(); i++) {
            crc ^= (data.charAt(i) << 8);
            for (int j = 0; j < 8; j++) {
                if ((crc & 0x8000) != 0) {
                    crc = (crc << 1) ^ 0x1021;
                } else {
                    crc <<= 1;
                }
                crc &= 0xFFFF;
            }
        }
        return String.format("%04X", crc);
    }
}
