package DO_AN_OOP;

    public class TAI_KHOAN {
        // Thuoc tinh
        private String maTaiKhoan;
        private String tenDangNhap;
        private String matKhau;
        private String vaiTro;
        private boolean hoatDong;

        // Constructor
        public TAI_KHOAN(String maTaiKhoan, String tenDangNhap,
                         String matKhau, String vaiTro) {
            this.maTaiKhoan = maTaiKhoan;
            this.tenDangNhap = tenDangNhap;
            this.matKhau = matKhau;
            this.vaiTro = vaiTro;
            this.hoatDong = true;
        }

        // Getter va Setter
        public String getMaTaiKhoan() {
            return maTaiKhoan;
        }

        public String getTenDangNhap() {
            return tenDangNhap;
        }

        public void setTenDangNhap(String tenDangNhap) {
            this.tenDangNhap = tenDangNhap;
        }

        public String getVaiTro() {
            return vaiTro;
        }

        public void setVaiTro(String vaiTro) {
            this.vaiTro = vaiTro;
        }

        public boolean isHoatDong() {
            return hoatDong;
        }

        public void setHoatDong(boolean hoatDong) {
            this.hoatDong = hoatDong;
        }

        // Kiem tra dang nhap
        public boolean dangNhap(String tenDangNhap, String matKhau) {
            return hoatDong
                    && this.tenDangNhap.equals(tenDangNhap)
                    && this.matKhau.equals(matKhau);
        }

        // Doi mat khau
        public boolean doiMatKhau(String matKhauCu, String matKhauMoi) {
            if (!this.matKhau.equals(matKhauCu)
                    || matKhauMoi == null
                    || matKhauMoi.isBlank()) {
                return false;
            }

            this.matKhau = matKhauMoi;
            return true;
        }

        // Hien thi thong tin, khong hien mat khau
        public void hienThiThongTin() {
            System.out.println("Ma tai khoan: " + maTaiKhoan);
            System.out.println("Ten dang nhap: " + tenDangNhap);
            System.out.println("Vai tro: " + vaiTro);
            System.out.println("Trang thai: "
                    + (hoatDong ? "Hoat dong" : "Bi khoa"));
        }
    }
