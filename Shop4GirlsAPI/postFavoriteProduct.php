<?php
	include("connect.php");
	$idtaikhoan=$_POST['idtaikhoan'];
    $idsanpham=$_POST['idsanpham'];
	$sql="INSERT INTO yeuthich(idtaikhoan,idsanpham) VALUES('$idtaikhoan','$idsanpham')";
	if($con->query($sql)){
		echo "success";
	}else{
		echo "error";
	}
?>