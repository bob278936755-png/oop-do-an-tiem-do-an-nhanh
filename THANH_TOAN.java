package DO_AN_OOP;

public class THANH_TOAN {
    private HOA_DON hoaDon;
    private String phuongThuc;
    private boolean daThanhToan;

    public THANH_TOAN(HOA_DON hoaDon, String phuongThuc) {
        this.hoaDon = hoaDon;
        this.phuongThuc = phuongThuc;
        this.daThanhToan = false;
    }

    public boolean thanhToan() {
        if (hoaDon.tinhThanhTien() >= 0) {
            daThanhToan = true;
        }
        return daThanhToan;
    }

    public String hienThiHoaDon() {
        return "========== HOA DON ==========\n"
            + "Ma hoa don: " + hoaDon.getMaHoaDon() + "\n"
            + "Khach hang: " + hoaDon.getTenKhachHang() + "\n"
            + "San pham: " + hoaDon.getTenSanPham() + "\n"
            + "Don gia: " + String.format("%,.0f", hoaDon.getDonGia()) + " VND\n"
            + "So luong: " + hoaDon.getSoLuong() + "\n"
            + "Tong tien: " + String.format("%,.0f", hoaDon.tinhTongTien()) + " VND\n"
            + "Giam gia: " + hoaDon.getGiamGia() + "%\n"
            + "Tien giam: " + String.format("%,.0f", hoaDon.tinhTienGiam()) + " VND\n"
            + "Thanh tien: " + String.format("%,.0f", hoaDon.tinhThanhTien()) + " VND\n"
            + "Phuong thuc: " + phuongThuc + "\n"
            + "Trang thai: " + (daThanhToan ? "Da thanh toan" : "Chua thanh toan")
            + "\n=============================";
    }
}

