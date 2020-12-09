<?php
    include("connect.php");
	$id= (integer) $_POST['id'];
	$pass=md5($_POST['password']);
	$sql= "UPDATE taikhoan SET matkhau='$pass' WHERE mataikhoan='$id'";
	if($con->query($sql)==TRUE)
	{
		echo "success";
	}
	else 
	{
		echo "error";
	}
?>