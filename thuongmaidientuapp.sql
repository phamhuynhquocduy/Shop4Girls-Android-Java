-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 13, 2020 lúc 12:33 PM
-- Phiên bản máy phục vụ: 10.4.11-MariaDB
-- Phiên bản PHP: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `thuongmaidientuapp`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiecdonhang`
--

CREATE TABLE `chitiecdonhang` (
  `id` int(11) NOT NULL,
  `madonhang` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `soluongsanpham` int(11) NOT NULL,
  `tientungsanpham` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `chitiecdonhang`
--

INSERT INTO `chitiecdonhang` (`id`, `madonhang`, `masanpham`, `soluongsanpham`, `tientungsanpham`) VALUES
(45, 45, 14, 1, 13290000),
(46, 47, 19, 3, 49170000),
(47, 48, 19, 1, 16390000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `id` int(11) NOT NULL,
  `madonhang` varchar(200) NOT NULL,
  `masanpham` varchar(200) NOT NULL,
  `tensanpham` varchar(200) NOT NULL,
  `giasanpham` int(11) NOT NULL,
  `soluongsanpham` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`id`, `madonhang`, `masanpham`, `tensanpham`, `giasanpham`, `soluongsanpham`) VALUES
(1, '1', '3', 'day chien', 1000000, 2),
(2, '3', '36', 'Navy Blue Printed Maxi Dress', 808, 1),
(3, '3', '33', ' Black Solid Coated Bodycon Dress', 10770, 3),
(4, '4', '34', 'Beige Printed Maxi Dress', 3597, 3),
(5, '5', '32', 'Love Bugs Daisy Line Bracelet', 1900, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `idkhachhang` int(11) DEFAULT NULL,
  `tongtien` float DEFAULT NULL,
  `ngaythanhtoan` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`id`, `idkhachhang`, `tongtien`, `ngaythanhtoan`) VALUES
(45, 6, 13290000, '2020-05-21'),
(46, 6, 49170000, '2020-06-20'),
(47, 6, 49170000, '2020-06-20'),
(48, 6, 16390000, '2020-07-12');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `id` int(11) NOT NULL,
  `ten` varchar(200) NOT NULL,
  `hinh` varchar(2000) NOT NULL,
  `idcha` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`id`, `ten`, `hinh`, `idcha`) VALUES
(1, 'Điện Thoại', 'https://vn-test-11.slatic.net/p/huawei-gr5-mini-l31g-16gb-gold-8778-6189362-2eb679224529d3ff0f02a4f320843b9f-catalog.jpg_340x340q80.jpg_.webp', 0),
(2, 'Laptop', 'https://i.dell.com/sites/csimages/Videos_Images/en/9eb776ec-d2b3-450c-b340-e1b5f6f31eeb.jpg', 0),
(3, 'Tivi', 'sddddddddddddddddddddddddddddddddd', 1),
(4, 'Tivi', 'sddddddddddddddddddddddddddddddddd', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaitaikhoan`
--

CREATE TABLE `loaitaikhoan` (
  `STT` int(11) NOT NULL,
  `TenLoai` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `loaitaikhoan`
--

INSERT INTO `loaitaikhoan` (`STT`, `TenLoai`) VALUES
(1, 'Người Quản Trị'),
(2, 'Khách Hàng');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `tensanpham` varchar(200) NOT NULL,
  `giasanpham` int(15) NOT NULL,
  `hinhanhsanpham` varchar(2000) NOT NULL,
  `motasanpham` varchar(1000) NOT NULL,
  `idsanpham` int(11) NOT NULL,
  `idthuonghieu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensanpham`, `giasanpham`, `hinhanhsanpham`, `motasanpham`, `idsanpham`, `idthuonghieu`) VALUES
(1, 'Huawei Y9 2019', 5400000, 'https://cdn.tgdd.vn/Products/Images/42/192313/huawei-y9-2019-blue-600x600.jpg', 'Màn hình \"tai thỏ\" FullView kích thước lớn.Huawei Y9 (2019) mang trong mình tới 4 camera với camera kép phía trước độ phân giải 16 MP + 2 MP và camera kép phía sau là 13 MP + 2 MP, cả hai cụm camera đều tích hợp với công nghệ chụp ảnh AI .Công nghệ nhận diện vân tay 4.0\r\n', 1, 0),
(2, 'Huawei Mate 20 Pro', 21990000, 'https://cdn.tgdd.vn/Products/Images/42/188963/huawei-mate-20-pro-tim-400x460.png', 'Huawei Mate 20 Pro được trang bị một không gian hiển thị rộng rãi có kích thước lên đến 6.39 inch đi kèm độ phân giải Quad HD+ (2K+) siêu nét.-Hiệu năng hàng đầu hiện nay.-6 GB RAM và 128 GB bộ nhớ trong đi kèm giúp bạn thỏa sức chạy đa nhiệm hay lưu trữ game và ứng dụng mà không cần lo lắng', 1, 0),
(5, 'Điện thoại OPPO Find X', 20000, 'https://cdn.tgdd.vn/Products/Images/42/165189/oppo-find-x-2-400x460-400x460.png', '            Sức mạnh đến từ con chip Snapdragon 845 cùng 8 GB RAM sẽ là thông số đáng mơ ước trên một chiếc smartphone và nay đã có mặt trên OPPO Find X.    ', 1, 0),
(7, 'Điện thoại iPhone Xs Max 512GB', 43990000, 'https://cdn.tgdd.vn/Products/Images/42/191482/iphone-xs-max-512gb-gold-400x460.png', 'Là chiếc smartphone cao cấp nhất của Apple với mức giá khủng chưa từng có, bộ nhớ trong lên tới 512GB, iPhone XS Max 512GB - sở hữu cái tên khác biệt so với các thế hệ trước, trang bị tới 6.5 inch đầu tiên của hãng cùng các thiết kế cao cấp hiện đại từ chip A12 cho tới camera AI.', 1, 0),
(8, 'Laptop Dell Vostro 3468', 10490000, 'https://cdn.tgdd.vn/Products/Images/44/166382/dell-vostro-3468-70142649-450-600x600.jpg', 'Dell Vostro 3468 i3 6006U là chiếc máy tính xách tay có cấu hình tốt trong tầm giá, được trang bị chip Intel Core i3 cho hiệu năng ổn định, ổ cứng HDD lưu trữ lên đến 500 GB.', 2, 0),
(9, 'Laptop Dell Inspiron', 11990000, 'https://cdn.tgdd.vn/Products/Images/44/189385/dell-inspiron-3476-8j61p11-450-600x600.png', 'Dell Inspiron 3476 i3 8130U là phiên bản máy tính xách tay được trang bị cấu hình cơ bản với chip Intel Core i3 Kabylake Refresh, RAM DDR4 4 GB, ổ cứng HDD lên đến 1 TB, cùng hệ điều hành Windows 10 được cài đặt sẵn. Đây sẽ là lựa chọn phù hợp cho đối tượng như học sinh - sinh viên cần một chiếc laptop đáp ứng tốt các nhu cầu cơ bản của công việc văn phòng cũng như học tập.', 2, 0),
(10, 'Laptop Apple Macbook Air', 21990000, 'https://cdn.tgdd.vn/Products/Images/44/106875/apple-macbook-air-mqd32sa-a-i5-5350u-400-1-450x300-600x600.jpg', '   Macbook Air MQD32SA/A i5 5350U với thiết kế vỏ nhôm nguyên khối Unibody rất đẹp, chắc chắn và sang trọng. Macbook Air là một chiếc máy tính xách tay siêu mỏng nhẹ, hiệu năng ổn định mượt mà, thời lượng pin cực lâu, phục vụ tốt cho nhu cầu làm việc lẫn giải trí.. ', 2, 0),
(11, 'Laptop Acer Aspire', 9990000, 'https://cdn.tgdd.vn/Products/Images/44/160296/acer-aspire-e5-476-i3-8130u-nxgwtsv002-ava-600x600.jpg', 'Acer Aspire E5 476 i3 8130U là phiên bản máy tính xách tay với cấu hình cao, sử dụng vi xử lý mạnh mẽ trong phân khúc nhưng vẫn rất tiết kiệm pin do sử dụng kiến trúc chip mới từ Intel. Laptop Acer với mức giá thành hợp lý cùng cấu hình cực kì mạnh mẽ, Aspire E5 476 có thể đáp ứng tốt cho người dùng phổ thông cần một chiếc máy laptop để học tập, giải trí.', 2, 0),
(12, 'Laptop Acer Spin 3 SP314 51 39WK', 12990000, 'https://cdn.tgdd.vn/Products/Images/44/145919/acer-spin-3-sp314-51-39wk-i3-7130u-nxguwsv001-cava-600x600.jpg', '      Acer Spin 3 SP314 51 39WK là mẫu máy tính có thiên hướng thiết kế về thời trang và sự linh hoạt, tiện lợi vượt trội. Là một chiếc laptop nhỏ gọn, màn hình cảm ứng và có thể biến đổi nhiều hình dạng phù hợp với các nhu cầu sử dụng.  ', 2, 0),
(13, 'Laptop HP Pavilion x360 ba080TU ', 12990000, 'https://cdn.tgdd.vn/Products/Images/44/179677/hp-pavilion-x360-ba080tu-3mr79pa-450-600x600.jpg', 'HP Pavilion x360 ba080TU là chiếc laptop xuất thân từ dòng sản phẩm Pavillion đến từ thương hiệu nổi tiếng HP. Với thiết kế vô cùng gọn nhẹ và cấu hình khá tốt, đáp ứng tốt cho người dùng có nhu cầu mang theo máy tính để học tập, làm việc', 2, 0),
(14, 'Laptop HP Pavilion 14 ce1011TU i3', 13290000, 'https://cdn.tgdd.vn/Products/Images/44/197626/hp-pavilion-14-ce1011tu-i3-8145u-4gb-1tb-win10-5j-600x600.jpg', 'Laptop HP Pavilion 14 ce1011TU (5JN17PA) với thiết kế trang nhã, mỏng nhẹ thuận tiện cho việc di chuyển. Cùng với đó là một cấu hình đáp ứng mượt mà các ứng dụng văn phòng cũng như xử lý khá tốt các ứng dụng đồ họa cơ bản, thì đây chắc hẳn sẽ là một sự lựa chọn đáng để cân nhắc dành cho các bạn sinh viên và nhân viên văn phòng trong phân khúc', 2, 0),
(15, 'Laptop Lenovo IdeaPad 130', 8990000, 'https://cdn.tgdd.vn/Products/Images/44/187012/lenovo-ideapad-130-14ikb-81h60017vn-ava-600x600.jpg', 'Laptop Lenovo IdeaPad 130 14IKB có cấu hình ở mức khá với hệ điều hành Windows 10 bản quyền, chip Intel Core i3 thế hệ thứ 7, 4 GB RAM cùng ổ cứng lưu trữ HDD 1 TB, cho hiệu năng hoạt động ổn định đối với các tác vụ cơ bản như soạn thảo văn bản, nhập liệu, học anh văn, làm bài thuyết trình,... Đây sẽ là chiếc máy tính phù hợp với đối tượng người dùng như nhân viên văn phòng, học sinh - sinh viên', 2, 0),
(17, ' Samsung Galaxy S9', 19990000, 'https://cdn.tgdd.vn/Products/Images/42/197080/samsung-galaxy-s9-plus-64gb-vang-do-400x460.png', 'Galaxy S9+ Vang Đỏ đã được Samsung chính thức mở bán vào dịp Noel, đầu năm mới. Máy tích hợp toàn bộ những tính năng cao cấp nhất như camera kép điều chỉnh khẩu độ, quét mống mắt, chống nước chống bụi và trang bị chip Exynos 9810 đầy mạnh mẽ', 1, 0),
(18, 'Samsung Galaxy A8 Star', 8990000, 'https://cdn.tgdd.vn/Products/Images/42/166247/samsung-galaxy-a8-star-tet-giatot-400x460-400x460.png', 'Samsung Galaxy A8 Star là một biến thể mới của dòng A trong gia đình Samsung với sự nâng cấp vượt trội về camera cũng như thay đổi trong thiết kế', 1, 0),
(19, 'Laptop HP Pavilion 14', 16390000, 'https://cdn.tgdd.vn/Products/Images/44/196907/hp-pavilion-14-ce1018tu-i5-8265u-4gb-16gb-1tb-14f-33397-thumb-600x600.jpg', 'Laptop HP Pavilion 14 (5RL41PA) vừa được HP đưa ra thị trường với thiết kế tinh tế, cùng với trọng lượng khá nhẹ thuận tiện hơn cho việc di chuyển hằng ngày. Cấu hình đủ mạnh để chạy mượt mà các ứng dụng văn phòng, xử lý tốt các thao tác kéo thả trong ứng dụng đồ họa cơ bản. Đây sẽ là sự lựa chọn đáng cân nhắc cho các bạn nhà học sinh, sinh viên và nhân viên văn phòng', 2, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `mataikhoan` int(11) NOT NULL,
  `ho` varchar(150) DEFAULT NULL,
  `ten` varchar(150) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `matkhau` varchar(250) DEFAULT NULL,
  `sdt` varchar(150) DEFAULT NULL,
  `gioitinh` bigint(20) DEFAULT NULL,
  `maloaitk` int(11) DEFAULT NULL,
  `diachi` varchar(355) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`mataikhoan`, `ho`, `ten`, `email`, `matkhau`, `sdt`, `gioitinh`, `maloaitk`, `diachi`) VALUES
(3, 'Nguyễn Quốc', 'Cường', 'spaceteam.hcmue@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '0943597723', 0, 2, '351A Lạc Long Quân Phường 5 Quận 11 TP.Hồ Chí Minh'),
(6, 'Duy', 'Pham', 'phamhjuynhquocduy@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '0394668337', 0, 2, '280 An Dương Vương Phường 4 Quận 5 TP.Hồ Chí Minh'),
(9, 'Admin', 'Cường', 'nguyenquoccuongcn20@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '0943597722', 0, 1, 'ĐH Sư Phạm TP.Hồ Chí Minh'),
(15, '', '', '0394668337', 'd41d8cd98f00b204e9800998ecf8427e', '58800', 0, 2, '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thuonghieu`
--

CREATE TABLE `thuonghieu` (
  `id` int(11) NOT NULL,
  `tenthuonghieu` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `yeuthich`
--

CREATE TABLE `yeuthich` (
  `idtaikhoan` int(11) NOT NULL,
  `idsanpham` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `yeuthich`
--

INSERT INTO `yeuthich` (`idtaikhoan`, `idsanpham`) VALUES
(3, 19),
(6, 13),
(6, 14),
(6, 17),
(6, 18),
(6, 19);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitiecdonhang`
--
ALTER TABLE `chitiecdonhang`
  ADD PRIMARY KEY (`id`),
  ADD KEY `madonhang` (`madonhang`),
  ADD KEY `masanpham` (`masanpham`);

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idkhachhang` (`idkhachhang`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `loaitaikhoan`
--
ALTER TABLE `loaitaikhoan`
  ADD PRIMARY KEY (`STT`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idsanpham` (`idsanpham`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`mataikhoan`),
  ADD KEY `MaLoaiTK` (`maloaitk`);

--
-- Chỉ mục cho bảng `yeuthich`
--
ALTER TABLE `yeuthich`
  ADD PRIMARY KEY (`idtaikhoan`,`idsanpham`),
  ADD KEY `idsanpham` (`idsanpham`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitiecdonhang`
--
ALTER TABLE `chitiecdonhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `loaitaikhoan`
--
ALTER TABLE `loaitaikhoan`
  MODIFY `STT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `mataikhoan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitiecdonhang`
--
ALTER TABLE `chitiecdonhang`
  ADD CONSTRAINT `chitiecdonhang_ibfk_1` FOREIGN KEY (`madonhang`) REFERENCES `donhang` (`id`),
  ADD CONSTRAINT `chitiecdonhang_ibfk_2` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`id`);

--
-- Các ràng buộc cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD CONSTRAINT `donhang_ibfk_1` FOREIGN KEY (`idkhachhang`) REFERENCES `taikhoan` (`MaTaiKhoan`);

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`idsanpham`) REFERENCES `loaisanpham` (`id`);

--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`MaLoaiTK`) REFERENCES `loaitaikhoan` (`STT`);

--
-- Các ràng buộc cho bảng `yeuthich`
--
ALTER TABLE `yeuthich`
  ADD CONSTRAINT `yeuthich_ibfk_1` FOREIGN KEY (`idtaikhoan`) REFERENCES `taikhoan` (`MaTaiKhoan`),
  ADD CONSTRAINT `yeuthich_ibfk_2` FOREIGN KEY (`idsanpham`) REFERENCES `sanpham` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
