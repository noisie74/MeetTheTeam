package michael.com.meettheteam.network;

import java.util.List;

import michael.com.meettheteam.model.Contacts;
import michael.com.meettheteam.model.Response;
import rx.Observable;
import retrofit2.http.GET;

/**
 * Created by Mikhail on 2/11/17.
 */

public interface NetworkService {

    @GET("/contacts")
    Observable<Response> getContacts();
}
