import java.util.ArrayList;
import java.util.List;


public class RandomPicture {

    private List<String> pictureTable = new ArrayList<String>(5);
    public void makeTable(){
        pictureTable.add("resources/m2.jpg");
        pictureTable.add("resources/m3.jpg");
        pictureTable.add("resources/m4.jpg");
        pictureTable.add("resources/m5.jpg");
        pictureTable.add("resources/m6.jpg");
    }

    private int random;

    public int getRandom(){
        return random;
    }

    public RandomPicture(){

        java.util.Random generator = new java.util.Random();
        this.random = generator.nextInt(4);
        makeTable();
    }
    public String generateRandom(){

        String p = pictureTable.get(random);
        return p;
    }
}
