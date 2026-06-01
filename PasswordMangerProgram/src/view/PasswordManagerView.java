package view;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/*
 * Description: This is the password manager view class that displays 
 * the password entries and an add button to add extra entries. You can 
 * also view and delete current passwords when needed.
 * 
 * https://stackoverflow.com/questions/2174319/is-it-possible-to-have-a-java-swing-border-only-on-the-top-side
 * https://stackoverflow.com/questions/1065691/how-to-set-the-background-color-of-a-jbutton-on-the-mac-os
 */

public class PasswordManagerView extends JFrame {

	// JButtons
	public JButton entryButtons[];
	public JButton addButton;

	// Password entry
	public JTextField titleField;
	public JTextField userField;
	public JTextField passField;
	public JComboBox tagMenu;
	public JButton addEntryButton;
	public JDialog dialog;

	// password viewer
	public JLabel titleLabel;
	public JLabel userLabel;
	public JLabel passLabel;
	public JLabel tagLabel;
	public JButton deleteButton;
	public JButton closeButton;
	public JDialog viewerDialog;

	// scrollbar
	JPanel scrollPanel;
	JScrollPane scrollbar;
	public JScrollBar vertbar;

	// passwords
	JPanel myPasswordsPanel;
	JLabel myPasswordsLabel;

	// menu bar
	userMenuBar menuBar;

	// entry placeholders
	JPanel placeHolderPanel;
	JPanel[] entryHolders;
	JLabel bottomLabel;

	public PasswordManagerView() {

		// creates the menu bar
		menuBar = new userMenuBar();
		add(menuBar);

		scrollBarSetup();
		initalizePasswordHeader();
		initalizePasswordViewer();
		initalizePasswordEntry();
		initalizeAddButton();
		frameSetup();

	}

	// sets up the frame
	public void frameSetup() {

		setTitle("KyLocks - Password Manager");
		setSize(1200, 600);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setFocusable(true);

	}

	// creates all buttonAmount buttons
	public void createButtons(int buttonAmount) {

		// create the entry button array
		entryButtons = new JButton[buttonAmount];
		// creates the holders behind the entry button
		entryHolders = new JPanel[buttonAmount];

		// place holder panel
		placeHolderPanel = new JPanel();
		placeHolderPanel.setLayout(null);
		placeHolderPanel.setBackground(WebsiteColours.LIGHTORANGE.getWebsiteColor());
		placeHolderPanel.setBounds(75, 140, 1050, 1500);

		// creates an entry button and entry holder for
		// every amount of buttons there is
		for (int i = 0; i < buttonAmount; i++) {

			int row = i / 6;
			int col = i % 6;
			// entry buttons
			entryButtons[i] = new JButton();
			entryButtons[i].setFont(WebsiteFont.LabelFont.getDynamicFont(15));
			entryButtons[i].setBackground(WebsiteColours.BRIGHTORANGE.getWebsiteColor());
			entryButtons[i].setForeground(WebsiteColours.OFFWHITE.getWebsiteColor());
			entryButtons[i].setOpaque(true);
			entryButtons[i].setBorder(BorderFactory.createLineBorder(WebsiteColours.BURNTORANGE.getWebsiteColor(), 5));
			entryButtons[i].setBounds(100 + (col * 175), 150 + (row * 150), 125, 125);
			entryButtons[i].setVisible(false);
			scrollPanel.add(entryButtons[i]);

			// entry place holders
			entryHolders[i] = new JPanel();
			entryHolders[i].setBounds(25 + (col * 175), 10 + (row * 150), 125, 125);
			entryHolders[i].setBackground(WebsiteColours.PLACEHOLDERORANGE.getWebsiteColor());

			placeHolderPanel.add(entryHolders[i]);

		}

		scrollPanel.add(placeHolderPanel);

		// bottom label
		bottomLabel = new JLabel("© 2025 KyLock. All rights reserved.");
		bottomLabel.setBounds(500, scrollPanel.getPreferredSize().height - 100, 300, 25);
		scrollPanel.add(bottomLabel);

	}

	// initazlizes the add button
	public void initalizeAddButton() {

		// addbutton
		addButton = new JButton();
		addButton.setBounds(900, 20, 100, 50);
		addButton.setIcon(WebsiteImages.addButton.getWebsiteImage(addButton.getWidth(), addButton.getHeight()));
		addButton.setBorderPainted(false);
		myPasswordsPanel.add(addButton);

	}

	// initalizes the password entry JOptionPane
	public void initalizePasswordEntry() {

		// creates a panel for the everything
		// inside the password entry
		JPanel passwordPanel = new JPanel();

		// creates three tags
		String[] tags = { "Login", "Card", "Other" };

		// creates all the fields required
		titleField = new JTextField("Title", 15);
		userField = new JTextField("Username", 15);
		passField = new JPasswordField("Password", 15);
		tagMenu = new JComboBox(tags);
		addEntryButton = new JButton("Add Entry");

		// adds all fields to the panel
		passwordPanel.add(titleField);
		passwordPanel.add(userField);
		passwordPanel.add(passField);
		passwordPanel.add(tagMenu);
		passwordPanel.add(addEntryButton);

		// makes there show no "ok" button
		Object[] options = {};
		JOptionPane pane = new JOptionPane(passwordPanel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null,
				options);
		dialog = pane.createDialog("Password Entry");

	}

	// initalizes the password viewer to display
	// passwords when the buttons are clicked
	public void initalizePasswordViewer() {

		// create a panel to put inside the JOptionPane
		JPanel passwordPanel = new JPanel();
		passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));

		// creates all the required
		// information for the password
		titleLabel = new JLabel();
		userLabel = new JLabel();
		passLabel = new JLabel();
		tagLabel = new JLabel();
		deleteButton = new JButton("Delete Entry");
		closeButton = new JButton("Close");

		// add everything to the panel
		passwordPanel.add(titleLabel);
		passwordPanel.add(userLabel);
		passwordPanel.add(passLabel);
		passwordPanel.add(tagLabel);
		passwordPanel.add(deleteButton);
		passwordPanel.add(closeButton);
		// problem where the JOptionPane was cutting out the last button
		// creates more space at the bottom
		passwordPanel.add(Box.createVerticalStrut(50));

		// removes OK button
		Object[] options = {};
		JOptionPane pane = new JOptionPane(passwordPanel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null,
				options);
		viewerDialog = pane.createDialog("Password Viewer");
		viewerDialog.pack();
		viewerDialog.setVisible(false);

	}

	// sets up the scroll bar
	public void scrollBarSetup() {

		// scroll panel
		scrollPanel = new JPanel();
		scrollPanel.setLayout(null);
		scrollPanel.setPreferredSize(new Dimension(1000, 1800));

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

	// initazlizes the password title
	public void initalizePasswordHeader() {

		// passwords panel
		// used to create a line that seperates
		// the buttons from the add button and title
		myPasswordsPanel = new JPanel();
		myPasswordsPanel.setLayout(null);
		myPasswordsPanel.setBounds(100, 50, 1000, 75);
		myPasswordsPanel
				.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, WebsiteColours.BURNTORANGE.getWebsiteColor()));

		// displays the text
		myPasswordsLabel = new JLabel("My Passwords");
		myPasswordsLabel.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		myPasswordsLabel.setBounds(5, 25, 200, 75);
		myPasswordsPanel.add(myPasswordsLabel);

		scrollPanel.add(myPasswordsPanel);
	}
}
