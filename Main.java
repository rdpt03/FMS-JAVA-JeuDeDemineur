import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	//symbols
	static String EMPTY_CASE = " ";
	static String BOMB_SYMBOL = "O";
	static String EXPLODED_BOMB = "#";
	static String SAFE_HIT = "X";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//generate the game
		Map<String, Map<Integer, String>> gameArea = generateGameMap();
		Scanner sc = new Scanner(System.in);
		boolean playing = true;
		
		while(playing) {
			//print map
			printGameMap(gameArea,false);
			
			//ask to the user there to shot
			System.out.println("Please insert where to step with the format LetterNumber (Example : A1)");
			
			String caseToStep = sc.nextLine().toUpperCase();
			if(caseToStep.matches("[A-F]([1-9]|1[0-2])")) {
				//get chars
				String CX = String.valueOf(caseToStep.charAt(0));
				int CY = (int) caseToStep.charAt(1) - '0';
				
				//check if there's a bomb
				if(gameArea.get(CX).get(CY).equals(BOMB_SYMBOL)) {
					//explode and end the game
					gameArea.get(CX).put(CY, EXPLODED_BOMB);
					printGameMap(gameArea,true);
					System.out.println("You stepped a bomb and lost");
					playing = false;
				}else {
					gameArea.get(CX).put(CY, SAFE_HIT);
				}
			}
			else {
				System.out.println("Invalid Entry!");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	static void printGameMap(Map<String, Map<Integer, String>> gameArea, boolean showBombs) {
		//show the map
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
		for(int i = 1; i <= gameArea.size(); i++) {
			//get the line
			String letter = String.valueOf((char)('A'+i-1));
			Map.Entry<String, Map<Integer,String>> line = new AbstractMap.SimpleEntry<String, Map<Integer,String>>(letter,gameArea.get(i));
			
			//print area
			System.out.println("+---+---+---+---+---+---+---+---+---+---+---+---+---+");
			//print the letters
			System.out.print("| "+line.getKey()+" |");
			//print TODO
			for(int j = 1; j <= line.getValue().size(); j++) {
				//get the case
				Map.Entry<Integer, String> caseGame = new AbstractMap.SimpleEntry<Integer,String>(j,line.getValue().get(j));
				
				//get the value
				String valueToCase = caseGame.getValue();
				
				//check if character is a bomb
				if(valueToCase.equals(BOMB_SYMBOL) && !showBombs) {
					System.out.print(" "+EMPTY_CASE+" |");
				}
				else {
					System.out.print(" "+caseGame.getValue()+" |");
				}
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
				line.getValue().put(j, EMPTY_CASE);
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
		if(gameArea.get(xToInsert).get(yToInsert).equals(BOMB_SYMBOL)) {
			insertBomb(gameArea);
		}else {
			gameArea.get(xToInsert).put(yToInsert,BOMB_SYMBOL);
		}
	}
}
