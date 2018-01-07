package src;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import GUI.Server;

public class Database {
	public static Set<Row> database = new HashSet<Row>();

	
	public static void insertDatabase(ArrayList<Row> insertDB) {
		database.addAll(insertDB);
	}
	
	/**
	 * reset the database
	 * @param paths of source csv files
	 */
	public static void resetDatabase(ArrayList<String> paths) {
		database.clear();
		IOfiles io;
		for (int i=0;i<paths.size();i++) {
			io =new IOfiles(paths.get(i));
			if(isDBfile(io)) {
				String nextRow = io.readLine();
				String[] sRow;
				while (nextRow != null && !nextRow.equals("")) {
					sRow = nextRow.split(",");
					int countWifi = Integer.parseInt(sRow[5]);
					Row r = new Row();
					for (int j = 0; j < countWifi; i++) {
						WiFi w = new WiFi(sRow[1], sRow[7 + (4 * j)], sRow[6 + (4 * j)], sRow[8 + (4 * j)], sRow[3], sRow[2],
								sRow[4], sRow[9 + (4 * j)], sRow[0]);
						r.add(w);
					}
					database.add(r);
					nextRow = io.readLine();
				}
			}else {
				File csvF = new File(paths.get(i));
				ArrayList<File> csvFiles = new ArrayList<File>();
				if (csvF.isDirectory())
					csvFiles = getCSVs(csvF);
				else
					csvFiles.add(csvF);
				Row allWifis = new Row(csvFiles);
				ResultFile.result.clear();
				ResultFile result = new ResultFile();
				result.rowsGroupByTimeModel(allWifis.getWifis());
				result.top10fromAnyGroup();
				database.addAll(ResultFile.result);
			}
			io.close();
		}
	}
	private static boolean isDBfile(IOfiles io) {
		String[] headers= io.readLine().split(",");
		return headers.length==46;
	}
	private static ArrayList<File> getCSVs(File folder) {
		ArrayList<File> ans = new ArrayList<File>();

		for (File currentF : folder.listFiles()) {
			if (currentF.isDirectory())
				ans.addAll(getCSVs(currentF));
			else if (currentF.getName().contains(".csv"))
				ans.add(currentF);
		}

		return ans;
	}
}
