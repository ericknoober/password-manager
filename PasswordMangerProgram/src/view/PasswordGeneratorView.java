package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/*
 * Description: This is the password generator view class where the GUI
 * is being displayed with filters, the generated password, and required
 * buttons are intialized
 */

public class PasswordGeneratorView extends JFrame {

	// JLabel
	public JLabel characterLengthCountLabel;
	JLabel filtersLabel;
	JLabel passwordGeneratorLabel;

	// JTextField
	public JTextField passwordField;

	// JButton
	public JButton generateButton;
	public JButton copyToClipboardButton;

	// filter buttons
	// JCheckBox
	public JCheckBox[] filterCheckBoxes = new JCheckBox[4];
	public JCheckBox uppercaseCheckBox;
	public JCheckBox lowercaseCheckBox;
	public JCheckBox numbersCheckBox;
	public JCheckBox symbolsCheckBox;

	// JSlider
	public JSlider characterLengthSlider;

	// JPanel
	userMenuBar menuBar;
	JPanel box;
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

	public PasswordGeneratorView() {

		// creates the menu bar
		menuBar = new userMenuBar();
		add(menuBar);

		scrollbarSetup();
		information();
		passwordTextArea();
		filterButtons();
		frameSetup();

		// allows the frame to start at the top
		SwingUtilities.invokeLater(() -> {
			vertbar.setValue(0);
		});

	}

	// sets up the frame
	public void frameSetup() {

		setTitle("KyLocks - Password Generator");
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
		box.setBounds(100, 75, 1000, 475);
		box.setBackground(WebsiteColours.BRIGHTORANGE.getWebsiteColor());
		box.setLayout(null);
		add(box);

		// password generator header
		passwordGeneratorLabel = new JLabel();
		passwordGeneratorLabel.setText("Password Generator");
		passwordGeneratorLabel.setBounds(250, 0, 500, 100);
		passwordGeneratorLabel.setForeground(WebsiteColours.OFFWHITE.getWebsiteColor());
		passwordGeneratorLabel.setFont(WebsiteFont.LabelFont.getDynamicFont(50));
		box.add(passwordGeneratorLabel);

		// generated password field
		passwordField = new JTextField(10);
		passwordField.setBounds(25, 100, 950, 100);
		passwordField.setBackground(WebsiteColours.OFFWHITE.getWebsiteColor());
		passwordField.setHorizontalAlignment(JTextField.CENTER);
		passwordField.setEditable(false);
		box.add(passwordField);

		// generate password button
		generateButton = new JButton();
		generateButton.setBounds(275, 200, 200, 75);
		generateButton.setBackground(Color.red);
		generateButton.setIcon(WebsiteImages.regenerateButton.getWebsiteImage(generateButton.getWidth(), generateButton.getHeight()));
		generateButton.setBorderPainted(false);
		box.add(generateButton);

		// copy to clipboard button
		copyToClipboardButton = new JButton();
		copyToClipboardButton.setBounds(525, 200, 200, 75);
		copyToClipboardButton.setBackground(Color.red);
		copyToClipboardButton.setIcon(WebsiteImages.copyToClipboardButton.getWebsiteImage(copyToClipboardButton.getWidth(), copyToClipboardButton.getHeight()));
		copyToClipboardButton.setBorderPainted(false);
		box.add(copyToClipboardButton);
	}

	// creates the filter buttons
	public void filterButtons() {

		// array of the filter names
		String[] filtersNames = { "Uppercase", "Lowercase", "Numbers", "Symbols" };

		// four password filters
		for (int i = 0; i < filterCheckBoxes.length; i++) {

			// creating the filter boxes
			filterCheckBoxes[i] = new JCheckBox(filtersNames[i]);
			filterCheckBoxes[i].setBounds(25 + (i * 125), 350, 100, 25);
			filterCheckBoxes[i].setFont(WebsiteFont.LabelFont.getDynamicFont(12));
			filterCheckBoxes[i].setForeground(WebsiteColours.OFFWHITE.getWebsiteColor());
			filterCheckBoxes[i].setSelected(true);
			box.add(filterCheckBoxes[i]);

		}

		// filter header
		filtersLabel = new JLabel("Filters");
		filtersLabel.setBounds(50, 325, 100, 25);
		filtersLabel.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		filtersLabel.setForeground(WebsiteColours.OFFWHITE.getWebsiteColor());
		box.add(filtersLabel);

		// character length label
		characterLengthCountLabel = new JLabel();
		characterLengthCountLabel.setBounds(525, 325, 250, 25);
		characterLengthCountLabel.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		characterLengthCountLabel.setForeground(WebsiteColours.OFFWHITE.getWebsiteColor());
		box.add(characterLengthCountLabel);

		// character length slider
		// slider orientation, min value, max value, start value
		characterLengthSlider = new JSlider(JSlider.HORIZONTAL, 5, 128, 14);
		characterLengthSlider.setBounds(500, 350, 475, 25);
		// characterLengthSlider.setForeground(WebsiteColours.DARKORANGE.getWebsiteColor());
		box.add(characterLengthSlider);

		// add the box to the scrollPanel
		scrollPanel.add(box);
	}

	// set up the scroll bar
	public void scrollbarSetup() {

		// scroll panel
		scrollPanel = new JPanel();
		scrollPanel.setLayout(null);
		scrollPanel.setPreferredSize(new Dimension(1000, 1450));

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

		// seperates the vertical componenet from the scroll pane
		vertbar = scrollbar.getVerticalScrollBar();

		add(scrollbar);
	}

	// displays the information
	public void information() {

		// information1
		information1Panel = new JPanel();
		information1Panel.setLayout(new BorderLayout());
		information1Panel.setBounds(75, 650, 1000, 350);
		information1Panel.setBackground(WebsiteColours.BRIGHTORANGE.getWebsiteColor());

		// information1 header
		information1Header = new JTextArea();
		information1Header.setText("Creating strong passwords");
		information1Header.setBackground(null);
		information1Header.setEditable(false);
		information1Header.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		information1Header.setForeground(WebsiteColours.OFFWHITE.getWebsiteColor());
		information1Panel.add(information1Header, BorderLayout.NORTH);

		// information1 body
		information1Body = new JTextArea();
		information1Body.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		information1Body.setText(
				"\n • It is recommended that you use at least 12 characters, but\n   14 characters is considered strong."
						+ "\n\n • You should always use a combination of uppercase letters,\n    lowercase letters, numbers and symbols."
						+ "\n\n • Don't use any dictonary words and make them different from\n   previous passwords.");
		information1Body.setBackground(null);
		information1Body.setEditable(false);
		information1Panel.add(information1Body);

		// image
		image1 = new JLabel();
		image1.setSize(400, 350);
		image1.setIcon(WebsiteImages.TwoPhones.getWebsiteImage(image1.getWidth(), image1.getHeight()));
		information1Panel.add(image1, BorderLayout.EAST);

		// add the information1panel to the scrollpanel
		scrollPanel.add(information1Panel);

		// information2
		information2Panel = new JPanel();
		information2Panel.setLayout(new BorderLayout());
		information2Panel.setBounds(300, 1050, 600, 250);
		information2Panel.setBackground(WebsiteColours.BRIGHTORANGE.getWebsiteColor());

		// information2 header
		information2Header = new JTextArea();
		information2Header.setText("Why use a password generator?");
		information2Header.setBackground(null);
		information2Header.setEditable(false);
		information2Header.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		information2Header.setForeground(WebsiteColours.OFFWHITE.getWebsiteColor());
		information2Panel.add(information2Header, BorderLayout.NORTH);

		// information2 body
		information2Body = new JTextArea();
		// information1Body.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		information2Body.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		information2Body.setText("\nWe use the API passay which uses character rules to randomly generate characters. "
				+ "Many people often resort to using easy-to-remember passwords, but the good news is that a strong "
				+ "password generator does the work for you by automatically creating strong, unique, and difficult-to-crack "
				+ "passwords or passphrases.");
		information2Body.setLineWrap(true);
		information2Body.setWrapStyleWord(true);
		information2Body.setBackground(null);
		information2Body.setEditable(false);
		information2Panel.add(information2Body);

		// add the information1panel to the scrollpanel
		scrollPanel.add(information2Panel);

		// bottom label
		bottomLabel = new JLabel("© 2025 KyLock. All rights reserved.");
		bottomLabel.setBounds(500, scrollPanel.getPreferredSize().height - 100, 300, 25);
		scrollPanel.add(bottomLabel);

	}
}
