import java.awt.*;
import java.util.Arrays;
import javax.swing.ImageIcon;
public class BadFoods {
    Parameters pr = new Parameters();
    Menu me = new Menu();
    /**Pozycje danych elementów*/
    //Happy Meal
    int[] xPosMc = new int[3];
    int[] yPosMc = new int[3];
    int[] PaceMc = new int[3];
    int[] McOn = new int[3];
    //Czekolada
    int[] xPosCz = new int[3];
    int[] yPosCz = new int[3];
    int[] PaceCz = new int[3];
    int[] CzOn = new int[3];
    //Cola
    int[] xPosC = new int[3];
    int[] yPosC = new int[3];
    int[] PaceC = new int[3];
    int[] COn = new int[3];


    /**Konstruktor klasy, ustawienie wszystkich potrzebnych parametrów dla elementów*/
    public BadFoods(){
        //ustawienie początkowych pozycji
        pr.setCoordinates(xPosMc,yPosMc);
        pr.setCoordinates(xPosCz,yPosCz);
        pr.setCoordinates(xPosC,yPosC);
        //ustawienie prędkości poruszania się obiektów
        pr.setPace(PaceMc, me.level);
        pr.setPace(PaceCz, me.level);
        pr.setPace(PaceC, me.level);
        //wgranie zdjęć obiektów
        pr.Mc = new ImageIcon(pr.imagesBad[0]);
        pr.czekolada = new ImageIcon(pr.imagesBad[1]);
        pr.cola = new ImageIcon(pr.imagesBad[2]);
        //włączenie obiektów
        Arrays.fill(McOn, 1);
        Arrays.fill(CzOn, 1);
        Arrays.fill(COn,  1);
    }
    /** Metoda odpowiadająca za pojawianie się niezdrowego jedzenia
     * @param c
     * @param g
     * @param x
     */
    public void spawnFoodBad(Component c,Graphics g, int x){
        spawnItemBad(c,g,pr.Mc,McOn,xPosMc,yPosMc,PaceMc,x);
        spawnItemBad(c,g,pr.czekolada,CzOn,xPosCz,yPosCz,PaceCz,x);
        spawnItemBad(c,g,pr.cola,COn,xPosC,yPosC,PaceC,x);
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
    public void spawnItemBad(Component c, Graphics g,ImageIcon icon, int[] on, int[]xTab, int[]yTab, int[]paceTab, int x){

        for(int i=0; i< on.length;i++)
        {

            if(on[i]==1)
            {

                icon.paintIcon(c,g,xTab[i], yTab[i]);
                xTab[i] -= paceTab[i];
                if(xTab[i] <= 100 && i == 0){
                    xTab[i] = 1600;
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
