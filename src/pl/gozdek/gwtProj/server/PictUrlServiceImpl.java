package pl.gozdek.gwtProj.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import pl.gozdek.gwtProj.client.PictUrlService;

public class PictUrlServiceImpl  extends RemoteServiceServlet implements
PictUrlService {

	private static final long serialVersionUID = 684483278612357380L;
	static Integer n = 0;
	ArrayList<String> images = new ArrayList<String>(); 
	
	public PictUrlServiceImpl() {
		images.add("http://www.buzzlol.com/wp-content/uploads/2011/08/meme-funny-Not-Sure-If....jpg");
		images.add("http://i1.kwejk.pl/site_media/obrazki/2012/10/99c5dee98c069fd943d7f319d3d3d856.jpeg?1350986584");
		images.add("http://wd10.photoblog.pl/np5/201110/CF/106591319.jpg");
		images.add("http://i1.kwejk.pl/site_media/obrazki/2012/10/77089885ad4c953b282d984c8a42194a.jpg?1351022354");
		images.add("http://weknowmemes.com/wp-content/uploads/2011/09/obama-not-bad-face-288x250.jpg");
	}
	
	@Override
	public String getRandomPictUrl(String token) {
		if (AuthServiceImpl.isTokenValidStatic(token)) { 
			int max = images.size();
			int min = 0;
			n = (int)(Math.random() * ( max - min ));
			System.out.println("N= " + n);
			System.out.println("N.size = " + images.size());
			return images.get(n);
		}
		return null;
	}

	@Override
	public String getNextPictUrl(String token) {
		if (AuthServiceImpl.isTokenValidStatic(token)) {
			System.out.println("N= " + n);
			return images.get((++n) % images.size());
		}
		return null;		
	}

	@Override
	public String getPrevPictUrl(String token) {
		if (AuthServiceImpl.isTokenValidStatic(token)) {
			System.out.println("N= "+n);
			if(n == 0)
				n = images.size();
			return images.get((--n) % images.size());
		}
		return null;		
	}
	
	@Override
	public String getFirstPictUrl(String token) {
		if (AuthServiceImpl.isTokenValidStatic(token)) {
			n = 0;
			System.out.println("N= "+n);
			return images.get(n);
		}
		return null;		
	}
	
	@Override
	public String getLastPictUrl(String token) {
		if (AuthServiceImpl.isTokenValidStatic(token)) {
			n = images.size() - 1;
			System.out.println("N= "+n);
			return images.get(n);
		}
		return null;		
	}

	@Override
	public String sendPictUrl(String url) {
		images.add(url);
		//System.out.println("URL		" + url);
		return images.get(images.size() - 1);
	}

}
