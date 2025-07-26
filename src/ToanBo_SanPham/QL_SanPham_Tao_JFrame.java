/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ToanBo_SanPham;

import ToanBo_SanPham.SanPham;
import javax.swing.table.DefaultTableModel;
import ToanBo_SanPham.QL_Tao_SanPham;
import java.awt.Image;
import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class QL_SanPham_Tao_JFrame extends javax.swing.JFrame {

    DefaultTableModel TableModel;
    QL_Tao_SanPham qlsp = new QL_Tao_SanPham();
    int Index = -1;
    String PathAnh = null;

    /**
     * Creates new form QL_SanPham_Tao_JFrame
     */
    public QL_SanPham_Tao_JFrame() {
        initComponents();
        this.setLocationRelativeTo(this);
        Initable_SP();
        FillToTable_SP();

        LocalDate ngayHienTai = LocalDate.now();
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Hiển thị vào textField
        txt_NgayTao_SP.setText(ngayHienTai.format(dinhDang));
        txt_NgayTao_SP.setEditable(false);
    }

    public void Initable_SP() {
        TableModel = new DefaultTableModel();
        String[] cols = {"Mã SP", "Tên SP", "Mô Tả SP", "Số Lượng", "Đơn Giá", "Mã Loại SP", "Hình Ảnh", "Ngày Tạo"};
        TableModel.setColumnIdentifiers(cols);
        tbl_SanPham.setModel(TableModel);
    }

    // Hiển Thị Tất Cả
    public void FillToTable_SP() {
        TableModel.setRowCount(0);
        for (SanPham sp : qlsp.GetAll_SP()) {
            TableModel.addRow(qlsp.GetRow_SP(sp));
        }
    }

    public void LamMoi_SP() {
        txt_Ma_SP.setText("");
        txt_Ten_SP.setText("");
        txt_DonGia_SP.setText("");
        txt_MoTa_SP.setText("");
        txt_SoLuong_SP.setText("");
        lb_Anh_SP.setIcon(null);      // Xóa hình ảnh đang hiển thị
        lb_Anh_SP.setText("Null");    // Ghi lại chữ nếu muốn
        PathAnh = null;
        FillToTable_SP();
        txt_NgayTao_SP.setText("");
        LocalDate ngayHienTai = LocalDate.now();
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Hiển thị vào textField
        txt_NgayTao_SP.setText(ngayHienTai.format(dinhDang));
    }

    public void Them_SP() {
        String Ma_SP = txt_Ma_SP.getText();
        String Ten_SP = txt_Ten_SP.getText();
        String MoTa_SP = txt_MoTa_SP.getText();
        int SoLuong_SP = Integer.parseInt(txt_SoLuong_SP.getText());
        float DonGia_SP = Float.valueOf(txt_DonGia_SP.getText());
        String Ma_LSP = txt_MaLSP.getText();
        String Anh_SP = PathAnh;
        // Khai báo pattern và formatter ngay đầu
        String datePattern = "dd/MM/yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);

        // ➤ Xử lý Ngày Bắt Đầu
        String ngayTao = txt_NgayTao_SP.getText().trim();
        Date Ngay_Tao_SP;

        if (ngayTao.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⛔ Ngày Bắt Đầu Khuyến Mãi không được để trống!");
            return;
        }

        try {
            LocalDate ngaybd = LocalDate.parse(ngayTao, formatter);
            Ngay_Tao_SP = Date.valueOf(ngaybd);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this,
                    "⚠️ Vui lòng nhập đúng định dạng ngày: " + datePattern + " (VD: 21/07/2025)",
                    "Lỗi định dạng",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        SanPham sp = new SanPham(Ma_SP, Ten_SP, MoTa_SP, SoLuong_SP, DonGia_SP, Ma_LSP, Anh_SP, Ngay_Tao_SP);
        int Result = qlsp.Them_TK(sp);
        if (Result == 1) {
            JOptionPane.showMessageDialog(this, "Thêm Sản Phẩm Thành Công.");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm Dữ Liệu Sản Phẩm Thất Bại.");
            return;
        }
    }

    public void Sua_SP() {
        Index = tbl_SanPham.getSelectedRow();
        if (Index >= 0) {
            String Ma_SP = txt_Ma_SP.getText();
            String Ten_SP = txt_Ten_SP.getText();
            String MoTa_SP = txt_MoTa_SP.getText();
            int SoLuong_SP = Integer.parseInt(txt_SoLuong_SP.getText());
            float DonGia_SP = Float.valueOf(txt_DonGia_SP.getText());
            String Ma_LSP = txt_MaLSP.getText();
            String Anh_SP = PathAnh;

            DateTimeFormatter NgayTao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate ngaytao = LocalDate.parse(txt_NgayTao_SP.getText(), NgayTao);
            Date Ngay_Tao_SP = Date.valueOf(ngaytao);

            SanPham sp = new SanPham(Ma_SP, Ten_SP, MoTa_SP, SoLuong_SP, DonGia_SP, Ma_LSP, Anh_SP, Ngay_Tao_SP);

            String TheoMa_SP = qlsp.GetAll_SP().get(Index).getMa_SP();
            int ReSult = qlsp.Sua_TK(sp, TheoMa_SP);
            if (ReSult == 1) {
                JOptionPane.showMessageDialog(this, "Sửa Sản Phẩm Thành Công.");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa Sản Phẩm Thất Bại.");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Dữ Liệu Trong Bảng Để Sửa Sản Phẩm.");
            return;
        }
    }

    public void Xoa_SP() {
        Index = tbl_SanPham.getSelectedRow();
        if (Index >= 0) {
            String TheoMa_SP = qlsp.GetAll_SP().get(Index).getMa_SP();
            String Ten_SP = qlsp.GetAll_SP().get(Index).getTen_SP();
            int Choice = JOptionPane.showConfirmDialog(this,
                    "Bạn Có Muốn Xoá Sản Phẩm:"
                    + "\n Mã Sản Phẩm: " + TheoMa_SP
                    + "\n Tên Sản Phẩm: " + Ten_SP, "Xác Nhận Xoá Sản Phẩm",
                    JOptionPane.YES_NO_OPTION);
            if (Choice == JOptionPane.YES_OPTION) {
                int Result = qlsp.Xoa_TK(TheoMa_SP);
                if (Result == 1) {
                    JOptionPane.showMessageDialog(this, "Xoá Sản Phẩm Thành Công.");
                } else {
                    JOptionPane.showMessageDialog(this, "Xoá Sản Phẩm Thất Bại.");
                    return;
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Sản Phẩm Trong Bảng Để Xoá.");
            return;
        }
    }

    public void ShowDetail() {
        Index = tbl_SanPham.getSelectedRow();
        if (Index >= 0) {
            SanPham sp = qlsp.GetAll_SP().get(Index);

            txt_Ma_SP.setText(sp.getMa_SP());
            txt_Ten_SP.setText(sp.getTen_SP());
            txt_DonGia_SP.setText(String.valueOf(sp.getDonGia_SP()));
            txt_SoLuong_SP.setText(String.valueOf(sp.getSoLuong_SP()));
            txt_MoTa_SP.setText(sp.getMoTa_SP());
            txt_Ma_SP.setText(sp.getMa_LSP());
            lb_Anh_SP.setText(sp.getHinhAnh_SP());

            // Định dạng ngày thành chuỗi dd/MM/yyyy
            // Thay phần xử lý ngày tạo bằng đoạn này:
            // Thay phần xử lý ngày tạo bằng đoạn này:
            Date ngayTao = sp.getNgayTao_SP();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String ngayTaoStr = (ngayTao != null) ? sdf.format(ngayTao) : "NULL";
            txt_NgayTao_SP.setText(ngayTaoStr);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_SanPham = new javax.swing.JTable();
        NhapThongTin_Panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lb_Anh_SP = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_MoTa_SP = new javax.swing.JTextArea();
        btn_ChonAnh = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_MaLSP = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_NgayTao_SP = new javax.swing.JTextField();
        txt_Ma_SP = new javax.swing.JTextField();
        txt_SoLuong_SP = new javax.swing.JTextField();
        txt_Ten_SP = new javax.swing.JTextField();
        txt_DonGia_SP = new javax.swing.JTextField();
        ChucNangChinh_Panel = new javax.swing.JPanel();
        btn_LamMoi = new javax.swing.JButton();
        btn_Them_SP = new javax.swing.JButton();
        btn_Sua_SP = new javax.swing.JButton();
        btn_Xoa_SP = new javax.swing.JButton();
        btn_Xoa_SP1 = new javax.swing.JButton();
        btn_Xoa_SP2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Bảng Danh Sách Sản Phẩm"));

        tbl_SanPham.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_SanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_SanPham);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        NhapThongTin_Panel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nhập Thông Tin Sản Phẩm"));

        jLabel1.setText("Mã SP: ");

        jLabel2.setText("Tên SP:");

        jLabel3.setText("Mô Tả SP:");

        jLabel4.setText("Đơn Giá:");

        jLabel5.setText("Số Lượng:");

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_Anh_SP, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_Anh_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txt_MoTa_SP.setColumns(20);
        txt_MoTa_SP.setRows(5);
        txt_MoTa_SP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(txt_MoTa_SP);

        btn_ChonAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Anh_SP.png"))); // NOI18N
        btn_ChonAnh.setText("Chọn Ảnh");
        btn_ChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ChonAnhActionPerformed(evt);
            }
        });

        jLabel6.setText("Mã Loại Sản Phẩm:");

        txt_MaLSP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("Ngày Tạo SP:");

        txt_NgayTao_SP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_Ma_SP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_SoLuong_SP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_Ten_SP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_DonGia_SP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout NhapThongTin_PanelLayout = new javax.swing.GroupLayout(NhapThongTin_Panel);
        NhapThongTin_Panel.setLayout(NhapThongTin_PanelLayout);
        NhapThongTin_PanelLayout.setHorizontalGroup(
            NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_DonGia_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txt_SoLuong_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_Ma_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, NhapThongTin_PanelLayout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txt_Ten_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_ChonAnh)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_NgayTao_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_MaLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        NhapThongTin_PanelLayout.setVerticalGroup(
            NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_ChonAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_NgayTao_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                        .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_Ma_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_Ten_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_DonGia_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_SoLuong_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_MaLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        ChucNangChinh_Panel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Chức Năng Chính"));

        btn_LamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/LamMoi_SP.png"))); // NOI18N
        btn_LamMoi.setText("Làm Mới");
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });

        btn_Them_SP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Them_SP.png"))); // NOI18N
        btn_Them_SP.setText("Thêm SP");
        btn_Them_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Them_SPActionPerformed(evt);
            }
        });

        btn_Sua_SP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Sua_SP.png"))); // NOI18N
        btn_Sua_SP.setText("Sửa SP");
        btn_Sua_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Sua_SPActionPerformed(evt);
            }
        });

        btn_Xoa_SP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Xoa_SP.png"))); // NOI18N
        btn_Xoa_SP.setText("Xoá SP");
        btn_Xoa_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Xoa_SPActionPerformed(evt);
            }
        });

        btn_Xoa_SP1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/NhapFlieExcel_NguyenLieu.png"))); // NOI18N
        btn_Xoa_SP1.setText("Nhập File Excel");
        btn_Xoa_SP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Xoa_SP1ActionPerformed(evt);
            }
        });

        btn_Xoa_SP2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/XuatFileExcel_NguyenLieu.png"))); // NOI18N
        btn_Xoa_SP2.setText("Xuất File Excel");
        btn_Xoa_SP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Xoa_SP2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ChucNangChinh_PanelLayout = new javax.swing.GroupLayout(ChucNangChinh_Panel);
        ChucNangChinh_Panel.setLayout(ChucNangChinh_PanelLayout);
        ChucNangChinh_PanelLayout.setHorizontalGroup(
            ChucNangChinh_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChucNangChinh_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ChucNangChinh_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Xoa_SP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Xoa_SP2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(ChucNangChinh_PanelLayout.createSequentialGroup()
                        .addGroup(ChucNangChinh_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btn_Sua_SP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_LamMoi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(ChucNangChinh_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_Them_SP, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(btn_Xoa_SP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        ChucNangChinh_PanelLayout.setVerticalGroup(
            ChucNangChinh_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChucNangChinh_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ChucNangChinh_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Them_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(ChucNangChinh_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Sua_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Xoa_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(btn_Xoa_SP1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(btn_Xoa_SP2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jButton1.setText("Đóng Trang");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(NhapThongTin_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ChucNangChinh_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(ChucNangChinh_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(NhapThongTin_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ChonAnhActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            PathAnh = file.getAbsolutePath(); // Gán lại cho biến toàn cục

            // Xử lý scale ảnh về đúng kích thước của lb_UpAnh
            ImageIcon icon = new ImageIcon(PathAnh);
            Image img = icon.getImage().getScaledInstance(lb_Anh_SP.getWidth(), lb_Anh_SP.getHeight(), Image.SCALE_SMOOTH);
            lb_Anh_SP.setIcon(new ImageIcon(img));
            lb_Anh_SP.setText(""); // Ẩn chữ nếu đang hiện "Null"
        }
    }//GEN-LAST:event_btn_ChonAnhActionPerformed

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        // TODO add your handling code here:
        LamMoi_SP();
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void btn_Them_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Them_SPActionPerformed
        // TODO add your handling code here:
        Them_SP();
    }//GEN-LAST:event_btn_Them_SPActionPerformed

    private void btn_Sua_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Sua_SPActionPerformed
        // TODO add your handling code here:
        Sua_SP();
    }//GEN-LAST:event_btn_Sua_SPActionPerformed

    private void btn_Xoa_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Xoa_SPActionPerformed
        // TODO add your handling code here:
        Xoa_SP();
    }//GEN-LAST:event_btn_Xoa_SPActionPerformed

    private void tbl_SanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SanPhamMouseClicked
        // TODO add your handling code here:
        ShowDetail();
    }//GEN-LAST:event_tbl_SanPhamMouseClicked

    private void btn_Xoa_SP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Xoa_SP1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Xoa_SP1ActionPerformed

    private void btn_Xoa_SP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Xoa_SP2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Xoa_SP2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(QL_SanPham_Tao_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QL_SanPham_Tao_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QL_SanPham_Tao_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QL_SanPham_Tao_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QL_SanPham_Tao_JFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ChucNangChinh_Panel;
    private javax.swing.JPanel NhapThongTin_Panel;
    private javax.swing.JButton btn_ChonAnh;
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JButton btn_Sua_SP;
    private javax.swing.JButton btn_Them_SP;
    private javax.swing.JButton btn_Xoa_SP;
    private javax.swing.JButton btn_Xoa_SP1;
    private javax.swing.JButton btn_Xoa_SP2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_Anh_SP;
    private javax.swing.JTable tbl_SanPham;
    private javax.swing.JTextField txt_DonGia_SP;
    private javax.swing.JTextField txt_MaLSP;
    private javax.swing.JTextField txt_Ma_SP;
    private javax.swing.JTextArea txt_MoTa_SP;
    private javax.swing.JTextField txt_NgayTao_SP;
    private javax.swing.JTextField txt_SoLuong_SP;
    private javax.swing.JTextField txt_Ten_SP;
    // End of variables declaration//GEN-END:variables
}
