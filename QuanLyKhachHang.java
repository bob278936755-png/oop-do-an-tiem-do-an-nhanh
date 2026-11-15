import java.util.Scanner;

/** Lớp quản lý danh sách khách hàng: hiển thị, thêm, xác định khách khi lập hóa đơn. */
public class QuanLyKhachHang {
    public static final String FILE = "khachhang.txt";

    private final DanhSachKhachHang dsKH = new DanhSachKhachHang();
    private final Scanner sc;

    public QuanLyKhachHang(Scanner sc) {
        this.sc = sc;
        dsKH.docFile(FILE);
    }

    public DanhSachKhachHang getDanhSach() { return dsKH; }

    public void hienThiDanhSach() {
        System.out.println("=== DANH SACH KHACH HANG ===");
        dsKH.hienThiTatCa();
    }

    public void themKhachHang() {
        System.out.println("=== THEM KHACH HANG ===");
        System.out.print("Ho ten: ");
        String ten = sc.nextLine().trim();
        System.out.print("So dien thoai: ");
        String sdt = sc.nextLine().trim();
        if (ten.isEmpty() || !sdt.matches("\\d{9,11}")) {
            System.out.println("Ten khong duoc rong, SDT phai gom 9-11 chu so!");
            return;
        }
        System.out.print("Loai khach (1 = Thuong, 2 = Thanh vien): ");
        String loai = sc.nextLine().trim();
        String ma = dsKH.taoMaMoi();

        KhachHang k;
        if (loai.equals("2")) {
            System.out.print("Ngay dang ky (dd/MM/yyyy): ");
            String ngay = sc.nextLine().trim();
            k = new ThanhVien(ma, ten, sdt, 0, ngay);   // đa hình: gán lớp con vào biến lớp cha
        } else {
            k = new KhachThuong(ma, ten, sdt);
        }

        if (dsKH.them(k)) {
            dsKH.ghiFile(FILE);
            System.out.println("Da them: " + ma);
        } else {
            System.out.println("Them that bai: trung ma hoac trung SDT!");
        }
    }

    /**
     * QUY ƯỚC VỚI D (lập hóa đơn):
     * - Nhận vào chuỗi nhập (SĐT hoặc mã KH).
     * - Tìm thấy  -> trả về KhachHang (KhachThuong hoặc ThanhVien, D dùng đa hình getTyLeGiam()).
     * - Chuỗi rỗng / không tìm thấy -> trả về null = KHÁCH LẺ (không lưu, giảm 0%).
     */
    public KhachHang xacDinhKhachHang(String thongTin) {
        if (thongTin == null || thongTin.trim().isEmpty()) return null;
        thongTin = thongTin.trim();
        KhachHang k = dsKH.timTheoSdt(thongTin);
        if (k == null) k = dsKH.timTheoMa(thongTin);
        return k;
    }

    /** Dùng thử chức năng xác định khách. */
    public void timKhachDeLapHoaDon() {
        System.out.print("Nhap SDT hoac ma KH (de trong = khach le): ");
        KhachHang k = xacDinhKhachHang(sc.nextLine());
        if (k == null) {
            System.out.println("-> Khach le (khong giam gia).");
        } else {
            k.hienThi();
        }
    }
}
