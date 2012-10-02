import java.util.Scanner;

import prefuse.data.Table;
import prefuse.data.io.CSVTableReader;
import prefuse.data.io.DataIOException;
import prefuse.data.io.DelimitedTextTableReader;

public class t_tests {

	/**
	 * member variables to store data table
	 */
	private static String m_file = "newfile.csv";
	private static Table m_table = null;
	
	/**
	 * size of the data table (after removing N/A)
	 */
	private static int n = 494;
	
	/**
	 * member variables to store the T-tables
	 */
	private static String m_t1File = "t_table(one tail).tsv";
	private static String m_t2File = "t_table(two tail).tsv";
	private static Table m_t1Table = null;
	@SuppressWarnings("unused")
	private static Table m_t2Table = null;
		
	/**
	 * member variables for the two sample sets
	 */
	private static float[] m_sample1 = new float[n];
	private static int m_n1 = 0;
	private static float m_x1 = 0;
	private static double m_s1 = 0;
	private static float[] m_sample2 = new float[n];
	private static int m_n2 = 0;
	private static float m_x2 = 0;
	private static double m_s2 = 0;

	
	/**
	 * To calculate average of all the values in a column
	 * 
	 * @param col - column of the table
	 * @return - average of the value in col
	 */
	public static float avg(int col) {
		float sum = 0;

		for (int i = 0; i < n; i++) {
			sum += m_table.getFloat(i, col);
		}

		return (sum / n);
	}

	
	/**
	 * To create two samples using the threshold as average value of col1
	 * Sample-1 : points with value below the threshold value
	 * Sample-2 : points with value above the threshold value
	 * 
	 * @param col1 - column used to separate two samples
	 * @param col2 - column whose values are stored in the sample sets
	 */
	public static void create_samples_avg(int col1, int col2) {
		float avg = avg(col1);

		for (int i = 0; i < n; i++) {
			if (m_table.getFloat(i, col1) < avg)
				m_sample1[m_n1++] = m_table.getFloat(i, col2);
			else
				m_sample2[m_n2++] = m_table.getFloat(i, col2);
		}
	}

	
	/**
	 * To create two samples based on the gender
	 * Sample-1 : all male MPs
	 * Sample-2 : all female MPs
	 * 
	 * @param col - column whose values are stored in the sample sets
	 */
	public static void create_samples_gender(int col) {
		for (int i = 0; i < n; i++) {
			if (m_table.get(i, 8).equals("Male"))
				m_sample1[m_n1++] = m_table.getFloat(i, col);
			else
				m_sample2[m_n2++] = m_table.getFloat(i, col);
		}
	}

	
	/**
	 * To create two samples based on the alliance
	 * Sample-1 : all MPs from alliance other than UPA or NDA
	 * Sample-2 : all MPs from UPA or NDA
	 * 
	 * @param col - column whose values are stored in the sample sets
	 */
	public static void create_samples_alliance(int col) {
		for (int i = 0; i < n; i++) {
			if (m_table.get(i, 7).equals("UPA")
					| m_table.get(i, 7).equals("NDA"))
				m_sample2[m_n2++] = m_table.getFloat(i, col);
			else
				m_sample1[m_n1++] = m_table.getFloat(i, col);
		}
	}

	
	/**
	 * To create two samples based on the size of the state
	 * Sample-1 : MPs from large states, i.e., states with more than 20 seats
	 * Sample-2 : MPs from small states
	 * 
	 * @param col - column whose values are stored in the sample sets
	 */
	public static void create_samples_state(int col) {
		for (int i = 0; i < n; i++) {
			String state = (String) m_table.get(i, 4);
			if (state.equals("Tamil Nadu") | state.equals("Maharashtra")
					| state.equals("West Bengal") | state.equals("Karnataka")
					| state.equals("Uttar Pradesh")
					| state.equals("Andhra Pradesh")
					| state.equals("Rajasthan") | state.equals("Bihar")
					| state.equals("Madhya Pradesh") | state.equals("Gujarat"))
				m_sample1[m_n1++] = m_table.getFloat(i, col);
			else
				m_sample2[m_n2++] = m_table.getFloat(i, col);
		}
	}

	
	/**
	 * To create two samples based on the region
	 * Sample-1 : MPs from South India
	 * Sample-2 : MPs from North India
	 * 
	 * @param col - column whose values are stored in the sample sets
	 */
	public static void create_samples_region(int col) {
		for (int i = 0; i < n; i++) {
			String state = (String) m_table.get(i, 4);
			if (state.equals("Tamil Nadu") | state.equals("Karnataka") | 
				state.equals("Andhra Pradesh") | state.equals("Kerala") | 
				state.equals("Lakshadweep") | state.equals("Puducherry") | 
				state.equals("Andaman and Nicobar Islands") | state.equals("Goa"))
				m_sample1[m_n1++] = m_table.getFloat(i, col);
			
			else if(state.equals("Jammu and Kashmir") | state.equals("Himachal Pradesh") | 
				state.equals("Uttarakhand") | state.equals("Haryana") | state.equals("Punjab") | 
				state.equals("Rajasthan") | state.equals("Uttar Pradesh") | state.equals("Bihar") | 
				state.equals("Jharkhand") | state.equals("Chhattisgarh") | 
				state.equals("Madhya Pradesh") | state.equals("Delhi"))
				m_sample2[m_n2++] = m_table.getFloat(i, col);
		}
	}

	
	/**
	 * To calculate the standard deviation of a given sample set
	 * 
	 * @param sample - sample set in form of array
	 * @param n - size of sample
	 * @param x - mean of sample
	 * @return - standard deviation of sample
	 */
	public static double std_dev(float[] sample, int n, float x) {
		float variance = 0;

		for (int i = 0; i < n; i++) {
			variance += (sample[i] - x) * (sample[i] - x);
		}
		variance = variance / n;

		return Math.sqrt(variance);
	}
	
	
	/**
	 * To calculate mean of a sample set
	 * 
	 * @param sample - sample set in form of array
	 * @param n - size of sample
	 * @return - means of sample
	 */
	public static float mean(float[] sample, int n) {
		float sum=0;
		
		for(int i=0;i<n;i++)
			sum += sample[i];
		
		return (sum/n);
	}

	
	/**
	 * To calculate standard error of the difference in sample means 
	 * 
	 * @param s1 - standard deviation of sample-1
	 * @param s2 - standard deviation of sample-2
	 * @param n1 - size of sample-1
	 * @param n2 - size of sample-2
	 * @return - standard error of the difference in sample means
	 */
	public static double std_error(double s1, double s2, int n1, int n2 ) {
		return Math.sqrt((s1 * s1 / n1) + (s2 * s2 / n2));
	}
	
	
	/**
	 * To calculate the T-value of the Welch t-test
	 * 
	 * @param x1 - mean of sample-1
	 * @param x2 - mean of sample-2
	 * @param se - standard error of the difference in sample means
	 * @return - t-value
	 */
	public static double t_value(float x1, float x2, double se) {
		return ((x1 - x2) / se);
	}
	
	
	/**
	 * To calculate the degrees of freedom of t-value
	 * @param s1 - standard deviation of sample-1
	 * @param s2 - standard deviation of sample-2
	 * @param n1 - size of sample-1
	 * @param n2 - size of sample-2
	 * @param se - standard error in the difference of sample means
	 * @return - degrees of freedom of t-value
	 */
	public static long degree_of_freedom(double s1, double s2, int n1, int n2, double se) {
		return Math.round( Math.pow(se, 4) / ((Math.pow(s1, 4) / (n1 * n1 * (n1 - 1))) + (Math.pow(s2, 4) / (n2
				* n2 * (n2 - 1)))) );
	}
	
	
	public static void main(String[] args) throws DataIOException {

		m_table = new CSVTableReader().readTable(m_file);
		m_t1Table = new DelimitedTextTableReader().readTable(m_t1File);
		m_t2Table = new DelimitedTextTableReader().readTable(m_t2File);

		System.out.println("Select the hypothesis you want to test:\n"
						+ "1. More experienced MPs maintain better attendance.\n"
						+ "2. MPs from small states maintain better attendance.\n"
						+ "3. MPs from UPA(in power) or NDA(opposition) maintain better attendance.\n"
						+ "4. MPs from North India maintain better attendance than MPs from South India.\n"
						+ "5. More educated MPs maintain better attendance.\n"
						+ "6. Female MPs maintain better attendance.\n"
						+ "7. MPs with greater number of debates maintain better attendance.\n"
						+ "8. MPs with greater number of questions maintain better attendance.\n"
						+ "9. Female MPs are better educated than male MPs.\n"
						+ "10. MPs from small states are better educated.\n"
						+ "11. MPs from UPA(in power) or NDA(opposition) are better educated.\n"
						+ "12. MPs with greater number of questions are better educated.\n"
						+ "13. MPs with greater number of debates are better educated.\n"
						+ "14. MPs from small states participate more in debates.\n"
						+ "15. MPs from UPA/NDA participate more in debates.\n"
						+ "16. MPs with greater number of questions participate more in debates.");

		Scanner c = new Scanner(System.in);
		int choice = c.nextInt();
		c.close();

		switch (choice) {
		case 1:
			create_samples_avg(11, 15);
			break;
		case 2:
			create_samples_state(15);
			break;
		case 3:
			create_samples_alliance(15);
			break;
		case 4:
			create_samples_region(15);
			break;
		case 5:
			create_samples_avg(25, 15);
			break;
		case 6:
			create_samples_gender(15);
			break;
		case 7:
			create_samples_avg(12, 15);
			break;
		case 8:
			create_samples_avg(14, 15);
			break;
		case 9:
			create_samples_gender(25);
			break;
		case 10:
			create_samples_state(25);
			break;
		case 11:
			create_samples_alliance(25);
			break;
		case 12:
			create_samples_avg(12, 25);
			break;
		case 13:
			create_samples_avg(14, 25);
			break;
		case 14:
			create_samples_state(14);
			break;
		case 15:
			create_samples_alliance(14);
			break;
		case 16:
			create_samples_avg(12, 14);
			break;
		}

		

		m_x1 = mean(m_sample1,m_n1);
//		System.out.println("mean of sample-1 = " + m_x1);
		m_x2 = mean(m_sample2,m_n2);
//		System.out.println("mean of sample-2 = " + m_x2);

		m_s1 = std_dev(m_sample1, m_n1, m_x1);
//		System.out.println("standard deviation of sample-1 = " + m_s1);
		m_s2 = std_dev(m_sample2, m_n2, m_x2);
//		System.out.println("standard deviation of sample-2 = " + m_s2);

		double se = std_error(m_s1, m_s2, m_n1, m_n2);
//		System.out.println("standard error of difference in sample means = " + se);
		
		double t = t_value(m_x1, m_x2, se);
		System.out.println("T-value = " + t);

		long df = degree_of_freedom(m_s1, m_s2, m_n1, m_n2, se);
		System.out.println("Degrees of Freedom: " + df);

		if (df <= 200) {
			if (Math.abs(t) > m_t1Table.getFloat((int) df - 1, 1)) {
				System.out.println("Null hypothesis can be rejected.");
				if(t<0)
					System.out.println("Alternate hypothesis is also rejected.");
			}
			else
				System.out.println("Null Hypothesis cannot be rejected.");
		}
		else {
			if (Math.abs(t) > m_t1Table.getFloat(199, 1)) {
				System.out.println("Null hypothesis can be rejected.");
				if(t<0)
					System.out.println("Alternate hypothesis is also rejected.");
			}
			else
				System.out.println("Null Hypothesis cannot be rejected.");
		}
	}

}
