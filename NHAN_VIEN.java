
package DO_AN_OOP;

public class NHAN_VIEN {
    // Thuoc tinh
    private String maNhanVien;
    private String hoTen;
    private String soDienThoai;
    private String chucVu;
    private double luong;
    private TAI_KHOAN taiKhoan;

    // Constructor
    public NHAN_VIEN(String maNhanVien, String hoTen,
                     String soDienThoai, String chucVu,
                     double luong, TAI_KHOAN taiKhoan) {
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.chucVu = chucVu;
        this.luong = Math.max(0, luong);
        this.taiKhoan = taiKhoan;
    }

    // Getter
    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getChucVu() {
        return chucVu;
    }

    public double getLuong() {
        return luong;
    }

    public TAI_KHOAN getTaiKhoan() {
        return taiKhoan;
    }

    // Setter
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public void setLuong(double luong) {
        if (luong >= 0) {
            this.luong = luong;
        }
    }

    public void setTaiKhoan(TAI_KHOAN taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    // Cap nhat thong tin nhan vien
    public void capNhatThongTin(String hoTen,
                                String soDienThoai,
                                String chucVu, double luong) {
        setHoTen(hoTen);
        setSoDienThoai(soDienThoai);
        setChucVu(chucVu);
        setLuong(luong);
    }

    // Hien thi thong tin
    public void hienThiThongTin() {
        System.out.println("Ma nhan vien: " + maNhanVien);
        System.out.println("Ho ten: " + hoTen);
        System.out.println("So dien thoai: " + soDienThoai);
        System.out.println("Chuc vu: " + chucVu);
        System.out.println("Luong: " + luong);

        if (taiKhoan != null) {
            System.out.println("Tai khoan: "
                    + taiKhoan.getTenDangNhap());
        } else {
            System.out.println("Tai khoan: Chua co");
        }
    }
}
