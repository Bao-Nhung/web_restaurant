/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View_JPanel;

import Service.QL_TaiKhoan;
import Model.Tai_Khoan;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ADMIN
 */
public class QL_TaiKhoan_Panel extends javax.swing.JPanel {

    DefaultTableModel TableModel;
    QL_TaiKhoan qltk = new QL_TaiKhoan();
    int index = -1;
    String PathAnh = null;

    /**
     * Creates new form QLNV
     */
    public QL_TaiKhoan_Panel() {
        initComponents();
        Initable();

        txt_Ngay_DK.setEditable(false);
        LocalDate ngayHienTai = LocalDate.now();
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Hiển thị vào textField
        txt_Ngay_DK.setText(ngayHienTai.format(dinhDang));
    }

    public void Initable() {
        TableModel = new DefaultTableModel();
        String[] cols = {"Mã Tài Khoản", "Tên Tài Khoản", "SĐT", "Email", "Địa Chỉ", "Vai Trò", "Ngày Đăng Ký", "Ảnh", "Trạng Thái"};
        TableModel.setColumnIdentifiers(cols);
        tbl_NhanVien.setModel(TableModel);
    }
    // Hiển Thị Tất Cả
    public void FillToTable() {
        TableModel.setRowCount(0);
        for (Tai_Khoan tk : qltk.Get_All()) {
            TableModel.addRow(qltk.GetRow(tk));
        }
    }
//    Hiển Thị Theo Vai Trò
    public void FillToTableTheoVaiTro(String VaiTro) {
        TableModel.setRowCount(0);
        for (Tai_Khoan tk : qltk.Get_All()) {
            if (tk.getVaiTro_TK().equalsIgnoreCase(VaiTro)) {
                TableModel.addRow(qltk.GetRow(tk));
            }
        }
    }
// Làm Mới Tài Khoản

    public void LamMoi() {
        txt_Ma_TK.setText("");
        txt_Ten_TK.setText("");
        txt_SDT_TK.setText("");
//        txt_Ngay_DK.setText("");
        txt_Email.setText("");
        txt_DiaChi.setText("");
        txt_TimKiem.setText("");
        btg_KieuTimKiem.clearSelection();
        btg_TrangThai.clearSelection();
        lb_UpAnh.setIcon(null);      // Xóa hình ảnh đang hiển thị
        lb_UpAnh.setText("Null");    // Ghi lại chữ nếu muốn
        PathAnh = null;              // Đặt lại biến ảnh (tránh lưu nhầm)

    }

    // Check Tài Khoản Khi Thêm
    public void Check_Form() {
        // Mã Tài Khoản 
        if (txt_Ma_TK.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã Tài Khoản Không Được Để Trống.");
            return;
        }
//        if (txt_Ma_TK.getText().trim().length() <= 15) {
//            JOptionPane.showMessageDialog(this, "Mã Tài Khoản Không Được Vượt Quá 15 Ký Tự.");
//            return;
//        }
        // Tên Tài Khoản
        if (txt_Ten_TK.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên Tài Khoản Không Được Để Trống.");
            return;
        }
        // Số Điện Thoại Tài Khoản
        if (txt_SDT_TK.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số Điện Thoại Của Tài Khoản Không Được Để Trống.");
            return;
        }

        if (txt_SDT_TK.getText().trim().length() != 10) {
            JOptionPane.showMessageDialog(this, "Chỉ Được Nhập Số Điện Thoại Có 10 Ký Tự.");
            return;
        }

        // Địa Chỉ Tài Khoản
        if (txt_DiaChi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa Chỉ Không Được Để Trống.");
            return;
        }

        // Trạng Thái     
        if (!rdo_HoatDong.isSelected() && !rdo_KhongHoatDong.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Trạng Thái Tài Khoản.");
            return; // Thoát không cho thêm nếu chưa chọn
        }
    }

    public boolean Check_ThongTin_TK() {
        if (txt_Ma_TK.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã Tài Khoản không được để trống.");
            return false;
        }
        if (txt_Ten_TK.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên Tài Khoản không được để trống.");
            return false;
        }
        if (txt_SDT_TK.getText().trim().isEmpty() || txt_SDT_TK.getText().length() != 10) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải gồm đúng 10 chữ số.");
            return false;
        }
        if (txt_Email.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email không được để trống.");
            return false;
        }
        if (txt_DiaChi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống.");
            return false;
        }
        if (!rdo_HoatDong.isSelected() && !rdo_KhongHoatDong.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn trạng thái.");
            return false;
        }
        return true;
    }

    // Thêm Dữ Liệu Tai Khoản
    public void ThemDL_TaiKhoan() {
        if (!Check_ThongTin_TK()) {
            return; // Dừng lại nếu chưa nhập đủ thông tin
        }
        String Ma_TK = txt_Ma_TK.getText();
        String Ten_TK = txt_Ten_TK.getText();
        String SDT = txt_SDT_TK.getText();
        String Email = txt_Email.getText(); // Sửa chỗ này, bạn gán nhầm Email = SDT
        String DiaChi = txt_DiaChi.getText();
        String VaiTro = "Nhân Viên";

        // Chuyển ngày đăng ký từ chuỗi sang java.sql.Date
        try {
            DateTimeFormatter DinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(txt_Ngay_DK.getText(), DinhDang);
            Date NgayDK = Date.valueOf(localDate);

            // Trạng thái: true (1) = Đang hoạt động, false (0) = Không hoạt động
            boolean TrangThai; // Vì Vai Trò Lưu Ở Nhân Viên Là Biến Boolean Nên Thêm Dữ Liệu Phải Thay Đổi
            if (VaiTro.equalsIgnoreCase("Đang Hoạt Động")) {
                TrangThai = true; // Nếu Vai Trò Ở Dao Diện Chọn Là Admin Thì Vai Trò Sẽ Là True
            } else {
                TrangThai = false; // Ngược Lại Dao Diện Là Nhân Viên Thì Vai Trf Sẽ Thành False
            }

            String Anh_TK = PathAnh;

            // Tạo đối tượng tài khoản
            Tai_Khoan tk = new Tai_Khoan(Ma_TK, Ten_TK, SDT, Email, DiaChi, VaiTro, NgayDK, Anh_TK, TrangThai);

            int Result = qltk.Them_TK(tk);

            if (Result == 1) {
                JOptionPane.showMessageDialog(this, "Thêm Dữ Liệu Tài Khoản Thành Công.");

            } else {
                JOptionPane.showMessageDialog(this, "Thêm Dữ Liệu Tài Khoản Thất Bại.");
                return;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi xử lý ngày đăng ký: " + e.getMessage());
        }
    }

    // Xoá Tài Khoản 
    public void Xoa_TK() {
        index = tbl_NhanVien.getSelectedRow();
        if (index >= 0) {
            String Ten = qltk.Get_All().get(index).getTen_TK();
            int Choice = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Xoá Tài Khoản"
                    + "\nTên:" + Ten, "Xác Nhận Xoá", JOptionPane.YES_NO_OPTION);
            if (Choice == JOptionPane.YES_OPTION) {
                String TheoMa = qltk.Get_All().get(index).getMa_TK();
                int ReSult = qltk.Xoa_TK(TheoMa);
                if (ReSult == 1) {
                    JOptionPane.showMessageDialog(this, "Xoá Tài Khoản: "
                            + "\nMã Tài Khoản: " + TheoMa
                            + "\nTên Tài Khoản: " + Ten
                            + "\n\nThành Công.");
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Xoá Thất Bại.");
            return;
        }
    }

    // Sửa Tài Khoản
    public void Sua_TK() {
        index = tbl_NhanVien.getSelectedRow();
        if (index >= 0) {
            String Ma_TK = txt_Ma_TK.getText();
            String Ten_TK = txt_Ten_TK.getText();
            String SDT = txt_SDT_TK.getText();
            String Email = txt_Email.getText(); // Sửa chỗ này, bạn gán nhầm Email = SDT
            String DiaChi = txt_DiaChi.getText();
            String VaiTro = "Nhân Viên";

            // Chuyển ngày đăng ký từ chuỗi sang java.sql.Date
            try {
                DateTimeFormatter DinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = LocalDate.parse(txt_Ngay_DK.getText(), DinhDang);
                Date NgayDK = Date.valueOf(localDate);

                // Trạng thái: true (1) = Đang hoạt động, false (0) = Không hoạt động
                String TrangThai = rdo_HoatDong.isSelected() ? "Đang Hoạt Động" : "Không Hoạt Động"; // Khi Muốn Đổi Thì Phải Chọn Một Trong Hai Là Hoạt Động Hay Không Hoạt Động
                boolean trangthai;
                if (VaiTro.equalsIgnoreCase("Đang Hoạt Động")) {
                    trangthai = true;
                } else {
                    trangthai = false;
                }

                String Anh_TK = PathAnh;

                // Tạo đối tượng tài khoản
                Tai_Khoan tk = new Tai_Khoan(Ma_TK, Ten_TK, SDT, Email, DiaChi, VaiTro, NgayDK, Anh_TK, trangthai);
                String TheoMa = qltk.Get_All().get(index).getMa_TK();
                int ReSult = qltk.Sua_TK(tk, TheoMa);
                if (ReSult == 1) {
                    JOptionPane.showMessageDialog(this, "Sửa Tài Khoản:"
                            + "\nMã Cũ: " + TheoMa
                            + "\nMã Tài Khoản Mới: " + Ma_TK
                            + "\nThành Công.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi xử lý ngày đăng ký: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Dữ Liệu Trong Bảng Để Sửa Tài Khoản.");
            return;
        }
    }

    public void ShowDetail_Tk() {
        index = tbl_NhanVien.getSelectedRow();
        if (index >= 0) {
            Tai_Khoan tk = qltk.Get_All().get(index);

            txt_Ma_TK.setText(tk.getMa_TK());
            txt_Ten_TK.setText(tk.getTen_TK());
            txt_SDT_TK.setText(tk.getSDT_TK());
            txt_Email.setText(tk.getEmail_TK());
            txt_DiaChi.setText(tk.getDiaChi_TK());

            // Định dạng ngày để hiển thị
            Date ngayDK = tk.getNgay_DK_TK(); // java.sql.Date
            DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String ngayDK_format = ngayDK.toLocalDate().format(dinhDang);
            txt_Ngay_DK.setText(ngayDK_format);

            // Hiển thị trạng thái
            boolean TrangThai = tk.getTrangThai_TK();
            if (TrangThai == true) {
                rdo_HoatDong.setSelected(true);
            } else {
                rdo_KhongHoatDong.setSelected(true);
            }

            // Hiển thị ảnh (nếu ảnh là đường dẫn lưu từ DB)
            PathAnh = tk.getAnh_TK();
            if (PathAnh != null && !PathAnh.equals("")) {
                ImageIcon icon = new ImageIcon(PathAnh);
                Image img = icon.getImage().getScaledInstance(lb_UpAnh.getWidth(), lb_UpAnh.getHeight(), Image.SCALE_SMOOTH);
                lb_UpAnh.setIcon(new ImageIcon(img));
                lb_UpAnh.setText("");
            } else {
                lb_UpAnh.setIcon(null);
                lb_UpAnh.setText("Null");
            }
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

        btg_VaiTro = new javax.swing.ButtonGroup();
        btg_TrangThai = new javax.swing.ButtonGroup();
        btg_KieuTimKiem = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_NhanVien = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lb_UpAnh = new javax.swing.JLabel();
        txt_Ma_TK = new javax.swing.JTextField();
        txt_Ten_TK = new javax.swing.JTextField();
        txt_SDT_TK = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_DiaChi = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        rdo_HoatDong = new javax.swing.JRadioButton();
        rdo_KhongHoatDong = new javax.swing.JRadioButton();
        txt_Ngay_DK = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_Email = new javax.swing.JTextField();
        btn_Chon_Anh = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btn_LamMoi = new javax.swing.JButton();
        btn_ThemDL = new javax.swing.JButton();
        btn_SuaDL = new javax.swing.JButton();
        btn_XoaDL = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txt_TimKiem = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        rdo_TheoMa = new javax.swing.JRadioButton();
        rdo_TheoTen = new javax.swing.JRadioButton();
        rdo_Theosdt = new javax.swing.JRadioButton();
        jButton5 = new javax.swing.JButton();

        tbl_NhanVien.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_NhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_NhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_NhanVien);

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập Thông Tin Tài Khoản"));

        jLabel2.setText("Mã Tài Khoản :");

        jLabel3.setText("Tên Tài Khoản :");

        jLabel4.setText("Số Điện Thoại :");

        jLabel5.setText("Địa Chỉ :");

        jLabel6.setText("Ngày Đăng Ký :");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lb_UpAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_UpAnhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_UpAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_UpAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        txt_DiaChi.setColumns(20);
        txt_DiaChi.setRows(5);
        jScrollPane1.setViewportView(txt_DiaChi);

        jLabel8.setText("Trạng Thái :");

        btg_TrangThai.add(rdo_HoatDong);
        rdo_HoatDong.setText("Đang Hoạt Động");

        btg_TrangThai.add(rdo_KhongHoatDong);
        rdo_KhongHoatDong.setText("Không Hoạt Động");

        jLabel7.setText("Email :");

        btn_Chon_Anh.setText("Chọn Ảnh");
        btn_Chon_Anh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Chon_AnhActionPerformed(evt);
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
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_Ngay_DK))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_Ma_TK))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_Ten_TK))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_SDT_TK))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addComponent(txt_Email))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rdo_KhongHoatDong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdo_HoatDong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Chon_Anh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_Ma_TK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_Ten_TK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_SDT_TK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_Chon_Anh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addGap(12, 12, 12)
                        .addComponent(rdo_HoatDong))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(70, 70, 70))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_KhongHoatDong)
                    .addComponent(jLabel6)
                    .addComponent(txt_Ngay_DK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thanh Chức Năng"));

        btn_LamMoi.setText("Làm Mới");
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });

        btn_ThemDL.setText("Thêm Dữ Lệu");
        btn_ThemDL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemDLActionPerformed(evt);
            }
        });

        btn_SuaDL.setText("Sửa Dữ Liệu");
        btn_SuaDL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaDLActionPerformed(evt);
            }
        });

        btn_XoaDL.setText("Xoá Dữ Liệu");
        btn_XoaDL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaDLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_LamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_ThemDL, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(btn_SuaDL, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(btn_XoaDL, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btn_ThemDL, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btn_SuaDL, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_XoaDL, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng Tìm Kiếm"));

        jLabel9.setText("Vui Lòng Nhập :");

        jLabel10.setText("Kiểu Tìm Kiếm :");

        btg_KieuTimKiem.add(rdo_TheoMa);
        rdo_TheoMa.setText("Theo Mã");
        rdo_TheoMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_TheoMaActionPerformed(evt);
            }
        });

        btg_KieuTimKiem.add(rdo_TheoTen);
        rdo_TheoTen.setText("Theo Tên");

        btg_KieuTimKiem.add(rdo_Theosdt);
        rdo_Theosdt.setText("Theo Số Điện Thoại");

        jButton5.setText("Tìm Kiếm");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rdo_TheoMa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdo_TheoTen)
                        .addGap(18, 18, 18)
                        .addComponent(rdo_Theosdt))
                    .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(rdo_TheoMa)
                    .addComponent(rdo_TheoTen)
                    .addComponent(rdo_Theosdt))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThemDLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemDLActionPerformed
        // TODO add your handling code here:
//        Check_Form();
        ThemDL_TaiKhoan();
    }//GEN-LAST:event_btn_ThemDLActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void rdo_TheoMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_TheoMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_TheoMaActionPerformed

    private void lb_UpAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_UpAnhMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_UpAnhMouseClicked

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        // TODO add your handling code here:
        LamMoi();
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void btn_Chon_AnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Chon_AnhActionPerformed
        // TODO add your handling code here:
        lb_UpAnh.setText("");
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setDialogTitle("Chọn ảnh đại diện");
//        fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "jpeg", "png", "gif"));
//
//        int result = fileChooser.showOpenDialog(null);
//        if (result == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = fileChooser.getSelectedFile();
//            PathAnh = selectedFile.getAbsolutePath();
//
//            // Hiển thị ảnh lên label (nếu có)
//            lb_UpAnh.setIcon(new ImageIcon(new ImageIcon(PathAnh).getImage().getScaledInstance(108, 116, Image.SCALE_SMOOTH)));
//
//            // Gán đường dẫn ảnh vào biến hoặc text field
        ////            txtPathAnh.setText(pathAnh);
//        }
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            PathAnh = file.getAbsolutePath(); // Gán lại cho biến toàn cục

            // Xử lý scale ảnh về đúng kích thước của lb_UpAnh
            ImageIcon icon = new ImageIcon(PathAnh);
            Image img = icon.getImage().getScaledInstance(lb_UpAnh.getWidth(), lb_UpAnh.getHeight(), Image.SCALE_SMOOTH);
            lb_UpAnh.setIcon(new ImageIcon(img));
            lb_UpAnh.setText(""); // Ẩn chữ nếu đang hiện "Null"
        }

    }//GEN-LAST:event_btn_Chon_AnhActionPerformed

    private void btn_SuaDLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaDLActionPerformed
        // TODO add your handling code here:
        Sua_TK();
        LamMoi();
    }//GEN-LAST:event_btn_SuaDLActionPerformed

    private void btn_XoaDLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaDLActionPerformed
        // TODO add your handling code here:
        Xoa_TK();
        LamMoi();
    }//GEN-LAST:event_btn_XoaDLActionPerformed

    private void tbl_NhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_NhanVienMouseClicked
        // TODO add your handling code here:
        ShowDetail_Tk();
    }//GEN-LAST:event_tbl_NhanVienMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btg_KieuTimKiem;
    private javax.swing.ButtonGroup btg_TrangThai;
    private javax.swing.ButtonGroup btg_VaiTro;
    private javax.swing.JButton btn_Chon_Anh;
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JButton btn_SuaDL;
    private javax.swing.JButton btn_ThemDL;
    private javax.swing.JButton btn_XoaDL;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_UpAnh;
    private javax.swing.JRadioButton rdo_HoatDong;
    private javax.swing.JRadioButton rdo_KhongHoatDong;
    private javax.swing.JRadioButton rdo_TheoMa;
    private javax.swing.JRadioButton rdo_TheoTen;
    private javax.swing.JRadioButton rdo_Theosdt;
    private javax.swing.JTable tbl_NhanVien;
    private javax.swing.JTextArea txt_DiaChi;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_Ma_TK;
    private javax.swing.JTextField txt_Ngay_DK;
    private javax.swing.JTextField txt_SDT_TK;
    private javax.swing.JTextField txt_Ten_TK;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables
}
