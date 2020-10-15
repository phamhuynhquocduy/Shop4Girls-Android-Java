<?php
	include("connect.php");
	$idtaikhoan=$_POST['idtaikhoan'];
    $idsanpham=$_POST['idsanpham'];
	$sql="DELETE FROM yeuthich Where idsanpham=$idsanpham and idtaikhoan=$idtaikhoan";
	if($con->query($sql)){
		echo "success";
	}else{
		echo "error";
	}
?>