import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class LevelsGridPane extends Pane {

    private GridPane grid;

    public LevelsGridPane(){

        grid = new GridPane();

        Button stripes = new Button();
        Button circle = new Button();
        Button layers = new Button();
        Button square = new Button();

        stripes.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("resources/strip.jpg"))));

        circle.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("resources/circ.jpg"))));
        layers.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("resources/lay.jpg"))));
        square.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("resources/sq.jpg"))));


        makeButton(stripes);
        makeButton(circle);
        makeButton(layers);
        makeButton(square);

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(100, 140, 100, 140));
        grid.setAlignment(Pos.CENTER);


        grid.add(stripes, 0, 1, 2, 1);
        grid.add(circle, 1, 1);
        grid.add(layers, 0, 2);
        grid.add(square, 1, 2);

        stripes.setOnAction(StripesHandler);
        circle.setOnAction(CircleHandler);
        layers.setOnAction(LayersHandler);
        square.setOnAction(SquareHandler);

        getChildren().add(grid);
    }

    EventHandler<ActionEvent> StripesHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            deleteComponents();
            getChildren().add(new Stripes());
        }
    };

    EventHandler<ActionEvent> CircleHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            deleteComponents();
            getChildren().add(new Circle());
        }
    };
    EventHandler<ActionEvent> SquareHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            deleteComponents();
            getChildren().add(new Square(64.0, 8 ,20, new ArrayList<List<ImageButton>>(), 1));
        }
    };

    EventHandler<ActionEvent> LayersHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            deleteComponents();
            getChildren().add(new Layers());
        }
    };

    private void deleteComponents(){
        getChildren().remove(grid);
    }

    private void makeButton(Button x){
        x.setPrefSize(200, 200);
    }
}
