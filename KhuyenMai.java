/** Chương trình khuyến mãi. doiTuong: TAT_CA hoặc THANH_VIEN. */
public class KhuyenMai implements IHienThi {
    private String maKM;
    private String tenKM;
    private double phanTramGiam;
    private String doiTuong;
    private String ngayBD;   // dd/MM/yyyy
    private String ngayKT;

    public KhuyenMai(String maKM, String tenKM, double phanTramGiam,
                     String doiTuong, String ngayBD, String ngayKT) {
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.phanTramGiam = phanTramGiam;
        this.doiTuong = doiTuong;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
    }

    public String getMaKM() { return maKM; }
    public String getTenKM() { return tenKM; }
    public double getPhanTramGiam() { return phanTramGiam; }
    public String getDoiTuong() { return doiTuong; }

    /** Khách có được áp dụng khuyến mãi này không? (khach = null: khách lẻ) */
    public boolean apDungCho(KhachHang khach) {
        if (doiTuong.equals("TAT_CA")) return true;
        return khach instanceof ThanhVien;
    }

    public String toFileString() {
        return maKM + "|" + tenKM + "|" + phanTramGiam + "|" + doiTuong + "|" + ngayBD + "|" + ngayKT;
    }

    @Override
    public void hienThi() {
        System.out.printf("%-6s | %-26s | %5.1f%% | %-10s | %s - %s%n",
                maKM, tenKM, phanTramGiam, doiTuong, ngayBD, ngayKT);
    }
}
