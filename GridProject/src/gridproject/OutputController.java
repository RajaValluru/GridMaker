/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gridproject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hruteesh Raja
 */
public class OutputController implements Initializable {
    public AnchorPane gridPane;
    public AnchorPane factPane;
    public AnchorPane permPane;
    public AnchorPane tangPane;
    public Label rowPerm;
    public Label colPerm;
    public Label colFact;
    public Label rowFact;
    ArrayList pointI;
    ArrayList pointJ;
    int size=StartController.Size;
    Circle pointG[][];
    Circle pointIG[][];
    Line vLinesG[];
    Line hLinesG[];
    Line vLinesIG[];
    Line hLinesIG[];
    ArrayList<Integer> rows;
    ArrayList<Integer> cols;
    double coordinates[][];
    public void setGraph(double centerX, double centerY, double radius, int sides) {
        coordinates=new double[StartController.Size][2];
        final double angleStep = Math.PI * 2 / sides;
        double angle = 0; 
        for (int i = 0; i < sides; i++, angle += angleStep) {
            coordinates[i][0]=Math.sin(angle) * radius + centerX;
            coordinates[i][1]=Math.cos(angle) * radius + centerY; 
        }
        
    }
    public void back(ActionEvent event)  throws Exception{
        Parent p =  FXMLLoader.load(getClass().getResource("Grid.fxml"));
        Scene SquareScene = new Scene(p);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(SquareScene);
        window.show();
    }
    public boolean findIntersection(Line a,Line b){
        double intersection[]=new double[2];
        double m1=(a.getStartY()-a.getEndY())/(a.getStartX()-a.getEndX());
        double m2=(b.getStartY()-b.getEndY())/(b.getStartX()-b.getEndX());
        double b1=-m1*a.getStartX()+a.getStartY();
        double b2=-m2*b.getStartX()+b.getStartY();
        intersection[0]=(b2-b1)/(m1-m2);
        intersection[1]=m1*(intersection[0])+b1;
        double astartX=a.getStartX()>a.getEndX()?a.getEndX():a.getStartX();
        double astartY=a.getStartY()>a.getEndY()?a.getEndY():a.getStartY();
        double aendX=a.getStartX()>a.getEndX()?a.getStartX():a.getEndX();
        double aendY=a.getStartY()>a.getEndY()?a.getStartY():a.getEndY();
        double bstartX=b.getStartX()>b.getEndX()?b.getEndX():b.getStartX();
        double bstartY=b.getStartY()>b.getEndY()?b.getEndY():b.getStartY();
        double bendX=b.getStartX()>b.getEndX()?b.getStartX():b.getEndX();
        double bendY=b.getStartY()>b.getEndY()?b.getStartY():b.getEndY();
        
        if(intersection[0]>=astartX&&intersection[0]<=aendX &&intersection[1]>=astartY && intersection[1]<=aendY &&intersection[0]>=bstartX&&intersection[0]<=bendX &&intersection[1]>=bstartY && intersection[1]<=bendY  ){
                return true;
        }
         return false;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pointI=GridController.pointI;
        pointJ=GridController.pointJ;
        pointG=new Circle[size][size];
        pointIG=new Circle[size][size];
        vLinesG=new Line[size];
        hLinesG=new Line[size];
        vLinesIG=new Line[size];
        hLinesIG=new Line[size];
        rows=GridController.rows;
        cols=GridController.cols;
        
        double incr=350/(size-1);
        double y=50;
        double x=50;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                pointG[i][j]=new Circle(x,y,3);
                
                gridPane.getChildren().add(pointG[i][j]);
                x+=incr;
            }
            x=50;
            y+=incr;
        }
        for(int i=0;i<size;i++){
            vLinesG[i]=new Line();
            vLinesG[i].getStrokeDashArray().addAll( 10d, 20d);
            vLinesG[i].setStartX(x);
            vLinesG[i].setStartY(50);
            vLinesG[i].setEndX(x);
            vLinesG[i].setEndY(400);
            gridPane.getChildren().add(vLinesG[i]);
            vLinesG[i].toBack();
            x+=incr;
        }
        y=50;
        for(int i=0;i<size;i++){
            hLinesG[i]=new Line();
            hLinesG[i].getStrokeDashArray().addAll( 10d, 20d);
            hLinesG[i].setStartX(50);
            hLinesG[i].setStartY(y);
            hLinesG[i].setEndX(400);
            hLinesG[i].setEndY(y);
            gridPane.getChildren().add(hLinesG[i]);
            hLinesG[i].toBack();
            y+=incr;
        }
        
        x=50;
        y=500;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                pointIG[i][j]=new Circle(x,y,3);
                
                gridPane.getChildren().add(pointIG[i][j]);
                x+=incr;
            }
            x=50;
            y+=incr;
        }
        for(int i=0;i<size;i++){
            vLinesIG[i]=new Line();
            vLinesIG[i].getStrokeDashArray().addAll( 10d, 20d);
            vLinesIG[i].setStartX(x);
            vLinesIG[i].setStartY(500);
            vLinesIG[i].setEndX(x);
            vLinesIG[i].setEndY(850);
            gridPane.getChildren().add(vLinesIG[i]);
            vLinesIG[i].toBack();
            x+=incr;
        }
        y=500;
        for(int i=0;i<size;i++){
            hLinesIG[i]=new Line();
            hLinesIG[i].getStrokeDashArray().addAll( 10d, 20d);
            hLinesIG[i].setStartX(50);
            hLinesIG[i].setStartY(y);
            hLinesIG[i].setEndX(400);
            hLinesIG[i].setEndY(y);
            gridPane.getChildren().add(hLinesIG[i]);
            hLinesIG[i].toBack();
            y+=incr;
        }
        Circle temp=null;
        for(int i=0;i<pointI.size();i++){
            Line tempAdd=new Line();
            int r=Integer.parseInt(pointI.get(i).toString());
            int c=Integer.parseInt(pointJ.get(i).toString());
            
                pointG[r][c].setRadius(10);
                pointG[r][c].setFill(Color.BLUE);
            if(i==0){
                temp=pointG[r][c];
            }
            else{
                tempAdd.setStartX(pointG[r][c].getCenterX());
                tempAdd.setStartY(pointG[r][c].getCenterY());
                tempAdd.setEndX(temp.getCenterX());
                tempAdd.setEndY(temp.getCenterY());
                tempAdd.setStrokeWidth(2);
                temp=pointG[r][c];
                
                
            }
            gridPane.getChildren().add(tempAdd);
            pointG[r][c].toFront();
        }
        temp=null;
        rows.add(rows.get(0));
        cols.add(cols.get(0));
        
        System.out.println(rows.size());
        System.out.println(cols.size());
        System.out.println(Arrays.toString(rows.toArray()));
        System.out.println(Arrays.toString(cols.toArray()));
        
        int a[][]=new int[2*size+1][2];
        int rc=0,cc=0;
        for(int i=0;i<2*size+1;i++){
            int r,c;
            r=Integer.parseInt(rows.get(rc).toString());
            c=Integer.parseInt(cols.get(cc).toString());
            a[i][0]=r;
            a[i][1]=c;
            if(i%2==0)
                rc++;
            else
                cc++;
            System.out.println(r+" "+c);
            
        }
        for(int i=0;i<2*size+1;i++){
            Line tempAdd=new Line();
            int r=a[i][0];
            int c=a[i][1];
            
            
            
            
                pointIG[r][c].setRadius(10);
                pointIG[r][c].setFill(Color.ORANGE);
                
            if(i==0){
                temp=pointIG[r][c];
            }
            else{
                tempAdd.setStartX(pointIG[r][c].getCenterX());
                tempAdd.setStartY(pointIG[r][c].getCenterY());
                tempAdd.setEndX(temp.getCenterX());
                tempAdd.setEndY(temp.getCenterY());
                tempAdd.setStrokeWidth(2);
                temp=pointIG[r][c];
                
                
            }
            
            gridPane.getChildren().add(tempAdd);
            pointIG[r][c].toFront();    
        }
        
        
        

        int colIndex[]=new int[size];
        int curRow[]=new int[size];
        for(int i=0;i<size;i++){
            curRow[i]=Integer.parseInt(rows.get(i).toString());

            colIndex[Integer.parseInt(cols.get(i).toString())]=i;
        }
        int iterations=0;
        while(true){
            boolean noChange=true;
            iterations++;
            for(int i=0;i<size-1;){
                int p,q;
                p=curRow[i];
                q=curRow[i+1];
                if(colIndex[p]>colIndex[q]){
                    curRow[i]=q;
                    curRow[i+1]=p;
                    i+=2;
                    noChange=false;
                }
                else{
                    i+=1;
                }
            
            }
            if(noChange)
                break;
        }
        
        Label rowRep[]=new Label[size];
        Label colRep[]=new Label[size];
        x=32;
        y=48;
        double yIncr=745/iterations;
        double xIncr=313/(size-1);
        for(int i=0;i<size;i++){
            rowRep[i]=new Label(Integer.toString(Integer.parseInt(rows.get(i).toString())+1));
            tangPane.getChildren().add(rowRep[i]);
            rowRep[i].setLayoutX(x);
            rowRep[i].setLayoutY(y);
            x+=xIncr;
        }
        x=32;
        y=826;
        for(int i=0;i<size;i++){
            colRep[i]=new Label(Integer.toString(Integer.parseInt(cols.get(i).toString())+1));
            tangPane.getChildren().add(colRep[i]);

            colRep[i].setLayoutX(x);
            colRep[i].setLayoutY(y);
            x+=xIncr;
        }
        Polyline tangles[]=new Polyline[size];
        y=70;
        x=35;
        double curRowX[]=new double[size];
        for(int i=0;i<size;i++){
            curRow[i]=Integer.parseInt(rows.get(i).toString());
            curRowX[i]=x;
            x+=xIncr;
            tangles[i]=new Polyline();
            tangPane.getChildren().add(tangles[i]);
        }
        Color bob[]=new Color[size];
        for(int i=0;i<size;i++){
            
            bob[i]=Color.color(Math.random(), Math.random(), Math.random());
        }
        while(true){
            boolean noChange=true;
            for(int i=0;i<size-1;){
                int p,q;
                p=curRow[i];
                q=curRow[i+1];
                double px,qx;
                px=curRowX[i];
                qx=curRowX[i+1];
                
                if(colIndex[p]>colIndex[q]){
                    curRow[i]=q;
                    curRow[i+1]=p;
  
                    noChange=false;

                    Line te=new Line();
                    te.setStrokeWidth(3);
                    te.setStartX(px);
                    te.setStartY(y);
                    te.setEndX(qx);
                    te.setEndY(y+yIncr);
                    tangPane.getChildren().addAll(te);
                    te.setStroke(bob[i]);
                    
                    
                    te=new Line();
                    te.setStrokeWidth(3);
                    te.setStartX(qx);
                    te.setStartY(y);
                    te.setEndX(px);
                    te.setEndY(y+yIncr);
                    tangPane.getChildren().addAll(te);
                    te.setStroke(bob[i+1]);
                    
                    Color tempC=bob[i];
                    bob[i]=bob[i+1];
                    bob[i+1]=tempC;
                    i+=2;
                }
                else{
 
                    Line te=new Line();
                    te.setStrokeWidth(3);
                    te.setStartX(px);
                    te.setStartY(y);
                    te.setEndX(px);
                    te.setEndY(y+yIncr);
                    tangPane.getChildren().addAll(te);
                    te.setStroke(bob[i]);
                    
                    i+=1;
                    
                }
                if(i==size-1){
                    Line te=new Line();
                    te.setStrokeWidth(3);
                    te.setStartX(curRowX[i]);
                    te.setStartY(y);
                    te.setEndX(curRowX[i]);
                    te.setEndY(y+yIncr);
                    te.setStroke(bob[i]);
                    
                    tangPane.getChildren().addAll(te);break;
                }
            
            }
            System.out.println(Arrays.toString(curRowX));
            System.out.println(Arrays.toString(curRow));
            
            y+=yIncr;
            if(noChange)
                break;
        }
        
        int x1=0,y1=0;
        double xC[]=new double[size];
        Line templines[]=new Line[size];
        int rowIndex[]=new int[size];
        for(int i=0;i<size;i++){
            curRow[i]=Integer.parseInt(rows.get(i).toString());

            rowIndex[Integer.parseInt(rows.get(i).toString())]=i;
        }
        int curCol[]=new int[size];
        for(int i=0;i<size;i++){
            curCol[i]=Integer.parseInt(cols.get(i).toString());

            colIndex[Integer.parseInt(cols.get(i).toString())]=i;
        }
        xIncr=200;
        for(int i=0;i<size;i++){
            templines[i]=new Line();
            templines[i].setStartX(xIncr*(i+1));
            templines[i].setStartY(100);
            templines[i].setEndX(xIncr*colIndex[i]);
            templines[i].setEndY(1000);
        }
        HashSet<Integer> h[]=new HashSet[size];
        for(int i=0;i<size;i++){
            h[i]=new HashSet();
            for(int j=0;j<size;j++){
                if(i!=j && findIntersection(templines[i],templines[j])){
                    h[i].add(j);
                    System.out.println(i+" asdf  "+j);
                }
            }
        }
        setGraph(222, 308, 150, size);
        for(int i=0;i<size;i++){
            
            for(int j=i+1;j<size;j++){
                if(i!=j && h[i].contains(j)){
                    Line l=new Line();
                    permPane.getChildren().add(l);
                    l.setStartX(coordinates[i][0]-4);
                    l.setStartY(coordinates[i][1]-4);
                    l.setEndX(coordinates[j][0]-4);
                    l.setEndY(coordinates[j][1]-4);
                    l.setStrokeWidth(3);
                    l.setStroke(Color.RED);
                    
                }
            }
            
        }
        
        for(int i=0;i<size;i++){
            templines[i]=new Line();
            templines[i].setStartX(xIncr*i);
            templines[i].setStartY(100);
            templines[i].setEndX(xIncr*rowIndex[i]);
            templines[i].setEndY(1000);
        }
        h=new HashSet[size];
        for(int i=0;i<size;i++){
            h[i]=new HashSet();
            for(int j=i+1;j<size;j++){
                if(i!=j && findIntersection(templines[i],templines[j])){
                    h[i].add(j);
                    System.out.println(i+" asdf  "+j);
                }
            }
        }
        for(int i=0;i<size;i++){
            
            for(int j=0;j<size;j++){
                if(i!=j && h[i].contains(j)){
                    Line l=new Line();
                    permPane.getChildren().add(l);
                    l.setStartX(coordinates[i][0]+4);
                    l.setStartY(coordinates[i][1]+4);
                    l.setEndX(coordinates[j][0]+4);
                    l.setEndY(coordinates[j][1]-2);
                    l.setStrokeWidth(3);
                    l.setStroke(Color.GREEN);
                    
                }
            }
            
        }
        for(int i=0;i<size;i++){
            Circle c=new Circle(10);
            c.setCenterX(coordinates[i][0]);
            c.setCenterY(coordinates[i][1]);
            c.setFill(Color.GREY);
            permPane.getChildren().addAll(c);
            Label l=new Label(Integer.toString(i+1));
            l.setLayoutX(coordinates[i][0]-4);
            l.setLayoutY(coordinates[i][1]-6);
            l.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
            permPane.getChildren().addAll(l);
            
        }
        String rowL="",colL="";
        for(int i=0;i<size;i++){
            rowL=rowL+Integer.toString(curRow[i]+1)+"    ";
            colL=colL+Integer.toString(curCol[i]+1)+"    ";
            
        }
        rowPerm.setText(rowL);
        colPerm.setText(colL);

        double d=0;
        int tens=1;
        int nums[]=new int[size];
        for(int j=size-1;j>=0;j--){
            nums[curRow[j]]=j+1;
            int count=0;
            for(int i=curRow[j];i>=0;i--){
                if(nums[i]!=0){
                    count++;
                }
            }
            d=d+tens*count;
            tens*=10;
            System.out.println(Arrays.toString(nums));
        }
        
        tens=1;
        rowFact.setText(Integer.toString((int)d));
        nums=new int[size];
        d=0;
        for(int j=size-1;j>=0;j--){
            nums[curCol[j]]=j+1;
            int count=0;
            for(int i=curCol[j];i>=0;i--){
                if(nums[i]!=0){
                    count++;
                }
            }
            d=d+tens*count;
            tens*=10;
            System.out.println("-  "+Arrays.toString(nums));
        }
        colFact.setText(Integer.toString((int)d));
        
    }
    
}
