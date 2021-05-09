import java.util.*;
import java.io.*;

public class Game {

	private char[][] matrix;
	private int UserPos;

	public Game () {
		matrix = new char[50][50];
		UserPos = 25;

		for(int i = 0; i < matrix.length; i++){
			Arrays.fill(matrix[i], ' ');
		}

		fillRow(matrix[0]);

		matrix[matrix.length-1][UserPos] = '*';
	}

	public void run () throws Exception{
		while(true){
			Thread.sleep(500);
			cleanConsole();
			update();
			print();
		}
	}

	private void cleanConsole() throws Exception{
		Process process = Runtime.getRuntime().exec("printf '\033\143'");
		StringBuilder output = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader (process.getInputStream()));

		String line;
		while((line = reader.readLine()) != null){
			output.append(line + '\n');
		}

		int exitVal = process.waitFor();
		if(exitVal == 0){
			System.out.println(output);
		}
	}

	private void fillRow(char[] row){
		for(int i = 0; i < row.length; i ++){
			if(isShow()){
				row[i] = '-';
			} else {
				row[i] = ' '; 
			}
		}
	}

	private boolean isShow(){
		Random rn = new Random();
		return rn.nextInt(5) + 1 == 1; 
	}

	private void update(){
		for(int i = matrix.length-1; i >= 1; i--){
			copyRow(i);
			if(i == matrix.length-1){
				matrix[i][UserPos] = '*';
			}
		}
		fillRow(matrix[0]);
	}

	private void copyRow(int cur){
		for(int i = 0; i < matrix[cur].length; i++){
			matrix[cur][i] = matrix[cur-1][i];
		}	
	}

	private void print(){
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[0].length; j++){
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
	}
}
