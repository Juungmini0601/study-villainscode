package io.jungmini.http;

/**
 * @author    : kimjungmin
 * Created on : 2025. 4. 8.
 */
public class HTTPMain {
	// curl http://localhost:9999
	public static void main(String[] args) {
		try {
			new HttpServer(9999).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
