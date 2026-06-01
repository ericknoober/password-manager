package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.BreachScannerController;
import controller.DashboardController;
import controller.PasswordGeneratorController;
import controller.PasswordManagerController;
import controller.PasswordStrengthController;

/*
 * Description: This is the user menu bar which is displayed
 * across all the frames and allows the user to navigate through
 * all the features and dashboard
 * 
 */

public class userMenuBar extends JPanel implements ActionListener {

	// JComboBox
	JComboBox<String> featuresButton;

	// JButotn
	JButton dashboardButton;

	public userMenuBar() {

		// logo icon
		JLabel logo = new JLabel();
		logo.setBounds(5, 5, 150, 40);
		logo.setIcon(WebsiteImages.KyLockLogo.getWebsiteImage(40, 40));
		add(logo);

		// logo label
		JLabel logoLabel = new JLabel("KyLock");
		logoLabel.setFont(WebsiteFont.KyLockFont.getWebsiteFont());
		logoLabel.setForeground(WebsiteColours.OFFWHITE.getWebsiteColor());
		logoLabel.setBounds(40, 5, 150, 40);
		add(logoLabel);

		// dashboard button
		dashboardButton = new JButton("Dashboard");
		dashboardButton.setFont(WebsiteFont.KyLockFont.getWebsiteFont());
		dashboardButton.setForeground(WebsiteColours.OFFWHITE.getWebsiteColor());
		dashboardButton.setBorderPainted(false);
		dashboardButton.setContentAreaFilled(false);
		dashboardButton.setBounds(150, 5, 150, 40);
		dashboardButton.addActionListener(this);
		add(dashboardButton);

		// features list
		String[] featuresList = { "Features", "Password Manager", "Password Generator", "Password Strength Tool",
				"Breach Scanner" };

		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			featuresButton = new JComboBox<>(featuresList);
			featuresButton.addActionListener(this);
			featuresButton.setBounds(300, 5, 200, 35);
			add(featuresButton);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// menu bar JPanel
		setBounds(0, 0, 1200, 45);
		setLayout(null);
		setBackground(WebsiteColours.BRIGHTORANGE.getWebsiteColor());

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// grabs the current window JFrame
		// used to call dispose() method
		java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);

		// if the dashboard button is clicked
		if (e.getSource() == dashboardButton) {

			// close frame and open dashboard
			window.dispose();
			new DashboardController();
		}

		// if the password manager button is clicked
		if (featuresButton.getSelectedItem() == "Password Manager") {

			// close frame and open password manager
			window.dispose();
			new PasswordManagerController();

			// if the password generator button is clicked
		} else if (featuresButton.getSelectedItem() == "Password Generator") {

			// close frame and open password generator
			window.dispose();
			new PasswordGeneratorController();

			// if the password strength tool button is clicked
		} else if (featuresButton.getSelectedItem() == "Password Strength Tool") {

			// close frame and open password strength testing tool
			window.dispose();
			new PasswordStrengthController();

			// if the breach scanner button is clicked
		} else if (featuresButton.getSelectedItem() == "Breach Scanner") {

			// close frame and open breach scanner
			window.dispose();
			new BreachScannerController();

		}

	}

}
