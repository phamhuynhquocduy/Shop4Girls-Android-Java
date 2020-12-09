<?php
	include("connect.php");
	$id=$_POST['idkhachhang'];
	$tongtien=$_POST['tongtien'];
	$today = date('Y-m-d');
	$week = strtotime(date("Y-m-d", strtotime($today)) . " +0 day");
	$week = strftime("%Y-%m-%d", $week);
	$sql="INSERT INTO donhang(id,idkhachhang,TONGTIEN,NgayThanhToan) VALUES(null,'$id','$tongtien','$week')";
	if($con->query($sql)){
		$iddonhang=$con->insert_id;
		echo $iddonhang;
	}else{
		echo "error";
	}
?>