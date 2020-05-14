package matrixMultiply;

import java.util.Random;

public class Matrix {
	
	private int size;
	private int values [][];
	private char seperator = ',';

	/* Constructor */
	
	public Matrix(int size) {
		this.size = size;
		values = new int [size][size];
	}
	
	/* Setter */
	public void setSize(int size){
		this.size = size;
	}
	
	public void setLine(int line, int [] values){
		this.values[line] = values;
	}
	
	public void setValue(int posX, int posY, int value){
		values[posX][posY] = value;
	}
	
	/* Getter */
	public int getSize(){
		return size;
	}
	
	public int[] getLine (int line){
		return values[line];
	}
	
	public int getValue(int posX, int posY){
		return values [posX][posY];
	}
	
	/* Print */
	/**
	 *	Gibt eine Matrix auf der Konsole aus
	 */
	public void print(){
		for (int i = 0; i < size; i++){
			System.out.print('[');
			for (int j = 0; j < size; j++){
				System.out.print(values[i][j]);
				if (j < size-1){
					System.out.print(seperator + " ");
				}
			}
			System.out.println(']');
		}
	}
	
	/* Other functions */
	/**
	 * Füllt eine Matrix mit zufälligen Werten zwischen
	 * 0 und $param1
	 * @param1 limit Größte mögliche Zahl der Matrix
	 */
	public void populate(int limit){

		Random rand = new Random();
		
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++){
				values [i][j] = rand.nextInt(limit);
			}
		}
	}
}
