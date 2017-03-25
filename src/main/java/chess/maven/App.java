package chess.maven;

import java.lang.reflect.InvocationTargetException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import sun.applet.Main;

@SpringBootApplication
public class App {
    
    
  public static void main(String[] a) throws InterruptedException, ClassNotFoundException,
            InstantiationException, IllegalAccessException, NoSuchFieldException,
            NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
      
    ApplicationContext ctx = SpringApplication.run(Main.class, a);
    
    Class classGame = Class.forName("chess.maven.Game");
        Object gameObj = classGame.newInstance(); 
        Game game  = (Game) gameObj; 

        // 1 - Capital letters
        // 0 - small
        game.start();
    
//    Reporter reporter = ctx.getBean(Reporter.class);
//    reporter.sendReports();
  }
}
