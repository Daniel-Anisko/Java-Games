import java.io.File;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;

/**
 * Metoda obsługująca wszystkie akcje które pojawią się na ekranie.
 * @author Daniel Aniśko 180188
 */
public class Gameplay extends JPanel implements ActionListener {
    //Deklaracja używania innych klas
    Parameters pr  = new Parameters();
    Foods fd       = new Foods();
    BadFoods bfd   = new BadFoods();
    Menu me = new Menu();
    //Zmienne do liczenia czasu
    /** ile czasu minęło */
    long elapsed;
    /**czas rozpoczęcia timera */
    long startTime;
    int lock = 0;
    //Czy puszczać dźwięk
    /** Dźwięk on/off */
    boolean czyDzwiek = true;
    //Dodanie KeyListenera
    private LudzikListener ludziklistener;

    /**
     * Konstruktor pola graficznego. Dodanie obsługi klawiszy
     */
    //Konstruktor
    public Gameplay(){
        //Deklaracja KeyListenera
        ludziklistener = new LudzikListener();
        addKeyListener(ludziklistener);

        Listener listener = new Listener();
        addMouseListener(listener);

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        //Ustawienie licznika
        pr.timer = new Timer(pr.delay,this);
        pr.timer.start();
    }
    /**Metoda malująca wszystko co się wyświetla na ekranie
     * @param g*/
    public void paint(Graphics g) {
        if(me.isMenu){
            super.paintComponent(g);
            //Narysowanie tła*
            g.drawImage(pr.imgmenu, 0, 0, null);

        }

        else if (pr.play && me.isMenu == false) {

            //Rozpoczecie zliczania czasu*
            if(lock == 0){
                startTime = System.currentTimeMillis();
                lock = 1;
            }
            super.paintComponent(g);

            //Narysowanie tła*
            g.drawImage(pr.img, 0, 0, null);


            //Ilość żyć*
            if(pr.lifes == 1) g.drawImage(pr.serce,0,910,null);
            else if(pr.lifes == 2) g.drawImage(pr.serce2,0,910,null);
            else if(pr.lifes == 3) g.drawImage(pr.serce3,0,910,null);
            //Dźwiek*
            if(czyDzwiek) g.drawImage(pr.muteOff,690,910,null);
            else g.drawImage(pr.muteOn,690,910,null);
            //Nowa gra*
            g.drawImage(pr.newGame, 940, 920, null);
            //Pauza*
            g.drawImage(pr.pause,1200, 910,null);
            //Wyświetlanie punktów*
            g.setColor(Color.yellow);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("" + pr.points, 480, 930);
            //Zliczanie czasu*
            elapsed = (System.currentTimeMillis() - startTime)/1000;
            g.drawString(Long.toString(elapsed) , 250, 930);
            //Zmiana wagi bohatera w zależności od liczby punktów
            if (pr.points < 20) {
                pr.ludzik = new ImageIcon(pr.heroStates[4]);
                if (pr.isOpen == false) {
                    pr.ludzik = new ImageIcon(pr.heroMouth[4]);
                }
            } else if (pr.points >= 20 && pr.points < 40) {
                pr.ludzik = new ImageIcon(pr.heroStates[3]);
                if (pr.isOpen == false) {
                    pr.ludzik = new ImageIcon(pr.heroMouth[3]);
                }
            } else if (pr.points >= 40 && pr.points < 60) {
                pr.ludzik = new ImageIcon(pr.heroStates[2]);
                if (pr.isOpen == false) {
                    pr.ludzik = new ImageIcon(pr.heroMouth[2]);
                }
            } else if (pr.points >= 60 && pr.points < 80) {
                pr.ludzik = new ImageIcon(pr.heroStates[1]);
                if (pr.isOpen == false) {
                    pr.ludzik = new ImageIcon(pr.heroMouth[1]);
                }
            } else if (pr.points >= 80 && pr.points < 100) {
                pr.ludzik = new ImageIcon(pr.heroStates[0]);
                if (pr.isOpen == false) {
                    pr.ludzik = new ImageIcon(pr.heroMouth[0]);
                }
            }
            //Wygrana jeżeli gracz ma powyżej 100pkt
            else if (pr.points >= 100) {
                if (me.level <= 2) me.level += 1;

                pr.play = false;
                lock = 0;
            }
            //Tworzenie jedzenia*
            fd.spawnFood(this, g, me.level);
            bfd.spawnFoodBad(this, g, me.level);
            //Sprawdzanie czy zjedzono element*
            //if (!pr.isOpen) { jeżeli chce by bohater jadł spacją to odkomentować
                if (pr.checkIfEaten(pr.x, pr.y, bfd.McOn, bfd.xPosMc, bfd.yPosMc)
                        || pr.checkIfEaten(pr.x, pr.y, bfd.CzOn, bfd.xPosCz, bfd.yPosCz)
                        || pr.checkIfEaten(pr.x, pr.y, bfd.COn, bfd.xPosC, bfd.yPosC)) {
                    pr.points -= 10;
                    pr.lifes -= 1;
                    if(pr.lifes == 0){
                        pr.play = false;

                    }
                    if(czyDzwiek) pr.playSound(new File("badhit.wav"));
                } else if (pr.checkIfEaten(pr.x, pr.y, fd.appleON, fd.xPosA, fd.yPosA)
                        || pr.checkIfEaten(pr.x, pr.y, fd.bananaON, fd.xPosB, fd.yPosB)
                        || pr.checkIfEaten(pr.x, pr.y, fd.waterON, fd.xPosW, fd.yPosW)
                        || pr.checkIfEaten(pr.x, pr.y, fd.carrotON, fd.xPosM, fd.yPosM)) {

                    if(czyDzwiek) pr.playSound(new File("hit.wav"));
                    pr.points += 10;

                }
            //}

            //narysowanie bohatera

            pr.ludzik.paintIcon(this, g, pr.x, pr.y);

            //Jeżeli koniec gry to wyświetl że gracz wygrał
            if (!pr.play && pr.lifes > 0) {
                g.setColor(Color.black);
                g.setFont(new Font("serif", Font.BOLD, 60));
                g.drawString("You won!", 400, 500);
            }
            else if(!pr.play && pr.lifes == 0){
                g.setColor(Color.black);
                g.setFont(new Font("serif", Font.BOLD, 60));
                g.drawString("You lose!", 400, 500);
            }
        }


    }
    /** Nadpisanie metody która odrysuwuje co się dzieje na ekranie*/
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

    }

    /**
     * Klasa opisująca działanie poszczególnych klawiszy
     */
    // Klasa opisująca działanie poszczególnych klawiszy
    private class LudzikListener implements KeyListener
    {
        public void keyTyped(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {
            //Jeżeli puszczono spację zmień stan isOpen na przeciwny, spowoduje to otworzenie buzi bohatera
            if(e.getKeyCode()== KeyEvent.VK_SPACE)
            {
                pr.isOpen = true;
            }
        }
        public void keyPressed(KeyEvent e) {
            //Jeżeli kliknięto spację zmień stan isOpen na przeciwny, spowoduje to zamknięcie buzi bohatera
            if(e.getKeyCode()== KeyEvent.VK_SPACE)
            {
                pr.isOpen = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_R && pr.play == false){

                fd = new Foods();
                bfd = new BadFoods();
                pr = new Parameters();
                pr.play = true;
                System.out.println(me.level);
            }
            if(e.getKeyCode() == KeyEvent.VK_Q){
                if(me.isMenu)
                me.isMenu = false;
                else if(!me.isMenu)
                    me.isMenu = true;
            }
            //Przyciski W i S służące do chodzenia góra-dół
            if(e.getKeyCode()== KeyEvent.VK_W)
            {
                if(pr.y > 100){
                pr.y -= 300;
                pr.x += 60;}
            }
            if(e.getKeyCode()== KeyEvent.VK_S)
            {
                if(pr.y<600){
                pr.y += 300;
                pr.x -= 60;}
            }

        }
    }
    private class Listener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            Point p = e.getPoint();
            int xMouse = (int) p.getX();
            int yMouse = (int) p.getY();
            if(pr.checkIfMuted(xMouse,yMouse)){
                czyDzwiek ^= true;
            }
            else if(pr.checkIfNewGame(xMouse,yMouse)){
                fd = new Foods();
                bfd = new BadFoods();
                pr = new Parameters();
                pr.play = true;
                me.level = 0;
                startTime = System.currentTimeMillis();
                System.out.println(me.level);
            }
            else if(pr.checkIfPause(xMouse,yMouse)){
                me.isMenu ^= true;
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
