/**
 * @author AJWuu
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import java.util.Random;

public class DessertGame extends Application {

	private int score = 0;

	private void randomizeButtonPositions(Random randomGenerator, Button[] buttons) {
		for (Button button : buttons) {
			button.setLayoutX(600*randomGenerator.nextDouble());
			button.setLayoutY(400*randomGenerator.nextDouble());
		}
	}

	@Override
	public void start(final Stage stage) {
		//Change window size and set title
		Scene scene = new Scene(borderPane, 640, 480);
		stage.setTitle("Dessert in the Desert JavaFX Game");

		BorderPane borderPane = new BorderPane();
		Label scoreLabel = new Label("Score: 0");
		borderPane.setTop(scoreLabel);
		BorderPane.setAlignment(scoreLabel, Pos.TOP_LEFT);

		Button exitButton = new Button("Exit");
		exitButton.setOnAction(event -> {
			Platform.exit();
		});
		exitButton.requestFocus(); //keep the Exit button in focus
		borderPane.setBottom(exitButton);
		BorderPane.setAlignment(exitButton, Pos.BOTTOM_RIGHT);

		//pane for the game blocks to move on
		Pane pane = new Pane();
		borderPane.setCenter(pane);
		BorderPane.setAlignment(pane, Pos.CENTER);

		//game buttons
		Button button0 = new Button("Dessert");
		Button button1 = new Button("Desert");
		Button button2 = new Button("Desert");
		Button button3 = new Button("Desert");
		Button button4 = new Button("Desert");
		Button button5 = new Button("Desert");
		Button button6 = new Button("Desert");
		Button button7 = new Button("Desert");
		Button[] buttons = {button0, button1, button2, button3, 
				button4, button5, button6, button7};
		randomizeButtonPositions(new Random(),buttons); //set initial random positions

		//mouse events
		EventHandler<ActionEvent> clickRight = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				score++;
				exitButton.requestFocus();
				scoreLabel.setText("Score: " + score);
				randomizeButtonPositions(new Random(),buttons);
			}
		};
		EventHandler<ActionEvent> clickWrong = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				score--;
				exitButton.requestFocus();
				scoreLabel.setText("Score: " + score);
				randomizeButtonPositions(new Random(),buttons);
			}
		};

		//connect with actions
		for (Button button : buttons) {
			if (button == button0) {
				button.setOnAction(clickRight);
			}
			else {
				button.setOnAction(clickWrong);
			}
			pane.getChildren().add(button);
		}

		//Display
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch();
	}

}
