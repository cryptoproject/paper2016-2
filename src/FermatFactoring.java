import javax.lang.model.util.ElementFilter;

public class FermatFactoring {

	private double integerN;
	
	
	public FermatFactoring(double integerN) {
		super();
		this.integerN = integerN;
	}



	public void factoring(){
		double rootK;
		double quadraticX;
		double quadraticY;
		double contD = 0;
		boolean factorFound =false;
		rootK = Math.floor(Math.sqrt(integerN)) + 1;
		quadraticY = (rootK*rootK) - integerN;
		
		while (factorFound==false) {
			
			double floorRootY = Math.floor(Math.sqrt(quadraticY));
			double rootY = Math.sqrt(quadraticY);
			
			if ((floorRootY < (integerN/2)) || (floorRootY==rootY)) {
				
				if (floorRootY==rootY) {

					quadraticX = Math.sqrt(integerN+quadraticY);
					quadraticY = rootY;
					
					double firstFactor = quadraticX - quadraticY;
					System.out.println("Primer factor no triviar de " + integerN + ":  " + firstFactor);
					
					double secondFactor = quadraticX + quadraticY;
					System.out.println("Segundo factor no triviar de " + integerN + ":  " + secondFactor);
					factorFound = true;
				}else{
					quadraticY = quadraticY + (2*rootK)+contD;
					contD = contD + 2;
				}
	
			}else {
				factorFound = true;
				System.out.println("No se encontro factor");
			}
			
			
		}
		
	}
	
	

}
