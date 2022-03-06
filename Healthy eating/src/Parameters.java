import java.io.File;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;
/**Klasa przechowująca parametry, zasoby graficzne, dźwięki oraz ogólne metody*/
public class Parameters {
    Random rand = new Random();
    int flag = 0;

    /**Dodanie Timera */
    public Timer timer;
    public int delay = 8;
    /**Sprawdzenie czy gracz zamknął buzię*/
    public boolean isOpen = true;
    /**stan gry */
    public boolean play = true;
    /**czy menu jest odpalone */

    /**Punkty gracza */
    int points = 0;
    /**Pozycje torów podających jedzenie */
    int[] xPos = {1600, 1600, 1600};
    int[] yPos = {100, 400, 700};
    /**Pozycja gracza*/
    public int x = 130;
    public int y = 370;
    /**Ilość serc*/
    public int lifes = 3;
    /**Czy wyciszone*/
    public boolean wyciszenie = false;
    /**Tablica przechowująca dobre jedzenie*/
    String[] imagesGood = new String[]{"GoodFood/jablko.png", "GoodFood/banana.png","GoodFood/woda.png","GoodFood/marchewka.png"};
    /**Tablica przechowująca złe jedzenie*/
    String[] imagesBad = new String[] {"BadFood/Mc.png","BadFood/czekolada.png","BadFood/cola.png"};
    /**Tablica przechowująca obrazy bohatera*/
     String[] heroStates = new String[] {"hero/stan1.png","hero/stan2.png","hero/stan3.png","hero/stan4.png","hero/stan5.png"};
     String[] heroMouth = new String[] {"hero/stan1zamk.png", "hero/stan2zamk.png","hero/stan3zamk.png","hero/stan4zamk.png","hero/stan5zamk.png"};
     String menuImg = "menu.png";
     /**Tło gry*/
    Image img = Toolkit.getDefaultToolkit().getImage("bgr.png"); /** przypisanie do img tła
    /**Menu*/
    Image imgmenu = Toolkit.getDefaultToolkit().getImage(menuImg);
    /**Życie*/
    Image serce = Toolkit.getDefaultToolkit().getImage("heart.png");
    Image serce2 = Toolkit.getDefaultToolkit().getImage("heart2.png");
    Image serce3 = Toolkit.getDefaultToolkit().getImage("heart3.png");
    /**Dźwięk*/
    Image muteOn = Toolkit.getDefaultToolkit().getImage("muteon.png");
    Image muteOff = Toolkit.getDefaultToolkit().getImage("muteoff.png");
    /**Nowa Gra*/
    Image newGame = Toolkit.getDefaultToolkit().getImage("newgame.png");
    /**Pauza*/
    Image pause = Toolkit.getDefaultToolkit().getImage("pause.png");
    /**Dobre jedzenie*/
    public ImageIcon banana;
    public ImageIcon apple;
    public ImageIcon water;
    public ImageIcon carrot;
    /**Zle jedzenie*/
    public ImageIcon Mc;
    public ImageIcon czekolada;
    public ImageIcon cola;
    /**Gracz*/
    public ImageIcon ludzik;
    /**Dzwiek*/
    public ImageIcon dzwiek;


    /**Ustawienie początkowych pozycji elementów
     * @param tabX  pozycja X
     * @param tabY  pozycja Y
     */
    public void setCoordinates(int[] tabX,int[] tabY){
        for(int i = 0 ; i < tabX.length; i++){
            tabX[i] = xPos[i];
            tabY[i] = yPos[i];

        }
    }

    /**Ustawienie prędkości na początku rozgrywki
     * @param paceTab tablica z dozwolonymi prędkościami
     * @param x taśma
     */
    public void setPace(int[] paceTab,int x){
        for(int i = 0; i<paceTab.length; i++){
            if(x==0) paceTab[i] = 1+ rand.nextInt(5);
            else if(x==1) paceTab[i] = 5 + rand.nextInt(10);
            else if(x==2) paceTab[i] = 10 + rand.nextInt(15);
        }
    }

    /**Po dojściu do końca ustawiana jest nowa prędkość
     * @param min
     * @param max
     * @return
     */
    public int resetPace(int min, int max){
        int x = 0;
        x = min+ rand.nextInt(max);
        return x;
    }

    /**Sprawdzenie czy gracz zjadł obiekt
     * @param x koordynat X obiektu
     * @param y koordynat Y obiektu
     * @param on czy wlaczony
     * @param xTab pozycja produktu X
     * @param yTab pozycja prodkutku Y
     * @return  zwracany warunek
     */

    public boolean checkIfEaten(int x, int y,int[] on,int[] xTab,int[] yTab){
        boolean eaten = false;
        for(int i = 0; i < on.length;i++){
            if(new Rectangle(x,y,80,200).intersects(new Rectangle(xTab[i],yTab[i],20,20))){
                eaten = true;
                xTab[i] = 0;
                break;

            }
        }
        return eaten;
    }

    /**Sprawdzenie wyciszenia
     * @param x pozycja X
     * @param y pozycja Y
     * @return zwracany warunek
     */
    public boolean checkIfMuted(int x, int y){
        boolean muted = false;
        boolean newgame = false;
        if(new Rectangle(x,y,40,40).intersects(new Rectangle(690,910,50,50))){
            muted ^= true;
        }
        return muted;
    }

    /**Sprawdzenie nowej gry
     * @param x pozycja X
     * @param y pozycja Y
     * @return zwracany warunek
     */
    public boolean checkIfNewGame(int x, int y){
        boolean newgame = false;
        if(new Rectangle(x,y,40,40).intersects(new Rectangle(940,920,40,20))){
            newgame ^= true;
        }
        return newgame;
    }

    /**Sprawdzenie pauzy
     * @param x pozyccja X
     * @param y pozycja y
     * @return zwracany warunek
     */
    //
    public boolean checkIfPause(int x, int y){
        boolean pause = false;
        if(new Rectangle(x,y,40,40).intersects(new Rectangle(1200,910,25,40))){
            pause ^= true;
        }
        return pause;
    }
    /**Odtworzenie dzwieku
     * @param f ścieżka do pliku dźwiękowego*/
    public static synchronized void playSound(final File f) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(f);
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

}
