/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.graphic;

import java.awt.Color;
import javax.swing.*;
import java.awt.Font;

/**
 * @author federico
 * @author gianvito
 * @author sandro
 */

public class Enigma{

    public static void displayEnigma(String desc) {
        JFrame f = new JFrame();
        JLabel labelEnigma = new JLabel(desc, JLabel.LEFT);
        labelEnigma.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        labelEnigma.setForeground (Color.black);
        JOptionPane.showMessageDialog(f,labelEnigma,"Indizio",JOptionPane.QUESTION_MESSAGE);
    }
}
