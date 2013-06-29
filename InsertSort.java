import java.util.Random;

class InsertSort
{
	public static void main(String args[])
	{
		long start = System.currentTimeMillis();
		int[] test =  new int[100000];
		Random r = new Random();
		for(int i = 0; i<test.length; i++)
		{
			test[i]=r.nextInt(100000);
		}
		System.out.println();
		int[] test2 = insertsort(test, test.length);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	public static int[] insertsort(int[] m, int size)
	{
		int[] array2 = new int[size];
		for(int i = 0; i<m.length; i++)
		{
			int ins = binarySearch(array2,m[i],0,i-1);
			if(ins<i)
			{
				for(int j = i-1; j>=ins; j--)
				{
					array2[j+1]=array2[j];
				}
			}
			array2[ins]=m[i];
		}
		return array2;
	}
	public static int binarySearch(int[] m, int val, int low, int high)
	{
		if(low>high)
		{
			return low;
		}
		int mid = (int)(low+(high-low)/2);
		if(m[mid]>val)
		{
			return binarySearch(m,val,low,mid-1);
		}
		else if(m[mid]<val)
		{
			return binarySearch(m,val,mid+1,high);
		}
		return mid;
	}
}