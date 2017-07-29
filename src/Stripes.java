import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.*;

public class Stripes extends Pane {

    private static int clickCount =2;
    private static ImageButton selected = null;

    private ImageButton[][] tab1= new ImageButton[8][8];
    private ImageButton[][] tab2 = new ImageButton[8][8];

    private List<String> randoms = new ArrayList<String>();


    public Stripes(){

        String p;
        for(int i=0; i< 96; i+=2){
            RandomPicture x = new RandomPicture();
            p = x.generateRandom();
            randoms.add(p);
            randoms.add(p);
        }

        Collections.shuffle(randoms);

        int dimension = 8;
        int howmany = 64;

        int z=0;

        for(int i=0; i<dimension; i++)
            for(int j=0 ;j<dimension; j++) {
                tab1[i][j] = null;
                tab2[i][j] = null;
            }
        for(int i=0 ;i<howmany ;i+=dimension)
        {
            if(i!=dimension*2 && i!=dimension*5)
            for (int j=0; j<dimension; j++){

                p = randoms.get(z);
                z++;

                ImageButton b = new ImageButton(i/8, j%8, i/8*80+15-i*2, j%8*80+15-j*6, 80, 80, 1, 8,p);

                this.getChildren().add(b);
                addAction(b);
                tab1[i/8][j%8] = b;
            }
        }
        for(int i=0; i<howmany; i+=dimension){
            for (int j=0; j<dimension; j++){
                if(j!=2 && j!=5){

                    p = randoms.get(z);
                    z++;

                    ImageButton b = new ImageButton(i/8, j%8, i/8*80+30-i*2, j%8*80+30-j*7, 80, 80, 2, 8,p);
                    this.getChildren().add(b);
                    addAction(b);
                    tab2[i/8][j%8] = b;
                }
            }
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
                        selected = x;
                    }
                    else{
                        if(hasSameIndex(x, selected) && x!=selected) {
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

    public Boolean hasSameIndex(ImageButton b, ImageButton other){

        if(b.getIndex().equals(other.getIndex()))
            return true;
        else return false;
    }

    private Boolean check(ImageButton b, ImageButton[][] tab) {
        int ile_obok = 0;
        int pos_x = b.getXm();
        int pos_y = b.getYm();

        if(tab[pos_x - 1][pos_y] == null) ile_obok++;
        if(tab[pos_x][pos_y-1] == null) ile_obok++;
        if(tab[pos_x + 1][pos_y] == null) ile_obok++;
        if(tab[pos_x][pos_y + 1] == null) ile_obok++;

        if(ile_obok >= 2) return true;
        return false;

    }

    public boolean checkPossibility(ImageButton b){
        int locationX = b.getXm();
        int locationY = b.getYm();
        int aroundNo = 0;
        int lay = b.getLayer();

        if(locationX==0 || locationY==0 || locationX==7 || locationY==7)
            return true;

        if(lay==1) return check(b, tab1);
        if(lay==2) return check(b, tab2);

        return false;
    }

    public void deleteButton(ImageButton b){
        getChildren().remove(b);
        if(b.getLayer()==1)
            tab1[b.getXm()] [b.getYm()] = null;
        if(b.getLayer()==2)
            tab2[b.getXm()] [b.getYm()] = null;
    }
}
