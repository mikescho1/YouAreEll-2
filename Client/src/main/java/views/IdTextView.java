package views;

import controllers.IdController;
import models.Id;

import java.util.ArrayList;

public class IdTextView {
    private Id idToDisplay;

    public IdTextView(Id idToDisplay) {
        this.idToDisplay = idToDisplay;

    }

    public IdTextView() {
    }

    ;

    @Override
    public String toString() {
        return String.format(
                "*********************************\n" +
                        "Name: %s\n" +
                        "GitHub ID: %s\n" +
                        "*********************************\n\n",
                idToDisplay.getName(), idToDisplay.getGitHubId());
    }

    public String toString(ArrayList<Id> idList) {
        String output = "";
        for (Id id : idList) {
            output += new IdTextView(id).toString();
        }
        return output;
    }
}
