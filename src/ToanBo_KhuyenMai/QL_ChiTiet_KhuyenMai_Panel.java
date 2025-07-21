/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ToanBo_KhuyenMai;

import javax.swing.table.DefaultTableModel;
import ToanBo_KhuyenMai.QL_KhuyenMai;
import ToanBo_KhuyenMai.KhuyenMai;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class QL_ChiTiet_KhuyenMai_Panel extends javax.swing.JPanel {

    int InDex = -1;
    DefaultTableModel TableMoDel;
    QL_KhuyenMai qlkm = new QL_KhuyenMai();

    /**
     * Creates new form QL_ChiTiet_KhuyenMai_Panel
     */
    public QL_ChiTiet_KhuyenMai_Panel() {
        initComponents();
        Initable();
        FillToTable();
    }

    public void Initable() {
        TableMoDel = new DefaultTableModel();
        String[] cols = {"Mã KM", "Tên KM", "Mô Tả", "Hình Thức", "Điểm Yêu Cầu ", "Giá Trị", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Ngày Trong Tháng", "Điều Kiện", "Trạng Thái"};
        TableMoDel.setColumnIdentifiers(cols);
        tbl_DanhSachKM.setModel(TableMoDel);
    }

    // Hiển Thị Tất Cả
    public void FillToTable() {
        TableMoDel.setRowCount(0);
        for (KhuyenMai km : qlkm.Get_All()) {
            TableMoDel.addRow(qlkm.GetRow(km));
        }
    }

    public void Loc_KM() {
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate bd = LocalDate.parse(txt_ThoiGian_BD.getText(), df);
            LocalDate kt = LocalDate.parse(txt_ThoiGian_KT.getText(), df);
            Date Ngay_BD_KM = Date.valueOf(bd);
            Date Ngay_KT_KM = Date.valueOf(kt);

            List<KhuyenMai> danhSachLoc = qlkm.Loc_KM(Ngay_BD_KM, Ngay_KT_KM);

            DefaultTableModel model = (DefaultTableModel) tbl_DanhSachKM.getModel();
            model.setRowCount(0);
            for (KhuyenMai km : danhSachLoc) {
                Object[] row = { /* như trên */};
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng ngày hoặc truy vấn: " + e.getMessage());
        }
    }

    public void TimKiem_KhuyenMai() {
        String TuKhoa_TK = txt_VuiLongNhap.getText().trim();
        if (TuKhoa_TK.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khoá tìm kiếm!");
            return;
        }
        if ((!rdo_TheoMa.isSelected() && !rdo_TheoTen.isSelected())) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Kiểu Tìm Kiếm Trước Khi Tìm Kiếm Khuyến Mãi.");
            return;
        }

        List<KhuyenMai> danhSachTim = new ArrayList<>();

        if (rdo_TheoMa.isSelected()) {
            danhSachTim = qlkm.TimKiem_TheoMa(TuKhoa_TK);
        } else if (rdo_TheoTen.isSelected()) {
            danhSachTim = qlkm.TimKiem_TheoTen(TuKhoa_TK);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn kiểu tìm kiếm (Mã hoặc Tên).");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tbl_DanhSachKM.getModel();
        model.setRowCount(0); // Xoá bảng cũ

        for (KhuyenMai km : danhSachTim) {
            model.addRow(qlkm.GetRow(km));
        }

        if (danhSachTim.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy khuyến mãi nào phù hợp.");
        }
    }

    public void ShowDetail() {
        InDex = tbl_DanhSachKM.getSelectedRow();
        if (InDex >= 0) {
            KhuyenMai km = qlkm.Get_All().get(InDex);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            String info = "🔔 Thông Tin Khuyến Mãi:\n"
                    + "───────────────────────────────\n"
                    + "🆔 Mã khuyến mãi: " + km.getMa_KM() + "\n"
                    + "📛 Tên khuyến mãi: " + km.getTen_KM() + "\n"
                    + "📦 Hình thức: " + km.getHinhThuc_KM() + "\n"
                    + "📝 Mô tả: " + km.getMoTa_KM() + "\n"
                    + "🏆 Điểm yêu cầu: " + km.getDiemYeuCau_KM() + "\n"
                    + "💸 Giá trị: " + km.getGiaTri_KM() + "\n"
                    + "📅 Ngày bắt đầu: " + km.getNgay_BD().toLocalDate().format(formatter) + "\n"
                    + "📅 Ngày kết thúc: " + km.getNgay_KT().toLocalDate().format(formatter) + "\n"
                    + "📆 Ngày trong tháng: " + km.getNgayTrongThang_KM() + "\n"
                    + "📋 Điều kiện: " + km.getDieuKien_KM() + "\n"
                    + "🚦 Trạng thái: " + (km.getTrangThai() ? "Đang hoạt động" : "Không hoạt động");

            JOptionPane.showMessageDialog(this, info, "Chi tiết khuyến mãi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng dữ liệu!", "Thông báo", JOptionPane.WARNING_MESSAGE);
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

        btg_KieuTK = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_VuiLongNhap = new javax.swing.JTextField();
        rdo_TheoTen = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        rdo_TheoMa = new javax.swing.JRadioButton();
        btn_TimKiem_KM = new javax.swing.JButton();
        btn_LamMoi_TimKiem = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_ThoiGian_BD = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_ThoiGian_KT = new javax.swing.JTextField();
        btn_Loc_KM = new javax.swing.JButton();
        btn_LamMoi_Loc = new javax.swing.JButton();
        Panel_DanhSachKM = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_DanhSachKM = new javax.swing.JTable();
        btn_Show_KM = new javax.swing.JButton();
        btn_Tao_KM = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nhập Thông Tin Tìm Kiếm Khuyến Mãi"));

        jLabel1.setText("Vui Lòng Nhập:");

        txt_VuiLongNhap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btg_KieuTK.add(rdo_TheoTen);
        rdo_TheoTen.setText("Theo Tên");

        jLabel2.setText("Kiểu Tìm Kiếm :");

        btg_KieuTK.add(rdo_TheoMa);
        rdo_TheoMa.setText("Theo Mã");

        btn_TimKiem_KM.setText("Tìm Kiếm");
        btn_TimKiem_KM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiem_KMActionPerformed(evt);
            }
        });

        btn_LamMoi_TimKiem.setText("Reset");
        btn_LamMoi_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoi_TimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_VuiLongNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdo_TheoTen)
                                .addGap(43, 43, 43)
                                .addComponent(rdo_TheoMa)))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_TimKiem_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(btn_LamMoi_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_VuiLongNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_TheoTen)
                    .addComponent(jLabel2)
                    .addComponent(rdo_TheoMa))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_LamMoi_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_TimKiem_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Lọc Khuyến Mãi"));

        jLabel3.setText("Thời Gian Bắt Đầu :");

        txt_ThoiGian_BD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Thời Gian Bắt Đầu :");

        txt_ThoiGian_KT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_Loc_KM.setText("Lọc Khuyến Mãi");
        btn_Loc_KM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Loc_KMActionPerformed(evt);
            }
        });

        btn_LamMoi_Loc.setText("Reset");
        btn_LamMoi_Loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoi_LocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txt_ThoiGian_BD, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txt_ThoiGian_KT)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btn_Loc_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(btn_LamMoi_Loc, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_ThoiGian_BD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_ThoiGian_KT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 12, Short.MAX_VALUE)
                        .addComponent(btn_LamMoi_Loc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_Loc_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        txt_ThoiGian_BD.getAccessibleContext().setAccessibleName("");

        Panel_DanhSachKM.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Bảng Danh Sách Khuyến Mãi"));

        tbl_DanhSachKM.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_DanhSachKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DanhSachKMMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_DanhSachKM);

        javax.swing.GroupLayout Panel_DanhSachKMLayout = new javax.swing.GroupLayout(Panel_DanhSachKM);
        Panel_DanhSachKM.setLayout(Panel_DanhSachKMLayout);
        Panel_DanhSachKMLayout.setHorizontalGroup(
            Panel_DanhSachKMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        Panel_DanhSachKMLayout.setVerticalGroup(
            Panel_DanhSachKMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
        );

        btn_Show_KM.setText("Chi Tiết Khuyến Mãi");
        btn_Show_KM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Show_KMActionPerformed(evt);
            }
        });

        btn_Tao_KM.setText("Tạo Khuyến Mãi");
        btn_Tao_KM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Tao_KMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Panel_DanhSachKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_Show_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Tao_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btn_Show_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Tao_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(Panel_DanhSachKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_Loc_KMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Loc_KMActionPerformed
        // TODO add your handling code here:
        Loc_KM();
    }//GEN-LAST:event_btn_Loc_KMActionPerformed

    private void btn_TimKiem_KMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiem_KMActionPerformed
        // TODO add your handling code here:
        TimKiem_KhuyenMai();
    }//GEN-LAST:event_btn_TimKiem_KMActionPerformed

    private void btn_LamMoi_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoi_TimKiemActionPerformed
        // TODO add your handling code here:
        txt_VuiLongNhap.setText("");
        btg_KieuTK.clearSelection();
    }//GEN-LAST:event_btn_LamMoi_TimKiemActionPerformed

    private void btn_LamMoi_LocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoi_LocActionPerformed
        // TODO add your handling code here:
        txt_ThoiGian_BD.setText("");
        txt_ThoiGian_KT.setText("");
    }//GEN-LAST:event_btn_LamMoi_LocActionPerformed

    private void tbl_DanhSachKMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DanhSachKMMouseClicked
        // TODO add your handling code here:
        ShowDetail();
    }//GEN-LAST:event_tbl_DanhSachKMMouseClicked

    private void btn_Show_KMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Show_KMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Show_KMActionPerformed

    private void btn_Tao_KMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Tao_KMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Tao_KMActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_DanhSachKM;
    private javax.swing.ButtonGroup btg_KieuTK;
    private javax.swing.JButton btn_LamMoi_Loc;
    private javax.swing.JButton btn_LamMoi_TimKiem;
    private javax.swing.JButton btn_Loc_KM;
    private javax.swing.JButton btn_Show_KM;
    private javax.swing.JButton btn_Tao_KM;
    private javax.swing.JButton btn_TimKiem_KM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdo_TheoMa;
    private javax.swing.JRadioButton rdo_TheoTen;
    private javax.swing.JTable tbl_DanhSachKM;
    private javax.swing.JTextField txt_ThoiGian_BD;
    private javax.swing.JTextField txt_ThoiGian_KT;
    private javax.swing.JTextField txt_VuiLongNhap;
    // End of variables declaration//GEN-END:variables
}
