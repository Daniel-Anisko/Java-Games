import java.awt.*;
import javax.swing.*;

/**Gra o zdrowym odżywianiu
 * @author Danie Aniśko 180188
 */
public class Main {
    /**
     * Metoda uruchamiająca grę. W tej metodzie ustawiane jest okno gry
     */
    public static void main(String[] args ){
        //Deklaracje
        JFrame obj        =    new JFrame();
        Gameplay gamePlay =    new Gameplay();

        //Podstawowe ustawienia
        //obj.setBounds(10, 10, 1450, 1000);
        obj.setSize(1280,1000);
        obj.setTitle("Time for a Diet!");
        obj.setBackground(Color.black);
        obj.setResizable(false);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay);
        obj.setVisible(true);
    }
}