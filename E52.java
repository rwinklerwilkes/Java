import java.util.Arrays;

class E52
{
	public static void main(String args[])
	{
		long start = System.currentTimeMillis();
		for(int i = 100000; i<1000000; i++)
		{
			if(compare(i))
			{
				System.out.println(i);
				break;
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	public static boolean compare(int n)
	{
		boolean works = true;
		for(int i = 6; i>=2; i--)
		{
			if(!containsSame(n, i*n))
			{
				works = false;
				return works;
			}
		}
		return works;
	}
	public static boolean containsSame(int m, int n)
	{
		String m1 = Integer.toString(m);
		String n1 = Integer.toString(n);
		if(m1.length()!=n1.length())
		{
			return false;
		}
		char[] arr1 = m1.toCharArray();
		char[] arr2 = n1.toCharArray();
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		if(Arrays.equals(arr1, arr2))
		{
			return true;
		}
		else return false;
	}
}