<?php
	include("connect.php");
	$json=$_POST['json'];
	$data=json_decode($json,true);
	foreach($data as $value){
		$idssanpham=$value['idsanpham'];
		$sosao=$value['sodanhgia'];
        $idkhachhang=$value['idkhachhang'];
        $today = date('Y-m-d');
	    $week = strtotime(date("Y-m-d", strtotime($today)) . " +0 day");
	    $week = strftime("%Y-%m-%d", $week);
		$query="INSERT INTO danhgia(id,idsanpham,sodanhgia,ngaydanhgia,idkhachhang) 
		VALUES (null,'$idsanpham','$sosao','$soluongsanpham','$week','$idkhachhang')";
		$Dta=$con->query($query);
	}
	if($Dta){
		echo "success";
	}else{
		echo "error";
	}
	
?>