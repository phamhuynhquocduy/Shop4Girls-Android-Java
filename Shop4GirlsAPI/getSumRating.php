<?php
		include("connect.php");
		$id=$_POST['id'];
		$sql="SELECT * FROM danhgia WHERE idsanpham = '$id' ";
        $Dta=$con->query($sql);
        $sum=0;
        $count=0;
        foreach($Dta as $value){
            $count+=1;
            $sum+=$value['sodanhgia'];
        }
        $sum/=$count;
		echo $sum
?>