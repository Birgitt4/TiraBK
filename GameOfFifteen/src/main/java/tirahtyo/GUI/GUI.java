/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirahtyo.GUI;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.util.Duration;
import tirahtyo.viisitoistapeli.GameOfFifteen;
import tirahtyo.viisitoistapeli.GameSolver;
/**
 *
 * Tämä on tällä hetkellä sotku! (mutta tämän voi kyllä ajaa!)
 */
public class GUI extends Application {

    private int size = 100;
    private int fontSize = 34;
    private Pane root = new Pane();
    private StackPane[] tiles;
    private Button shuffle;
    private double duration = 100;
    
    private GameOfFifteen game = new GameOfFifteen();
    
    public void shuffle() {
        game.suffle();
        while (!game.isSolvable()) {
            game.suffle();
        }
        //Järkevä tapa alustaa sama järjestys tilesseille?
        
        for (int i = 0; i < 16; i++) {
            if (game.getGrid()[i] == 0) {
                continue;
            }
            
            int tileNumber = game.getGrid()[i];
            StackPane spane = tiles[tileNumber-1];
            spane.setTranslateX((i%4)*size);
            spane.setTranslateY((i/4)*size);
        }
        
    }
    
    public void solve() {
        
        AnimationTimer timer = new AnimationTimer() {
            private int i = 0;
            private long lastUpdate = 0; 
            private GameOfFifteen helpGame = new GameOfFifteen();
            private GameSolver solver = new GameSolver(game);
            private int[] moves;
            
            @Override
            public void start() {
                helpGame.setGrid(game.getGrid().clone());
                moves = solver.solver();
                if (moves.length == 1 && moves[0] == 0) {
                    this.stop();
                    return;
                }
                super.start();
            }
            @Override
            public void stop() {
                shuffle.setDisable(false);
                super.stop();
            }
            @Override
            public void handle(long now) {
                if (now-lastUpdate >= 1000000*duration) {

                    int blank = helpGame.getBlank();
                    int tileNumber = moves[i];
                    StackPane spane = tiles[tileNumber-1];
                    double tileIndex = (spane.getTranslateX()/size) + (spane.getTranslateY()/size)*4;
                    int tileI = (int) tileIndex;
            
                    if (tileI == blank-4) {
                        moveDown(spane);
                        helpGame.goUp();
                        
                    }
                    else if (tileI == blank-1) {
                        //moveright here and logic left
                        moveRight(spane);
                        helpGame.goLeft();
                    }
                    else if (tileI == blank+1) {
                        //left and right
                        moveLeft(spane);
                        helpGame.goRight();
                    }
                    else if (tileI == blank+4) {
                        moveUp(spane);
                        helpGame.goDown();
                    }
                    lastUpdate = now;
                    i++;
                    if (i == moves.length) {
                        this.stop();
                    }
                }
                
            }
        };
        timer.start();
    }
    
    public Pane createScene() {
        root.setPrefSize(575, 400);
        BackgroundFill bg = new BackgroundFill(Color.GREY, new CornerRadii(0), new Insets(0, 175, 0, 0));
        root.setBackground(new Background(bg));

        Button solve = new Button("Solve");
        solve.setShape(new Circle(60));
        solve.setMinSize(60, 60);
        String styles = "-fx-background-color: red;"
                + "-fx-border-color: black;"
                + "-fx-text-fill: gold;";
        solve.setStyle(styles);
        solve.setTranslateX(450);
        solve.setTranslateY(150);
        shuffle = new Button("Shuffle");
        shuffle.setShape(new Circle(60));
        shuffle.setMinSize(60, 60);
        shuffle.setTranslateX(450);
        shuffle.setTranslateY(50);
        shuffle.setStyle(styles); 
        
        
        solve.setOnMouseClicked((event) -> {
            shuffle.setDisable(true);
            solve();
        });
        
        shuffle.setOnMouseClicked((event) -> {
            shuffle();
        });
        
        
        StackPane s1 = new StackPane();
        StackPane s2 = new StackPane();
        StackPane s3 = new StackPane();
        StackPane s4 = new StackPane();
        StackPane s5 = new StackPane();
        StackPane s6 = new StackPane();
        StackPane s7 = new StackPane();
        StackPane s8 = new StackPane();
        StackPane s9 = new StackPane();
        StackPane s10 = new StackPane();
        StackPane s11 = new StackPane();
        StackPane s12 = new StackPane();
        StackPane s13 = new StackPane();
        StackPane s14 = new StackPane();
        StackPane s15 = new StackPane();
        
        tiles = new StackPane[]{s1, s2, s3, s4, s5, s6, s7, s8, s9,
            s10, s11, s12, s13, s14, s15};        
        
        for (int i = 0; i < 15; i++) {
            int x = (i % 4) * size;
            int y = (i / 4) * size;
            tiles[i].setPrefSize(size, size);
            tiles[i].setTranslateX(x);
            tiles[i].setTranslateY(y);
            
            Text text = createText(i);
            Rectangle rect = createRectangle(i);
            tiles[i].getChildren().addAll(rect, text);
            root.getChildren().addAll(tiles[i]);
        }

        Line line = new Line();
        line.setFill(Color.BLACK);
        line.setStartX(4*size +1);
        line.setStartY(0);
        line.setEndX(4*size +1);
        line.setEndY(4*size);
        line.setStrokeWidth(2);
        
        root.getChildren().addAll(line, solve, shuffle);
        
        return root;
    }
    public Text createText(int i) {
        Text text = new Text(Integer.toString(i+1));
        text.setFill(Color.GOLD);
        text.setFont(new Font(fontSize));
        return text;
    }
    public Rectangle createRectangle(int i) {
        Rectangle rect = new Rectangle();
        if (i % 2 == 0) {
            rect.setFill(Color.WHITE);
        }
        else {
            rect.setFill(Color.RED);
        }
        rect.setArcHeight(10);
        rect.setArcWidth(10);
        rect.setHeight(size);
        rect.setWidth(size);
        rect.setStroke(Color.BLACK);
        return rect;
    }

    
    public void moveLeft(Node node) {
        TranslateTransition left = new TranslateTransition();
        left.setDuration(Duration.millis(duration));
        double from = node.getTranslateX();
        left.setFromX(from);
        left.setToX(from-size);
        left.setNode(node);
        left.play();
    }
    public void moveRight(Node node) {
        TranslateTransition right = new TranslateTransition();
        right.setDuration(Duration.millis(duration));
        double from = node.getTranslateX();
        right.setFromX(from);
        right.setToX(from+size);
        right.setNode(node);
        right.play();
    }
    public void moveDown(Node node) {
        TranslateTransition down = new TranslateTransition();
        down.setDuration(Duration.millis(duration));
        double from = node.getTranslateY();
        down.setFromY(from);
        down.setToY(from+size);
        down.setNode(node);
        down.play();
    }
    public void moveUp(Node node) {
        TranslateTransition up = new TranslateTransition();
        up.setDuration(Duration.millis(duration));
        double from = node.getTranslateY();
        up.setFromY(from);
        up.setToY(from-size);
        up.setNode(node);
        up.play();
    }

    
    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(createScene());
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(GUI.class);
    }
}
