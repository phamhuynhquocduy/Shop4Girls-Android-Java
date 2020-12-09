<?php
		include("connect.php");
		$taikhoan=$_POST['taikhoan'];
		$sql="SELECT * FROM taikhoan WHERE email = '$taikhoan' ";
		$Dta=$con->query($sql);
		$row=$Dta->fetch_assoc();
		if($row!=null){
			echo $row['mataikhoan'];
		}else{
			echo "error";
		}
		
?>