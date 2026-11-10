# Role A tuần 2

Mở thư mục repo trong VS Code. Dùng JDK 17 trở lên.

Biên dịch toàn bộ từ terminal PowerShell

```powershell
javac -encoding UTF-8 -d out src/rolea/*.java *.java
java -cp out rolea.ManHinhSanPham
```

Chạy hóa đơn tích hợp

```powershell
java -cp out DO_AN_OOP.QUAN_LY_HOA_DON
```

Trong hóa đơn, nút Quản lý sản phẩm mở màn hình A trong cùng phiên chạy. Chọn món từ danh sách để tự điền giá. Mở danh sách chọn lại để tải các món vừa thêm hoặc sửa. Khi tính tiền, hóa đơn tra lại mã sản phẩm và lấy giá hiện tại. Hóa đơn đã tính lưu tên và giá tại thời điểm tính. Hai chương trình Java chạy riêng không chia sẻ dữ liệu.

Màn hình A có bảng sản phẩm, thêm, sửa, xóa và làm mới. Chọn dòng trước khi sửa hoặc xóa. Mã sản phẩm cố định khi sửa. Danh mục có thêm, đổi tên, xóa. Không xóa danh mục đang chứa sản phẩm. Dữ liệu lưu trong bộ nhớ, khởi động lại sẽ trở về mẫu.

Menu console tuần 1 vẫn chạy bằng rolea.TestRoleA. Chức năng tuần 2 sử dụng màn hình Swing.

Các lớp A nằm trong package rolea. QuanLySanPham cung cấp getSanPham(), timTheoMa(), them(), sua(), xoa() cho các module khác.

Đã biên dịch cùng code main và kiểm tra thêm, sửa, xóa, mã trùng không phân biệt hoa thường, tên trống, giá âm, sửa sai không làm đổi dữ liệu cũ, danh mục có sản phẩm và giá hóa đơn. Giao diện Swing cần kiểm tra thao tác trực tiếp trên máy người dùng.

Chưa có PRD chi tiết trong repo. Bản này triển khai theo danh sách công việc tuần 2. Chưa bổ sung kho hoặc lưu file.

Để luyện code, viết lại them(), timTheoMa(), sua(), xoa() trong DanhSachSanPham rồi thử thêm 1 món, sửa giá và xóa món đó. Theo luồng nút Thêm trong ManHinhSanPham để thấy dữ liệu đi từ ô nhập qua SanPham tới danh sách.
