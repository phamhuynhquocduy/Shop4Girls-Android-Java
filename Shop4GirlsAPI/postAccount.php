<?php
	include("connect.php");
	$ho=$_POST['ho'];
	$ten=$_POST['ten'];
	$email=$_POST['email'];
	$matkhau=md5($_POST['matkhau']);
	$sdt=$_POST['dt'];
	$gioitinh=$_POST['gioitinh'];
	$kiemtra="SELECT * FROM taikhoan WHERE email='$email'";
	$dchi=$_POST['diachi'];
	$tontai=$con->query($kiemtra);
	if($tontai->num_rows >= 1){
		echo "Tài Khoản Đã Tồn Tại";
	}else{
		$sql="INSERT INTO taikhoan  VALUES(null,'$ho','$ten','$email','$matkhau','$sdt','$gioitinh',2,'$dchi')";
		$Dta=$con->query($sql);
		if($Dta){
		echo "success";
		}else{
		echo "error";
		}
	}
?>