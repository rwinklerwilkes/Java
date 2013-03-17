class E57
{
	public static void main(String args[])
	{
		Fraction[] f = new Fraction[1000];
		f[0] = new Fraction(1,2);
		final Fraction ONE = new Fraction(1,1);
		final Fraction TWO = new Fraction(2,1);
		for(int i = 1; i<f.length; i++)
		{
			f[i]=Fraction.divide(1,Fraction.add(TWO,f[i-1]));
		}
		int sum = 0;
		for(int i = 0; i<f.length; i++)
		{
			Fraction output = Fraction.add(f[i],ONE);
			String num = String.valueOf(output.getNum());
			String denom = String.valueOf(output.getDenom());
			if(num.length()>denom.length())
			{
				if(i<100)
				{
					System.out.println(output);
				}
				sum++;
			}
		}
		System.out.println(sum);
	}
}