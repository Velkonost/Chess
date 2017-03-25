package chess.maven;

import java.lang.reflect.InvocationTargetException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import sun.applet.Main;

@SpringBootApplication
public class App {
    
  // 1 - Capital letters
  // 0 - small
    public static void main(String[] a) throws InterruptedException, ClassNotFoundException,
              InstantiationException, IllegalAccessException, NoSuchFieldException,
              NoSuchMethodException, IllegalArgumentException, InvocationTargetException {

        ApplicationContext ctx = SpringApplication.run(App.class, a);
        Game game  = ctx.getBean(Game.class);
        game.start();

    }
}
