package chess.maven;

import java.lang.reflect.InvocationTargetException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {
    
    public static ApplicationContext ctx;
    
  // 1 - Capital letters
  // 0 - small
    public static Game game;
    public static void main(String[] a) throws InterruptedException, ClassNotFoundException,
              InstantiationException, IllegalAccessException, NoSuchFieldException,
              NoSuchMethodException, IllegalArgumentException, InvocationTargetException {

        ctx = SpringApplication.run(App.class, a);
       

    }
}
