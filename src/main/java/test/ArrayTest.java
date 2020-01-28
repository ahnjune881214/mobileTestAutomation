package test;

public class ArrayTest {
	public static void main(String args[]) throws Exception {
		array();
		test();
	}

	public static void array() throws Exception {
		int[][] table = { { 1, 2 }, { 2, 2 }, { 3, 2 }, { 1, 2 } };

		System.out.println("table.length : " + table.length);
		System.out.println("table[0].length : " + table[0].length);
	}

	public static void test() throws Exception {
		int i, j, k;
		int[][] a = new int[3][5];

		k = 1;
		for (j = 0; j < 5; j++) {
			for (i = 0; i < 3; i++) {
				a[i][j] = k;
//				System.out.println("abc : " + a[i][j]);
				k++;
			}
		}

		for (i = 0; i < 3; i++) {
			for (j = 0; j < 5; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}

}
