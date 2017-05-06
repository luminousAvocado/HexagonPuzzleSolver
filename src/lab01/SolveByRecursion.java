package lab01;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class SolveByRecursion {


	private int hexagonPos = 0; // the hexagon position, middle == 0, first(top) == 1...
	private List<Hexagon> origList = new ArrayList<>(); // keeps a list of the original list for middle element iterations, this list will change order
	protected List<Hexagon> finalList = new ArrayList<>(); // the final list that possible solutions get added to, or removed from if not right
	private int midHexCounter = 0;
	
	
	
	//makes a copy of the original list
	public SolveByRecursion(List<Hexagon> inputList){
		for(int index = 0; index < inputList.size(); index++){
			origList.add(inputList.get(index));
		}
	}


	
	
	//simple recursive method, if the tile matches, continue recursively, adding the next hexagon
	//if no match, returns false, goes back to solveUtility method
	public boolean solve(List<Hexagon> baseList) {

		FindMatch tryMatch = new FindMatch();
		
		
		if (tryMatch.attemptMatch(hexagonPos, baseList, finalList) == true) { //after last hexagon has been placed, baseList is empty, null, return false
			finalList.add(baseList.get(0));
			baseList.remove(0);
			hexagonPos++;
			solve(baseList);
		}
		
		return false;
	}
	
	
	
	
	public List<Hexagon> solveUtility(List<Hexagon> baseList){
			
		
		Rotate newRotation = new Rotate();
		
		
		//crappy "no solution" tester
		if(midHexCounter >= 50){
			JOptionPane.showMessageDialog(null, "No Solution!");
			return origList;
		}
		
		
		//lays middle tile down
		if(hexagonPos == 0){
			baseList.get(0).setTriedPos(hexagonPos, true);
			finalList.add(baseList.get(0));
			baseList.remove(0);
			hexagonPos++;
		}
		
		
		if(solve(baseList) == false){
			
			
			
			if(baseList.size() == 0){	//if base case is reached, returns finalList
				JOptionPane.showMessageDialog(null, "Solution found!");
				return finalList;
			}
			
			
			
			if(baseList.get(0).getRotationCounter() < 5){ //if rotation counter for a hexagon is less than its max possibility of 5, rotate
				newRotation.rotateTile(baseList.get(0));  //method also takes cares of backtrace issue, backtrace and then try other sides of already
				baseList.get(0).incrementRotation();      //tested hexagon, but not the sides we already tried
			}
			
			else if(baseList.get(0).getTriedPos(hexagonPos) == false){ //max rotations have beeen reached, 
				baseList.get(0).setTriedPos(hexagonPos, true); //sets that this hexagon has been tried at this hexagonPos already
				baseList.get(0).setRotationCounter(0); //resets rotation counter = 0
				baseList.add(baseList.get(0)); //sends baseList(0) to end of baseList
				baseList.remove(0); //deletes baseList(0)
			}
			
			//**BACKTRACE**
			else{ //we've iterated through the list of hexagons for that hexagonPos, and we're back at the first hexagon, also marked as "true" for this hexPos
				for(int counter = 0; counter < baseList.size(); counter++){ //resets the "tried" boolean for all hexagons in baseList,
					baseList.get(counter).setTriedPos(hexagonPos, false);   //since they may be tested at the same hex pos, but against another hex
				}
				hexagonPos--;
				
				//crappy "no solution" tester, increment every time we swap out middle hexagon, theoretically max should be 6
				if(hexagonPos == 0){
					midHexCounter++;
				}
				
				
				finalList.get(finalList.size() - 1).setTriedPos(hexagonPos, true); //hexagon for backtracing, gets sets to true for its hexagonPos, note --
				baseList.add(finalList.get(finalList.size() - 1));
				finalList.remove(finalList.size() - 1);
			}	
		}
		

		
		return solveUtility(baseList);
	}
}
