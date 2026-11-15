import java.util.Scanner;

/** Lớp quản lý danh sách khuyến mãi. */
public class QuanLyKhuyenMai {
    public static final String FILE = "khuyenmai.txt";

    private final DanhSachKhuyenMai dsKM = new DanhSachKhuyenMai();
    private final Scanner sc;

    public QuanLyKhuyenMai(Scanner sc) {
        this.sc = sc;
        dsKM.docFile(FILE);
    }

    public DanhSachKhuyenMai getDanhSach() { return dsKM; }

    public void hienThiDanhSach() {
        System.out.println("=== DANH SACH KHUYEN MAI ===");
        dsKM.hienThiTatCa();
    }

    public void themKhuyenMai() {
        System.out.println("=== THEM KHUYEN MAI ===");
        try {
            System.out.print("Ma KM: ");
            String ma = sc.nextLine().trim();
            System.out.print("Ten KM: ");
            String ten = sc.nextLine().trim();
            System.out.print("Phan tram giam: ");
            double pt = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Doi tuong (1 = Tat ca, 2 = Thanh vien): ");
            String dt = sc.nextLine().trim().equals("2") ? "THANH_VIEN" : "TAT_CA";
            System.out.print("Ngay bat dau (dd/MM/yyyy): ");
            String bd = sc.nextLine().trim();
            System.out.print("Ngay ket thuc (dd/MM/yyyy): ");
            String kt = sc.nextLine().trim();
            if (ma.isEmpty() || pt <= 0 || pt > 100) { System.out.println("Du lieu khong hop le!"); return; }
            if (dsKM.them(new KhuyenMai(ma, ten, pt, dt, bd, kt))) {
                dsKM.ghiFile(FILE);
                System.out.println("Da them khuyen mai.");
            } else {
                System.out.println("Trung ma khuyen mai!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Phan tram giam phai la so!");
        }
    }

    /** Dùng cho D khi lập hóa đơn: khuyến mãi tốt nhất cho khách (null = khách lẻ). */
    public KhuyenMai khuyenMaiTotNhat(KhachHang khach) {
        return dsKM.timTotNhatCho(khach);
    }
}
