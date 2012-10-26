package pl.gozdek.gwtProj.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("authservice")
public interface AuthService extends RemoteService {
	String isValidUserPassword(String login, String password);
	
	boolean isTokenValid(String token);
}
