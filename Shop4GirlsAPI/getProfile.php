<?php
    include("connect.php");
    $id=$_POST['id'];
	$result= "SELECT * FROM taikhoan WHERE mataikhoan= '$id' ";
	$data=$con->query($result);
	$mangLoai=array();
	while($row = $data->fetch_assoc()){
		array_push($mangLoai,
		new Taikhoan(
		$row['ho'],
        $row['ten'],
        $row['email'],
        $row['sdt'],
        $row['matkhau'],
        $row['gioitinh'],
        $row['diachi']));
	}
	class Taikhoan{
		function Taikhoan($ho,$ten,$email,$dienthoai,$matkhau,$gioitinh,$diachi){
			$this->ho=$ho;
            $this->ten=$ten;
            $this->email=$email;
            $this->dienthoai=$dienthoai;
            $this->matkhau=$matkhau;
            $this->gioitinh=$gioitinh;
            $this->diachi=$diachi;
		}
	}
	echo json_encode($mangLoai);
?>