import java.math.BigInteger;

import javax.lang.model.util.ElementFilter;

public class FermatFactoring {

	private BigInteger integerN;
	
	
	public FermatFactoring(BigInteger integerN) {
		super();
		this.integerN = integerN;
	}



	public void factoring(){
		BigInteger rootK;
		double rootK_;
		BigInteger quadraticX;
		BigInteger quadraticY;
		double contD = 0;
		boolean factorFound = false;
		
		rootK_ = Math.floor(Math.sqrt(integerN.intValue())) + 1;
		rootK = BigInteger.valueOf((long) rootK_);
		quadraticY = rootK.multiply(rootK).subtract(integerN);
		
		while (factorFound==false) {
			
			double floorRootY = Math.floor(Math.sqrt(quadraticY.intValue()));
			double rootY = Math.sqrt(quadraticY.intValue());
			
			if ((floorRootY < (integerN.intValue()/2)) || (floorRootY==rootY)) {
				
				if (floorRootY==rootY) {

					//quadraticX = Math.sqrt(integerN+quadraticY);
					quadraticX = quadraticY.add(integerN);
					//quadraticX = quadraticX.pow(1/2);
					quadraticX = sqrt(quadraticX);
					quadraticY = BigInteger.valueOf((long) rootY);
					
					double firstFactor = quadraticX.intValue() - quadraticY.intValue();
					System.out.println("Primer factor no trivial de " + integerN + ":  " + firstFactor);
					
					double secondFactor = quadraticX.intValue() + quadraticY.intValue();
					System.out.println("Segundo factor no trivial de " + integerN + ":  " + secondFactor);
					factorFound = true;
					
				}else{
					//quadraticY = quadraticY + (2*rootK) + contD;
					quadraticY = quadraticY.add(rootK.add(rootK));
					quadraticY = quadraticY.add(BigInteger.valueOf((long) contD));
					contD = contD + 2;
				}
	
			}else {
				factorFound = true;
				System.out.println("No se encontro factor");
			}
			
			
		}
		
	}
	
	public static BigInteger sqrt(BigInteger x) {
	    BigInteger div = BigInteger.ZERO.setBit(x.bitLength()/2);
	    BigInteger div2 = div;
	    // Loop until we hit the same value twice in a row, or wind
	    // up alternating.
	    for(;;) {
	        BigInteger y = div.add(x.divide(div)).shiftRight(1);
	        if (y.equals(div) || y.equals(div2))
	            return y;
	        div2 = div;
	        div = y;
	    }
	
	}

}
