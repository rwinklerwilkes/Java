class E50
{
	static boolean sieve[];
	static int primes[];
	static final int MAX_PRIME = 1000000;
	public static void main(String args[])
	{
		fillSieve();
		int largest =  0;
		int lNumPrimes = 0;
		for(int i = 0; primes[i]<MAX_PRIME/2; i++)
		{
			int sum = primes[i];
			int counter = i+1;
			int numPrimes = 1;
			while(sum<MAX_PRIME)
			{
				if(counter<primes.length)
				{
					sum+=primes[counter];
				}
				else
				{
					break;
				}
				numPrimes++;
				if(sum<MAX_PRIME&&sieve[sum]&&numPrimes>lNumPrimes)
				{
					largest = sum;
					lNumPrimes=numPrimes;
				}
				counter++;
			}
		}
		System.out.println(largest);
	}
	public static void fillSieve()
	{
		sieve = new boolean[1000000];
		for(int i = 0; i<sieve.length; i++)
		{
			sieve[i]=true;
		}
		sieve[0]=false;
		sieve[1]=false;
		for(int i = 2; i<1000000; i++)
		{
			if(sieve[i]==true)
			{
				for(int j = 0; ; j++)
				{
					long val = i*i+i*j;
					if(val>=1000000||val<0)
					{
						break;
					}
					sieve[(i*i)+(i*j)]=false;
				}
			}
		}
		primes = new int[78500];
		int counter = 1;
		primes[0] = 2;
		for(int i = 3; i<sieve.length; i+=2)
		{
			if(sieve[i])
			{
				primes[counter]=i;
				counter++;
			}
		}
	}
}