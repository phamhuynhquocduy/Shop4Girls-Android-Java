<?php
		include("connect.php");
		$taikhoan=$_POST['taikhoan'];
		$matkhau=md5($_POST['matkhau']);
		$sql="SELECT * FROM taikhoan WHERE email = '$taikhoan' AND matkhau = '$matkhau' ";
		$Dta=$con->query($sql);
		$row=$Dta->fetch_assoc();
		if($row!=null){
			echo $row['mataikhoan'];
		}else{
			echo "error";
		}
		
?>