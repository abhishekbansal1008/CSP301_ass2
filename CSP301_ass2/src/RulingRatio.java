import java.util.LinkedList;

import prefuse.data.Table;
import prefuse.data.io.CSVTableReader;
import prefuse.data.io.DataIOException;

public class RulingRatio {

	/**
	 * Displays the fraction of seats held by the majority party in the state 
	 * 
	 * @author Abhishek Bansal
	 * @param l - Linked List containing the parties of all the candidates
	 * @return - void, but displays the ratio calculated
	 */
	
	public static void rulingratio(LinkedList<?> l) {
		int j = l.size();
		double count = 0.0;
		Object s = new Object();
		LinkedList<Object> mem = new LinkedList<>();
		
		// Generates all the different parties present in the state
		
		for (int i = 0; i < j; i++) {
			s = l.get(i);
			if (!mem.contains(s)) {
				// System.out.print(s + "  ");
				mem.add(s);
			}
		}
		
		// Calculates their frequency and stores the maximum in count2
		for (int a = 0; a < mem.size(); a++) {
			s = mem.get(a);
			double count2 = 0;
			for (int i = 0; i < j; i++) {
				if (l.get(i).equals(s)) {
					count2++;
				}
			}
			if (count < count2) {
				count = count2;
			}
		}
		
		// displays ruling ratio
		
		double ratio = count / j;
		System.out.println((Math.round(ratio * 100)) / 100.0);

	}

	/**
	 * main function
	 * 
	 * @author Abhishek Bansal
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		// reads file and creates a table through CSVTableReader
		
		LinkedList<Object> l = new LinkedList<Object>();
		LinkedList<Object> l_partyofstate;
		Table t1 = new Table();
		CSVTableReader t = new CSVTableReader();
		try {
			t1 = t.readTable("MPTrack-15.csv");
		} catch (DataIOException e) {
			System.out.println("File not Found!!");
		}
		
		int a = 552;
		Object s;
		
		// Finds all the distinct states 
		for (int i = 0; i < a; i++) {
			s = t1.get(i, 4);
			if (!l.contains(s)) {
				l.add(s);
			}
		}
		
		// Finds all the parties of the MP of each state
		for (int i = 0; i < l.size(); i++) {
			Object abc = l.get(i);
			l_partyofstate = new LinkedList<Object>();
			for (int j = 0; j < a; j++) {
				s = t1.get(j, 6);
				if (t1.get(j, 4).equals(abc)) {
					l_partyofstate.add(s);
				}
			}
			rulingratio(l_partyofstate);
		}

	}

}
