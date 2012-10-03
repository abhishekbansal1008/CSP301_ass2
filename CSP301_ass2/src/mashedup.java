import java.util.LinkedList;

import prefuse.data.Table;
import prefuse.data.io.CSVTableReader;
import prefuse.data.io.DataIOException;

public class mashedup {

	// function for averages
	@SuppressWarnings("rawtypes")
	public static int average(LinkedList l) {
		double sum = 0;
		float len = l.size();
		if (len == 0) {
			return 0;
		}
		for (int i = 0; i < len; i++) {
			int t = (int) l.get(i);
			sum = sum + t;
		}
		float f = (float) sum;

		return (Math.round(f / len));

	}

	/**
	 * Function for sorting the names of parties or states based on their MP
	 * count.
	 * 
	 * @author Abhishek Bansal
	 * @param l
	 *            - Linked list having names of parties or states
	 * @param l1
	 *            - Linked list having number of occurrences of data fields in
	 *            l.
	 * @return - Linked list which is a sorted form of l with respect to values
	 *         in l1.
	 */
	public static LinkedList<Object> sorter(LinkedList<Object> l,
			LinkedList<Integer> l1) {
		LinkedList<Object> l2 = new LinkedList<>();
		int num = l1.size();
		for (int j = 0; j < num; j++) {
			int ptr = 0, base = 0;
			for (int i = 0; i < l1.size(); i++) {
				int abc = (int) l1.get(i);
				if (abc > base) {
					base = abc;
					ptr = i;
				}
			}

			l2.add(l.get(ptr));
			l.remove(ptr);
			l1.remove(ptr);

		}

		return l2;

	}

	/**
	 * Gives data for creating mashups
	 * 
	 * @author Abhishek Bansal
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 494;
		LinkedList<Object> l_major = new LinkedList<Object>();
		LinkedList<Object> l_state = new LinkedList<Object>();
		LinkedList<Integer> l_counts = new LinkedList<Integer>();
		LinkedList<Integer> l_counts2 = new LinkedList<Integer>();
		LinkedList<Object> l = new LinkedList<Object>();
		LinkedList<Object> l1 = new LinkedList<Object>();
		LinkedList<Object> l_age;
		LinkedList<Object> l_debate;
		LinkedList<Object> l_edu;
		LinkedList<Object> l_ques;
		LinkedList<Object> l_atten;
		Table t1 = new Table();
		CSVTableReader t = new CSVTableReader();
		try {
			t1 = t.readTable("newfile.csv");
		} catch (DataIOException e) {
			// TODO Auto-generated catch block
			System.out.println("File not Found!!");
		}

		// Selects distinct parties
		Object s, s1, s2, s3, s4;
		for (int i = 0; i < a; i++) {
			s = t1.get(i, 6);
			if (!l.contains(s)) {
				l.add(s);
			}
		}

		// Selects distinct states
		for (int i = 0; i < a; i++) {
			s1 = t1.get(i, 4);
			if (!l1.contains(s1)) {
				l1.add(s1);
			}
		}
		for (int i = 0; i < l.size(); i++) {
			Object abc = l.get(i);
			l_atten = new LinkedList<Object>();
			int count = 0;

			// counts number of MPs of each party
			for (int j = 0; j < a; j++) {
				s = t1.get(j, 11);
				if (t1.get(j, 6).equals(abc)) {
					count++;
				}
			}

			if (count > 6) {
				l_major.add(abc);
				l_counts.add(count);
			}
		}

		l_major = sorter(l_major, l_counts);

		for (int i = 0; i < l1.size(); i++) {
			Object abc = l1.get(i);
			int count = 0;

			// counts number of MPs of each state
			for (int j = 0; j < a; j++) {
				s = t1.get(j, 11);
				if (t1.get(j, 4).equals(abc)) {
					count++;
				}
			}

			l_state.add(abc);
			l_counts2.add(count);
		}

		l_state = sorter(l_state, l_counts2);

		// Code for calculating average value of a column

		for (int i = 0; i < l_state.size(); i++) {
			Object jagah = l_state.get(i);
			for (int k = 0; k < l_major.size(); k++) {
				Object bande = l_major.get(k);
				l_age = new LinkedList<Object>();
				l_debate = new LinkedList<Object>();
				l_edu = new LinkedList<Object>();
				l_ques = new LinkedList<Object>();
				l_atten = new LinkedList<Object>();
				for (int j = 0; j < a; j++) {
					s = t1.get(j, 11);
					s1 = t1.get(j, 12);
					s2 = t1.get(j, 25);
					s3 = t1.get(j, 14);
					s4 = t1.get(j, 15);
					if (t1.get(j, 4).equals(jagah)
							&& t1.get(j, 6).equals(bande)) {
						l_age.add(s);
						l_debate.add(s1);
						l_edu.add(s2);
						l_ques.add(s3);
						l_atten.add(s4);
					}
				}
				System.out.print("[" + (i + 1) + "," + (k + 1) + ","
						+ average(l_edu) + "],");
			}
		}
		// System.out.println("Average attendance - " + average(l_atten));
	}
}
