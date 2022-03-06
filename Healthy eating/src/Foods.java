import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import javax.swing.ImageIcon;
public class Foods {

    Random rand = new Random();
    Parameters pr = new Parameters();
    Menu me = new Menu();
    /**Pozycje danych elementów*/
    //Banan
    int[] xPosB = new int[3];
    int[] yPosB = new int[3];
    int[] PaceB = new int[3];
    int[] bananaON = new int[3];
    //Jabłko
    int[] xPosA = new int[3];
    int[] yPosA = new int[3];
    int[] PaceA = new int[3];
    int[] appleON = new int[3];
    //Woda
    int[] xPosW = new int[3];
    int[] yPosW = new int[3];
    int[] PaceW = new int[3];
    int[] waterON = new int[3];
    //Marchewka
    int[] xPosM = new int[3];
    int[] yPosM = new int[3];
    int[] PaceM = new int[3];
    int[] carrotON = new int[3];
    /**Konstruktor klasy, ustawienie wszystkich potrzebnych parametrów dla elementów*/
    public Foods() {
        //Ustawianie koordynatów początkowych
        pr.setCoordinates(xPosB,yPosB);
        pr.setCoordinates(xPosA,yPosA);
        pr.setCoordinates(xPosW,yPosW);
        pr.setCoordinates(xPosM,yPosM);
        //Ustawianie prędkości przemieszczania się
        pr.setPace(PaceB,me.level);
        pr.setPace(PaceA,me.level);
        pr.setPace(PaceW, me.level);
        pr.setPace(PaceM,me.level);
        //Wgranie zdjęcia
        pr.banana = new ImageIcon(pr.imagesGood[1]);
        pr.apple = new ImageIcon(pr.imagesGood[0]);
        pr.water = new ImageIcon(pr.imagesGood[2]);
        pr.carrot = new ImageIcon(pr.imagesGood[3]);
        //Włączenie obiektów
        Arrays.fill(bananaON, 1);
        Arrays.fill(appleON, 1);
        Arrays.fill(waterON, 1);
        Arrays.fill(carrotON,1);
    }

    /** Metoda odpowiadająca za pojawianie się jedzenia
     * @param c
     * @param g
     * @param x
     */
    public void spawnFood(Component c, Graphics g,int x){
        spawnItemGood(c,g, pr.banana,bananaON,xPosB,yPosB,PaceB,x);
        spawnItemGood(c,g, pr.apple,appleON,xPosA,yPosA,PaceA,x);
        spawnItemGood(c,g,pr.water,waterON,xPosW,yPosW,PaceW,x);
        spawnItemGood(c,g,pr.carrot,carrotON,xPosM,yPosM,PaceM,x);

    }

    /**
     * Metoda opisująca ruch zdrowego jedzenia
     * @param c
     * @param g
     * @param icon jaki element ma się pokazać
     * @param on   czy włączony
     * @param xTab koordynat X
     * @param yTab koordynat Y
     * @param paceTab prędkośc porusznaia się
     * @param x wybór taśmy
     */
    public void spawnItemGood(Component c, Graphics g,ImageIcon icon, int[] on, int[]xTab, int[]yTab, int[]paceTab, int x) {

        for (int i = 0; i < on.length; i++) {
            if(on[i]==1){
            icon.paintIcon(c, g, xTab[i], yTab[i]);
            xTab[i] -= paceTab[i];
                if(xTab[i] <= 100 && i == 0){
                    xTab[i] = 1600; //Jeżeli obiekt na 0 taśmie dojdzie do końca to daj go na początek
                    if(x==0)
                        paceTab[i] = pr.resetPace(1,5);
                    else if(x==1)
                        paceTab[i] = pr.resetPace(5,10);
                    else if(x==2)
                        paceTab[i] = pr.resetPace(10,15);
                }
                else if(xTab[i] <= 80 && i == 1){
                    xTab[i] = 1600;
                    if(x==0)
                        paceTab[i] = pr.resetPace(1,5);
                    else if(x==1)
                        paceTab[i] = pr.resetPace(5,10);
                    else if(x==2)
                        paceTab[i] = pr.resetPace(10,15);
                }
                else if(xTab[i] <= 70 && i == 2){
                    xTab[i] = 1600;
                    if(x==0)
                        paceTab[i] = pr.resetPace(1,5);
                    else if(x==1)
                        paceTab[i] = pr.resetPace(5,10);
                    else if(x==2)
                        paceTab[i] = pr.resetPace(10,15);
                }
        }
        }
    }





}