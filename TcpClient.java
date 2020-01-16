import java.io.*;
import java.net.Socket;

/**
 * @ClassName TcpClien
 * @Description TODO
 * @Auther danni
 * @Date 2020/1/5 21:12]
 * @Version 1.0
 **/

public class TcpClient {
    public static void  send(OutputStream os){
        User user=new User();
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String line="";
        String name="";
        String password="";
        try {
            System.out.println("请选择功能：");
            System.out.println("1:登录账户");
            System.out.println("2:注册账户");
            line=br.readLine();
            ObjectOutputStream bos=new ObjectOutputStream(os);
            switch (line){
                case "1":
                    System.out.print("请输入用户名：");
                    name=br.readLine();
                    System.out.print("请输入密码：");
                    password=br.readLine();
                    bos.write(line.getBytes());
                    break;
                case "2":
                    System.out.print("请输入用户名：");
                    name=br.readLine();
                    System.out.print("请输入密码：");
                    password=br.readLine();
                    bos.write(line.getBytes());
                    break;
                    default:break;
            }
            bos.writeObject(new User(name,password));
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void recive(InputStream is) {
        BufferedReader br=new BufferedReader(new InputStreamReader(is));
        try {
            String line=br.readLine();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("192.168.43.47",8899);
        send(socket.getOutputStream());
        recive(socket.getInputStream());
    }
}
