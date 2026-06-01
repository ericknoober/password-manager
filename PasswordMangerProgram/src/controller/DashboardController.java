package controller;

import model.PasswordManagerModel;
import view.DashboardView;

/*
 * Description: the dashboard controller class that updates
 * data from the model and updates view
 */

public class DashboardController {

	// view
	DashboardView view;
	// data
	PasswordManagerModel model;

	public DashboardController() {

		model = new PasswordManagerModel();
		view = new DashboardView();

		// set up the chart here to get data
		view.chartSetup(model.getPreviousDataSize(), 60);
		updateStats();
		// frame set up here to make the chart appear first
		view.frameSetup();

	}

	// updates the values shown on the dash board
	public void updateStats() {

		// chart panel
		// sets values for the amount of passwords left and used
		view.dataset.setValue("Passwords Used ", model.getPreviousDataSize());
		view.dataset.setValue("Passwords left ", 60 - model.getPreviousDataSize());

		// top entry titles
		// validation for no entries inside the database
		if (model.getTopTitle().equals("null: 0") || model.getTopTitle().equals("null: 1")) {
			view.topSite.setText("None");
		} else {
			view.topSite.setText(model.getTopTitle());
		}

		// tags
		// gets data for the tags
		int[] count = model.getTagsCount();
		// sets text from tags
		view.tagsCount[0].setText("Login: " + count[0]);
		view.tagsCount[1].setText("Card: " + count[1]);
		view.tagsCount[2].setText("Other: " + count[2]);

		// password health
		// sets text to the oldest entry
		view.passwordHealthLabel.setText(model.getOldestEntry());

	}

}
