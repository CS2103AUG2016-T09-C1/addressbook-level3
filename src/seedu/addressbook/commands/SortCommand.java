package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortCommand extends Command {
	
	public static final String COMMAND_WORD = "sort";
	
	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
			+ "Sorts the address book alphabetically and displays the list.\n\t"
            + "Example: " + COMMAND_WORD;
	
	@Override
    public CommandResult execute() {
		List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
		List<ReadOnlyPerson> sorted = new ArrayList<ReadOnlyPerson>(allPersons);
		Collections.sort(sorted, Person.PersonNameComparator);
        return new CommandResult(getMessageForPersonListShownSummary(sorted), sorted);
    }
}
