package DO_AN_OOP; 
import java.awt.*;
import javax.swing.*;
import rolea.*;

public class QUAN_LY_HOA_DON extends JFrame {

    JTextField txtMa = new JTextField();
    JTextField txtKhach = new JTextField();
    JComboBox<SanPham> cboSanPham = new JComboBox<>();
    JTextField txtGia = new JTextField();
    JTextField txtSoLuong = new JTextField();
    JTextField txtGiamGia = new JTextField();

    JComboBox<String> cboThanhToan = new JComboBox<>(
        new String[]{"Tien mat", "Chuyen khoan"}
    );

    JTextArea txtKetQua = new JTextArea();

    HOA_DON hoaDon;
    THANH_TOAN thanhToan;

    public QUAN_LY_HOA_DON() {
        setTitle("QUAN LY HOA DON & THANH TOAN");
        setSize(650, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("Ma hoa don:"));
        panel.add(txtMa);

        panel.add(new JLabel("Ten khach hang:"));
        panel.add(txtKhach);

        panel.add(new JLabel("Ten san pham:"));
        panel.add(cboSanPham);
        txtGia.setEditable(false);

        panel.add(new JLabel("Don gia:"));
        panel.add(txtGia);

        panel.add(new JLabel("So luong:"));
        panel.add(txtSoLuong);

        panel.add(new JLabel("Giam gia (%):"));
        panel.add(txtGiamGia);

        panel.add(new JLabel("Phuong thuc thanh toan:"));
        panel.add(cboThanhToan);

        JButton btnTinh = new JButton("Tinh tien");
        JButton btnThanhToan = new JButton("Thanh toan");
        JButton btnMoi = new JButton("Lam moi");

        JPanel panelNut = new JPanel();
        panelNut.add(btnTinh);
        panelNut.add(btnThanhToan);
        panelNut.add(btnMoi);

        txtKetQua.setEditable(false);
        txtKetQua.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JPanel tren = new JPanel(new BorderLayout());
        tren.add(panel, BorderLayout.CENTER);
        tren.add(panelNut, BorderLayout.SOUTH);

        add(tren, BorderLayout.NORTH);
        add(new JScrollPane(txtKetQua), BorderLayout.CENTER);

        cboSanPham.addActionListener(e -> capNhatGia());
        cboSanPham.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) { taiSanPham(); }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {}
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {}
        });
        JButton btnSanPham = new JButton("Quản lý sản phẩm");
        panelNut.add(btnSanPham);
        btnSanPham.addActionListener(e -> new ManHinhSanPham().setVisible(true));
        taiSanPham();
        btnTinh.addActionListener(e -> tinhTien());
        btnThanhToan.addActionListener(e -> xuLyThanhToan());
        btnMoi.addActionListener(e -> lamMoi());
    }

    private void taiSanPham() {
        SanPham cu = (SanPham) cboSanPham.getSelectedItem();
        cboSanPham.setModel(new DefaultComboBoxModel<>(QuanLySanPham.getSanPham()));
        if (cu != null && QuanLySanPham.timTheoMa(cu.getMa()) != null) {
            cboSanPham.setSelectedItem(QuanLySanPham.timTheoMa(cu.getMa()));
        }
        capNhatGia();
    }

    private void capNhatGia() {
        SanPham sp = (SanPham) cboSanPham.getSelectedItem();
        txtGia.setText(sp == null ? "" : Long.toString(sp.getGiaBan()));
    }

    public void tinhTien() {
        try {
            String ma = txtMa.getText().trim();
            String khach = txtKhach.getText().trim();
            SanPham chon = (SanPham) cboSanPham.getSelectedItem();
            SanPham sp = chon == null ? null : QuanLySanPham.timTheoMa(chon.getMa());
            if (sp == null) {
                JOptionPane.showMessageDialog(this, "Chọn sản phẩm còn tồn tại.");
                taiSanPham();
                return;
            }
            String sanPham = sp.getTen();

            double gia = sp.getGiaBan();
            capNhatGia();
            int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
            double giam = Double.parseDouble(txtGiamGia.getText().trim());

            if (ma.isEmpty() || khach.isEmpty() || sanPham.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui long nhap day du thong tin!");
                return;
            }

            if (!Double.isFinite(gia) || !Double.isFinite(giam)
                    || gia < 0 || soLuong <= 0 || giam < 0 || giam > 100
                    || !Double.isFinite(gia * soLuong)) {
                JOptionPane.showMessageDialog(this, "Gia, so luong hoac giam gia khong hop le!");
                return;
            }

            hoaDon = new HOA_DON(ma, khach, sanPham, gia, soLuong, giam);

            thanhToan = new THANH_TOAN(
                hoaDon, cboThanhToan.getSelectedItem().toString()
            );

            txtKetQua.setText(thanhToan.hienThiHoaDon());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui long nhap dung dinh dang so!");
        }
    }

    public void xuLyThanhToan() {
        if (hoaDon == null) {
            JOptionPane.showMessageDialog(this, "Vui long tinh tien truoc!");
            return;
        }

        if (thanhToan.thanhToan()) {
            txtKetQua.setText(thanhToan.hienThiHoaDon());
            JOptionPane.showMessageDialog(this, "Thanh toan thanh cong!");
        }
    }

    public void lamMoi() {
        txtMa.setText("");
        txtKhach.setText("");
        taiSanPham();
        capNhatGia();
        txtSoLuong.setText("");
        txtGiamGia.setText("");
        txtKetQua.setText("");
        cboThanhToan.setSelectedIndex(0);

        hoaDon = null;
        thanhToan = null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new QUAN_LY_HOA_DON().setVisible(true);
        });
    }
}
