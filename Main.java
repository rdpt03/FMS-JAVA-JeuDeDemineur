import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//test area

		Map<String, Map<Integer, String>> gameArea = generateGameMap();
		
		for(Map.Entry<String, Map<Integer,String>> line : gameArea.entrySet()) {
			System.out.println("--------------------------------");
			System.out.print("|"+line.getKey()+"|");
			for(Map.Entry<Integer, String> caseGame : line.getValue().entrySet()) {
				System.out.print(caseGame.getValue()+"|");
			}
			System.out.println();
		}
		
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
