package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/*
 * Description: This is the breach scanner view class that creates
 * the GUI for the breach scanner that shows 
 * 
 * https://stackoverflow.com/questions/58955392/how-to-set-horizontal-scroll-pane-starting-location-on-the-left-end
 * https://stackoverflow.com/questions/291115/java-swing-using-jscrollpane-and-having-it-scroll-back-to-top
 */

public class BreachScannerView extends JFrame {

	// JTextField
	public JTextField passwordArea;

	// JButton
	public JButton passwordButton;

	// JLabel
	public JLabel isPwnedLabel;
	public JLabel extraInformationLabel;
	JLabel breachScannerLabel;

	// JPanel
	userMenuBar menuBar;
	JPanel box;
	public JPanel breachBox;
	public JPanel scrollPanel;

	// JScrollBar
	public JScrollPane scrollbar;
	public JScrollBar vertbar;

	// information stuff
	public JLabel bottomLabel;
	// information 1
	public JPanel information1Panel;
	JTextArea information1Header;
	JTextArea information1Body;
	JLabel image1;

	// information 2
	public JPanel information2Panel;
	JTextArea information2Header;
	JTextArea information2Body;
	JLabel image2;

	public BreachScannerView() {

		// add the custom menu bar
		menuBar = new userMenuBar();
		add(menuBar);

		information();
		passwordSetup();
		displayBreach();
		scrollbarSetup();
		frameSetup();

		// calls the method to start the frame at the top
		SwingUtilities.invokeLater(() -> {
			vertbar.setValue(0);
		});

	}

	// setup frame
	public void frameSetup() {

		setTitle("KyLocks - Breach Scanner");
		setSize(1200, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setVisible(true);
		setFocusable(true);

	}

	// sets up password buttons and panel
	public void passwordSetup() {

		// box panel
		box = new JPanel();
		box.setBounds(100, 75, 1000, 475);
		box.setBackground(WebsiteColours.BRIGHTORANGE.getWebsiteColor());
		box.setLayout(null);
		add(box);

		// breach scanner label
		breachScannerLabel = new JLabel();
		breachScannerLabel.setBounds(300, 25, 500, 50);
		breachScannerLabel.setForeground(WebsiteColours.OFFWHITE.getWebsiteColor());
		breachScannerLabel.setFont(WebsiteFont.LabelFont.getDynamicFont(50));
		breachScannerLabel.setText("Breach Scanner");
		box.add(breachScannerLabel);

		// password text area
		passwordArea = new HintTextField("Enter Password");
		passwordArea.setForeground(WebsiteColours.BURNTORANGE.getWebsiteColor());
		passwordArea.setBackground(WebsiteColours.OFFWHITE.getWebsiteColor());
		passwordArea.setFont(WebsiteFont.PasswordFont.getWebsiteFont());
		passwordArea.setBounds(250, 150, 500, 100);
		box.add(passwordArea);

		// password button
		passwordButton = new JButton();
		passwordButton.setBounds(350, 250, 300, 50);
		passwordButton.setBackground(Color.PINK);
		passwordButton.setIcon(WebsiteImages.checkButton.getWebsiteImage(passwordButton.getWidth(), passwordButton.getHeight()));
		passwordButton.setBorderPainted(false);
		box.add(passwordButton);

	}

	// initalize the password breach prompt
	public void displayBreach() {

		// breach box JPanel
		breachBox = new JPanel();
		breachBox.setBounds(200, 600, 800, 300);
		breachBox.setBackground(WebsiteColours.DARKGREEN.getWebsiteColor());
		breachBox.setLayout(null);
		breachBox.setVisible(false);
		add(breachBox);

		// is pwned label
		isPwnedLabel = new JLabel();
		isPwnedLabel.setBounds(0, 50, 800, 200);
		isPwnedLabel.setForeground(WebsiteColours.OFFWHITE.getWebsiteColor());
		isPwnedLabel.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		isPwnedLabel.setText("Connect to the internet!");
		isPwnedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		isPwnedLabel.setVisible(false);
		breachBox.add(isPwnedLabel);

		// extra information label
		extraInformationLabel = new JLabel();
		extraInformationLabel.setBounds(0, 100, 800, 200);
		extraInformationLabel.setForeground(WebsiteColours.OFFWHITE.getWebsiteColor());
		extraInformationLabel.setFont(WebsiteFont.LabelFont.getDynamicFont(12));
		extraInformationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		breachBox.add(extraInformationLabel);

	}

	// scroll bar setup
	public void scrollbarSetup() {

		// scroll panel
		scrollPanel = new JPanel();
		scrollPanel.setLayout(null);
		scrollPanel.setPreferredSize(new Dimension(1000, 1650));

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

		// isolate the vertical bar from the scroll bar
		vertbar = scrollbar.getVerticalScrollBar();

		// add componenets to the scroll panel
		scrollPanel.add(breachBox);
		scrollPanel.add(box);
		scrollPanel.add(bottomLabel);
		scrollPanel.add(information1Panel);
		scrollPanel.add(information2Panel);
		scrollPanel.add(image1);
		scrollPanel.add(image2);
		add(scrollbar);
	}

	// display the information
	public void information() {

		// information 1
		// information panel
		information1Panel = new JPanel();
		information1Panel.setLayout(new BorderLayout());
		information1Panel.setBounds(125, 650, 600, 450);
		information1Panel.setBackground(WebsiteColours.BRIGHTORANGE.getWebsiteColor());

		// information header
		information1Header = new JTextArea();
		information1Header.setText("Why scan for Breached Passwords?");
		information1Header.setBackground(null);
		information1Header.setEditable(false);
		information1Header.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		information1Header.setForeground(WebsiteColours.OFFWHITE.getWebsiteColor());
		information1Panel.add(information1Header, BorderLayout.NORTH);

		// informtion body
		information1Body = new JTextArea();
		information1Body.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		information1Body.setText("\n 1. Breached passwords are public knowledge\n"
				+ "When a data breach happens, usernames, emails, and passwords are often leaked and posted online or sold on the dark web."
				+ "If your password is in one of those leaks, hackers can and will try it across many sites.\n"
				+ "2. People reuse passwords\n"
				+ "If you've used the same password on multiple accounts, and one site gets breached, all your other accounts are now vulnerable. "
				+ "Scanning helps detect and stop that chain reaction.\n" + "3. Prevent future hacks\n"
				+ "If you catch a compromised password early and change it, you stop attackers before they can access sensitive info like banking, email, "
				+ "or work accounts.");
		//allows the text to automatically skips lines
		information1Body.setLineWrap(true);
		information1Body.setWrapStyleWord(true);
		information1Body.setBackground(null);
		information1Body.setEditable(false);
		information1Panel.add(information1Body);

		// image
		image1 = new JLabel();
		image1.setBounds(information1Panel.getBounds().x + 625, information1Panel.getBounds().y + 25, 300, 400);
		image1.setIcon(WebsiteImages.QuestionMark.getWebsiteImage(image1.getWidth(), image1.getHeight()));

		// information 2
		information2Panel = new JPanel();
		information2Panel.setLayout(new BorderLayout());
		information2Panel.setBounds(550, 1200, 600, 250);
		information2Panel.setBackground(WebsiteColours.BRIGHTORANGE.getWebsiteColor());

		// information header
		information2Header = new JTextArea();
		information2Header.setText("Is your password safe?");
		information2Header.setBackground(null);
		information2Header.setEditable(false);
		information2Header.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		information2Header.setForeground(WebsiteColours.OFFWHITE.getWebsiteColor());
		information2Panel.add(information2Header, BorderLayout.NORTH);

		// information body
		information2Body = new JTextArea();
		information2Body.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		information2Body
				.setText("\nWe use the HaveIBeenPwned API to scan through internet databases in order to find a match. "
						+ "Your password is safe with us! We convert your entered password into a hash code using the SHA-1 algorithm "
						+ "and match other hash codees with the same five letter prefix.");
		information2Body.setLineWrap(true);
		information2Body.setWrapStyleWord(true);
		information2Body.setBackground(null);
		information2Body.setEditable(false);
		information2Panel.add(information2Body);

		// image
		image2 = new JLabel();
		image2.setBounds(information2Panel.getBounds().x - 500, information2Panel.getBounds().y - 50, 450, 350);
		image2.setIcon(WebsiteImages.Sha1Image.getWebsiteImage(image2.getWidth(), image2.getHeight()));

		// bottom label
		bottomLabel = new JLabel("© 2025 KyLock. All rights reserved.");
		bottomLabel.setBounds(500, 1550, 300, 25);

	}

	// update the positions of the panel
	// afer the ispwned prompt label is displayed
	public void updatePanelPostions() {

		//move the information1panel and image
		information1Panel.setLocation(information1Panel.getBounds().x, information1Panel.getBounds().y + 275);
		image1.setLocation(image1.getBounds().x, image1.getBounds().y + 275);

		//move the information2panel and image
		information2Panel.setLocation(information2Panel.getBounds().x, information2Panel.getBounds().y + 250);
		image2.setLocation(information2Panel.getBounds().x - 500, information2Panel.getBounds().y - 50);

		// scroll panel
		scrollPanel.setPreferredSize(new Dimension(scrollPanel.getBounds().x, scrollPanel.getHeight() + 200));
		// Note: gets previous height
		//bottom label
		bottomLabel.setLocation(bottomLabel.getBounds().x, scrollPanel.getHeight() + 100);
	}

}
