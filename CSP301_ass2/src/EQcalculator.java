import java.util.LinkedList;

import prefuse.data.Table;
import prefuse.data.io.CSVTableReader;
import prefuse.data.io.DataIOException;

public class EQcalculator {

	// function for averages
	@SuppressWarnings("rawtypes")
	public static float average(LinkedList l) {
		double sum = 0;
		float len = l.size();
		if (len == 0) {
			return (float) 0.0;
		}
		for (int i = 0; i < len; i++) {
			int t = (int) l.get(i);
			sum = sum + t;
		}
		float f = (float) sum;

		return ((f / len));

	}

	/**
	 * Calculates the education quotient(EQ) of a party or state by taking the
	 * mean of all the education values of MPs involved. The education values
	 * are assigned according to education level of the MPs Under Matric are
	 * given 10 points, under graduates are given 20, those with diplomas are
	 * given 45, grads are given 60 points, post grads a healthy 75 points and
	 * those holding PhD have 90 points to their name.
	 * 
	 * @author abhishek bansal
	 * 
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LinkedList<Object> l = new LinkedList<Object>();
		LinkedList<Object> l_eq = new LinkedList<Object>();
		LinkedList<Object> l_val;

		// reading the file
		Table t1 = new Table();
		CSVTableReader t = new CSVTableReader();
		try {
			t1 = t.readTable("MPTrack-15.csv");
		} catch (DataIOException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found!");
		}

		Object s;
		int a = 552;

		// Selects distinct parties
		for (int i = 0; i < a; i++) {
			s = t1.get(i, 6);
			if (!l.contains(s)) {
				l.add(s);
			}
		}

		// calculates EQ of parties
		for (int i = 0; i < l.size(); i++) {
			Object abc = l.get(i);
			l_val = new LinkedList<Object>();
			for (int j = 0; j < a; j++) {
				s = t1.get(j, 25);
				if (t1.get(j, 6).equals(abc)) {
					l_val.add(s);
				}
			}
			l_eq.add(average(l_val));
			System.out.println(l.get(i) + " has EQ :" + average(l_val));
		}

		// gives lowest party EQ
		float base = (float) l_eq.get(0);
		int count2 = 0;
		for (int i = 0; i < l_eq.size(); i++) {
			float abc = (float) l_eq.get(i);
			if (abc < base) {
				count2 = i;
				base = abc;
			}
		}
		System.out.println("\n" + base + " is lowest party EQ of "
				+ l.get(count2));
	}
}
