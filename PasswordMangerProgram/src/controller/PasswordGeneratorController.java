package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.PasswordGeneratorModel;
import view.PasswordGeneratorView;
import view.WebsiteFont;

/*
 * Description: password generator controller class
 * Grabs the API data and updates the GUI
 */

public class PasswordGeneratorController implements ActionListener, ChangeListener {

	// view
	PasswordGeneratorView view;
	// model
	PasswordGeneratorModel model;

	// password filters
	// activates all the filters
	boolean[] passwordFilters = { true, true, true, true };
	// starting character length
	int characterLength = 14;
	// password string
	String password;

	public PasswordGeneratorController() {

		view = new PasswordGeneratorView();
		model = new PasswordGeneratorModel();

		// action listeners
		view.generateButton.addActionListener(this);
		view.copyToClipboardButton.addActionListener(this);
		view.characterLengthSlider.addChangeListener(this);
		for (int i = 0; i < view.filterCheckBoxes.length; i++) {
			view.filterCheckBoxes[i].addActionListener(this);
		}

		// displays the inital length count preset
		view.characterLengthCountLabel.setText("Character Length: " + characterLength);
		// displays the password with defualt filters
		displayPassword();

	}

	// makes user use one filter is active so no error would occur
	public void filterValidation() {

		// active filters
		int amountSelected = 0;

		// checks how many filters are active
		for (int i = 0; i < view.filterCheckBoxes.length; i++) {
			if (view.filterCheckBoxes[i].isSelected()) {
				passwordFilters[i] = true;
				amountSelected++;
			} else {
				passwordFilters[i] = false;
			}
		}

		// if there is only one filter selected
		// dont allow user to deselected
		if (amountSelected == 1) {
			for (int i = 0; i < view.filterCheckBoxes.length; i++) {
				if (view.filterCheckBoxes[i].isSelected()) {
					view.filterCheckBoxes[i].setEnabled(false);
				}
			}
		} else {
			for (int i = 0; i < view.filterCheckBoxes.length; i++) {
				if (view.filterCheckBoxes[i].isSelected()) {
					view.filterCheckBoxes[i].setEnabled(true);
				}
			}
		}
		// reset for every time user changes the filters
		amountSelected = 0;

	}

	// displays password with the current filtrs
	public void displayPassword() {

		// creates the password using the given character rules
		password = model.createPassword(passwordFilters, characterLength);
		// set text to password
		view.passwordField.setText(password);
		// adjust the size the password based on the length
		int adjustedSize = Math.max(11, 75 - characterLength);
		view.passwordField.setFont(WebsiteFont.PasswordFont.getDynamicFont(adjustedSize));

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// if the generate button is clicked
		if (e.getSource() == view.generateButton) {

			// display the password with the current settings
			displayPassword();

			// if the copy to clipboard button is clicked
		} else if (e.getSource() == view.copyToClipboardButton) {

			// copys password to user's clipboard
			model.copyToClipboard(password);

		}

		// for loop
		// checks if any of the filters are clicked
		for (int i = 0; i < view.filterCheckBoxes.length; i++) {

			if (e.getSource() == view.filterCheckBoxes[i]) {
				// update new password with filters
				filterValidation();
				displayPassword();

			}
		}

	}

	// JSlider action listener
	@Override
	public void stateChanged(ChangeEvent e) {

		// every time the character length slider is moved
		if (e.getSource() == view.characterLengthSlider) {

			// get the value of the slider to the charactert length
			characterLength = view.characterLengthSlider.getValue();
			// sets text
			view.characterLengthCountLabel.setText("Character Length: " + characterLength);
			// updates password
			displayPassword();
		}

	}

}
