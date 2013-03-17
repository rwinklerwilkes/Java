import java.util.HashSet;

class FacChain
{
	public static int[] factorials = new int[]{1,1,2,6,24,120,720,5040,40320,362880};
	public static void main(String args[])
	{
		long start = System.currentTimeMillis();
		int sum = 0;
		for(int i = 2; i<1000000; i++)
		{
			if(chainLength(i)==60)
			{
				sum++;
			}
		}
		System.out.println(sum);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	public static int chainLength(int start)
	{
		HashSet<Integer> hs = new HashSet<Integer>();
		int sum = 0;
		int next = start;
		do
		{
			hs.add(next);
			int intermediate = next;
			next = 0;
			while(intermediate>0)
			{
				next+=factorials[intermediate%10];
				intermediate/=10;
			}
			sum++;
		}
		while(!hs.contains(next));
		return sum;
	}
}