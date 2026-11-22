package rolea;

public class SanPham {
    private final String ma;
    private String ten;
    private long giaBan;
    private DanhMuc danhMuc;

    public SanPham(String ma, String ten, long giaBan, DanhMuc danhMuc) {
        if (ma == null || ma.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã sản phẩm không được để trống.");
        }
        this.ma = ma.trim();
        setTen(ten);
        setGiaBan(giaBan);
        setDanhMuc(danhMuc);
    }

    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        if (ten == null || ten.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên sản phẩm không được để trống.");
        }
        this.ten = ten.trim();
    }

    public long getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(long giaBan) {
        if (giaBan < 0) {
            throw new IllegalArgumentException("Giá bán không được âm.");
        }
        this.giaBan = giaBan;
    }

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        if (danhMuc == null) {
            throw new IllegalArgumentException("Danh mục không hợp lệ.");
        }
        this.danhMuc = danhMuc;
    }

    @Override
    public String toString() {
        return ma + " - " + ten;
    }
}
