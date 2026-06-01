package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.PasswordManagerModel;
import view.PasswordManagerView;

/*
 * Description: This is the password manager controller class
 * where all the database of entries are handled and shown
 * 
 * Note: When adding a entry and checking its password
 * it shows up encrypted and you need to reopen the frame to show the password
 */

public class PasswordManagerController implements ActionListener {

	// view
	PasswordManagerView view;
	// model
	PasswordManagerModel model;

	// amount of buttons / entries
	int buttonAmount = 60;
	// current button being clicked
	// for password viewer
	int currentEntryIndex = 0;

	public PasswordManagerController() {

		model = new PasswordManagerModel();
		view = new PasswordManagerView();

		// create the buttonAmount of button
		view.createButtons(buttonAmount);
		// action listeners
		view.addButton.addActionListener(this);
		view.addEntryButton.addActionListener(this);
		view.closeButton.addActionListener(this);
		view.deleteButton.addActionListener(this);

		// makes visible the previous saved password entries
		for (int i = 0; i < buttonAmount; i++) {
			if (i <= model.getPreviousDataSize() - 1) {
				view.entryButtons[i].setVisible(true);
				view.entryButtons[i].setText(model.dataFile.getEntries().get(i).getTitle());
			}

			view.entryButtons[i].addActionListener(this);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// if the add button is clicked
		if (e.getSource() == view.addButton) {

			// if frame is opened on the 14th entry
			if (model.getPreviousDataSize() + 1 < buttonAmount && model.getPreviousDataSize() != buttonAmount - 1) {

				// show the JOptionPane
				view.dialog.setVisible(true);

				// if the frame is oepning on the second last entry
			} else if (model.getPreviousDataSize() == buttonAmount - 1) {

				// show the JOptionPane
				view.dialog.setVisible(true);

			} else {
				// max entries have been met prompt
				JOptionPane.showInternalMessageDialog(null,
						buttonAmount + "/" + buttonAmount + " max entries have been met! Delete some entries!");
			}

			// if the add entry button in JOptionPane is clicked
		} else if (e.getSource() == view.addEntryButton) {

			// user validation
			// if the fields are all empty
			if (view.titleField.getText().equals("") || view.userField.getText().equals("")
					|| view.passField.getText().equals("")) {

				// prompt user to fill out the fields
				JOptionPane.showMessageDialog(null, "Fill out all fields!");

			} else {

				// add the entries fields to the database
				model.addEntry(view.titleField.getText(), view.userField.getText(), view.passField.getText(),
						view.tagMenu.getSelectedItem().toString());
				// make the next button visible
				makeButtonsVisisble();
				// remove the add entry JOptionPane
				view.dialog.setVisible(false);

			}

			// if the password viewer's close button is clicked
		} else if (e.getSource() == view.closeButton) {
			view.viewerDialog.setVisible(false);
		}

		// if any of the entry buttons are clicked
		for (int i = 0; i < view.entryButtons.length; i++) {
			if (e.getSource() == view.entryButtons[i]) {

				// current button
				currentEntryIndex = i;

				// information of the current entry
				String[] info = model.getEntryInfo(i);
				// set the text
				view.titleLabel.setText("Title: " + info[0]);
				view.userLabel.setText("Username: " + info[1]);
				view.passLabel.setText("Password: " + info[2]);
				view.tagLabel.setText("Tag: " + info[3]);

				// display the information is a JOptionPane
				view.viewerDialog.setVisible(true);
				// exit the for loop
				break;
				// password viewer button

				// if the delete button is clicked
			} else if (e.getSource() == view.deleteButton) {

				// making the last one invisible makes it easier to mimic autosort
				view.entryButtons[model.getPreviousDataSize() - 1].setVisible(false);
				// delete the entry
				model.deleteEntry(currentEntryIndex);
				// close the JOptionPane
				view.viewerDialog.setVisible(false);

				// updates titles
				for (int j = 0; j < buttonAmount; j++) {
					if (j <= model.getPreviousDataSize() - 1) {
						// update the text of all the entries
						view.entryButtons[j].setText(model.updatedDb.getEntries().get(j).getTitle());
					}
				}
				// exit the for loop
				break;
			}
		}

	}

	// makes buttons visible after
	// deleting or adding entries
	public void makeButtonsVisisble() {

		// makes the current button visible and sets the title
		int currentIndex = model.getDataSize() - 1;
		view.entryButtons[currentIndex].setVisible(true);
		view.entryButtons[currentIndex].setText(model.dataFile.getEntries().get(currentIndex).getTitle());
	}

}
