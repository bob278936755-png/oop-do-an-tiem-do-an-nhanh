package rolea;

public class DanhMuc {
    private final String ma;
    private String ten;

    public DanhMuc(String ma, String ten) {
        this.ma = ma;
        setTen(ten);
    }

    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
