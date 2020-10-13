<?php
    include("connect.php");
    $id=$_POST[$id];
	$result= "SELECT * FROM sanpham WHERE idcha'$id'";
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
            $row['idsanpham'],));
	}
	class Sanphammoinhat{
		function Sanphammoinhat($id,$tensp,$giasp,$hinhanhsp,$motasp,$idsanpham){
			$this->id=$id;
			$this->tensp=$tensp;
			$this->giasp=$giasp;
			$this->hinhanhsp=$hinhanhsp;
			$this->motasp=$motasp;
			$this->idsanpham=$idsanpham;
		}
	}
	echo json_encode($mangloaisanphammoinhat);
?>