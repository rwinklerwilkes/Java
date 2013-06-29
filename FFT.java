import org.apache.commons.math3.complex.Complex;

class FFT
{
	static Complex i = Complex.I;
	static Double pi = Math.PI;
	public static void main(String args[])
	{
		Complex[] a = new Complex[4];
		a[0] = new Complex(1);
		a[1] = new Complex(2);
		a[2] = new Complex(3);
		a[3] = new Complex(4);
		Complex[] y = fftrec(a);
                double[] yinv = ifft(y);
		for(int j = 0; j<y.length; j++)
		{
			System.out.println("FFT: " + y[j]);
                        System.out.println("Inverse FFT: " + yinv[j]);
		}
                double[] array1 = {1,2,1,0};
                double[] array2 = {2,1,0,0};
                double[] hopeWorks = convolute(array1,array2);
                for(int j = 0; j<hopeWorks.length; j++)
		{
			System.out.println(hopeWorks[j]);
		}
	}
        public static double[] convolute(double[] a, double[] b)
        {
            int largestLength = Math.max(a.length,b.length);
            Complex[] aCopy = new Complex[2*largestLength];
            Complex[] bCopy = new Complex[2*largestLength];
            for(int j = 0; j<aCopy.length; j++)
            {
                if(j<a.length)
                {
                    aCopy[j] = new Complex(a[j]);
                }
                else
                {
                    aCopy[j] = new Complex(0);
                }
                if(j<b.length)
                {
                    bCopy[j] = new Complex(b[j]);
                }
                else
                {
                    bCopy[j] = new Complex(0);
                }
            }
            aCopy = fftrec(aCopy);
            bCopy = fftrec(bCopy);
            Complex[] retArray = new Complex[aCopy.length];
            for(int j = 0; j<aCopy.length; j++)
            {
                retArray[j] = aCopy[j].multiply(bCopy[j]);
            }
            return ifft(retArray);
        }
	public static Complex[] fftrec(Complex[] a)
	{
		int n = a.length;
		if(n==1)
		{
			return a;
		}
		Complex[] aZero = new Complex[n/2];
		Complex[] aOne = new Complex[n/2];
		double on = 2*pi/n;
		Complex omegan = new Complex(on);
		omegan = omegan.multiply(i);
		omegan = omegan.exp();
		Complex omega = new Complex(1);
		for(int j = 0; j<n; j+=2)
		{
			int actJ = j/2;
			aZero[actJ] = a[j];
			aOne[actJ] = a[j+1];
		}
		Complex[] y = new Complex[n];
		Complex[] yZero = fftrec(aZero);
		Complex[] yOne = fftrec(aOne);
		for(int k = 0;k<=n/2-1; k++)
		{
			Complex y0 = yZero[k];
			Complex y1 = yOne[k];
                        Complex t = omega.multiply(y1);
			y[k] = y0.add(t);
			y[k+n/2] = y0.subtract(t);
			omega = omegan.multiply(omega);
		}
		return y;
	}
        public static double[] ifft(Complex [] a)
        {
            Complex[] y = ifftrec(a);
            double [] retArray = new double[y.length];
            for(int j = 0; j<retArray.length; j++)
            {
                retArray[j] = y[j].getReal()/retArray.length;
            }
            return retArray;
        }
        public static Complex[] ifftrec(Complex[] a)
	{
		int n = a.length;
		if(n==1)
		{
			return a;
		}
		Complex[] aZero = new Complex[n/2];
		Complex[] aOne = new Complex[n/2];
		double on = 2*pi/n;
		Complex omegan = new Complex(on);
		omegan = omegan.multiply(i);
                omegan = omegan.multiply(-1);
		omegan = omegan.exp();
		Complex omega = new Complex(1);
		for(int j = 0; j<n; j+=2)
		{
			int actJ = j/2;
			aZero[actJ] = a[j];
			aOne[actJ] = a[j+1];
		}
		Complex[] y = new Complex[n];
		Complex[] yZero = ifftrec(aZero);
		Complex[] yOne = ifftrec(aOne);
		for(int k = 0;k<=n/2-1; k++)
		{
			Complex y0 = yZero[k];
			Complex y1 = yOne[k];
                        Complex t = omega.multiply(y1);
			y[k] = y0.add(t);
			y[k+n/2] = y0.subtract(t);
			omega = omegan.multiply(omega);
		}
		return y;
	}
}