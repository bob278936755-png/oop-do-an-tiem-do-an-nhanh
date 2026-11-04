# Role A tuần 1

Mở thư mục này trong VS Code. Chạy `src/rolea/TestRoleA.java` bằng nút **Run** trên hàm main hoặc dùng JDK trong terminal:

```powershell
javac -encoding UTF-8 -d out src/rolea/*.java
java -cp out rolea.TestRoleA
```

Menu có xem danh mục, xem sản phẩm và thêm sản phẩm. Dữ liệu mẫu và sản phẩm mới nằm trong bộ nhớ của phiên chạy.

Khi ghép chương trình, menu chung gọi `QuanLySanPham.menu(scanner)` với Scanner dùng chung. Role D gọi `QuanLySanPham.timTheoMa("SP01")` để lấy sản phẩm và đọc `getTen()`, `getGiaBan()`. Hàm trả về `null` nếu mã không tồn tại. Giá lưu theo số đồng.
