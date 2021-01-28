/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gridproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

/**
 *
 * @author Hruteesh Raja
 */
public class StartController implements Initializable {
    public static int Size=4;
    public Label size;
    public Slider ss;
    public boolean slider=false;
    public void createPolygon(ActionEvent event)  throws Exception{
        Parent p =  FXMLLoader.load(getClass().getResource("Grid.fxml"));
        Scene SquareScene = new Scene(p);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(SquareScene);
        window.show();
    }
        public void slided(){
        if(slider){
            int x=(int)Math.floor(ss.getValue());
            x=x%2==0?x:x+1;
            size.setText(Integer.toString(x));
            Size=x;
        }
    }
    
    public void sliderPressed(){
        slider=true;
    }
    public void sliderReleased(){
        slider=false;
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ss.setMin(4);
       ss.setMax(12);
    }    
 
    
}
