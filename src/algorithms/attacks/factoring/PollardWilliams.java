import java.math.BigInteger;
import java.util.Random;

public class PollardWilliams {
	
	private BigInteger integerN;
	private int boundB;
	
	public PollardWilliams(BigInteger integerN, int boundB) {
		super();
		this.integerN = integerN;
		this.boundB = boundB;
	}
	
	public void factoring(){
		
		BigInteger randomA;//nextRandomBigInteger(integerN);
		BigInteger gcdAandN = BigInteger.valueOf(0);
		boolean factorFound =false;
		int [] vectorK = new int [boundB];
		int tempo = 1;
		for (int i = 0; i < boundB ; i++) {
			vectorK[i] = i+1;
		}
		for (int i = 0; i < boundB ; i++) {
			tempo = getLCM(tempo, vectorK[i]);
		}
		int i = 2;
		while (factorFound==false) {
			randomA = BigInteger.valueOf(i);
			System.out.println(gcdAandN.intValue());
			System.out.println(integerN.intValue());
			if ((1<gcdAandN.intValue()) && (gcdAandN.intValue()<integerN.intValue())) {
				factorFound = true;
			}else {
				BigInteger lcmK = BigInteger.valueOf(tempo);
				BigInteger expoA = randomA.modPow(lcmK, integerN);
				gcdAandN = getGCD(expoA.subtract(BigInteger.valueOf(1)),integerN);
				System.out.println("El minimo comun multiplo es: " + lcmK);
				System.out.println("Factor no trivial: " + gcdAandN);
				i++;
			}
		}
		
	}
	
	public BigInteger nextRandomBigInteger(BigInteger n) {
	    Random rand = new Random();
	    BigInteger result = new BigInteger(n.bitLength(), rand);
	    while( result.compareTo(n) >= 0 ) {
	        result = new BigInteger(n.bitLength(), rand);
	    }
	    return result;
	}
	private static BigInteger getGCD(BigInteger a, BigInteger b) {
		BigInteger gcd = a.gcd(b);
	    return gcd;
	}


	static int  getLCM(int a,int b) {
	    int x;
	    int y;
	    if(a<b){
	        x=a;
	        y=b;   
	    } else{
	         x=b;
	         y=a;   
	    }
	    int i=1;
	    while(true){
	
	        int x1=x*i;
	        int y1=y*i;
	        for(int j=1;j<=i;j++){
		        if(x1==y*j){
		            return x1;
		        }
	        }
	        i++;
	    }

	}
}
