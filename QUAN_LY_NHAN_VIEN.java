package DO_AN_OOP;

import java.util.ArrayList;

public class QUAN_LY_NHAN_VIEN {

    private final ArrayList<NHAN_VIEN> danhSach = new ArrayList<>();

    // Them nhan vien
    public boolean themNhanVien(NHAN_VIEN nv) {
        if (nv == null || nv.getMaNhanVien() == null
                || nv.getMaNhanVien().isBlank()
                || timNhanVien(nv.getMaNhanVien()) != null) {
            return false;
        }

        danhSach.add(nv);
        return true;
    }

    // Tim nhan vien theo ma
    public NHAN_VIEN timNhanVien(String maNhanVien) {
        if (maNhanVien == null) {
            return null;
        }

        for (NHAN_VIEN nv : danhSach) {
            if (nv.getMaNhanVien().equalsIgnoreCase(maNhanVien.trim())) {
                return nv;
            }
        }

        return null;
    }

    // Sua thong tin nhan vien
    public boolean suaNhanVien(String maNhanVien,
                               String hoTen,
                               String soDienThoai,
                               String chucVu,
                               double luong) {
        NHAN_VIEN nv = timNhanVien(maNhanVien);

        if (nv == null || luong < 0) {
            return false;
        }

        nv.capNhatThongTin(hoTen, soDienThoai, chucVu, luong);
        return true;
    }

    // Xoa nhan vien
    public boolean xoaNhanVien(String maNhanVien) {
        NHAN_VIEN nv = timNhanVien(maNhanVien);

        if (nv == null) {
            return false;
        }

        danhSach.remove(nv);
        return true;
    }

    // Hien thi danh sach
    public void hienThiDanhSach() {
        if (danhSach.isEmpty()) {
            System.out.println("Danh sach nhan vien trong!");
            return;
        }

        for (NHAN_VIEN nv : danhSach) {
            nv.hienThiThongTin();
            System.out.println("--------------------");
        }
    }

    public int demNhanVien() {
        return danhSach.size();
    }
}

