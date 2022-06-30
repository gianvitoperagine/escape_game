/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.graphic;

import javax.swing.*;

/**
 * @author federico
 * @author gianvito
 * @author sandro
 */

public class Map {

    public static void openMap(Map m) {
        JFrame f = new JFrame();
        JLabel labelEnigma = new JLabel(new ImageIcon(".//images//mappa.jpg"));
        JOptionPane.showMessageDialog(f,labelEnigma,"Mappa",JOptionPane.INFORMATION_MESSAGE);
    }
}
