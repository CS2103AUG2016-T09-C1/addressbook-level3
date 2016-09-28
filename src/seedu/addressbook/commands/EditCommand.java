package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

public class EditCommand extends Command {
    
    public static final String COMMAND_WORD = "edit";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Edits a person's data from address book."
            + "Example: " + COMMAND_WORD + " 1" + " address" + " changeTo";
    
    public static final String MESSAGE_SUCCESS = "Edited data successfully";
    public static final String MESSAGE_ASKCONFIRMATION = "Are you sure you want to edit data?";

    private static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";;
    
    private final String toEdit;
    private final String changeTo;
    
    public EditCommand(int targetVisibleIndex, String toEdit, String changeTo) {
        super(targetVisibleIndex);
        this.toEdit = toEdit;
        this.changeTo = changeTo;
    }
    
    public String getEdit() {
        return toEdit;
    }
    
    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            if( toEdit == "address") {       
                target.setAddress(changeTo, true);              
            }
            else if (toEdit == "phone") {
                target.setPhone(changeTo, true);
            }
            else if(toEdit == "email") {
                target.setEmail(changeTo, true);
            }
            else {
                target.setName(changeTo);
            }
            
            ReadOnlyPerson copy = getTargetPerson();
            Person newPerson = new Person(target.getName(),
                                          target.getPhone(),
                                          target.getEmail(),
                                          target.getAddress(),
                                          target.getTags());
            
            
          addressBook.addPerson(newPerson);
          addressBook.removePerson(copy);           
          return new CommandResult(String.format(MESSAGE_SUCCESS));
        } catch (IndexOutOfBoundsException ie) {
          return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (IllegalValueException ive) {
            return new CommandResult("Invalid input");
        }
    }

}
