package rolea;

public class DanhSachSanPham {
    private final SanPham[] danhSach = new SanPham[100];
    private int soLuong;

    public void them(SanPham sanPham) {
        if (timTheoMa(sanPham.getMa()) != null) {
            throw new IllegalArgumentException("Mã sản phẩm đã tồn tại.");
        }
        if (soLuong == danhSach.length) {
            throw new IllegalStateException("Danh sách sản phẩm đã đầy.");
        }
        danhSach[soLuong++] = sanPham;
    }

    public SanPham timTheoMa(String ma) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMa().equalsIgnoreCase(ma)) {
                return danhSach[i];
            }
        }
        return null;
    }

    public void hienThi() {
        System.out.printf("%-10s %-24s %12s  %s%n", "Mã", "Tên sản phẩm", "Giá (đ)", "Danh mục");
        for (int i = 0; i < soLuong; i++) {
            SanPham sp = danhSach[i];
            System.out.printf("%-10s %-24s %12d  %s%n",
                    sp.getMa(), sp.getTen(), sp.getGiaBan(), sp.getDanhMuc().getTen());
        }
    }
}
