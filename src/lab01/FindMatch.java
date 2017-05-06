package lab01;

import java.util.List;

public class FindMatch {

	private boolean match;
	//private int result;
	private int center = 0;
	private int firstHex = 0;
	private int secondHex = 1;
	
	
	/* takes hexagon position as parameter, the baseList, the finalList
	 * depending on the hexagon position, compares the appropriate tiles
	*/ 
	public boolean attemptMatch(int hexagonPos, List<Hexagon> testList, List<Hexagon> finalList){

			switch(hexagonPos){
				case 0: match = true;
						break;
						
				case 1: if(testList.get(firstHex).getTile4().equals(finalList.get(center).getTile1())){
							match = true;
						} else{
							match = false;
						}
						break;
						
				case 2: if((testList.get(firstHex).getTile5().equals(finalList.get(center).getTile2())) && (testList.get(firstHex).getTile6().equals(finalList.get(hexagonPos - 1).getTile3()))){
							match = true;
						} else{
							match = false;
						}
						break;
						
				case 3:	if((testList.get(firstHex).getTile6().equals(finalList.get(center).getTile3())) && (testList.get(firstHex).getTile1().equals(finalList.get(hexagonPos - 1).getTile4()))){
							match = true;
						} else{
							match = false;
						}
						break;
						
				case 4: if((testList.get(firstHex).getTile1().equals(finalList.get(center).getTile4())) && (testList.get(firstHex).getTile2().equals(finalList.get(hexagonPos - 1).getTile5()))){
							match = true;
						} else{
							match = false;
						}
						break;
						
				case 5: if((testList.get(firstHex).getTile2().equals(finalList.get(center).getTile5())) && (testList.get(firstHex).getTile3().equals(finalList.get(hexagonPos - 1).getTile6()))){
							match = true;
						} else{
							match = false;
						}
						break;
						
				case 6: if((testList.get(firstHex).getTile3().equals(finalList.get(center).getTile6())) && (testList.get(firstHex).getTile4().equals(finalList.get(hexagonPos - 1).getTile1())) && (testList.get(firstHex).getTile2().equals(finalList.get(secondHex).getTile5()))){
							match = true;
						} else{
							match = false;
						}
						break;
			}	
		return match;
	}
}
