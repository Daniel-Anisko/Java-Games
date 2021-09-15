import java.awt.*;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;
public class Obstacles{


    Random rand = new Random();
    int[] ObstXPos = {106, 220, 337, 488, 606, 720, 143, 256, 375, 450, 570, 685}; //X'y samochodow
    int[] ObstYPos = {0,0,0,0,0,0,0,0,0,0,0,0}; // Y samochodow
    public int[] Pace =
                    {1 + rand.nextInt(5),1 +rand.nextInt(5), 1 + rand.nextInt(5),
                     1 + rand.nextInt(5),1 + rand.nextInt(5),1 + rand.nextInt(5),
                     1 + rand.nextInt(5),1 +rand.nextInt(5), 1 + rand.nextInt(5),
                     1 + rand.nextInt(5),1 + rand.nextInt(5),1 + rand.nextInt(5)};

    public int[] Pace2 =
                    {2 + rand.nextInt(7),2 +rand.nextInt(7),  2 + rand.nextInt(7),
                     2 + rand.nextInt(7),2 + rand.nextInt(7), 2 + rand.nextInt(7),
                     2 + rand.nextInt(7),2 + rand.nextInt(7), 2 + rand.nextInt(7),
                     2 + rand.nextInt(7),2 + rand.nextInt(7), 2 + rand.nextInt(7)};

    int[] carON = new int[12];
    String[] images = new String[] {"auto.png","auto2.png","auto3.png","auto4.png"}; //rozne samochody
    final ImageIcon samochod; //obrazek samochodu
    public Obstacles(){
        samochod = new ImageIcon(images[rand.nextInt(3)]); // losowanie koloru samochodu dla planszy
        Arrays.fill(carON, 1); //wlaczenie samochodow

    }
    public void drawCar(Component c, Graphics g, boolean level){ //rysowanie samochodu

        for(int i=0; i< carON.length;i++)
        {

            if(carON[i]==1)
            {

                samochod.paintIcon(c,g,ObstXPos[i], ObstYPos[i]);  //ustawienie koordynatow i zachowan samochodow
                if(!level)
                ObstYPos[i] += Pace[i];
                else
                    ObstYPos[i] += Pace2[i];
                if(ObstYPos[i] >= 630){
                   ObstYPos[i] = 1;

               }


            }
        }


    }

    public boolean checkCollision(int x,int y){ //sprawdzenie czy bohater zderzyl sie z samochodem
        boolean collision = false;
        for(int i = 0; i < carON.length;i++){
            if(new Rectangle(x,y,40,88).intersects(new Rectangle(ObstXPos[i],ObstYPos[i],25,90))){
                collision = true;
                break;
            }
        }
    return collision;
    }

}
