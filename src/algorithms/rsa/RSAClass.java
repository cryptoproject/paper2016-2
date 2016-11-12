package algorithms.rsa;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class RSAClass {
	private int firstPrimeP;
	private int secondPrimeQ;
	private int randomNumberE;
	private int valueD;
	private int messageM;
	private int primeProductN;
	private int eulerPHI;
	private Map<String, Integer> EEAMap;
	
	
	
	public RSAClass(int p, int q, int e, int m) {
		this.firstPrimeP = p;
		this.secondPrimeQ = q;
		this.randomNumberE = e;
		this.messageM = m;
		EEAMap = new HashMap<>();
	}
	public void keyGeneration(){
		primeProductN = firstPrimeP*secondPrimeQ;
		eulerPHI = (firstPrimeP-1)*(secondPrimeQ-1);
		
		valueD = extendedEulerAlgorithm(eulerPHI, randomNumberE);
		int[] publicKey = {randomNumberE,primeProductN};
		int[] privateKey = {valueD,primeProductN};
		
		int c = Encrypt(publicKey, messageM);
		System.out.println("C="+c);
		
		int m = Decrypt(privateKey, c);
		System.out.println("M="+m);
	}
		
	private int extendedEulerAlgorithm(int phi, int e){
		
		if(phi<e)System.out.println("Error Phi < E");
		
		int a = phi;
		int b = e;		
		int tempMod;
		int d=0;
		
		int lastI = 0;
		boolean flag = true;
		
		// EEA divisions
		for(int i=1;flag;i++){			
			EEAMap.put(i+"a", a);
			EEAMap.put(i+"b", b);
			
			if(b!=0){
				int q = (int) Math.floor(a/b);
				EEAMap.put(i+"q",q);
				tempMod = a%b;
				a = b;
				b = tempMod;
			}else{
				EEAMap.put(i+"q",-1);
				d = a;
				lastI = i;
				flag=false;
			}
			
		}
		
		int y = 0;
		int x = 1;
		//EEA Extended part
		EEAMap.put(lastI+"x", x);
		EEAMap.put(lastI+"y", y);
		for(int k = lastI-1;k>0;k--){
			x = y;
			y = (d-EEAMap.get(k+"a")*x)/EEAMap.get(k+"b");	
			EEAMap.put(k+"x", x);
			EEAMap.put(k+"y", y);
		}
		
		return EEAMap.get("1y");
	}
	
	private int Encrypt(int[] publicKey, int m){
		return powerMod(m, publicKey[0], publicKey[1]);		
	}
	
	private int Decrypt(int[] privateKey, int c){
		return powerMod(c, privateKey[0], privateKey[1]);		
	}
	
	private int powerMod(int aIn, int bIn, int nIn){		
		BigInteger a = BigInteger.valueOf(aIn);
		BigInteger b = BigInteger.valueOf(bIn);
		BigInteger n = BigInteger.valueOf(nIn);
		return a.modPow(b, n).intValue();
	}
}
