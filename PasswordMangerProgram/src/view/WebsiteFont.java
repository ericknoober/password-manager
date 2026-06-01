package view;

import java.awt.Font;
/*
 * Description: This is an enum class 
 * that creates and holds all the fonts 
 * used in the website
 * 
 * https://docs.oracle.com/javase/8/docs/api/java/awt/Font.html
 */

public enum WebsiteFont {

	// Fonts
	PasswordFont(new Font("MONOSPACED", Font.PLAIN, 50)), 
	LabelFont(new Font("Dialog", Font.PLAIN, 20)),
	KyLockFont(new Font("Arial", Font.PLAIN, 20));
	// store specificed colour
	private final Font WebsiteFont;

	// Constructor
	WebsiteFont(Font WebsiteFont) {
		this.WebsiteFont = WebsiteFont;
	}

	// Getter method
	public Font getWebsiteFont() {
		return WebsiteFont;
	}
	
	//allows the user to use a certain font size
	public Font getDynamicFont(int characters) {
		
		//takes a float arugment to specifically change the font size
		return WebsiteFont.deriveFont((float)characters);
	}
}
