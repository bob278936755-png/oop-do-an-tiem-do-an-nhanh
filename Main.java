import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, "UTF-8");
        QuanLyKhachHang qlKH = new QuanLyKhachHang(sc);
        QuanLyKhuyenMai qlKM = new QuanLyKhuyenMai(sc);

        int chon;
        do {
            System.out.println("\n===== QUAN LY KHACH HANG - THANH VIEN - KHUYEN MAI =====");
            System.out.println("1. Hien thi danh sach khach hang");
            System.out.println("2. Them khach hang");
            System.out.println("3. Hien thi danh sach khuyen mai");
            System.out.println("4. Them khuyen mai");
            System.out.println("5. Xac dinh khach hang khi lap hoa don (thu)");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            try {
                chon = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                chon = -1;
            }
            switch (chon) {
                case 1: qlKH.hienThiDanhSach(); break;
                case 2: qlKH.themKhachHang(); break;
                case 3: qlKM.hienThiDanhSach(); break;
                case 4: qlKM.themKhuyenMai(); break;
                case 5: qlKH.timKhachDeLapHoaDon(); break;
                case 0: System.out.println("Tong khach hang da tao trong phien: " + KhachHang.getDem()); break;
                default: System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }
}
