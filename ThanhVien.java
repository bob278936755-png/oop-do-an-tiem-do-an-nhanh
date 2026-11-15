/** Khách có thẻ thành viên: tích điểm, có hạng, được giảm giá theo hạng. */
public class ThanhVien extends KhachHang {
    private int diem;
    private String ngayDangKy;   // dd/MM/yyyy

    public ThanhVien(String maKH, String tenKH, String sdt, int diem, String ngayDangKy) {
        super(maKH, tenKH, sdt);
        this.diem = diem;
        this.ngayDangKy = ngayDangKy;
    }

    public int getDiem() { return diem; }
    public String getNgayDangKy() { return ngayDangKy; }
    public void congDiem(int d) { if (d > 0) diem += d; }

    /** Hạng tính tự động từ điểm tích lũy. */
    public String getHang() {
        if (diem >= 1000) return "KIMCUONG";
        if (diem >= 500) return "VANG";
        if (diem >= 100) return "BAC";
        return "DONG";
    }

    @Override
    public String getLoai() { return "THANHVIEN"; }

    @Override
    public double getTyLeGiam() {
        switch (getHang()) {
            case "KIMCUONG": return 12;
            case "VANG": return 8;
            case "BAC": return 5;
            default: return 3;
        }
    }

    @Override
    public String toFileString() {
        return "THANHVIEN|" + maKH + "|" + tenKH + "|" + sdt + "|" + diem + "|" + ngayDangKy;
    }

    @Override
    public void hienThi() {
        super.hienThi();
        System.out.printf("          -> Hang %-8s | %d diem | dang ky %s%n", getHang(), diem, ngayDangKy);
    }
}
