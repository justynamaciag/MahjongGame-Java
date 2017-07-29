import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class MahjongJavaGame extends Application {

    public static void main(String[] args)
    {
        launch(args);
    }
    public void start(Stage primaryStage) {

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        primaryStage.setTitle("Mahjong");

        MainPane frame = new MainPane();

        Layers l = new Layers();

        primaryStage.setResizable(false);

        primaryStage.setScene(new Scene(frame, screenSize.getHeight()/1.5, screenSize.getWidth()/2));
        primaryStage.show();
    }

}
