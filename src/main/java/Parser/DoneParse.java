package Parser;

import Commands.Command;
import Commands.DeleteCommand;
import Commands.DoneCommand;
import DukeExceptions.DukeException;
import DukeExceptions.DukeInvalidFormatException;
import Interface.*;
import Tasks.Deadline;
import Tasks.Event;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Parse Donecommand for event and deadline and return formatted command to Parser
 */
public class DoneParse extends Parse {
    private static String[] split;
    private static String[] split1;
    private static String fullCommand;
    private static final Logger LOGGER = Logger.getLogger(Parser.class.getName());


    public DoneParse(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    /**
     * @return Command which represents the parsed Deletecommand
     * @throws Exception Returned if command does not adhere to format
     */
    @Override
    public Command execute() throws Exception {
        if (fullCommand.trim().startsWith("done/e")) {
            try { //add/e module_code description /at date from time to time
                String activity = fullCommand.trim().replaceFirst("done/e", "");
                split = activity.split("/at"); //split[0] is " module_code description", split[1] is "date from time to time"
                split1 = split[0].trim().split(" ");
                if(!super.isModCode(split1[0])){
                    throw new DukeInvalidFormatException("\u2639" + " OOPS!!! The ModCode is invalid");
                }
                if (split[0].trim().isEmpty()) {
                    throw new DukeException("\u2639" + " OOPS!!! The description of a event cannot be empty.");
                }
                String[] out = DateTimeParser.EventParse(split[1]);
                return new DoneCommand("event", new Event(split[0].trim(), out[0],out[1],out[2]));
            } catch (ParseException | ArrayIndexOutOfBoundsException e) {
                LOGGER.log(Level.INFO, e.toString(), e);
                throw new DukeException("OOPS!!! Please enter in the format as follows:\n" +
                        "done/e mod_code name_of_event /at dd/MM/yyyy /from HHmm /to HHmm\n" +
                        "or done/e mod_code name_of_event /at week x day /from HHmm /to HHmm\n");
            }
        } else if (fullCommand.trim().startsWith("delete/d")) {
            try {
                String activity = fullCommand.trim().replaceFirst(("delete/d"), "");
                split = activity.split("/by");
                split1 = split[0].trim().split(" ");
                if(!super.isModCode(split1[0])){
                    throw new DukeInvalidFormatException("\u2639" + " OOPS!!! The ModCode is invalid");
                }
                if (split[0].trim().isEmpty()) {
                    throw new DukeException("\u2639" + " OOPS!!! The description of a deadline cannot be empty.");
                }
                String[] out = DateTimeParser.DeadlineParse(split[1]);
                return new DoneCommand("deadline", new Deadline(split[0].trim(), out[0],out[1]));

            } catch (ParseException | ArrayIndexOutOfBoundsException e) {
                LOGGER.log(Level.INFO, e.toString(), e);
                throw new DukeException("OOPS!!! Please enter in the format as follows:\n" +
                        "done/d mod_code name_of_event /by dd/MM/yyyy HHmm\n" +
                        "or done/d mod_code name_of_event /by week x day HHmm\n");
            }
        } else {
            throw new DukeException("\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}