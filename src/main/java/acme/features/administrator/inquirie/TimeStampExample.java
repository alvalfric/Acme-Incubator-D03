
package acme.features.administrator.inquirie;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStampExample {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");


	public static void main(final String[] args) {

		//method 1
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(timestamp);

		//method 2 - via Date
		Date date = new Date();
		System.out.println(new Timestamp(date.getTime()));

		//return number of milliseconds since January 1, 1970, 00:00:00 GMT
		System.out.println(timestamp.getTime());

		//format timestamp
		System.out.println(TimeStampExample.sdf.format(timestamp));

	}

}
