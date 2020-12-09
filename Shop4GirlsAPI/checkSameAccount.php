<?php
	include("connect.php");
    $email=$_POST['email'];
	$kiemtra="SELECT * FROM taikhoan WHERE email='$email'";
	$tontai=$con->query($kiemtra);
	if($tontai->num_rows >= 1){
		echo "error";
	}
?>