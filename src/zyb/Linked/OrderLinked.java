package zyb.Linked;

import com.mysql.cj.x.protobuf.MysqlxCrud;

/**
 * @author 一只鱼zzz
 * @version 1.0
 */
public class OrderLinked {

  //头结点
  OrderNode head;
  //链表的长度
  int size;
  public OrderLinked(){
    this.size=0;
    this.head = new OrderNode(0,null,null);
  }

  public void addHead(int userId,String shopName,String time){
    addAtIndex(0,userId,shopName,time);
  }

  /**
   * 将数据库中的数据保存到链表中
   * @param index
   * @param userId
   * @param shopName
   * @param time
   */
  public void addAtIndex(int index,int userId,String shopName,String time){
    if(index>size){
      return;
    }
    index = Math.max(0,index);
    size++;
    OrderNode pred = head;
    for(int i=0;i<index;i++){
      pred = pred.next;
    }
    OrderNode toAdd = new OrderNode(userId,shopName,time);
    toAdd.next = pred.next;
    pred.next = toAdd;
  }

  /**
   * 根据用户的id查询用户本人所有的预定信息
   * @param id
   */
  public void viewUserReserve(int id){
    OrderNode cur = head;
    for(int i=0;i<size;i++){
      if(cur.userId==id){
        System.out.println("用户"+cur.userId+"预定了:"+cur.shopName+"时间是:"+cur.time);
      }
      cur = cur.next;
    }
  }

  /**
   * 根据用户的id和输入的餐馆名称，查询预约的餐馆的时间
   * @param id
   * @param shopName
   */
  public void viewUserReserveTime(int id,String shopName){
     OrderNode cur = head;
     for(int i=0;i<size;i++){
       if(cur.userId==id&&cur.shopName.equals(shopName)){
         System.out.println("用户"+id+"在该餐馆预约的时间为："+cur.time);
       }
       cur = cur.next;
     }
  }

  /**
   * 在order表中新添加一条预约信息
   * @param id
   * @param shopName
   * @param time
   */
  public void addUserReserveMes(int id,String shopName,String time){
    addAtIndex(0,id,shopName,time);
  }

  /**
   * 根据商户名查询该商户所有的预定信息
   * @param shopName
   */
  public void viewMesByName(String shopName){
    OrderNode cur = head;
    for(int i=0;i<size;i++){
      cur = cur.next;
      if(cur.shopName.equals(shopName)){
        System.out.println("用户名："+cur.userId+"在"+cur.time+"预定了该餐馆");
      }
    }
  }


  //内部类节点对象
  class OrderNode{
    int userId;
    String shopName;
    String time;
    OrderNode next;

    public OrderNode(int userId, String shopName, String time) {
      this.userId = userId;
      this.shopName = shopName;
      this.time = time;
      next = null;
    }
  }

}
