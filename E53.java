class E53
{
	private static double[][] pascal;
	public static void main(String args[])
	{
		long start = System.currentTimeMillis();
		int limit = Integer.parseInt(args[0]);
		pascal = new double[limit+1][limit+1];
		fillPascal();
		int sum = calcSum();
		System.out.println(sum);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	private static void fillPascal()
	{
		pascal[0][0]=1;
		for(int n = 1; n<pascal[0].length; n++)
		{
			pascal[0][n]=0;
		}
		for(int m = 1; m<pascal.length; m++)
		{
			for(int n = 0; n<pascal[m].length; n++)
			{
				if(n==0)
				{
					pascal[m][n]=1;
				}
				else
				{
					pascal[m][n]=(pascal[m-1][n-1]+pascal[m-1][n]);
				}
			}
		}
	}
	private static int calcSum()
	{
		final int MAX_VAL = 1000000;
		int sum = 0;
		for(int m = 0; m<pascal.length; m++)
		{
			for(int n = 0; n<pascal.length; n++)
			{
				if(pascal[m][n]>=MAX_VAL)
				{
					sum++;
				}
			}
		}
		return sum;
	}
}