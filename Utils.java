import java.io.Closeable;

/**
 * @ClassName Util
 * @Description TODO
 * @Auther danni
 * @Date 2020/1/5 21:25]
 * @Version 1.0
 **/

public class Utils {
    public static void release(Closeable... targets){
        for(Closeable target:targets) {
            try {
                if(null!=target) {
                    target.close();
                }
            }catch(Exception e) {

            }
        }
    }
}
