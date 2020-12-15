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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.util.Duration;
import tirahtyo.tests.PerformanceTests;
import tirahtyo.viisitoistapeli.GameOfFifteen;
import tirahtyo.viisitoistapeli.GameSolver;
/**
 * Gui for 15 puzzle and 15-puzzle solver
 * 
 */
public class GUI extends Application {

    private Stage stage;
    private final int size = 100;
    private final int fontSize = 34;
    private Pane root = new Pane();
    private StackPane[] tiles;
    private Button shuffle;
    private Button solve;
    private final double duration = 200;
    private String route;
    private Label movesMade;
    private String time;
    private Button[] buttons = new Button[5];
    
    private GameOfFifteen game = new GameOfFifteen();
    /**
     * calls GameOfFifteen shuffle and then shows the mixed board.
     */
    public void shuffle() {
        game.suffle();
        while (!game.isSolvable()) {
            game.suffle();
        }
        for (int i = 0; i < 16; i++) {
            if (game.getGrid()[i] == 0) {
                continue;
            }
            int tileNumber = game.getGrid()[i];
            StackPane spane = tiles[tileNumber-1];
            spane.setTranslateX((i%4)*size);
            spane.setTranslateY((i/4)*size);
        }
        disableButtons(false);
    }
    /**
     * Calls solver and then shows animation of the solution.
     */
    public void solve() {
        route = "Moves:\n";
        AnimationTimer timer = new AnimationTimer() {
            private int i = 0;
            private long lastUpdate = 0; 
            private GameOfFifteen helpGame = new GameOfFifteen();
            private GameSolver solver = new GameSolver(game);
            private int[] moves;
            
            @Override
            public void start() {
                helpGame.setGrid(game.getGrid().clone());
                long begin = System.nanoTime();
                moves = solver.solver();
                long end = System.nanoTime();
                time = "Time: " + (end-begin)/1000000 + "ms";
                if (moves.length == 1 && moves[0] == 0) {
                    this.stop();
                    return;
                }
                super.start();
            }
            @Override
            public void stop() {
                disableButtons(false);
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
                        route +="d, ";
                        moveDown(spane);
                        helpGame.goUp();
                        
                    }
                    else if (tileI == blank-1) {
                        route +="r, ";
                        moveRight(spane);
                        helpGame.goLeft();
                    }
                    else if (tileI == blank+1) {
                        route +="l, ";
                        moveLeft(spane);
                        helpGame.goRight();
                    }
                    else if (tileI == blank+4) {
                        route +="u, ";
                        moveUp(spane);
                        helpGame.goDown();
                    }
                    lastUpdate = now;
                    i++;
                    if (i % 10 == 0) {
                        route += "\n";
                    }
                    if (i == moves.length) {
                        route += i + " moves\n" + time;
                        movesMade.setText(route);
                        movesMade.setVisible(true);
                        this.stop();
                    }
                }
                
            }
        };
        timer.start();
    }
    /**
     * Building the main scene
     * @return Pane for the Scene
     */
    public Pane createScene() {
        root.setPrefSize(700, 400);
        BackgroundFill bg = new BackgroundFill(Color.GREY, new CornerRadii(0), new Insets(0, 300, 0, 0));
        root.setBackground(new Background(bg));
        
        solve = new Button("Solve");
        solve.setShape(new Circle(60));
        solve.setMinSize(60, 60);
        String styles = "-fx-background-color: red;"
                + "-fx-border-color: black;"
                + "-fx-text-fill: gold;";
        solve.setStyle(styles);
        solve.setTranslateX(450);
        solve.setTranslateY(50);
        buttons[0] = solve;
        shuffle = new Button("Shuffle");
        shuffle.setShape(new Circle(60));
        shuffle.setMinSize(60, 60);
        shuffle.setTranslateX(550);
        shuffle.setTranslateY(50);
        shuffle.setStyle(styles); 
        buttons[1] = shuffle;
        
        solve.setOnMouseClicked((event) -> {
            disableButtons(true);
            solve();
        });
        shuffle.setOnMouseClicked((event) -> {
            movesMade.setVisible(false);
            disableButtons(true);
            shuffle();
        });
        movesMade = new Label(route);
        movesMade.setFont(new Font("Verdana", 14));
        movesMade.setTranslateX(450);
        movesMade.setTranslateY(120);
        
        tiles = new StackPane[15];        
        for (int i = 0; i < 15; i++) {
            final StackPane tile = new StackPane();
            tiles[i] = tile;
            int x = (i % 4) * size;
            int y = (i / 4) * size;
            tiles[i].setPrefSize(size, size);
            tiles[i].setTranslateX(x);
            tiles[i].setTranslateY(y);
            Text text = createText(i);
            Rectangle rect = createRectangle(i);
            tiles[i].getChildren().addAll(rect, text);
            root.getChildren().add(tiles[i]);
            tiles[i].setOnMouseClicked(event -> {
                makeMove(tile);
            });
        }

        Line line = new Line();
        line.setFill(Color.BLACK);
        line.setStartX(4*size +1);
        line.setStartY(0);
        line.setEndX(4*size +1);
        line.setEndY(4*size);
        line.setStrokeWidth(2);
        
        Button tests = new Button("performance testing");
        tests.setTranslateX(450);
        tests.setTranslateY(350);
        tests.setFocusTraversable(false);
        buttons[2] = tests;
        tests.setOnMouseClicked(event -> {
            askFileName();
        });
        
        root.getChildren().addAll(line, solve, shuffle, movesMade, tests);
        return root;
    }

    private Text createText(int i) {
        Text text = new Text(Integer.toString(i+1));
        text.setFill(Color.GOLD);
        text.setFont(new Font(fontSize));
        return text;
    }

    private Rectangle createRectangle(int i) {
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

    /**
     * makes a transition to the left for a tile.
     * @param node tile what should move.
     */
    public void moveLeft(Node node) {
        TranslateTransition left = new TranslateTransition();
        left.setDuration(Duration.millis(duration));
        double from = node.getTranslateX();
        left.setFromX(from);
        left.setToX(from-size);
        left.setNode(node);
        left.play();
    }
    /**
     * makes a transition to the right for a tile.
     * @param node tile what should move.
     */
    public void moveRight(Node node) {
        TranslateTransition right = new TranslateTransition();
        right.setDuration(Duration.millis(duration));
        double from = node.getTranslateX();
        right.setFromX(from);
        right.setToX(from+size);
        right.setNode(node);
        right.play();
    }
    /**
     * makes a transition down for a tile.
     * @param node tile what should move.
     */
    public void moveDown(Node node) {
        TranslateTransition down = new TranslateTransition();
        down.setDuration(Duration.millis(duration));
        double from = node.getTranslateY();
        down.setFromY(from);
        down.setToY(from+size);
        down.setNode(node);
        down.play();
    }
    /**
     * makes a transition up for a tile.
     * @param node tile what should move.
     */
    public void moveUp(Node node) {
        TranslateTransition up = new TranslateTransition();
        up.setDuration(Duration.millis(duration));
        double from = node.getTranslateY();
        up.setFromY(from);
        up.setToY(from-size);
        up.setNode(node);
        up.play();
    }
    /**
     * If user clicks a tile, the tile should move to the empty space (if allowed move).
     * @param tile tile that the user clicked.
     */
    public void makeMove(StackPane tile) {
        int x = (int) tile.getTranslateX();
        int y = (int) tile.getTranslateY();
        int i = x / size + (y / size) * 4;
        int blank = game.getBlank();
        if (blank == i+4) {
            moveDown(tile);
            game.goUp();
        } else if (blank == i-4) {
            moveUp(tile);
            game.goDown();
        } else if (blank == i-1) {
            moveLeft(tile);
            game.goRight();
        } else if (blank == i+1) {
            moveRight(tile);
            game.goLeft();
        }
    }
    /**
     * new window for performance testing. Asks for a name of a file where the test
     * results are saved.
     */
    public void askFileName() {
        Stage fileStage = new Stage();
        Pane filePane = new Pane();
        filePane.setPrefSize(300, 200);
        Label askFile = new Label("Where do you want to save results of the tests?\n"
                + "Give name/path of the file:\n"
                + "(Note that performance tests might take a while!)");
        askFile.setTranslateX(30);
        askFile.setTranslateY(30);
        TextField getFile = new TextField();
        getFile.setTranslateX(30);
        getFile.setTranslateY(100);
        
        Button go = new Button("Drive tests!");
        go.setTranslateX(30);
        go.setTranslateY(150);
        buttons[3] = go;
        Button cancel = new Button("cancel");
        cancel.setTranslateX(150);
        cancel.setTranslateY(150);
        buttons[4] = cancel;
        filePane.getChildren().addAll(askFile, getFile, go, cancel);
        go.setOnMouseClicked(event -> {
            if (!getFile.getText().isEmpty()) {
                disableButtons(true);
                driveTests(getFile.getText());
            }
        });
        cancel.setOnMouseClicked(event -> {
            fileStage.close();
        });
        fileStage.setScene(new Scene(filePane));
        fileStage.show();
    }
    private void driveTests(String file) {
        PerformanceTests test = new PerformanceTests(file);
        test.run();
        test.runSmallerBoards();
        try {
            test.saveTests();
        } catch (Exception e) {
            e.getMessage();
        }
        disableButtons(false);
    }
    private void disableButtons(boolean disable) {
        for (Button button : buttons) {
            if (button != null) {
                button.setDisable(disable);
            }
        }
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        Scene scene = new Scene(createScene());
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(GUI.class);
    }
}
