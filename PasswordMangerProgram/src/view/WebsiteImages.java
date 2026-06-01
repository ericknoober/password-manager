package view;

import javax.swing.ImageIcon;

/*
 * Description: This is an enum class 
 * that creates and holds all the images 
 * used in the website
 */

public enum WebsiteImages {

	// Images
	Eye(new ImageIcon("images/eye.png")), EyeClosed(new ImageIcon("images/eyeClosed.png")),
	KyLockLogo(new ImageIcon("images/KyLockLogo.png")), Sha1Image(new ImageIcon("images/Sha-1Image.png")),
	QuestionMark(new ImageIcon("images/questionMark.png")), TwoPhones(new ImageIcon("images/2phones.png")),
	StrengthChart(new ImageIcon("images/passwordStrengthChart.png")), addButton(new ImageIcon("images/addButton.png")),
	checkButton(new ImageIcon("images/checkButton.png")), regenerateButton(new ImageIcon("images/regenerateButton.png")),
	copyToClipboardButton(new ImageIcon("images/copyToClipboardButton.png"));

	// store specificed image
	private final ImageIcon WebsiteImage;

	// Constructor
	WebsiteImages(ImageIcon WebsiteImage) {
		this.WebsiteImage = WebsiteImage;
	}

	// Getter method
	public ImageIcon getWebsiteImage(int width, int height) {

		//reutnrs selected image
		return new ImageIcon(WebsiteImage.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));

	}
}