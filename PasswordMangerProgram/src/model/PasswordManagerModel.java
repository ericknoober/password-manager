package model;

import java.util.List;

import de.slackspace.openkeepass.KeePassDatabase;
import de.slackspace.openkeepass.domain.Entry;
import de.slackspace.openkeepass.domain.EntryBuilder;
import de.slackspace.openkeepass.domain.Group;
import de.slackspace.openkeepass.domain.GroupBuilder;
import de.slackspace.openkeepass.domain.KeePassFile;
import de.slackspace.openkeepass.domain.KeePassFileBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Description: This is the password manager model class that controls
 * the password database and grabs specific information about each entry.
 * It also sorts the entries based on a criteria for the dashboard class
 * 
 * https://github.com/cternes/openkeepass?tab=readme-ov-file
 *These extra libraries are requried to access the other libraries
 * https://mvnrepository.com/artifact/org.simpleframework/simple-xml/2.7.1
 * https://mvnrepository.com/artifact/com.madgag.spongycastle/core/1.58.0.0
 * 
 * https://www.geeksforgeeks.org/java/localdate-isbefore-method-in-java-with-examples/
 * https://stackoverflow.com/questions/44650075/localdatetime-in-seconds
 * 
 * Note: a small workaround to add password entries. kdbx file makes a clone of the current 
 * group of password and saves that
 */

public class PasswordManagerModel {

	// updated databse
	public KeePassFile updatedDb;

	// opening database variables
	String masterPassword;
	KeePassDatabase database;
	public KeePassFile dataFile;

	public PasswordManagerModel() {

		// load inital database
		reloadDataBase();

	}

	// reopens the database after every move due
	// to duplication needed
	public void reloadDataBase() {
		// opens the database with a master password
		masterPassword = "8agAa1ubb1os2tEfpFi4";
		database = KeePassDatabase.getInstance("KyLockDataBase.kdbx");
		dataFile = database.openDatabase(masterPassword);
	}

	// add an entru
	public void addEntry(String title, String username, String password, String tag) {

		// reload database
		reloadDataBase();

		// get the time for the dashboard oldest entry
		LocalDateTime creationTime = LocalDateTime.now();
		// format the time
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatted = creationTime.format(formatter);

		// New entry
		// add a title, username, password, tag, and time stamp
		Entry newEntry = new EntryBuilder().title(title).username(username).password(password).addTag(tag)
				.addTag(formatted).build();

		// Build a new KeePass file while modifying the target group
		KeePassFileBuilder builder = new KeePassFileBuilder(dataFile);

		// create an duplicated group with the old entries
		List<Group> group1 = dataFile.getTopGroups();
		if (group1.get(0).getName().equals("User1")) {
			Group updatedGroup = new GroupBuilder(group1.get(0)).name("Duplicated Group").addEntry(newEntry).build();
			builder.addTopGroups(updatedGroup);
		}

		// build the updated databse
		updatedDb = builder.build();
		// save the database
		KeePassDatabase.write(updatedDb, masterPassword, "KyLockDataBase.kdbx");

	}

	// delete an entry
	public void deleteEntry(int currentIndex) {

		// reload the database with new entries
		reloadDataBase();

		// create a new file with the database
		KeePassFileBuilder builder = new KeePassFileBuilder(dataFile);

		// get the groups from the database
		List<Group> group1 = dataFile.getTopGroups();

		// gets the first groups entries then removes the selected entry
		group1.get(0).getEntries().remove(currentIndex);

		// rebuild the updated database without the selected entry
		if (group1.get(0).getName().equals("User1")) {
			Group updatedGroup = new GroupBuilder(group1.get(0)).name("Duplicated Group").build();
			builder.addTopGroups(updatedGroup);
		}

		// build database
		updatedDb = builder.build();
		// svae database
		KeePassDatabase.write(updatedDb, masterPassword, "KyLockDataBase.kdbx");

	}

	// gets the current data size
	public int getDataSize() {

		// gets the new data size
		return (updatedDb.getGroups().get(1).getEntries().size());

	}

	// gets the old data size
	// used for inital data length
	public int getPreviousDataSize() {

		// gets the old data size
		return (dataFile.getGroups().get(1).getEntries().size());

	}

	// gets entry information excluding the time stamp
	public String[] getEntryInfo(int index) {

		// return an array of the selected entry
		return new String[] { dataFile.getEntries().get(index).getTitle(),
				dataFile.getEntries().get(index).getUsername(), dataFile.getEntries().get(index).getPassword(),
				dataFile.getEntries().get(index).getTags().get(0) };
	}

	// gets the top tittle for the dashboard
	public String getTopTitle() {

		// variables for the top title and entry amount
		String highestEntryTitle = null;
		int highestEntryCount = 0;
		int count;

		for (int i = 0; i < getPreviousDataSize(); i++) {

			// keeps a count
			count = 0;

			for (int j = 0; j < getPreviousDataSize() - 1; j++) {

				// if the title is the same as other titles
				if (dataFile.getEntries().get(i).getTitle().equals(dataFile.getEntries().get(j).getTitle())) {

					// increase count
					count++;

				}

			}

			// find the entry title with the highest count
			if (count > highestEntryCount) {
				highestEntryTitle = dataFile.getEntries().get(i).getTitle();
				highestEntryCount = count;
			}

		}

		// return highest title and count
		return highestEntryTitle + ": " + (highestEntryCount + 1);
	}

	// gets the tag count
	public int[] getTagsCount() {

		// tag count for login, card, other
		int[] tagsCount = { 0, 0, 0 };
		String[] tagsName = { "Login", "Card", "Other" };

		for (int i = 0; i < getPreviousDataSize(); i++) {

			for (int j = 0; j < tagsName.length; j++) {

				// checks how many entries share the same tag
				if (dataFile.getEntries().get(i).getTags().get(0).equals(tagsName[j])) {

					// increase tag count
					tagsCount[j]++;

				}

			}

		}

		// return tags count
		return tagsCount;

	}

	// get the oldest entry
	public String getOldestEntry() {

		// formats the time
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// variable for oldest entry and its time
		Entry oldestEntry = null;
		LocalDateTime oldestTime = null;

		// validations if there is no entries
		if (getPreviousDataSize() != 0) {
			// loops thorough array for the oldest time
			for (int i = 0; i < getPreviousDataSize(); i++) {

				String timeStr = dataFile.getEntries().get(i).getTags().get(1);
				LocalDateTime entryTime = LocalDateTime.parse(timeStr, formatter);

				// is the current entry time is older than the oldest time
				if (oldestTime == null || entryTime.isBefore(oldestTime)) {
					oldestTime = entryTime;
					oldestEntry = dataFile.getEntries().get(i);

				}
			}

			//return the time and entry data
			return "Date: " + oldestTime.getMonth() + " " + oldestTime.getDayOfMonth() + ", " + oldestTime.getYear()
					+ "\nTitle: " + oldestEntry.getTitle() + "\nUsername: " + oldestEntry.getUsername() + "\nPassword: "
					+ oldestEntry.getPassword() + "\n\nMake sure to change \nyour password every \nthree months.";

		} else {
			//returns none if there is not data
			return "None";
		}
	}

}
