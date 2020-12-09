<?php
	include("connect.php");
	$result= "SELECT * FROM sanpham WHERE tongsosao>=4.3";
	$data=$con->query($result);
	$mangloaisanphammoinhat=array();
	while($row = $data->fetch_assoc()){
		array_push($mangloaisanphammoinhat,
		new Sanphammoinhat(
			$row['id'],
            $row['tensanpham'],
            $row['giasanpham'],
            $row['hinhanhsanpham'],
            $row['motasanpham'],
            $row['idsanpham'],
            $row['tongsosao']));
	}
	class Sanphammoinhat{
		function Sanphammoinhat($id,$tensp,$giasp,$hinhanhsp,$motasp,$idsanpham,$sosao){
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