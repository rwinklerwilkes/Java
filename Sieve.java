class Sieve
{
	public static void main(String args[])
	{
		boolean[] sieve = new boolean[1000000];
		for(int i = 0; i<sieve.length; i++)
		{
			sieve[i]=true;
		}
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
		for(int i = 2; i<100; i++)
		{
			if(sieve[i]==true)
			{
				System.out.println(i);
			}
		}
	}
	public static boolean isPrime(int n)
	{
		if(n==1)
		{
			return false;
		}
		if(n%2==0)
		{
			if(n>3)
			{
				return false;
			}
		}
		for(int i = 3; i<=Math.sqrt(n); i+=2)
		{
			if(n%i==0)
			{
				return false;
			}
		}
		return true;
	}
}