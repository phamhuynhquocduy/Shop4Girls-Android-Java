<?php
    include("connect.php");
	$id= (integer) $_POST['id'];
	$ho=$_POST['ho'];
	$ten=$_POST['ten'];
	$email=$_POST['email'];
	$dt=$_POST['dt'];
	$diachi=$_POST['diachi'];
	$gioitinh=$_POST['gioitinh'];
	$sql= "UPDATE taikhoan SET ho='$ho' , sdt='$dt', diachi='$diachi' , gioitinh = $gioitinh, ten='$ten', email='$email' WHERE mataikhoan= '$id' ";
	if($con->query($sql)==TRUE)
	{
		echo "success";
	}
	else 
	{
		echo "error";
	}
?>