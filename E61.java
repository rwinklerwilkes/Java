import java.util.ArrayList;
import java.util.Arrays;

class E61
{
    private static final int triStart = 45;
    private static final int triEnd = 140;
    private static final int sqStart = 32;
    private static final int sqEnd = 100;
    private static final int pentStart = 26;
    private static final int pentEnd = 81;
    private static final int hexStart = 23;
    private static final int hexEnd = 70;
    private static final int heptStart = 21;
    private static final int heptEnd = 63;
    private static final int octStart = 19;
    private static final int octEnd = 58;
    //These static final ints are inclusive of the last value < 10000
    private static int triInd;
    private static int[] set = new int[6];
    private static int[] triNums = new int[96];
    private static int[] triFirstTwo = new int[96];
	private static int[] octFirstTwo;
	private static int[] heptFirstTwo;
    private static ArrayList<Integer> workingOcts;
	private static ArrayList<Integer> workingHepts;
	private static ArrayList<Integer> workingHex;
    public static void main(String args[])
    {
        triInd = 0;
        while(triInd<=triEnd-triStart)
        {
            triNums[triInd] = nextTriangle(triInd);
            String s = String.valueOf(triNums[triInd]);
            triFirstTwo[triInd] = Integer.parseInt(s.substring(0,2));
			triInd++;
        }
        Arrays.sort(triFirstTwo);
        workingOcts = new ArrayList<Integer>();
        fillOcts();
		fillHepts();
		fillHex();
		for(int i: triNums)
		{
			System.out.println(i);
		}
    }
    public static int nextTriangle(int triInd)
    {
        int index = triStart + triInd;
        return (index*(index+1)/2);
    }
    public static void fillOcts()
    {
        int index = octStart;
        workingOcts = new ArrayList<Integer>();
        while(index<=octEnd)
        {
            int num = index*(3*index-2);
            String s = String.valueOf(num);
            int lastTwo = Integer.parseInt(s.substring(2,4));
            if(Arrays.binarySearch(triFirstTwo,lastTwo)>=0)
            {
                workingOcts.add(num);
            }
			index++;
        }
		workingOcts.trimToSize();
		octFirstTwo=new int[workingOcts.size()];
		for(int i = 0; i<octFirstTwo.length; i++)
		{
			octFirstTwo[i]=Integer.parseInt(String.valueOf(workingOcts.get(i)).substring(0,2));
		}
		Arrays.sort(octFirstTwo);
    }
    public static void fillHepts()
    {
        int index = heptStart;
        workingHepts = new ArrayList<Integer>();
        while(index<=heptEnd)
        {
            int num = (index*(5*index-3)/2);
            String s = String.valueOf(num);
            int lastTwo = Integer.parseInt(s.substring(2,4));
            if(Arrays.binarySearch(octFirstTwo,lastTwo)>=0)
            {
                workingHepts.add(num);
            }
			index++;
        }
		workingHepts.trimToSize();
		heptFirstTwo=new int[workingHepts.size()];
		for(int i = 0; i<heptFirstTwo.length; i++)
		{
			heptFirstTwo[i]=Integer.parseInt(String.valueOf(workingHepts.get(i)).substring(0,2));
		}
		Arrays.sort(heptFirstTwo);
    }
	private static void fillHex()
	{
		int index = hexStart;
        workingHex = new ArrayList<Integer>();
        while(index<=hexEnd)
        {
            int num = (index*(2*index-1));
            String s = String.valueOf(num);
            int lastTwo = Integer.parseInt(s.substring(2,4));
            if(Arrays.binarySearch(heptFirstTwo,lastTwo)>=0)
            {
                workingHex.add(num);
            }
			index++;
        }
	}
}