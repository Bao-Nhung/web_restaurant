/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ToanBo_KhachHang;

import ToanBo_KhachHang.KhachHang;
import ToanBo_KhachHang.QL_KhachHang;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class QL_KhachHang_Panel extends javax.swing.JPanel {

    DefaultTableModel TableModel;
    int Index = -1;
    QL_KhachHang qlkh = new QL_KhachHang();

    /**
     * Creates new form QL_KhachHang_Panel
     */
    public QL_KhachHang_Panel() {
        initComponents();
        Initable();
        FillToTable();
        
        LocalDate ngayHienTai = LocalDate.now();
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Hiển thị vào textField
        txt_NgayTaoKH.setText(ngayHienTai.format(dinhDang));
    }

    public void Initable() {
        TableModel = new DefaultTableModel();
        String[] cols = {"Mã Khách Hàng", "Tên KH", "SĐT", "Email", "Ngày Tạo", "Điểm Tích Luỹ", "Loại Khách Hàng"};
        TableModel.setColumnIdentifiers(cols);
        tbl_KhachHang.setModel(TableModel);
        
    }

    // Hiển Thị Tất Cả
    public void FillToTable() {
        TableModel.setRowCount(0);
        for (KhachHang kh : qlkh.Get_All()) {
            TableModel.addRow(qlkh.GetRow(kh));
        }
    }

    public void LamMoi() {
        txt_Ma_KH.setText("");
        txt_Ten_KH.setText("");
        LocalDate ngayHienTai = LocalDate.now();
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Hiển thị vào textField
        txt_NgayTaoKH.setText(ngayHienTai.format(dinhDang));
        txt_DiemTichLuy.setText("");
        txt_SDT_KH.setText("");
        btg_LoaiKH.clearSelection();
        FillToTable();
    }

    public void Them_KH() {
        String Ma_KH = txt_Ma_KH.getText();
        String Ten_KH = txt_Ten_KH.getText();
        String SDT_KH = txt_SDT_KH.getText();

        // Xử lý ngày tạo khách hàng với kiểm tra lỗi
        Date NgayTao_KH = null;
        try {
            String txtNgayTao = txt_NgayTaoKH.getText();
            if (txtNgayTao.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày tạo khách hàng.");
                return;
            }
            DateTimeFormatter DinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(txtNgayTao, DinhDang);
            NgayTao_KH = Date.valueOf(localDate);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi xử lý ngày đăng ký: " + e.getMessage());
            return;
        }

        // Xử lý các dữ liệu khác
        int DiemTichLuy;
        try {
            DiemTichLuy = Integer.parseInt(txt_DiemTichLuy.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Điểm tích lũy phải là số nguyên.");
            return;
        }

        String LoaiKhachHang = rdo_KhachLuxury.isSelected() ? "Khách Luxury"
                : rdo_KhachVIP.isSelected() ? "Khách VIP"
                : "Khách Thường";

        String Email = txt_Email_KH.getText();

        // Tạo đối tượng khách hàng
        KhachHang kh = new KhachHang(Ma_KH, Ten_KH, SDT_KH, Email, NgayTao_KH, LoaiKhachHang, DiemTichLuy);

        // Thêm khách hàng vào hệ thống
        int Result = qlkh.Them_KH(kh);
        if (Result == 1) {
            JOptionPane.showMessageDialog(this, "Thêm Dữ Liệu Khách Hàng Thành Công.");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm Dữ Liệu Khách Hàng Thất Bại.\nVui Lòng Kiểm Tra Dữ Liệu Bạn Nhập.");
        }
    }

    public void Sua_KH() {
        Index = tbl_KhachHang.getSelectedRow();
        if (Index >= 0) {
            String Ma_KH = txt_Ma_KH.getText();
            String Ten_KH = txt_Ten_KH.getText();
            String SDT_KH = txt_SDT_KH.getText();

            // Xử lý ngày tạo khách hàng với kiểm tra lỗi
            Date NgayTao_KH = null;
            try {
                String txtNgayTao = txt_NgayTaoKH.getText();
                if (txtNgayTao.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày tạo khách hàng.");
                    return;
                }
                DateTimeFormatter DinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = LocalDate.parse(txtNgayTao, DinhDang);
                NgayTao_KH = Date.valueOf(localDate);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi xử lý ngày đăng ký: " + e.getMessage());
                return;
            }

            // Xử lý các dữ liệu khác
            int DiemTichLuy;
            try {
                DiemTichLuy = Integer.parseInt(txt_DiemTichLuy.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Điểm tích lũy phải là số nguyên.");
                return;
            }

            String LoaiKhachHang = rdo_KhachLuxury.isSelected() ? "Khách Luxury"
                    : rdo_KhachVIP.isSelected() ? "Khách VIP"
                    : "Khách Thường";

            String Email = txt_Email_KH.getText();

            // Tạo đối tượng khách hàng
            KhachHang kh = new KhachHang(Ma_KH, Ten_KH, SDT_KH, Email, NgayTao_KH, LoaiKhachHang, DiemTichLuy);

            String TheoMa = qlkh.Get_All().get(Index).getMa_KH();
            int ReSult = qlkh.Sua_KH(kh, TheoMa);
            if (ReSult == 1) {
                JOptionPane.showMessageDialog(this, "Sửa Khách Hàng Có Mã Cũ Là: " + TheoMa
                        + "\n Mã Khách Hàng Mới Là: " + Ma_KH
                        + "\n Tên Khách Hàng: " + Ten_KH
                        + "\n Số Điện Thoại: " + SDT_KH
                        + "\n Thành Công.");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa Dữ Liệu Khách Hàng Thất Bai.");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Dữ Liệu Khách Hàng Trong Bảng Để Cập Nhập.");
            return;
        }
    }

    public void Xoa_KH() {
        Index = tbl_KhachHang.getSelectedRow();
        if (Index >= 0) {
            String TheoMa = qlkh.Get_All().get(Index).getMa_KH();
            String Ten = qlkh.Get_All().get(Index).getTen_KH();
            String SDT = qlkh.Get_All().get(Index).getSDT_KH();
            int Choice = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Xoá Tài Khoản:"
                    + "\n Mã Khách Hàng :  " + TheoMa
                    + "\n Tên Khách Hàng: " + Ten
                    + "\n Số Điện Thoại: " + SDT, "Xác Nhận Xoá Tài Khoản", JOptionPane.YES_NO_OPTION);
            if (Choice == JOptionPane.YES_OPTION) {

                int Result = qlkh.Xoa_KH(TheoMa);
                if (Result == 1) {
                    JOptionPane.showMessageDialog(this, "Xoá Dữ Liệu Khách Hàng Thành Công.");
                } else {
                    JOptionPane.showMessageDialog(this, "Xoá Khách Hàng Có Mã : " + TheoMa 
                                                        + "\n Tên Khách Hàng Là: " + Ten 
                                                        + "\n Thất Bại."); 
                    return;
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Dữ Liệu Khách Hàng Trong Bảng Để Xoá.");
            return;
        }
    }

    public void ShowDetail() {
    Index = tbl_KhachHang.getSelectedRow();
    if (Index >= 0) {
        KhachHang kh = qlkh.Get_All().get(Index);

        // Hiển thị thông tin lên các textfield
        txt_Ma_KH.setText(kh.getMa_KH());
        txt_Ten_KH.setText(kh.getTen_KH());
        txt_SDT_KH.setText(kh.getSDT_KH());
        txt_Email_KH.setText(kh.getEmail_KH());

        // Định dạng lại ngày để hiển thị
        DateTimeFormatter DinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = kh.getNgayTao_KH().toLocalDate();
        txt_NgayTaoKH.setText(DinhDang.format(localDate));

        // Gán điểm tích lũy
        txt_DiemTichLuy.setText(String.valueOf(kh.getDiemTichLuy()));

        // Set loại khách hàng theo radio button
        switch (kh.getLoai_KH()) {
            case "Khách Luxury":
                rdo_KhachLuxury.setSelected(true);
                break;
            case "Khách VIP":
                rdo_KhachVIP.setSelected(true);
                break;
            default:
                rdo_KhachThuong.setSelected(true);
                break;
        }
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng khách hàng trong bảng!");
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

        btg_LoaiKH = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        btn_LamMoi = new javax.swing.JButton();
        btn_ThemKH = new javax.swing.JButton();
        btn_SuaKH = new javax.swing.JButton();
        btn_XoaKH = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_Ma_KH = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_Ten_KH = new javax.swing.JTextField();
        txt_SDT_KH = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_NgayTaoKH = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        rdo_KhachThuong = new javax.swing.JRadioButton();
        rdo_KhachVIP = new javax.swing.JRadioButton();
        rdo_KhachLuxury = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        txt_DiemTichLuy = new javax.swing.JTextField();
        txt_Email_KH = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        rdo_VangLai = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_KhachHang = new javax.swing.JTable();

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Chức Năng Chính"));

        btn_LamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/LamMoi_KhachHang.png"))); // NOI18N
        btn_LamMoi.setText("Làm Mới");
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });

        btn_ThemKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Them_KhachHang.png"))); // NOI18N
        btn_ThemKH.setText("Thêm");
        btn_ThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemKHActionPerformed(evt);
            }
        });

        btn_SuaKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Sua_KhachHang.png"))); // NOI18N
        btn_SuaKH.setText("Sửa");
        btn_SuaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaKHActionPerformed(evt);
            }
        });

        btn_XoaKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Xoa_KhachHang.png"))); // NOI18N
        btn_XoaKH.setText("Xoá");
        btn_XoaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_XoaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_SuaKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_ThemKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_ThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btn_SuaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_XoaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nhập Thông Tin Khách Hàng"));

        jLabel1.setText("Mã KH :");

        txt_Ma_KH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Ma_KHActionPerformed(evt);
            }
        });

        jLabel2.setText("Tên KH:");

        jLabel3.setText("SDT :");

        jLabel4.setText("Ngày Tạo :");

        jLabel5.setText("Loại KH:");

        btg_LoaiKH.add(rdo_KhachThuong);
        rdo_KhachThuong.setText("Khách Thường");
        rdo_KhachThuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_KhachThuongActionPerformed(evt);
            }
        });

        btg_LoaiKH.add(rdo_KhachVIP);
        rdo_KhachVIP.setText("Khách VIP");

        btg_LoaiKH.add(rdo_KhachLuxury);
        rdo_KhachLuxury.setText("Khách Luxury");
        rdo_KhachLuxury.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_KhachLuxuryActionPerformed(evt);
            }
        });

        jLabel6.setText("Điểm Tích Luỹ :");

        jLabel7.setText("Email :");

        btg_LoaiKH.add(rdo_VangLai);
        rdo_VangLai.setText("Khách Vãng Lai");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_Email_KH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_Ten_KH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_SDT_KH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_Ma_KH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(87, 87, 87)))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_NgayTaoKH, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(txt_DiemTichLuy)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdo_VangLai)
                        .addGap(28, 28, 28)
                        .addComponent(rdo_KhachThuong)
                        .addGap(18, 18, 18)
                        .addComponent(rdo_KhachVIP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(rdo_KhachLuxury)))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_NgayTaoKH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_DiemTichLuy, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(217, 217, 217))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_Ma_KH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addComponent(txt_Ten_KH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_SDT_KH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Email_KH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdo_VangLai)
                            .addComponent(rdo_KhachThuong)
                            .addComponent(rdo_KhachVIP)
                            .addComponent(rdo_KhachLuxury))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Bảng Danh Sách Khách Hàng"));

        tbl_KhachHang.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_KhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_KhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_KhachHang);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdo_KhachThuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_KhachThuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_KhachThuongActionPerformed

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        // TODO add your handling code here:
        LamMoi();
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void btn_ThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemKHActionPerformed
        // TODO add your handling code here:
        Them_KH();
        FillToTable();
    }//GEN-LAST:event_btn_ThemKHActionPerformed

    private void btn_SuaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaKHActionPerformed
        // TODO add your handling code here:
        Sua_KH();
        FillToTable();
    }//GEN-LAST:event_btn_SuaKHActionPerformed

    private void btn_XoaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaKHActionPerformed
        // TODO add your handling code here:
        Xoa_KH();
        FillToTable();
    }//GEN-LAST:event_btn_XoaKHActionPerformed

    private void tbl_KhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_KhachHangMouseClicked
        // TODO add your handling code here:
        ShowDetail();
    }//GEN-LAST:event_tbl_KhachHangMouseClicked

    private void txt_Ma_KHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Ma_KHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Ma_KHActionPerformed

    private void rdo_KhachLuxuryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_KhachLuxuryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_KhachLuxuryActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btg_LoaiKH;
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JButton btn_SuaKH;
    private javax.swing.JButton btn_ThemKH;
    private javax.swing.JButton btn_XoaKH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdo_KhachLuxury;
    private javax.swing.JRadioButton rdo_KhachThuong;
    private javax.swing.JRadioButton rdo_KhachVIP;
    private javax.swing.JRadioButton rdo_VangLai;
    private javax.swing.JTable tbl_KhachHang;
    private javax.swing.JTextField txt_DiemTichLuy;
    private javax.swing.JTextField txt_Email_KH;
    private javax.swing.JTextField txt_Ma_KH;
    private javax.swing.JTextField txt_NgayTaoKH;
    private javax.swing.JTextField txt_SDT_KH;
    private javax.swing.JTextField txt_Ten_KH;
    // End of variables declaration//GEN-END:variables
}
