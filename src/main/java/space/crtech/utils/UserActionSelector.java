package space.crtech.utils;


import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;

public abstract class UserActionSelector {
    ArrayList<UserAction> userActions;
    private static String initialPompt = "Available actions: ";
    private static String actionsPrompt = "Select action: ";
    private static String outOfBoundsPrompt = "Incorrect action id!";
    private static PrintStream outputStream = System.out;

    public UserActionSelector() {
        userActions = new ArrayList<>();
    }

    public UserActionSelector(Collection<UserAction> actions) {
        userActions = (ArrayList<UserAction>) actions;
    }

    public void run() {
        outputStream.println(initialPompt);
        for (UserAction action: userActions) {
            int actionID = userActions.indexOf(action);
            outputStream.println("\t" + actionID + " - " + action.getName());
        }
        int selectedAction;
        do {
            outputStream.println(actionsPrompt);
            selectedAction = getInput();
        } while (!(selectedAction>=0 && selectedAction<userActions.size()) && printlnAndReturnTrue(outOfBoundsPrompt));
        userActions.get(selectedAction).act();
    }

    public static void run(Collection<UserAction> actions, Callable<Integer> getInput) throws Exception {
        ArrayList<UserAction> actionsAsArrayList = (ArrayList<UserAction>) actions;
        outputStream.println(initialPompt);
        for (UserAction action: actionsAsArrayList) {
            int actionID = actionsAsArrayList.indexOf(action);
            outputStream.println("\t" + actionID + " - " + action.getName());
        }
        int selectedAction;
        do {
            outputStream.println(actionsPrompt);
            selectedAction = getInput.call();
        } while (!(selectedAction>=0 && selectedAction<actionsAsArrayList.size()) && printlnAndReturnTrue(outOfBoundsPrompt));
        actionsAsArrayList.get(selectedAction).act();
    }

    public abstract int getInput();

    private static boolean printlnAndReturnTrue(String prompt) {
        outputStream.println(prompt);
        return true;
    }

    public static void setActionsPrompt(String newActionsPrompt) {
        actionsPrompt = newActionsPrompt;
    }

    public static void setInitialPompt(String newInitialPompt) {
        initialPompt = newInitialPompt;
    }

    public static void setOutOfBoundsPrompt(String newOutOfBoundsPrompt) {
        outOfBoundsPrompt = newOutOfBoundsPrompt;
    }

    public static void setOutputStream(PrintStream newOutputStream) {
        outputStream = newOutputStream;
    }
}
