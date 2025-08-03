/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ToanBo_KhuyenMai;
// 📅 Xử lý ngày tháng

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
// 🗓️ Tương tác với SQL kiểu DATE
import java.sql.*;
import java.time.ZoneId;

// 🖥️ Giao diện Swing
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class QL_KhuyenMai_JFrame extends javax.swing.JFrame {

    DefaultTableModel TableModel_SP = new DefaultTableModel();
    DefaultTableModel TableModel_DangHoatDong = new DefaultTableModel();
    DefaultTableModel TableModel_KhongHoatDong = new DefaultTableModel();
    int InDex = -1;
    QL_KhuyenMai qlkm = new QL_KhuyenMai();

    /**
     * Creates new form QL_KhuyenMai_JFrame
     */
    public QL_KhuyenMai_JFrame() {
        initComponents();
        Initable();
        FillToTable();
        Initable_KM01();
        FillToTable_KM01();
        Initable_KM02();
        FillToTable_KM02();

        String maTuDong = String.format("KM%03d", qlkm.getSoLuongKhuyenMai() + 1);
        txt_Ma_KM.setText(maTuDong);  // txt_MaKM là ô hiển thị mã khuyến mãi
        txt_Ma_KM.setEnabled(false);

        // Sử Lý Chọn
        cbox_HinhThuc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                XuLy_HinhThucKM(); // gọi hàm xử lý mỗi khi người dùng đổi lựa chọn
            }
        });
        // Ngày Bắt Đầu KM
        txt_NgayBatDau_KM.addPropertyChangeListener("date", evt -> {
            CapNhatTrangThaiTheoNgay();
        });
        rdo_HoatDong.setSelected(false);
        rdo_KhongHoatDong.setSelected(false);
        rdo_HoatDong.setEnabled(false);
        rdo_KhongHoatDong.setEnabled(false);
        txt_DiemTichLuy.setEnabled(false);
        txt_TienMat.setEnabled(false);
    }

    public void CapNhatTrangThaiTheoNgay() {
        LocalDate ngayHienTai = LocalDate.now();

        // Lấy ngày từ JDateChooser dưới dạng java.util.Date
        java.util.Date utilDateBatDau = txt_NgayBatDau_KM.getDate();
        java.util.Date utilDateKetThuc = txt_NgayKetThuc_KM.getDate();

        // Kiểm tra rỗng trước khi xử lý
        if (utilDateBatDau == null || utilDateKetThuc == null) {
            rdo_KhongHoatDong.setSelected(false);
            rdo_HoatDong.setSelected(false);
            return;
        }

        // Chuyển sang LocalDate để so sánh ngày
        LocalDate ngayBatDau = DateUtils.convertToLocalDate(utilDateBatDau);
        LocalDate ngayKetThuc = DateUtils.convertToLocalDate(utilDateKetThuc);

        // Cập nhật trạng thái theo logic thời gian
        if (ngayHienTai.isBefore(ngayBatDau)) {
            rdo_KhongHoatDong.setSelected(true);
            rdo_HoatDong.setSelected(false);
        } else if (!ngayHienTai.isAfter(ngayKetThuc)) {
            rdo_HoatDong.setSelected(true);
            rdo_KhongHoatDong.setSelected(false);
        } else {
            rdo_KhongHoatDong.setSelected(true);
            rdo_HoatDong.setSelected(false);
        }

        // Nếu cần lưu vào CSDL, chuyển sang java.sql.Date như sau:
        java.sql.Date sqlDateBatDau = new java.sql.Date(utilDateBatDau.getTime());
        java.sql.Date sqlDateKetThuc = new java.sql.Date(utilDateKetThuc.getTime());

        // sqlDateBatDau và sqlDateKetThuc đã sẵn sàng để insert/update vào database nhé!
    }

    public void Initable() {
        TableModel_SP = new DefaultTableModel();
        String[] cols = {"Mã KM", "Tên KM", "Mô Tả", "Hình Thức", "Điểm Yêu Cầu ", "Giá Trị", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Ngày Trong Tháng", "Điều Kiện", "Trạng Thái"};
        TableModel_SP.setColumnIdentifiers(cols);
        tbl_KhuyenMai.setModel(TableModel_SP);
    }

    // Hiển Thị Tất Cả
    public void FillToTable() {
        TableModel_SP.setRowCount(0);
        for (KhuyenMai km : qlkm.Get_All()) {
            TableModel_SP.addRow(qlkm.GetRow(km));
        }
    }

    // Khuyến Mãi Theo Trạng Thái 01
    public void Initable_KM01() {
        TableModel_DangHoatDong = new DefaultTableModel();
        String[] cols = {"Mã KM", "Tên KM", "Mô Tả", "Hình Thức", "Điểm Yêu Cầu ", "Giá Trị", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Ngày Trong Tháng", "Điều Kiện"};
        TableModel_DangHoatDong.setColumnIdentifiers(cols);
        tbl_KM_DangHoatDong.setModel(TableModel_DangHoatDong);
    }

    // Hiển Thị Tất Cả
    public void FillToTable_KM01() {
        TableModel_KhongHoatDong.setRowCount(0);
        for (KhuyenMai_10_O km : qlkm.Get_All_KM01()) {
            TableModel_DangHoatDong.addRow(qlkm.GetRow_KM01(km));
        }
    }

    // Khuyến Mãi Theo Trạng Thái 02
    public void Initable_KM02() {
        TableModel_KhongHoatDong = new DefaultTableModel();
        String[] cols = {"Mã KM", "Tên KM", "Mô Tả", "Hình Thức", "Điểm Yêu Cầu ", "Giá Trị", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Ngày Trong Tháng", "Điều Kiện"};
        TableModel_KhongHoatDong.setColumnIdentifiers(cols);
        tbl_KM_KhongHoatDong.setModel(TableModel_KhongHoatDong);
    }

    // Hiển Thị Tất Cả
    public void FillToTable_KM02() {
        TableModel_KhongHoatDong.setRowCount(0);
        for (KhuyenMai_10_O km : qlkm.Get_All_KM02()) {
            TableModel_KhongHoatDong.addRow(qlkm.GetRow_KM02(km));
        }
    }

    public void LamMoi_KM() {
        txt_Ten_KM.setText("");
        txt_MoTa_KM.setText("");
        cbox_HinhThuc.setSelectedItem("Điểm Tích Luỹ");
        txt_DiemTichLuy.setValue(0);
        txt_TienMat.setText("");
        txt_GiaTri_KM.setText("");
        txt_NgayBatDau_KM.setDate(null);
        txt_NgayKetThuc_KM.setDate(null);
        txt_NgayTrongThang_KM.setText("");
        txt_DieuKien_KM.setText("");
        btg_TrangThai.clearSelection();
        String maTuDong = String.format("KM%03d", qlkm.getSoLuongKhuyenMai() + 1);
        txt_Ma_KM.setText(maTuDong);  // txt_MaKM là ô hiển thị mã khuyến mãi
        txt_Ma_KM.setEnabled(false);
    }

    public void Them_KM() {
        // 🎯 Mã tự động
        String Ma_KM = String.format("KM%03d", qlkm.getSoLuongKhuyenMai() + 1);
        txt_Ma_KM.setText(Ma_KM);
        txt_Ma_KM.setEnabled(false);

        // 📋 Lấy thông tin từ form
        String Ten_KM = txt_Ten_KM.getText().trim();
        String MoTa_KM = txt_MoTa_KM.getText().trim();
        String HinhThuc_KM = cbox_HinhThuc.getSelectedItem().toString().trim();
        String ngayThangKM = txt_NgayTrongThang_KM.getText().trim();
        String DieuKien_KM = txt_DieuKien_KM.getText().trim();
        float GiaTri_KM = 0;
        try {
            String input = txt_GiaTri_KM.getText().trim();
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Giá Trị Khuyến Mãi Không Được Để Trống.");
                return;
            }
            // 📌 Kiểm tra ký tự chỉ cho phép số và dấu thập phân
            if (!input.matches("\\d+(\\.\\d{1,2})?")) {
                JOptionPane.showMessageDialog(this, "❌ Giá trị khuyến mãi chỉ được nhập số, không chứa chữ hay ký tự đặc biệt!");
                return;
            }

            GiaTri_KM = Float.parseFloat(input);

            // 🔒 Giới hạn từ 1000 → 1.000.000
            if (GiaTri_KM < 1000) {
                JOptionPane.showMessageDialog(this, "⚠️ Giá trị khuyến mãi phải từ 1.000 trở lên!");
                return;
            }

            if (GiaTri_KM > 1000000) {
                JOptionPane.showMessageDialog(this, "⚠️ Giá trị khuyến mãi không được vượt quá 1.000.000!");
                return;
            }

            // 🎯 Giá trị hợp lệ → dùng tiếp
            // xử lý tiếp logic...
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "❌ Giá trị nhập không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        int DiemYeuCau = (int) txt_DiemTichLuy.getValue();
        int TienMat = Integer.parseInt(txt_TienMat.getText().trim());

        // 🧪 Kiểm tra hình thức
        if (HinhThuc_KM.toLowerCase().contains("điểm")) {
            if (DiemYeuCau <= 0) {
                JOptionPane.showMessageDialog(this, "❌ Nhập số điểm tích lũy để đổi khuyến mãi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            TienMat = 0;
        } else if (HinhThuc_KM.toLowerCase().contains("tiền")) {
            if (TienMat <= 0) {
                JOptionPane.showMessageDialog(this, "❌ Nhập số tiền cần để nhận khuyến mãi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DiemYeuCau = 0;
        } else {
            JOptionPane.showMessageDialog(this, "❌ Chọn hình thức khuyến mãi hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 📅 Ngày và trạng thái
        LocalDate bd = DateUtils.convertToLocalDate(txt_NgayBatDau_KM.getDate());
        LocalDate kt = DateUtils.convertToLocalDate(txt_NgayKetThuc_KM.getDate());
        LocalDate ngayHienTai = LocalDate.now();

        if (bd.isBefore(ngayHienTai)) {
            JOptionPane.showMessageDialog(this, "❌ Ngày bắt đầu phải từ hôm nay!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (kt.isBefore(ngayHienTai)) {
            JOptionPane.showMessageDialog(this, "❌ Ngày kết thúc phải từ hôm nay trở đi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (bd.isAfter(kt)) {
            JOptionPane.showMessageDialog(this, "❌ Ngày bắt đầu không được sau ngày kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int ngayTrongThang = Integer.parseInt(ngayThangKM);
        if (ngayTrongThang < 1 || ngayTrongThang > 31) {
            JOptionPane.showMessageDialog(this, "⚠️ Ngày phải từ 1 → 31", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean TrangThai_KM = !bd.isAfter(ngayHienTai);
        if (TrangThai_KM) {
            rdo_HoatDong.setSelected(true);
        } else {
            rdo_KhongHoatDong.setSelected(true);
        }

        // 📦 Tạo đối tượng và thêm
        java.sql.Date Ngay_BD_KM = java.sql.Date.valueOf(bd);
        java.sql.Date Ngay_KT_KM = java.sql.Date.valueOf(kt);

        // 🧮 Giá trị cuối cùng truyền vào constructor
        int diemYeuCauFinal = 0;
        if (HinhThuc_KM.toLowerCase().contains("điểm")) {
            diemYeuCauFinal = DiemYeuCau;
        } else if (HinhThuc_KM.toLowerCase().contains("tiền")) {
            diemYeuCauFinal = TienMat;
        }

// 📦 Tạo đối tượng khuyến mãi đúng hình thức
        KhuyenMai km = new KhuyenMai(Ma_KM, Ten_KM, MoTa_KM, HinhThuc_KM,
                diemYeuCauFinal, GiaTri_KM, Ngay_BD_KM, Ngay_KT_KM,
                String.valueOf(ngayTrongThang), DieuKien_KM, TrangThai_KM);

        int kq = qlkm.Them_KM(km);
        if (kq == 1) {
            JOptionPane.showMessageDialog(this, "✅ Thêm khuyến mãi thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "❌ Thêm khuyến mãi thất bại!");
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
        int index = tbl_KhuyenMai.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "⚠️ Vui lòng chọn dòng cần sửa trong bảng!");
            return;
        }

        try {
            // 📋 Lấy dữ liệu từ form
            String maKM = txt_Ma_KM.getText().trim();
            String tenKM = txt_Ten_KM.getText().trim();
            String moTaKM = txt_MoTa_KM.getText().trim();

            String hinhThucKM = cbox_HinhThuc.getSelectedItem().toString().trim();

// 🚦 Kiểm tra theo hình thức KM
            float TienMat = 0f;
            int diemYeuCau = 0;

            if (hinhThucKM.equalsIgnoreCase("Điểm Tích Luỹ")) {
                try {
                    diemYeuCau = (Integer) txt_DiemTichLuy.getValue();
                    if (diemYeuCau <= 0) {
                        JOptionPane.showMessageDialog(this, "❌ Điểm tích lũy phải lớn hơn 0!");
                        return;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "❌ Dữ liệu điểm tích lũy không hợp lệ!");
                    return;
                }
            } else if (hinhThucKM.equalsIgnoreCase("Tiền Mặt")) {
                try {
                    String TienMatString = txt_TienMat.getText().trim();
                    if (!TienMatString.matches("\\d+(\\.\\d{1,2})?")) {
                        JOptionPane.showMessageDialog(this, "❌ Tiền mặt phải là số!");
                        return;
                    }
                    diemYeuCau = Integer.valueOf(TienMatString);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "❌ Dữ liệu tiền mặt không hợp lệ!");
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ Vui lòng chọn hình thức khuyến mãi hợp lệ!");
                return;
            }

            String giaTriInput = txt_GiaTri_KM.getText().trim();
            if (!giaTriInput.matches("\\d+(\\.\\d{1,2})?")) {
                JOptionPane.showMessageDialog(this, "❌ Giá trị KM chỉ được nhập số!");
                return;
            }

            float giaTriKM = Float.parseFloat(giaTriInput);
            if (giaTriKM < 1000 || giaTriKM > 1000000) {
                JOptionPane.showMessageDialog(this, "⚠️ Giá trị KM phải từ 1.000 đến 1.000.000!");
                return;
            }

            LocalDate bd = txt_NgayBatDau_KM.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate kt = txt_NgayKetThuc_KM.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            Date ngayBD = java.sql.Date.valueOf(bd);
            Date ngayKT = java.sql.Date.valueOf(kt);

            String ngayTrongThang = txt_NgayTrongThang_KM.getText().trim();
            String dieuKienKM = txt_DieuKien_KM.getText().trim();
            boolean trangThai = rdo_HoatDong.isSelected();

            // 📦 Tạo đối tượng KhuyenMai mới
            KhuyenMai km = new KhuyenMai(maKM, tenKM, moTaKM, hinhThucKM, diemYeuCau,
                    giaTriKM, ngayBD, ngayKT, ngayTrongThang, dieuKienKM, trangThai);

            // 🧾 Lấy mã KM cũ và cập nhật
            String maCu = qlkm.Get_All().get(index).getMa_KM();
            int result = qlkm.Sua_KM(km, maCu);

            if (result == 1) {
                JOptionPane.showMessageDialog(this, "✅ Sửa thành công!\n🔁 Mã cũ: " + maCu + "\n🆕 Mã mới: " + maKM);
            } else {
                JOptionPane.showMessageDialog(this, "❌ Sửa thất bại!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "⚠️ Lỗi khi sửa khuyến mãi: " + e.getMessage());
        }
    }

    public void ShowDetail() {
        int index = tbl_KhuyenMai.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "⚠️ Vui lòng chọn dòng cần xem chi tiết!");
            return;
        }

        KhuyenMai km = qlkm.Get_All().get(index);

        // 🌟 Hiển thị thông tin chung
        txt_Ma_KM.setText(km.getMa_KM());
        txt_Ten_KM.setText(km.getTen_KM());
        txt_MoTa_KM.setText(km.getMoTa_KM());
        cbox_HinhThuc.setSelectedItem(km.getHinhThuc_KM());

        // 💰 Phân biệt theo hình thức KM
        String hinhThuc = km.getHinhThuc_KM();
        if (hinhThuc.equalsIgnoreCase("Tiền Mặt")) {
            txt_TienMat.setText(String.valueOf(km.getDiemYeuCau_KM()));
            txt_TienMat.setEnabled(true);

            txt_DiemTichLuy.setValue(0);
            txt_DiemTichLuy.setEnabled(false);
        } else if (hinhThuc.equalsIgnoreCase("Điểm Tích Luỹ")) {
            txt_DiemTichLuy.setValue(km.getDiemYeuCau_KM());
            txt_DiemTichLuy.setEnabled(true);

            txt_TienMat.setText("");
            txt_TienMat.setEnabled(false);
        } else {
            txt_TienMat.setText("");
            txt_DiemTichLuy.setValue(0);
            txt_TienMat.setEnabled(false);
            txt_DiemTichLuy.setEnabled(false);
            JOptionPane.showMessageDialog(this, "❌ Hình thức KM không xác định!");
        }

        txt_GiaTri_KM.setText(String.valueOf(km.getGiaTri_KM()));

        // 📅 Hiển thị ngày khuyến mãi
        txt_NgayBatDau_KM.setDate(new Date(km.getNgay_BD().getTime()));
        txt_NgayKetThuc_KM.setDate(new Date(km.getNgay_KT().getTime()));

        txt_NgayTrongThang_KM.setText(km.getNgayTrongThang_KM());
        txt_DieuKien_KM.setText(km.getDieuKien_KM());

        // 🚦 Trạng thái hoạt động
        rdo_HoatDong.setSelected(km.getTrangThai());
        rdo_KhongHoatDong.setSelected(!km.getTrangThai());
    }

    public void XuLy_HinhThucKM() {
        String hinhThuc = cbox_HinhThuc.getSelectedItem().toString();
        if (hinhThuc.contains("Tiền") || hinhThuc.equalsIgnoreCase("Tiền Mặt")) {
            txt_DiemTichLuy.setEnabled(false);
            txt_TienMat.setEnabled(true);
            txt_DiemTichLuy.setValue(0);
        } else if (hinhThuc.contains("Điểm") || hinhThuc.contains("Điểm Tích Luỹ")) {
            txt_TienMat.setEnabled(false);
            txt_DiemTichLuy.setEnabled(true);
            txt_TienMat.setText("0");
        } else {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Hình Thức Khuyến Mãi Nhé.");
            txt_DiemTichLuy.setEnabled(false);
            txt_GiaTri_KM.setEnabled(false);
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
        jPanel1 = new javax.swing.JPanel();
        btn_LamMoiKM = new javax.swing.JButton();
        btn_ThemKM = new javax.swing.JButton();
        btn_SuaKM = new javax.swing.JButton();
        btn_XoaKM = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        QL_Table = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_KhuyenMai = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_KM_DangHoatDong = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_KM_KhongHoatDong = new javax.swing.JTable();
        NhapThongTin_Panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_Ma_KM = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_Ten_KM = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_GiaTri_KM = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
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
        txt_NgayBatDau_KM = new com.toedter.calendar.JDateChooser();
        txt_NgayKetThuc_KM = new com.toedter.calendar.JDateChooser();
        txt_DiemTichLuy = new javax.swing.JSpinner();
        txt_TienMat = new javax.swing.JTextField();
        cbox_HinhThuc = new javax.swing.JComboBox<>();
        btn_DongTrang = new javax.swing.JButton();
        btn_HuongDanSuDung = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Chức Năng Chính"));

        btn_LamMoiKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/KhuyenMai.png"))); // NOI18N
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
                    .addComponent(btn_LamMoiKM, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addComponent(btn_XoaKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_SuaKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_ThemKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btn_LamMoiKM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btn_ThemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btn_SuaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_XoaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
        jScrollPane3.setViewportView(tbl_KhuyenMai);

        QL_Table.addTab("Tất Cả Khuyến Mãi", jScrollPane3);

        tbl_KM_DangHoatDong.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_KM_DangHoatDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_KM_DangHoatDongMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_KM_DangHoatDong);

        QL_Table.addTab("Khuyến Mãi Đang Hoạt Động", jScrollPane2);

        tbl_KM_KhongHoatDong.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_KM_KhongHoatDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_KM_KhongHoatDongMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_KM_KhongHoatDong);

        QL_Table.addTab("Khuyến Mãi Không Hoạt Động", jScrollPane4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(QL_Table)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(QL_Table, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
        );

        NhapThongTin_Panel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nhập Thông Tin Khuyến Mãi"));

        jLabel1.setText("Mã KM:");

        txt_Ma_KM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Tên KM:");

        txt_Ten_KM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Hình Thức:");

        jLabel4.setText("Vui Lòng Nhập:");

        txt_GiaTri_KM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setText("Giá Trị :");

        jLabel6.setText("Ngày Bắt Đầu:");

        jLabel7.setText("Ngày Kết Thúc:");

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

        txt_NgayBatDau_KM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_NgayBatDau_KM.setDateFormatString("yyyy-MM-dd");

        txt_NgayKetThuc_KM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_NgayKetThuc_KM.setDateFormatString("yyyy-MM-dd");

        txt_DiemTichLuy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_TienMat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cbox_HinhThuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "null", "Điểm Tích Luỹ", "Tiền Mặt" }));
        cbox_HinhThuc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
                                .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_Ten_KM, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_Ma_KM)
                                    .addComponent(cbox_HinhThuc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_GiaTri_KM)
                                    .addComponent(txt_NgayTrongThang_KM)
                                    .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                                        .addComponent(txt_DiemTichLuy)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_TienMat, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(26, 26, 26))
                    .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdo_KhongHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdo_HoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)))
                .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_DieuKien_KM)
                    .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                        .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(txt_NgayBatDau_KM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_NgayKetThuc_KM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        NhapThongTin_PanelLayout.setVerticalGroup(
            NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                        .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NhapThongTin_PanelLayout.createSequentialGroup()
                                .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_Ma_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_Ten_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbox_HinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21))
                            .addGroup(NhapThongTin_PanelLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jLabel2)
                                .addGap(19, 19, 19)))
                        .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_DiemTichLuy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_TienMat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(NhapThongTin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_GiaTri_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_NgayBatDau_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_NgayKetThuc_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_DieuKien_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        btn_DongTrang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Dong_Trang.png"))); // NOI18N
        btn_DongTrang.setText("Đóng Trang");
        btn_DongTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DongTrangActionPerformed(evt);
            }
        });

        btn_HuongDanSuDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/HuongDan_Đuung.png"))); // NOI18N
        btn_HuongDanSuDung.setText("Hướng Dẫn Sử Dụng");
        btn_HuongDanSuDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuongDanSuDungActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_DongTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(NhapThongTin_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_HuongDanSuDung, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(NhapThongTin_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_HuongDanSuDung)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(16, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_DongTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_LamMoiKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiKMActionPerformed
        // TODO add your handling code here:
        LamMoi_KM();
        FillToTable();
        FillToTable_KM01();
        FillToTable_KM02();
    }//GEN-LAST:event_btn_LamMoiKMActionPerformed

    private void btn_ThemKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemKMActionPerformed
        // TODO add your handling code here:
        Them_KM();
        FillToTable();
    }//GEN-LAST:event_btn_ThemKMActionPerformed

    private void btn_SuaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaKMActionPerformed
        // TODO add your handling code here:
        Sua_KM();
        FillToTable();
    }//GEN-LAST:event_btn_SuaKMActionPerformed

    private void btn_XoaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaKMActionPerformed
        // TODO add your handling code here:
        Xoa_KM();
        FillToTable();
    }//GEN-LAST:event_btn_XoaKMActionPerformed

    private void btn_DongTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DongTrangActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btn_DongTrangActionPerformed

    private void btn_HuongDanSuDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuongDanSuDungActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btn_HuongDanSuDungActionPerformed

    private void tbl_KhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_KhuyenMaiMouseClicked
        // TODO add your handling code here:
        ShowDetail();
    }//GEN-LAST:event_tbl_KhuyenMaiMouseClicked

    private void tbl_KM_DangHoatDongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_KM_DangHoatDongMouseClicked
        // TODO add your handling code here:
        ShowDetail();
    }//GEN-LAST:event_tbl_KM_DangHoatDongMouseClicked

    private void tbl_KM_KhongHoatDongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_KM_KhongHoatDongMouseClicked
        // TODO add your handling code here:
        ShowDetail();
    }//GEN-LAST:event_tbl_KM_KhongHoatDongMouseClicked

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
            java.util.logging.Logger.getLogger(QL_KhuyenMai_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QL_KhuyenMai_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QL_KhuyenMai_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QL_KhuyenMai_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QL_KhuyenMai_JFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel NhapThongTin_Panel;
    private javax.swing.JTabbedPane QL_Table;
    private javax.swing.ButtonGroup btg_TrangThai;
    private javax.swing.JButton btn_DongTrang;
    private javax.swing.JButton btn_HuongDanSuDung;
    private javax.swing.JButton btn_LamMoiKM;
    private javax.swing.JButton btn_SuaKM;
    private javax.swing.JButton btn_ThemKM;
    private javax.swing.JButton btn_XoaKM;
    private javax.swing.JComboBox<String> cbox_HinhThuc;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JRadioButton rdo_HoatDong;
    private javax.swing.JRadioButton rdo_KhongHoatDong;
    private javax.swing.JTable tbl_KM_DangHoatDong;
    private javax.swing.JTable tbl_KM_KhongHoatDong;
    private javax.swing.JTable tbl_KhuyenMai;
    private javax.swing.JSpinner txt_DiemTichLuy;
    private javax.swing.JTextField txt_DieuKien_KM;
    private javax.swing.JTextField txt_GiaTri_KM;
    private javax.swing.JTextField txt_Ma_KM;
    private javax.swing.JTextArea txt_MoTa_KM;
    private com.toedter.calendar.JDateChooser txt_NgayBatDau_KM;
    private com.toedter.calendar.JDateChooser txt_NgayKetThuc_KM;
    private javax.swing.JTextField txt_NgayTrongThang_KM;
    private javax.swing.JTextField txt_Ten_KM;
    private javax.swing.JTextField txt_TienMat;
    // End of variables declaration//GEN-END:variables
}
