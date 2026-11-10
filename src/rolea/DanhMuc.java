package rolea;

public class DanhMuc {
    private final String ma;
    private String ten;

    public DanhMuc(String ma, String ten) {
        if (ma == null || ma.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã danh mục không được trống.");
        }
        this.ma = ma.trim();
        setTen(ten);
    }

    public String getMa() { return ma; }
    public String getTen() { return ten; }

    public void setTen(String ten) {
        if (ten == null || ten.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên danh mục không được trống.");
        }
        this.ten = ten.trim();
    }

    @Override
    public String toString() { return ma + " - " + ten; }
}
