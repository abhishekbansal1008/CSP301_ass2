import java.util.LinkedList;

import prefuse.data.Table;
import prefuse.data.io.CSVTableReader;
import prefuse.data.io.DataIOException;

public class Statewise {

	// function for averages
	@SuppressWarnings("rawtypes")
	public static int average(LinkedList l) {
		double sum = 0;
		float len = l.size();
		if (len == 0) {
			return 0;
		}
		for (int i = 0; i < len; i++) {
			double t = (double) l.get(i);
			sum = sum + t;
		}
		float f = (float) sum;

		return (Math.round(f * 100 / len));

	}

	/**
	 * Distinguishes data on basis of states
	 * 
	 * @author Abhishek Bansal
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 552;
		LinkedList<Object> l_major = new LinkedList<Object>();
		LinkedList<Object> l = new LinkedList<Object>();
		LinkedList<Object> l_age;
		LinkedList<Object> l_debate;
		LinkedList<Object> l_bills;
		LinkedList<Object> l_ques;
		LinkedList<Object> l_atten;
		Table t1 = new Table();
		CSVTableReader t = new CSVTableReader();
		try {
			t1 = t.readTable("MPTrack-15.csv");
		} catch (DataIOException e) {
			// TODO Auto-generated catch block
			System.out.println("File not Found!!");
		}

		// Selects distinct states
		Object s, s1, s2, s3, s4;
		for (int i = 0; i < a; i++) {
			s = t1.get(i, 4);
			if (!l.contains(s)) {
				l.add(s);
			}
		}
		
		
		for (int i = 0; i < l.size(); i++) {
			Object abc = l.get(i);
			l_atten = new LinkedList<Object>();
			int count = 0;
			
			// counts number of MPs of each state
			for (int j = 0; j < a; j++) {
				s = t1.get(j, 11);
				if (t1.get(j, 4).equals(abc)) {
					count++;
				}
			}
			System.out.println(abc + " has " + count + " members. ");

			// Gives major states (having more than 20 MPs)
			if (count > 20) {
				l_major.add(abc);
			}
		}

		System.out.println("Major states : " + l_major);

		// Code for calculating average value of a column

		for (int i = 0; i < l.size(); i++) {
			Object abc = l.get(i);
			l_age = new LinkedList<Object>();
			l_debate = new LinkedList<Object>();
			l_bills = new LinkedList<Object>();
			l_ques = new LinkedList<Object>();
			l_atten = new LinkedList<Object>();
			for (int j = 0; j < a; j++) {
				s = t1.get(j, 11);
				s1 = t1.get(j, 12);
				s2 = t1.get(j, 13);
				s3 = t1.get(j, 14);
				s4 = t1.get(j, 15);
				if (t1.get(j, 4).equals(abc)) {
					l_age.add(s);
					l_debate.add(s1);
					l_bills.add(s2);
					l_ques.add(s3);
					l_atten.add(s4);
				}
			}
			// System.out.println(average(l_atten));
		}
		// System.out.println("Average attendance - " + average(l_atten));
	}
}
