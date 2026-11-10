package rolea;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ManHinhSanPham extends JFrame {
    private final DefaultTableModel bang = new DefaultTableModel(
            new String[]{"Mã", "Tên", "Giá (đ)", "Danh mục"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) { return false; }
    };
    private final JTable danhSach = new JTable(bang);
    private final JTextField ma = new JTextField();
    private final JTextField ten = new JTextField();
    private final JTextField gia = new JTextField();
    private final JComboBox<DanhMuc> danhMuc = new JComboBox<>();

    public ManHinhSanPham() {
        setTitle("Role A - Quản lý sản phẩm và danh mục");
        setSize(820, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel form = new JPanel(new GridLayout(4, 2, 8, 8));
        form.add(new JLabel("Mã sản phẩm"));
        form.add(ma);
        form.add(new JLabel("Tên sản phẩm"));
        form.add(ten);
        form.add(new JLabel("Giá bán (đồng)"));
        form.add(gia);
        form.add(new JLabel("Danh mục"));
        form.add(danhMuc);
        JPanel nut = new JPanel();
        JButton them = new JButton("Thêm");
        JButton sua = new JButton("Sửa");
        JButton xoa = new JButton("Xóa");
        JButton moi = new JButton("Làm mới");
        JButton dm = new JButton("Quản lý danh mục");
        nut.add(them);
        nut.add(sua);
        nut.add(xoa);
        nut.add(moi);
        nut.add(dm);
        JPanel tren = new JPanel(new BorderLayout(8, 8));
        tren.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tren.add(form, BorderLayout.CENTER);
        tren.add(nut, BorderLayout.SOUTH);
        add(tren, BorderLayout.NORTH);
        add(new JScrollPane(danhSach), BorderLayout.CENTER);
        danhSach.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        danhSach.getSelectionModel().addListSelectionListener(e -> {
            int row = danhSach.getSelectedRow();
            if (row < 0) return;
            SanPham sp = QuanLySanPham.timTheoMa(bang.getValueAt(row, 0).toString());
            ma.setText(sp.getMa());
            ma.setEditable(false);
            ten.setText(sp.getTen());
            gia.setText(Long.toString(sp.getGiaBan()));
            danhMuc.setSelectedItem(sp.getDanhMuc());
        });
        them.addActionListener(e -> xuLy(() -> {
            if (!ma.isEditable()) throw new IllegalArgumentException("Bấm Làm mới trước khi thêm.");
            QuanLySanPham.them(new SanPham(ma.getText(), ten.getText(),
                    Long.parseLong(gia.getText().trim()), (DanhMuc) danhMuc.getSelectedItem()));
        }));
        sua.addActionListener(e -> xuLy(() -> {
            yeuCauChon();
            QuanLySanPham.sua(ma.getText(), ten.getText(),
                    Long.parseLong(gia.getText().trim()), (DanhMuc) danhMuc.getSelectedItem());
        }));
        xoa.addActionListener(e -> xuLy(() -> {
            yeuCauChon();
            if (JOptionPane.showConfirmDialog(this, "Xóa sản phẩm " + ma.getText() + "?",
                    "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                QuanLySanPham.xoa(ma.getText());
            }
        }));
        moi.addActionListener(e -> taiLai());
        dm.addActionListener(e -> quanLyDanhMuc());
        taiLai();
    }

    private void yeuCauChon() {
        if (danhSach.getSelectedRow() < 0) {
            throw new IllegalArgumentException("Chọn sản phẩm trong bảng trước.");
        }
    }

    private void xuLy(Runnable thaoTac) {
        try {
            thaoTac.run();
            taiLai();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá phải là số nguyên trong phạm vi long.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void taiLai() {
        danhSach.clearSelection();
        bang.setRowCount(0);
        for (SanPham sp : QuanLySanPham.getSanPham()) {
            bang.addRow(new Object[]{sp.getMa(), sp.getTen(), sp.getGiaBan(), sp.getDanhMuc()});
        }
        danhMuc.setModel(new DefaultComboBoxModel<>(QuanLySanPham.getDanhMuc()));
        ma.setEditable(true);
        ma.setText("");
        ten.setText("");
        gia.setText("");
    }

    private void quanLyDanhMuc() {
        JDialog cuaSo = new JDialog(this, "Danh mục", true);
        DefaultListModel<DanhMuc> model = new DefaultListModel<>();
        for (DanhMuc dm : QuanLySanPham.getDanhMuc()) model.addElement(dm);
        JList<DanhMuc> list = new JList<>(model);
        JPanel nut = new JPanel();
        JButton them = new JButton("Thêm");
        JButton sua = new JButton("Đổi tên");
        JButton xoa = new JButton("Xóa");
        nut.add(them);
        nut.add(sua);
        nut.add(xoa);
        them.addActionListener(e -> {
            String maMoi = JOptionPane.showInputDialog(cuaSo, "Mã danh mục");
            if (maMoi == null) return;
            String tenMoi = JOptionPane.showInputDialog(cuaSo, "Tên danh mục");
            if (tenMoi == null) return;
            try {
                QuanLySanPham.themDanhMuc(maMoi, tenMoi);
                model.addElement(QuanLySanPham.getDanhMuc()[model.size()]);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(cuaSo, ex.getMessage());
            }
        });
        sua.addActionListener(e -> {
            DanhMuc dm = list.getSelectedValue();
            if (dm == null) return;
            String tenMoi = JOptionPane.showInputDialog(cuaSo, "Tên danh mục", dm.getTen());
            if (tenMoi == null) return;
            try {
                dm.setTen(tenMoi);
                list.repaint();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(cuaSo, ex.getMessage());
            }
        });
        xoa.addActionListener(e -> {
            DanhMuc dm = list.getSelectedValue();
            if (dm == null) return;
            if (JOptionPane.showConfirmDialog(cuaSo, "Xóa " + dm + "?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;
            try {
                QuanLySanPham.xoaDanhMuc(dm);
                model.removeElement(dm);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(cuaSo, ex.getMessage());
            }
        });
        cuaSo.add(new JScrollPane(list), BorderLayout.CENTER);
        cuaSo.add(nut, BorderLayout.SOUTH);
        cuaSo.setSize(460, 320);
        cuaSo.setLocationRelativeTo(this);
        cuaSo.setVisible(true);
        taiLai();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManHinhSanPham().setVisible(true));
    }
}
