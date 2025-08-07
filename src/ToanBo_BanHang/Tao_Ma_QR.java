/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ToanBo_BanHang;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import com.google.zxing.EncodeHintType;
import java.awt.Desktop;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author ADMIN
 */
public class Tao_Ma_QR extends javax.swing.JFrame {

    /**
     * Creates new form Tao_Ma_QR
     */
    public Tao_Ma_QR() {
        initComponents();
//
        String[] danhSachNganHang = {
            "Vietcombank",
            "Techcombank",
            "VietinBank",
            "BIDV",
            "Agribank",
            "MB Bank",
            "ACB",
            "Sacombank",
            "TPBank",
            "VPBank"
        };

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(danhSachNganHang);
        cbox_Ten_Ngan_Hang.setModel(model);

//        btn_TaoMa_QR.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    String soTK = txt_SoTaiKhoan.getText().trim();
//                    String tenNH = cbox_Ten_Ngan_Hang.getSelectedItem().toString().trim();
//                    String soTien = txt_SoTien.getText().trim();
//
//                    // Kiểm tra dữ liệu hợp lệ
//                    if (soTK.isEmpty() || tenNH.isEmpty() || soTien.isEmpty()) {
//                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin.");
//                        return;
//                    }
//
//                    double amount = Double.parseDouble(soTien);
//
//                    String memo = "Thanh toan hoa don";
//                    String qrContent = String.format(
//                            "vietqr://%s?acc=%s&name=%s&amount=%.0f&memo=%s",
//                            tenNH.toLowerCase().replaceAll(" ", ""), // tên ngân hàng dạng chuẩn, không dấu cách
//                            soTK,
//                            "TEN+CHU+TK", // hoặc cho phép nhập thêm
//                            amount,
//                            memo.replace(" ", "+")
//                    );
//
//                    BufferedImage qrImage = generateQRImage(qrContent);
//                    lb_HienMa_QR.setIcon(new ImageIcon(qrImage));
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(null, "Lỗi khi tạo mã QR!");
//                }
//            }
//        });
        btn_TaoMa_QR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String stk = txt_SoTaiKhoan.getText().trim();
                    if (stk.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Số Tài Khoản Không Được Để Trống.");
                        return;
                    }
                    String bankId = layBankIdTuTen(cbox_Ten_Ngan_Hang.getSelectedItem().toString().trim());
                    String tenTk = txt_Ten_TaiKhoan.getText(); // Có thể nhập từ giao diện nếu cần
                    double soTien = Double.parseDouble(txt_SoTien.getText().trim());
                    String noiDung = txt_NoiDung.getText().trim();

                    String linkQR = taoLinkVietQR(bankId, stk, tenTk, soTien, noiDung);

                    // ✅ Hiển thị link lên label
                    lb_HienMa_QR.setText("<html><a href='" + linkQR + "'>Click để mở QR VietQR</a></html>");

                    // ✅ Mở link bằng trình duyệt (nếu muốn)
                    Desktop.getDesktop().browse(new URI(linkQR));

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi tạo link VietQR: " + ex.getMessage());
                }
            }
        });

    }

    public static String taoLinkVietQR(String bankId, String stk, String tenTk, double soTien, String noiDung) throws UnsupportedEncodingException {
        String baseUrl = "https://img.vietqr.io/image/";
        String fullUrl = baseUrl
                + bankId.toLowerCase() + "-" + stk + "-compact2.png"
                + "?amount=" + (int) soTien
                + "&addInfo=" + URLEncoder.encode(noiDung, "UTF-8")
                + "&accountName=" + URLEncoder.encode(tenTk, "UTF-8");
        return fullUrl;
    }

    public static String layBankIdTuTen(String tenNganHang) {
        switch (tenNganHang) {
            case "Vietcombank":
                return "vietcombank";
            case "Techcombank":
                return "techcombank";
            case "VietinBank":
                return "vietinbank";
            case "BIDV":
                return "bidv";
            case "Agribank":
                return "agribank";
            case "MB Bank":
                return "mbbank";
            case "ACB":
                return "acb";
            case "Sacombank":
                return "sacombank";
            case "TPBank":
                return "tpbank";
            case "VPBank":
                return "vpbank";
            default:
                return "vietcombank"; // mặc định
        }
    }
    //    public BufferedImage generateQRImage(String text) throws WriterException {
            //        int width = 200;
            //        int height = 200;
            //
            //        QRCodeWriter qrCodeWriter = new QRCodeWriter();
            //        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            //
            //        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            //        for (int x = 0; x < width; x++) {
            //            for (int y = 0; y < height; y++) {
            //                image.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
            //            }
            //        }
            //        return image;
            //    }


    public BufferedImage generateQRImage(String text) throws WriterException {
        int width = 300;
        int height = 300;

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // Hỗ trợ tiếng Việt
        hints.put(EncodeHintType.MARGIN, 1);              // Giảm viền trắng

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
            }
        }
        return image;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_HienMa_QR = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_SoTaiKhoan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_SoTien = new javax.swing.JTextField();
        btn_TaoMa_QR = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Danh_SachMa_QR = new javax.swing.JTable();
        btn_Sua = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        cbox_Ten_Ngan_Hang = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txt_NoiDung = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_Ten_TaiKhoan = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lb_HienMa_QR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã QR:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Số Tài Khoản: ");

        txt_SoTaiKhoan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Tên Ngân Hàng: ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Số Tiền:");

        txt_SoTien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_SoTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SoTienActionPerformed(evt);
            }
        });

        btn_TaoMa_QR.setText("Tạo Mã QR");

        jButton2.setText("Huỷ Tạo");

        jButton3.setText("Làm Mới");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        tbl_Danh_SachMa_QR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl_Danh_SachMa_QR);

        btn_Sua.setText("Sửa Mã QR");

        btn_Xoa.setText("Xoá Mã QR");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        cbox_Ten_Ngan_Hang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Nội Dung:");

        txt_NoiDung.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Tên Tài Khoản :");

        txt_Ten_TaiKhoan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_SoTaiKhoan))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lb_HienMa_QR, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbox_Ten_Ngan_Hang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_SoTien)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_NoiDung)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_Ten_TaiKhoan)))
                        .addContainerGap(17, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_TaoMa_QR, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                    .addComponent(lb_HienMa_QR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_SoTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Ten_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbox_Ten_Ngan_Hang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_SoTien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_NoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_TaoMa_QR, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btn_XoaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        txt_SoTaiKhoan.setText("");
        cbox_Ten_Ngan_Hang.setSelectedItem("Vietcombank");
        txt_SoTien.setText("");
        lb_HienMa_QR.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txt_SoTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SoTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SoTienActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tao_Ma_QR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tao_Ma_QR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tao_Ma_QR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tao_Ma_QR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tao_Ma_QR().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_TaoMa_QR;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JComboBox<String> cbox_Ten_Ngan_Hang;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_HienMa_QR;
    private javax.swing.JTable tbl_Danh_SachMa_QR;
    private javax.swing.JTextField txt_NoiDung;
    private javax.swing.JTextField txt_SoTaiKhoan;
    private javax.swing.JTextField txt_SoTien;
    private javax.swing.JTextField txt_Ten_TaiKhoan;
    // End of variables declaration//GEN-END:variables
}
