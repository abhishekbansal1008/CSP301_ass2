package CSPassign2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.Table;
import prefuse.data.io.CSVTableReader;

public class creator {

	//member variables
	private static Graph m_graph;
	private static String m_file = "MPTrack-15.csv";
	private static Table m_table = null;

	//member functions
	
	public static void m_setupData() throws FileNotFoundException, IOException {
        try {
            m_table = new CSVTableReader().readTable(m_file);
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(1);
        }
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		//Reads data into the graph
		m_setupData();
//		System.out.println(m_table.getColumnName(6));
		
		FileWriter fw1 = new FileWriter("test.csv", true);
		BufferedWriter bw1 = new BufferedWriter(fw1);
		bw1.write("Attendance, Age, MP Name, State, Political Party, Coalition, Gender\n");
		bw1.close();
		fw1.close();
		
		String[] coalition = {"UPA", "NDA", "OTHERS"};
		for(int i=0;i<552;i++) {
			Random rand = new Random();
//			int attendance = rand.nextInt(100);
//			int age = rand.nextInt(80);
			int s = rand.nextInt(3);
			FileWriter fw2 = new FileWriter("test1.csv", true);
			BufferedWriter bw2 = new BufferedWriter(fw2);
			bw2.write(m_table.get(i,14)+", "+m_table.get(i,10)+", "+m_table.get(i,0)+", "+
					  m_table.get(i,4)+", "+m_table.get(i,6)+", "+coalition[s]+", "+
					  m_table.get(i,7)+"\n");
			bw2.close();
			fw2.close();
		}

	}

}
