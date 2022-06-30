/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure.games;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.graphic.Enigma;
import di.uniba.map.b.adventure.graphic.Map;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.AdvObjectContainer;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.type.Room;
import di.uniba.map.b.adventure.type.Time;
import java.io.PrintStream;
import java.util.Iterator;


/**
 * @author federico
 * @author gianvito
 * @author sandro
 */

public class EscapeGame extends GameDescription {

    Time timer;
    
    @Override
    public void init() throws Exception {
        //Commands
        Command nord = new Command(CommandType.NORD, "nord");
        nord.setAlias(new String[]{"n", "N", "Nord", "NORD"});
        getCommands().add(nord);
        
        Command iventory = new Command(CommandType.INVENTORY, "inventario");
        iventory.setAlias(new String[]{"inv"});
        getCommands().add(iventory);
        
        Command sud = new Command(CommandType.SOUTH, "sud");
        sud.setAlias(new String[]{"s", "S", "Sud", "SUD"});
        getCommands().add(sud);
        
        Command est = new Command(CommandType.EAST, "est");
        est.setAlias(new String[]{"e", "E", "Est", "EST"});
        getCommands().add(est);
        
        Command ovest = new Command(CommandType.WEST, "ovest");
        ovest.setAlias(new String[]{"o", "O", "Ovest", "OVEST"});
        getCommands().add(ovest);
        
        Command end = new Command(CommandType.END, "esci");
        end.setAlias(new String[]{"end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati","exit"});
        getCommands().add(end);
        
        Command look = new Command(CommandType.LOOK_AT, "osserva");
        look.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi"});
        getCommands().add(look);
        
        Command pickup = new Command(CommandType.PICK_UP, "raccogli");
        pickup.setAlias(new String[]{"prendi"});
        getCommands().add(pickup);
        
        Command open = new Command(CommandType.OPEN, "apri");
        open.setAlias(new String[]{});
        getCommands().add(open);
        
        Command use = new Command(CommandType.USE, "usa");
        use.setAlias(new String[]{"utilizza"});
        getCommands().add(use);
        
        //Rooms
        Room hall = new Room(0, "Ingresso","Sei nell'ingresso, sei circondato da polvere e animali morti, si può andare in una sola direzione ... potresti trovare qualcosa qui!");
        hall.setLook("C'è un pacco di fiammiferi e una chiave... chissà che porta potrebbe aprire...\n");
        
        Room kitchen = new Room(1, "Cucina", "Ti trovi nella cucina.\n L'arredamento è in pessime condizioni e ci sono tante macerie. ");
        kitchen.setLook("C'è un tavolo con delle candele. Per utilizzarle avresti bisogno di un altro oggetto");
        
        Room closet = new Room(2, "Sgabuzzino", "Ti trovi nello sgabuzzino.\n Vecchi oggetti domestici e materiale per il giardinaggio. Inoltre ci sono dei ratti molto carini e affamati. "
                + "\nNon è il caso di rimanere per tanto tempo qui dentro!\n");
        closet.setLook("C'è un pacco di batterie e un indovinello da risolvere..");
        
        Room bathroom = new Room(3, "Bagno", "Sei nel bagno.\n Finalmente puoi fare i tuoi bisognini in pace.");
        bathroom.setLook("Non c'è nulla qui, cosa potrebbe mai esserci in un bagno trasandato come questo!?");
        
        Room corridor = new Room(4, "Corridoio", "Sei nel corridoio\n Continuando nel corridoio ti sposterai in un altro lato della casa.\n Ci sono dei quadri raffiguranti l'uomo che ha comandato nel nostro paese tra il 31 ottobre 1922 e il 25 luglio 1943");
        corridor.setLook("C'è un foglio di giornale qui! Potrebbe esserci un indizio..");
        
        Room bedroom = new Room(5, "Camera da letto", "Sei nella camera da letto.\n Qui potresti riposarti su un letto di ferro arrugginito a causa di sostanze naturali.");
        bedroom.setLook("Sul comodino c'è una torcia senza batterie.");
        
        Room wardrobe = new Room(6, "Guardaroba", "Sei nel guardaroba.\n Qui puoi ammirare il vestiario molto discutibile che utilizzavano i vecchi proprietari.");
        wardrobe.setLook("C'è una cassaforte qui che richiede degli oggetti per l'apertura. Se non li possiedi, dovresti farti un giro per la casa.");
        
        Room office = new Room(7, "Studio", "Sei nello studio.\n La stanza è ridotta parecchio male ed è molto disordinata, credo che passerai molto tempo qui dentro");
        office.setLook("C'è un enigma da risolvere qui.");
        
        Room secretRoom = new Room(8, "Stanza segreta", "Sei nella stanza segreta.\n Complimenti per essere arrivato fin qui, adesso davanti a te hai una porta. Ti serve una chiave per aprirla. ");
        secretRoom.setLook("Non c'è nulla di interessante qui.");
        secretRoom.setVisible(false);
        
        
        //maps
        hall.setEast(kitchen);
        kitchen.setNorth(closet);
        kitchen.setEast(bathroom);
        kitchen.setSouth(corridor);
        kitchen.setWest(hall);
        closet.setSouth(kitchen);
        bathroom.setWest(kitchen);
        corridor.setNorth(kitchen);
        corridor.setSouth(bedroom);
        bedroom.setEast(office);
        bedroom.setWest(wardrobe);
        bedroom.setNorth(corridor);
        wardrobe.setEast(bedroom);
        office.setEast(secretRoom);
        office.setWest(bedroom);
        secretRoom.setWest(office);
        getRooms().add(kitchen);
        getRooms().add(closet);
        getRooms().add(hall);
        getRooms().add(bathroom);
        getRooms().add(corridor);
        getRooms().add(wardrobe);
        getRooms().add(bedroom);
        getRooms().add(office);
        getRooms().add(secretRoom);
        
        //objects
        AdvObject match = new AdvObject(1, "fiammifero", "Un pacco di fiammiferi, chissà se si accenderanno a causa dell'umidità del posto..");
        match.setAlias(new String[]{"fiammiferi", "cerino", "cerini"});
        match.setUsable(true);
        hall.getObjects().add(match);
        
        AdvObject key = new AdvObject(2, "chiave", "Una semplice chiave, chissà se e quale porta apre..");
        key.setAlias(new String[]{"chiavi"});
        key.setUsable(true);
        hall.getObjects().add(key);
        
        AdvObject candle = new AdvObject(3, "candela", "Una candela, finalmente potrai osservare le stanze con più luminosità!");
        candle.setAlias(new String[]{"candele", "cero", "fiaccola", "fiaccole"});
        candle.setUsable(true);
        kitchen.getObjects().add(candle);
        
        AdvObject battery = new AdvObject(4, "batteria", "Un pacco di batterie, chissà se sono cariche.");
        battery.setAlias(new String[]{"batterie", "pile", "pila"});
        closet.getObjects().add(battery);
        
        AdvObject riddle = new AdvObject(5, "indovinello", "Ecco un indovinello, ci potrebbe essere qualcosa di interessante sopra! Saresti in grado di risolverlo?");
        riddle.setAlias(new String[]{"rebus"});
        riddle.setOpenable(true);
        closet.getObjects().add(riddle);
        
        AdvObjectContainer safe = new AdvObjectContainer(6, "cassaforte", "Ecco una cassaforte, potrebbe contenere qualcosa di interessante per il tuo obiettivo! Ma quale sarà il codice?");
        safe.setAlias(new String[]{"scrigno", "caveau"});
        safe.setOpenable(true);
        safe.setPickupable(false); 
        wardrobe.getObjects().add(safe);
        
        AdvObject clue = new AdvObject(7, "indizio", "Hai trovato un indizio! Potrebbe svelare qualcosa di interessante! Saresti in grado di capirlo?");
        clue.setAlias(new String[]{"pista"});
        clue.setOpenable(true);
        corridor.getObjects().add(clue);
        
        AdvObject enigma = new AdvObject(8, "enigma", "Ecco un enigma, ci potrebbe essere qualcosa di interessante sopra! Saresti in grado di risolverlo?");
        enigma.setAlias(new String[]{"problema"});
        enigma.setOpenable(true);
        office.getObjects().add(enigma);
        
        AdvObject flashlight = new AdvObject(9, "torcia", "Una torcia, ora puoi smettere di riempirti di cera!");
        flashlight.setAlias(new String[]{"lampada", "lume"});
        flashlight.setUsable(true);
        bedroom.getObjects().add(flashlight);
        
        AdvObject map = new AdvObject(10, "mappa", "Ecco la mappa! Ora puoi scappare da questo posto!");
        map.setAlias(new String[]{"cartina"});
        map.setOpenable(true);
        safe.add(map);
        
        AdvObject door = new AdvObject(11, "porta", "Ecco una porta...potrebbe esserti d'aiuto per salvarti ;)");
        door.setAlias(new String[]{"portone"});
        door.setOpenable(true);
        door.setPickupable(false);
        secretRoom.getObjects().add(door);
        
        //set starting room
        setCurrentRoom(hall);
    }

    @Override
    public void nextMove(ParserOutput p, PrintStream out) {
        if (p.getCommand() == null) {
            out.println("Non ho capito cosa devo fare! Prova con un altro comando.");
        } else {
            //move
            boolean noroom = false;
            boolean move = false;
            if (null != p.getCommand().getType()) 
                switch (p.getCommand().getType()) {
                    
                    case NORD:
                        if (getCurrentRoom().getNorth() != null) {
                            setCurrentRoom(getCurrentRoom().getNorth());
                            move = true;
                        } else {
                            noroom = true;
                        }   
                        break;
                        
                    case SOUTH:
                        if (getCurrentRoom().getSouth() != null) {
                            setCurrentRoom(getCurrentRoom().getSouth());
                            move = true;
                        } else {
                            noroom = true;
                        }   
                        break;
                        
                    case EAST:
                        boolean secretRoom_accessible = false;
                        if (getCurrentRoom().getEast() != null) {
                            if(getCurrentRoom().getName().equals("Studio")) {
                                for (AdvObject o : getInventory()) {
                                    if(o.getName().equals("mappa")) {
                                        setCurrentRoom(getCurrentRoom().getEast());
                                        move = true;
                                        secretRoom_accessible = true; 
                                    }
                                }
                                if (!secretRoom_accessible) {
                                    noroom = true;
                                }
                            } 
                            else {
                                setCurrentRoom(getCurrentRoom().getEast());
                                move = true;
                            }   
                        } else {
                            noroom = true;
                        }   
                        break;
                        
                    case WEST:
                        if (getCurrentRoom().getWest() != null) {
                            setCurrentRoom(getCurrentRoom().getWest());
                            move = true;
                        } else {
                            noroom = true;
                        }   
                        break;
                        
                    case INVENTORY:
                        if (getInventory().isEmpty())
                        {
                            System.out.println("Inventario vuoto!");
                        }
                        else
                        {
                            out.println("Inventario:");
                            for (AdvObject o : getInventory()) {
                                out.println(o.getName() + ": " + o.getDescription());
                            }   
                        }
                        break;
                        
                    case LOOK_AT:
                        if (getCurrentRoom().getLook() != null) {
                            out.println(getCurrentRoom().getLook());
                        } else {
                            out.println("Non c'è niente di interessante qui.");
                        }   
                        break;
                        
                    case PICK_UP:
                        if (p.getObject() != null) {
                            if (p.getObject().isPickupable()) {
                                getInventory().add(p.getObject());
                                getCurrentRoom().getObjects().remove(p.getObject());
                                out.println("Hai raccolto: " + p.getObject().getDescription());
                                if(p.getObject().getName().equals("mappa"))
                                {         
                                    out.println("OH NO! E' scattato l'allarme di diversi C4.. hai 30 secondi per trovare la via d'uscita e scappare da qui vivo, buona fortuna!");
                                    timer = new Time();
                                    timer.startTimer(30000);
                                }
                            } else {
                                out.println("Non puoi raccogliere questo oggetto.");
                            }
                        } else {
                            out.println("Non c'è niente da raccogliere qui.");
                        }   
                        break;
                        
                    case OPEN:
                        int countEnigma = 0;
                        boolean doorOpened = false;
                        
                        if (p.getObject() == null && p.getInvObject() == null) {
                            out.println("Non c'è niente da aprire qui.");
                        } else {
                            if (p.getObject() != null) {
                                if (p.getObject().isOpenable() && p.getObject().isOpen() == false) {
                                    //Si verifica che l'utente abbia raccolto gli oggetti necessari per aprire la cassaforte
                                    if(p.getObject().getName().equals("cassaforte"))
                                    {
                                        for (AdvObject o : getInventory()) {
                                            if(o.getName().equals("indovinello") || o.getName().equals("indizio") || o.getName().equals("enigma")){
                                                countEnigma++;
                                            }
                                        }
                                        if(countEnigma == 3)
                                        {
                                            if (p.getObject() instanceof AdvObjectContainer) {
                                                out.println("Hai aperto: " + p.getObject().getName());
                                                AdvObjectContainer c = (AdvObjectContainer) p.getObject();
                                                if (!c.getList().isEmpty()) {
                                                    out.print(c.getName() + " contiene:");
                                                    Iterator<AdvObject> it = c.getList().iterator();
                                                    while (it.hasNext()) {
                                                        AdvObject next = it.next();
                                                        getCurrentRoom().getObjects().add(next);
                                                        out.print(" " + next.getName());
                                                        it.remove();
                                                    }
                                                    out.println();
                                                }
                                            }
                                        } else {
                                            out.println("Non puoi aprire questo oggetto. Non possiedi ancora tutti gli enigmi e gli indovinelli necessari!");
                                        }
                                    }
                                    //Si veriifca che l'utente abbia nell'inventario una chiave per aprire la porta
                                    if(p.getObject().getName().equals("porta")) {
                                        for (AdvObject o : getInventory()) {
                                            if (o.getName().equals("chiave")) {
                                                doorOpened = true;
                                            }
                                        }
                                        if(doorOpened == true) {
                                            System.out.println("Hai aperto la porta, adesso puoi fuggire da qui!");
                                            end(out);
                                        }
                                        else
                                        {
                                            System.out.println("Hai bisogno di una chiave per aprire questa porta ..");
                                        }
                                    }
                                } else {
                                    out.println("Non puoi aprire questo oggetto.");
                                }
                            }
                            if (p.getInvObject() != null) {
                                //Stampa la lista degli oggetti presenti nell'inventario 
                                if (p.getInvObject().isOpenable() && p.getInvObject().isOpen() == false) {
                                    if (p.getInvObject() instanceof AdvObjectContainer) {
                                        AdvObjectContainer c = (AdvObjectContainer) p.getInvObject();
                                        if (!c.getList().isEmpty()) {
                                            out.print(c.getName() + " contiene:");
                                            Iterator<AdvObject> it = c.getList().iterator();
                                            while (it.hasNext()) {
                                                AdvObject next = it.next();
                                                getInventory().add(next);
                                                out.print(" " + next.getName());
                                                it.remove();
                                            }
                                            out.println();
                                        }
                                    } else {
                                        p.getInvObject().setOpen(true);
                                    }
                                    
                                    Enigma e = new Enigma();
                                    if (p.getInvObject().getName().equals("indovinello")) {
                                            e.displayEnigma("INDOVINELLO Chi la usa non la vede, chi la vende non la usa, chi la compra non la vorrebbe usare. Cos'è? ");             
                                    }
                                    if (p.getInvObject().getName().equals("indizio")) {
                                            e.displayEnigma("INDIZIO La donna è stata rapita come ostaggio e legata ad una bomba a orologeria in casa abbandonata. E tu saresti in grado di scappare?");  
                                    }
                                    if (p.getInvObject().getName().equals("enigma")) {
                                            e.displayEnigma("ENIGMA <<Molta gente è poco riconoscente nei confronti della vita... ma tu no, non più ora :)>> ");                                      
                                    }
                                    if (p.getInvObject().getName().equals("mappa")) {
                                            Map m = new Map();
                                            m.openMap(m);
                                    }
                                    out.println("Hai aperto nel tuo inventario: " + p.getInvObject().getName());
                                } else {
                                    out.println("Non puoi aprire questo oggetto.");
                                }
                            }
                        }   
                        break;
                        
                    case USE:
                        
                        boolean  flag_light = false;
                        boolean flag_match = false;
                        boolean flag_bat = false;
                        if (p.getInvObject() != null && p.getInvObject().isUsable()) {
                            switch (p.getInvObject().getName()){

                                case "torcia":

                                    for (AdvObject o : getInventory()) {
                                        if(o.getName().equals("batteria")){
                                            flag_light = true;
                                        }
                                    }
                                    if (flag_light == true) {
                                        out.println("Hai utilizzato: " + p.getInvObject().getName());
                                    }
                                    else {
                                        out.println("Non puoi utilizzare la torcia senza pile LOL ;) ");
                                    }
                                    break;

                                case "candela":

                                    for (AdvObject o : getInventory()) {
                                        if(o.getName().equals("fiammifero")){
                                            flag_match = true;
                                        }
                                    }
                                    if (flag_match == true){
                                        out.println("Hai utilizzato: " + p.getInvObject().getName());
                                    }
                                    else {
                                        out.println("Non puoi utilizzare la candela senza fiammiferi LOL ;) ");
                                    }
                                    break;

                                case "batteria":

                                    for (AdvObject o : getInventory()) {
                                        if(o.getName().equals("torcia")){
                                            flag_bat = true;
                                        }
                                    }
                                    if (flag_bat == true){
                                        out.println("Solo ora puoi utilizzare le batterie in quanto è presente la torcia nel tuo inventario ");
                                    }
                                    else {
                                        out.println("Non puoi utilizzare le batterie senza avere a tua disposizone la torcia LOL ;) ");
                                    }
                                    break;

                                default:
                                    out.println("Hai utilizzato: " + p.getInvObject().getName());
                                    break;

                                }
                        } else {
                            out.println("Non ci sono oggetti che puoi utilizzare qui.");
                        }       
                        break;
                        
                    default:
                        break;
            }
            if (noroom) {
                out.println("Da quella parte non si può andare c'è un muro!\nNon hai ancora acquisito i poteri per oltrepassare i muri...");
            } else if (move) {
                out.println(getCurrentRoom().getName());
                out.println("===================================================================================================================================");
                out.println(getCurrentRoom().getDescription());
            }
        }
    }

    private void end(PrintStream out) {
        
        out.println("La porta e' stata aperta, niente ti divide dal mondo esterno. Goditi la pace e il riposo dopo questa lunga fuga!\nSei riuscito a scappare in tempo prima che le numerose bombe all'interno della casa ti facessero saltare la testa :)");
        System.exit(0);
    }
}