package lab01;

public class Rotate {

	public Hexagon rotateTile(Hexagon tempHex){
		
		String tempColor1, tempColor2;
		
		tempColor1 = tempHex.getTile1();
		tempHex.setTile1(tempHex.getTile6());
		tempColor2 = tempHex.getTile2();
		tempHex.setTile2(tempColor1);;
		tempColor1 = tempHex.getTile3();
		tempHex.setTile3(tempColor2);
		tempColor2 = tempHex.getTile4();
		tempHex.setTile4(tempColor1);
		tempColor1 = tempHex.getTile5();
		tempHex.setTile5(tempColor2);
		tempHex.setTile6(tempColor1);
		
		return tempHex;
	}
}
