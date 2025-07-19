/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ToanBo_KhuyenMai;

import javax.swing.table.DefaultTableModel;
import ToanBo_KhuyenMai.QL_KhuyenMai;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class QL_KhuyenMai_Panel extends javax.swing.JPanel {

    DefaultTableModel TableModel = new DefaultTableModel();
    int InDex = -1;
    QL_KhuyenMai qlkm = new QL_KhuyenMai();

    /**
     * Creates new form QLKM
     */
    public QL_KhuyenMai_Panel() {
        initComponents();
        Initable();
        FillToTable();
        txt_NgayBatDau_KM.setEditable(false);
        LocalDate ngayHienTai = LocalDate.now();
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Hiển thị vào textField
        txt_NgayBatDau_KM.setText(ngayHienTai.format(dinhDang));
    }

    public void Initable() {
        TableModel = new DefaultTableModel();
        String[] cols = {"Mã KM", "Tên KM", "Mô Tả", "Hình Thức", "Điểm Yêu Cầu ", "Giá Trị", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Ngày Trong Tháng", "Điều Kiện", "Trạng Thái"};
        TableModel.setColumnIdentifiers(cols);
        tbl_KhuyenMai.setModel(TableModel);
    }

    // Hiển Thị Tất Cả
    public void FillToTable() {
        TableModel.setRowCount(0);
        for (KhuyenMai km : qlkm.Get_All()) {
            TableModel.addRow(qlkm.GetRow(km));
        }
    }

    public void LamMoi_KM() {
        txt_Ma_KM.setText("");
        txt_Ten_KM.setText("");
        txt_MoTa_KM.setText("");
        txt_HinhThuc_KM.setText("");
        txt_DiemYeuCau_KM.setText("");
        txt_GiaTri_KM.setText("");

        LocalDate ngayHienTai = LocalDate.now();
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Hiển thị vào textField
        txt_NgayBatDau_KM.setText(ngayHienTai.format(dinhDang));

        txt_NgạyKetThuc_KM.setText("");
        txt_NgayTrongThang_KM.setText("");
        txt_DieuKien_KM.setText("");
        btg_TrangThai.clearSelection();
    }

    public boolean Check_Form(){
        String Ma_KM = txt_Ma_KM.getText();
        String Ten_KM = txt_Ten_KM.getText();
        String MoTa_KM = txt_MoTa_KM.getText();
        String HìnhThuc_KM = txt_HinhThuc_KM.getText();
        int DiemYeuCau = Integer.valueOf(txt_DiemYeuCau_KM.getText());
        float GiaTri_KM = Float.valueOf(txt_GiaTri_KM.getText());
        if (txt_Ma_KM.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã Khuyến Mãi Không Được Để Trống.");
            return false;
        }
        
        return true;
    }
    public void Them_KM() {
        String Ma_KM = txt_Ma_KM.getText();
        String Ten_KM = txt_Ten_KM.getText();
        String MoTa_KM = txt_MoTa_KM.getText();
        String HìnhThuc_KM = txt_HinhThuc_KM.getText();
        int DiemYeuCau = Integer.valueOf(txt_DiemYeuCau_KM.getText());
        float GiaTri_KM = Float.valueOf(txt_GiaTri_KM.getText());

        DateTimeFormatter NgayBD = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate ngaybd = LocalDate.parse(txt_NgayBatDau_KM.getText(), NgayBD);
        Date Ngay_BD_KM = Date.valueOf(ngaybd);

        DateTimeFormatter NgayKT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate ngaykt = LocalDate.parse(txt_NgạyKetThuc_KM.getText(), NgayKT);
        Date Ngay_KT_KM = Date.valueOf(ngaykt);

        String NgayTrongThang_KM = txt_NgayTrongThang_KM.getText();
        String DieuKien_KM = txt_DieuKien_KM.getText();

        boolean TrangThai = rdo_HoatDong.isSelected();

        KhuyenMai km = new KhuyenMai(Ma_KM, Ten_KM, MoTa_KM, HìnhThuc_KM, DiemYeuCau, GiaTri_KM, Ngay_BD_KM, Ngay_KT_KM, NgayTrongThang_KM, DieuKien_KM, TrangThai);
        int ReSult = qlkm.Them_KM(km);

        if (ReSult == 1) {
            JOptionPane.showMessageDialog(this, "Thêm Thành Công Khuyến Mãi.");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm Khuyến Mãi Thất Bại.");
            return;
        }
    }

    public void Xoa_KM() {
        InDex = tbl_KhuyenMai.getSelectedRow();
        if (InDex >= 0) {
            String TheoMa_KM = qlkm.Get_All().get(InDex).getMa_KM();
            String Ten_KM = qlkm.Get_All().get(InDex).getTen_KM();

            int Choice = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Xoá Khuyến Mãi:"
                    + "\n Mã KM: " + TheoMa_KM
                    + "\n Tên KM:" + Ten_KM, "Xác Nhận Xoá Khuyến Mãi ?", JOptionPane.YES_NO_OPTION);
            if (Choice == JOptionPane.YES_OPTION) {
                int ReSult = qlkm.Xoa_KM(TheoMa_KM);
                if (ReSult == 1) {
                    JOptionPane.showMessageDialog(this, "Xoá Khuyến Mãi:"
                            + "\n Mã KM: " + TheoMa_KM
                            + "\n Tên KM: " + Ten_KM
                            + "\n Thành Công.");
                } else {
                    JOptionPane.showMessageDialog(this, "Xoá Khuyến Mãi Thất Bại."
                            + "\n Mã KM: " + TheoMa_KM
                            + "\n Tên KM: " + Ten_KM
                            + "\n Thát Bại.");
                    return;
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Dữ Liệu Khuyến Mãi Trong Bảng Để Xoá Khuyến Mãi.");
            return;
        }
    }

    public void Sua_KM() {
        InDex = tbl_KhuyenMai.getSelectedRow();
        if (InDex >= 0) {
            String Ma_KM = txt_Ma_KM.getText();
            String Ten_KM = txt_Ten_KM.getText();
            String MoTa_KM = txt_MoTa_KM.getText();
            String HìnhThuc_KM = txt_HinhThuc_KM.getText();
            int DiemYeuCau = Integer.valueOf(txt_DiemYeuCau_KM.getText());
            float GiaTri_KM = Float.valueOf(txt_GiaTri_KM.getText());

            DateTimeFormatter NgayBD = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate ngaybd = LocalDate.parse(txt_NgayBatDau_KM.getText(), NgayBD);
            Date Ngay_BD_KM = Date.valueOf(ngaybd);

            DateTimeFormatter NgayKT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate ngaykt = LocalDate.parse(txt_NgạyKetThuc_KM.getText(), NgayKT);
            Date Ngay_KT_KM = Date.valueOf(ngaykt);

            String NgayTrongThang_KM = txt_NgayTrongThang_KM.getText();
            String DieuKien_KM = txt_DieuKien_KM.getText();

            boolean TrangThai = rdo_HoatDong.isSelected();

            KhuyenMai km = new KhuyenMai(Ma_KM, Ten_KM, MoTa_KM, HìnhThuc_KM, DiemYeuCau, GiaTri_KM, Ngay_BD_KM, Ngay_KT_KM, NgayTrongThang_KM, DieuKien_KM, TrangThai);

            String TheoMa_KM = qlkm.Get_All().get(InDex).getMa_KM();

            int ReSult = qlkm.Sua_KM(km, TheoMa_KM);
            if (ReSult == 1) {
                JOptionPane.showMessageDialog(this, "Sửa Khuyến Mãi:"
                        + "\n Mã Khuyến Mãi Cũ: " + TheoMa_KM
                        + "\n Mã Khuyễn Mãi Mới: " + Ma_KM + "\n Tên Khuyến Mãi Mới: " + Ten_KM + "\n Thành Công.");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa Khuyến Mãi Thất Bại.");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Dữ Liệu Trong Bảng Khuyến Mãi Để Xoá Khuyến Mãi.");
            return;
        }
    }

    public void ShowDetail() {
        InDex = tbl_KhuyenMai.getSelectedRow();
        if (InDex >= 0) {
            KhuyenMai km = qlkm.Get_All().get(InDex);

            txt_Ma_KM.setText(km.getMa_KM());
            txt_Ten_KM.setText(km.getTen_KM());
            txt_MoTa_KM.setText(km.getMoTa_KM());
            txt_HinhThuc_KM.setText(km.getHinhThuc_KM());
            txt_DiemYeuCau_KM.setText(String.valueOf(km.getDiemYeuCau_KM()));
            txt_GiaTri_KM.setText(String.valueOf(km.getGiaTri_KM()));

            // Định dạng ngày thành chuỗi dd/MM/yyyy
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            txt_NgayBatDau_KM.setText(km.getNgay_BD().toLocalDate().format(formatter));
            txt_NgạyKetThuc_KM.setText(km.getNgay_KT().toLocalDate().format(formatter));

            txt_NgayTrongThang_KM.setText(km.getNgayTrongThang_KM());
            txt_DieuKien_KM.setText(km.getDieuKien_KM());

            // Hiển thị trạng thái bằng radio
            if (km.getTrangThai()) {
                rdo_HoatDong.setSelected(true);
            } else {
                rdo_KhongHoatDong.setSelected(true);
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
        NhapThongTin_Panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_Ma_KM = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_Ten_KM = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_HinhThuc_KM = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_DiemYeuCau_KM = new javax.swing.JTextField();
        txt_GiaTri_KM = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_NgayBatDau_KM = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_NgạyKetThuc_KM = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_NgayTrongThang_KM = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        rdo_HoatDong = new javax.swing.JRadioButton();
        rdo_KhongHoatDong = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_MoTa_KM = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        txt_DieuKien_KM = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btn_LamMoiKM = new javax.swing.JButton();
        btn_ThemKM = new javax.swing.JButton();
        btn_SuaKM = new javax.swing.JButton();
        btn_XoaKM = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_KhuyenMai = new javax.swing.JTable();

        NhapThongTin_Panel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nhập Thông Tin Khuyến Mãi"));

        jLabel1.setText("Mã KM:");

        txt_Ma_KM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Tên KM:");

        txt_Ten_KM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Hình Thức:");

        txt_HinhThuc_KM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Điểm Yêu Cầu:");

        txt_DiemYeuCau_KM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_GiaTri_KM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setText("Giá Trị :");

        jLabel6.setText("Ngày Bắt Đầu:");

        txt_NgayBatDau_KM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("Ngày Kết Thúc:");

        txt_NgạyKetThuc_KM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setText("Ngày Trong Tháng:");

        txt_NgayTrongThang_KM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setText("Trạng Thái:");

        btg_TrangThai.add(rdo_HoatDong);
        rdo_HoatDong.setText("Đang Hoạt Động");

        btg_TrangThai.add(rdo_KhongHoatDong);
        rdo_KhongHoatDong.setText("Không Hoạt Động");

        jLabel10.setText("Mô Tả:");

        txt_MoTa_KM.setColumns(20);
        txt_MoTa_KM.setRows(5);
        txt_MoTa_KM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(txt_MoTa_KM);

        jLabel11.setText("Điều Kiện :");

        txt_DieuKien_KM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout NhapThongTin_PanelLayout = new javax.swing.GroupLayout(NhapThongTin_Panel);
        NhapThongTin_Panel.setLayout(NhapThongTin_PanelLayout);
        NhapThongTin_PanelLayout.setHorizontalGroup(
            NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                        .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                                .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_Ma_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_Ten_KM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_HinhThuc_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_NgayTrongThang_KM))
                                    .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_DiemYeuCau_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_GiaTri_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(83, 83, 83))
                    .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdo_KhongHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdo_HoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_NgayBatDau_KM)
                    .addComponent(txt_NgạyKetThuc_KM)
                    .addComponent(txt_DieuKien_KM)
                    .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                        .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        NhapThongTin_PanelLayout.setVerticalGroup(
            NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                        .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jLabel2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NhapThongTin_PanelLayout.createSequentialGroup()
                                .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_Ma_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_Ten_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_HinhThuc_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)))
                        .addGap(18, 18, 18)
                        .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_DiemYeuCau_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_GiaTri_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_NgayTrongThang_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(rdo_HoatDong))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdo_KhongHoatDong))
                    .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_NgayBatDau_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_NgạyKetThuc_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_DieuKien_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Chức Năng Chính"));

        btn_LamMoiKM.setText("Làm Mới");
        btn_LamMoiKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiKMActionPerformed(evt);
            }
        });

        btn_ThemKM.setText("Thêm KM");
        btn_ThemKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemKMActionPerformed(evt);
            }
        });

        btn_SuaKM.setText("Sửa KM");
        btn_SuaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaKMActionPerformed(evt);
            }
        });

        btn_XoaKM.setText("Xoá KM");
        btn_XoaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaKMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_LamMoiKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_XoaKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_SuaKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_ThemKM, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_LamMoiKM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btn_ThemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_SuaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btn_XoaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Danh Sách Khuyến Mãi"));

        tbl_KhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_KhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_KhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_KhuyenMai);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 843, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(NhapThongTin_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(NhapThongTin_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_LamMoiKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiKMActionPerformed
        // TODO add your handling code here:
        LamMoi_KM();
    }//GEN-LAST:event_btn_LamMoiKMActionPerformed

    private void btn_ThemKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemKMActionPerformed
        // TODO add your handling code here:
        Them_KM();
    }//GEN-LAST:event_btn_ThemKMActionPerformed

    private void btn_SuaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaKMActionPerformed
        // TODO add your handling code here:
        Sua_KM();
    }//GEN-LAST:event_btn_SuaKMActionPerformed

    private void btn_XoaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaKMActionPerformed
        // TODO add your handling code here:
        Xoa_KM();
    }//GEN-LAST:event_btn_XoaKMActionPerformed

    private void tbl_KhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_KhuyenMaiMouseClicked
        // TODO add your handling code here:
        ShowDetail();
    }//GEN-LAST:event_tbl_KhuyenMaiMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel NhapThongTin_Panel;
    private javax.swing.ButtonGroup btg_TrangThai;
    private javax.swing.JButton btn_LamMoiKM;
    private javax.swing.JButton btn_SuaKM;
    private javax.swing.JButton btn_ThemKM;
    private javax.swing.JButton btn_XoaKM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdo_HoatDong;
    private javax.swing.JRadioButton rdo_KhongHoatDong;
    private javax.swing.JTable tbl_KhuyenMai;
    private javax.swing.JTextField txt_DiemYeuCau_KM;
    private javax.swing.JTextField txt_DieuKien_KM;
    private javax.swing.JTextField txt_GiaTri_KM;
    private javax.swing.JTextField txt_HinhThuc_KM;
    private javax.swing.JTextField txt_Ma_KM;
    private javax.swing.JTextArea txt_MoTa_KM;
    private javax.swing.JTextField txt_NgayBatDau_KM;
    private javax.swing.JTextField txt_NgayTrongThang_KM;
    private javax.swing.JTextField txt_NgạyKetThuc_KM;
    private javax.swing.JTextField txt_Ten_KM;
    // End of variables declaration//GEN-END:variables
}
