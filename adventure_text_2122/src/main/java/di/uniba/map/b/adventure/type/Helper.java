/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.type;

/**
 * @author federico
 * @author gianvito
 * @author sandro
 */

public class Helper {
    
    String name = "";
    String description = "";
    
    public Helper(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String descr){
        description = descr;
    }
    
    public String commands() {
        return ("Sei stato rapito, ti trovi in una casa abbandonata chissà dove...\nIl tuo scopo sara' quello di fuggire prima che arrivino i balordi!"
                + "\nComandi:"
                + "\n 1) Digitare nord per spostarti verso nord "
                + "\n 2) Digitare sud per spostarti verso sud "
                + "\n 3) Digitare est per spostarti verso est "
                + "\n 4) Digitare ovest per spostarti verso ovest "
                + "\n 5) Digitare inventario per aprire la lista degli oggetti raccolti"
                + "\n 6) Digitare esci per abbandonare la partita "
                + "\n 7) Digitare osserva per esaminare la stanza corrente "
                + "\n 8) Digitare raccogli per raccogliere gli oggetti presenti nella stanza "
                + "\n 9) Digitare apri per aprire alcuni oggetti "
                + "\n 10) Digitare corri per fuggire solo quando permesso "
                + "\n 11) Digitare usa per utilizzae i vari oggetti, se possibile ");
    }
}