import java.util.Random;

class Radix
{
	public static void main(String args[])
	{
		long start = System.currentTimeMillis();
		int[] toSort = new int[1000000];
		Random r = new Random();
		for(int i = 0; i<toSort.length; i++)
		{
			toSort[i]=r.nextInt(1000000);
		}
		System.out.println("populated");
		int[] result = sort(toSort, 0,6);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	public static int[] sort(int[] m, int pos,int length)
	{
		int divide = (int)(Math.pow(10,pos));
		Queue[] q = new Queue[10];
		for(int i =0; i<q.length; i++)
		{
			q[i]=new Queue();
		}
		for(int i = 0; i<m.length; i++)
		{
			int toAdd = (m[i]/divide)%10;
			q[toAdd].push(m[i]);
		}
		int i = 0;
		int j = 0;
		while(j<q.length)
		{
			while(q[j].size>0)
			{
				m[i]=q[j].pop();
				i++;
			}
			j++;
		}
		pos++;
		if(pos<length)
		{
			return sort(m,pos,length);
		}
		else
		{
			return m;
		}
	}
}