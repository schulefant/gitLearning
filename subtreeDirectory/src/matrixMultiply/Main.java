package matrixMultiply;

import java.io.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int size = 0;
		System.out.println("Bitte geben Sie die Größe der Matrix ein: ");
		size = readInt();
		Matrix matrix1 = new Matrix(size);
		matrix1.populate(size);
		Matrix matrix2 = new Matrix(size);
		matrix2.populate(size);
		Matrix result  = new Matrix(size);

		Thread   [] thread 	= new Thread[size];
		
		for (int i = 0; i < size; i++){
			Runnable mat = new MatrixMultiply(matrix1, matrix2, result, i);
			thread[i] 	 = new Thread(mat);
			thread[i].start();
		}
		
		try {
			for (int i = 0; i < size; i++){
				thread[i].join();
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
		
		matrix1.print();
		matrix2.print();
		
		System.out.println("Ergebnis: ");
		result.print();

	}
	
	public static int readInt(){
		int input = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		try {
			input = Integer.parseInt(br.readLine());
        } catch (Exception e){}
		
		return input;
	}

}
