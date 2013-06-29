import java.util.Random;

class Mergesort
{
	public static void main(String args[])
	{
		long start = System.currentTimeMillis();
		int[] test =  new int[1000000];
		Random r = new Random();
		for(int i = 0; i<test.length; i++)
		{
			test[i] = r.nextInt(1000000);
		}
		mergesort(test, 0, test.length-1);
		for(int i = 0; i<10; i++)
		{
			System.out.println(test[i]);
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	public static void mergesort(int[] m, int p, int r)
	{
		int q = (int)(p+r)/2;
		if(p<r)
		{
			mergesort(m,p,q);
			mergesort(m,q+1,r);
			merge(m,p,q,r);
		}
	}
	public static void merge(int[] m, int p, int q, int r)
	{
		int[] helper1 = new int[q-p+1];
		int[] helper2 = new int[r-q];
		for(int i = 0; i<=q-p; i++)
		{
			helper1[i]=m[p+i];
		}
		for(int j = 0; j<r-q; j++)
		{
			helper2[j]=m[q+j+1];
		}
		int i = 0;
		int j = 0;
		for(int k = p; k<=r; k++)
		{
			if(i>=helper1.length)
			{
				m[k]=helper2[j];
				j++;
			}
			else if(j>=helper2.length)
			{
				m[k]=helper1[i];
				i++;
			}
			else if(helper1[i]<=helper2[j])
			{
				m[k]=helper1[i];
				i++;
			}
			else
			{
				m[k]=helper2[j];
				j++;
			}
		}
	}
}