package view;

import java.awt.Color;

/*
 * Description: This is an enum class 
 * that creates and holds all the colours 
 * used in the website
 */

public enum WebsiteColours {

	// Colours
	BRIGHTORANGE(new Color(255, 165, 0)),
	BURNTORANGE(new Color(204, 85, 0)),
	LIGHTORANGE(new Color(253, 169, 72)),
	PLACEHOLDERORANGE(new Color (207, 123, 27)),
	OFFWHITE(new Color(255, 253, 253)),
	DARKGREEN(new Color(2, 48, 32)),
	DARKRED(new Color(139, 0, 0));

	// store specificed colour
	private final Color guiColor;

	// Constructor
	WebsiteColours(Color guiColor) {
		this.guiColor = guiColor;
	}

	// Getter method
	public Color getWebsiteColor() {
		return guiColor;
	}
}
