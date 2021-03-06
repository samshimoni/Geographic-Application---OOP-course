package Tests;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import BuisnessLogic.Row;
import BuisnessLogic.WiFi;

public class RowTest {

	@Test
	public void testRow() {
		Row r = new Row();
		assertTrue(r.getWifis() != null);
		assertTrue(r.getWifis().size() == 0);
	}

	@Test
	public void testRowArrayListOfFile() {
		File f = new File("input files\\Wiggle WIFI\\WigleWifi_20171027162905.csv");
		ArrayList<File> af = new ArrayList<File>();
		af.add(f);
		Row r = new Row(af);

		int counter = -2;
		try {
			FileReader fr = new FileReader(f.getPath());
			BufferedReader br = new BufferedReader(fr);
			while (br.readLine() != null) {
				counter++;
			}
			br.close();
			fr.close();
			assertTrue(counter == r.getWifis().size());
		} catch (IOException ex) {
			System.out.print("Error reading file\n" + ex);
			System.exit(2);
		}
	}

	@Test
	public void testSetRow() {
		ArrayList<WiFi> toDisplay = new ArrayList<>();
		toDisplay.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-26 14:07:34,11,-85,1.10432894787016,35.20499025104117,400.1184746940897,16,BT",
				"SM-G920F"));
		toDisplay.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-26 14:07:34,11,-85,2.10432894787016,35.20499025104117,500.1184746940897,16,WIFI",
				"SM-G920F"));
		toDisplay.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-26 14:07:34,11,-85,3.10432894787016,35.20499025104117,600.1184746940897,16,WIFI",
				"SM-G920F"));
		toDisplay.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-28 14:07:34,11,-85,4.10432894787016,35.20499025104117,700.1184746940897,16,WIFI",
				"SM-G920F"));
		toDisplay.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-28 14:07:34,11,-85,4.10432894787016,35.20499025104117,700.1184746940897,16,WIFI",
				"SM-G920F"));
		toDisplay.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-28 14:07:34,11,-85,4.10432894787016,35.20499025104117,700.1184746940897,16,WIFI",
				"Iphone"));
		toDisplay.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-28 14:07:34,11,-85,5.10432894787016,35.20499025104117,688.1184746940897,16,BT",
				"SM-G920F"));

		Row r = new Row();
		r.setRow(toDisplay);

		assertTrue(r.getWifis().size() == toDisplay.size());

		int size = r.getWifis().size();
		r.setRow(null);
		assertTrue(r.getWifis().size() == size);
	}

	@Test
	public void testGetRow() {
		ArrayList<WiFi> toDisplay = new ArrayList<>();
		toDisplay.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-26 14:07:34,11,-85,1.10432894787016,35.20499025104117,400.1184746940897,16,BT",
				"SM-G920F"));
		toDisplay.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-26 14:07:34,11,-85,2.10432894787016,35.20499025104117,500.1184746940897,16,WIFI",
				"SM-G920F"));
		toDisplay.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-26 14:07:34,11,-85,3.10432894787016,35.20499025104117,600.1184746940897,16,WIFI",
				"SM-G920F"));
		toDisplay.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-28 14:07:34,11,-85,4.10432894787016,35.20499025104117,700.1184746940897,16,WIFI",
				"SM-G920F"));
		toDisplay.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-28 14:07:34,11,-85,4.10432894787016,35.20499025104117,700.1184746940897,16,WIFI",
				"SM-G920F"));
		toDisplay.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-28 14:07:34,11,-85,4.10432894787016,35.20499025104117,700.1184746940897,16,WIFI",
				"Iphone"));
		toDisplay.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-28 14:07:34,11,-85,5.10432894787016,35.20499025104117,688.1184746940897,16,BT",
				"SM-G920F"));

		Row r = new Row();
		r.setRow(toDisplay);

		assertTrue(r.getWifis().size() == toDisplay.size());
	}

	@Test
	public void testAdd() {
		Row r = new Row();

		int size = r.getWifis().size();
		r.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-28 14:07:34,11,-85,5.10432894787016,35.20499025104117,688.1184746940897,16,BT",
				"SM-G920F"));
		assertTrue(r.getWifis().size() == size + 1);

		size = r.getWifis().size();
		r.add(null);
		assertTrue(r.getWifis().size() == size);

	}

	@Test
	public void testGetWiFi() {
		ArrayList<WiFi> toDisplay = new ArrayList<>();
		toDisplay.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-26 14:07:34,11,-85,1.10432894787016,35.20499025104117,400.1184746940897,16,BT",
				"SM-G920F"));
		toDisplay.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-26 14:07:34,11,-85,2.10432894787016,35.20499025104117,500.1184746940897,16,WIFI",
				"SM-G920F"));
		toDisplay.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-26 14:07:34,11,-85,3.10432894787016,35.20499025104117,600.1184746940897,16,WIFI",
				"SM-G920F"));
		toDisplay.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-28 14:07:34,11,-85,4.10432894787016,35.20499025104117,700.1184746940897,16,WIFI",
				"SM-G920F"));
		toDisplay.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-28 14:07:34,11,-85,4.10432894787016,35.20499025104117,700.1184746940897,16,WIFI",
				"SM-G920F"));
		toDisplay.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-28 14:07:34,11,-85,4.10432894787016,35.20499025104117,700.1184746940897,16,WIFI",
				"Iphone"));
		toDisplay.add(new WiFi(
				"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-28 14:07:34,11,-85,5.10432894787016,35.20499025104117,688.1184746940897,16,BT",
				"SM-G920F"));

		Row r = new Row();
		r.setRow(toDisplay);
		boolean check = true;
		for (int i = 0; i < r.size(); i++) {
			if (!toDisplay.get(i).equals(r.getWiFi(i))) {
				check = false;
				continue;
			}
			assertTrue(check);
		}
	}

	
	  @Test 
	  public void testSize() { 
		  ArrayList<WiFi> toDisplay = new ArrayList<>();
			toDisplay.add(new WiFi(
					"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-26 14:07:34,11,-85,1.10432894787016,35.20499025104117,400.1184746940897,16,BT",
					"SM-G920F"));
			toDisplay.add(new WiFi(
					"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-26 14:07:34,11,-85,2.10432894787016,35.20499025104117,500.1184746940897,16,WIFI",
					"SM-G920F"));
			toDisplay.add(new WiFi(
					"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-26 14:07:34,11,-85,3.10432894787016,35.20499025104117,600.1184746940897,16,WIFI",
					"SM-G920F"));
			toDisplay.add(new WiFi(
					"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-28 14:07:34,11,-85,4.10432894787016,35.20499025104117,700.1184746940897,16,WIFI",
					"SM-G920F"));
			toDisplay.add(new WiFi(
					"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-28 14:07:34,11,-85,4.10432894787016,35.20499025104117,700.1184746940897,16,WIFI",
					"SM-G920F"));
			toDisplay.add(new WiFi(
					"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-28 14:07:34,11,-85,4.10432894787016,35.20499025104117,700.1184746940897,16,WIFI",
					"Iphone"));
			toDisplay.add(new WiFi(
					"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-28 14:07:34,11,-85,5.10432894787016,35.20499025104117,688.1184746940897,16,BT",
					"SM-G920F"));

			Row r = new Row();
			r.setRow(toDisplay);
			
			assertTrue(r.size()==toDisplay.size());
		 }
	  
	  @Test 
	  public void testIsEmpty() { 
		  Row r = new Row();
		  assertTrue(r.isEmpty());
		  
		  r.add(new WiFi(
					"24:c9:a1:36:52:f8,Ariel_University,[ESS],2017-10-28 14:07:34,11,-85,5.10432894787016,35.20499025104117,688.1184746940897,16,BT",
					"SM-G920F"));
		  assertTrue(!r.isEmpty());
		}
	 
}
