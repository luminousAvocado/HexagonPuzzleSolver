package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import lab01.FileReader;
import lab01.Hexagon;
import lab01.SolveByRecursion;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;


public class DisplaySolution extends Application {
	
	
	private List<Hexagon> listOfHex = new ArrayList<>();
	private List<Hexagon> solvedList = new ArrayList<>();
	private List<Hexagon> origList = new ArrayList<>();
	
	
	
	public DisplaySolution(){
	}
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			root.getStyleClass().add("border");
			root.setStyle("-fx-background-color: #3e3636; -fx-padding: 5;");
			
			
			HBox topRow = new HBox(15);
			topRow.setStyle("-fx-alignment: center; -fx-background-color: white; -fx-padding: 10px;");
			Button loadFileButton = new Button("Load File");
			topRow.getChildren().add(loadFileButton);
			
			
			
			/* CREATES HEXAGONS
			 * Got this from book, calls a method(below) that creates a hexagon, given the coords of center(x,y)
			*/
			List<Polygon> polygonList = new ArrayList<>(); 
			Pane pane = new Pane();
			pane.setStyle("-fx-alignment: center;");
			polygonList.add(createHexagon(400, 400)); //center
			polygonList.add(createHexagon(400, 220)); //top
			polygonList.add(createHexagon(557, 310)); //top right
			polygonList.add(createHexagon(557, 490)); //bottom right
			polygonList.add(createHexagon(400, 580)); //bottom
			polygonList.add(createHexagon(243, 490)); //bottom left
			polygonList.add(createHexagon(243, 310)); //top left		
			for(int polyCounter = 0; polyCounter < polygonList.size(); polyCounter++){ //adds each hexagon to pane
				pane.getChildren().add(polygonList.get(polyCounter));
			}
			
			
			
			//Event handler for loading File, if had more time, make create 6 triangles portion into separate method
			loadFileButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
				
				@Override
				public void handle(Event arg0){
					
					FileReader read = new FileReader();
					JFileChooser fileChooser = new JFileChooser();
					int retVal = fileChooser.showOpenDialog(null);
					
					
					if(retVal == JFileChooser.APPROVE_OPTION){
						File selectedFile = fileChooser.getSelectedFile();
						try {
							
							listOfHex = read.readFromFile(selectedFile); //readFromFile method returns an arrayList of hexagons, assigns to listOfHex
							SolveByRecursion attemptSolve = new SolveByRecursion(listOfHex);				
							JOptionPane.showMessageDialog(null,  "File has been loaded!");
							solvedList = attemptSolve.solveUtility(listOfHex); //sends to solver
							System.out.println(solvedList);
							
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
					}
					
					
					
					//creates and displays 6 triangles per hexagon center coords
					if(solvedList.size() == 7){
						pane.getChildren().addAll(createTriangle(400, 400, polygonList.get(0).getPoints(), solvedList.get(0)));
						Text text0 = new Text(400, 400, solvedList.get(0).getTileNumber());
						text0.setStyle("-fx-fill: white; -fx-font-size: 60; -fx-stroke: black; -fx-stroke-width: 1.5px;");
						pane.getChildren().add(text0);
						
						pane.getChildren().addAll(createTriangle(400, 220, polygonList.get(1).getPoints(), solvedList.get(1)));
						Text text1 = new Text(400, 220, solvedList.get(1).getTileNumber());
						text1.setStyle("-fx-fill: white; -fx-font-size: 60; -fx-stroke: black; -fx-stroke-width: 1.5px;");
						pane.getChildren().add(text1);
						
						pane.getChildren().addAll(createTriangle(557, 310, polygonList.get(2).getPoints(), solvedList.get(2)));
						Text text2 = new Text(557, 310, solvedList.get(2).getTileNumber());
						text2.setStyle("-fx-fill: white; -fx-font-size: 60; -fx-stroke: black; -fx-stroke-width: 1.5px;");
						pane.getChildren().add(text2);
						
						pane.getChildren().addAll(createTriangle(557, 490, polygonList.get(3).getPoints(), solvedList.get(3)));
						Text text3 = new Text(557, 490, solvedList.get(3).getTileNumber());
						text3.setStyle("-fx-fill: white; -fx-font-size: 60; -fx-stroke: black; -fx-stroke-width: 1.5px;");
						pane.getChildren().add(text3);
						
						pane.getChildren().addAll(createTriangle(400, 580, polygonList.get(4).getPoints(), solvedList.get(4)));
						Text text4 = new Text(400, 580, solvedList.get(4).getTileNumber());
						text4.setStyle("-fx-fill: white; -fx-font-size: 60; -fx-stroke: black; -fx-stroke-width: 1.5px;");
						pane.getChildren().add(text4);
						
						pane.getChildren().addAll(createTriangle(243, 490, polygonList.get(5).getPoints(), solvedList.get(5)));
						Text text5 = new Text(243, 490, solvedList.get(5).getTileNumber());
						text5.setStyle("-fx-fill: white; -fx-font-size: 60; -fx-stroke: black; -fx-stroke-width: 1.5px;");
						pane.getChildren().add(text5);
						
						pane.getChildren().addAll(createTriangle(243, 310, polygonList.get(6).getPoints(), solvedList.get(6)));
						Text text6 = new Text(243, 310, solvedList.get(6).getTileNumber());
						text6.setStyle("-fx-fill: white; -fx-font-size: 60; -fx-stroke: black; -fx-stroke-width: 1.5px;");
						pane.getChildren().add(text6);
					}
				}
			});	
			
			
			
			root.setTop(topRow);
			root.setCenter(pane);
			Scene scene = new Scene(root, 800, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("CS 2013 Lab 01+02 James Kang");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//from book
	public Polygon createHexagon(int centerX, int centerY){		
		Polygon newHexagon = new Polygon();	
		ObservableList<Double> pointList = newHexagon.getPoints();
		int radius = 100;	
		
		for (int pointCounter = 0; pointCounter < 6; pointCounter++){
			pointList.add(centerX + radius * Math.cos(2 * pointCounter * Math.PI / 6));
			pointList.add(centerY - radius * Math.sin(2 * pointCounter * Math.PI / 6));
		}
		
		pointList.add(pointList.get(0)); //these two lines complete the loop(hexagon), making the final point = to first point
		pointList.add(pointList.get(1));
		
		newHexagon.setStyle("-fx-stroke: white; -fx-stroke-width: 1px;");	
		
		return newHexagon;
	}
	
	
	
	/* centerX and centerY is the center of hexagon, also the same shared middle point of all triangles
	 * ObservableList is a list of doubles of all the (x,y) coords of the points of the hexagon
	 * testHex is the specific hexagon we're creating triangles for, this is so we can get the colors right
	*/
	public List<Polygon> createTriangle(double centerX, double centerY, ObservableList<Double> obsList, Hexagon testHex){
	
		List<Polygon> triangleList = new ArrayList<>();
		
		
		//each loop creates a new triangle					//goes from 0 - 12 since hexagon has 6 points, 12 coords total
		for(int counter = 0; counter < 12; counter +=2 ){ //counter indicates the position of the triangle in the hexagon
			Polygon newTriangle = new Polygon();
			newTriangle.setStyle("-fx-stroke: black; -fx-stroke-width: 2px;");
			ObservableList<Double> pointList = newTriangle.getPoints(); //a new list of the (x,y) for each point on triangle, 3 total
			pointList.add(centerX); //centerX and centerY is same for all triangles, all meet in middle, thus not dependent on counter
			pointList.add(centerY);
			pointList.add(obsList.get(counter)); 
			pointList.add(obsList.get(counter + 1));
			pointList.add(obsList.get(counter + 2));
			pointList.add(obsList.get(counter + 3));	
			
			switch (counter) {

			case 0: //case corresponds to triangle position, took trial and error, sets color of triangle
				if (testHex.getTile2().equals("R")) {
					newTriangle.setFill(Color.RED);
				} else if (testHex.getTile2().equals("B")) {
					newTriangle.setFill(Color.BLUE);
				} else if (testHex.getTile2().equals("Y")) {
					newTriangle.setFill(Color.YELLOW);
				} else if (testHex.getTile2().equals("G")) {
					newTriangle.setFill(Color.GREEN);
				} else if (testHex.getTile2().equals("O")) {
					newTriangle.setFill(Color.ORANGE);
				} else if (testHex.getTile2().equals("P")) {
					newTriangle.setFill(Color.PURPLE);
				}
				break;
			case 2:
				if (testHex.getTile1().equals("R")) {
					newTriangle.setFill(Color.RED);
				} else if (testHex.getTile1().equals("B")) {
					newTriangle.setFill(Color.BLUE);
				} else if (testHex.getTile1().equals("Y")) {
					newTriangle.setFill(Color.YELLOW);
				} else if (testHex.getTile1().equals("G")) {
					newTriangle.setFill(Color.GREEN);
				} else if (testHex.getTile1().equals("O")) {
					newTriangle.setFill(Color.ORANGE);
				} else if (testHex.getTile1().equals("P")) {
					newTriangle.setFill(Color.PURPLE);
				}
				break;
			case 4:
				if (testHex.getTile6().equals("R")) {
					newTriangle.setFill(Color.RED);
				} else if (testHex.getTile6().equals("B")) {
					newTriangle.setFill(Color.BLUE);
				} else if (testHex.getTile6().equals("Y")) {
					newTriangle.setFill(Color.YELLOW);
				} else if (testHex.getTile6().equals("G")) {
					newTriangle.setFill(Color.GREEN);
				} else if (testHex.getTile6().equals("O")) {
					newTriangle.setFill(Color.ORANGE);
				} else if (testHex.getTile6().equals("P")) {
					newTriangle.setFill(Color.PURPLE);
				}
				break;
			case 6:
				if (testHex.getTile5().equals("R")) {
					newTriangle.setFill(Color.RED);
				} else if (testHex.getTile5().equals("B")) {
					newTriangle.setFill(Color.BLUE);
				} else if (testHex.getTile5().equals("Y")) {
					newTriangle.setFill(Color.YELLOW);
				} else if (testHex.getTile5().equals("G")) {
					newTriangle.setFill(Color.GREEN);
				} else if (testHex.getTile5().equals("O")) {
					newTriangle.setFill(Color.ORANGE);
				} else if (testHex.getTile5().equals("P")) {
					newTriangle.setFill(Color.PURPLE);
				}
				break;
			case 8:
				if (testHex.getTile4().equals("R")) {
					newTriangle.setFill(Color.RED);
				} else if (testHex.getTile4().equals("B")) {
					newTriangle.setFill(Color.BLUE);
				} else if (testHex.getTile4().equals("Y")) {
					newTriangle.setFill(Color.YELLOW);
				} else if (testHex.getTile4().equals("G")) {
					newTriangle.setFill(Color.GREEN);
				} else if (testHex.getTile4().equals("O")) {
					newTriangle.setFill(Color.ORANGE);
				} else if (testHex.getTile4().equals("P")) {
					newTriangle.setFill(Color.PURPLE);
				}
				break;
			case 10:
				if (testHex.getTile3().equals("R")) {
					newTriangle.setFill(Color.RED);
				} else if (testHex.getTile3().equals("B")) {
					newTriangle.setFill(Color.BLUE);
				} else if (testHex.getTile3().equals("Y")) {
					newTriangle.setFill(Color.YELLOW);
				} else if (testHex.getTile3().equals("G")) {
					newTriangle.setFill(Color.GREEN);
				} else if (testHex.getTile3().equals("O")) {
					newTriangle.setFill(Color.ORANGE);
				} else if (testHex.getTile3().equals("P")) {
					newTriangle.setFill(Color.PURPLE);
				}
				break;
			}		
			triangleList.add(newTriangle);
		}	
		return triangleList;
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}

