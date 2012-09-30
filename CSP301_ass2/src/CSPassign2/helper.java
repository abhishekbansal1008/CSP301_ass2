package CSPassign2;

import java.util.LinkedList;
import prefuse.data.Table;
import prefuse.data.io.CSVTableReader;
import prefuse.data.io.DataIOException;

public class helper {

	/**
	 * @param args
	 * @throws DataIOException
	 */
	public static void main(String[] args) throws DataIOException {
		int a = 551;
		Table t1 = new Table();
		CSVTableReader t = new CSVTableReader();
		t1 = t.readTable("MPTrack-15.csv");
		Object s;
		for (int i = 0; i < a; i++) {
			System.out.print("['" + t1.getString(i, 0) + "','"
					+ t1.getString(i, 4) + "','" + t1.getString(i, 5) + "','"
					+ t1.getString(i, 6) + "','" + t1.getString(i, 7) + "','"
					+ t1.getString(i, 8) + "','" + t1.getString(i, 9) + "',"
					+ t1.getInt(i, 11) + "," +  t1.get(i, 25) + "],");
		}

	}

}
