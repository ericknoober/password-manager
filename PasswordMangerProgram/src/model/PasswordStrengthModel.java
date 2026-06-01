package model;

import me.gosimple.nbvcxz.Nbvcxz;
import me.gosimple.nbvcxz.scoring.Result;
import me.gosimple.nbvcxz.scoring.TimeEstimate;

/*
 * Description: This is the password strength model class that
 * gets the passwod strength using the Nbvcxz method
 * 
 * https://github.com/GoSimpleLLC/nbvcxz
 */

public class PasswordStrengthModel {

	// puts the time to crack password into a string
	String timeToCrackPassword;

	// API tools
	public Nbvcxz strengthTool;
	public Result result;

	public PasswordStrengthModel() {

		// using english language
		// and english dictionary
		// default settings
		strengthTool = new Nbvcxz();

	}

	// sets the password strength
	public void setPassword(String password) {

		// get password strength
		result = strengthTool.estimate(password);
	}

	// gets the password score as an int
	public int getPasswordScore() {

		// put password strength on a scale of 0-4
		return result.getBasicScore();

	}

	// gets the password score and puts it on scale
	// 0-4
	public String getPasswordScoreString() {

		// put password strength on a scale of 0-4
		if (result.getBasicScore() == 0) {
			return "Very Weak";
		} else if (result.getBasicScore() == 1) {
			return "Weak";
		} else if (result.getBasicScore() == 2) {
			return "Good";
		} else if (result.getBasicScore() == 3) {
			return "Strong";
		} else {
			return "Very Strong";
		}

	}

	// gets the password cracks time
	public String getPasswordCrackTime() {
		// offline attack, fast hash, many cores
		// 10B guesses per second
		return timeToCrackPassword = TimeEstimate.getTimeToCrackFormatted(result, "OFFLINE_BCRYPT_12");
	}

}
