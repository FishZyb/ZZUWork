package zyb.Linked;

/**
 * @author 一只鱼zzz
 * @version 1.0
 * 商家信息链表合集
 */
public class ShopLinked {
  //头结点
  ShopNode head;
  //链表的长度
  int size;

  public ShopLinked(){
    this.size=0;
    this.head = new ShopNode(null,0,null,0,0,0,null,0,null,null);
  }

  //商户信息节点对象
  class ShopNode{
    String shopType;//商户类型
    int id;//商户Id
    String shopName;
    int password;
    int avgScore;
    int avePrice;
    String address;
    long phone;
    String[] food;
    String[] comment;

    ShopNode next;

    public ShopNode(String shopType, int id, String shopName, int password, int avgScore, int avePrice, String address, long phone, String[] food, String[] comment) {
      this.shopType = shopType;
      this.id = id;
      this.shopName = shopName;
      this.password = password;
      this.avgScore = avgScore;
      this.avePrice = avePrice;
      this.address = address;
      this.phone = phone;
      this.food = food;
      this.comment = comment;

      next = null;
    }
  }
}
