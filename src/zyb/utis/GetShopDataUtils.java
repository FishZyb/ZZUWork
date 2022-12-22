package zyb.utis;

import zyb.bean.Food;
import zyb.bean.Shop;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 一只鱼zzz
 * @version 1.0
 */
public class GetShopDataUtils {
    //创建一个List集合，读取shop文件中的内容
    public static List<Shop> shops = new ArrayList<>();

    static{
      try {
        File file = new File("src/resources/data/shop.txt");
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        BufferedReader bufferedReader = new BufferedReader(reader);

        String str;

        str = bufferedReader.readLine();//一次读一行
        while(str!=null){
          List<Food> foods = new ArrayList<>();
          List<String> commons = new ArrayList<>();
          String shopType = str.substring(10);
          str = bufferedReader.readLine();
          String shopId = str.substring(8);
          str=bufferedReader.readLine();
          String shopName = str.substring(10);
          str=bufferedReader.readLine();
          String shopPassword = str.substring(14);
          str=bufferedReader.readLine();
          String avgScore = str.substring(10);
          str=bufferedReader.readLine();
          String avePrice = str.substring(10);
          str=bufferedReader.readLine();
          String address = str.substring(9);
          str=bufferedReader.readLine();
          String phone = str.substring(7);
          str=bufferedReader.readLine();
          //抓取前4个字符，看看是否是food
          String food = str.substring(0,4);
          //当抓取的是“food字符时”
          while("food".equals(food)){
            String[] strings = str.split(",");//按照","字符分割
            String foodId = strings[0].substring(9);
            String foodName = strings[1].substring(12);
            String foodPrice = strings[2].substring(13);
            Food food1 = new Food(Integer.parseInt(foodId),foodName,Double.parseDouble(foodPrice));
            foods.add(food1);
            str = bufferedReader.readLine();
            //抓取前四个字符，看是否是food
            food = str.substring(0,4);
          }
          //开始抓取comment
          String commonStr = str.substring(0,7);
          while("Comment".equals(commonStr)){
            if(str!=null){
              String common = str.substring(10);
              commons.add(common);
              str = bufferedReader.readLine();
              if(str!=null){
                commonStr = str.substring(0,7);
              }else{
                break;
              }
            }else{
              break;
            }
          }
          Shop shop = new Shop(shopType,shopId,shopName,shopPassword,avgScore,avePrice,address,phone,foods,commons);
          shops.add(shop);
        }

      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
}
