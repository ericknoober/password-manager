package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/*
 * Description: This is the password strength view class
 * that shows the password strength along with the status 
 * and password
 * https://stackoverflow.com/questions/59647091/uimanager-setlookandfeel-for-nested-classes
 */

public class PasswordStrengthView extends JFrame {

	// JTextField
	public JPasswordField passwordField;

	// JButton
	public JButton passwordButton;

	// JLabel
	public JLabel characterLengthCountLabel;
	public JLabel timeToCrackLabel;
	public JLabel timeToCrackValueLabel;
	public JLabel passwordStrengthLabel;
	public JLabel passwordStrengthValueLabel;
	JLabel passwordStrengthHeader;

	// JPanel
	JPanel box;
	userMenuBar menuBar;
	JPanel scrollPanel;

	// Scrollbar
	JScrollPane scrollbar;
	JScrollBar vertbar;

	// information
	JLabel bottomLabel;
	// information1
	JPanel information1Panel;
	JTextArea information1Header;
	JTextArea information1Body;
	JLabel image1;
	// information2
	JPanel information2Panel;
	JTextArea information2Header;
	JTextArea information2Body;
	// information2
	JPanel information3Panel;
	JTextArea information3Header;
	JTextArea information3Body;

	// JProgressBar
	public JProgressBar strengthProgressBar;

	public PasswordStrengthView() {

		// creates the menu bar
		menuBar = new userMenuBar();
		add(menuBar);

		scrollbarSetup();
		information();
		passwordTextArea();
		displayStats();
		frameSetup();

		// allows the frame to start at the top
		SwingUtilities.invokeLater(() -> {
			vertbar.setValue(0);
		});

	}

	// sets up the frame
	public void frameSetup() {

		setTitle("KyLocks - Password Strength Testing Tool");
		setSize(1200, 600);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setFocusable(true);

	}

	// creates the password text area
	public void passwordTextArea() {

		// box panel
		box = new JPanel();
		box.setBounds(150, 75, 900, 475);
		box.setBackground(WebsiteColours.BRIGHTORANGE.getWebsiteColor());
		box.setLayout(null);
		add(box);

		// password strength title
		passwordStrengthHeader = new JLabel();
		passwordStrengthHeader.setBounds(180, 25, 600, 50);
		passwordStrengthHeader.setText("Password Strength Testing Tool");
		passwordStrengthHeader.setFont(WebsiteFont.LabelFont.getDynamicFont(35));
		passwordStrengthHeader.setForeground(WebsiteColours.OFFWHITE.getWebsiteColor());
		box.add(passwordStrengthHeader);

		// eye button that switches password visibility
		passwordButton = new JButton();
		passwordButton.setBounds(625, 135, 75, 75);
		passwordButton.setIcon(WebsiteImages.EyeClosed.getWebsiteImage(50, 40));
		passwordButton.setBorderPainted(false);
		box.add(passwordButton);

		// generated password field
		// limits characters to 128
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 125, 500, 100);
		passwordField.setBackground(WebsiteColours.OFFWHITE.getWebsiteColor());
		passwordField.setFont(WebsiteFont.PasswordFont.getWebsiteFont());
		// follow the current text in password so it doesn't go off screen
		passwordField.scrollRectToVisible(passwordField.getBounds());
		// creates space at the end of the password field for the eye button
		// creates a border along the right side
		passwordField.setBorder(BorderFactory.createCompoundBorder(passwordField.getBorder(),
				BorderFactory.createEmptyBorder(0, 0, 0, 80)));
		box.add(passwordField);

	}

	// displasys the status of your entered spassword
	public void displayStats() {

		// overides the default mac colours
		// allows the JProgressbar to change colours due to mac
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// time to crack title
		timeToCrackLabel = new JLabel("Time To Crack:");
		timeToCrackLabel.setBounds(525, 250, 500, 100);
		timeToCrackLabel.setForeground(WebsiteColours.OFFWHITE.getWebsiteColor());
		timeToCrackLabel.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		box.add(timeToCrackLabel);

		// time to crack status
		timeToCrackValueLabel = new JLabel();
		timeToCrackValueLabel.setBounds(535, 280, 300, 100);
		timeToCrackValueLabel.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		box.add(timeToCrackValueLabel);

		// password strength title
		passwordStrengthLabel = new JLabel("Password Strength: ");
		passwordStrengthLabel.setBounds(175, 250, 400, 100);
		passwordStrengthLabel.setForeground(WebsiteColours.OFFWHITE.getWebsiteColor());
		passwordStrengthLabel.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		box.add(passwordStrengthLabel);

		// password strength status
		passwordStrengthValueLabel = new JLabel();
		passwordStrengthValueLabel.setBounds(185, 280, 200, 100);
		passwordStrengthValueLabel.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		box.add(passwordStrengthValueLabel);

		// strength progress bar
		strengthProgressBar = new JProgressBar();
		strengthProgressBar.setBounds(200, 230, 500, 20);
		box.add(strengthProgressBar);

		scrollPanel.add(box);

	}

	// sets up the scrollbar
	public void scrollbarSetup() {

		// scroll panel
		scrollPanel = new JPanel();
		scrollPanel.setLayout(null);
		scrollPanel.setPreferredSize(new Dimension(1000, 1850));

		// scroll bar
		scrollbar = new JScrollPane(scrollPanel);
		// sets the dimensions of the scroll pane
		scrollbar.setBounds(0, 45, 1200, 600);
		// setting to be a vertical scroll bar
		scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// setting the scroll bar speed to be 15 units
		scrollbar.getVerticalScrollBar().setUnitIncrement(15);
		// setting to be no horizontal scrollbar
		scrollbar.setHorizontalScrollBar(null);
		// removes border around the area of the scroll pane
		scrollbar.setBorder(BorderFactory.createEmptyBorder());

		// creates a seperate componenet for the vertical bar
		vertbar = scrollbar.getVerticalScrollBar();

		add(scrollbar);
	}

	// displays the information
	public void information() {
		// information1
		information1Panel = new JPanel();
		information1Panel.setLayout(new BorderLayout());
		information1Panel.setBounds(275, 650, 650, 175);
		information1Panel.setBackground(WebsiteColours.BRIGHTORANGE.getWebsiteColor());

		// information1 header
		information1Header = new JTextArea();
		information1Header.setText("Why does password strength matter?");
		information1Header.setBackground(null);
		information1Header.setEditable(false);
		information1Header.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		information1Header.setForeground(WebsiteColours.OFFWHITE.getWebsiteColor());
		information1Panel.add(information1Header, BorderLayout.NORTH);

		// information1 body
		information1Body = new JTextArea();
		// information1Body.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		information1Body.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		information1Body.setText("Using the same password over and over again can lead to a huge security risk. "
				+ "Choosing a unique and complex password for every site helps keep your information secure. "
				+ "Use this password strength chart to guide your next password decisions.");
		information1Body.setLineWrap(true);
		information1Body.setWrapStyleWord(true);
		information1Body.setBackground(null);
		information1Body.setEditable(false);
		information1Panel.add(information1Body);

		scrollPanel.add(information1Panel);

		// image
		image1 = new JLabel();
		image1.setBounds(225, 875, 750, 400);
		image1.setIcon(WebsiteImages.StrengthChart.getWebsiteImage(image1.getWidth(), image1.getHeight()));
		scrollPanel.add(image1);

		// information2
		information2Panel = new JPanel();
		information2Panel.setLayout(new BorderLayout());
		information2Panel.setBounds(175, 1300, 400, 350);
		information2Panel.setBackground(WebsiteColours.BRIGHTORANGE.getWebsiteColor());

		// information2 header
		information2Header = new JTextArea();
		information2Header.setText("How do password strength testers work?");
		information2Header.setBackground(null);
		information2Header.setEditable(false);
		information2Header.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		information2Header.setForeground(WebsiteColours.OFFWHITE.getWebsiteColor());
		information2Panel.add(information2Header, BorderLayout.NORTH);

		// information2 body
		information2Body = new JTextArea();
		// information1Body.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		information2Body.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		information2Body.setText("\nWe use the java implementation of a tool called zxcvbn. These "
				+ "testers measure the strength by attempting to brute force an attack using every "
				+ "possible letter, number, and special character until it is cracked.");
		information2Body.setLineWrap(true);
		information2Body.setWrapStyleWord(true);
		information2Body.setBackground(null);
		information2Body.setEditable(false);
		information2Panel.add(information2Body);

		scrollPanel.add(information2Panel);

		// information3
		information3Panel = new JPanel();
		information3Panel.setLayout(new BorderLayout());
		information3Panel.setBounds(625, 1300, 400, 350);
		information3Panel.setBackground(WebsiteColours.BRIGHTORANGE.getWebsiteColor());

		// information3 header
		information3Header = new JTextArea();
		information3Header.setText("Is it ok to type my real password?");
		information3Header.setBackground(null);
		information3Header.setEditable(false);
		information3Header.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		information3Header.setForeground(WebsiteColours.OFFWHITE.getWebsiteColor());
		information3Panel.add(information3Header, BorderLayout.NORTH);

		// information3 body
		information3Body = new JTextArea();
		// information1Body.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		information3Body.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		information3Body.setText("\nYes. Your password is only locally proccessed through the java program. "
				+ "Any attempt to steal data would be a breach of privacy.");
		information3Body.setLineWrap(true);
		information3Body.setWrapStyleWord(true);
		information3Body.setBackground(null);
		information3Body.setEditable(false);
		information3Panel.add(information3Body);

		scrollPanel.add(information3Panel);

		// bottom label
		bottomLabel = new JLabel("© 2025 KyLock. All rights reserved.");
		bottomLabel.setBounds(500, scrollPanel.getPreferredSize().height - 100, 300, 25);
		scrollPanel.add(bottomLabel);

	}

	// sets the colour based on the password strength
	public void setLabelColours(int index) {

		// based on the index score, change the current colour
		Color currentColour = null;

		if (index == 0) {

			// dark red
			currentColour = new Color(153, 10, 0);

		} else if (index == 1) {

			// red
			currentColour = new Color(255, 65, 51);

		} else if (index == 2) {

			// yellow
			currentColour = new Color(250, 198, 27);

		} else if (index == 3) {

			// light green
			currentColour = new Color(120, 219, 66);

		} else if (index == 4) {

			// green
			currentColour = new Color(83, 191, 29);
		}

		// sets the status labels to the respective colour
		passwordField.setForeground(currentColour);
		timeToCrackValueLabel.setForeground(currentColour);
		passwordStrengthValueLabel.setForeground(currentColour);
		strengthProgressBar.setForeground(currentColour);
	}

}
