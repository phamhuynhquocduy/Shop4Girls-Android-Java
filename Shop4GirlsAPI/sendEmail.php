<?php
if(isset($_POST['email'])&&isset($_POST['random'])){
    $to      = $_POST['email'];
    $subject = 'Xác Thực Email';
    $message = 'Mã xác thực'+$_POST['random'];
    $headers = 'From: quocduy02082k@gmail.com' . "\r\n" .
    'Reply-To: quocduy02082k@gmail.com' . "\r\n" .
    'X-Mailer: PHP/' . phpversion();
    $result=mail($to, $subject, $message, $headers);
    if(!$result) {   
        echo "error";   
   } else {
       echo "success";
   } 
}
?> 

