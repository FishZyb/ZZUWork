package zyb.test;

import zyb.bean.Shop;
import zyb.utis.GetShopDataUtils;

/**
 * @author 一只鱼zzz
 * @version 1.0
 */
public class TestShopData {
  public static void main(String[] args) {
    for (Shop shop : GetShopDataUtils.shops) {
      System.out.println(shop);
    }

  }
}
