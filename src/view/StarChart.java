package view;

import cs_1c.SparseMat;
import data.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StarChart extends Application
{
	private GraphView graphView;

	@Override
	public void start(Stage stage)
	{
		DataModel model = new DataModel();
		SparseMat<Character>[] starMap = model.data();
		String title = "Stars near the Earth";
		VBox vbox = new VBox();
		HBox btnPlace = new HBox();
		btnPlace.setId("buttonplace");
		btnPlace.setSpacing(20);
		btnPlace.setAlignment(Pos.CENTER);
		Button YX = new Button("(y, x)");
		YX.setId("dark-blue");
		YX.setOnAction(event -> {
			graphView.clear();
         graphView.update(0); // 0=(y,x) 1=(y,z) 2=(z,x)
      });
		Button YZ = new Button("(y, z)");
		YZ.setId("dark-blue");
		YZ.setOnAction(event -> {
			graphView.clear();
			graphView.update(1); // 0=(y,x) 1=(y,z) 2=(z,x)
      });
		Button ZX = new Button("(z, x)");
		ZX.setId("dark-blue");
		ZX.setOnAction(event -> {
			graphView.clear();
			graphView.update(2); // 0=(y,x) 1=(y,z) 2=(z,x)
      });
		Button close = new Button("Close");
		close.setId("grey");
		close.setOnAction(event -> {
			Platform.exit();
		});
		Button clear = new Button("Clear");
		clear.setId("grey");
		clear.setOnAction(event -> {
			graphView.clear();
		});
		btnPlace.getChildren().addAll(YX, YZ, ZX, clear, close);

		graphView = new GraphView(starMap, title);
		graphView.setMinSize(600, 600);
		graphView.setId("graph");

		vbox.getChildren().addAll(btnPlace, graphView);

		// Creates a scene and adds the graph to the scene.
		Scene scene = new Scene(vbox);
		// Places the scene in the stage
		stage.setScene(scene);

		// Set the stage title
		stage.setTitle("Stars near the Earth");

		scene.getStylesheets().add("chart.css");
		stage.setOnCloseRequest(e -> Platform.exit());
		// Display the stage
		stage.show();

	}

	public static void main(String[] args)
	{
		launch();
	}
}