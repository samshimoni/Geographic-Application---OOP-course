package GUI;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import src.AlgorithmI;
import src.AvgMacPoint;
import src.Database;

public class AlgorithmIServer {
	
	public static AvgMacPoint algo1(HttpExchange request) throws IOException {
		String mac = request.getRequestURI().getQuery();
		AlgorithmI alg =new AlgorithmI(Database.database,mac);
		return alg.getAvgMacPoint();
	}
}
