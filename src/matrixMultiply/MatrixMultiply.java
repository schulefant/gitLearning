package matrixMultiply;

public class MatrixMultiply implements Runnable {

	Matrix faktor1;
	Matrix faktor2;
	Matrix produkt;
	int line;
	int tempValue = 0;
	int size;
	
	public MatrixMultiply(Matrix fak1, Matrix fak2, Matrix result, int line) {
		this.faktor1 = fak1;
		this.faktor2 = fak2;
		this.produkt = result;
		this.line 	 = line;
		size = faktor1.getSize();
	}
	
	@Override
	public void run() {
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++){
				tempValue += faktor1.getValue(line, j) * faktor2.getValue(j, i); 
			}
			produkt.setValue(line, i, tempValue);
			tempValue = 0;
		}
	}

}
