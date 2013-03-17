import java.math.BigInteger;

class E55
{  
	public static void main(String args[])
    {
		long start = System.currentTimeMillis();
        int sum = 0;
        for(int i = 1; i<10000; i++)
        {
            if(lychrel(0, new BigInteger("" + i))==30)
            {
                sum++;
            }
        }
		System.out.println(sum);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
    }
    public static long lychrel(int it, BigInteger n)
    {
        it++;
        BigInteger m = reverse(n);
        BigInteger test = m.add(n);
        if(it==30||isPalindrome(test))
        {
            return it;
        }
        return lychrel(it, test);
    }
    public static BigInteger reverse(BigInteger n)
    {
        return (new BigInteger(new StringBuffer(n.toString()).reverse().toString()));
    }
    public static boolean isPalindrome(BigInteger m)
    {
        String one = m.toString();
        String two = new StringBuffer(m.toString()).reverse().toString();
        if(one.equals(two))
        {
            return true;
        }
        return false;
    }
}