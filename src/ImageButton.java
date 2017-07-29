import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ImageButton extends ImageView {

    private int locationX;
    private int locationY;
    private String index;
    private int xCord;
    private int yCord;
    private int layer;
    private int dimension;


    private List<String> pictureTable = new ArrayList<String>(5);

    public void makeTable(){
        pictureTable.add("m2.jpg");
        pictureTable.add("m3.jpg");
        pictureTable.add("m4.jpg");
        pictureTable.add("m5.jpg");
        pictureTable.add("m6.jpg");
    }

    public ImageButton(int xCord, int yCord, int locationX, int locationY, int width, int height, int layer, int dimension, String random){

        makeTable();
        this.dimension = dimension;
        this.layer = layer;

        setImage(new Image(getClass().getResourceAsStream(random)));

        this.locationX = locationX;
        this.locationY = locationY;
        this.xCord =xCord;
        this.yCord =yCord;
        index = random;
        setLayoutX(this.locationX);
        setLayoutY(this.locationY);

    }


    public int getXm(){
        return this.xCord;
    }

    public int getYm(){
        return yCord;
    }

    public int getLocation_X(){
        return locationX;
    }

    public int getLocation_Y(){
        return locationY;
    }

    public int getLayer(){
        return this.layer;
    }

    public int getDimension(){
        return this.dimension;
    }

    public String getIndex(){
        return index;
    }

}
