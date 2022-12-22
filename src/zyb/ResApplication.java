package zyb;

import zyb.Linked.OrderLinked;
import zyb.Linked.UserLinked;
import zyb.bean.Shop;
import zyb.utis.GetShopDataUtils;
import zyb.utis.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * @author 一只鱼zzz
 * @version 1.0
 */
public class ResApplication {

  //用户数据的链表存储结构
  UserLinked userLinked = new UserLinked();

  //Order数据的链表存储结构
  OrderLinked orderLinked = new OrderLinked();

  //连接数据库
  Connection connection = null;
  PreparedStatement preparedStatement = null;
  ResultSet resultSet = null;

  /**
   * 主函数
   * @param args
   */
  public static void main(String[] args) {
    ResApplication resApplication = new ResApplication();
    resApplication.menuSystem();
  }

  /**
   * 初始界面菜单显示
   */
  public void menuSystem(){
    //选择进入用户子系统还是商家子系统
    System.out.println("----------------请选择您的身份----------------");
    System.out.println("输入1：用户");
    System.out.println("输入2：商家");
    System.out.println("输入：");
    //获得输入的选择
    int choice = inInt();
    if(choice==1){
      //调用显示用户子系统
      menuUser();
    }else{
      //调用显示商家子系统
      menuAdmin();
    }
  }

  /**
   * 用户子系统界面显示
   */
  public void menuUser(){
    //先让用户进行登录
    System.out.println("----------------用户系统----------------");
    System.out.println("\t\t\t\t登录（1）");
    System.out.println("\t\t\t\t注册（2）");
    System.out.println("请输入您的选择：");
    int choice = inInt();
    if(choice==1){
      System.out.println("请输入账号：");
      int userName = inInt();
      System.out.println("请输入密码：");
      int password = inInt();
      boolean isUser = userLogin(userName,password);
      //如果账号密码存在并且正确
      if(isUser){
        //将餐馆的预定信息也保存在链表中
        saveOrderData();

        System.out.println("----------------欢迎登录用户管理系统----------------");
        System.out.println("\t\t\t\t个人信息管理(1)");
        System.out.println("\t\t\t\t餐馆查询(2)");
        System.out.println("\t\t\t\t餐馆预定(3)");
//        System.out.println("\t\t\t\t餐馆推荐(4)");
        int userChoice = inInt();
        switch (userChoice){
          case 1:
            menuUserMessage(userName);
            break;
          case 2:
            UserResFid(userName);
            break;
          case 3:
            menuUserResReserve(userName);
            break;
//          case 4:
//            userResRecommend(userName);
//            break;
        }
      }else{
        System.out.println("账号或密码输入错误，请重新登录");
      }
    }else{
      System.out.println("请输入注册账号：");
      int userNameApply = inInt();
      System.out.println("请输入您的密码：");
      int passwordApply = inInt();
    }

  }

  /**
   * 判断用户是否注册，并实现对文本中保存的数据的账号密码验证。
   * @param userName
   * @param password
   * @return
   */
  public  boolean userLogin(int userName,int password){
    String sql = "select * from user";
    try {
      connection = JDBCUtils.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      resultSet = preparedStatement.executeQuery();

      while(resultSet.next()){
        //将从数据库中查询到的数据存储到链表中
        int id = resultSet.getInt(1);
        int usrPs =resultSet.getInt(2);
        long usrPh = resultSet.getLong(3);
        userLinked.addHead(id,usrPs,usrPh);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      try {
        JDBCUtils.close(resultSet,preparedStatement,connection);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    //根据userName查询password，如果匹配正确的话，返回true
    int userLinkedPassword = userLinked.getPassword(userName);
    if(userLinkedPassword==password){
      return true;
    }
    return false;
  }

  /**
   * 个人用户信息管理菜单
   */
  public void menuUserMessage(int userName){
    System.out.println("----------------用户个人信息管理----------------");
    System.out.println("\t\t\t\t查看个人信息(1)");
    System.out.println("\t\t\t\t修改个人信息(2)");
    System.out.println("\t\t\t\t注销个人信息(3)");
    System.out.println("请输入您的选择：");
    int choice = inInt();
    switch (choice){
      case 1:
        viewUserMes(userName);
        break;
      case 2:
        updateUserMes(userName);
        break;
      case 3:
        deleteUserMes(userName);
        break;
    }
  }

  /**
   * 查看用户信息(根据userName查找保存在链表中的数据,然后输出在控制台中)
   */
  public void viewUserMes(int userName){
    userLinked.view(userName);
    menuUserMessage(userName);
  }

  /**
   * 根据用户名先查找到用户信息，然后选择修改信息(将链表中的信息也进行修改)
   */
  public void updateUserMes(int userName){
    System.out.println("用户"+userName+"请输入您想要修改的密码：");
    int newPs = inInt();
    System.out.println("用户"+userName+"请输入您想要修改的联系方式：");
    long newPh = inLong();
    //修改链表中的数据
    userLinked.updateAtId(userName,newPs,newPh);
    //调用数据库，修改数据库中的数据
    String sql = "update user set password = ?,phone = ? where account = ?";
    try {
      connection = JDBCUtils.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1,newPs);
      preparedStatement.setLong(2,newPh);
      preparedStatement.setInt(3,userName);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      try {
        JDBCUtils.close(resultSet,preparedStatement,connection);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    System.out.println("修改成功！");
    menuUserMessage(userName);
  }

  /**
   * 删除用户信息
   */
  public void deleteUserMes(int userName){
    System.out.println("您确定要进行用户注销吗？是（1）/否（2）:");
    int choice = inInt();
    if(choice==1){
      userLinked.deleteAtIndex(userName);
      //然后调用数据库将数据删除
      String sql = "delete from user where account = ?";
      try {
        connection = JDBCUtils.getConnection();
        preparedStatement = connection.prepareStatement(sql);
        //给占位符赋值
        preparedStatement.setInt(1,userName);
      } catch (SQLException e) {
        e.printStackTrace();
      }finally {
        try {
          JDBCUtils.close(resultSet,preparedStatement,connection);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      System.out.println("用户注销成功!");
    }else{
      menuUserMessage(userName);
    }
  }

  /**
   * 用户餐馆查询
   * 将餐馆的名称作为查找的关键字，利用哈希表查找算法来实现，如果该餐馆存在，则返回餐馆的基本信息
   * 以及用户到该餐馆的最短距离（用户的地址为郑州轻工业大学）。
   */
  public static void UserResFid(int userName){
    System.out.println("请输入您想要查找的餐馆名称：");
    String shopName = inString();
    //拿到所有的餐馆的所有信息
    List<Shop> shops = GetShopDataUtils.shops;
    //进行字符串的比对
    for(Shop shop : shops){
      //如果查询到了餐馆名称，在控制台输出餐馆的基本信息
      if(shopName.equals(shop.getShopName())){
        System.out.println(shop);
        //以及用户所在地郑州轻工业大学到该餐馆的距离

        break;
      }
    }

  }

  /**
   * 餐馆预定
   */
  public void menuUserResReserve(int id){
    System.out.println("\t\t\t\t查询用户所有预定(1)");
    System.out.println("\t\t\t\t查询某餐馆的预定(2)");
    System.out.println("\t\t\t\t添加预定(3)");
    System.out.println("\t\t\t\t餐馆推荐(4)");
    int choice = inInt();
    switch (choice){
      case 1:
        findUserReserve(id);
        break;
      case 2:
        userFindResReserve(id);
        break;
      case 3:
        addReserve(id);
        break;
      case 4:
        userResRecommend(id);
        break;

    }
  }

  /**
   * 将数据库中的数据保存到链表中
   */
  public void saveOrderData(){
    String sql = "select * from userOrder";
    try {
      connection = JDBCUtils.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      resultSet = preparedStatement.executeQuery();

      while(resultSet.next()){
        //将从数据库中查询到的数据存储到链表中
        int userId = resultSet.getInt(1);
        String shopName = resultSet.getString(2);
        String time = resultSet.getString(3);
        orderLinked.addHead(userId,shopName,time);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      try {
        JDBCUtils.close(resultSet,preparedStatement,connection);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 查询用户当前所有的预定
   * @param id
   */
  public void findUserReserve(int id){
    orderLinked.viewUserReserve(id);
  }

  /**
   * 通过输入餐馆的名称，查询该用户在该餐馆的预定信息
   */
  public void userFindResReserve(int id){
    System.out.println("请输入您想要查询的餐馆名称：");
    String shopName = inString();
    orderLinked.viewUserReserveTime(id,shopName);
  }

  /**
   * 输入餐馆的名称，如果餐馆在shop文件中存在，那么增加一条新的预定信息到order表中。
   */
  public static void addReserve(int id){
    System.out.println("请输入您想预定的餐馆名称：");
    String shopName = inString();
  }

  /**
   * 用户餐馆推荐
   */
  public static void userResRecommend(int id){

  }

  /**
   * 商家管理员子系统界面显示
   */
  public static void menuAdmin(){
    System.out.println("----------------商家登录系统----------------");
    System.out.println("请输入账号：");
    int adminName = inInt();
    System.out.println("请输入密码：");
    int adminPassword = inInt();
    boolean isAdmin = adminLogin(adminName,adminPassword);
    if(isAdmin){
      System.out.println("----------------欢迎登录商家管理系统----------------");
      System.out.println("\t\t\t\t查询餐馆预定（1）");
      System.out.println("\t\t\t\t查询用户预定（2）");
      System.out.println("\t\t\t\t处理预定（3）");
      int adminChoice = inInt();
      switch (adminChoice){
        case 1:
          findResReserve();
          break;
        case 2:
          findUserResReserve();
          break;
        case 3:
          disPoseReserve();
      }
    }
  }

  /**
   * 验证商家登录
   * @param adminName
   * @param adminPassword
   * @return
   */
  public static boolean adminLogin(int adminName,int adminPassword){
    return true;
  }

  /**
   * 商家查询餐馆预定
   */
  public static void findResReserve(){

  }

  public static void findUserResReserve(){

  }

  /**
   * 商家处理预定
   */
  public static void disPoseReserve(){

  }

  /**
   * 在控制台输入一个数字，并将这个数字返回
   * @return
   */
  public static int inInt(){
    Scanner in = new Scanner(System.in);
    int choice = in.nextInt();
    return choice;
  }

  public static long inLong(){
    Scanner in = new Scanner(System.in);
    long choice = in.nextLong();
    return choice;
  }

  public static String inString(){
    Scanner in = new Scanner(System.in);
    String choice = in.nextLine();
    return choice;
  }




}
