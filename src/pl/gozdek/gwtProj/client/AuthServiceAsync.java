package pl.gozdek.gwtProj.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AuthServiceAsync {

	void isValidUserPassword(String login, String password,
			AsyncCallback<String> callback);

	void isTokenValid(String token, AsyncCallback<Boolean> callback);

}
