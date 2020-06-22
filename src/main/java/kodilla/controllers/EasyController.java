package kodilla.controllers;

import kodilla.Computer;
import kodilla.Human;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EasyController {

    Human player = new Human();
    Computer computer = new Computer();
    Line line = new Line();
    private boolean turnX = true;
    private boolean playable = true;
    private Button[] buttons;
    private Random random = new Random();


    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private Button newGame;
    @FXML
    private GridPane grid;
    @FXML
    private Button playerXPoints;
    @FXML
    private Button playerOPoints;
    @FXML
    private MenuItem hard;


    @FXML
    void initialize() {

        buttons = new Button[9];
        buttons[0] = button1;
        buttons[1] = button2;
        buttons[2] = button3;
        buttons[3] = button4;
        buttons[4] = button5;
        buttons[5] = button6;
        buttons[6] = button7;
        buttons[7] = button8;
        buttons[8] = button9;

        setButtonsAction();
        getPlayerXPoints();
        getPlayerOPoints();

        newGame.setOnMouseClicked(event -> {
            clearButtons();
            turnX = true;
        });
    }

    private void clearButtons() {
        for (Button button : buttons) {
            button.setText("");
            playable = true;
        }
        grid.getChildren().remove(line);
    }

    private void setButtonsAction() {
        for (Button button : buttons) {
            button.setOnMouseClicked(event -> {
                playerMove(button);
                checkState();
                if (!turnX && playable) {
                    computerMove();
                    checkState();
                }
                if (!playable) {
                    showEndGame();
                    clearButtons();
                }
            });
        }
    }

    private void showEndGame() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Congratulations!");
        alert.setContentText("You win!");

        alert.showAndWait();
        playable = true;
    }

    private void playerMove(Button button) {
        if ("".equals(button.getText())) {
            button.setText("X");
            if (button.getText().equals("X") || button.getText().equals("O")) {
                turnX = true;
            }
            turnX = false;
        }
    }

    private void computerMove() {
        if (playable) {
            do {
                int drawn = random.nextInt(buttons.length);
                Button button = buttons[drawn];
                if ("".equals(button.getText())) {
                    button.setText("O");
                    turnX = true;
                }
            } while (!turnX);
        }
    }

    private void checkState() {

        // vertical
        for (int i = 0; i < 3; i++) {
            List<String> buttonsText = new ArrayList<>();
            buttonsText.add(buttons[i].getText());
            buttonsText.add(buttons[i + 3].getText());
            buttonsText.add(buttons[i + 6].getText());

            long xCount = buttonsText.stream().filter("X"::equals).count();
            long oCount = buttonsText.stream().filter("O"::equals).count();

            if (xCount == 3 || oCount == 3) {
                if (xCount == 3) {
                    player.addPoints();
                }
                if (oCount == 3) {
                    computer.addPoints();
                }
                playable = false;
                winAnimation(buttons[i], buttons[i + 6], Direction.VERTICAL);
                break;
            }
        }
        // horizontal
        for (int i = 0; i < 9; i = i + 3) {
            List<String> buttonsText = new ArrayList<>();
            buttonsText.add(buttons[i].getText());
            buttonsText.add(buttons[i + 1].getText());
            buttonsText.add(buttons[i + 2].getText());

            long xCount = buttonsText.stream().filter("X"::equals).count();
            long oCount = buttonsText.stream().filter("O"::equals).count();

            if (xCount == 3 || oCount == 3) {
                if (xCount == 3) {
                    player.addPoints();
                }
                if (oCount == 3) {
                    computer.addPoints();
                }
                playable = false;
                winAnimation(buttons[i], buttons[i + 2], Direction.HORIZONTAL);
                break;
            }
        }

        // diagonal
        List<String> buttonsText = new ArrayList<>();
        buttonsText.add(buttons[0].getText());
        buttonsText.add(buttons[4].getText());
        buttonsText.add(buttons[8].getText());
        long xCount = buttonsText.stream().filter("X"::equals).count();
        long oCount = buttonsText.stream().filter("O"::equals).count();

        if (xCount == 3 || oCount == 3) {
            if (xCount == 3) {
                player.addPoints();
            }
            if (oCount == 3) {
                computer.addPoints();
            }
            playable = false;
            winAnimation(buttons[0], buttons[8], Direction.DIAGONAL);
            return;
        }
        List<String> buttonsText1 = new ArrayList<>();
        buttonsText1.add(buttons[2].getText());
        buttonsText1.add(buttons[4].getText());
        buttonsText1.add(buttons[6].getText());
        xCount = buttonsText1.stream().filter("X"::equals).count();
        oCount = buttonsText1.stream().filter("O"::equals).count();

        if (xCount == 3 || oCount == 3) {
            if (xCount == 3) {
                player.addPoints();
            }
            if (oCount == 3) {
                computer.addPoints();
            }
            playable = false;
            winAnimation(buttons[2], buttons[6], Direction.ANTIDIAGONAL);
        }
        buttonsText1.clear();
    }

    private void winAnimation(Button button1, Button button2, Direction direction) {

        if (Direction.HORIZONTAL.equals(direction)) {
            line.setStartX(button1.getLayoutX() + 90);

            line.setStartY(button1.getLayoutY() + 45);

            line.setEndX(button2.getLayoutX() + 180);

            line.setEndY(button2.getLayoutY() + 45);
        }

        if (Direction.VERTICAL.equals(direction)) {
            line.setStartX(button1.getLayoutX() + 45);

            line.setStartY(button1.getLayoutY() + 100);

            line.setEndX(button2.getLayoutX() + 45);

            line.setEndY(button2.getLayoutY() + 170);

//            line.setTranslateY(button1.getLayoutY() + 90);
//            line.setTranslateX(button2.getLayoutX() + 45);
        }

        if (Direction.DIAGONAL.equals(direction)) {
            line.setStartX(button1.getLayoutX() + 90);

            line.setStartY(button1.getLayoutY() + 90);

            line.setEndX(button2.getLayoutX() + 90);

            line.setEndY(button2.getLayoutY() + 90);

//            line.setTranslateY(button1.getLayoutY() + 90);
//            line.setTranslateX(button1.getLayoutX() + 45);
        }

        if (Direction.ANTIDIAGONAL.equals(direction)) {
            line.setStartX(button1.getLayoutX() + 90);

            line.setStartY(button1.getLayoutY() + 90);

            line.setEndX(button2.getLayoutX() + 90);

            line.setEndY(button2.getLayoutY() + 90);
        }

        grid.getChildren().remove(line);
        grid.getChildren().add(line);
        getPlayerXPoints();
        getPlayerOPoints();
    }

    private void getPlayerXPoints() {
        playerXPoints.setText(player.getPoints());
    }

    private void getPlayerOPoints() {
        playerOPoints.setText(computer.getPoints());
    }
}
