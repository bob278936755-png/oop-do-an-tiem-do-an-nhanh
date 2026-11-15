/** Abstract class cha của KhachThuong và ThanhVien. */
public abstract class KhachHang implements IHienThi {
    private static int dem = 0;          // biến static: tổng số khách hàng đã tạo

    protected String maKH;
    protected String tenKH;
    protected String sdt;

    public KhachHang(String maKH, String tenKH, String sdt) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.sdt = sdt;
        dem++;
    }

    public static int getDem() { return dem; }

    public String getMaKH() { return maKH; }
    public String getTenKH() { return tenKH; }
    public String getSdt() { return sdt; }
    public void setTenKH(String tenKH) { this.tenKH = tenKH; }
    public void setSdt(String sdt) { this.sdt = sdt; }

    // Các phương thức trừu tượng - lớp con bắt buộc cài đặt (đa hình)
    public abstract String getLoai();
    public abstract double getTyLeGiam();      // % giảm theo loại khách
    public abstract String toFileString();     // dòng ghi vào file text

    public double tinhTienSauGiam(double tongTien) {
        return tongTien * (1 - getTyLeGiam() / 100.0);
    }

    @Override
    public void hienThi() {
        System.out.printf("%-7s | %-22s | %-12s | %-10s | giam %4.1f%%%n",
                maKH, tenKH, sdt, getLoai(), getTyLeGiam());
    }
}
