import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName TcpServer
 * @Description TODO
 * @Auther danni
 * @Date 2020/1/5 21:12]
 * @Version 1.0
 **/

public class TcpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(8899);
        while(true) {
            Socket client = server.accept();
            System.out.println("用户" + client.getInetAddress().getHostAddress() + "连接成功！");
            new Thread(new Channel(client)).start();
        }
    }
}
 class Channel implements Runnable{
     private Socket client;
     private static  ObjectInputStream ois;
     private static BufferedWriter bw;
     private static List<User> users;
     public Channel(Socket client) {
         this.client = client;
         try{
             ois= new ObjectInputStream(client.getInputStream());
             bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
             users=datas.data();
         }catch (IOException e){
           Utils.release(ois,bw);
         }
     }
     static User recive(){
         try {
             byte[] buff=new byte[1024];
             int len=ois.read(buff);
             String line=new String(buff,0,len);
             System.out.println(line);
             User user=(User) ois.readObject();
             if(line.equals("1")){
                send(user);
             }else if(line.equals("2")){
                datas.insert(user);
                bw.write("注册成功！");
                 bw.flush();
             }
             return user;
         } catch (Exception e) {
             e.printStackTrace();
         }
         return null;
     }
     static void send(User user){
         for(User u:users){
             if(u.getName().equals(user.getName())&&u.getPassword().equals(user.getPassword())){
                 try {
                     bw.write(user.getName()+"登录成功！");
                     bw.flush();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
                 return;
             }
         }
         try {
             bw.write("登录失败！");
             bw.flush();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     @Override
     public void run() {
         User user=recive();
         if(user!=null){
             System.out.println("用户名："+user.getName());
             System.out.println("密码："+user.getPassword());
         }
         Utils.release(ois,bw,client);
     }
 }
