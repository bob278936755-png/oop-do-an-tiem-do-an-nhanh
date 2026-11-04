package rolea;

import java.util.Scanner;

public class QuanLySanPham {
    private static final DanhMuc[] DANH_MUC = {
        new DanhMuc("DM01", "Món ăn"),
        new DanhMuc("DM02", "Đồ uống")
    };
    private static final DanhSachSanPham DANH_SACH = new DanhSachSanPham();

    static {
        DANH_SACH.them(new SanPham("SP01", "Burger gà", 35000, DANH_MUC[0]));
        DANH_SACH.them(new SanPham("SP02", "Khoai tây chiên", 22000, DANH_MUC[0]));
        DANH_SACH.them(new SanPham("SP03", "Trà đào", 25000, DANH_MUC[1]));
    }

    public static SanPham timTheoMa(String ma) {
        return DANH_SACH.timTheoMa(ma);
    }

    public static void menu(Scanner scanner) {
        while (true) {
            System.out.println("\n1. Xem danh mục\n2. Xem sản phẩm\n3. Thêm sản phẩm\n0. Quay lại");
            System.out.print("Chọn: ");
            switch (scanner.nextLine().trim()) {
                case "1":
                    hienThiDanhMuc();
                    break;
                case "2":
                    DANH_SACH.hienThi();
                    break;
                case "3":
                    themSanPham(scanner);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void hienThiDanhMuc() {
        for (int i = 0; i < DANH_MUC.length; i++) {
            System.out.printf("%d. %s - %s%n", i + 1, DANH_MUC[i].getMa(), DANH_MUC[i].getTen());
        }
    }

    private static void themSanPham(Scanner scanner) {
        System.out.print("Mã sản phẩm: ");
        String ma = scanner.nextLine();
        System.out.print("Tên sản phẩm: ");
        String ten = scanner.nextLine();
        try {
            System.out.print("Giá bán (đ): ");
            long giaBan = Long.parseLong(scanner.nextLine().trim());
            hienThiDanhMuc();
            System.out.print("Chọn số thứ tự danh mục: ");
            int viTri = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (viTri < 0 || viTri >= DANH_MUC.length) {
                System.out.println("Danh mục không hợp lệ.");
                return;
            }
            DANH_SACH.them(new SanPham(ma, ten, giaBan, DANH_MUC[viTri]));
            System.out.println("Đã thêm sản phẩm.");
        } catch (NumberFormatException e) {
            System.out.println("Giá bán và lựa chọn danh mục phải là số nguyên.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
