import prefuse.data.Table;
import prefuse.data.io.CSVTableReader;
import prefuse.data.io.DataIOException;

public class MPRating {

	/**
	 * Calculates MP rating of each member of parliament on the basis of the
	 * education, attendance, debates participated, questions raised and bills
	 * passed.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// reads file through csv table reader
		Table t1 = new Table();
		CSVTableReader t = new CSVTableReader();
		try {
			t1 = t.readTable("newfile.csv");
		} catch (DataIOException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found!!!");
		}
		double atten;
		int a = 494;
		int debate, bill, ques, edu;
		double debr, billr, quesr, attenr, edur; // variables for individual
													// ratings
		for (int i = 0; i < a; i++) {
			debate = (int) t1.get(i, 12);
			bill = (int) t1.get(i, 13);
			ques = (int) t1.get(i, 14);
			atten = (double) t1.get(i, 15);
			edu = (int) t1.get(i, 25);
			// calculating debate rating
			// rating decided by giving ranges based on data distribution
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
			// calculating questions rating
			// decided through ranges
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
			// calculating bills rating
			// decided through ranges
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
			// calculating education ratings
			// decided by education level of each MP

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
			// calculating attendance rating
			// decided by the difference between MP attendance and average
			// attendance
			{
				attenr = (int) (3 + (10 * (atten - 0.77)));
				if (attenr < 0) {
					attenr = 0;
				}
			}

			// Generates total rating by generating weighted mean of each
			// individual rating
			// Weights - education - 0.8
			// debate - 1.2
			// bills passed - 1.4
			// attendance - 1.5
			// questions raised - 1.1
			// Each weight decided by their relevance in the activity of the MP.
			// So an MP who is not highly educated but has a high attendance and
			// more debates is rated more than the MP who is highly educated but
			// neglects his parliamentary duties and skips the assembly meetings

			double sum = (0.8 * edur) + (1.2 * debr) + (1.4 * billr)
					+ (1.1 * quesr) + (1.5 * attenr);
			double rate = sum / 6.0;
			System.out.println((Math.round(rate * 100)) / 100.0);
		}
	}

}
