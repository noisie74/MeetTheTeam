package michael.com.meettheteam.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikhail on 2/11/17.
 */

public class Response {

    @SerializedName("contacts")
    @Expose
    private List<Contacts> teamContacts = new ArrayList<Contacts>();

    public List<Contacts> getTeamContacts() {
        return teamContacts;
    }
}
