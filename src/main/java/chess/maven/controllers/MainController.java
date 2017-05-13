package chess.maven.controllers;

import static chess.maven.App.ctx;
import chess.maven.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    
    @RequestMapping("/game")
    public String gamePage(Model model) {
        
        Game game = ctx.getBean(Game.class);
        game.start();
        
        char[][] gameField = game.getField();
        if (gameField != null) model.addAttribute("start", "Game started successful!");
        else model.addAttribute("start", "Game didn't start. There is an error!");
        
        return "game";
    } 
}
