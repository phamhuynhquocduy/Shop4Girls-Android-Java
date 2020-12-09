<?php
	include("connect.php");
	$idkhachhang=$_POST['idkhachhang'];
	$mangsanpham=array();
	$query="SELECT s.id,s.tensanpham,s.hinhanhsanpham,s.giasanpham,c.soluongsanpham,c.tientungsanpham, c.trangthai
			FROM donhang d,chitiecdonhang c,sanpham s
			WHERE d.id=c.madonhang AND c.masanpham=s.id AND d.idkhachhang=$idkhachhang";
	$data=$con->query($query);
	$mangsanpham=array();
	while($row = $data->fetch_assoc()){
		array_push($mangsanpham,
		new Sanpham(
		$row['id'],	
		$row['tensanpham'],
		$row['hinhanhsanpham'],
		$row['giasanpham'],
		$row['soluongsanpham'],
		$row['tientungsanpham'],
		$row['trangthai']));
	}
	class Sanpham{
		function Sanpham($idsp,$tensp,$hinhanhsp,$giasp,$soluongsanpham,$tientungsanpham,$trangthai){
			$this->idsp=$idsp;
			$this->tensp=$tensp;
			$this->hinhanhsp=$hinhanhsp;
			$this->giasp=$giasp;
			$this->soluongsanpham=$soluongsanpham;
			$this->tientungsanpham=$tientungsanpham;
			$this->trangthai=$trangthai;
		}
	}
	echo json_encode($mangsanpham);