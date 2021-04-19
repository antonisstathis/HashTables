import java.util.ArrayList;
import java.util.Random;

public class Card {
	
	private Random generator;
	private String number;
	private String[] d = {"A","B","C","D"};
	private String[] digits;
	private int[] pos;
	private Visit[] array;
	private int counter = 0;
	private int pointer;
	private int totalPayment=0;
	private int totalVisits=0;
	private int row=-1; // Here is saved the row that this card will be saved in hashtable after a collision.
	
	public Card(Random generator,int pointer) {
		
		this.generator=generator;
		number=String.valueOf((int) (generator.nextInt(10)));
		digits=new String[16];
		pos = new int[4];
		array = new Visit[100];
		this.pointer=pointer;
		
		producePositions(pos);
		insertDigits();
		enterLetters();
		produceCardNumber();
		
	}
	
	public Visit[] getVisits() {
		
		return array;
	}
	
	public void insertVisit(Visit visit) {
		
		array[counter]=visit;
		counter++;
		int payment=visit.getPayment();
		totalPayment=totalPayment+payment;
		totalVisits++;
	}
	
	public int getNumberOfVisits() {
		
		return counter;
	}
	
	public int getPointer() {
		
		return pointer;
	}
	
	private void producePositions(int[] pos) {
		
		// This function produce four random and different integers from 0 to 15
		// These 4 integers are the positions that will be placed the letters (A,B,C,D).
		
		boolean condition=true;
		while(condition) {
			
			int flag=0;
			
			for(int i=0;i<4;i++) {
				pos[i]=(int) (generator.nextInt(16));
			}
			
			for(int i=0;i<4;i++) {
				for(int j=i+1;j<4;j++) {
					if(pos[i]==pos[j]) {
						flag=1;
					}
				}
			}
			
			if(flag==0) {
				condition=false;
			}
		}
			
	}
	
	private void insertDigits() {
		
		// This function choose 16 random digits from 0 to 9 for the card.
		
		for (int i=0;i<16;i++) {
			if(i==0) {
				digits[i]=number;
			}
			else {
				int dig = (int) (generator.nextInt(10)); // produce a random digit from 0 to 9
				String digit = String.valueOf(dig);
				digits[i]=digit;
			}
		}
		
	}
	
	private void enterLetters() {
		
		// This function place the four letters in the correct positions.
		
		for(int i=0;i<4;i++) {
			digits[pos[i]]=d[i];
		}
		
	}
	
	private void produceCardNumber() {
		
		// This function eventually produce the final card number.
		
		number=digits[0];
		for(int i=1;i<16;i++) {
			number=number+digits[i];
		}
	}
	
	public String getNumber() {
		
		return number;
	}
	
	public int getTotalPayment() {
		
		return totalPayment;
	}
	
	public int getTotalVisits() {
		
		return totalVisits;
	}
	
	public void setRow(int row) {
		
		this.row=row;
	}
	
	public int getRow() {
		
		return row;
	}
	
}
