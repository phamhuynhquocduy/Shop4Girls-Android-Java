<?php
    include("connect.php");
    $loai=$_GET['page'];
	$resultCategory= "SELECT * FROM loaisanpham WHERE idcha=$loai ";	 
	$data=$con->query($resultCategory);
	$mangLoai=array();
	while($row = $data->fetch_assoc()){
		array_push($mangLoai,
		new Loai(
		$row['id'],
        $row['ten'],
        $row['hinh'],
        $row['idcha']));
	}
	class Loai{
		function Loai($id,$ten,$hinh,$idcha){
			$this->id=$id;
			$this->ten=$ten;
			$this->hinh=$hinh;
			$this->idcha=$idcha;
		}
	}
	echo json_encode($mangLoai);
?>
