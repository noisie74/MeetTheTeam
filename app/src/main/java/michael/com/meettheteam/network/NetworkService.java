package michael.com.meettheteam.network;

import java.util.List;

import michael.com.meettheteam.model.Contacts;
import rx.Observable;
import retrofit2.http.GET;

/**
 * Network
 */

public interface NetworkService {

    @GET("/contacts")
    Observable<List<Contacts>> getContacts();
}
