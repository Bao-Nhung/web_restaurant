/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View_JPanel;

import Model.NguyenLieu;
import javax.swing.table.DefaultTableModel;
import Service.QL_NguyenLieu;
import java.awt.Image;
import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class QL_NguyenLieu_Panel extends javax.swing.JPanel {

    DefaultTableModel TableModel;
    QL_NguyenLieu qlnl = new QL_NguyenLieu();
    int Index = -1;
    String PathAnh = null;

    /**
     * Creates new form QLDH
     */
    public QL_NguyenLieu_Panel() {
        initComponents();
        txt_NgayNhap.setEditable(false);
        LocalDate ngayHienTai = LocalDate.now();
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Hiển thị vào textField
        txt_NgayNhap.setText(ngayHienTai.format(dinhDang));
    }

    public void Initable() {
        TableModel = new DefaultTableModel();
        String[] cols = {"Mã NL", "Tên NL", "Số Lượng", "Đơn Vị Tính", "Giá Nhập", "Ngày Nhập", "Ảnh NL"};
        TableModel.setColumnIdentifiers(cols);
        tbl_NguyenLieu.setModel(TableModel);
    }

    // Hiển Thị Tất Cả
    public void FillToTable() {
        TableModel.setRowCount(0);
        for (NguyenLieu nl : qlnl.Get_All()) {
            TableModel.addRow(qlnl.GetRow(nl));
        }
    }

    public void LamMoi_NL() {
        txt_MaNL.setText("");
        txt_TenNL.setText("");
        txt_SoLuong.setText("");
        txt_DonViTinh.setText("");
        lb_HienAnh.setIcon(null);      // Xóa hình ảnh đang hiển thị
        lb_HienAnh.setText("Null");    // Ghi lại chữ nếu muốn
        PathAnh = null;
    }

    public void Them_NL() {
        String Ma_NL = txt_MaNL.getText();
        String Ten_NL = txt_TenNL.getText();
        int Soluong_NL = Integer.valueOf(txt_SoLuong.getText());
        String DonViTinh_NL = txt_DonViTinh.getText();
        float GiaNhap_NL = Float.valueOf(txt_GiaNhap.getText());
        DateTimeFormatter DinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(txt_NgayNhap.getText(), DinhDang);
        Date NgayDK = Date.valueOf(localDate);
        String Anh_NL = PathAnh;

        NguyenLieu nl = new NguyenLieu(Ma_NL, Ten_NL, DonViTinh_NL, Soluong_NL, GiaNhap_NL, NgayDK, Anh_NL);
        int ReSult = qlnl.Them_NL(nl);
        if (ReSult == 1) {
            JOptionPane.showMessageDialog(this, "Thêm Thông Tin Nguyên Liệu:"
                    + "\n Mã Nguyên Liệu: " + Ma_NL
                    + "\n Tên Nguyên Liệu: " + Ten_NL
                    + "\n Số Lượng Nhập Vào: " + Soluong_NL
                    + "\n Đơn Vị Tính: " + DonViTinh_NL
                    + "\n Giá Nhập Nguyên Liệu: " + GiaNhap_NL
                    + "\n Thành Công.");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm Nguyên Liệu Thất Bại.");
            return;
        }
    }

    public void Sua_NL() {
        Index = tbl_NguyenLieu.getSelectedRow();
        if (Index >= 0) {
            String Ma_NL = txt_MaNL.getText();
            String Ten_NL = txt_TenNL.getText();
            int Soluong_NL = Integer.valueOf(txt_SoLuong.getText());
            String DonViTinh_NL = txt_DonViTinh.getText();
            float GiaNhap_NL = Float.valueOf(txt_GiaNhap.getText());
            DateTimeFormatter DinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(txt_NgayNhap.getText(), DinhDang);
            Date NgayDK = Date.valueOf(localDate);
            String Anh_NL = PathAnh;

            NguyenLieu nl = new NguyenLieu(Ma_NL, Ten_NL, DonViTinh_NL, Soluong_NL, GiaNhap_NL, NgayDK, Anh_NL);
            
            String TheoMa = qlnl.Get_All().get(Index).getMa_NL();
            int ReSult = qlnl.Sua_NL(nl, TheoMa);
            if (ReSult == 1) {
                JOptionPane.showMessageDialog(this, "Sửa Thông Tin Nguyên Liệu Thành Công.");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa Thông Tin Nguyên Liệu Thất Bại");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Nguyên Liệu Trong Bảng Để Sửa.");
        }
    }

    public void Xoa_NL() {
        Index = tbl_NguyenLieu.getSelectedRow();
        if (Index >= 0) {
            String TheoMa = qlnl.Get_All().get(Index).getMa_NL();
            String Ten = qlnl.Get_All().get(Index).getTen_NL();
            int Choice = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Xoá Nguyên Liệu:"
                    + "\n Mã Nguyên Liệu: " + TheoMa
                    + "\n Tên Nguyên Liệu: " + Ten, "Xác Nhận Xoá Nguyên Liệu.", JOptionPane.YES_NO_OPTION);
            if (Choice == JOptionPane.YES_OPTION) {
                int Result = qlnl.Xoa_NL(TheoMa);
                if (Result == 1) {
                    JOptionPane.showMessageDialog(this, "Xoá Nguyên Liệu:"
                            + "\n Mã Nguyên Liệu: " + TheoMa
                            + "\n Tên Nguyên Liệu: " + Ten
                            + "\n Thành Công.");
                } else {
                    JOptionPane.showMessageDialog(this, "Xoá Nguyên Liệu Thất Bại.");
                    return;
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Thông Tin Nguyên Liệu Ở Trong Bảng.");
            return;
        }
    }

    public void ShowDetail() {
        Index = tbl_NguyenLieu.getSelectedRow();
        if (Index >= 0) {
            NguyenLieu nl = qlnl.Get_All().get(Index);
            txt_MaNL.setText(nl.getMa_NL());
            txt_TenNL.setText(nl.getTen_NL());
            txt_SoLuong.setText(String.valueOf(nl.getSoLuongTon_NL()));
            txt_DonViTinh.setText(nl.getDonViTinh_NL());
            txt_GiaNhap.setText(String.valueOf(nl.getGiaNhap_NL()));
            txt_NgayNhap.setText(String.valueOf(nl.getNgayNhap_NL()));
            lb_HienAnh.setText(nl.getAnh_NL());
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_MaNL = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_TenNL = new javax.swing.JTextField();
        txt_SoLuong = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_DonViTinh = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_GiaNhap = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_NgayNhap = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lb_HienAnh = new javax.swing.JLabel();
        btn_ChonAnh = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btn_LamMoi = new javax.swing.JButton();
        btn_ThemNL = new javax.swing.JButton();
        btn_SuaNL = new javax.swing.JButton();
        btn_XoaNL = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txt_TimKiem = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_NguyenLieu = new javax.swing.JTable();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nhập Thông Tin Nguyên Liệu"));

        jLabel1.setText("Mã NL :");

        txt_MaNL.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel2.setText("Tên NL :");

        txt_TenNL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_SoLuong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Số Lượng :");

        txt_DonViTinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Đơn Vị Tính :");

        jLabel5.setText("Giá Nhập :");

        txt_GiaNhap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("Ngày Nhập :");

        txt_NgayNhap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lb_HienAnh.setText("jLabel7");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_HienAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_HienAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_ChonAnh.setText("Choose Anh");
        btn_ChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ChonAnhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_NgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_DonViTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_MaNL, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_TenNL, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_GiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_ChonAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_MaNL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_TenNL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_DonViTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_GiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_NgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_ChonAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Chức Năng Chính"));

        btn_LamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/LamMoi_TK.png"))); // NOI18N
        btn_LamMoi.setText("Làm Mới");
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });

        btn_ThemNL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Them_TK.png"))); // NOI18N
        btn_ThemNL.setText("Thêm NL");
        btn_ThemNL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNLActionPerformed(evt);
            }
        });

        btn_SuaNL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Sua_TK.png"))); // NOI18N
        btn_SuaNL.setText("Sửa NL");
        btn_SuaNL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaNLActionPerformed(evt);
            }
        });

        btn_XoaNL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Delete_TK.png"))); // NOI18N
        btn_XoaNL.setText("Xoá NL");
        btn_XoaNL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaNLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_LamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_ThemNL, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(btn_SuaNL, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(btn_XoaNL, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btn_ThemNL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_SuaNL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_XoaNL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Chức Năng Tìm Kiếm Nguyên Liệu"));

        jLabel8.setText("Vui Lòng Nhập :");

        txt_TimKiem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/TimKiem_TK.png"))); // NOI18N
        jButton6.setText("Tìm Kiếm");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbl_NguyenLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        tbl_NguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_NguyenLieuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_NguyenLieu);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ChonAnhActionPerformed
        // TODO add your handling code here:
        lb_HienAnh.setText("");
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            PathAnh = file.getAbsolutePath(); // Gán lại cho biến toàn cục

            // Xử lý scale ảnh về đúng kích thước của lb_UpAnh
            ImageIcon icon = new ImageIcon(PathAnh);
            Image img = icon.getImage().getScaledInstance(lb_HienAnh.getWidth(), lb_HienAnh.getHeight(), Image.SCALE_SMOOTH);
            lb_HienAnh.setIcon(new ImageIcon(img));
            lb_HienAnh.setText(""); // Ẩn chữ nếu đang hiện "Null"
        }
    }//GEN-LAST:event_btn_ChonAnhActionPerformed

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        // TODO add your handling code here:
        LamMoi_NL();
        FillToTable();
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void btn_ThemNLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNLActionPerformed
        // TODO add your handling code here:
        Them_NL();
        FillToTable();
    }//GEN-LAST:event_btn_ThemNLActionPerformed

    private void btn_SuaNLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaNLActionPerformed
        // TODO add your handling code here:
        Sua_NL();
        FillToTable();
    }//GEN-LAST:event_btn_SuaNLActionPerformed

    private void btn_XoaNLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaNLActionPerformed
        // TODO add your handling code here:
        Xoa_NL();
        FillToTable();
    }//GEN-LAST:event_btn_XoaNLActionPerformed

    private void tbl_NguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_NguyenLieuMouseClicked
        // TODO add your handling code here:
        ShowDetail();
    }//GEN-LAST:event_tbl_NguyenLieuMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ChonAnh;
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JButton btn_SuaNL;
    private javax.swing.JButton btn_ThemNL;
    private javax.swing.JButton btn_XoaNL;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_HienAnh;
    private javax.swing.JTable tbl_NguyenLieu;
    private javax.swing.JTextField txt_DonViTinh;
    private javax.swing.JTextField txt_GiaNhap;
    private javax.swing.JTextField txt_MaNL;
    private javax.swing.JTextField txt_NgayNhap;
    private javax.swing.JTextField txt_SoLuong;
    private javax.swing.JTextField txt_TenNL;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables
}
