import java.util.LinkedList;

import prefuse.data.Table;
import prefuse.data.io.CSVTableReader;
import prefuse.data.io.DataIOException;

public class Spreader {

	
	@SuppressWarnings("rawtypes")
	public static float average(LinkedList l) {
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

		return ((f / len));

	}

	/**
	 * Method used to create spreadsheets for data visualizations
	 * 
	 * @author Abhishek bansal
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LinkedList<Object> l1 = new LinkedList<Object>();
		LinkedList<Object> l = new LinkedList<Object>();
		// LinkedList<Object> l_age;
		LinkedList<Object> l_debate;
		LinkedList<Object> l_bills;
		LinkedList<Object> l_ques;
		LinkedList<Object> l_atten;

		// reads file
		Table t1 = new Table();
		CSVTableReader t = new CSVTableReader();
		try {
			t1 = t.readTable("newfile.csv");
		} catch (DataIOException e) {
			// TODO Auto-generated catch block
			System.out.println("File not Found!!");
		}

		int a = 494;

		// Selects distinct parties
		Object s, s1, s2, s3, s4;
		for (int i = 0; i < a; i++) {
			s = t1.get(i, 6);
			if (!l1.contains(s)) {
				l1.add(s);
			}

		}

		// counts number of occurrences in a range
		int count = 0;
		for (int j = 0; j < a; j++) {
			int x = (int) t1.get(j, 14);
			if (x > 250 && x <= 400) {
				count++;
			}

		}
		System.out.println(count);

		// code for generating average value of a column
		for (int i = 0; i < l.size(); i++) {
			Object abc = l.get(i);

			l_debate = new LinkedList<Object>();
			l_bills = new LinkedList<Object>();
			l_ques = new LinkedList<Object>();
			l_atten = new LinkedList<Object>();
			for (int j = 0; j < a; j++) {
				// s = t1.get(j, 25);
				s1 = t1.get(j, 4);
				s2 = t1.get(j, 5);
				s3 = t1.get(j, 6);
				s4 = t1.get(j, 7);
				if (t1.get(j, 3).equals(abc)) {
					// System.out.println(abc);
					// l_age.add(s);
					l_debate.add(s1);
					l_bills.add(s2);
					l_ques.add(s3);
					l_atten.add(s4);
				}
			}
			// System.out.println(abc);
			// System.out.println(average(l_bills));
		}
	}

}
