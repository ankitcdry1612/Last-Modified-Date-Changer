import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{
	private File file;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Pane pane=new Pane();
		pane.setBackground(new Background(new BackgroundFill(Color.BROWN, null, null)));
		Text title=new Text("File last modified date changer");
		title.setX(80);
		title.setY(70);
		title.setFont(Font.font(18));
		Button close=new Button("X");
		close.setLayoutX(465);
		close.setLayoutY(0);
		close.setStyle("-fx-background-color: peachpuff;");
		close.setTextFill(Color.BLACK);
		close.setFont(Font.font(18));
		close.setOnAction(e ->{
			System.exit(0);
		});
		TextField filepath=new TextField("Browse File");
		filepath.setLayoutX(80);
		filepath.setLayoutY(100);
		Button browse=new Button("Browse");
		browse.setLayoutX(280);
		browse.setLayoutY(100);
		browse.setOnAction(e ->{
			file=chooseFile(stage);
			filepath.setText(file.getPath());
		});
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		TextField newDate=new TextField();
		newDate.setLayoutX(80);
		newDate.setLayoutY(150);
		Text newdate=new Text("Enter New Date in DD/MM/YY format");
		newdate.setX(280);
		newdate.setY(165);
		Button change=new Button("Change");
		change.setLayoutX(280);
		change.setLayoutY(200);
		change.setOnAction(e ->{
			try {
				Date newLastModifiedDate = dateFormat.parse(newDate.getText());
				file.setLastModified(newLastModifiedDate.getTime());
			} catch (ParseException e1) {
				
			}
		});
		pane.getChildren().addAll(title,close,filepath,browse,newDate,newdate,change);
		Scene scene=new Scene(pane,500,300);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
	}
	public File chooseFile(Stage stage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		File file=fileChooser.showSaveDialog(stage);
		return file;
	}

}
