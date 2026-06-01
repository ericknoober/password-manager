package view;

import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

/*
 * Description: This hinttextfield class allows there to be 
 * text on the extension JTextField.
 * 
 * Imported from my Grade 11 CAI program
 * 
 */

public class HintTextField extends JTextField {

	// gain
	Font gainFont = WebsiteFont.PasswordFont.getWebsiteFont();
	Font lostFont = WebsiteFont.PasswordFont.getWebsiteFont();

	public HintTextField(final String hint) {

		// sets the text to
		// the set argument
		setText(hint);
		// if the JTextField is not clicked
		setFont(lostFont);

		// determines if the JTextField
		// is being clicked
		this.addFocusListener(new FocusAdapter() {

			// when the textbox is not clicked
			@Override
			public void focusGained(FocusEvent e) {

				// once text box is clicked
				// checks for the username or password text
				if (getText().equals(hint)) {

					// sets the JTextField to be empty
					// allows the user to text
					setText("");
					setFont(gainFont);

				}

				else {

					// sets the text to
					// the current text
					setText(getText());
					setFont(gainFont);

				}

			}

			// when the textbox is not clicked
			@Override
			public void focusLost(FocusEvent e) {

				// if not text is entered yet
				// or the text is the same
				if (getText().equals(hint) || getText().length() == 0) {

					// set the text as the argument
					setText(hint);
					setFont(lostFont);

				} else {

					// leave the text the same
					setText(getText());
					setFont(gainFont);

				}

			}

		});

	}

}