import java.util.ArrayList;

class E38
{
	public static int largest;
	public static void main(String args[])
	{
		long start = System.currentTimeMillis();
		largest=123456789;
		for(int i = 2; i<100000; i++)
		{
			panNum(i);
		}
		System.out.println(largest);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	public static void panNum(int n)
	{
		StringBuilder sb = new StringBuilder();
		int count = 1;
		while(sb.length()<10)
		{
			int next = n*count;
			count++;
			sb.append(next);
			String test = sb.toString();
			if(test.length()==9&&isPandig(test))
			{
				int size = Integer.parseInt(test);
				if(size>largest)
				{
					largest=size;
				}
			}
		}
	}
	public static boolean isPandig(String n)
	{
		ArrayList<Integer> digits = new ArrayList<Integer>();
		for(int i = 0; i<n.length(); i++)
		{
			int m = Character.getNumericValue(n.charAt(i));
			if(m==0||digits.contains(m))
			{
				return false;
			}
			else
			{
				digits.add(m);
			}
		}
		return true;
	}
}