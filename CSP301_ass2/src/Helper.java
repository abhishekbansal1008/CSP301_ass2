import java.util.LinkedList;
import prefuse.data.Table;
import prefuse.data.io.CSVTableReader;
import prefuse.data.io.DataIOException;

public class Helper {

	/**
	 * Function for calculating the average of a linked list
	 * 
	 * 
	 * @author Abhishek Bansal
	 * @param l
	 *            - list of integer values
	 * @return - rounded off average of the values in the list
	 */
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
	 * Function for calculating the average of a linked list
	 * 
	 * 
	 * @author Abhishek Bansal
	 * @param l
	 *            - list of float values
	 * @return - average of the values in the list
	 */
	@SuppressWarnings("rawtypes")
	public static float average2(LinkedList l) {
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
	 * Function for calculating the average attendance of a linked list
	 * 
	 * 
	 * @author Abhishek Bansal
	 * @param l
	 *            - list of float values
	 * @return - average of the values in the list
	 */
	@SuppressWarnings("rawtypes")
	public static int average3(LinkedList l) {
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

	// helps in generating data and testing blocks of code for other java files
	public static void main(String[] args) throws DataIOException {

		int a = 494;
		LinkedList<Object> l = new LinkedList<Object>();
		LinkedList<Object> l_age;
		LinkedList<Object> l_debate;
		LinkedList<Object> l_bills;
		LinkedList<Object> l_ques;
		LinkedList<Object> l_atten;
		Table t1 = new Table();
		CSVTableReader t = new CSVTableReader();
		t1 = t.readTable("newfile.csv");
		Object s, s1, s2, s3, s4;
		for (int i = 0; i < a; i++) {
			s = t1.get(i, 6);
			if (!l.contains(s)) {
				// System.out.println(s);
				l.add(s);
			}
		}
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
				if (t1.get(j, 6).equals(abc)) {
					l_age.add(s);
					l_debate.add(s1);
					l_bills.add(s2);
					l_ques.add(s3);
					l_atten.add(s4);

				}
			}
			System.out.print("['" + l.get(i) + "'," + average3(l_atten) + "],");

			// System.out.println("['" + abc + "'," + count + "],");

			// System.out.println("female - " + average(lf));

		}
	}
}
