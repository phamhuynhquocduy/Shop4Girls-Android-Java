-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 14, 2020 lúc 06:07 PM
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
-- Cơ sở dữ liệu: `shop4girls`
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
(5, 'Mắt', 'https://png.pngtree.com/png-clipart/20190516/original/pngtree-eyelashes-logo-icon-design-template-vector-png-image_3762764.jpg', 0),
(6, 'Mặt ', 'https://png.pngtree.com/png-clipart/20190604/original/pngtree-body-care-png-image_922434.jpg', 0),
(7, 'Môi', 'https://i.pinimg.com/originals/51/9e/14/519e14af2601a7c3343de0a1791cb0fe.png', 0);

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
(24, 'Kẻ Mắt Nước Maybelline', 98000, 'https://media.hasaki.vn/catalog/product/k/e/ke-mat-nuoc-maybelline-sac-net-khong-troi-mau-den-0-5g-01_img_80x80_d200c5_fit_center.jpg', 'Bút kẻ mắt dạng nước Hyper Sharp Power đến từ Maybelline sẽ giúp bạn có đôi mắt to tròn và sắc sảo hơn. Nhờ công thức ưu việt chứa những phân tử carbon siêu nhỏ kết hợp cùng công nghệ hút sáng đặc biệt, Hyper Sharp Power tạo ra những nét vẽ có màu mực đen tuyền ấn tượng, không lem, không trôi nhưng lại rất dễ tẩy trang. Hơn nữa, đầu cọ cực nhỏ và sắc mảnh sẽ giúp bạn linh loạt hơn khi kẻ mắt và dễ dàng tạo ra nhiều phong cách khác nhau từ mảnh tự nhiên đến dày ấn tượng. Hiện Hasaki.vn cung cấp bút kẻ mắt nước Hyper Sharp Power với mức giá tốt nhất thị trường.', 5, 1),
(26, 'Kem Lót Trang Điểm Maybelline Baby Skin 22ml', 98000, 'https://media.hasaki.vn/catalog/product/k/e/kem-lot-trang-diem-maybelline-baby-skin-22mlt_img_80x80_d200c5_fit_center.jpg', 'Sử dụng kem lót khi trang điểm là một bước không thể thiếu để sở hữu gương mặt đẹp hoàn hảo. Maybelline Baby Skin Instant Pore Eraser chắc chắn sẽ là phương pháp cứu nguy cho lớp nền của bạn. Mau trôi, nhanh xuống tông, lộ khuyết điểm: mụn đỏ và lỗ chân lông to chỉ cò là chuyện nhỏ nếu bạn sử dụng em kem lót kiềm dầu Maybelline Baby Skin Instant Pore Eraser', 6, 1),
(27, 'Mascara Maybelline Làm Cong Mi 100° 9.2ml', 85000, 'https://media.hasaki.vn/catalog/product/m/a/mascara-maybelline-lam-cong-mi-100-9-2mlg_img_80x80_d200c5_fit_center.jpg', 'Maybeline là thương hiệu mỹ phẩm nổi tiếng tại mỹ đã cho ra đời nhiều dòng sản phẩm được nhiều khách hàng từ giới nổi tiếng đến những người phụ nữ bình thường biết đến. Lấy cảm hứng từ sự tự tin của những người phụ nữ tài năng, Maybeline cung cấp cho người dùng những công thức khoa học tiên tiến mang đến xu hưỡng mới nhất, dễ dàng sử dụng với giá thành hợp lý.', 5, 1),
(28, 'Mascara Maybelline Làm Dài Và Tơi Mi Màu Đen 10ml', 136000, 'https://media.hasaki.vn/catalog/product/m/a/mascara-maybelline-lam-dai-va-toi-mi-mau-den-10ml-5_img_80x80_d200c5_fit_center.jpg', 'Bạn muốn hàng mi dày cong ấn tượng, hay tự nhiên lôi cuốn? Mascara Làm Dài Và Tơi Mi Maybelline Lash Sensational WaterProof Mascara là dòng sản phẩm mới ra mắt năm 2015 của thương hiệu mỹ phẩm đình đám Maybelline. Nhờ đầu cọ lược độc đáo với 6 loại lược  khác nhau trong 10 lớp lược, sản phẩm này giúp bạn chải đều từng lớp mi nhỏ và ngắn nhất, cho hàng mi bạn tơi dài và xòe đều như cánh quạt, làm đôi mắt càng trở nên sâu hút hồn và quyến rũ.', 5, 1),
(29, 'Bút Kẻ Mắt Nước Sắc Mảnh Maybelline Màu Đen 0.5g', 116000, 'https://media.hasaki.vn/catalog/product/b/u/but-ke-mat-nuoc-sac-manh-maybelline-mau-den-0-5g_img_80x80_d200c5_fit_center.jpg', 'Một đôi mắt đẹp, sắc sảo thì không thể thiếu viền kẻ mắt, không những  đem lại ánh nhìn mê hoặc mà đây còn là bước không thể thiếu trong việc trang điểm mắt. Bút Kẻ MắtNước Sắc Mảnh Maybelline HyperSharp Liner Màu Đen 0.5g từ thương hiệu mỹ phẩm trang điểm Maybelline New York nổi tiếng của Mỹ với đầu cọ cải tiến chỉ 0.01mm và rất mềm mại, do vậy đường kẻ mắt sẽ cực kì linh hoạt và dễ điều khiển, bạn có thể dễ dàng vẽ mắt từ nét mỏng tự nhiên đến dày cá tính. Cấu trúc ống mực cũng đã được cải tiến so với phiên bản cũ giúp dòng mực chảy từ ống xuống ngòi đều hơn, đậm hơn.', 5, 1),
(30, 'Kem Nền Maybelline Mịn Nhẹ Kiềm Dầu Chống Nắng 30ml', 144000, 'https://media.hasaki.vn/catalog/product/k/e/kem-nen-maybelline-min-nhe-kiem-dau-chong-nang-120-classic-ivory-30ml_1_img_80x80_d200c5_fit_center.jpg', 'Kem Nền Maybelline Mịn Nhẹ Kiềm Dầu Chống Nắng Matte Poreless Foundation SPF 22 30ml là kem nền đến từ thương hiệu mỹ phẩm MAYBELLINE của Mỹ, với công nghệ Micro Powders hạt phấn siêu nhỏ không dầu, không hương liệu độc quyền cho hiệu ứng mịn lì tự nhiên, tiệp màu da cùng khả năng kiềm dầu hiệu quả cả ngày dài, phù hợp cho da dầu.', 6, 1),
(31, 'Bút Cushion Che Khuyết Điểm Maybelline Giảm Quầng Thâm Light 6ml', 122000, 'https://media.hasaki.vn/catalog/product/b/u/but-cushion-che-khuyet-diem-maybelline-giam-quang-tham-120-light-6ml-1_img_80x80_d200c5_fit_center.jpg', 'Che Khuyết Điểm Maybelline Instant Age Rewind Treatment Concealer chính là “cứu tinh” cho những đôi mắt “gấu trúc” cần che đi quầng thâm mệt mỏi và bọng mắt “xấu xí” khi có thể che phủ những khuyết điểm chỉ trong giây lát và giữ kết quả “tuyệt vời” ấy trong nhiều giờ, để bạn có thể tự tin với đôi mắt tươi sáng và gương mặt đầy vẻ trẻ trung.', 6, 1),
(32, 'Son Kem Lì Maybelline New York Màu Cam Cháy', 125000, 'https://media.hasaki.vn/catalog/product/s/o/son-kem-chuan-li-giu-mau-16h-maybelline-new-york-mau-do-cam-118-dancer-5ml_img_80x80_d200c5_fit_center.jpg', 'Son Kem Chuẩn Lì Giữ Màu 16h Maybelline New York Superstay Matte Ink City Edition là dòng son kem lì khiến nhiều cô gái say đắm đến từ thương hiệu mỹ phẩm trang điểm Maybelline New York nổi tiếng của Mỹ, nay đã ra mắt thêm bộ sưu tập mới City Edition bao gồm 8 tông màu mới siêu hot được truyền cảm hứng từ thành phố New York, thời thượng và cực chất. Đồng thời chất son vẫn được giữ nguyên vẹn, chuẩn mịn lì cùng độ lâu trôi bền màu lên đến 16 tiếng, không lem, không trôi, không gây khô môi.', 7, 1),
(33, 'Son Kem Lì Maybelline Màu Cam Đất 117 Ground Breake', 145000, 'https://media.hasaki.vn/catalog/product/s/o/son-kem-chuan-li-giu-mau-16h-maybelline-new-york-mau-do-117-ground-breaker-5ml_img_80x80_d200c5_fit_center.jpg', 'Son Kem Chuẩn Lì Giữ Màu 16h Maybelline New York Superstay Matte Ink City Edition là dòng son kem lì khiến nhiều cô gái say đắm đến từ thương hiệu mỹ phẩm trang điểm Maybelline New York nổi tiếng của Mỹ, nay đã ra mắt thêm bộ sưu tập mới City Edition bao gồm 8 tông màu mới siêu hot được truyền cảm hứng từ thành phố New York, thời thượng và cực chất. Đồng thời chất son vẫn được giữ nguyên vẹn, chuẩn mịn lì cùng độ lâu trôi bền màu lên đến 16 tiếng, không lem, không trôi, không gây khô môi.', 7, 1),
(34, 'Son Lì ZA Màu Đỏ Đậm RD407 3.5g', 260000, 'https://media.hasaki.vn/catalog/product/s/o/son-li-za-mau-do-dam-rd407-3-5g85_1__img_80x80_d200c5_fit_center.jpg', 'Son Lì ZA Vivid Dare Lipstick 3.5g từ \"ZA\" thương hiệu NHẬT - trực thuộc SHISEIDO Bám Màu Siêu Lâu nhưng Không Hề Khô Môi đâu nha.  Lên môi siêu mê. Thương hiệu mỹ phẩm Nhật Bản thuộc tập đoàn mỹ phẩm Shiseido. Hiện nay, các dòng son trên thị trường tuy là son lì nhưng sẽ tạo cảm giác khô cho môi. Trong khi đó, dòng son Vivid Dare giúp cho môi đủ độ ẩm mịn nhưng vẫn giữ được màu son lâu phai. Bên cạnh đó, sản phẩm với công nghệ bắt màu độc đáo, giữ màu son tươi sáng suốt 8 giờ, đem đến cho bạn nhiều phong cách khác nhau, tự tin thể hiện cá tính.', 7, 6),
(35, 'Son Bút Chì Za Màu Tím Purple 675 3g', 149000, 'https://media.hasaki.vn/catalog/product/s/o/son-but-chi-za-mau-tim-purple-675-3g11_1__img_80x80_d200c5_fit_center.jpg', 'Son bút chì chắc hẳn đã không còn xa lạ gì với chị em phụ nữ. Dù hiện nay trên thị trường son màu và son lì đang chiếm ưu thế hơn nhưng cũng không thể phủ nhận được độ “hot” của son bút chì. Son Bút Chì Za Lip Crayon nổi bật với các màu sắc tươi tắn, là xu hướng rất thịnh của mùa thu năm nay. Sản phẩm kết hợp cùng thành phần nhiều dưỡng chất giúp cho đôi môi mềm mại mịn màng. Đôi môi căng mịn, tràn đây sức sống sẽ là điểm nổi bật tôn lên vẻ đẹp của phái nữ đấy các nàng nhé!\n\n\n', 7, 6),
(36, 'Son Lì ZA Màu Đỏ Cam RD401 3.5g', 260000, 'https://media.hasaki.vn/catalog/product/s/o/son-li-za-mau-do-cam-rd401-3-5g86_1__img_80x80_d200c5_fit_center.jpg', 'Son Lì ZA Vivid Dare Lipstick 3.5g từ \"ZA\" thương hiệu NHẬT - trực thuộc SHISEIDO Bám Màu Siêu Lâu nhưng Không Hề Khô Môi đâu nha. Lên môi siêu mê. Thương hiệu mỹ phẩm Nhật Bản thuộc tập đoàn mỹ phẩm Shiseido. Hiện nay, các dòng son trên thị trường tuy là son lì nhưng sẽ tạo cảm giác khô cho môi. Trong khi đó, dòng son Vivid Dare giúp cho môi đủ độ ẩm mịn nhưng vẫn giữ được màu son lâu phai. Bên cạnh đó, sản phẩm với công nghệ bắt màu độc đáo, giữ màu son tươi sáng suốt 8 giờ, đem đến cho bạn nhiều phong cách khác nhau, tự tin thể hiện cá tính.\n\n', 7, 6),
(37, 'Son Lì ZA Màu Hồng RS405 3.5g', 260000, 'https://media.hasaki.vn/catalog/product/s/o/son-li-za-mau-hong-rs405-3-5g87_1__img_80x80_d200c5_fit_center.jpg', 'Son Lì ZA Vivid Dare Lipstick 3.5g từ \"ZA\" thương hiệu NHẬT - trực thuộc SHISEIDO Bám Màu Siêu Lâu nhưng Không Hề Khô Môi đâu nha. Lên môi siêu mê. Thương hiệu mỹ phẩm Nhật Bản thuộc tập đoàn mỹ phẩm Shiseido. Hiện nay, các dòng son trên thị trường tuy là son lì nhưng sẽ tạo cảm giác khô cho môi. Trong khi đó, dòng son Vivid Dare giúp cho môi đủ độ ẩm mịn nhưng vẫn giữ được màu son lâu phai. Bên cạnh đó, sản phẩm với công nghệ bắt màu độc đáo, giữ màu son tươi sáng suốt 8 giờ, đem đến cho bạn nhiều phong cách khác nhau, tự tin thể hiện cá tính.\n\n', 7, 6),
(38, 'Mascara Za Làm Dài Mi 9g', 185000, 'https://media.hasaki.vn/catalog/product/m/a/mascara-za-lam-dai-mi-9g19_1__img_80x80_d200c5_fit_center.jpg', 'Mascara dài và dày mi ZA Killer Volume Long The Black với công thức độc đáo giúp mi dài gấp 200% độ dài bình thường tạo cảm giác đôi mắt to tròn , quyến rũ hơn. Za Killer Volume Long Mascara có các sợi nối mi giúp làn mi dài gấp đôi nhưng vẫn tạo cảm giác nhẹ nhàng, thoái mái. Sản phẩm còn có tinh dầu hoa Anh Thảo ngăn rụng mi, thiết kế đầu cọ dễ sử dụng cho đôi mắt có ánh nhìn long lanh với làn mi cong, dài mượt.\n\n\n', 5, 6),
(39, 'Mascara Za Làm Cong Mi 9gr', 165000, 'https://media.hasaki.vn/catalog/product/s/-/s-l1000_6_img_80x80_d200c5_fit_center.jpg', 'Masscara Za Killer Volume Curl Mascara với dưỡng chất làm dày và dài mi có thể làm mi cong lên đến 170%, sản phẩm làm dày mi cho bạn làn mi cong vút và quyến rũ chỉ trong một lần chải. Ngoài ra, sản phẩm với thành phần chứa Collagen, Vitamin E giúp dưỡng mi, cho làn mi dày và bóng khỏe vượt trội. Sản phẩm không lem, lâu trôi, không vón cục giúp làn mi cong đẹp trong thời gian dài.\n', 5, 6),
(40, 'Nước Tẩy Trang Za Chuyên Sâu Dành Cho Mắt & Môi 90ml', 145000, 'https://media.hasaki.vn/catalog/product/n/u/nuoc-tay-trang-chuyen-sau-danh-cho-mat-moi-za-90ml_1_img_80x80_d200c5_fit_center.jpg', 'Tẩy trang chính là bước đầu tiên và không thể thiếu để có được một làn da đẹp. Các sản phẩm tẩy dầu hay thường được gọi là tẩy trang không chỉ có tác dụng loại bỏ các mỹ phẩm trang điểm trên da, nó còn có tác dụng rửa sạch các chất nhờn do tuyến dầu trong da gây nên. Vì thế, ngay cả khi bạn không trang điểm cũng nên làm sạch da bằng các sản phẩm tẩy dầu, đặc biệt là với những bạn có da dầu.\n\n', 6, 6),
(41, 'Kem Tẩy Tế Bào Chết Da Mặt Za 100g', 170000, 'https://media.hasaki.vn/catalog/product/k/e/kem-tay-te-bao-chet-da-mat-za-100g_1__img_80x80_d200c5_fit_center.jpg', 'Không như các sản phẩm tẩy tế bào chết thông thường, Za True White Ex Exfoliating Clay không chỉ giúp loại bỏ các tế bào chết trên da, mà còn giúp lấy đi lượng dầu thừa trên bề mặt cho bạn làn da trắng mịn rạng rỡ từ bên trong. Kem tẩy tế bào chết và loại bỏ đi những bụi bẩm bám trên da và làm sạch lớp sừng trên bề mặt da cho làn da sáng mịn tự nhiên. Kem tẩy tế bào chết cho da mặt có dạng mặt nạ đất sét làm mềm mại làn da, tươi sáng ngay tức thì, loại bỏ sạm nám, tàn nhang do tác động của tia UV.\n\n', 6, 6),
(42, 'Kem Dưỡng Sáng Da Ban Ngày Za SPF20/PA++ 40g', 195000, 'https://media.hasaki.vn/catalog/product/k/e/kem-duong-sang-da-ban-ngay-za-spf20-pa-40g_1__img_80x80_d200c5_fit_center.jpg', 'Rất nhiều các cô gái mong muốn được sở hữu một làn da sáng, mịn màng thu hút mọi ánh nhìn và là chuẩn cái đẹp của người Châu Á. Tuy nhiên do chưa sử dụng đúng sản phẩm và chăm sóc da đúng cách nên mong muốn ấy chưa trở thành sự thật. Hôm nay, Hasaki sẽ giới thiệu đến các bạn một bộ kem dưỡng sáng da đến từ một nhãn hàng riêng của Shiseido - bộ Kem Dưỡng Sáng Da Za bao gồm kem dưỡng ban ngày và ban đêm với thành phần chiết xuất từ thiên nhiên có chứa nhiều dưỡng chất giúp da sáng, mịn màng, đồng thời hỗ trợ làm mờ nếp nhăn do lão hóa, vết sạm, nám,…trên da hiệu quả.', 6, 6),
(43, 'Tinh Chất Dưỡng Sáng Za 3 Trong 1 150ml', 257000, 'https://media.hasaki.vn/catalog/product/t/i/tinh-chat-duong-sang-za-3-trong-1-150ml69_1__img_80x80_d200c5_fit_center.jpg', 'Triết lý làm đẹp kiểu Nhật Bản vốn chú trọng đến việc giữ ẩm, vì tin rằng đó là nền tảng cần thiết nhất của một làn da khoẻ đẹp. Tinh Chất Dưỡng Da Za True White Ex Essence Lotion cũng vậy: bên cạnh việc làm sáng, thành phần 4MSK được độc quyền sáng chế bởi tập đoàn Shiseido có khả năng đập tan & tăng cường quá trình đào thải melanin giúp da đều màu, giảm thâm và tuyệt đối an toàn với các loại da.\n', 6, 6),
(53, 'Kem Dưỡng Nâng Tông Da Laneige SPF 35 PA++ 50ml', 760000, 'https://media.hasaki.vn/catalog/product/k/e/kem-duong-nang-tong-da-laneige-spf-35-pa-50ml-01_img_80x80_d200c5_fit_center.jpg', 'Kem Dưỡng Nâng Tông Da Laneige White Dew Tone Up Fluid SPF 35 PA++ từ thương hiệu Laneige của Hàn Quốc giúp làm sáng và nâng tông màu da. Được tạo ra từ cỏ Tam Bạch Thảo có nguồn gốc từ thiên nhiên an toàn và dịu nhẹ, mỗi sản phẩm đến từ dòng White Dew đều là giải pháp giúp bạn thoát khỏi những vấn đề về làn da không đều màu, thiếu độ đàn hồi hay mất đi làn da tươi trẻ vốn có. Kem dưỡng giúp làm sáng da và bảo vệ làn da trước tia UV, ánh sáng xanh và các hạt bụi mịn mang lại làn da khỏe đẹp với 8 lớp màng bảo vệ giúp bảo vệ da suốt 8 giờ đồng hồ\n\n', 6, 7),
(54, 'Mặt Nạ Ngủ Môi Laneige Hương Quả Mọng Mini 8g', 142000, 'https://media.hasaki.vn/catalog/product/m/a/mat-na-ngu-moi-laneige-huong-qua-mong-mini-8g-1_img_80x80_d200c5_fit_center.jpg', 'Mặt Nạ Ngủ Môi Laneige Lip Sleeping Mask là một trong những dòng sản phẩm bán chạy và được yêu thích nhất của thương hiệu mỹ phẩm cao cấp Laneige, giúp chăm sóc đôi môi ngay cả trong giấc ngủ, nhẹ nhàng loại bỏ các tế bào chết trên môi, đồng thời dưỡng ẩm cho đôi môi trông luôn mềm mượt, căng mọng và đàn hồi cùng với mùi thơm nhẹ nhàng từ các chiết xuất tự nhiên. Hãy trải nghiệm cảm giác đôi môi được chăm sóc mềm mại vào ban đêm với Mặt Nạ Ngủ Môi Laneige Lip Sleeping Mask với 4 mùi hương tươi mới như quả mọng, bưởi, táo và vani!', 6, 7),
(55, 'Kem Hiệu Chỉnh Nâng Tông Làm Sáng Da Laneige 50ml', 760000, 'https://media.hasaki.vn/catalog/product/k/e/kem-hieu-chinh-nang-tong-lam-sang-da-laneige-50ml_img_80x80_d200c5_fit_center.jpg', 'Kem dưỡng sáng da là sản phẩm không thể thiếu giúp chị em tự tin với làn da trắng mịn rạng rỡ. Nếu bạn đang tìm một dòng kem giải quyết cho vấn đề dưỡng sáng thì Kem Hiệu Chỉnh Nâng Tông Làm Sáng Da Laneige White Dew Tone-Up Cream từ thương hiệu mỹ phẩm cao cấp Laneige đến từ Hàn Quốc sẽ là một lựa chọn không nên bỏ qua. Sản phẩm nâng tông màu da với cấu tạo dưỡng ẩm tức thời làm sáng các vùng da xỉn màu, vùng da tối màu ngay sau khi sử dụng. Đây là sản phẩm nằm trong dòng Dưỡng ẩm làm sáng da “White Dew” có chứa thành phần tự nhiên được chiết xuất từ cỏ Bạch Thảo, thành phần làm sáng da được cấp bằng sáng chế độc quyền của Laneige.\n\n', 6, 7),
(56, 'Kem Dưỡng Laneige Dưỡng Ẩm, Chống Lão Hóa Da 50ml', 1360000, 'https://media.hasaki.vn/catalog/product/l/a/laneige-time-freeze-intensive-cream-50ml-100180067_img_80x80_d200c5_fit_center.jpg', 'Kem dưỡng ẩm, chống lão hóa Time Freeze Intensive Cream là sản phẩm thuộc dòng Chống lão hóa Time Freeze của thương hiệu mỹ phẩm hàng đầu Laneige đến từ Hàn Quốc. Sản phẩm với công nghệ Dynamic CollagenTM - công nghệ tái tổng hợp collagen đầu tiên trên thế giới - nhanh chóng từng bước tác động vào các sợi collagen đã lão hóa, thẩm thấu sâu vào da để khôi phục độ đàn hồi và làm trẻ hóa hệ thống collagen mới mềm dẻo hơn, săn chắc hơn. Time Freeze Intensive Cream đã chứng minh hiệu quả phục hồi kết cấu da khỏe mạnh, săn chắc hơn, tốc độ tăng cường độ đàn hồi cho da nhanh hơn.', 6, 7),
(57, 'Phấn Nước Kiềm Dầu Laneige Kèm Lõi Thay Thế 21 Beige 15Gx2', 629000, 'https://media.hasaki.vn/catalog/product/p/h/phan-nuoc-kiem-dau-laneige-kem-loi-thay-the-21-beige-15gx2-01_img_80x80_d200c5_fit_center.jpg', 'Được biết đến như một thương hiệu mỹ phẩm nổi tiếng từ Hàn Quốc, Laneige từ lâu đã giữ một vị trí quan trọng trên thị trường mỹ phẩm Việt Nam, và là một trong những thương hiệu được đông đảo chị em yêu thích và tin dùng trong suốt nhiều năm qua. Bắt nguồn từ cái tên Laneige có nghĩa là Tuyết trong tiếng Pháp, thương hiệu này lấy vẻ đẹp tinh khiết của tuyết làm trọng tâm và luôn hoàn thiện vẻ đẹp của phụ nữ một cách tinh tế. Các dòng sản phẩm của Laneige đều được nghiên cứu và ứng dụng sao cho thân thiện với làn da của bạn, bổ sung tối đa độ ẩm cần thiết cho da, mang đến vẻ đẹp tự nhiên trong trẻo như sương sớm ban mai và tràn đầy sức sống. Một ưu điểm lớn của Laneige đó chính là sản phẩm đã được thiết kế để phù hợp với làn da châu Á, chính thế mạnh này đã đưa Laneige trở thành cái tên không thể quên đối với phụ nữ Việt Nam.', 6, 7),
(58, 'Phấn Nước Laneige Trang Điểm Và Che Khuyết Điểm', 560000, 'https://media.hasaki.vn/catalog/product/p/h/phan-nuoc-laneige-trang-diem-va-che-khuyet-diem-n21-14g-2-5g-01_img_80x80_d200c5_fit_center.jpg', 'Đối với phụ nữ ngày này thì ngoại hình xinh đẹp là không thể thiếu giúp phụ nữ thêm phần tự tin. Và nếu bạn là một cô gái khá bận rộn hay yêu thích phong cách trang điểm nhẹ nhàng, tự nhiên thì phấn nước chính là một sự lựa chọn vô cùng lý tưởng đấy nhé. Phấn Nước + Che Khuyết Điểm 2 Trong 1 Laneige Cover Cushion & Concealing Base Layering Cover Cushion với kết cấu 2 trong 1 vừa là kem che khuyết điểm, vừa là phấn nước giúp điều chỉnh độ che phủ, lâu trôi và giúp mang đến cho bạn làn da sáng mịn', 6, 7),
(59, 'Phấn Nước Đa Năng Laneige Ngăn Ngừa Lão Hóa', 688000, 'https://media.hasaki.vn/catalog/product/p/h/phan-nuoc-da-nang-laneige-ngan-ngua-lao-hoa-kem-loi-thay-the-21-natural-beige-15gx2_img_80x80_d200c5_fit_center.jpg', 'Xuất hiện và được sử dụng phổ biến tại Hàn Quốc, sau đó lan rộng ở các nước Châu Á, trong đó có Việt Nam, xu hướng trang điểm phủ sương đang là một trong những xu hướng làm đẹp đang thịnh hành. Với cách trang điểm này làn da của bạn sẽ trở nên căng bóng, mịn màng, đặc biệt mang đến vẻ ngoài tự nhiên như không hề trang điểm. Bí quyết đối với làn da phủ sương này chính là việc sử dụng Phấn nước thay thế cho toàn bộ các bước trang điểm gồm kem lót, kem nền, phấn phủ khiến lớp trang điểm dày cộm như trước đây! Điểm chung của những dòng phấn nước chính là khả năng kiềm dầu rất tốt, do đó thích hợp sử dụng cho mọi loại da kể cả với da dầu.', 6, 7),
(60, 'Mặt Nạ Ngủ Laneige Ngăn Ngừa Lão Hóa 100ml', 696000, 'https://media.hasaki.vn/catalog/product/m/a/mat-na-ngu-laneige-san-chac-da-100ml_img_80x80_d200c5_fit_center.jpg', 'Mặt Nạ Ngủ Ngăn Ngừa Lão Hóa Laneige Time Freeze Firming Sleeping Mask thuộc dòng sản phẩm mặt nạ ngủ chăm sóc da của thương hiệu mỹ phẩm cao cấp Laneige sẽ là giải pháp giúp tăng cường khả năng đàn hồi cho da, mang lại cho bạn làn da trông săn chắc vào mỗi sáng thức dậy.\n\n', 6, 7),
(61, 'Phấn Nước Laneige Đa Năng Dưỡng Sáng', 734000, 'https://media.hasaki.vn/catalog/product/p/h/phan-nuoc-laneige-da-nang-duong-sang-kem-loi-thay-the-23-sand-15gx2-01_img_80x80_d200c5_fit_center.jpg', 'Cushion là một dạng Kem nền được nhúng trong miếng nệm mút. Nghe thì đơn giản thế thôi nhưng các đặc điểm nổi trội của nó mới chính là lý do vì sao hộp Cushion nho nhỏ đã chinh phục được hầu hết phái đẹp: Miếng nệm mút của Cushion được thiết kế với công nghệ bơm không khí, bạn chỉ cần chạm nhẹ tay, lớp không khí mát sẽ tràn ra từ miếng nệm mút, mang theo những hạt bong bóng khí nhỏ chứa phấn và dưỡng chất lập tức làm dịu mát cho da. Cấu tạo của miếng nệm mút cũng giúp lấy ra một lượng kem mỏng, thật vừa phải để tạo nên lớp nền tự nhiên, mịn màng và thân thiện với da.', 6, 7),
(62, 'Son MAC Mịn Lì 646 Marrakesh Màu Đỏ Đất', 560000, 'https://media.hasaki.vn/catalog/product/g/o/google-shopping-son-thoi-min-li-mac-marrakesh-mau-do-dat-3g-1_img_80x80_d200c5_fit_center.jpg', 'Son Thỏi Mịn Lì MAC Matte Lipstick 3g là một trong những dòng sản phẩm đặc trưng làm nên tên tuổi của thương hiệu trang điểm chuyên nghiệp M.A.C, sở hữu chất son mịn lì đặc trưng mà không hề khô môi, cùng những gam màu đa dạng được phối trộn bởi công thức đặc biệt của MAC, cho ra những màu son lì siêu chuẩn, tô điểm cho đôi môi thêm quyến rũ và cuốn hút ánh nhìn.', 7, 2),
(63, 'Son MAC Siêu Lì 702 Dangerous Màu Đỏ Cam 3g', 560000, 'https://media.hasaki.vn/catalog/product/g/o/google-shopping-son-thoi-sieu-li-mac-dangerous-mau-do-cam-3g-4_img_80x80_d200c5_fit_center.jpg', 'MAC Retro Matte Lipstick là một trong những dòng son lì mang tính biểu tượng đã làm nên tên tuổi của thương hiệu trang điểm chuyên nghiệp M.A.C, với công thức bền màu lâu trôi lên đến 8h, cùng khả năng lên màu chuẩn sắc và chất son hoàn toàn mịn lì, chuẩn matte trên môi, giúp tô điểm cho đôi môi của bạn trở nên đẹp hoàn hảo.', 7, 2),
(64, 'Bộ 3 Son Thỏi MAC Signature Star', 549000, 'https://media.hasaki.vn/catalog/product/g/o/google-shopping-bo-03-son-thoi-mac-signature-star_img_80x80_d200c5_fit_center.jpg', 'Bộ 03 Son Thỏi MAC Starring You Signature Stars Lipstick Kit là phiên bản đặc biệt được thiết kế dành riêng cho mùa lễ hội 2020, với 3 tông màu son quyến rũ, thời thượng nhất thuộc top best seller của thương hiệu mỹ phẩm trang điểm chuyên nghiệp M·A·C Cosmetics. Đồng thời, bộ sản phẩm được MAC khoác lên mình một diện mạo hoàn toàn mới, tươi trẻ và tràn ngập sắc màu với những ngôi sao lấp lánh.', 7, 2),
(65, 'Phấn Phủ MAC Dạng Bột Kiềm Dầu 9g', 850000, 'https://media.hasaki.vn/catalog/product/g/o/google-shopping-phan-phu-dang-bot-kiem-dau-mac-9g_img_80x80_d200c5_fit_center.jpg', 'Phấn Phủ Dạng Bột Kiềm Dầu MAC Prep + Prime Transparent Finishing Powder là sản phẩm thuộc thương hiệu M.A.C nổi tiếng không chỉ ở Mỹ mà còn trên toàn thế giới. Các sản phẩm của M.A.C luôn mang đến những ưu điểm vô cùng xịn sò và sản phẩm lần này cũng như thế.', 6, 2),
(66, 'Phấn Nền Phủ MAC NC25 Màu Tự Nhiên 15g', 970000, 'https://media.hasaki.vn/catalog/product/g/o/google-shopping-phan-nen-phu-mac-nc25-mau-tu-nhien-15g_img_80x80_d200c5_fit_center.jpg', 'Phấn Nền Phủ MAC Studio Fix Powder Plus Foundation là dòng sản phẩm Best Seller từ thương hiệu mỹ phẩm trang điểm chuyên nghiệp nổi tiếng M·A·C, nổi bật với công dụng trang điểm 2 trong 1: kem nền + phấn phủ chỉ trong một bước, mang đến cho bạn lớp nền hoàn hảo mịn màng, không tì vết với độ che phủ từ trung bình đến cao và kết cấu mượt mà không làm bí da.', 6, 2),
(67, 'Kem Nền MAC Kiềm Dầu SPF 15 NC15 Màu Sáng 30ml', 970000, 'https://media.hasaki.vn/catalog/product/g/o/google-shopping-kem-nen-kiem-dau-mac-spf-15-nc15-mau-sang-30ml-3_img_80x80_d200c5_fit_center.jpg', 'Kem Nền Kiềm Dầu MAC Studio Fix Fluid SPF 15 Fond De Teint SPF 15 là dòng sản phẩm trang điểm mặt bán chạy nhất của thương hiệu mỹ phẩm trang điểm chuyên nghiệp M·A·C xuất xứ từ Mỹ, nổi tiếng với lớp finish mịn lì cùng độ che phủ cao, kết hợp chỉ số chống nắng phổ rộng SPF 15 và khả năng bền màu lâu trôi đến 24h, kể cả trên nền da dầu vào mùa hè, chắc chắn sẽ mang đến cho bạn gái một vẻ ngoài hoàn hảo suốt cả ngày lẫn đêm.', 6, 2),
(68, 'Son MAC Dưỡng Ẩm Màu Đỏ Rượu Vang 410 La Femme 3g', 560000, 'https://media.hasaki.vn/catalog/product/g/o/google-shopping-son-moi-duong-am-mac-mau-do-ruou-vang-410-la-femme-3g-1_1_img_80x80_d200c5_fit_center.jpg', 'Son Môi Dưỡng Ẩm MAC Love Me Lipstick là bộ sưu tập son môi mới nhất từ thương hiệu mỹ phẩm trang điểm chuyên nghiệp M·A·C Cosmetics được ra mắt vào nửa cuối 2019, với khả năng lên màu chuẩn sắc cùng công thức dưỡng ẩm vượt trội, tô điểm cho đôi môi của nàng trở nên căng mọng quyến rũ.', 6, 2),
(69, 'Bảng Che Khuyết Điểm Và Hiệu Chỉnh Màu Da MAC Extra Deep 6g', 1240000, 'https://media.hasaki.vn/catalog/product/g/o/google-shopping-bang-che-khuyet-diem-va-hieu-chinh-mau-da-mac-extra-deep-6g_img_80x80_d200c5_fit_center.jpg', 'Tác dụng có thể khác nhau tuỳ cơ địa của người dùng', 6, 2),
(70, 'Son Kem MAC Bền Màu Quite the Standout Màu Cam Đỏ 5ml', 720000, 'https://media.hasaki.vn/catalog/product/g/o/google-shopping-son-kem-ben-mau-mac-quite-the-standout-mau-cam-do-5ml_img_80x80_d200c5_fit_center.jpg', 'MAC là thương hiệu thời trang gợi cảm, đẳng cấp và sang trọng đến từ Châu Âu. Mỗi lần thương hiệu này ra mắt bộ sưu tập là lại khuấy đảo thị trường thời trang thế giới. Trong các dòng sản phẩm thời trang của MAC, Son MAC được đông đảo phái đẹp lựa chọn với chất lượng son cùng với mức giá khá mềm đối với một sản phẩm thời trang hàng đầu thế giới', 7, 2),
(71, 'Son MAC Dưỡng Ẩm Màu Hồng Ấm 405 Under The Covers 3g', 560000, 'https://media.hasaki.vn/catalog/product/g/o/google-shopping-son-moi-duong-am-mac-mau-hong-am-405-under-the-covers-3gjpg_img_80x80_d200c5_fit_center.jpg', 'Son Môi Dưỡng Ẩm MAC Love Me Lipstick là bộ sưu tập son môi mới nhất từ thương hiệu mỹ phẩm trang điểm chuyên nghiệp M·A·C Cosmetics được ra mắt vào nửa cuối 2019, với khả năng lên màu chuẩn sắc cùng công thức dưỡng ẩm vượt trội, tô điểm cho đôi môi của nàng trở nên căng mọng quyến rũ.', 7, 2),
(72, 'Nước Tẩy Trang B.O.M Từ 8 Loại Trà 500ml', 289000, 'https://media.hasaki.vn/catalog/product/n/u/nuoc-tay-trang-bom-8-loai-tra-500ml_img_80x80_d200c5_fit_center.jpg', 'Sau khi trang điểm, nếu chỉ dùng sữa rửa mặt hoặc toner để tẩy trang thì không thể nào lấy đi hoàn toàn cặn bẩn trang điểm còn sót lại trên da, từ đó dẫn đến bít tắc lỗ chân lông và tăng nguy cơ bị mụn. Nước Tẩy Trang B.O.M Từ 8 Loại Trà từ thương hiệu B.O.M giúp làm sạch bụi bẩn, bã nhờn, nhẹ nhàng lấy đi các lớp trang điểm có độ bám dính cao như kem nền, kem che khuyết điểm, son...giúp bạn ngăn ngừa những tổn thương đối với da do việc không tẩy trang hoặc tẩy trang không sạch gây ra.', 6, 3),
(73, 'Kẻ Mắt B.O.M 02 Wonder Brown', 235000, 'https://media.hasaki.vn/catalog/product/k/e/ke-mat-b-o-m-02-wonder-brown_img_80x80_d200c5_fit_center.jpg', 'Đôi mắt luôn được xem là cửa sổ tâm hồn. Với đôi mắt sâu thẳm, đầy sắc nét sẽ khiến gương mặt bạn trở nên quyến rũ, thần thái rạng ngời. Hiểu được điều đó, sau đây Hasaki xin giới thiệu đến bạn em kẻ mắt Wonderproof Pen Eye Liner đến từ thương hiệu B.O.M hiện đang được ưa chuộng tại thị trường Hàn Quốc', 5, 3),
(74, 'Sữa Dưỡng B.O.M Từ 8 Loại Trà 120ml', 259000, 'https://media.hasaki.vn/catalog/product/g/o/google-shopping-sua-duong-b-o-m-tu-8-loai-tra-120ml_img_80x80_d200c5_fit_center.jpg', 'Nếu bạn là người ghét cảm giác da ẩm dính khó chịu sau khi sử dụng kem dưỡng ẩm, hoặc da bạn dễ bị bí và nổi mụn do kem dưỡng quá nhiều độ ẩm, hãy thử trải nghiệm sản phẩm Sữa Dưỡng Từ 8 Loại Trà B.O.M Eight Tea Lotion đến từ thương hiệu mỹ phẩm B.O.M được ưa chuộng tại Hàn Quốc. Thấu hiểu được nỗi băn khoăn của phái đẹp khi lựa chọn sản phẩm chăm sóc cho làn da nhạy cảm, B.O.M đã cho ra đời dòng sản phẩm chiết xuất từ 8 loại trà tự nhiên nuôi dưỡng làn da dịu nhẹ mà hiệu quả, trong đó có Sữa Dưỡng B.O.M Eight Tea Lotion. ', 6, 3),
(75, 'Son Dưỡng B.O.M Màu Đỏ Tự Nhiên 02 Pure Red 4.5g', 242000, 'https://media.hasaki.vn/catalog/product/s/o/son-duong-mau-do-tu-nhien-b-o-m-02-pure-red-4-5g_img_80x80_d200c5_fit_center.jpg', 'Chỉ với một lớp son môi mỏng nhẹ sẽ khiến gương mặt bạn trở nên rạng ngời, sáng bừng sức sống. Sau đây Hasaki xin giới thiệu đến bạn Son Dưỡng Có Màu B.O.M Dewy Lip Balm với thành phần chính là chiết xuất từ bơ hạt mỡ sẽ giúp chăm sóc cho làn môi của bạn thêm phần căng mọng, mịn màng trông thấy. Cùng tông màu hồng ngọt ngào, dịu dàng sẽ giúp biến hóa khuôn mặt bạn trở nên tươi tắn, xinh yêu hơn hẳn.', 7, 3),
(76, 'Son Kem Lì B.O.M H RD H RD 102 Vampire Red', 277000, 'https://media.hasaki.vn/catalog/product/s/o/son-kem-li-b-o-m-h-rd-h-rd-102-vampire-red-2_img_80x80_d200c5_fit_center.jpg', 'Nếu bạn là tín đồ của những dòng son kem lì thì B.O.M Matt Holic Tint chắc hẳn sẽ làm bạn hoàn toàn hài lòng đấy nhé! Với sắc son lì khó cưỡng sẽ tạo nên phong thái đẹp quyến rũ không lẫn giữa đám đông. Chất son mịn mượt đến từ Son Kem Lì B.O.M Matt Holic Tint sẽ che phủ hoàn hảo những vết vân môi, tình trạng lộ rãnh môi đem đến cho bạn đôi môi mịn mượt, đầy hoàn hảo.', 7, 3),
(77, 'Chì Kẻ Mày B.O.M 3 In 1 03 Choco Brown', 208000, 'https://media.hasaki.vn/catalog/product/g/o/google-shopping-chi-ke-may-b-o-m-3-in-1-03-choco-brown_img_80x80_d200c5_fit_center.jpg', 'Chì Kẻ Mày B.O.M 3 In 1 Triple Edge Eye Brow với thiết kế dạng vặn tiện dụng gồm 3 đầu: đầu chì, đầu cọ bột và đầu chổi tán giúp tạo hình chân mày trở nên chính xác và gọn gàng hơn. Đầu cọ bột nhằm tán bột đều, làm đầy những chỗ còn trống. Ngoài ra, bạn còn có thể sử dụng để tán lên đường chân tóc tạo hiệu ứng mái tóc dày bồng bềnh và có chiều sâu hơn cho gương mặt. Đầu chổi sẽ giúp những sợi chân mày còn sót lại vào nếp ngay ngắn & thẳng hàng.', 5, 3),
(78, 'Phấn Mắt Dạng Lỏng Có Nhũ B.O.M 01 Mirror Ball 3.5g', 267000, 'https://media.hasaki.vn/catalog/product/p/h/phan-mat-dang-long-co-nhu-b-o-m-01-mirror-ball_img_80x80_d200c5_fit_center.jpg', 'Một make-up look hoàn hảo cho buổi tiệc tối của bạn đương nhiên không thể thiếu được sự tô điểm của các loại phấn mắt có nhũ lấp lánh, giúp tạo điểm nhấn thu hút cho ánh nhìn thêm rạng rỡ. Hasaki xin giới thiệu đến các bạn Phấn Mắt Dạng Lỏng Có Nhũ B.O.M Pearl Party Eye Glitter với chiết xuất từ bột ngọc trai chứa những hạt nhỏ li ti cho hiệu ứng ánh sáng long lanh trên mắt sẽ là người bạn đồng hành tuyệt vời cho mọi sự kiện cần nổi bật của các nàng, đặc biệt phù hợp với những cô gái yêu thích phong cách make-up Hàn Quốc.', 5, 3),
(79, 'Son Kem Lì B.O.M H RS 301 Lovely Rose', 277000, 'https://media.hasaki.vn/catalog/product/s/o/son-kem-li-b-o-m-h-rs-301-lovely-rose_img_80x80_d200c5_fit_center.jpg', 'Nếu bạn là tín đồ của những dòng son kem lì thì B.O.M Matt Holic Tint chắc hẳn sẽ làm bạn hoàn toàn hài lòng đấy nhé! Với sắc son lì khó cưỡng sẽ tạo nên phong thái đẹp quyến rũ không lẫn giữa đám đông. Chất son mịn mượt đến từ Son Kem Lì B.O.M Matt Holic Tint sẽ che phủ hoàn hảo những vết vân môi, tình trạng lộ rãnh môi đem đến cho bạn đôi môi mịn mượt, đầy hoàn hảo.', 7, 3),
(80, 'Chì Kẻ Mày B.O.M 3 In 1 02 Dark Brown', 208000, 'https://media.hasaki.vn/catalog/product/g/o/google-shopping-chi-ke-may-b-o-m-3-in-1-02-dark-brown_img_80x80_d200c5_fit_center.jpg', 'Chì Kẻ Mày B.O.M 3 In 1 Triple Edge Eye Brow với thiết kế dạng vặn tiện dụng gồm 3 đầu: đầu chì, đầu cọ bột và đầu chổi tán giúp tạo hình chân mày trở nên chính xác và gọn gàng hơn. Đầu cọ bột nhằm tán bột đều, làm đầy những chỗ còn trống. Ngoài ra, bạn còn có thể sử dụng để tán lên đường chân tóc tạo hiệu ứng mái tóc dày bồng bềnh và có chiều sâu hơn cho gương mặt. Đầu chổi sẽ giúp những sợi chân mày còn sót lại vào nếp ngay ngắn & thẳng hàng.', 5, 3),
(81, 'Son Kem Lì B.O.M H RD 101 Miss Cherry', 277000, 'https://media.hasaki.vn/catalog/product/s/o/son-kem-li-b-o-m-h-rd-101-miss-cherry_img_80x80_d200c5_fit_center.jpg', 'Nếu bạn là tín đồ của những dòng son kem lì thì B.O.M Matt Holic Tint chắc hẳn sẽ làm bạn hoàn toàn hài lòng đấy nhé! Với sắc son lì khó cưỡng sẽ tạo nên phong thái đẹp quyến rũ không lẫn giữa đám đông. Chất son mịn mượt đến từ Son Kem Lì B.O.M Matt Holic Tint sẽ che phủ hoàn hảo những vết vân môi, tình trạng lộ rãnh môi đem đến cho bạn đôi môi mịn mượt, đầy hoàn hảo.', 7, 3),
(82, 'Tinh Chất Dưỡng Eucerin Làm Giảm Mụn 30ml', 500000, 'https://media.hasaki.vn/catalog/product/t/i/tinh-chat-duong-eucerin-lam-giam-mun-30ml-01_1_img_80x80_d200c5_fit_center.jpg', 'Eucerin là thương hiệu nổi tiếng tại Đức, trực thuộc tập đoàn Beiersdorf AG. Từ những năm 1980s, thương hiệu Eucerin đã được giới thiệu rộng rãi tại các chi nhánh của Beiersdorf trên toàn cầu, trong đó có Mỹ. Ngoài các sản phẩm chăm sóc cơ thể và mặt, thương hiệu Eucerin còn có các sản phẩm làm sạch và sản phẩm chống nắng. Eucerin chủ trương phối hợp chặt chẽ với các chuyên gia da liễu và nắm bắt những công nghệ tiên tiến nhằm tạo ra những sản phẩm có chất lượng, an toàn đến tay người tiêu dùng. Với 100 năm kinh nghiệm, Eucerin là thương hiệu vinh dự được các chuyên gia da liễu khuyên dùng tại Châu Âu.', 6, 8),
(83, 'Kem Dưỡng Eucerin Ngăn Ngừa Lão Hóa Ban Đêm 50ml', 618000, 'https://media.hasaki.vn/catalog/product/k/e/kem-duong-eucerin-ngan-ngua-lao-hoa-ban-dem-50ml-02_img_80x80_d200c5_fit_center.jpg', 'Kem Dưỡng Ngăn Ngừa Lão Hóa Eucerin Q10 ACTIVE Cream đến từ thương hiệu dược mỹ phẩm Eucerin của Đức là dòng sản phẩm kem dưỡng dành cho làn da lão hóa sớm, với công thức Coenzyme Q10 chuyên biệt giúp làm trẻ hóa tế bào da và đồng thời ngăn ngừa các dấu hiệu lão hóa xuất hiện trên làn da nhạy cảm.', 6, 8),
(84, 'Xịt Khoáng Dưỡng Ẩm Eucerin Cho Da Nhạy Cảm 50ml', 108000, 'https://media.hasaki.vn/catalog/product/x/i/xit-khoang-duong-am-eucerin-cho-da-nhay-cam-50ml-01_img_80x80_d200c5_fit_center.jpg', 'Môi trường điều hòa có thể tạo cho bạn cảm giác thoải mái, mát mẻ nhưng bạn có biết da sẽ bị mất cân bằng độ ẩm và nhanh chóng lão hóa nếu không được chăm sóc đúng cách? Không chỉ thế da khi trang điểm cũng rất dễ bị khô và mốc khiến bạn cảm giác thiếu tự tin và khó chịu. Vậy phải làm sao? Thật đơn giản, bạn chỉ cần sử dụng sản phẩm xịt khoáng. Đây là một giải pháp giúp dưỡng da, bổ sung nước, vitamin và khoáng chất. Ngoài ra còn giúp duy trì độ ẩm và tạo sự tươi sáng cho làn da bạn gái. Xịt Khoáng Dưỡng Da Aquaporin Mist Spray từ thương hiệu Eucerin của Đức sẽ là một trong những gợi ý dành cho các cô nàng vì khả năng cấp ẩm nhanh chóng của nó.', 6, 8),
(85, 'Bọt Tẩy Trang Eucerin Làm Sạch & Dưỡng Ẩm Da 150ml', 325000, 'https://media.hasaki.vn/catalog/product/b/o/bot-tay-trang-eucerin-lam-sach-duong-am-da-150ml_img_80x80_d200c5_fit_center.jpg', 'Bọt Tẩy Trang Làm Sạch & Dưỡng Ẩm Da Eucerin Dermato Clean Hyaluron Micellar Foam 3 in 1 150ml là sản phẩm làm sạch đến từ thương hiệu dược mỹ phẩm Eucerin lâu đời xuất xứ từ Đức, có công thức bọt Micellar dịu nhẹ nhưng hiệu quả, giúp nhẹ nhàng loại bỏ toàn bộ các tạp chất, lớp trang điểm và bã nhờn dư thừa chỉ trong một bước duy nhất mà không làm khô căng da sau khi rửa mặt.', 6, 8),
(86, 'Tẩy Tế Bào Chết Eucerin Dành Cho Da Mụn 100ml', 275000, 'https://media.hasaki.vn/catalog/product/t/a/tay-te-bao-chet-eucerin-danh-cho-da-mun-100ml-03_img_80x80_d200c5_fit_center.jpg', 'Tẩy Tế Bào Chết Dành Cho Da Mụn Eucerin Pro ACNE Solution Scrub 100ml là sản phẩm tẩy tế bào chết không chứa dầu, kết hợp thành phần Lactic Acid giúp làm sạch nhẹ dịu, giảm các tác nhân gây mụn đầu trắng và mụn đầu đen, đồng thời loại bỏ cặn trang điểm hiệu quả. Sản phẩm thích hợp sử dụng cho những bạn có làn da nhờn mụn mong muốn sở hữu một làn da mịn màng và khỏe mạnh hơn.\n\n', 6, 8),
(87, 'Kem Chống Nắng Eucerin Làm Đều Màu Da Fair SPF 50+ 50ml', 375000, 'https://media.hasaki.vn/catalog/product/k/e/kem-chong-nang-eucerin-lam-deu-mau-da-fair-spf-50-50ml-1_5_img_80x80_d200c5_fit_center.jpg', 'Ngày nay, các loại kem chống nắng ngoài việc giúp bảo vệ da trước tác hại từ ánh nắng mặt trời còn kèm theo các tác dụng khác như giúp dưỡng ẩm, giúp làm sáng, dưỡng da,... Bên cạnh đó, có những loại kem chống nắng còn có tác dụng như sản phẩm kem lót thay thế. Tại Hasaki, Eucerin Sun Tinted CC Cream # Fair SPF50+ được biết đến như một sản phẩm không những giúp bảo vệ da trước tia UV hiệu quả, mà còn mang lại khả năng che phủ cực tốt, giúp che mờ khuyết điểm, giúp làn da của bạn trông căng mướt và mịn màng', 6, 8),
(88, 'Sữa Tắm Eucerin Làm Sạch Cho Mặt & Toàn Thân 400ml', 239000, 'https://media.hasaki.vn/catalog/product/s/u/sua-tam-eucerin-danh-cho-da-nhay-cam-400ml-01_img_80x80_d200c5_fit_center.jpg', 'Sữa Tắm Eucerin pH5 for Body & Face WashLotion là sản phẩm làm sạch dành cho da mặt và toàn thân đến từ thương hiệu dược mỹ phẩm Eucerin của Đức, được điều chế với công thức pH5 Enzyme Protection dịu nhẹ phù hợp cho làn da nhạy cảm, giúp nhẹ nhàng làm sạch và duy trì lớp bảo vệ da nhạy cảm trước các tác động của môi trường, cho làn da mềm mại và ẩm mượt suốt cả ngày.', 6, 8),
(89, 'Gel Rửa Mặt Eucerin Làm Sạch Dịu Nhẹ Da Nhạy Cảm 200ml', 326000, 'https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-gel-rua-mat-eucerin-lam-sach-diu-nhe-da-nhay-cam-200ml_img_80x80_d200c5_fit_center.jpg', 'Gel Rửa Mặt Eucerin Làm Sạch Dịu Nhẹ Da Nhạy Cảm DermatoCLEAN Hyaluron Cleansing Gel 200ml là sữa rửa mặt thuộc dòng sản phẩm DermatoCLEAN làm sạch dịu nhẹ cho da nhạy cảm đến từ thương hiệu dược mỹ phẩm Eucerin, giúp nhẹ nhàng loại bỏ lớp trang điểm và bụi bẩn, tạp chất trên da một cách hiệu quả, mang lại làn da sạch bóng và mịn màng sau khi rửa mặt, đồng thời hỗ trợ da hô hấp dễ dàng hơn, chuẩn bị tốt hơn cho giai đoạn tái tạo.', 6, 8),
(90, 'Kem Dưỡng Hỗ Trợ Giảm Mụn, Làm Sáng Da 50ml + Gel Rửa Mặt Da Dầu Mụn 200 ml', 659000, 'https://media.hasaki.vn/catalog/product/c/o/combo-eucerin-kem-duong-ho-tro-giam-mun-lam-sang-da-50ml-gel-rua-mat-da-dau-mun-200-ml_img_80x80_d200c5_fit_center.jpg', 'Eucerin được biết đến là một thương hiệu dược mỹ phẩm nổi tiếng tại Đức, trực thuộc tập đoàn Beiersdorf AG. Từ những năm 1980s, thương hiệu Eucerin đã được giới thiệu rộng rãi tại các chi nhánh của Beiersdorf trên toàn cầu, trong đó có Mỹ. Ngoài các sản phẩm chăm sóc cơ thể và mặt, thương hiệu Eucerin còn có các sản phẩm làm sạch và sản phẩm chống nắng. Eucerin chủ trương phối hợp chặt chẽ với các chuyên gia da liễu và nắm bắt những công nghệ tiên tiến nhằm tạo ra những sản phẩm có chất lượng, an toàn đến tay người tiêu dùng. Với 100 năm kinh nghiệm, Eucerin là thương hiệu vinh dự được các chuyên gia da liễu khuyên dùng tại Châu Âu.', 6, 8),
(91, 'Kem Dưỡng Eucerin Ngăn Ngừa Lão Hóa Vùng Mắt 15ml', 969000, 'https://media.hasaki.vn/catalog/product/k/e/kem-duong-eucerin-ngan-ngua-lao-hoa-vung-mat-15ml-01_img_80x80_d200c5_fit_center.jpg', 'Kem Dưỡng Ngăn Ngừa Lão Hóa Vùng Mắt Anti Age Hyaluron Filler 15ml với công thức chăm sóc vùng mắt tiên tiến với SPF 15 cùng lớp bảo vệ tia UVA giúp làm mờ các nếp nhăn vùng da quanh mắt, đã được kiểm nghiệm với sự giám sát của các bác sĩ nhãn khoa.', 6, 8),
(92, 'Bộ Đôi Laneige Cấp Nước & Ngăn Ngừa Lão Hóa Da 2 Món', 766000, 'https://media.hasaki.vn/catalog/product/g/o/google-shopping-bo-doi-laneige-cap-nuoc-ngan-ngua-lao-hoa-da-2-mon_img_80x80_d200c5_fit_center.jpg', 'Mặt Nạ Ngủ Laneige Cung Cấp Nước Water Sleeping Mask 100ml là dòng mặt nạ ngủ đến từ thương hiệu mỹ phẩm cao cấp Laneige của Hàn Quốc, với công nghệ SleeptoxTM phục hồi lại trạng thái tối ưu cho da, Moisture WrapTM cung cấp độ ẩm sâu cho da suốt đêm và SleepscentTM (bao gồm tinh dầu hoa cam, hoa hồng, hoa ngọc lan tây và gỗ đàn hương) giúp bạn thư giãn cả về thể chất lẫn tinh thần và đặc biệt thích hợp với những người hay bị khó ngủ. Kết hợp với công nghệ hiện đại là chuỗi thành phần cực kỳ tốt cho da, đặc biệt như Chiết xuất trà xanh Camellia Sinensis là chất dưỡng ẩm, chống oxy hóa, se khít lỗ chân lông, chống nắng cho da và giúp trắng da dịu nhẹ số 1 của người Châu Á.', 6, 7),
(93, 'Son Kem Lì Lâu Trôi L\'Oreal 358 Cowboy', 193000, 'https://media.hasaki.vn/catalog/product/s/o/son-kem-li-lau-troi-l-oreal-358-cowboy_img_80x80_d200c5_fit_center.jpg', 'Cơn sốt son lì cho đến thời điểm hiện tại vẫn chưa hề hạ nhiệt! Bắt kịp xu hướng ấy, thương hiệu đình đám L\'Oreal Paris vừa cho ra mắt siêu phẩm Son Kem Lì Lâu Trôi Infallible Lip Pro Matte Liquid Lipstick với 5 sắc màu thời thượng cùng chất son mịn lì, sắc son lên môi chuẩn màu hứa hẹn sẽ mang đến cho bạn đôi môi căng mịn, bền màu và lâu trôi suốt cả ngày dài.', 7, 9),
(94, 'Son Lì Mịn Môi L\'oreal 300 Flaming Cloud Màu Đỏ Gạch 3.7g', 179000, 'https://media.hasaki.vn/catalog/product/s/o/son-li-min-moi-l-oreal-300-flaming-cloud-mau-do-gach-3-7g_img_80x80_d200c5_fit_center.jpg', 'Thỏi son là vũ khí tối thượng của mọi phụ nữ, được ví von như chiếc gậy ma thuật biến họ đẹp lên trong phút chốc. Cơn sốt son lì chưa bao giờ \"hạ nhiệt\", để chiều lòng các tín đồ Makeup, L\'Oréal Paris vừa cho ra mắt Bộ Sưu Tập Son Lì Color Riche Moist Matte mới, hứa hẹn sẽ đem đến trải nghiệm đẳng cấp với chất son mịn lì cùng những tông màu cá tính và thời thượng.', 7, 9),
(95, 'Son Kem Lì Nhẹ Môi Cao Cấp L\'Oréal Paris 114 I Represent 7ml', 200000, 'https://media.hasaki.vn/catalog/product/s/o/son-kem-li-l-oreal-114-i-represent-7ml_img_80x80_d200c5_fit_center.jpg', 'L\'Oreal Rouge Signature Matte Liquid Lipstick là dòng son kem mịn lì cao cấp đến từ thương hiệu L\'Oreal Paris nổi tiếng xuất xứ từ Pháp, có kết cấu son mỏng mượt và \"nhẹ tênh như không\" khi lên môi, cho lớp finish đúng chuẩn son lì nhưng vẫn mang lại cảm giác mềm mại và mịn màng như nhung. Đặc biệt, son có khả năng lên màu cực chuẩn ngay từ lần đầu tiên chạm môi và bền màu lâu trôi lên đến 8 giờ sau khi sử dụng.', 7, 9),
(96, 'Kem Nền Dưỡng Ẩm L\'Oreal True Match G2 30ml', 216000, 'https://media.hasaki.vn/catalog/product/k/e/kem-nen-duong-am-true-match-g2-30ml-2_img_80x80_d200c5_fit_center.jpg', 'Kem Nền Dưỡng Ẩm L\'Oreal True Match Liquid Foundation chính là sản phẩm rất phù hợp với làn da châu Á. Được tích hợp các thành phần dưỡng ẩm giúp bổ sung độ ẩm đầy đủ cho làn da cùng với các hạt màu siêu mềm mịn, sản phẩm sẽ tạo độ che phủ và mang đến cho bạn làn da mịn màng, tươi sáng thật tự nhiên. Đặc biệt, lớp phấn còn có khả năng kiềm dầu hiệu quả cho bạn sự thông thoáng suốt cả ngày.', 6, 9),
(97, 'Kem Nền Lâu Trôi L\'Oreal Infallible 130 True Beige 30ml', 229000, 'https://media.hasaki.vn/catalog/product/k/e/kem-nen-l-oreal-infallible-130-true-beige-30mli_img_80x80_d200c5_fit_center.jpg', 'Kem nền là lớp trang điểm đóng vai trò quan trọng trong quy trình trang điểm giúp bạn định hình nên lớp trang điểm và tạo ấn tượng về một làn da đẹp và khỏe mạnh. Hãy cùng chào đón siêu phẩm mới từ L\'Oréal Paris - Kem Nền Lâu Trôi L\'Oreal Infallible 24h Fresh Wear - dòng kem nền lâu trôi giúp giải quyết những nỗi băn khoăn muôn thuở của các cô gái khi chọn lựa một sản phẩm kem nền như: nhanh xuống màu, che phủ kém, cảm giác nặng mặt và bí trên da,...Với thành phần chứa chiết xuất từ các loại khoáng chất thiên nhiên cùng chất kem mềm mượt, dễ tán, L\'Oreal Infallible 24h Fresh Wear sẽ giúp bạn có một lớp nền trang điểm đẹp, lì và hạn chế tình trạng xuống tông, đổ dầu sau khi một thời gian trang điểm. Kem không gây bóng nhờn, giúp da khô thoáng suốt cả ngày. Sản phẩm có tông màu đa dạng phù hợp với từng sắc da.', 6, 9),
(98, 'Sữa Rửa Mặt L\'Oreal Làm Sáng Da 100ml', 79000, 'https://media.hasaki.vn/catalog/product/s/u/sua-rua-mat-l-oreal-lam-sang-da-100ml_5__img_80x80_d200c5_fit_center.jpg', 'L\'Oreal White Perfect Milky Foam là sự lựa chọn phù hợp nhằm giúp bạn chăm sóc và nuôi dưỡng làn da trở nên tươi sáng hồng hào. Sữa Rửa Mặt Làm Sáng Da L\'Oreal nhẹ nhàng làm sạch, loại bỏ mọi bụi bẩn, bã nhờn sâu trong lỗ chân lông, cân bằng độ pH cho da đồng thời hỗ trợ ngăn ngừa quá trình tăng sắc tố melanin. Mang đến cho bạn làn da tươi sáng, khỏe mạnh và căng tràn sức sống.', 6, 9),
(99, 'Son Lì Ánh Kim L\'Oreal Paris Màu Nude 259 Nude After Party 3.7g', 197000, 'https://media.hasaki.vn/catalog/product/s/o/son-li-anh-kim-l_oreal-paris-mau-nude-259-nude-after-party-3.7g_img_80x80_d200c5_fit_center.jpg', 'Thời gian vừa qua, các dòng son lì đã \"làm mưa làm gió\" trong cộng đồng làm đẹp, tuy nhiên để giúp cho vẻ ngoài ở các buổi tiệc thêm lung linh, quyến rũ và tỏa sáng thì son lì dù có hấp dẫn đến đâu cũng nên nhường \"ngôi vị\" cho các dòng son ánh nhũ siêu độc đáo và nổi bật. Son Lì Ánh Kim từ thương hiệu L\'Oreal Paris với chất son ánh kim, thiết kế sang trọng cùng các gam màu \"hot-trend\" chắc chắn sẽ là sự lựa chọn phù hợp \"hô biến\" các nàng thành \"nữ hoàng\" xinh đẹp trong mọi sự kiện luôn đấy!\n\n', 7, 9),
(100, 'Tinh Chất L\'Oreal Dưỡng Ngăn Ngừa Nếp Nhăn 30ml\n', 351000, 'https://media.hasaki.vn/catalog/product/t/i/tinh-chat-l-oreal-duong-ngan-ngua-nep-nhan-30ml1_img_80x80_d200c5_fit_center.jpg', 'Lão hóa da là một quy luật bất biến của thời gian mà không một ai có thể thay đổi được. Qua thời gian, làn da sẽ dần mất độ đàn hồi, bắt đầu xuất hiện các nếp nhăn và chảy xệ,...Vậy có cách nào để níu kéo tuổi thanh xuân và làm chậm quá trình lão hóa hay không? Câu trả lời từ Hasaki là có, và Tinh Chất Dưỡng Giúp Ngăn Ngừa Nếp Nhăn L\'Oreal Revitalift Intensive Repairing Essence sẽ giúp bạn làm được điều này. Sản phẩm dạng tinh chất với kết cấu nhẹ nhàng sẽ giúp nuôi dưỡng da mềm mịn, săn chắc, giúp làm giảm nếp nhăn và se khít lỗ chân lông hiệu quả. Sản phẩm hiện đã có mặt tại Hasaki.', 6, 9),
(101, 'Nước Hoa Hồng Sáng Da 200ml', 323000, 'https://media.hasaki.vn/catalog/product/c/o/combo-l-oreal-nuoc-hoa-hong-sang-da-200ml-kem-duong-ngay-ho-tro-giam-tham-nam-50ml_img_80x80_d200c5_fit_center.jpg', 'Làn da sáng khỏe, hồng hào tự nhiên luôn là mong muốn của bất kỳ cô gái nào. Để sở hữu được làn da ấy, thì chắc hẳn trong routine hằng ngày không thể thiếu các dòng kem dưỡng sáng da được đâu nhé! Hasaki xin mách nhỏ đến bạn bộ Combo dưỡng da đến từ thương hiệu L\'Oreal Paris nổi tiếng khắp thế giới gồm Nước Hoa Hồng Sáng Da White Perfect Toner kết hợp cùng Kem Dưỡng Ban Ngày White Perfect Clinical Day Cream sẽ chăm sóc da toàn diện, hứa hẹn mang đến cho bạn làn da tỏa sáng đầy rực rỡ.', 6, 9),
(102, 'Bút Sáp Vẽ Mắt 2 Đầu L\'Oreal 110 Mocha Coffee 1.5g', 179000, 'https://media.hasaki.vn/catalog/product/b/u/but-sap-ve-mat-2-dau-l-oreal-110-mocha-coffee-1-5g_1_img_80x80_d200c5_fit_center.jpg', 'Đôi mắt gợi cảm, đầy cuốn hút luôn là điểm nhấn trong xu hướng makeup look hiện nay đó các cô nàng nha! Với đôi mắt lung linh ánh nhũ hay tông khói huyền bí sẽ khiến bạn trở nên tỏa sáng hơn bao giờ hết. Và Bút Sáp Vẽ Mắt 2 Đầu L\'Oreal Le Stylo Smoky Shadow Stick sẽ giúp bạn thực hiện điều đó, bạn có thể thỏa sức mix match tone màu đậm nhạt theo ý mình với đầu cọ kabuki đầy tiện dụng và mới lạ tạo nên vô vàn kiểu makeup khác nhau.', 5, 9),
(103, 'Kem Che Khuyết Điểm L\'Oreal True Match 2N Vanilla 6.8ml', 156000, 'https://media.hasaki.vn/catalog/product/k/e/kem-che-khuyet-diem-l-oreal-true-match-2n-vanilla-6-8ml-1-4_img_80x80_d200c5_fit_center.jpg', 'Che khuyết điểm là công đoạn không thể thiếu trong quy trình makeup, đặc biệt là đối với các cô nàng làn da có nhiều khuyết điểm và cú đêm chính hiệu. Concealer ngay chốc lát sẽ khiến gương mặt bạn trở nên tươi sáng, đầy sức sống và không tì vết! Đến với Kem Che Khuyết ĐiểmTrue Match The One Concealer của thương hiệu đình đám L\'Oréal Paris sẽ giúp hiệu chỉnh tông màu da, che đi mọi khuyết điểm đồng thời giấu đi sự mệt mỏi, kém sắc trên làn da bạn.', 6, 9),
(104, 'Nước Tẩy Trang Dành Cho Mắt Môi Bbia 100ml', 90000, 'https://media.hasaki.vn/catalog/product/n/u/nuoc-tay-trang-mat-moi-bbia-100ml_img_80x80_d200c5_fit_center.jpg', 'Nước Tẩy Trang Dành Cho Mắt Môi Bbia Soft Gentle Lip & Eye Remover 100ml là sản phẩm thuộc thương hiệu Bbia Hàn Quốc. Đây là sản phẩm tẩy trang chuyên dụng cho môi và mắt, giúp lấy đi lớp makeup water-proof cứng đầu chỉ với một lần tẩy trang duy nhất', 5, 4),
(105, 'Bút Che Khuyết Điểm Bbia 00 Ivory Beige 6.2g', 110000, 'https://media.hasaki.vn/catalog/product/b/u/but-che-khuyet-diem-bbia-00-ivory-beige-6-2g_img_80x80_d200c5_fit_center.jpg', 'Bút Che Khuyết Điểm Bbia Last Concealer 00 Ivory Beige 6.2g từ thương hiệu Bbia của Hàn Quốc với công dụng che phủ hoàn hảo, che phủ các khuyết điểm như nốt mẩn đỏ, tàn nhang, lô chân lông to cho da đẹp không tỳ vết. Chỉ một lượng nhỏ đã có thể che phủ các khuyết điểm mà không bị vón cục hay không đều màu cho cảm giác nhẹ nhàng thoải mái. Bút che khuyết điểm giúp duy trì làn da đều màu, không tì vết cả ngày mà không lo bị phai màu.', 6, 4),
(106, 'Son Tint Dạng Kem Bbia Màu Hồng Cam Đất 24 Trendy Note 5g', 150000, 'https://media.hasaki.vn/catalog/product/s/o/son-tint-dang-kem-bbia-mau-hong-cam-dat-24-trendy-note-5g_img_80x80_d200c5_fit_center.jpg', 'Son Tint Dạng Kem Bbia Last Velvet Lip Tint là dòng son kem lì nổi tiếng rất được yêu thích của thương hiệu mỹ phẩm Bbia Hàn Quốc, được giới trẻ săn lùng nhờ chất son chuẩn mịn lì, có khả năng lên màu rõ và sống động chỉ sau một lần thoa, đồng thời giúp che phủ các khuyết điểm cho đôi môi mịn màng như được \"chỉnh sửa photoshop\" cùng độ bám dính cực cao, giữ màu son được bền chặt suốt cả ngày dài.', 7, 4),
(107, 'Son Lì Bút Chì Bbia Màu Cam LR2 Mama 0.8g', 110000, 'https://media.hasaki.vn/catalog/product/s/o/son-li-but-chi-bbia-mau-cam-lr2-mama-0-8g_img_80x80_d200c5_fit_center.jpg', 'Bbia là thương hiệu mỹ phẩm bình dân tại Hàn Quốc, được rất nhiều ngôi sao nổi tiếng yêu thích. Đặc biệt là dòng son Bbia, đã nhanh chóng trở thành một trào lưu mới được nhiều chị em “săn lùng”. Các sản phẩm của hãng đa dạng cà thường tập trung vào makeup như: son môi, má hồng, chì kẻ mắt, phấn nền…Bbia phù hợp với mọi đối tượng và hướng đến mọi phong cách. Bbia luôn được giới trẻ săn đón nồng nhiệt, đặc biệt là các cô nàng tuổi Teen.', 7, 4),
(108, 'Son Tint Dạng Kem Bbia Màu Đỏ Nâu 25 Final Note 5g', 150000, 'https://media.hasaki.vn/catalog/product/s/o/son-tint-dang-kem-bbia-mau-do-nau-25-final-note-5g-2_img_80x80_d200c5_fit_center.jpg', 'Son Tint Dạng Kem Bbia Last Velvet Lip Tint là dòng son kem lì nổi tiếng rất được yêu thích của thương hiệu mỹ phẩm Bbia Hàn Quốc, được giới trẻ săn lùng nhờ chất son chuẩn mịn lì, có khả năng lên màu rõ và sống động chỉ sau một lần thoa, đồng thời giúp che phủ các khuyết điểm cho đôi môi mịn màng như được \"chỉnh sửa photoshop\" cùng độ bám dính cực cao, giữ màu son được bền chặt suốt cả ngày dài.', 7, 4),
(109, 'Tinh Chất Chống Nắng Sunplay Giữ Ẩm, Hiệu Chỉnh Sắc Da 50g', 144000, 'https://media.hasaki.vn/catalog/product/t/i/tinh-chat-chong-nang-hieu-chinh-sac-da-sunplay-spf50-pa-50g-06_img_80x80_d200c5_fit_center.jpg', 'Tinh Chất Chống Nắng Hiệu Chỉnh Sắc Da Sunplay Skin Aqua Tone Up UV Essence SPF50+/PA++++  là sản phẩm chống nắng thuộc thương hiệu kem chống nắng nổi tiếng Sunplay đến từ Nhật Bản, không chỉ giúp bảo vệ da trước tia UV hiệu quả, mà còn mang lại khả năng hiệu chỉnh làn da cực tốt, làn da bạn sẽ trở nên đều màu, tươi tắn và rạng rỡ hơn. Sản phẩm được bình chọn là kem chống nắng No.1 năm 2018 trên trang Cosme - trang thông tin mỹ phẩm hàng đầu Nhật Bản.', 6, 5);
INSERT INTO `sanpham` (`id`, `tensanpham`, `giasanpham`, `hinhanhsanpham`, `motasanpham`, `idsanpham`, `idthuonghieu`) VALUES
(110, 'Sữa Chống Nắng Sunplay Dưỡng Da Sáng Mịn 55g', 154000, 'https://media.hasaki.vn/catalog/product/s/u/sua-chong-nang-sunplay-duong-da-sang-min-spf50-pa-55g-003_1_img_80x80_d200c5_fit_center.jpg', 'Với đặc tính kháng nước và mồ hôi cao, Skin Aqua Clear White luôn bảo đảm được cả hai tác động đó là vừa bảo vệ da vừa nuôi dưỡng da khi phải vận động liên tục ngoài trời hoặc dưới nước nhờ thành phần bổ sung Vitamin C, Vitamin E, Pro Vitamin B5, Hyaluronic Acid, giữ ẩm và nuôi dưỡng làn da một cách hiệu quả. Hơn nữa, công thức cải tiến không nhờn rít, dịu nhẹ và nhanh chóng thấm vào da, sản phẩm cho bạn cảm giác mát lạnh, nhẹ tênh, thích hợp cho cả da mặt.', 6, 5),
(111, 'Sữa Chống Nắng Sunplay Kiềm Dầu, Nâng Tông Da 50g', 142000, 'https://media.hasaki.vn/catalog/product/s/u/sua-chong-nang-kiem-dau-nang-tong-che-khuyet-diem-sunplay-spf50-pa-50g-4_img_80x80_d200c5_fit_center.jpg', 'Skin Aqua Tone Up UV là dòng sản phẩm chống nắng cực hot đã \"làm mưa làm gió\" suốt thời gian vừa qua từ thương hiệu Sunplay. Với khả năng chống nắng cực đỉnh SPF50+, PA++++, dưỡng ẩm và làm sáng da, kết hợp các hạt ngọc trai siêu mịn giúp che phủ tự nhiên, nâng tone da sáng hồng trong suốt, Tone Up UV Lavender thật sự đã lắm đắm say bao trái tim. Nối tiếp thành công của Lavender, Skin Aqua tiếp tục cho ra mắt Tone Up UV phiên bản Mint Green mới, dành tặng những nàng có làn da ửng đỏ, thiếu tự tin vui chơi dưới nắng hè.', 6, 5),
(112, 'ữa Chống Nắng Sunplay Kiềm Dầu, Hiệu Chỉnh Sắc Da 50g', 154000, 'https://media.hasaki.vn/catalog/product/s/u/sua-chong-nang-hieu-chinh-sac-da-sunplay-50g-001_img_80x80_d200c5_fit_center.jpg', 'Tia tử ngoại (tia UV) có thành phần chính là tìa UV-A và UV-B. Nếu như tia UV-A là nguyên nhân gây chảy xệ, ung thư da,...thì tia UV-B là nguyên nhân gây nên hiện tượng bỏng da, cháy nắng. Chính vì vậy, việc sử dụng các sản phẩm chống nắng đúng cách hàng ngày là điều cực kỳ quan trọng. Tại Hasaki, Sunplay Skin Aqua Tone Up UV Milk SPF50+/PA++++ được biết đến như một loại sữa dưỡng không những giúp bảo vệ da trước tia UV hiệu quả, mà còn mang lại khả năng hiệu chỉnh làn da cực tốt, giúp làn da trở nên đều màu, tươi tắn và rạng rỡ hơn.', 6, 5),
(113, 'Sữa Chống Nắng Sunplay Dưỡng Da Ngăn Ngừa Mụn 25g', 96000, 'https://media.hasaki.vn/catalog/product/s/u/sua-chong-nang-duong-da-sunplay-ngan-ngua-mun-spf50-pa-25g-04_img_80x80_d200c5_fit_center.jpg', 'Với khí hậu oi bức ở Việt Nam, đặc biệt là trong những ngày hè tiết trời nắng nóng, ánh nắng chói chang, bỏng rát như thế này thì việc sử dụng kem chống nắng khi ra ngoài là điều vô cùng cần thiết. Thoa kem chống nắng là một trong những bước quan trọng trong quy trình chăm sóc, làm đẹp và bảo vệ làn da giúp da chống lại những tác động xấu của ánh nắng mặt trời, không những thế ánh nắng mặt trời còn là nguyên nhân gây nên các bệnh ung thư da cực kỳ nguy hiểm', 6, 5),
(114, 'Kem Chống Nắng Dưỡng Thể Sunplay Dưỡng Sáng Mịn Da 150g', 179000, 'https://media.hasaki.vn/catalog/product/k/e/kem-chong-nang-duong-the-trang-min-sunplay-spf-50-pa-150g_img_80x80_d200c5_fit_center.jpg', 'Kem Chống Nắng Dưỡng Thể Sunplay Skin Aqua UV Body Lotion SPF 50+ PA++++ 150g từ thương hiệu Sunplay của Nhật giúp bảo vệ làn da cơ thể khỏi tác hai của tia UV đồng thời mang lại thêm công dụng dưỡng trắng, làm mát da hiệu quả. Kem chống nắng Sunplay mới ra mắt dòng sản phẩm dành riêng cho cơ thể với dung tích lớn giúp bạn tiết kiệm hơn và bảo vệ cũng như dưỡng da hiệu quả nhất.', 6, 5),
(115, 'Sữa Chống Nắng Sunplay Dưỡng Da Sáng Mịn 25g', 98000, 'https://media.hasaki.vn/catalog/product/s/u/sua-chong-nang-sunplay-duong-da-sang-min-spf50-pa-25g-03_img_80x80_d200c5_fit_center.jpg', 'Với đặc tính kháng nước và mồ hôi cao, Skin Aqua Clear White luôn bảo đảm được cả hai tác động đó là vừa bảo vệ da vừa nuôi dưỡng da khi phải vận động liên tục ngoài trời hoặc dưới nước nhờ thành phần bổ sung Vitamin C, Vitamin E, Pro Vitamin B5, Hyaluronic Acid, giữ ẩm và nuôi dưỡng làn da một cách hiệu quả. Hơn nữa, công thức cải tiến không nhờn rít, dịu nhẹ và nhanh chóng thấm vào da, sản phẩm cho bạn cảm giác mát lạnh, nhẹ tênh, thích hợp cho cả da mặt.', 6, 5),
(116, 'Tinh Chất Chống Nắng Sunplay Dưỡng Ẩm, Nâng Tông Da 50g', 147000, 'https://media.hasaki.vn/catalog/product/t/i/tinh-chat-chong-nang-duong-am-nang-tong-che-khuyet-diem-sunplay-spf50-pa-50g-5_img_80x80_d200c5_fit_center.jpg', 'Skin Aqua Tone Up UV là dòng sản phẩm chống nắng cực hot đã \"làm mưa làm gió\" suốt thời gian vừa qua từ thương hiệu Sunplay. Với khả năng chống nắng cực đỉnh SPF50+, PA++++, dưỡng ẩm và làm sáng da, kết hợp các hạt ngọc trai siêu mịn giúp che phủ tự nhiên, nâng tone da sáng hồng trong suốt, Tone Up UV Lavender thật sự đã lắm đắm say bao trái tim. Nối tiếp thành công của Lavender, Skin Aqua tiếp tục cho ra mắt Tone Up UV phiên bản Mint Green mới, dành tặng những nàng có làn da ửng đỏ, thiếu tự tin vui chơi dưới nắng hè.', 6, 5),
(117, 'Combo Sunplay Tinh Chất Chống Nắng 50g + Sữa Chống Nắng 30g', 179000, 'https://media.hasaki.vn/catalog/product/c/o/combo-sunplay-tinh-chat-chong-nang-spf50-pa-50g-sua-chong-nang-spf81-pa-30g_img_80x80_d200c5_fit_center.jpg', 'Tinh Chất Chống Nắng Sunplay Skin Aqua Tone Up UV Essence SPF50+/PA++++ có khả năng chống nắng tối ưu với các thành phần chống nắng ưu việt, giúp bảo vệ da suốt nhiều giờ liền:\n\nSPF50+: ngăn ngừa tác hại của tia UVB (ngăn chặn các hiện tượng rát da, cháy nắng,…)\nPA++++: ngăn ngừa tác hại của tia UVA (giúp bảo vệ da, ngăn chặn đen sạm, nám, tàn nhang, nếp nhăn, đốm nâu,…)', 6, 5),
(118, 'Sữa Chống Nắng Sunplay Dưỡng Ẩm, Bảo Vệ Da Hằng Ngày 70g', 132000, 'https://media.hasaki.vn/catalog/product/s/u/sua-chong-nang-duong-am-sunplay-skin-aqua-spf-50-pa-70g-02_2_img_80x80_d200c5_fit_center.jpg', 'Ánh nắng mặt trời chính là nỗi khiếp sợ của tất cả các chị em. Không chỉ là tác nhân gây nên nám, tàn nhang, nó còn khiến da dẻ trở nên nhăn nheo hơn. Vậy nên, bạn có thể không tô son, có thể không đánh phấn nhưng đừng bao giờ quên bôi kem chống nắng, đặc biệt là mỗi khi hè về nhé. Hôm nay, Hasaki.vn xin giới thiệu đến các bạn Sữa Chống Nắng Giữ Ẩm Da Sunplay Skin Aqua UV Moisture Milk với chỉ số SPF50+ PA++++ có tác dụng đặc biệt là dưỡng da và giúp cân bằng độ ẩm tự nhiên cho làn da cũng như duy trì cho làn da mềm mịn mỗi ngày, đồng thời bảo vệ hiệu quả cho làn da bạn trước tác hại của ánh nắng mặt trời', 6, 5),
(119, 'Xịt Chống Nắng Kháng Bụi Sunplay SPF50+ PA++++ 50g', 127000, 'https://media.hasaki.vn/catalog/product/x/i/xit-chong-nang-khang-bui-sunplay-spf50-pa-50g-05_1_img_80x80_d200c5_fit_center.jpg', 'Trong tình hình không khí ngày càng ô nhiễm như hiện nay, đặc biệt là khi chỉ số bụi mịn PM2.5 tăng cao báo động thì không chỉ sức khỏe của chúng ta bị ảnh hưởng xấu mà làn da cũng chịu nhiều tổn thương không nhỏ. Do đó, bên cạnh việc bảo vệ da trước tia UV, nhiều loại kem chống nắng tiên tiến đã được bổ sung thêm khả năng kháng bụi mịn, kháng ô nhiễm nhằm bảo vệ làn da khỏi tác hại của môi trường', 6, 5),
(120, 'Sữa Chống Nắng Sunplay Ngăn Sạm Da, Bảo Vệ Vượt Trội 30g', 75000, 'https://media.hasaki.vn/catalog/product/s/u/sua-chong-nang-duong-da-sunplay-spf81-pa-30g-01_img_80x80_d200c5_fit_center.jpg', 'Sản phẩm chính là sự lựa chọn phù hợp dành cho những bạn đi biển bởi tính kháng nước và mồ hôi cao, Sunplay Super Block bảo đảm hai tác động vừa bảo vệ da vừa nuôi dưỡng da khi phải vận động liên tục ngoài trời hoặc dưới nước nhờ thành phần bổ sung Vitamin C, Vitamin E, Pro Vitamin B5, Hyaluronic Acid, giữ ẩm và nuôi dưỡng làn da một cách hiệu quả. Dung dịch nhanh chóng thấm vào da, không gây nhờn rít, cho bạn cảm giác mát lạnh trên da, và thích hợp cho cả da mặt hay toàn thân.', 6, 5),
(121, 'Sữa Chống Nắng Trang Điểm Sunplay SPF50+ PA++++ 25g', 109000, 'https://media.hasaki.vn/catalog/product/s/u/sua-chong-nang-trang-diem-sunplay-spf50-pa-25g-004_img_80x80_d200c5_fit_center.jpg', 'Hasaki.vn cũng xin giới thiệu đến các bạn sữa chống nắng trang điểm Skin Aqua-Clear White CC Milk với chỉ số SPF50+ PA++++ sẽ bảo vệ toàn diện cho làn da bạn khỏi tác hại của ánh nắng mặt trời. Bạn có thể sử dụng sản phẩm như một lớp nền trang điểm nhẹ vừa có tác dụng chống nắng vừa có thể dưỡng da mà lại cho bạn cảm giác nhẹ nhàng, không bết dính và kiểm soát dầu rất tốt.', 6, 5),
(122, 'Gel Dưỡng Sáng Da Sunplay Chống Nắng SPF50 PA++++ 70g', 143000, 'https://media.hasaki.vn/catalog/product/g/e/gel-duong-sang-da-sunplay-chong-nang-spf50-pa-70g-02_img_80x80_d200c5_fit_center.jpg', 'Skin Aqua Silky White mang lại cho bạn cảm giác nhẹ nhàng, mượt mà như tơ với các hạt phấn siêu mịn có tác dụng kiểm soát dầu và giữ cho bề mặt da trơn láng. Với đặc tính kháng nước và mồ hôi cao, Skin Aqua Silky White luôn bảo đảm được cả hai tác động đó là vừa bảo vệ da vừa nuôi dưỡng da khi phải vận động liên tục ngoài trời hoặc dưới nước nhờ thành phần bổ sung Vitamin C, Vitamin E, Pro Vitamin B5, Hyaluronic Acid, giữ ẩm và nuôi dưỡng làn da một cách hiệu quả.', 6, 5),
(123, 'Sữa Chống Nắng Làm Mát Da Sunplay SPF50+/PA++++ 30g', 77000, 'https://media.hasaki.vn/catalog/product/g/o/google-shopping-sua-chong-nang-lam-mat-da-sunplay-spf50-pa-30g_img_80x80_d200c5_fit_center.jpg', 'Sunplay là thương hiệu thuộc tập đoàn Rohto-Mentholatum được thành lập từ năm 1899 tại Osaka, Nhật Bản. Qua hơn 100 năm có mặt trên thị trường, đến nay thương hiệu Rohto-Mentholatum đã phát triển và mở rộng việc kinh doanh trên cả 5 châu lục với 11 chi nhánh trên khắp thế giới, sở hữu một loạt thương hiệu con chuyên về lĩnh vực dược mỹ phẩm chăm sóc sức khỏe. Trong đó có chi nhánh Rohto Việt Nam được thành lập năm 1997 chịu trách nhiệm sản xuất cho toàn bộ khu vực Châu Á Thái Bình Dương. Dòng kem chống nắng nhãn hiệu Sunplay rất được người tiêu dùng ưa chuộng bởi chất lượng sản phẩm tốt, giá thành phải chăng, chỉ số SPF cao và an toàn cho da.', 6, 5),
(124, 'Chì Vặn Kẻ Mắt Vacosi', 77000, 'https://media.hasaki.vn/catalog/product/c/h/chi-van-ke-mat-vacosi_img_80x80_d200c5_fit_center.jpg', 'Mặc dù xu hướng kẻ mắt đang dần trở nên phổ biến và quen thuộc với mọi cô gái, nhưng việc phân biệt và lựa chọn một sản phẩm thích hợp với mình là điều không hề dễ dàng. Nếu bạn là cô gái đang từng bước làm quen với việc kẻ mắt thì kẻ mắt dạng bút chì chính là sản phẩm bạn nghĩ đến đầu tiên đấy. Loại kẻ mắt này ngoài việc giúp bạn dễ dàng luyện tập và làm quen với các bước, các kiểu kẻ mắt thường thấy. Và chì vặn kẻ mắt của thương hiệu Vacosi sẽ là một lựa chọn không thể nào hoàn hảo hơn cho các bạn đấy', 5, 10),
(125, 'Bấm Gốc Mi Vàng Vacosi', 89000, 'https://media.hasaki.vn/catalog/product/b/a/bam-goc-mi-vang-vacosi_img_80x80_d200c5_fit_center.jpg', 'Lông mi là một phần quan trọng quyết định sự thu hút của đôi mắt, và dùng kẹp mi là một trong những cách \"ăn gian\" giúp đôi mắt của bạn trở nên to tròn đáng yêu và khiến hàng mi của bạn sẽ cong vút tự nhiên suốt cả ngày dài. Là dụng cụ cần thiết không thể thiếu trong túi đồ trang điểm của bạn gái, Bấm Gốc Mi Vàng Vacosi Pro Artist Curler với thiết kế đẹp mắt cùng tay cầm dễ dàng thao tác sẽ giúp tạo hàng mi cong mượt tự nhiên trước khi bạn chuốt mascara cho đôi mắt thêm cuốn hút. Sản phầm hiện đã có mặt tại Hasaki với mức giá hợp lý.', 5, 10),
(126, 'Bấm Mi Vàng Vacosi', 100000, 'https://media.hasaki.vn/catalog/product/b/a/bam-mi-vang-vacosi_img_80x80_d200c5_fit_center.jpg', 'Sở hữu làn mi dài đẹp cong vút tự nhiên là mơ ước của mọi cô nàng, nhưng không phải ai cũng may mắn như thế, nếu hàng lông mi cứ cụp xuống sẽ khiến đôi mắt của bạn trở nên nhỏ và buồn hơn. Tuy nhiên, để khắc phục điều này cho những làn mi ngắn và thưa cũng vẫn có rất nhiều cách. Phổ biến vẫn là sử dụng mascara, nhưng để tạo được hiệu ứng hàng mi cong vút gợi cảm thì trước khi chuốt mascara bạn vẫn phải dùng một dụng cụ khác để làm cong mi đó chính là kẹp bấm mi. Chính vì thế, hôm nay Hasaki.vn xin giới thiệu với các bạn sản phẩm Bấm mi vàng Pro Classic Curler của thương hiệu Vacosi.\n\n', 5, 10),
(127, 'Keo Dán Mi Vacosi', 130000, 'https://media.hasaki.vn/catalog/product/k/e/keo-dan-mi-vacosi9_img_80x80_d200c5_fit_center.jpg', 'Sở hữu làn mi dài đẹp cong vút tự nhiên là mơ ước của mọi cô nàng, nhưng không phải ai cũng may mắn như thế. Tuy nhiên, để khắc phục cho những làn mi ngắn và thưa cũng vẫn có rất nhiều cách. Nếu chuốt mascara vẫn chưa đủ đối với bạn thì bạn vẫn có thể làm dày cho hàng mi bằng cách sử dụng mi giả. Để hỗ trợ bạn trong quá trình sư dụng mi giả thì Hasaki.vn xin giới thiệu với bạn sản phẩm keo dán mi giả Natural Studio Eyelash 3D Adhesive đến từ thương hiệu mỹ phẩm Hàn Quốc – Vacosi với hiệu quả bám dính cao mà không hề gây hại cho mi, bạn có thể yên tâm về chất lượng và độ tin cậy của sản phẩm.', 5, 10),
(128, 'Cọ Phủ Phấn Mắt Vacosi E15', 31000, 'https://media.hasaki.vn/catalog/product/c/o/co-phu-phan-mat-vacosi-e15_img_80x80_d200c5_fit_center.jpg', 'Nhiều người nghĩ rằng chỉ cần một cây cọ mềm rồi đánh phấn mắt là được, nhưng họ không biết rằng cọ má có nhiều loại. Để điều chỉnh được lượng phấn được tán ra trên đôi mắt, cần phụ thuộc vào hình dáng của đầu cọ, chất lượng của lông cọ và kích thước cọ. Cọ Phủ Phấn Mắt E15 của thương hiệu mỹ phẩm Vacosi sẽ là một lựa chọn hoàn hảo cho các bạn đang muốn tìm cho mình một cây cọ phủ phấn mắt tốt nhất.\n\n', 5, 10),
(129, 'Phấn Má Hồng Vacosi OR08 Dewy Peach 8g', 155000, 'https://media.hasaki.vn/catalog/product/p/h/phan-ma-hong-vacosi-or08-dewy-peach-8g_img_80x80_d200c5_fit_center.jpg', 'Bên cạnh việc tô son, đánh má hồng cũng là bước trang điểm không thể bỏ qua nếu bạn muốn khuôn mặt của mình trông tươi tắn, rạng ngời và thu hút hơn. Không những vậy, má hồng là điểm nhấn quan trọng làm nổi bật thần thái và khí chất của bạn, thậm chí nếu đánh má hồng đúng cách sẽ có thể đánh lừa ảo giác, giúp khuôn mặt thon gọn và có đường nét hơn.', 6, 10),
(130, 'Cọ Định Hình Mày Đầu Bằng Vacosi EP03', 90000, 'https://media.hasaki.vn/catalog/product/c/o/co-ke-vien-mat-dau-bang-vacosi-ep03_img_80x80_d200c5_fit_center.jpg', 'Những ai đã và đang tập tành makeup hẳn sẽ hiểu được tầm quan trọng của cặp lông mày. Tuy nhỏ nhưng cặp lông mày lại giúp định hình gương mặt và cũng là yếu tố quyết định thần thái của gương mặt. Nếu bạn đang tìm kiếm một sản phẩm giúp định hình dáng chân mày sao cho gọn và chính xác, thì Cọ Định Hình Mày Đầu Bằng Vacosi EP03 chính là một lựa chọn phù hợp. Sản phẩm có thiết kế với đầu cọ bằng, mỏng, giúp tạo ra những đường nét tinh tế và chính xác, dễ dàng tạo cho bạn một đôi chân mày đẹp sắc nét nhưng vẫn tự nhiên. Sản phẩm hiện đã có mặt tại Hasaki.', 5, 10),
(131, 'Bút Kẻ Mày Tatoo Vacosi 03 Dark Brown 3g', 88000, 'https://media.hasaki.vn/catalog/product/b/u/but-ke-may-tatoo-vacosi-03-dark-brown-3g_img_80x80_d200c5_fit_center.jpg', 'Bút Kẻ Mày Vacosi Realbrow Tattoo Pen được xem là sản phẩm không thể thiếu dành cho các cô nàng bận rộn, giữ cho cặp mày của bạn sắc nét trong suốt ngày dài. Các bạn gái hiện nay đang ưa chuộng các dòng kẻ mày tattoo như thế này lắm nhé! Sản phẩm với thiết kế bút dạ 4 điểm cho từng nét vẻ trở nên tự nhiên và thanh mảnh, cùng khả năng bám màu cực lâu, không trôi khi tiếp xúc với nước, đảm bảo sẽ khiến bạn hài lòng.', 5, 10),
(132, 'Lông Mi Giả Chuyên Nghiệp 4D VACOSI ED01', 60000, 'https://media.hasaki.vn/catalog/product/l/o/long-mi-4d-vacosi-ed01_1_img_80x80_d200c5_fit_center.jpg', 'Lông Mi Giả Chuyên Nghiệp 4D VACOSI 4D Pro Eyelashes đến từ VACOSI - nhãn hiệu mỹ phẩm trang điểm chuyên nghiệp của Hàn Quốc, với chất liệu cao cấp được thiết kế thủ công, tạo ra những sợi mi mỏng nhẹ tự nhiên, cho hiệu ứng 4D ấn tượng, đem đến hàng mi dày, dài và cong vút mà không làm nặng mắt, góp phần tăng thêm sự quyến rũ và thu hút ngay từ ánh nhìn đầu tiên.\n\n', 5, 10),
(133, 'Bảng Tạo Khối Chuyên Nghiệp Vacosi 12HS 12 Ô 45g', 379000, 'https://media.hasaki.vn/catalog/product/b/a/bang-tao-khoi-chuyen-nghiep-vacosi-12hs-12-o-45g_img_80x80_d200c5_fit_center.jpg', 'Bảng Tạo Khối Chuyên Nghiệp Vacosi 12HS 12 Ô Contour Highlight & Shading là sản phẩm đến từ thương hiệu Vacosi của Hàn Quốc. Sở hữu sản phẩm bạn sẽ không cần phải mua thêm bất cứ sản phẩm bắt sáng hay tạo khối nào khác. Sản phẩm thích hợp với mọi phong cách trang điểm khác nhau.\n\n', 6, 10),
(134, 'Bông Phấn Ướt Hình Oval Tròn Vacosi BP17', 40000, 'https://media.hasaki.vn/catalog/product/b/o/bong-phan-uot-hinh-oval-tron-vacosi-bp17_img_80x80_d200c5_fit_center.jpg', 'Để trở thành một chuyên gia trang điểm, bạn cần sử dụng bông phấn thay vì bằng tay vì tay là nơi tiếp xúc với rất nhiều nguồn vi khuẩn. Mặt khác, sử dụng tay sẽ rất khó để tán đều phấn lên vùng mặt, phấn có thể bám chỗ dày chỗ mỏng, trông không đều màu. Bông Phấn Ướt Hình Oval Tròn BP17 của hãng sản xuất mỹ phẩm nổi tiếng Vacosi là một sản phẩm được rất nhiều pháp đẹp yêu thích dùng để đánh phấn lót và phấn nền dạng nước và kem, giúp tán đều phấn và mang lại một lớp nền hoàn hảo cho những lớp trang điểm tiếp theo.', 6, 10),
(135, 'Phấn Mắt Vacosi 4 Ô 08 Teddy Brown', 155000, 'https://media.hasaki.vn/catalog/product/p/h/phan-mat-vacosi-08-teddy-brown_img_80x80_d200c5_fit_center.jpg', 'Giống như son môi, phấn mắt chính là “vũ khí” khiến cho đôi mắt trông to hơn, sâu hơn, đậm nét hơn và mang đến cho bạn nhiều sự biến tấu về màu sắc. Phấn Mắt Vacosi 4 Ô Square Eyeshadow hội tụ nhiều bảng màu khác nhau, là công cụ không thể thiếu trong bộ đồ nghề trang điểm của bạn.', 6, 10);

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
(6, 'Duy', 'Pham', 'phamhjuynhquocduy@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '0394668337', 0, 2, '280 An Dương Vương Phường 4 Quận 5 TP.Hồ Chí Minh');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thuonghieu`
--

CREATE TABLE `thuonghieu` (
  `id` int(11) NOT NULL,
  `tenthuonghieu` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `thuonghieu`
--

INSERT INTO `thuonghieu` (`id`, `tenthuonghieu`) VALUES
(1, 'Maybelline'),
(2, 'M.A.C'),
(3, 'B.O.M'),
(4, 'Bbia'),
(5, 'Sunplay'),
(6, 'Za'),
(7, 'LANEIGE'),
(8, 'Eucerin'),
(9, 'L\'OREAL'),
(10, 'VACOSI');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `yeuthich`
--

CREATE TABLE `yeuthich` (
  `idtaikhoan` int(11) NOT NULL,
  `idsanpham` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
-- Chỉ mục cho bảng `thuonghieu`
--
ALTER TABLE `thuonghieu`
  ADD PRIMARY KEY (`id`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `loaitaikhoan`
--
ALTER TABLE `loaitaikhoan`
  MODIFY `STT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=136;

--
-- AUTO_INCREMENT cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `mataikhoan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `thuonghieu`
--
ALTER TABLE `thuonghieu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD CONSTRAINT `donhang_ibfk_1` FOREIGN KEY (`idkhachhang`) REFERENCES `taikhoan` (`mataikhoan`);

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`idsanpham`) REFERENCES `loaisanpham` (`id`);

--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`maloaitk`) REFERENCES `loaitaikhoan` (`STT`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
