package zyb.utis;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author 一只鱼zzz
 * @version 1.0
 * 基于druid数据库连接池的工具类
 */
public class JDBCUtils {

  private static DataSource ds;

  //在静态代码块完成ds的初始化
  static{
    Properties properties = new Properties();
    try {
      properties.load(new FileInputStream("src/resources/druid.properties"));
      ds = DruidDataSourceFactory.createDataSource(properties);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //编写getConnection方法
  public static Connection getConnection() throws SQLException {
    return ds.getConnection();
  }

  //编写关闭连接池的方法
  public static void close(ResultSet resultSet, Statement statement,Connection connection) throws SQLException {
    if(resultSet!=null){
      resultSet.close();
    }
    if(statement!=null){
      statement.close();
    }
    if(connection!=null){
      connection.close();
    }
  }

}
