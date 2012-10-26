package pl.gozdek.gwtProj.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PictUrlServiceAsync {

	void getRandomPictUrl(String token, AsyncCallback<String> callback);
	void getNextPictUrl(String token, AsyncCallback<String> callback);
	void getPrevPictUrl(String token, AsyncCallback<String> callback);
	void getFirstPictUrl(String token, AsyncCallback<String> callback);
	void getLastPictUrl(String token, AsyncCallback<String> callback);
	void sendPictUrl(String url, AsyncCallback<String> callback);
}
