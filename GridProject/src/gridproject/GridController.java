/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gridproject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hruteesh Raja
 */
public class GridController implements Initializable {
    int size=StartController.Size;
    Circle points[][];
    Line hLines[];
    Line vLines[];
    public AnchorPane CA;
    boolean start=false,right=false;
    int cRow=-1,cCol=-1;
    Polyline trace;
    int turn=-1;
    public static ArrayList<Integer> rows;
    public static ArrayList<Integer> cols;
    int startX, startY;
    public static ArrayList<Integer> pointI;
    public static ArrayList<Integer> pointJ;
    
    HashSet<Integer> rowsh;
    HashSet<Integer> colsh;
    
    Line green;
    public void Reset(ActionEvent event)  throws Exception{
        Parent p =  FXMLLoader.load(getClass().getResource("Grid.fxml"));
        Scene SquareScene = new Scene(p);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(SquareScene);
        window.show();
    }
    public void Finish(ActionEvent event)  throws Exception{
        Parent p =  FXMLLoader.load(getClass().getResource("Output.fxml"));
        Scene SquareScene = new Scene(p);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(SquareScene);
        window.show();
    }
    public void back(ActionEvent event)  throws Exception{
        Parent p =  FXMLLoader.load(getClass().getResource("Start.fxml"));
        Scene SquareScene = new Scene(p);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(SquareScene);
        window.show();
        StartController.Size=4;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hLines=new Line[size];
        vLines=new Line[size];
        points=new Circle[size][size];
        trace=new Polyline();
        trace.setStrokeWidth(4);
        CA.getChildren().add(trace);
        rowsh=new HashSet<>();
        colsh=new HashSet<>();
        rows=new ArrayList();
        cols=new ArrayList();
        pointI=new ArrayList();
        pointJ=new ArrayList();
        
        green=new Line();
        green.setStrokeWidth(4);
        green.toBack();
        green.setStroke(Color.GREEN);
        green.getStrokeDashArray().addAll( 20d, 10d);

        CA.getChildren().add(green);
        double incr=700/(size-1);
        double y=100;
        double x=100;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                points[i][j]=new Circle(x,y,10);
                
                CA.getChildren().add(points[i][j]);
                x+=incr;
                final int I=i,J=j;
                points[i][j].setOnMouseClicked(t ->{
                    if(!(cRow==I&&cCol==J)){
                        
                        if(start && right){
                            green.setVisible(true);
                            int cTurn=0;
                            if(I==cRow){
                                if(cCol<J)
                                    cTurn=0;
                                else
                                    cTurn=2;
                            }
                            else if(J==cCol){
                                if(cRow<I)
                                    cTurn=1;
                                else
                                    cTurn=3;
                            }
                            else{
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Information Dialog");
                                alert.setHeaderText(null);
                                alert.setContentText("You can only traverse through the grid.");
                                alert.showAndWait();
                                cTurn=40;
                            }
                            System.out.println(cTurn+" "+turn);
                            if(cTurn!=40){
                                if((turn+1)%4==cTurn && ((!rowsh.contains(I) &&!colsh.contains(J))|| (rowsh.size()==size&&colsh.size()==size-1)||(rowsh.size()==size-1&&colsh.size()==size))){
                                    Double temp[]=new Double[2];
                                    temp[0]=points[I][J].getCenterX();
                                    temp[1]=points[I][J].getCenterY();
                                    trace.getPoints().addAll(temp);
                                    turn=cTurn;
                                    cRow=I;
                                    cCol=J;
                                    points[I][J].setFill(Color.BLUE);
                                    points[I][J].setRadius(20);
                                    pointI.add(I);
                                    pointJ.add(J);
                                    green.setStartX(points[I][J].getCenterX());
                                    green.setStartY(points[I][J].getCenterY());
                                    green.setEndX(points[I][J].getCenterX());
                                    green.setEndY(points[I][J].getCenterY());
                                    
                                    if(turn==0){
                                        green.setEndY(800);
                                        
                                    }
                                    else if(turn==1){
                                        green.setEndX(100);
                                    }
                                    else if(turn==2){
                                        green.setEndY(100);
                                    }
                                    else{
                                        green.setEndX(800);
                                        
                                    }
                                    if(turn==0||turn==2){
                                        rowsh.add(I);
                                        rows.add(I);
                                    }
                                    else{
                                        cols.add(J);
                                        colsh.add(J);
                                    }    
                                }
                                else{
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Information Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Wrong way. You are only allowed to make right turns and cannot traverse the same row/column more than once.");
                                    alert.showAndWait();
                                }
                            }
                        }
                        else if(start){
                            right=true;
                            if(I==cRow){
                                if(cCol<J)
                                    turn=0;
                                else
                                    turn=2;
                            }
                            else if(J==cCol){
                                if(cCol<I)
                                    turn=1;
                                else
                                    turn=3;
                            }
                            Double temp[]=new Double[2];
                            temp[0]=points[I][J].getCenterX();
                            temp[1]=points[I][J].getCenterY();
                            trace.getPoints().addAll(temp);
                            cRow=I;
                            cCol=J;
                            
                            green.setStartX(points[I][J].getCenterX());
                            green.setStartY(points[I][J].getCenterY());
                            green.setEndX(points[I][J].getCenterX());
                            green.setEndY(points[I][J].getCenterY());
                            if(turn==0){
                                green.setEndY(800);
                            }
                            else if(turn==1){
                                green.setEndX(100);
                            }
                            else if(turn==2){
                                green.setEndY(100);
                            }
                            else{
                                green.setEndX(800);
                            }
                            points[I][J].setFill(Color.BLUE);
                            points[I][J].setRadius(20);
                            pointI.add(I);
                            pointJ.add(J);
                            if(turn==0||turn==2){
                                rowsh.add(I);
                                rows.add(I);
                            }
                            else{
                                cols.add(J);
                                colsh.add(J);
                            }
                        }
                        else{
                            start=true;
                            cRow=I;
                            cCol=J;
                            points[I][J].setFill(Color.BLUE);
                            points[I][J].setRadius(20);
                            Double temp[]=new Double[2];
                            temp[0]=points[I][J].getCenterX();
                            temp[1]=points[I][J].getCenterY();
                            trace.getPoints().addAll(temp);
                            
                            rows.clear();
                            cols.clear();
                            rowsh.clear();
                            colsh.clear();
                            startX=I;
                            startY=J;
                            pointI.add(I);
                            pointJ.add(J);
                            
                         
                        }
                    }
                    if((rowsh.size()==size ||colsh.size()==size)&&startX==I&&startY==J){
                        green.setVisible(false);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("You have completed the grid! Press Finish to see output.");
                        alert.showAndWait();
                                   
                    }
                    
                });
                
            }
            x=100;
            y+=incr;
        }
        x=100;
        y=100;
        for(int i=0;i<size;i++){
            vLines[i]=new Line();
            vLines[i].getStrokeDashArray().addAll( 10d, 20d);
            vLines[i].setStartX(x);
            vLines[i].setStartY(100);
            vLines[i].setEndX(x);
            vLines[i].setEndY(800);
            CA.getChildren().add(vLines[i]);
            vLines[i].toBack();
            x+=incr;
        }
        for(int i=0;i<size;i++){
            hLines[i]=new Line();
            hLines[i].getStrokeDashArray().addAll( 10d, 20d);
            hLines[i].setStartX(100);
            hLines[i].setStartY(y);
            hLines[i].setEndX(800);
            hLines[i].setEndY(y);
            CA.getChildren().add(hLines[i]);
            hLines[i].toBack();
            y+=incr;
        }
        
        
        
    }    
    
}
