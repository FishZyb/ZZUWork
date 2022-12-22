package zyb.Linked;

/**
 * @author 一只鱼zzz
 * @version 1.0
 * 用户数据的链表存储
 */
public class UserLinked {

  //头结点
  UserNode head;
  //链表的长度
  int size;

  public UserLinked(){
    this.size=0;
    this.head = new UserNode(0,0,0);
  }

  /**
   * 返回第index位置的节点的userName值
   * @param index
   * @return
   */
  public int getUserName(int index){
    if(index<0||index>size){
      return -1;
    }
    UserNode cur = head;
    for(int i=0;i<index;i++){
      cur = cur.next;
    }
    return cur.userName;
  }

  /**
   * 根据userName的值，匹配到链表中的密码，并将密码返回
   * @param userName
   * @return
   */
  public int getPassword(int userName){
    UserNode cur = head;
    if(cur.userName==userName){
      return cur.password;
    }
    for(int i=0;i<size;i++){
      cur = cur.next;
      if(cur.userName==userName){
        return cur.password;
      }
    }
    return -1;
  }

  /**
   * 在链表的第一个元素之前添加一个结点，值为以下三个
   * @param userName
   * @param password
   * @param phone
   */
  public void addHead(int userName,int password,long phone){
    addAtIndex(0,userName,password,phone);
  }

  /**
   * 在链表的第index个节点之前添加值为下列三个参数的节点
   * @param index
   * @param userName
   * @param password
   * @param phone
   */
  public void addAtIndex(int index,int userName,int password,long phone){
    if(index>size){
      return;
    }
    index = Math.max(0,index);
    size++;
    UserNode pred = head;
    for(int i=0;i<index;i++){
      pred = pred.next;
    }
    UserNode toAdd = new UserNode(userName,password,phone);
    toAdd.next = pred.next;
    pred.next = toAdd;
  }

  /**
   * 删除链表中用户名为id的节点
   * @param id
   */
  public void deleteAtIndex(int id){
    size--;
    UserNode pred = head;
    for(int i=0;i<size;i++){
      if(pred.userName==id){
        pred.next = pred.next.next;
        break;
      }
      pred = pred.next;
    }
  }

  /**
   * 根据id查找用户，并将用户的密码和电话进行修改，修改后的值为新的两个参数
   * @param id
   * @param password
   * @param phone
   */
  public void updateAtId(int id,int password,long phone){
    UserNode cur = head;
    for(int i=0;i<size;i++){
      if(cur.userName==id){
        cur.password = password;
        cur.phone = phone;
        break;
      }
      cur = cur.next;
    }
  }

  /**
   * 根据用户id，将当前用户的信息输出到控制台上
   * @param id
   */
  public void view(int id){
    UserNode cur = head;
    for(int i=0;i<size;i++){
      if(cur.userName==id){
        System.out.println("用户名："+cur.userName+","+"密码："+cur.password+","+"联系方式："+cur.phone);
        break;
      }
      cur = cur.next;
    }
  }


  //内部类节点对象
  class UserNode{
    int userName; //账号
    int password; //密码
    long phone;//联系方式
    UserNode next;
    public UserNode(int userName,int password,long phone){
      this.userName = userName;
      this.password = password;
      this.phone = phone;
      next = null;
    }
  }

}
