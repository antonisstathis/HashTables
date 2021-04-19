import java.util.Random;

public class Visit {
	
	private Random generator;
	private String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	
	private Card card; 
	private String day;
	private int payment;
	
	public Visit(Card card,Random generator) {
		
		this.card=card;
		this.generator=generator;
		int pos=(int) (generator.nextInt(6)); // generates a random number from 0 to 5
		
		day=days[pos];
		payment=randomPayment(generator);
	}
	
	public Visit(String day,int payment) {
		
		this.day=day;
		this.payment=payment;
	}
	
	public Card getCard() {
			
		return card;
	}
	
	public String getDay() {
		
		return day;
	}
	
	public int getPayment() {
		
		return payment;
	}
	
	private int randomPayment(Random generator) {
	    
	    int num = (int) (20+(generator.nextInt(80))); // generates a random number from 20 to 100

	    return num;
	}
	
}
