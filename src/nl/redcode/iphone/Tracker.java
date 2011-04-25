package nl.redcode.iphone;

import java.io.File;
import java.util.List;

import nl.redcode.iphone.kml.KMLGenerator;
import nl.redcode.iphone.sqllite.DataPoint;
import nl.redcode.iphone.sqllite.GeoDataExtractor;

/**
 * Main class
 * 
 * TODO: This could use a nice GUI...!
 * TODO: Also: iPhone backups are in default directories, why not check those and let the user pick one if found.
 * 
 * @author Roy van Rijn
 *
 */
public class Tracker {

	public static void main(String[] args) throws Exception {
		if(args == null || args.length != 1) {
			System.out.println("Usage: ");
			System.out.println("java -jar iPhoneJTrack.jar \"<location of consolidated.db file>\"");
			System.out.println("");
			System.out.println("For example:");
			System.out.println("java -jar iPhoneJTrack.jar \"./consolidated.db\"");
			System.out.println("");
			System.out.println("The output is a file named 'iPhoneData.kml' which can be loaded into Google Earth for example.");
			System.exit(1);
		}

		File sqlLite = new File(args[0].replace("\\", "/"));
		System.out.println(sqlLite);
		
		GeoDataExtractor e = new GeoDataExtractor();
		List<DataPoint> geoData = e.extractGeoData(sqlLite);
		
		KMLGenerator kmlGenerator = new KMLGenerator();
		File kmlFile = new File("iPhoneData.kml");
		kmlGenerator.generateKML(kmlFile, geoData);
		
		System.out.println("Generated KML file at " + kmlFile.getAbsoluteFile());
	}
}
