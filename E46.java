import java.util.ArrayList;

class E46
{
	public static boolean[] sieve;
	public static void main(String args[])
	{
		long start = System.currentTimeMillis();
		fillSieve();
		for(int i = 33; i<=sieve.length; i+=2)
		{
			if(!sieve[i])
			{
				if(!testPrime(i))
				{
					System.out.println(i);
					break;
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	public static void fillSieve()
	{
		sieve = new boolean[10000];
		for(int i = 0; i<sieve.length; i++)
		{
			sieve[i]=true;
		}
		for(int i = 2; i<10000; i++)
		{
			if(sieve[i]==true)
			{
				for(int j = 0; ; j++)
				{
					long val = i*i+i*j;
					if(val>=10000||val<0)
					{
						break;
					}
					sieve[(i*i)+(i*j)]=false;
				}
			}
		}
	}
	public static boolean testPrime(int n)
	{
		boolean pass = false;
		for(int i = 1; i<=300; i++)
		{
			int m = 2*((int)Math.pow(i, 2.0));
			int test = n-m;
			if(test>0&&sieve[test])
			{
				pass=true;
				break;
			}
		}
		return pass;
	}
}