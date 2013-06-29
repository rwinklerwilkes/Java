class Heap
{
	private int[] heapArray;
	public int length;
	private boolean isMaxHeap = false;
	
	public int getParent(int n) throws Exception
	{
		if(!validIndex(n))
		{
			throw new IndexOutOfBoundsException("Index out of heap array bounds");
		}
		if(n==0)
		{
			return n;
		}
		else
		{
			return (int)(Math.floor((n-1)/2));
		}
	}
	public int left(int n) throws Exception
	{
		if(!validIndex(n))
		{
			throw new IndexOutOfBoundsException("Index out of heap array bounds");
		}
		if((2*n+1)<length)
		{
			return 2*n+1;
		}
		else
		{
			return length+1;
		}
	}
	public int right(int n) throws Exception
	{
		if(!validIndex(n))
		{
			throw new IndexOutOfBoundsException("Index out of heap array bounds");
		}
		if((2*n+2)<length)
		{
			return (2*n+2);
		}
		else
		{
			return length+1;
		}
	}
	
	public int valueAt(int n) throws Exception
	{
		if(!validIndex(n))
		{
			throw new IndexOutOfBoundsException("Index out of heap array bounds " + n);
		}
		return heapArray[n];
	}
	public Heap()
	{
		heapArray = new int[10];
		length = 0;
	}
	public Heap(int n)
	{
		heapArray = new int[n];
		length = 0;
	}
	public Heap(int[] array) throws Exception
	{
		this(array.length);
		for(int i = 0; i<array.length; i++)
		{
			heapArray[i] = array[i];
			length++;
		}
		buildMaxHeap();
	}
	private boolean validIndex(int n)
	{
		if(n>=length)
		{
			return false;
		}
		return true;
	}
	public void traverseInOrder(int n) throws Exception
	{
		if(n>=length)
		{
			return;
		}
		else
		{
			traverseInOrder(left(n));
			System.out.println(valueAt(n) + " at " + n);
			traverseInOrder(right(n));
		}
	}
	private void maxHeapify(int n) throws Exception
	{
		if(length==0)
		{
			return;
		}
		int l = left(n);
		int r = right(n);
		int largest;
		if(l<length&&valueAt(l)>valueAt(n))
		{
			largest = l;
		}
		else
		{
			largest = n;
		}
		if(r<length&&valueAt(r)>valueAt(largest))
		{
			largest = r;
		}
		if(largest!=n)
		{
			exchange(largest,n);
			maxHeapify(largest);
		}
	}
	private void exchange(int a, int b) throws Exception
	{
		if(!validIndex(a)||!validIndex(b))
		{
			throw new IndexOutOfBoundsException("Index out of heap array bounds");
		}
		int temp = heapArray[a];
		heapArray[a]=heapArray[b];
		heapArray[b]=temp;
		//System.out.println("Exchanged " + valueAt(a) + " and " + valueAt(b));
	}
	private void buildMaxHeap() throws Exception
	{
		int start = (int)(length-2)/2;
		while(start>=0)
		{
			maxHeapify(start);
			start--;
		}
		isMaxHeap = true;
	}
	public int[] sort() throws Exception
	{
		if(!isMaxHeap)
		{
			buildMaxHeap();
		}
		while(length>0)
		{
			exchange(0,length-1);
			length--;
			maxHeapify(0);
		}
		return heapArray;
	}
}