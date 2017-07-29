import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Square extends Pane implements ButtonsChecker {

    private static int clickCount =2;
    private static ImageButton selected = null;

    private List<String> losy = new ArrayList<String>();
    private double howmany;
    private int dimension;
    private int offset;
    private List<List<ImageButton>> buttons = new ArrayList<List<ImageButton>>();
    private int layer;

    public Square(double howmany, int dimension, int offset, List<List<ImageButton>> table, int layer ){

        buttons = table;
        String p;
        for(int i=0; i< 64; i+=2){
            RandomPicture x = new RandomPicture();
            p = x.generateRandom();
            losy.add(p);
            losy.add(p);

        }
        Collections.shuffle(losy);
        int z=0;
        for(int i=0 ;i<howmany; i+=dimension) {

            List<ImageButton> row = new ArrayList<ImageButton>();

            for (int j = 0; j < dimension; j++) {

                p = losy.get(z);
                z++;

                addButtons((i / dimension), (j % dimension), (i / dimension * 80 + 20- 2*i), (j % dimension * 80 + 20 -7*j), 80, 80, 1, dimension, row,p);

            }
            addRow(row, buttons);
        }

    }

    public void addButtons(int x, int y, int pol_x, int pol_y, int height, int width, int lay, int dimension, List<ImageButton> row, String p ){

        ImageButton button = new ImageButton(x, y, pol_x, pol_y, 80, 80, 1, dimension,  p);
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

    public void addRow(List<ImageButton> row, List<List<ImageButton>> tablica)
    {
        tablica.add(row);
    }


    public boolean checkPossibility(ImageButton b){
        int x = b.getXm();
        int y = b.getYm();
        int lay = b.getLayer();
        int wymiar = b.getDimension();
        int aroundNo = 0;


        if(x==0 || x==wymiar-1 || y==0 || y==wymiar-1) return true;

        if(buttons.get(x-1).get(y).getParent()!=null) aroundNo++;
        if(buttons.get(x+1).get(y).getParent()!=null) aroundNo++;
        if(buttons.get(x).get(y-1).getParent()!=null) aroundNo++;
        if(buttons.get(x).get(y-1).getParent()!=null) aroundNo++;

        if(aroundNo!=4) return true;
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
