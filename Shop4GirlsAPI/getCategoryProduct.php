<?php
    include("connect.php");
	$id=$_GET['id'];
	$result= "SELECT * FROM sanpham WHERE idsanpham='$id' ORDER BY id DESC LIMIT 10";
	$data=$con->query($result);
	$mangloaisanphammoinhat=array();
	while($row = $data->fetch_assoc()){
		array_push($mangloaisanphammoinhat,
		new Sanpham(
			$row['id'],
            $row['tensanpham'],
            $row['giasanpham'],
            $row['hinhanhsanpham'],
            $row['motasanpham'],
			$row['idsanpham'],
			$row['tongsosao']));
	}
	class Sanpham{
		function Sanpham($id,$tensp,$giasp,$hinhanhsp,$motasp,$idsanpham,$sosao){
			$this->id=$id;
			$this->tensp=$tensp;
			$this->giasp=$giasp;
			$this->hinhanhsp=$hinhanhsp;
			$this->motasp=$motasp;
			$this->idsanpham=$idsanpham;
			$this->sosao=$sosao;
		}
	}
	echo json_encode($mangloaisanphammoinhat);
?>