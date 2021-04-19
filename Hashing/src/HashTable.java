import java.util.ArrayList;

public class HashTable {
	
	private Visit[][] hashtable;
	private int N;
	private int[] collisions;
	private int countCollisions=0;
	private int countCol=0;
	
	public HashTable(int N) {
		
		this.N = N;
		hashtable = new Visit[N][100];
		collisions = new int[4000000];
		
		for (int i=0;i<N;i++) {
			for(int j=0;j<100;j++) {
				hashtable[i][j] = null;
			}
		}
	}
	
	public void addVisit(Visit visit,Card card,int c,int numberOfVisit) {
		
		int position=(c%N);
		Visit v = hashtable[position][0];
		
		if(v==null) {
			hashtable[position][numberOfVisit] = visit;
			int payment = visit.getPayment();
		}
		else {
			if(card.getNumber() == v.getCard().getNumber()) {
				hashtable[position][numberOfVisit] = visit;
			}
			else {
				
				int row=visit.getCard().getRow();
				if(row==-1) {
					countCol++;
					countCollisions++;
					hashtable=findEmptyRow(hashtable,position,visit,numberOfVisit,v);
				}
				else {
					hashtable[row][numberOfVisit-1] = visit;
				}
				
			}
		}
		
		
	}
	
	private Visit[][] findEmptyRow(Visit[][] ht,int position,Visit visit,int numberOfVisit,Visit v) {
		
		boolean condition=true;
		while(condition) {
			position=nextPosition(position);
			v = ht[position][0];
			
			if(v==null) {
				ht[position][numberOfVisit] = visit;
				visit.getCard().setRow(position);
				
				collisions[countCollisions-1]=position;
				condition=false;
			}
		}
		
		return ht;
	}
	
	private int nextPosition(int position) {
		
		if(position!=N-1) {
			position++;
		}
		else {
			position=0;
		}
		
		return position;
	}
	
	public void doubleHashTable(int length) {
		
		Visit[][] tempHashTable = new Visit[length][100];
		
		// Set null the temporary hash table.
		tempHashTable = setNull(length,tempHashTable);
		
		// Transfer data.
		tempHashTable = transferData(length,tempHashTable);
		
		N=length;
		hashtable=tempHashTable;
	}
	
	private Visit[][] setNull (int length,Visit[][] ht) {
		
			// Set null the empty positions.
			for (int i=0;i<length;i++) {
				for(int j=0;j<100;j++) {
					ht[i][j] = null;
				}
			}
			
			return ht;
	}
	
	private Visit[][] transferData(int length,Visit[][] tempHashTable) {
		
		// Transfer data.
		
		resetRows(); // Set to -1 the row variable in each card that had a collision.
		
		for (int i=0;i<N;i++){
			for(int j=0;j<100;j++) {
		    	  
				Visit visit = hashtable[i][j];
		    	  
		    	if(visit==null) {
		    		break;
		    	}
		    	else {
		    		Card card = visit.getCard();
		    		int pointer = card.getPointer();
		    		int position = (pointer%length);
		    		  
		    		
		    		Visit v = tempHashTable[position][0];
		    		if(v==null) {
		    			tempHashTable[position][j] = visit;
		    		}
		    		else {
		    			if(card.getNumber() == v.getCard().getNumber()) {
		    				tempHashTable[position][j] = visit;
		    			}
		    			else {
		    				int row=visit.getCard().getRow();
		    				if(row==-1) {
		    					countCollisions++;
		    					tempHashTable=findEmptyRow(tempHashTable,position,visit,j,v);
		    				}
		    				else {
		    					tempHashTable[row][j-1] = visit;
		    				}
		    				
		    			}
		    		}
		    		  
		    		  
		    	}
		    	  
		      }
		}
		
		
		
		return tempHashTable;
	}
	
	private void resetRows() {
		
		for(int i=0;i<countCollisions;i++) {
			int row = collisions[i];
			Visit visit = hashtable[row][0];
			Card card = visit.getCard();
			card.setRow(-1);
		}
		
		collisions = new int[4000000];
		
		countCollisions=0;
	}
	
	public Card findMin (int prime) {
		
		int counter=0;
		int counter1=0;
		Card card = null;
		int minPayment=100000;
		System.out.println("Prime="+String.valueOf(prime));
		for(int i=0;i<prime;i++) {
			Visit visit=hashtable[i][0];
			if(visit==null) {
				counter++;
				continue;
			}
			int payment=visit.getCard().getTotalPayment();
			if(payment<minPayment) {
				minPayment=payment;
				card=visit.getCard();
				counter1++;
			}
		}
		
		System.out.println("The card with minimum total payment is: "+(card.getNumber())+" and the total payments is: "+String.valueOf(card.getTotalPayment()));
		
		return card;
	}
	
	public Card findMaxVisitsCard (int prime) {
		
		Card card = null;
		int maxVisits=0;
		for(int i=0;i<prime;i++) {
			Visit visit=hashtable[i][0];
			if(visit==null) {
				continue;
			}
				
			int totalVisits=visit.getCard().getTotalVisits();
			
			if(totalVisits>maxVisits) {
				maxVisits=totalVisits;
				card=visit.getCard();
			}
		}
		
		System.out.println("The card with minimum total visits is: "+(card.getNumber())+" and the total visits are: "+String.valueOf(card.getTotalVisits()));
		
		return card;
	}
	
	public Card findMinVisitsCard (int prime) {
			
		Card card = null;
		int minVisits=1000;
		for(int i=0;i<prime;i++) {
			Visit visit=hashtable[i][0];
			if(visit==null) {
				continue;
			}
			
			int totalVisits=visit.getCard().getTotalVisits();
			
			if(totalVisits<minVisits) {
				minVisits=totalVisits;
				card=visit.getCard();
			}
		}
		
		System.out.println("The card with maximum total visits is: "+(card.getNumber())+" and the total visits are: "+String.valueOf(card.getTotalVisits()));
		
		return card;
	}
	
	public int getCollisions() {
		
		return countCol;
	}
	
}