package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;

/*
 * Description: The API grabs the first five letters letters of the hash
 * as a prefix and searches its repository for matches of the 
 * same prefix. It then searches the data with the remaining letters
 * of the hash and determines if your password has been leaked if your
 * hash matches with any of them.
 * 
 * https://haveibeenpwned.com/API/v3#SearchingPwnedPasswordsByRange
 * https://stackoverflow.com/questions/4895523/java-string-to-sha1
 * https://stackoverflow.com/questions/12405159/how-to-get-data-from-a-url
 */

public class BreachScannerModel {

	// keeps a counter on how many times
	// your password has been leaked
	static int leakedAmount = 0;

	// check password requires the HaveIbeenPwned API.
	// it uses the https site to get data and
	// determines whether the password has been preached
	// it also uses the first hash so the password needs
	// to be converted
	public static boolean checkPassword(String password) throws Exception {
		// convert password to hash
		String hash = convertToHash(password);

		// intialize the url
		// grabs the first five letters of the hash as a prefix
		URL pwnedPasswords = new URL("https://api.pwnedpasswords.com/range/" + hash.substring(0, 5));
		// conects java with the url
		URLConnection yc = pwnedPasswords.openConnection();

		// reading data inside the url
		BufferedReader dataReader = new BufferedReader(new InputStreamReader(yc.getInputStream()));

		// holds the read data
		String line;
		// if the read line is not null
		while ((line = dataReader.readLine()) != null) {

			// if the hashes match each other
			if (line.substring(0, 35).equalsIgnoreCase(hash.substring(5))) {
				// gets the amount of leaked times
				leakedAmount = Integer.parseInt(line.substring(36));
				// returns that the password has been leaked
				return true;

			}

		}

		// password has never been leaked
		return false;

	}

	// converts the user's string the hash code for the API
	// converts the users password into a hash using the SHA-1 algorithm
	// to allow the password to entered into the API
	public static String convertToHash(String password) throws Exception {

		// SHA-1 is a cryptographic hash algorithm that encodes the user's
		// password
		MessageDigest md = MessageDigest.getInstance("SHA-1");

		// uses the SHA-1 alogirthm and that requires byte data type
		byte[] digest = md.digest(password.getBytes());

		// converts byte array back to string to put back into the url
		StringBuilder sb = new StringBuilder();
		for (byte b : digest) {
			// format specifier %02x used for hexidecimals
			sb.append(String.format("%02x", b));
		}

		//returns the hash
		return sb.toString();
	}

	public int getLeakedAmount() {

		//reutnrs the leaked amount
		return leakedAmount;

	}

}
