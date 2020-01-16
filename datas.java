import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName datas
 * @Description TODO
 * @Auther danni
 * @Date 2020/1/5 21:54]
 * @Version 1.0
 **/

public class datas {
  static Connection initial(){
      Connection connection=null;
      try{
          Class.forName("com.mysql.jdbc.Driver");
          connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stu?useSSl=false&characterEncoding=UTF8",
                  "root",
                  "0813");
          // System.out.println(connection);
      }catch (Exception e) {
          System.err.println("数据库连接异常");
      }
      return connection;
    }
   public static List<User> data(){
      Connection connection=initial();
       List<User> users=new ArrayList<>();

       try(PreparedStatement statement =connection.prepareStatement("select * from user")){
           String uname="";
           String uphone="";
           ResultSet resultSet=statement.executeQuery();
           while(resultSet.next()){
               uname=resultSet.getString(1);
               uphone=resultSet.getString(2);
               users.add(new User(uname,uphone));
           }
       }catch (Exception e){
           System.err.println("数据库异常");
       }
       return users;
   }
   public static  void insert(User user){
      Connection connection=initial();
      try(PreparedStatement statement =connection.prepareStatement("Insert into user values (?,?)")) {
           statement.setString(1, user.getName());
           statement.setString(2, user.getPassword());
           statement.execute();
          System.out.println("成功插入！");
       }catch (Exception e){
           System.err.println("插入信息异常！");
       }
   }
}
