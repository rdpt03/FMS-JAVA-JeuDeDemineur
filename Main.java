import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//generate the game
		Map<String, Map<Integer, String>> gameArea = generateGameMap();
		
		//show the map
		//TODO 
		
		//print first line
		System.out.println("+---+---+---+---+---+---+---+---+---+---+---+---+---+");
		//print numbers 
		for(int i=0; i<=gameArea.get("A").size();i++) {
			//0 -> "|   |"
			if(i==0) {
				System.out.print("|   |");
			}
			//1 to 9 -> "| n |"
			else if(1 <= i && i <= 9){
				System.out.print(" "+i+" |");
			}//10 or more -> "| nn|"
			else {
				System.out.print(" "+i+"|");
			}
		}
		
		System.out.println();
		
		//line loop
		for(Map.Entry<String, Map<Integer,String>> line : gameArea.entrySet()) {
			System.out.println("+---+---+---+---+---+---+---+---+---+---+---+---+---+");
			//print the letters
			System.out.print("| "+line.getKey()+" |");
			//print TODO
			for(Map.Entry<Integer, String> caseGame : line.getValue().entrySet()) {
				//to replace with just spaces
				System.out.print(" "+caseGame.getValue()+" |");
			}
			System.out.println();
		}
		System.out.println("+---+---+---+---+---+---+---+---+---+---+---+---+---+");
		
	}
	
	static Map<String, Map<Integer, String>> generateGameMap(){
		//game area
		Map<String, Map<Integer, String>> gameArea = new HashMap<>();
		//create lines
		for(int i=1; i<=6; i++) {
			//generate letter
			String letterLine = String.valueOf((char) ('A'+i-1));

			//create the line
			Map.Entry<String, Map<Integer, String>> line = new AbstractMap.SimpleEntry<String, Map<Integer,String>>(letterLine, new HashMap<>());
			
			//create the columns
			for(int j = 1; j<=12; j++) {
				line.getValue().put(j, " ");
			}
			//insert into game area
			gameArea.put(line.getKey(), line.getValue());
			
		}
		//add bombs
		for(int i=1;i<=9;i++) {
			insertBomb(gameArea);
		}
		return gameArea;
	}
	
	static void insertBomb(Map<String, Map<Integer, String>> gameArea){
		//generate where it goes
		String xToInsert = String.valueOf((char) ('A' + (int)(Math.random() * 6)));
		int yToInsert = 1 + (int)(Math.random() * 12);
		
		//check if already bomb
		if(gameArea.get(xToInsert).get(yToInsert).equals("#")) {
			System.out.println("HALT!");
			insertBomb(gameArea);
		}else {
			gameArea.get(xToInsert).put(yToInsert,"#");
		}
	}
}
