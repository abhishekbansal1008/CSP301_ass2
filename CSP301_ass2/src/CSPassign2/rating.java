package CSPassign2;

import java.util.LinkedList;

import prefuse.data.Table;
import prefuse.data.io.CSVTableReader;
import prefuse.data.io.DataIOException;

public class rating {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a = 494;
		double count =0;
		Table t1 = new Table();
		CSVTableReader t = new CSVTableReader();
		try {
			t1 = t.readTable("newfile.csv");
		} catch (DataIOException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found!!!");
		}
		double atten;
		String name;
		int debate, bill, ques, edu;
		double debr, billr, quesr, attenr, edur;
		for (int i = 0; i < a; i++) {
			name = (String) t1.get(i, 6);
			debate = (int) t1.get(i, 12);
			bill = (int) t1.get(i, 13);
			ques = (int) t1.get(i, 14);
			atten = (double) t1.get(i, 15);
			edu = (int) t1.get(i, 25);
			// debate
			{
				if (debate < 10)
					debr = 0;
				else if (debate < 18)
					debr = 1;
				else if (debate < 25)
					debr = 2;
				else if (debate < 33)
					debr = 3;
				else if (debate < 50)
					debr = 4;
				else
					debr = 5;
			}
			// questions
			{
				if (ques < 25)
					quesr = 0;
				else if (ques < 75)
					quesr = 1;
				else if (ques < 150)
					quesr = 2;
				else if (ques < 250)
					quesr = 3;
				else if (ques < 400)
					quesr = 4;
				else
					quesr = 5;
			}
			// bills
			{
				if (bill < 1)
					billr = 2;
				else if (bill < 4)
					billr = 3;
				else if (bill < 9)
					billr = 4;
				else
					billr = 5;
			}
			// education
			{
				if (edu < 15)
					edur = 0;
				else if (edu < 25)
					edur = 1;
				else if (edu < 50)
					edur = 2;
				else if (edu < 65)
					edur = 3;
				else if (edu < 77)
					edur = 4;
				else
					edur = 5;
			}
			// attendance
			{
				attenr = (int) (3 + (10 * (atten - 0.77)));
				if (attenr < 0) {
					attenr = 0;
				}
			}
			double sum = (0.8 * edur) + (1.2 * debr) + (1.4 * billr) + (1.1 * quesr) + (1.5 * attenr);
			double rate = sum / 6.0;
			count+=rate;
			System.out.println((Math.round(rate*100))/100.0);
		}
		//System.out.println("tot : "+ count/495);
	}

}
