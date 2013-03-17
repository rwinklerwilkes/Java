class E58
{
	public static boolean[] sieve;
	public static void main(String args[])
	{
		fillSieve();
		int[] array = getDiagonals();
		System.out.println(calcRatio(array));
	}
	public static int[] getDiagonals()
	{
		int[] diagonals = new int[100000000];
		diagonals[0]=1;
		int i = 1;
		int curValue = 1;
		int curAdd=2;
		while(i<diagonals.length-5)
		{
			int counter = 0;
			while(counter<4)
			{
				curValue+=curAdd;
				diagonals[i]=curValue;
				counter++;
				i++;
			}
			curAdd+=2;
		}
		return diagonals;
	}
	public static void fillSieve()
	{
		sieve = new boolean[100000000];
		for(int i = 0; i<sieve.length; i++)
		{
			sieve[i]=true;
		}
		sieve[0]=false;
		sieve[1]=false;
		for(int i = 2; i<100000000; i++)
		{
			if(sieve[i]==true)
			{
				for(int j = 0; ; j++)
				{
					long val = i*i+i*j;
					if(val>=100000000||val<0)
					{
						break;
					}
					sieve[(i*i)+(i*j)]=false;
				}
			}
		}
	}
	public static int calcRatio(int[] array)
	{
		int sideLength = 7;
		double ratio = 1;
		while(ratio>.1)
		{
			int largest = sideLength*sideLength;
			System.out.println(largest);
			int prime = 0;
			int notPrime = 0;
			for(int i = 0; array[i]<=largest; i++)
			{
				if(sieve[array[i]])
				{
					prime++;
				}
				else
				{
					notPrime++;
				}
			}
			ratio = (double)prime/(double)(prime+notPrime);
			sideLength+=2;
		}
		return sideLength;
	}
}