package pl.gozdek.gwtProj.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("randompictservice")
public interface PictUrlService  extends RemoteService {
	String getRandomPictUrl(String token);
	String getNextPictUrl(String token);
	String getPrevPictUrl(String token);
	String getFirstPictUrl(String token);
	String getLastPictUrl(String token);
	String sendPictUrl(String url);
}
