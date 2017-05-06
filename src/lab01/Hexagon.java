package lab01;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Polygon;

public class Hexagon {
	
	
	private String tileNumber;
	private String originalStringInput;
	private String tile1, tile2, tile3, tile4, tile5, tile6;
	private int rotationCounter = 0;
	private boolean[] triedAtPos = new boolean[7]; //a boolean array that keeps track of whether a hexagon has been tried at a hexagonPos before
													//hexagonPos corresponds to index of this array

	
	
	public Hexagon() {
	}

	
	//**Note - when FileReader reads file, 
	//accepts string, assigns to variables depending on [index] in the String parameter
	public Hexagon(String colors){
		originalStringInput = colors;
		tileNumber = colors.substring(0, 1); //substring(beg index, ending index) ending index is up to, not including
		tile1 = colors.substring(1, 2);
		tile2 = colors.substring(2, 3);
		tile3 = colors.substring(3, 4);
		tile4 = colors.substring(4, 5);
		tile5 = colors.substring(5, 6);
		tile6 = colors.substring(6, 7);
	}
	
	
	
	public void setTriedPos(int index, boolean tried){
		this.triedAtPos[index] = tried;
	}
	
	public boolean getTriedPos(int index){
		return triedAtPos[index];
	}
	
	public void clearTriedPos(){
		
	}
	
	public void incrementRotation(){
		rotationCounter +=1;
	}
	
	public int getRotationCounter() {
		return rotationCounter;
	}
	
	public void setRotationCounter(int rotationCounter) {
		this.rotationCounter = rotationCounter;
	}	
	
	public String getTile1() {
		return tile1;
	}


	public void setTile1(String tile1) {
		this.tile1 = tile1;
	}


	public String getTile2() {
		return tile2;
	}


	public void setTile2(String tile2) {
		this.tile2 = tile2;
	}


	public String getTile3() {
		return tile3;
	}


	public void setTile3(String tile3) {
		this.tile3 = tile3;
	}


	public String getTile4() {
		return tile4;
	}


	public void setTile4(String tile4) {
		this.tile4 = tile4;
	}


	public String getTile5() {
		return tile5;
	}


	public void setTile5(String tile5) {
		this.tile5 = tile5;
	}


	public String getTile6() {
		return tile6;
	}


	public void setTile6(String tile6) {
		this.tile6 = tile6;
	}


	public void setTileNumber(String tileNumber){
		this.tileNumber = tileNumber;
	}
	
	public String getTileNumber(){
		return tileNumber;
	}
	
	public String getOriginalString(){
		return originalStringInput;
	}
	
	public String toString(){
		return "Tile Number: " + tileNumber + ", Colors: " + getTile1() + getTile2() + getTile3() + getTile4() + getTile5() + getTile6() + "\n";
	}
}
