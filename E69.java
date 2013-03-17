import java.util.ArrayList;
import java.util.HashMap;

class E69
{
	public static boolean[] sieve;
	public static void main(String args[])
	{
		fillSieve();
		double largest = 3;
		int index = 10;
		for(int i = 10; i<10000000; i++)
		{
			if(!sieve[i])
			{
				double tot = totient(i,primeFac(i));
				double val = (double)(i/tot);
				if(val>largest)
				{
					largest = val;
					index = i;
				}
			}
		}
		System.out.println(index);
	}
	public static double totient(int primeNum, Integer[] prime)
	{
		double multiplicand = primeNum;
		for(int i = 0; i<prime.length; i++)
		{
			double denom = prime[i].intValue();
			multiplicand*=(1-(1/denom));
		}
		return multiplicand;
	}
	public static Integer[] primeFac(int n)
	{
		ArrayList<Integer> fac = new ArrayList<Integer>();
		while(!sieve[n])
		{
			if(n%2==0)
			{
				fac.add(2);
				while(n%2==0)
				{
					n/=2;
				}
			}
			for(int i = 3; i<=Math.sqrt(n); i+=2)
			{
				if(sieve[i]&&(n%i==0))
				{
					if(!fac.contains(i))
					{
						fac.add(i);
					}
					int m = n/i;
					n= m;
				}
			}
		}
		if(!fac.contains(n)&&n!=1)
		{
			fac.add(n);
		}
		fac.trimToSize();
		Integer[] intArray = new Integer[fac.size()];
		fac.toArray(intArray);
		return intArray;
	}
	public static void fillSieve()
	{
		sieve = new boolean[1000000];
		for(int i = 0; i<sieve.length; i++)
		{
			sieve[i]=true;
		}
		for(long i = 2; i<1000000; i++)
		{
			if(sieve[(int)i])
			{
				for(long j = 0; ; j++)
				{
					long val = i*i+i*j;
					if(val<0||val>=1000000)
					{
						break;
					}
					sieve[(int)val]=false;
				}
			}
		}
	}
}