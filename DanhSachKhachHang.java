import java.io.*;

/** Lớp danh sách khách hàng - chỉ dùng mảng thường (không ArrayList). */
public class DanhSachKhachHang {
    private KhachHang[] ds = new KhachHang[10];
    private int n = 0;

    public int getSoLuong() { return n; }
    public KhachHang get(int i) { return (i >= 0 && i < n) ? ds[i] : null; }

    private void moRong() {
        KhachHang[] moi = new KhachHang[ds.length * 2];
        System.arraycopy(ds, 0, moi, 0, n);
        ds = moi;
    }

    /** Thêm khách; trả về false nếu trùng mã hoặc trùng SĐT. */
    public boolean them(KhachHang k) {
        if (k == null || timTheoMa(k.getMaKH()) != null || timTheoSdt(k.getSdt()) != null) return false;
        if (n == ds.length) moRong();
        ds[n++] = k;
        return true;
    }

    public KhachHang timTheoMa(String ma) {
        for (int i = 0; i < n; i++)
            if (ds[i].getMaKH().equalsIgnoreCase(ma)) return ds[i];
        return null;
    }

    public KhachHang timTheoSdt(String sdt) {
        for (int i = 0; i < n; i++)
            if (ds[i].getSdt().equals(sdt)) return ds[i];
        return null;
    }

    /** Sinh mã mới dạng KH001, KH002... */
    public String taoMaMoi() {
        int max = 0;
        for (int i = 0; i < n; i++) {
            try {
                int so = Integer.parseInt(ds[i].getMaKH().substring(2));
                if (so > max) max = so;
            } catch (Exception e) { /* bỏ qua mã không đúng định dạng */ }
        }
        return String.format("KH%03d", max + 1);
    }

    public void hienThiTatCa() {
        if (n == 0) { System.out.println("(Danh sach trong)"); return; }
        System.out.printf("%-7s | %-22s | %-12s | %-10s | Giam gia%n", "Ma", "Ten", "SDT", "Loai");
        System.out.println("-".repeat(75));
        for (int i = 0; i < n; i++) ds[i].hienThi();   // đa hình
        System.out.println("-".repeat(75));
        System.out.println("Tong: " + n + " khach hang");
    }

    // ---------- File text ----------
    public void docFile(String tenFile) {
        File f = new File(tenFile);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(f), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] p = line.split("\\|");
                try {
                    if (p[0].equals("THUONG") && p.length >= 4)
                        them(new KhachThuong(p[1], p[2], p[3]));
                    else if (p[0].equals("THANHVIEN") && p.length >= 6)
                        them(new ThanhVien(p[1], p[2], p[3], Integer.parseInt(p[4]), p[5]));
                } catch (NumberFormatException e) {
                    System.out.println("Bo qua dong loi: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }

    public void ghiFile(String tenFile) {
        try (PrintWriter pw = new PrintWriter(
                new OutputStreamWriter(new FileOutputStream(tenFile), "UTF-8"))) {
            pw.println("# LOAI|MA|TEN|SDT[|DIEM|NGAYDANGKY]");
            for (int i = 0; i < n; i++) pw.println(ds[i].toFileString());
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }
}
