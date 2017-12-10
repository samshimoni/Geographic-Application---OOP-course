import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.TimePrimitive;
import de.micromata.opengis.kml.v_2_2_0.TimeStamp;
/**
 *responsible of writing the final kml file 
 */
public class KML {
/**
 * creating the kml and sending it to directory
 * @param toDisplay
 */
	public void makeKML(ArrayList<WiFi> toDisplay) {
		Kml kml = new Kml();
		Document doc = kml.createAndSetDocument();
		String time;
		for (int i = 0; i < toDisplay.size(); i++) {
			WiFi w = toDisplay.get(i);
			time = convertTimeFormat(w.getTime());
			TimeStamp ts = new TimeStamp();
			ts.setWhen(time);
			doc.createAndAddPlacemark().withName(w.getSSID()).withOpen(Boolean.TRUE).withTimePrimitive(ts)
					.withDescription("mac: " + w.getMac() + " freq: " + w.getFreq() + " signal: " + w.getSignal())
					.createAndSetPoint().addToCoordinates(Double.parseDouble(w.getLon()),
							Double.parseDouble(w.getLat()), Double.parseDouble(w.getAlt()));
		}
		try {
			kml.marshal(new File("src\\placemarks.kml"));
		} catch (IOException ex) {
			System.out.print("Error reading file\n" + ex);
			System.exit(2);
		}
	}

	public void makeAVG1KML(ArrayList<AvgPoint> toDisplay) {
		Kml kml = new Kml();
		Document doc = kml.createAndSetDocument();
		for (int i = 0; i < toDisplay.size(); i++) {
			AvgPoint p= toDisplay.get(i);
			
			doc.createAndAddPlacemark().withName(p.getSSID()).withOpen(Boolean.TRUE)
					.withDescription("mac: " + p.getMac())
					.createAndSetPoint().addToCoordinates(p.getAvgLon(), p.getAvgLat(), p.getAvgAlt());
		}
		try {
			kml.marshal(new File("src\\AvgPlacemarks.kml"));
		} catch (IOException ex) {
			System.out.print("Error reading file\n" + ex);
			System.exit(2);
		}
	}
	
	/**
	 *converting the time format from yyyy\MM\dd hh:mm:ss to yyyy\MM\dd+T+hh:mm:ss{ google earth format}
	 * @param oldTimeFormat date time in old format yyyy\MM\dd hh:mm:ss
	 * @return the new format yyyy\MM\dd+T+hh:mm:ss
	 */
	private String convertTimeFormat(String oldTimeFormat) {
		String[] dateTime = oldTimeFormat.split(" ");
		return dateTime[0] + 'T' + dateTime[1];
	}
}
