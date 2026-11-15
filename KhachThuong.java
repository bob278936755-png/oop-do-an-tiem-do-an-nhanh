/** Khách thường: không có thẻ thành viên, không được giảm giá theo hạng. */
public class KhachThuong extends KhachHang {

    public KhachThuong(String maKH, String tenKH, String sdt) {
        super(maKH, tenKH, sdt);
    }

    @Override
    public String getLoai() { return "THUONG"; }

    @Override
    public double getTyLeGiam() { return 0; }

    @Override
    public String toFileString() {
        return "THUONG|" + maKH + "|" + tenKH + "|" + sdt;
    }
}
