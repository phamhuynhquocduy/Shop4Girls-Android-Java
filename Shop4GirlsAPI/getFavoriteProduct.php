<?php
	include("connect.php");
	$mangsanpham=array();
	$idtk=$_POST['idtaikhoan'];
	$query="SELECT sanpham.id,sanpham.tensanpham,sanpham.giasanpham,sanpham.hinhanhsanpham,sanpham.motasanpham,sanpham.idsanpham FROM sanpham inner JOIN yeuthich on sanpham.id = yeuthich.idsanpham where yeuthich.idtaikhoan=$idtk";
	$data=$con->query($query);
	$mangsanpham=array();
	while($row = $data->fetch_assoc()){
		array_push($mangsanpham,
		new Sanpham(
		$row['id'],
		$row['tensanpham'],
		$row['giasanpham'],
		$row['hinhanhsanpham'],
		$row['motasanpham'],
		$row['idsanpham']));
	}
	class Sanpham{
		function Sanpham($id,$tensp,$giasp,$hinhanhsp,$motasp,$idsanpham){
			$this->id=$id;
			$this->tensp=$tensp;
			$this->giasp=$giasp;
			$this->hinhanhsp=$hinhanhsp;
			$this->motasp=$motasp;
			$this->idsanpham=$idsanpham;
		}
	}
	echo json_encode($mangsanpham);
?>