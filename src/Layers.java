import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Layers extends Pane implements ButtonsChecker {

    private int clickCount;
    private ImageButton selected;

    private List<List<ImageButton>> buttons1 = new ArrayList<List<ImageButton>>();
    private List<List<ImageButton>> buttons2 = new ArrayList<List<ImageButton>>();
    private List<List<ImageButton>> buttons3 = new ArrayList<List<ImageButton>>();

    private List<String> randoms = new ArrayList<String>();

    String p;

    public Layers(){

        for(int i=0; i< 116; i+=2){
        RandomPicture x = new RandomPicture();
        p = x.generateRandom();
        randoms.add(p);
        randoms.add(p);
        }

        Collections.shuffle(randoms);
        clickCount = 2;
        selected = null;

        addButtons(1, 64.0, 40, buttons1, 0);
        addButtons(2, 36.0, 110, buttons2, 64);
        addButtons(3, 16.0, 160, buttons3, 100);
    }

    public void addButtons(int layer, double howmany, int przesuniecie, List<List<ImageButton>> tablica, int z){

        int wymiar = (int) Math.sqrt(howmany);

        for(int i=0 ;i<howmany; i+=wymiar) {

            List<ImageButton> row = new ArrayList<ImageButton>();

            for (int j = 0; j < wymiar; j++) {

                p = randoms.get(z);
                z++;

                System.out.println(z);
                ImageButton button = new ImageButton((i / wymiar), (j % wymiar), (i / wymiar * 80 + przesuniecie -2*i), (j % wymiar * 80 + przesuniecie-7*j), 80, 80, layer, wymiar, p);

                this.getChildren().add(button);

                row.add(button);

                button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

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
                                if(hasSameIndex(x, selected) && x!=selected ) {
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
            tablica.add(row);
        }

    }

    private Boolean check(ImageButton b, List<List<ImageButton>> tablica) {
        int ileObok = 0;
        int x = b.getXm();
        int y = b.getYm();

        if(tablica.get(x-1).get(y).getParent()!=null) ileObok++;
        if(tablica.get(x+1).get(y).getParent()!=null) ileObok++;
        if(tablica.get(x).get(y-1).getParent()!=null) ileObok++;
        if(tablica.get(x).get(y-1).getParent()!=null) ileObok++;

        if(ileObok!=4) return true;
        return false;
    }

    public boolean checkPossibility(ImageButton b){
        int x = b.getXm();
        int y = b.getYm();
        int lay = b.getLayer();
        int wymiar = b.getDimension();

        if(x==0 || x==wymiar-1 || y==0 || y==wymiar-1) return true;

        if(lay==1) return check(b, buttons1);
        if(lay==2) return check(b, buttons2);
        if(lay==3) return  check(b, buttons3);


        return false;
    }

    public boolean hasSameIndex(ImageButton b, ImageButton other){

        if(b.getIndex().equals(other.getIndex()))
            return true;
        else return false;
    }

    public void deleteButton(ImageButton b){
        this.getChildren().remove(b);
    }


}
