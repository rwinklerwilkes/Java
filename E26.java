import java.math.BigInteger;

class LongCycle
{
	public static void main(String args[])
	{
		long start = System.currentTimeMillis();
		int largest = 0;
		for(int i = 1; i<1000; i+=2)
		{
			if(lambda(i)>largest)
			{
				largest = i;
			}
		}
		System.out.println(largest);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	public static int lambda(int n)
	{
		for(int i = 1; i<1000; i++)
		{
			BigInteger ten = BigInteger.valueOf(10);
			BigInteger power = ten.pow(i);
			BigInteger test = power.subtract(BigInteger.ONE);
			if(test.mod(BigInteger.valueOf(n)).compareTo(BigInteger.ZERO)==0)
			{
				return i;
			}
		}
		return -1;
	}
}