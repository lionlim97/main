package Tasks;

/**
 * Represents a task in the Duke program.
 */
public class Task {
    private final String description;
    private boolean isDone;
    private boolean isReminder;
    private String remindTime;

    /**
     * Creates Task object.
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.isReminder = false;
        this.remindTime = "";
    }

    public String getType() {
        return "void";
    }

    /**
     * Checks whether the task is completed.
     * @return This returns a tick or cross depending on the boolean value of isDone
     */
    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    private String getReminderStatus() {
        return (isReminder ? "[HR]" : "[NR]");
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDescription() {
        String[] split = description.split(" ");
        String taskDescription = "";
        for (int i = 0; i < split.length; i++) {
            if (!split[i].equals(getModCode())) {
                taskDescription += split[i] + " ";
            }
        }
        return taskDescription;
    }

    public String remindTimeToString() {
        return "[<R" + remindTime + "/R>]";
    }
    public String toString() {
        if (isReminder) {
            return "[" + getStatusIcon()
                    + "] " + getReminderStatus()  + remindTimeToString() + getDescription();
        } else {
            return "[" + getStatusIcon() + "] " + getReminderStatus()  + remindTimeToString() + getDescription();
        }
    }

    public String getDateTime(){
        return "void";
    }

    public String getModCode() {
        String[] split = description.trim().split(" ");
        return split[0];
    }

    public void setRemindTime(String time) {
        remindTime = time;
    }

    public String getRemindTime() {
        return this.remindTime;
    }

    public void setReminder(boolean reminder) {
        this.isReminder = reminder;
    }

    public boolean getReminder() {
        return this.isReminder;
    }
}