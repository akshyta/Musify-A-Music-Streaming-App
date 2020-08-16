
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
 
public class ArtistBio extends Application {
	
	int artistID;
	 
	ArtistBio(int id)
	{ 
		artistID=id; 
	}

	public static void main(String[] args) {
        launch(args); 
    } 
   
    @Override
    public void start(final Stage primaryStage) throws SQLException {
    	primaryStage.setTitle("Update Bio");
       
        GridPane grid=new GridPane();
        grid.setPadding(new Insets(100,0,0,50)); // top,right,bottom,left
        grid.setVgap(20);   
        grid.setHgap(20);     
         
        String f="-fx-font-weight: bolder ; -fx-text-fill: black; -fx-font:20px sans-serif";
        
        Label biol=new Label("Update Bio:"); 
        biol.setStyle("-fx-font-weight: bolder ; -fx-text-fill: white; -fx-font:20px sans-serif");
        GridPane.setConstraints(biol,0,0); 
        final TextField bio=new TextField();         
        bio.setPromptText("Enter New Bio");
        GridPane.setConstraints(bio,1,0);
       
        Button update = new Button();
        update.setStyle(f);
        update.setText("Update");
        GridPane.setConstraints(update,1,1);
        
        EventHandler<ActionEvent> updateBioEvent=new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e)  
            { 
            	try {
            		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Musify","root","sql@iiitdelhi");
            		Statement sta=con.createStatement();
            		System.out.println(bio.getText());
            		String q="Update Artist set ArtistBio = \"" +bio.getText()+"\" where ArtistID = " + artistID+";";
            		System.out.println(q);
            		sta.executeUpdate(q);
            		new ArtistPage(artistID).start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } 
        }; 
        update.setOnAction(updateBioEvent);
           
 
        grid.setStyle("-fx-background-image: url('https://png.pngtree.com/thumb_back/fw800/back_our/20190621/ourmid/pngtree-cool-black-background-promotion-main-map-image_188883.jpg')");
        grid.getChildren().addAll(biol,bio,update);
        primaryStage.setScene(new Scene(grid,600,640));
        primaryStage.show();
    }
}