import java.util.Random;

class Quicksort
{
	public static void main(String args[])
	{
		Random r = new Random();
		int[] partTest = new int[20];
		for(int i = 0; i<partTest.length; i++)
		{
			partTest[i]=r.nextInt(20);
			System.out.print(partTest[i] + " ");
		}
		System.out.println();
		sort(partTest, 0, partTest.length-1);
		for(int i = 0; i<partTest.length; i++)
		{
			System.out.print(partTest[i] + " ");
		}
	}
	public static int partition(int[] m, int low, int high)
	{
		int val=m[low];
		int i=low;
		int j=high+1;
		while(true)
		{
			while(m[++i]<val)
			{
				if(i==high)
				{
					break;
				}
			}
			while(m[--j]>val)
			{
				if(j==low)
				{
					break;
				}
			}
			if(i>=j)
			{
				break;
			}
			exch(m,i,j);
		}
		exch(m,low,j);
		
		return j;
	}
	public static void exch(int[] m, int i, int j)
	{
		int temp = m[i];
		m[i]=m[j];
		m[j]=temp;
	}
	public static void sort(int[] m, int low, int high)
	{
		if(high<=low) return;
		int j = partition(m, low, high);
		sort(m, low, j-1);
		sort(m, j+1, high);
	}
}