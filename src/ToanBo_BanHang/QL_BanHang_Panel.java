/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ToanBo_BanHang;

import ToanBo_KhachHang.KhachHang_4_O;
import ToanBo_SanPham.SanPham;
import javax.swing.table.DefaultTableModel;
import ToanBo_SanPham.QL_Tao_SanPham;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import ToanBo_KhachHang.QL_KhachHang;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author ADMIN
 */
public class QL_BanHang_Panel extends javax.swing.JPanel {

    DefaultTableModel TableModel;
    QL_Tao_SanPham QL_Tao_SP = new QL_Tao_SanPham();
    QL_Tao_HoaDon QL_Tao_HD = new QL_Tao_HoaDon();
    QL_KhachHang QL_KH = new QL_KhachHang();

    /**
     * Creates new form Menu
     */
    public QL_BanHang_Panel() {
        initComponents();
        Initable_SP();
        FillToTable_SP();
        Initable_HD();
        FillToTable_HD();
        Initable_SP_DUOCCHON();
        LocalDateTime thoiGianHienTai = LocalDateTime.now();
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        txt_ThoiGianTao.setText(thoiGianHienTai.format(dinhDang));
        txt_ThoiGianTao.setEnabled(false);

        // Phần Áp Dụng Khuyến Mãi
        tbtn_ApDung.addActionListener(e -> {
            if (tbtn_ApDung.isSelected()) {
                tbtn_HuyBo.setSelected(false);
                // Thực thi logic áp dụng
            }
        });

        tbtn_HuyBo.addActionListener(e -> {
            if (tbtn_HuyBo.isSelected()) {
                tbtn_ApDung.setSelected(false);
                // Thực thi logic huỷ bỏ
            }
        });

        // Phần Có Muốn Tạo Hoá Đơn Hay Không
        tbtn_Tao_HD.addActionListener(e -> {
            if (tbtn_Tao_HD.isSelected()) {
                tbtn_HuyTao.setSelected(false);
                // Thực thi logic áp dụng
            }
        });

        tbtn_HuyTao.addActionListener(e -> {
            if (tbtn_HuyTao.isSelected()) {
                tbtn_Tao_HD.setSelected(false);
                // Thực thi logic huỷ bỏ
            }
        });

        // Phần Khách Hàng
        txt_Ten_KH.setEditable(false);
        // Phần Số Tiền Cần Trả Lại Cho Khách
        txt_SoTien_CanTraLai.setEditable(false);
        // Tìm Kiếm Khách Hàng
        TK();
        // Chỉnh Ảnh TRong Bảng Cho Đẹp
        Chinh_Bang();
        tbl_DanhSach_SP.setBackground(new Color(245, 245, 245));
        tbl_DanhSach_SP.setForeground(Color.BLACK);
        tbl_DanhSach_SP.setGridColor(new Color(200, 200, 200));
        tbl_DanhSach_SP.setSelectionBackground(new Color(30, 144, 255));
        tbl_DanhSach_SP.setSelectionForeground(Color.WHITE);
        tbl_DanhSach_SP.setRowHeight(30);

        JTableHeader header = tbl_DanhSach_SP.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(230, 230, 230));
        header.setForeground(Color.DARK_GRAY);
    }

    public void Chinh_Bang() {
        TableColumn imageColumn = tbl_DanhSach_SP.getColumnModel().getColumn(2); // Index cột ảnh

        imageColumn.setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                JLabel label = new JLabel();
                if (value instanceof ImageIcon) {
                    label.setIcon((ImageIcon) value);
                } else {
                    label.setText("Không có ảnh");
                }
                label.setHorizontalAlignment(JLabel.CENTER);
                return label;
            }
        });
    }

    public void Initable_HD() {
        TableModel = new DefaultTableModel();
        String[] cols = {"Mã HD", "Mã TK", "Ngày Tạo", "Tổng Tiền", "Hình Thức TT", "Trạng Thái", "Mã KM", "SOTIENCANTRA", "Mã KH", "Tích Điểm"};
        TableModel.setColumnIdentifiers(cols);
        tbl_BangHoaDon.setModel(TableModel);
    }

    public void FillToTable_HD() {
        TableModel.setRowCount(0);
        for (HoaDon hd : QL_Tao_HD.Get_All_HoaDon()) {
            TableModel.addRow(QL_Tao_HD.Get_Row_HD(hd));
        }
    }

    public void Initable_SP() {
        TableModel = new DefaultTableModel();
        String[] cols = {"Mã SP", "Tên SP", "Mô Tả SP", "Số Lượng", "Đơn Giá", "Mã Loại SP", "Hình Ảnh"};
        TableModel.setColumnIdentifiers(cols);
        tbl_DanhSach_SP.setModel(TableModel);
    }

    public void Initable_SP_DUOCCHON() {
        TableModel = new DefaultTableModel();
        String[] cols = {"Mã SP", "Tên SP", "Số Lượng", "Đơn Giá", "Hình Ảnh"};
        TableModel.setColumnIdentifiers(cols);
        tbl_Bang_SP_DaChon.setModel(TableModel);
    }

    // Hiển Thị Tất Cả
    public void FillToTable_SP() {
        TableModel.setRowCount(0);
        for (SanPham sp : QL_Tao_SP.GetAll_SP()) {
            TableModel.addRow(QL_Tao_SP.GetRow_SP_Anh(sp));
        }
    }

    public void Reset() {
        LocalDateTime thoiGianHienTai = LocalDateTime.now();
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        txt_ThoiGianTao.setText(thoiGianHienTai.format(dinhDang));
        txt_Ma_TK.setText("");
        btg_HinhThuc_TT.clearSelection();
        txt_MaHD.setText("");
        txt_Ma_KH.setText("");
        txt_Ten_KH.setText("");
        lb_ThanhTien.setText("");
        lb_HienDiemTL.setText("");
        lb_HienTongTien_ChuaGiamGia.setText("");
        lb_Hien_TienGiamGia.setText("");
        lb_SoDiemDuocCong.setText("");
        txt_SoTienKhachTra.setText("");
        txt_SoTien_CanTraLai.setText("");
        tbtn_ApDung.setSelected(false);
        tbtn_HuyBo.setSelected(false);
        tbtn_SuDungDiem.setSelected(false);
        tbtn_Tao_HD.setSelected(false);
        tbtn_HuyTao.setSelected(false);

        FillToTable_HD();
        FillToTable_SP();
    }

    public void Tao_HD() {
        String Ma_HD = txt_MaHD.getText();
        if (Ma_HD.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã Hoá Đơn Không Được Để Trống.");
            return;
        }

        String Ma_TK = txt_Ma_TK.getText();
        if (Ma_TK.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã Tài Khoản Không Được Để Trống.");
            return;
        }

        String Ma_KH = txt_Ma_KH.getText();
        if (Ma_KH.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã Khách Hàng Không Được Để Trống.");
            return;
        }

        String HinhThuc_TT = rdo_TienMat.isSelected() ? "Tiền Mặt" : "Chuyển Khoản";
        String TrangThai_HD = "Chưa Thanh Toán";
        String Ma_KM = txt_KhuyenMai.getText();

        int So_Diem_Duoc_Cong = 0;
        try {
            String diemCongStr = lb_SoDiemDuocCong.getText();
            So_Diem_Duoc_Cong = diemCongStr.isEmpty() ? 0 : Integer.parseInt(diemCongStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số điểm được cộng không hợp lệ.");
            return;
        }

        float Thanh_Tien = 0f;
        try {
            String thanhTienStr = lb_ThanhTien.getText();
            Thanh_Tien = thanhTienStr.isEmpty() ? 0f : Float.parseFloat(thanhTienStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Thành tiền không hợp lệ.");
            return;
        }

        float SoTien_KhachTra = 0f;
        try {
            String tienKhachTraStr = txt_SoTienKhachTra.getText();
            SoTien_KhachTra = tienKhachTraStr.isEmpty() ? 0f : Float.parseFloat(tienKhachTraStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số tiền khách trả không hợp lệ.");
            return;
        }

        String input = txt_ThoiGianTao.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date utilDate = null;
        try {
            utilDate = sdf.parse(input);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Thời gian tạo hoá đơn không đúng định dạng.");
            Logger.getLogger(QL_BanHang_Panel.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        // ✨ Chuyển sang java.sql.Date nếu cần cho cơ sở dữ liệu
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        // 📦 Tạo đối tượng hóa đơn
        HoaDon hd = new HoaDon(
                Ma_HD, Ma_TK, sqlDate, Thanh_Tien,
                HinhThuc_TT, TrangThai_HD, Ma_KM,
                SoTien_KhachTra, Ma_KH, So_Diem_Duoc_Cong
        );

        // 📤 Gọi DAO lưu hóa đơn
        int ReSult = QL_Tao_HD.Tao_HD(hd);
        if (ReSult == 1) {
            FillToTable_HD();
            JOptionPane.showMessageDialog(this, "Tạo Hoá Đơn Thành Công.");
        } else {
            JOptionPane.showMessageDialog(this, "Tạo Hoá Đơn Thất Bại.");
        }
    }

    public void TK() {
        txt_Ma_KH.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String tuKhoa = txt_Ma_KH.getText().trim();
                List<KhachHang_4_O> dsKH = QL_KH.timKiemTheoTaiKhoan(tuKhoa);
                if (dsKH.isEmpty()) {
                    return;
                }

                String[] columnNames = {"Mã KH", "Tên KH", "SĐT", "Điểm TL"};
                Object[][] data = new Object[dsKH.size()][columnNames.length];

                for (int i = 0; i < dsKH.size(); i++) {
                    KhachHang_4_O kh = dsKH.get(i);
                    data[i][0] = kh.getMa_KH();
                    data[i][1] = kh.getTen_KH();
                    data[i][2] = kh.getSDT();
                    data[i][3] = kh.getDiemTichLuy();
                }

                JTable table = new JTable(data, columnNames);
                JScrollPane scrollPane = new JScrollPane(table);
                JPopupMenu popup = new JPopupMenu();
                popup.add(scrollPane);
                popup.show(txt_Ma_KH, 0, txt_Ma_KH.getHeight());

                table.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow >= 0) {
                            String maKH = table.getValueAt(selectedRow, 0).toString();
                            KhachHang_4_O kh = QL_KH.timKHTheoMa(maKH);
                            txt_Ma_KH.setText(kh.getMa_KH());
                            txt_Ten_KH.setText(kh.getTen_KH());
                            lb_HienDiemTL.setText(String.valueOf(kh.getDiemTichLuy()));
                            popup.setVisible(false);
                        }
                    }
                });
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btg_HinhThuc_TT = new javax.swing.ButtonGroup();
        ThongTin_HD = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_Ma_KH = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_Ten_KH = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lb_HienDiemTL = new javax.swing.JLabel();
        tbtn_SuDungDiem = new javax.swing.JToggleButton();
        jLabel6 = new javax.swing.JLabel();
        txt_KhuyenMai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lb_HienTongTien_ChuaGiamGia = new javax.swing.JLabel();
        lb_Hien_TienGiamGia = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lb_SoDiemDuocCong = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lb_ThanhTien = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_SoTienKhachTra = new javax.swing.JTextField();
        txt_SoTien_CanTraLai = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tbtn_Tao_HD = new javax.swing.JToggleButton();
        tbtn_HuyTao = new javax.swing.JToggleButton();
        jButton3 = new javax.swing.JButton();
        tbtn_ApDung = new javax.swing.JToggleButton();
        tbtn_HuyBo = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_MaHD = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_DanhSach_SP = new javax.swing.JTable();
        txt_ThoiGianTao = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_BangHoaDon = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_Bang_SP_DaChon = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txt_Ma_TK = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        rdo_TienMat = new javax.swing.JRadioButton();
        rdo_ChuyenKhoan = new javax.swing.JRadioButton();

        ThongTin_HD.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nhập Thông Tin Hoá Đơn"));

        jLabel2.setText("Mã KH:");

        txt_Ma_KH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Ma_KHActionPerformed(evt);
            }
        });

        jLabel3.setText("Tên KH:");

        jLabel5.setText("Số Điểm Tích Luỹ:");

        lb_HienDiemTL.setText("Null");

        tbtn_SuDungDiem.setText("Sử Dụng Điểm");

        jLabel6.setText("Khuyến Mãi:");

        jLabel7.setText("Tổng Cộng :");

        lb_HienTongTien_ChuaGiamGia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        lb_Hien_TienGiamGia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel10.setText("Giảm Giá :");

        jLabel11.setText("Điểm Cộng:");

        lb_SoDiemDuocCong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel13.setText("Thành Tiền :");

        lb_ThanhTien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel15.setText("Khách Trả :");

        txt_SoTien_CanTraLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SoTien_CanTraLaiActionPerformed(evt);
            }
        });

        jLabel16.setText("Tiền Trả Lại :");

        tbtn_Tao_HD.setText("Tạo Hoá Đơn");
        tbtn_Tao_HD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtn_Tao_HDActionPerformed(evt);
            }
        });

        tbtn_HuyTao.setText("Huỷ Tạo");

        jButton3.setText("Thanh Toán");

        tbtn_ApDung.setText("Áp Dụng");

        tbtn_HuyBo.setText("Huỷ Bỏ");

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ThongTin_HDLayout = new javax.swing.GroupLayout(ThongTin_HD);
        ThongTin_HD.setLayout(ThongTin_HDLayout);
        ThongTin_HDLayout.setHorizontalGroup(
            ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTin_HDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTin_HDLayout.createSequentialGroup()
                        .addGroup(ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_Ma_KH, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(txt_Ten_KH)))
                    .addGroup(ThongTin_HDLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(lb_HienDiemTL, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tbtn_SuDungDiem))
                    .addGroup(ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ThongTin_HDLayout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_KhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ThongTin_HDLayout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addGroup(ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThongTin_HDLayout.createSequentialGroup()
                                    .addComponent(tbtn_Tao_HD, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tbtn_HuyTao, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(42, 42, 42))
                                .addGroup(ThongTin_HDLayout.createSequentialGroup()
                                    .addGroup(ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ThongTin_HDLayout.createSequentialGroup()
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(lb_Hien_TienGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ThongTin_HDLayout.createSequentialGroup()
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(lb_HienTongTien_ChuaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(ThongTin_HDLayout.createSequentialGroup()
                                            .addGroup(ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txt_SoTienKhachTra)
                                                .addComponent(txt_SoTien_CanTraLai, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lb_ThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lb_SoDiemDuocCong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(ThongTin_HDLayout.createSequentialGroup()
                                            .addComponent(tbtn_ApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(34, 34, 34)
                                            .addComponent(tbtn_HuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(ThongTin_HDLayout.createSequentialGroup()
                                            .addGap(19, 19, 19)
                                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(0, 0, Short.MAX_VALUE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThongTin_HDLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        ThongTin_HDLayout.setVerticalGroup(
            ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTin_HDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Ma_KH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Ten_KH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_HienDiemTL, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbtn_SuDungDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_KhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbtn_ApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbtn_HuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lb_HienTongTien_ChuaGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_Hien_TienGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_SoDiemDuocCong, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_ThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_SoTienKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_SoTien_CanTraLai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ThongTin_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbtn_Tao_HD, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbtn_HuyTao, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Mã HD:");

        jLabel8.setText("Thời Gian Tạo:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Bảng Danh Sách Sản Phẩm"));

        tbl_DanhSach_SP.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_DanhSach_SP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Bảng Danh Sách Hoá đơn"));

        tbl_BangHoaDon.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbl_BangHoaDon);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Bảng Danh Sách Sản Phẩm Được Chọn"));

        tbl_Bang_SP_DaChon.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tbl_Bang_SP_DaChon);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Chức Năng Sản Phẩm"));

        jLabel4.setText("Số Lượng SP Cần Dùng :");

        jButton4.setText("Chọn SP");

        jButton5.setText("Huỷ Chọn SP");

        jButton6.setText("Sửa Chọn");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 27, Short.MAX_VALUE))
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel9.setText("Mã Nhân Viên :");

        jLabel12.setText("Hình Thức TT:");

        btg_HinhThuc_TT.add(rdo_TienMat);
        rdo_TienMat.setText("Tiền Mặt");

        btg_HinhThuc_TT.add(rdo_ChuyenKhoan);
        rdo_ChuyenKhoan.setText("Chuyển Khoản");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(ThongTin_HD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_ThoiGianTao, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_Ma_TK, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(rdo_TienMat, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(rdo_ChuyenKhoan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_MaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_MaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdo_TienMat)
                        .addComponent(rdo_ChuyenKhoan))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_ThoiGianTao, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(txt_Ma_TK, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(ThongTin_HD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_Ma_KHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Ma_KHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Ma_KHActionPerformed

    private void txt_SoTien_CanTraLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SoTien_CanTraLaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SoTien_CanTraLaiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbtn_Tao_HDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtn_Tao_HDActionPerformed
        // TODO add your handling code here:
        Tao_HD();
    }//GEN-LAST:event_tbtn_Tao_HDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ThongTin_HD;
    private javax.swing.ButtonGroup btg_HinhThuc_TT;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel lb_HienDiemTL;
    private javax.swing.JLabel lb_HienTongTien_ChuaGiamGia;
    private javax.swing.JLabel lb_Hien_TienGiamGia;
    private javax.swing.JLabel lb_SoDiemDuocCong;
    private javax.swing.JLabel lb_ThanhTien;
    private javax.swing.JRadioButton rdo_ChuyenKhoan;
    private javax.swing.JRadioButton rdo_TienMat;
    private javax.swing.JTable tbl_BangHoaDon;
    private javax.swing.JTable tbl_Bang_SP_DaChon;
    private javax.swing.JTable tbl_DanhSach_SP;
    private javax.swing.JToggleButton tbtn_ApDung;
    private javax.swing.JToggleButton tbtn_HuyBo;
    private javax.swing.JToggleButton tbtn_HuyTao;
    private javax.swing.JToggleButton tbtn_SuDungDiem;
    private javax.swing.JToggleButton tbtn_Tao_HD;
    private javax.swing.JTextField txt_KhuyenMai;
    private javax.swing.JTextField txt_MaHD;
    private javax.swing.JTextField txt_Ma_KH;
    private javax.swing.JTextField txt_Ma_TK;
    private javax.swing.JTextField txt_SoTienKhachTra;
    private javax.swing.JTextField txt_SoTien_CanTraLai;
    private javax.swing.JTextField txt_Ten_KH;
    private javax.swing.JTextField txt_ThoiGianTao;
    // End of variables declaration//GEN-END:variables
}
