/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ToanBo_TaiKhoan;

import ToanBo_TaiKhoan.QL_TaiKhoan;
import ToanBo_TaiKhoan.Tai_Khoan;
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
import javax.swing.SwingUtilities;

/**
 *
 * @author ADMIN
 */
public class QL_TaiKhoan_NhanVien_JFrame extends javax.swing.JFrame {

    DefaultTableModel TableModel;
    QL_TaiKhoan qltk = new QL_TaiKhoan();
    int index = -1;
    String PathAnh = null;

    /**
     * Creates new form QL_TaiKhoan_NhanVien_JFrame
     */
    public QL_TaiKhoan_NhanVien_JFrame() {
        initComponents();
        Initable();
        FillToTableTheoVaiTro("Nhân Viên");
        txt_Ngay_DK.setEditable(false);
        LocalDate ngayHienTai = LocalDate.now();
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Hiển thị vào textField
        txt_Ngay_DK.setText(ngayHienTai.format(dinhDang));
    }

    public void Initable() {
        TableModel = new DefaultTableModel();
            String[] cols = {"Mã Tài Khoản", "Tên Tài Khoản", "SĐT", "Email", "Địa Chỉ", "Ngày Đăng Ký", "Ảnh", "Trạng Thái"};
        TableModel.setColumnIdentifiers(cols);
        tbl_NhanVien.setModel(TableModel);
    }

    // Hiển Thị Tất Cả
//    public void FillToTable() {
//        TableModel.setRowCount(0);
//        for (Tai_Khoan tk : qltk.Get_All()) {
//            TableModel.addRow(qltk.GetRow(tk));
//        }
//    }
//    Hiển Thị Theo Vai Trò
    public void FillToTableTheoVaiTro(String VaiTro) {
        TableModel.setRowCount(0);
        for (Tai_Khoan_8_O tk : qltk.Get_All_NhanVien()) {
                TableModel.addRow(qltk.GetRow_NhanVien(tk));
        }
    }
// Làm Mới Tài Khoản

    public void LamMoi() {
        txt_Ma_TK.setText("");
        txt_Ten_TK.setText("");
        txt_SDT_TK.setText("");
        LocalDate ngayHienTai = LocalDate.now();
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Hiển thị vào textField
        txt_Ngay_DK.setText(ngayHienTai.format(dinhDang));
        txt_Email.setText("");
        txt_DiaChi.setText("");
        btg_TrangThai.clearSelection();
        lb_UpAnh_Real.setIcon(null);      // Xóa hình ảnh đang hiển thị
        lb_UpAnh_Real.setText("Null");    // Ghi lại chữ nếu muốn
        PathAnh = null;              // Đặt lại biến ảnh (tránh lưu nhầm)
        FillToTableTheoVaiTro("Nhân Viên");
    }

    // Check Tài Khoản Khi Thêm
    public void Check_Form() {
        // Mã Tài Khoản 
        if (txt_Ma_TK.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã Tài Khoản Không Được Để Trống.");
            return;
        }
        if (txt_Ma_TK.getText().trim().length() <= 15) {
            JOptionPane.showMessageDialog(this, "Mã Tài Khoản Không Được Vượt Quá 15 Ký Tự.");
            return;
        }
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
                    FillToTableTheoVaiTro("Nhân Viên");
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
                Tai_Khoan_8_O tk = new Tai_Khoan_8_O(Ma_TK, Ten_TK, SDT, Email, DiaChi, NgayDK, Anh_TK, trangthai);
                String TheoMa = qltk.Get_All().get(index).getMa_TK();
                int ReSult = qltk.Sua_TK_NhanVien(tk, TheoMa);
                if (ReSult == 1) {
                    JOptionPane.showMessageDialog(this, "Sửa Tài Khoản:"
                            + "\nMã Cũ: " + TheoMa
                            + "\nMã Tài Khoản Mới: " + Ma_TK
                            + "\nThành Công.");
                    FillToTableTheoVaiTro("Nhân Viên");
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
            if (lb_UpAnh_Real != null) {
                String pathAnh = tk.getAnh_TK();
                if (pathAnh != null && !pathAnh.trim().isEmpty()) {
                    ImageIcon icon = new ImageIcon(pathAnh);
                    Image img = icon.getImage().getScaledInstance(lb_UpAnh_Real.getWidth(), lb_UpAnh_Real.getHeight(), Image.SCALE_SMOOTH);
                    lb_UpAnh_Real.setIcon(new ImageIcon(img));
                    lb_UpAnh_Real.setText(""); // Xóa chữ nếu có
                } else {
                    lb_UpAnh_Real.setIcon(null);
                    lb_UpAnh_Real.setText("Không có ảnh"); // hoặc "Null" như anh muốn
                }
            } else {
                System.out.println("⚠️ lb_UpAnh chưa được khởi tạo!");
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

        btg_TrangThai = new javax.swing.ButtonGroup();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        lb_UpAnh_Real = new javax.swing.JLabel();
        txt_Ma_TK = new javax.swing.JTextField();
        txt_Ten_TK = new javax.swing.JTextField();
        txt_SDT_TK = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_DiaChi = new javax.swing.JTextArea();
        jLabel27 = new javax.swing.JLabel();
        rdo_HoatDong = new javax.swing.JRadioButton();
        rdo_KhongHoatDong = new javax.swing.JRadioButton();
        txt_Ngay_DK = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txt_Email = new javax.swing.JTextField();
        btn_Chon_Anh = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        btn_LamMoi = new javax.swing.JButton();
        btn_ThemDL = new javax.swing.JButton();
        btn_SuaDL = new javax.swing.JButton();
        btn_XoaDL = new javax.swing.JButton();
        btn_DongTrang = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_NhanVien = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập Thông Tin Tài Khoản"));

        jLabel22.setText("Mã Tài Khoản :");

        jLabel23.setText("Tên Tài Khoản :");

        jLabel24.setText("Số Điện Thoại :");

        jLabel25.setText("Địa Chỉ :");

        jLabel26.setText("Ngày Đăng Ký :");

        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lb_UpAnh_Real.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_UpAnh_RealMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_UpAnh_Real, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_UpAnh_Real, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addContainerGap())
        );

        txt_Ma_TK.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_Ten_TK.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_SDT_TK.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_DiaChi.setColumns(20);
        txt_DiaChi.setRows(5);
        txt_DiaChi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(txt_DiaChi);

        jLabel27.setText("Trạng Thái :");

        rdo_HoatDong.setText("Đang Hoạt Động");

        rdo_KhongHoatDong.setText("Không Hoạt Động");

        txt_Ngay_DK.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel28.setText("Email :");

        txt_Email.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_Chon_Anh.setText("Chọn Ảnh");
        btn_Chon_Anh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Chon_AnhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_Ngay_DK))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_Ma_TK, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_Ten_TK))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1)
                                    .addComponent(txt_Email)
                                    .addComponent(txt_SDT_TK))))))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_Chon_Anh, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdo_HoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdo_KhongHoatDong))))
                .addGap(19, 19, 19))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_Chon_Anh, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(192, 192, 192)
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdo_HoatDong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdo_KhongHoatDong))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txt_Ma_TK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txt_Ten_TK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(txt_SDT_TK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_Ngay_DK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Thanh Chức Năng"));

        btn_LamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/LamMoi_TK.png"))); // NOI18N
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

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_LamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_ThemDL, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(btn_SuaDL, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(btn_XoaDL, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_ThemDL, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_SuaDL, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_XoaDL, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btn_DongTrang.setText("Đóng Trang");
        btn_DongTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DongTrangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_DongTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_DongTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Bảng Danh Sách Nhân Viên"));

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

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lb_UpAnh_RealMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_UpAnh_RealMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_UpAnh_RealMouseClicked

    private void btn_Chon_AnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Chon_AnhActionPerformed
        // TODO add your handling code here:
        if (lb_UpAnh_Real != null) {
            lb_UpAnh_Real.setText(""); // Xóa chữ cũ nếu có

            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                PathAnh = file.getAbsolutePath(); // Gán lại cho biến toàn cục

                // Đợi JLabel hiển thị xong rồi mới lấy kích thước thật
                SwingUtilities.invokeLater(() -> {
                    int width = lb_UpAnh_Real.getWidth();
                    int height = lb_UpAnh_Real.getHeight();

                    // Nếu JLabel chưa hiển thị, dùng kích thước mặc định
                    if (width <= 0 || height <= 0) {
                        width = 120;
                        height = 120;
                    }

                    ImageIcon icon = new ImageIcon(PathAnh);
                    Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    lb_UpAnh_Real.setIcon(new ImageIcon(img));
                    lb_UpAnh_Real.setText(""); // Ẩn chữ nếu đang hiện "Null"
                });
            }
        } else {
            JOptionPane.showMessageDialog(this, "⚠️ Lỗi: lb_UpAnh chưa được khởi tạo!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_Chon_AnhActionPerformed

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        // TODO add your handling code here:
        LamMoi();
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void btn_ThemDLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemDLActionPerformed
        // TODO add your handling code here:
        //        Check_Form();
        ThemDL_TaiKhoan();
    }//GEN-LAST:event_btn_ThemDLActionPerformed

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

    private void btn_DongTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DongTrangActionPerformed
        // TODO add your handling code here:
        dispose();

    }//GEN-LAST:event_btn_DongTrangActionPerformed

    private void tbl_NhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_NhanVienMouseClicked
        // TODO add your handling code here:
        ShowDetail_Tk();
    }//GEN-LAST:event_tbl_NhanVienMouseClicked

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
            java.util.logging.Logger.getLogger(QL_TaiKhoan_NhanVien_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QL_TaiKhoan_NhanVien_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QL_TaiKhoan_NhanVien_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QL_TaiKhoan_NhanVien_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QL_TaiKhoan_NhanVien_JFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btg_TrangThai;
    private javax.swing.JButton btn_Chon_Anh;
    private javax.swing.JButton btn_DongTrang;
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JButton btn_SuaDL;
    private javax.swing.JButton btn_ThemDL;
    private javax.swing.JButton btn_XoaDL;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_UpAnh;
    private javax.swing.JLabel lb_UpAnh1;
    private javax.swing.JLabel lb_UpAnh2;
    private javax.swing.JLabel lb_UpAnh3;
    private javax.swing.JLabel lb_UpAnh_Real;
    private javax.swing.JRadioButton rdo_HoatDong;
    private javax.swing.JRadioButton rdo_KhongHoatDong;
    private javax.swing.JTable tbl_NhanVien;
    private javax.swing.JTextArea txt_DiaChi;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_Ma_TK;
    private javax.swing.JTextField txt_Ngay_DK;
    private javax.swing.JTextField txt_SDT_TK;
    private javax.swing.JTextField txt_Ten_TK;
    // End of variables declaration//GEN-END:variables
}
