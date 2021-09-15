import java.util.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
public class Gameplay  extends JPanel implements ActionListener
{
    Random rand = new Random();
    private Obstacles obs; //uzywanie klasy Obstacles
    private ImageIcon ludzik; //ustawienie zdjecia bohatera
    private int x = 20; //poczatkowe koordynaty bohatera
    private int y = 300;
    private boolean lvl = false;
    private int level = 0;
    private Timer timer;
    private int delay = 8;
    private int nitro;
    private boolean isWon = false;
    private boolean isEnded = false;
    private LudzikListener ludziklistener; //do sluchania klawiatury
    private boolean play = true; //warunek gry
    Image img = Toolkit.getDefaultToolkit().getImage("bgr.png"); // przypisanie do img tła
    String[] levels = new String[] {"Level 0","Level 1"}; //poziom
    //konstruktor
    public Gameplay(){

        obs = new Obstacles(); // uzycie klasy Obstacles
    ludziklistener = new LudzikListener(); //do sluchania klawiatur
    setFocusable(true);
    addKeyListener(ludziklistener); //do sluchania klawiatury
    setFocusTraversalKeysEnabled(false);
    timer = new Timer(delay,this);
    timer.start();
    }
    //metoda rysująca to co się wyświetla
    public void paint(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null); //narysowanie tła
        g.setColor(Color.red);
        g.setFont(new Font("serif",Font.BOLD,30));
        g.drawString(levels[level], 10, 30);
        if(play){

                obs.drawCar(this, g, lvl); //narysowanie samochodów

            ludzik = new ImageIcon("ludz.png"); //narysowanie bohatera
            ludzik.paintIcon(this, g,x,y);
            if(obs.checkCollision(x,y)){ //sprawdzenie czy gracz dotknal samochodu
                play = false;
                isEnded = true;
                lvl = false;
                if(level > 0)
                    level-=1;

            }
            if(x <= -10 ){ //blokada wychodzenia poza ekran
                x += 10;
            }
            else if(x >= 800){ //jezeli gracz przeszedl przez cala plansze to wygral
                g.setColor(Color.green);
                g.setFont(new Font("serif",Font.BOLD,60));
                g.drawString("You won", 300, 330);
                isWon = true;
                lvl = true;
                if(level <1)
                level+=1;
                //level1 = true;


            }
            else if(y >=530){ //blokada wychodzenia poza ekran
                y-=10;
            }
            else if(y <= -10){ //blokada wychodzenia poza ekran
                y+=10;
            }
            else{}
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        repaint();
    }
    //uzywanie klawiatury do chodzenia
    private class LudzikListener implements KeyListener
    {
        public void keyTyped(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {}
        public void keyPressed(KeyEvent e) {

            if(e.getKeyCode() == KeyEvent.VK_SPACE && (isEnded || isWon)){//restart gry
                obs = new Obstacles();
                play = true;
                x = 20;
                y = 300;
            }
            if(e.getKeyCode()== KeyEvent.VK_W)
            {
                y-=10;
            }
            if(e.getKeyCode()== KeyEvent.VK_A)
            {
                x-=10;
            }
            if(e.getKeyCode()== KeyEvent.VK_S)
            {
                y+=10;
            }
            if(e.getKeyCode()== KeyEvent.VK_D)
            {
                x+=10;
            }

        }
    } }