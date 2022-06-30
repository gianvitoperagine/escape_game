/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.parser.Parser;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.CommandType;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

/**
 * @author federico
 * @author gianvito
 * @author sandro
 */

public class Engine {

    private final GameDescription game;
    
    private Parser parser;

    public Engine(GameDescription game) {
        this.game = game;
        try {
            this.game.init();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            Set<String> stopwords = Utils.loadFileListInSet(new File(".//resources//stopwords"));
            parser = new Parser(stopwords);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public final void begin()
    {
        System.out.println("===================================================================================================================================");
        System.out.println("        * Escape Game *     ");
        System.out.println("Sei appena stato rapito e ti trovi in una casa abbandonata. \nL'entrata è bloccata, per uscirne vivo dovresti trovare un'uscita segreta...");
        System.out.println("===================================================================================================================================");
    }
    
    
    public void execute() {
        
        System.out.println(game.getCurrentRoom().getName());
        System.out.println();
        System.out.println(game.getCurrentRoom().getDescription());
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            ParserOutput p = parser.parse(command, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
            if (p == null || p.getCommand() == null) {
                System.out.println("Non capisco quello che mi vuoi dire.");
            } else if (p.getCommand() != null && p.getCommand().getType() == CommandType.END) {
                System.out.println("Hai paura di continuare il gioco? Sei un fifone! Addio :)");
                break;
            } else {
                game.nextMove(p, System.out);
                System.out.println();
            }
        }
    }
}
