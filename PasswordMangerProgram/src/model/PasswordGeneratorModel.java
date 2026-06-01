package model;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.passay.Rule;

/*
 * Description: This is the password generator model class that creates
 * character rules to randomly generate a password using the passay API
 * 
 * https://www.tutorialspoint.com/passay/passay_password_generation.htm
 * https://www.tutorialspoint.com/passay/passay_characterrule.htm
 * https://stackoverflow.com/questions/3591945/copying-to-the-clipboard-in-java/3592022
 * 
 */

public class PasswordGeneratorModel {

	public String createPassword(boolean[] passwordRulesCheck, int characterLength) {

		// creates an arraylist to hold the rules
		ArrayList<Rule> rules = new ArrayList();

		// password rules

		// uppercase
		if (passwordRulesCheck[0]) {
			// includes at least one uppercase character
			rules.add(new CharacterRule(EnglishCharacterData.UpperCase, 1));

		}

		// lowercase
		if (passwordRulesCheck[1]) {
			// includes at least one lowercase character
			rules.add(new CharacterRule(EnglishCharacterData.LowerCase, 1));
		}

		// numbers
		if (passwordRulesCheck[2]) {
			// includes at least one number
			rules.add(new CharacterRule(EnglishCharacterData.Digit, 1));
		}

		// special characters
		if (passwordRulesCheck[3]) {
			// includes at least one special character
			rules.add(new CharacterRule(EnglishCharacterData.Special, 1));
		}

		// initalize password generator
		PasswordGenerator passwordGenerator = new PasswordGenerator();

		//return and create the generated password with the rules and length
		return passwordGenerator.generatePassword(characterLength, rules);
	}

	// allows user to copy password to clipboard
	public void copyToClipboard(String password) {

		// converts string to stringselection
		StringSelection selection = new StringSelection(password);
		// gets clipyboard
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		// pastes string to clipboard
		clipboard.setContents(selection, selection);

	}
}