package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import model.BreachScannerModel;
import view.BreachScannerView;
import view.WebsiteColours;

/*
 * Description: This is the breach scanner controller class.
 * This class gets the data from the model and updates the view.
 * 
 * https://stackoverflow.com/questions/8088002/how-to-use-a-swing-timer-to-start-stop-animation
 */

public class BreachScannerController implements ActionListener {

	// view
	BreachScannerView view;
	// model
	BreachScannerModel model;

	// boolean for animation
	// if the check button is clicked twice
	// there is no need to increase the length of
	// the scrollPane
	boolean checkedOnce = true;

	public BreachScannerController() {

		view = new BreachScannerView();
		model = new BreachScannerModel();

		// action listener
		view.passwordButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// if the password button is pressed
		if (e.getSource() == view.passwordButton) {

			// scroll animation
			// moves the panels lower to show the
			// leaked prompt label
			scrollAnimation();

			try {
				// if your password has been leaked
				if (model.checkPassword(view.passwordArea.getText())) {
					// update the text
					view.isPwnedLabel.setText("Your password has been leaked " + model.getLeakedAmount() + " times.");
					view.extraInformationLabel.setText("Your password has been in a data breach! If this is still "
							+ "your password, change it immediately!");
					// change the background colour to red
					view.breachBox.setBackground(WebsiteColours.DARKRED.getWebsiteColor());

					// if your password has never been leaked
				} else {
					// update text
					view.isPwnedLabel.setText("Your password never been leaked!");
					view.extraInformationLabel.setText("Your password is considered strong and has never "
							+ "been in a data breach! Make sure to change your password every three months.");
					// change the background colour to green
					view.breachBox.setBackground(WebsiteColours.DARKGREEN.getWebsiteColor());

				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			// if the check button was not clicked twice
			if (checkedOnce) {
				view.updatePanelPostions();
				checkedOnce = false;
			}

			// make the leaked prompt visible
			view.isPwnedLabel.setVisible(true);
			view.breachBox.setVisible(true);
		}

	}

	// scroll animation that automatically
	// moves the scroll when the check button is clicked
	public void scrollAnimation() {

		// timer for frame animation intervals
		Timer timer = new Timer(1, new ActionListener() {
			// get current position in the scroll
			int start = view.vertbar.getValue();

			// includes action performed inside the action listener
			// performs action every second
			public void actionPerformed(ActionEvent e) {
				// keeps running until the scrollpane is
				// in the correct position
				if (start < 550) {
					// update scrollpane
					view.vertbar.setValue(start);
					// change frame speed
					start += 7;
				} else {
					// stop when the target has been
					// reached
					((Timer) e.getSource()).stop();
				}
			}
		});

		// starts timmer
		timer.start();
	}

}
