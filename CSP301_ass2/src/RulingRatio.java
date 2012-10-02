import java.util.LinkedList;

import prefuse.data.Table;
import prefuse.data.io.CSVTableReader;
import prefuse.data.io.DataIOException;

public class RulingRatio {

	public static void rulingratio(LinkedList<?> l) {
		int j = l.size();
		double count = 0.0;
		Object s = new Object();
		LinkedList<Object> mem = new LinkedList<>();
		for (int i = 0; i < j; i++) {
			s = l.get(i);
			if (!mem.contains(s)) {
				// System.out.print(s + "  ");
				mem.add(s);
			}
		}
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
		double ratio = count / j;
		System.out.println((Math.round(ratio * 100)) / 100.0);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 552;
		LinkedList<Object> l = new LinkedList<Object>();
		LinkedList<Object> l_partyofstate;
		Table t1 = new Table();
		CSVTableReader t = new CSVTableReader();
		try {
			t1 = t.readTable("MPTrack-15.csv");
		} catch (DataIOException e) {
			// TODO Auto-generated catch block
			System.out.println("File not Found!!");
		}
		Object s;
		for (int i = 0; i < a; i++) {
			s = t1.get(i, 4);
			if (!l.contains(s)) {
				l.add(s);
			}
		}
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
