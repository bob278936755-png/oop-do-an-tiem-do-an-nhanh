import java.io.*;

/** Lớp danh sách khuyến mãi - dùng mảng thường. */
public class DanhSachKhuyenMai {
    private KhuyenMai[] ds = new KhuyenMai[10];
    private int n = 0;

    public int getSoLuong() { return n; }

    public boolean them(KhuyenMai km) {
        if (km == null || timTheoMa(km.getMaKM()) != null) return false;
        if (n == ds.length) {
            KhuyenMai[] moi = new KhuyenMai[ds.length * 2];
            System.arraycopy(ds, 0, moi, 0, n);
            ds = moi;
        }
        ds[n++] = km;
        return true;
    }

    public KhuyenMai timTheoMa(String ma) {
        for (int i = 0; i < n; i++)
            if (ds[i].getMaKM().equalsIgnoreCase(ma)) return ds[i];
        return null;
    }

    /** Khuyến mãi có % giảm cao nhất áp dụng được cho khách. */
    public KhuyenMai timTotNhatCho(KhachHang khach) {
        KhuyenMai tot = null;
        for (int i = 0; i < n; i++)
            if (ds[i].apDungCho(khach) && (tot == null || ds[i].getPhanTramGiam() > tot.getPhanTramGiam()))
                tot = ds[i];
        return tot;
    }

    public void hienThiTatCa() {
        if (n == 0) { System.out.println("(Danh sach trong)"); return; }
        for (int i = 0; i < n; i++) ds[i].hienThi();
    }

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
                    if (p.length >= 6)
                        them(new KhuyenMai(p[0], p[1], Double.parseDouble(p[2]), p[3], p[4], p[5]));
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
            pw.println("# MA|TEN|PHANTRAM|DOITUONG|NGAYBD|NGAYKT");
            for (int i = 0; i < n; i++) pw.println(ds[i].toFileString());
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }
}
