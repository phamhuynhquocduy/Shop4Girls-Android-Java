<?php
    include("connect.php");
	$result= "SELECT * FROM thuonghieu ";
	$data=$con->query($result);
	$mangloaisanphammoinhat=array();
	while($row = $data->fetch_assoc()){
		array_push($mangloaisanphammoinhat,
		new Sanpham(
			$row['id'],
            $row['tenthuonghieu']));
	}
	class Sanpham{
		function Sanpham($id,$ten){
			$this->id=$id;
			$this->ten=$ten;
		}
	}
	echo json_encode($mangloaisanphammoinhat);
?>