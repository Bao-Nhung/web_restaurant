/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ToanBo_SanPham;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class QL_Tao_LoaiSanPham_JFrame extends javax.swing.JFrame {

    DefaultTableModel TableModel;
    QL_Tao_LoaiSanPham qltlsp = new QL_Tao_LoaiSanPham();
    int Index = -1;

    /**
     * Creates new form QL_Tao_LoáianPham
     */
    public QL_Tao_LoaiSanPham_JFrame() {
        initComponents();
        Initable_LSP();
        FillToTable_LSP();
        this.setLocationRelativeTo(this);
    }

    public void FillToTable_LSP() {
        TableModel.setRowCount(0);
        for (LoaiSanPham lsp : qltlsp.Get_All_LSP()) {
            TableModel.addRow(qltlsp.Get_Row_LSP(lsp));
        }
    }

    public void Initable_LSP() {
        TableModel = new DefaultTableModel();
        String[] cols = {"Mã Loại Sản Phẩm", "Tên Loại Sản Phẩm", "Mô Tả Loại Sản Phẩm"};
        TableModel.setColumnIdentifiers(cols);
        tbl_LoaiSanPham.setModel(TableModel);
    }

    public void LamMoi_LSP() {
        txt_Ma_LSP.setText("");
        txt_Ten_LSP.setText("");
        txt_MoTa_LSP.setText("");
        FillToTable_LSP();
    }

    public void Them_LSP() {
        String Ma_LSP = txt_Ma_LSP.getText().trim();
        String Ten_LSP = txt_Ten_LSP.getText().trim();
        String MoTa_LSP = txt_MoTa_LSP.getText().trim();

        // 🧠 Kiểm tra Mã LSP
        if (Ma_LSP.isEmpty()) {
            txt_Ma_LSP.setBackground(Color.RED);
            JOptionPane.showMessageDialog(this, "❌ Mã Loại Sản Phẩm Không Được Để Trống.");
            return;
        }
        if (Ma_LSP.length() < 3 || Ma_LSP.length() > 20) {
            txt_Ma_LSP.setBackground(Color.RED);
            JOptionPane.showMessageDialog(this, "❌ Mã Loại Sản Phẩm Phải Từ 3–20 Ký Tự.");
            return;
        }

        // 🔎 Kiểm tra trùng mã LSP
        List<LoaiSanPham> dsLSP = qltlsp.Get_All_LSP(); // ✅ Giả sử hàm lấy danh sách
        for (LoaiSanPham lsp : dsLSP) {
            if (lsp.getMa_LSP().equalsIgnoreCase(Ma_LSP)) {
                txt_Ma_LSP.setBackground(Color.RED);
                JOptionPane.showMessageDialog(this, "❌ Mã Loại Sản Phẩm Đã Tồn Tại.");
                return;
            }
        }
        txt_Ma_LSP.setBackground(Color.WHITE);

        // 🧠 Kiểm tra Tên LSP
        if (Ten_LSP.isEmpty() || Ten_LSP.length() < 5) {
            txt_Ten_LSP.setBackground(Color.RED);
            JOptionPane.showMessageDialog(this, "❌ Tên Loại Sản Phẩm Phải Có Ít Nhất 5 Ký Tự.");
            return;
        }
        txt_Ten_LSP.setBackground(Color.WHITE);

        // 🧠 Kiểm tra Mô Tả
        if (MoTa_LSP.isEmpty() || MoTa_LSP.length() < 5) {
            txt_MoTa_LSP.setBackground(Color.RED);
            JOptionPane.showMessageDialog(this, "❌ Mô Tả Phải Có Ít Nhất 5 Ký Tự.");
            return;
        }
        txt_MoTa_LSP.setBackground(Color.WHITE);

        // ✅ Nếu tất cả ok ➜ thêm
        LoaiSanPham lsp = new LoaiSanPham(Ma_LSP, Ten_LSP, MoTa_LSP);
        int ReSult = qltlsp.Them_LSP(lsp);

        if (ReSult == 1) {
            JOptionPane.showMessageDialog(this, "✅ Thêm Loại Sản Phẩm Thành Công.");
        } else {
            JOptionPane.showMessageDialog(this, "❌ Thêm Thất Bại, Vui Lòng Kiểm Tra Lại.");
        }
    }

    public void Xoa_LSP() {
        Index = tbl_LoaiSanPham.getSelectedRow();
        if (Index >= 0) {
            String TheoMa = qltlsp.Get_All_LSP().get(Index).getMa_LSP();
            int Choice = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Xoá Loại Sản Phẩm:", "Xác Nhập Xoá Loại Sản Phẩm ?", JOptionPane.YES_NO_OPTION);
            if (Choice == JOptionPane.YES_OPTION) {
                int ReSult = qltlsp.Xoa_LSP(TheoMa);
                if (ReSult == 1) {
                    JOptionPane.showMessageDialog(this, "Xoá Loại Sản Phẩm Thành Công.");
                } else {
                    JOptionPane.showMessageDialog(this, "Xoá Loại Sản Phẩm Thất Bại.");
                    return;
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Dữ Liệu Trong Bảng Danh Sách Loại Sản Phẩm.");
            return;
        }
    }

    public void Sua_LSP() {
        Index = tbl_LoaiSanPham.getSelectedRow();
        if (Index >= 0) {
            String Ma_LSP = txt_Ma_LSP.getText().trim();
            String Ten_LSP = txt_Ten_LSP.getText().trim();
            String MoTa_LSP = txt_MoTa_LSP.getText().trim();

            // 🧠 Kiểm tra Mã LSP
            if (Ma_LSP.isEmpty()) {
                txt_Ma_LSP.setBackground(Color.RED);
                JOptionPane.showMessageDialog(this, "❌ Mã Loại Sản Phẩm Không Được Để Trống.");
                return;
            }
            if (Ma_LSP.length() < 3 || Ma_LSP.length() > 20) {
                txt_Ma_LSP.setBackground(Color.RED);
                JOptionPane.showMessageDialog(this, "❌ Mã Loại Sản Phẩm Phải Từ 3–20 Ký Tự.");
                return;
            }

            txt_Ma_LSP.setBackground(Color.WHITE);

            // 🧠 Kiểm tra Tên LSP
            if (Ten_LSP.isEmpty() || Ten_LSP.length() < 5) {
                txt_Ten_LSP.setBackground(Color.RED);
                JOptionPane.showMessageDialog(this, "❌ Tên Loại Sản Phẩm Phải Có Ít Nhất 5 Ký Tự.");
                return;
            }
            txt_Ten_LSP.setBackground(Color.WHITE);

            // 🧠 Kiểm tra Mô Tả
            if (MoTa_LSP.isEmpty() || MoTa_LSP.length() < 5) {
                txt_MoTa_LSP.setBackground(Color.RED);
                JOptionPane.showMessageDialog(this, "❌ Mô Tả Phải Có Ít Nhất 5 Ký Tự.");
                return;
            }
            txt_MoTa_LSP.setBackground(Color.WHITE);

            // ✅ Nếu tất cả ok ➜ thêm
            LoaiSanPham lsp = new LoaiSanPham(Ma_LSP, Ten_LSP, MoTa_LSP);
            String TheoMa = qltlsp.Get_All_LSP().get(Index).getMa_LSP();
            
            int ReSult = qltlsp.Sua_LSP(lsp, TheoMa);
            if (ReSult == 1) {
                JOptionPane.showMessageDialog(this, "Sửa Loại Sản Phẩm Thành Công.");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa Loại Sản Phẩm Thất Bại.");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Dữ Liệu Trong Bảng Danh Sách Loại Sản Phẩm.");
            return;
        }
    }
    
    public void ShowDetail(){
        Index = tbl_LoaiSanPham.getSelectedRow();
        if (Index >= 0) {
            LoaiSanPham lsp = qltlsp.Get_All_LSP().get(Index);
            txt_Ma_LSP.setText(lsp.getMa_LSP());
            txt_Ten_LSP.setText(lsp.getTen_LSP());
            txt_MoTa_LSP.setText(lsp.getMoTa_LSP());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nhapThongTin_Panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_Ma_LSP = new javax.swing.JTextField();
        txt_Ten_LSP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_MoTa_LSP = new javax.swing.JTextArea();
        ChucNangChinh = new javax.swing.JPanel();
        btn_LamMoi = new javax.swing.JButton();
        btn_Them = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        DanhSach_LSP = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_LoaiSanPham = new javax.swing.JTable();
        btn_Dong = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nhapThongTin_Panel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ô Nhập Thông Tin Loại Sản Phẩm."));

        jLabel1.setText("Mã Loại Sản Phẩm :");

        jLabel2.setText("Tên Loại San Phẩm:");

        jLabel3.setText("Mô Tả Loại Sản Phẩm: ");

        txt_MoTa_LSP.setColumns(20);
        txt_MoTa_LSP.setRows(5);
        jScrollPane1.setViewportView(txt_MoTa_LSP);

        javax.swing.GroupLayout nhapThongTin_PanelLayout = new javax.swing.GroupLayout(nhapThongTin_Panel);
        nhapThongTin_Panel.setLayout(nhapThongTin_PanelLayout);
        nhapThongTin_PanelLayout.setHorizontalGroup(
            nhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhapThongTin_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Ma_LSP)
                    .addComponent(txt_Ten_LSP, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        nhapThongTin_PanelLayout.setVerticalGroup(
            nhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhapThongTin_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_Ma_LSP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_Ten_LSP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ChucNangChinh.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ô Chức Năng Chính"));

        btn_LamMoi.setText("Làm Mới LSP");
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });

        btn_Them.setText("Thêm LSP");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        btn_Sua.setText("Sửa LSP");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

        btn_Xoa.setText("Xoá LSP");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ChucNangChinhLayout = new javax.swing.GroupLayout(ChucNangChinh);
        ChucNangChinh.setLayout(ChucNangChinhLayout);
        ChucNangChinhLayout.setHorizontalGroup(
            ChucNangChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChucNangChinhLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(ChucNangChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        ChucNangChinhLayout.setVerticalGroup(
            ChucNangChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChucNangChinhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        DanhSach_LSP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Bảng Danh Sách Loại Sản Phẩm"));

        tbl_LoaiSanPham.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_LoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_LoaiSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_LoaiSanPham);

        javax.swing.GroupLayout DanhSach_LSPLayout = new javax.swing.GroupLayout(DanhSach_LSP);
        DanhSach_LSP.setLayout(DanhSach_LSPLayout);
        DanhSach_LSPLayout.setHorizontalGroup(
            DanhSach_LSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        DanhSach_LSPLayout.setVerticalGroup(
            DanhSach_LSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btn_Dong.setText("Đóng Trang");
        btn_Dong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DanhSach_LSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nhapThongTin_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ChucNangChinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btn_Dong, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(ChucNangChinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nhapThongTin_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btn_Dong, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DanhSach_LSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_LoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_LoaiSanPhamMouseClicked
        // TODO add your handling code here:
        ShowDetail();
    }//GEN-LAST:event_tbl_LoaiSanPhamMouseClicked

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        // TODO add your handling code here:
        LamMoi_LSP();
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        // TODO add your handling code here:
        Them_LSP();
        FillToTable_LSP();
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        // TODO add your handling code here:
        Sua_LSP();
        FillToTable_LSP();
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        // TODO add your handling code here:
        Xoa_LSP();
        FillToTable_LSP();
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_DongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DongActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btn_DongActionPerformed

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
            java.util.logging.Logger.getLogger(QL_Tao_LoaiSanPham_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QL_Tao_LoaiSanPham_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QL_Tao_LoaiSanPham_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QL_Tao_LoaiSanPham_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QL_Tao_LoaiSanPham_JFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ChucNangChinh;
    private javax.swing.JPanel DanhSach_LSP;
    private javax.swing.JButton btn_Dong;
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel nhapThongTin_Panel;
    private javax.swing.JTable tbl_LoaiSanPham;
    private javax.swing.JTextField txt_Ma_LSP;
    private javax.swing.JTextArea txt_MoTa_LSP;
    private javax.swing.JTextField txt_Ten_LSP;
    // End of variables declaration//GEN-END:variables
}
