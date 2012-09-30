package CSPassign2;

import java.util.LinkedList;

import prefuse.data.Table;
import prefuse.data.io.CSVTableReader;
import prefuse.data.io.DataIOException;

public class spreader {

	/**
	 * @param args
	 */
	
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 493;
		LinkedList<Object> l1 = new LinkedList<Object>();
		LinkedList<Object> l = new LinkedList<Object>();
		LinkedList<Object> l_age;
		LinkedList<Object> l_debate;
		LinkedList<Object> l_bills;
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

		Object s, s1, s2, s3, s4;
		for (int i = 0; i < a; i++) {
			s = t1.get(i, 6);
			if (!l1.contains(s)) {
				// System.out.println(s);
				l1.add(s);
			}

		}

		for (int i = 0; i < l1.size(); i++) {
			Object abc = l1.get(i);
			int count = 0;
			for (int j = 0; j < a; j++) {
				// int x = (int) t1.get(j, 14);
				if ((t1.get(j, 6).equals(abc))) {
					count++;
				}
			}
			if (count > 12) {
				//System.out.println(abc);
				l.add(abc);
			}
		}
		l.remove("Biju Janata Dal");
	//	System.out.println(l);
		for (int i = 0; i < l.size(); i++) {
			Object abc = l.get(i);
			int count = 0;
			for (int j = 0; j < a; j++) {
				int x = (int) t1.get(j, 14);
				if ( (x>400)&& (t1.get(j, 6).equals(abc))) {
					count++;
				}
			}
			System.out.println(count);
		}
		
		for (int i = 0; i < l.size(); i++) {
			Object abc =l.get(i);
			l_age = new LinkedList<Object>();
			l_debate = new LinkedList<Object>();
			l_bills = new LinkedList<Object>();
			l_ques = new LinkedList<Object>();
			l_atten = new LinkedList<Object>();
			for (int j = 0; j < a; j++) {
				s = t1.get(j, 25);
				s1 = t1.get(j, 12);
				s2 = t1.get(j, 13);
				s3 = t1.get(j, 14);
				s4 = t1.get(j, 15);
				if (t1.get(j, 6).equals(abc)) {
					//System.out.println(abc);
					l_age.add(s);
					l_debate.add(s1);
					l_bills.add(s2);
					l_ques.add(s3);
					l_atten.add(s4);
				}
			}
			//System.out.println(abc);
		//	System.out.println(average(l_atten));
		}
	}

}