package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.PasswordStrengthModel;
import view.PasswordStrengthView;
import view.WebsiteImages;

/*
 * Description: This is the password strength controller class
 * It gathers the data from the zxcbn API and updates the view 
 * with the results
 * 
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/JPasswordField.html
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/JTextField.html
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/JTextField.AccessibleJTextField.html
 */

public class PasswordStrengthController implements ActionListener, DocumentListener {

	// view
	PasswordStrengthView view;
	// model
	PasswordStrengthModel model;

	// is the password being shown for the eye
	boolean isPassShown;

	// password
	String password;

	public PasswordStrengthController() {

		view = new PasswordStrengthView();
		model = new PasswordStrengthModel();

		// add action listener
		view.passwordButton.addActionListener(this);
		// add document listener
		view.passwordField.getDocument().addDocumentListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// if the eye button is clicked
		if (e.getSource() == view.passwordButton) {

			// second click on the eye
			if (isPassShown) {
				// hides passwords
				view.passwordButton.setIcon(WebsiteImages.EyeClosed.getWebsiteImage(50, 40));
				view.passwordField.setEchoChar('●');
				isPassShown = false;
				// first click on the eye
			} else {
				// shows password
				view.passwordButton.setIcon(WebsiteImages.Eye.getWebsiteImage(50, 40));
				// set boolean of eye to be true
				isPassShown = true;
				// make pasword field visible
				view.passwordField.setEchoChar((char) 0);

			}

		}

	}

	// if there is typing in the password field
	@Override
	public void insertUpdate(DocumentEvent e) {

		// get the users current password
		model.setPassword(view.passwordField.getText());
		// change the password values
		view.timeToCrackValueLabel.setText(model.getPasswordCrackTime());
		view.passwordStrengthValueLabel.setText(model.getPasswordScoreString());
		view.strengthProgressBar.setValue(model.getPasswordScore() * 25);
		// change the colour based on the strength
		view.setLabelColours(model.getPasswordScore());
	}

	@Override
	public void removeUpdate(DocumentEvent e) {

	}

	@Override
	public void changedUpdate(DocumentEvent e) {

	}

}
