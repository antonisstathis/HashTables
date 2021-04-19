
public class ProducePrimeNumber {
	
	private int position=0;
	private int counter=0;
	
	public ProducePrimeNumber() {
		
	}
	
	public int produce(int start,int end) {
	    
	    int flag;
	    int prime=0;
	    
	    for (int i=start;i<=end;i++) {
	 
	        flag = 1;
	        for (int j=2;j<=i/2;j++) {
	            if (i%j == 0){
	                flag = 0;
	                break;
	            }
	        }
	 
	        if (flag == 1) {
	            //System.out.println("Prime="+String.valueOf(i));
	            prime=i;
	            break;
	        }
	    }
	    
		return prime;
	}
	 
}
