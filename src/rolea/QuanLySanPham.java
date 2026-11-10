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

    private static final java.util.ArrayList<DanhMuc> danhMuc =
            new java.util.ArrayList<>(java.util.Arrays.asList(DANH_MUC));

    public static DanhMuc[] getDanhMuc() {
        return danhMuc.toArray(new DanhMuc[0]);
    }

    public static SanPham[] getSanPham() {
        return DANH_SACH.getDanhSach();
    }

    public static void them(SanPham sp) { DANH_SACH.them(sp); }

    public static void sua(String ma, String ten, long gia, DanhMuc dm) {
        DANH_SACH.sua(ma, ten, gia, dm);
    }

    public static void xoa(String ma) { DANH_SACH.xoa(ma); }

    public static void themDanhMuc(String ma, String ten) {
        DanhMuc moi = new DanhMuc(ma, ten);
        for (DanhMuc dm : danhMuc) {
            if (dm.getMa().equalsIgnoreCase(moi.getMa())) {
                throw new IllegalArgumentException("Mã danh mục đã tồn tại.");
            }
        }
        danhMuc.add(moi);
    }

    public static void xoaDanhMuc(DanhMuc dm) {
        for (SanPham sp : getSanPham()) {
            if (sp.getDanhMuc() == dm) {
                throw new IllegalArgumentException("Danh mục đang có sản phẩm.");
            }
        }
        danhMuc.remove(dm);
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
        for (int i = 0; i < danhMuc.size(); i++) {
            System.out.printf("%d. %s - %s%n", i + 1, danhMuc.get(i).getMa(), danhMuc.get(i).getTen());
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
            if (viTri < 0 || viTri >= danhMuc.size()) {
                System.out.println("Danh mục không hợp lệ.");
                return;
            }
            DANH_SACH.them(new SanPham(ma, ten, giaBan, danhMuc.get(viTri)));
            System.out.println("Đã thêm sản phẩm.");
        } catch (NumberFormatException e) {
            System.out.println("Giá bán và lựa chọn danh mục phải là số nguyên.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
