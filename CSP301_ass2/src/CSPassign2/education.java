package CSPassign2;

import java.util.LinkedList;

import prefuse.data.Table;
import prefuse.data.io.CSVTableReader;
import prefuse.data.io.DataIOException;

public class education {

	/**
	 * @param args
	 */

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 494;
		LinkedList<Object> l = new LinkedList<Object>();
		LinkedList<Object> l_eq = new LinkedList<Object>();
		LinkedList<Object> l_val;
		Table t1 = new Table();
		CSVTableReader t = new CSVTableReader();
		try {
			t1 = t.readTable("newfile.csv");
		} catch (DataIOException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found!");
		}
		Object s, s1;
		for (int i = 0; i < a; i++) {
			s = t1.get(i, 6);
			if (!l.contains(s)) {
				// System.out.println(s);
				l.add(s);
			}
		}
		for (int i = 0; i < l.size(); i++) {
			Object abc = l.get(i);
			int count = 0;
			l_val = new LinkedList<Object>();
			for (int j = 0; j < a; j++) {
				s = t1.get(j, 25);
				if (t1.get(j, 6).equals(abc)) {
					l_val.add(s);
					count++;
				}
			}
			l_eq.add(average(l_val));
			System.out.print("['" + l.get(i) + "'," + average(l_val) + "],");
		}
		s = l_eq.get(0);
		int count = 0;
		for (int i = 0; i < l_eq.size(); i++) {
			Object abc = l_eq.get(i);
			if (((float) abc) < ((float) s)) {
				count = i;
				s = abc;
			}
		}

		// System.out.println(s+" " + );
	}
}
