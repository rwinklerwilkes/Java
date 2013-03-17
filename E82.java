class E82
{
}
class Matrix
{
	private int size;
	private int[][] values;
	private int[][] fastest;
	public Matrix(int[][] matrix)
	{
		values = matrix;
		size = matrix.length;
		fastest = new int[size][size];
		for(int i = 0; i<fastest.length; i++)
		{
			fastest[i][0] = values[i][0];
		}
		for(int i = 1; i<fastest.length; i++)
		{
			for(int j = 0; j<fastest.length; j++)
			{
			}
		}
	}
}