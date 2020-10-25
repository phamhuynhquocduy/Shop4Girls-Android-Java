package com.example.shop4girls.connect;

public class Server {
    public  static String url="https://mshop4girls.000webhostapp.com//";
//    public  static String url="http:/192.168.2.107:8080/Shop4GirlsAPI/";
    public  static String getAccount=url+"getAccount.php";
    public  static String postAccount=url+"postAccount.php";
    public static String getNewProduct=url+"getNewProduct.php";
    public static String getProfile=url+"getProfile.php";
    public static String getCategory = url+"getCategory.php";
    public static String updateProfile=url+"updateProfile.php";
    public static String getProduct=url+"getProduct.php?id=";
    public static String getFavorite=url+"getFavoriteProduct.php";
    public static String postFavorite=url+"postFavoriteProduct.php";
    public static String delFavorite=url+"deleteFavoriteProduct.php";
    public static String getOrderProduct=url+"getOrderProduct.php";
    public static String sendEmail=url+"sendEmail.php";
    public static String getCategoryProduct =url+"getCategoryProduct.php?id=";
}
