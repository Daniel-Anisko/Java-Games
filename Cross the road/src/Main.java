import java.awt.Color;
import javax.swing.JFrame;
public class Main {
    public static void main(String[] args){
        JFrame obj = new JFrame();
        Gameplay gamePlay = new Gameplay();
        obj.setBounds(10, 10, 800, 630);
        obj.setTitle("Cross the road");
        obj.setBackground(Color.black);
        obj.setResizable(false);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay);
        obj.setVisible(true);

    }
}
