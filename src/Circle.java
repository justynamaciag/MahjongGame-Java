import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Circle extends Pane implements ButtonsChecker {

    private static int clickCount =2;
    private static ImageButton selected = null;

    private ImageButton[][] tab= new ImageButton[8][8];

    private List<String> randoms = new ArrayList<String>();

    String p;

    public Circle(){

        for(int i=0; i< 40; i+=2){
            RandomPicture x = new RandomPicture();
            p = x.generateRandom();
            randoms.add(p);
            randoms.add(p);

        }
        Collections.shuffle(randoms);

        for(int i=0 ;i<8; i++)
            for(int j=0; j<8; j++)
                tab[i][j] = null;

        int k=3;
        int z=0;

        for(int i=0; i<32; i+=8)
        {
            for(int j=k; j<8-k; j++){

                p = randoms.get(z);
                z++;

                ImageButton b = new ImageButton(i/8, j%8, i/8*80+20-i*2, j%8*80+20-j*7, 80, 80, 1, 8,p);
                this.getChildren().add(b);
                addAction(b);
                tab[i/8][j%8] = b;


            }
            k--;
        }
        k=0;
        for(int i = 32; i<64; i+=8){
            for(int j=k; j<8-k; j++){

                p = randoms.get(z);
                z++;

                ImageButton b = new ImageButton(i/8, j%8, i/8*80+20 -i*2, j%8*80+20- j*7, 80, 80, 1, 8,p);
                this.getChildren().add(b);
                addAction(b);
                tab[i/8][j%8] = b;
            }
            k++;

        }

    }

    private void addAction(ImageButton b)
    {
        b.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                ImageButton x =(ImageButton) event.getSource();

                if(checkPossibility(x)){

                    if(clickCount == 0){
                        return;
                    }
                    clickCount--;

                    if(selected == null) {
                        //nie wybrano jeszcze przycisku
                        selected = x;
                    }
                    else{
                        //jezeli nacisnieto te same przyciski
                        if(hasSameIndex(x, selected) && x!=selected) {
                            //usuniecie tego przycisku z tablicy
                            deleteButton(x);
                            deleteButton(selected);
                        }
                        selected=null;
                        clickCount=2;
                    }
                }
                else {
                    selected = null;
                    clickCount=2;
                }

                event.consume();

            }
        });
    }

    public boolean hasSameIndex(ImageButton b, ImageButton other){

        if(b.getIndex().equals(other.getIndex()))
            return true;
        else return false;
    }

    public boolean checkPossibility(ImageButton b){
        int locationX = b.getXm();
        int locationY = b.getYm();
        int aroundNo = 0;

        if(locationX==0 || locationY==0 || locationX==7 || locationY==7)
            return true;

        if(tab[locationX - 1][locationY] == null) aroundNo++;
        if(tab[locationX][locationY-1] == null) aroundNo++;
        if(tab[locationX + 1][locationY] == null) aroundNo++;
        if(tab[locationX][locationY + 1] == null) aroundNo++;

        if(aroundNo >= 1) return true;
        return false;

    }

    public void deleteButton(ImageButton b){
        getChildren().remove(b);
        tab [b.getXm()] [b.getYm()] = null;
    }

}
