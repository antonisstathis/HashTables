import java.util.ArrayList;
import java.util.Random;

public class App {

	public static void main(String[] args) {
		
		int start=1000;
		int prime;
		int N=50000; // total number of cards
		int M=2000000; // total number of visits
		float loadfactor=(float) 0.6;
		Card[] cards = new Card[50000];
		Random cardGenerator = new Random(228524);
		Random visitsGenerator = new Random(228524);
		Random chooseCard = new Random(228524);
		ProducePrimeNumber primenumbers = new ProducePrimeNumber();
		
		prime=primenumbers.produce(start,start+100);
		HashTable hashtable = new HashTable(prime);
		
		// Create 50000 cards and save them in an array.
		for(int i=0;i<N;i++) {
			Card card = new Card(cardGenerator,i);
			cards[i] = card;
		}
		
		
		// M=2000000 because we want to create two million visits in total.
		// The cards will be 50000. So we will have approximately 40 visits with each card.
		// 40*50000=2000000 visits in total
		long startTime = System.nanoTime();
		for(int i=0;i<M;i++) {
			
			// Check if load factor is reached.
			// If yes then produce a new prime number which is approximately double by previous one.
			// Then double the length of the hash table.
			float i_f = (float) i;
			float prime_f = (float) prime;
			float quantity= (float) (i_f/prime_f);
			if(quantity>loadfactor) {
				start=2*start;
				prime=primenumbers.produce(start,start+100);
				hashtable.doubleHashTable(prime);
			}
			
			// Choose one card randomly out of 50000.
			int pos = chooseCard.nextInt(N);
			Card card = cards[pos];
			
			// Create a visit with this specific card.
			Visit visit = new Visit(card,visitsGenerator);
			card.insertVisit(visit);
			int nofv = card.getNumberOfVisits(); // nofv is the total number of visits that this card has.
			
			// Add this visit to the hashtable.
			hashtable.addVisit(visit,card,pos,nofv-1);
		}
		long stopTime = System.nanoTime();
		long duration = (stopTime - startTime);
		System.out.println("It took " + String.valueOf(duration) + "ns to save two million visits.");
		
		Card minPaymentCard = hashtable.findMin(prime);
		Card maxVisitsCard = hashtable.findMaxVisitsCard(prime);
		Card minVisitsCard = hashtable.findMinVisitsCard(prime);
		int collisions = hashtable.getCollisions();
		
		System.out.println("Hashtable rows are:" + String.valueOf(prime));
		System.out.println("The total collisions were:" + String.valueOf(collisions));

	}

}
