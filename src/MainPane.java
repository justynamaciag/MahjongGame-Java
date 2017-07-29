import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class MainPane extends BorderPane  {

    private Text napis;
    private BorderPane bp;
    private GridPane topGrid;


    public MainPane(){

        napis = new Text("Witaj w grze Mahjong!");
        napis.setFont(Font.font("Montype Corsiva", FontWeight.BOLD, 50));
        napis.setFill(Paint.valueOf("Green"));

        topGrid = addGridPane(napis);
        setTop(topGrid);

        setCenter(new LevelsGridPane());

    }
    private GridPane addGridPane(Text napis){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 5, 5));
        grid.add(napis, 0, 0, 2, 1);
        return grid;
    }

    private void deleteGrid(){
        getChildren().remove(topGrid);
    }

    EventHandler<ActionEvent> GameHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            deleteGrid();
            getChildren().add(new LevelsGridPane());
        }
    };





}
