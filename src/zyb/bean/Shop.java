package zyb.bean;

import java.util.List;

/**
 * @author 一只鱼zzz
 * @version 1.0
 */
public class Shop {

  private String shopType;

  private String shopId;

  private String shopName;

  private String shopPassword;

  private String avgScore;

  private String avePrice;

  private String address;

  private String phone;

  private List<Food> foods;

  private List<String> commons;

  public Shop(String shopType, String shopId, String shopName, String shopPassword, String avgScore, String avePrice, String address, String phone, List<Food> foods, List<String> commons) {
    this.shopType = shopType;
    this.shopId = shopId;
    this.shopName = shopName;
    this.shopPassword = shopPassword;
    this.avgScore = avgScore;
    this.avePrice = avePrice;
    this.address = address;
    this.phone = phone;
    this.foods = foods;
    this.commons = commons;
  }

  public String getShopType() {
    return shopType;
  }

  public void setShopType(String shopType) {
    this.shopType = shopType;
  }

  public String getShopId() {
    return shopId;
  }

  public void setShopId(String shopId) {
    this.shopId = shopId;
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public String getShopPassword() {
    return shopPassword;
  }

  public void setShopPassword(String shopPassword) {
    this.shopPassword = shopPassword;
  }

  public String getAvgScore() {
    return avgScore;
  }

  public void setAvgScore(String avgScore) {
    this.avgScore = avgScore;
  }

  public String getAvePrice() {
    return avePrice;
  }

  public void setAvePrice(String avePrice) {
    this.avePrice = avePrice;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public List<Food> getFoods() {
    return foods;
  }

  public void setFoods(List<Food> foods) {
    this.foods = foods;
  }

  public List<String> getCommons() {
    return commons;
  }

  public void setCommons(List<String> commons) {
    this.commons = commons;
  }

  @Override
  public String toString() {
    return "Shop{" +
      "shopType='" + shopType + '\'' +
      ", shopId='" + shopId + '\'' +
      ", shopName='" + shopName + '\'' +
      ", shopPassword='" + shopPassword + '\'' +
      ", avgScore='" + avgScore + '\'' +
      ", avePrice='" + avePrice + '\'' +
      ", address='" + address + '\'' +
      ", phone='" + phone + '\'' +
      ", foods=" + foods +
      ", commons=" + commons +
      '}';
  }
}
