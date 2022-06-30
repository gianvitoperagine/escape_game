/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.type;

import static java.lang.System.out;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author federico
 * @author gianvito
 * @author sandro
 */

public class Time{
    Timer timer;
    Instant startTime;
    
    
    public void setTime(Instant start) {
        startTime = start;
    }
    
    public Instant getTime() {
        return startTime;
    }
    
    public void startTimer(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds); //Programma l'attività per 30 secondi
    }

    class RemindTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("Tempo scaduto! Si sente la casa tremare, sta per esplodere qualcosa...");
            timer.cancel();
            endTimer();     //Termina il thread del countdown 
        }
    }
    
    public void endTimer() {   
        out.println("BOOM! La casa cade a pezzi, tutto si riduce in rovina poichè numerosi C4 erano stati piazzati nella casa!\nMi dispiace che non hai salutato la tua famiglia in tempo! Adesso li puoi rivedere dal paradiso... addio! ");  
        System.exit(0);
    }
}
