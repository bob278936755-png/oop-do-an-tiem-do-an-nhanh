package DO_AN_OOP;
public class HOA_DON {
    private String maHoaDon;
    private String tenKhachHang;
    private String tenSanPham;
    private double donGia;
    private int soLuong;
    private double giamGia;

    public HOA_DON(String maHoaDon, String tenKhachHang,
        String tenSanPham, double donGia,
        int soLuong, double giamGia) {
        this.maHoaDon = maHoaDon;
        this.tenKhachHang = tenKhachHang;
        this.tenSanPham = tenSanPham;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.giamGia = giamGia;
    }

    public double tinhTongTien() {
        return donGia * soLuong;
    }

    public double tinhTienGiam() {
        return tinhTongTien() * giamGia / 100;
    }

    public double tinhThanhTien() {
        return tinhTongTien() - tinhTienGiam();
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public double getDonGia() {
        return donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getGiamGia() {
        return giamGia;
    }
}
