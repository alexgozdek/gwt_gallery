package pl.gozdek.gwtProj.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


public class GwtGallery implements EntryPoint {

	private final AuthServiceAsync greetingService = GWT.create(AuthService.class);
	private final PictUrlServiceAsync randomPictService = GWT.create(PictUrlService.class);
		
	private Boolean loggedIn = false;
	final Button sendButton = new Button("Log In");
	final Label statusLabel = new Label();
	
	final Button sendPictButton = new Button("Send");
	final TextBox sendPictField = new TextBox();	
	final Label pictLabel = new Label();
	
	final TextBox userField = new TextBox();
	final PasswordTextBox passField = new PasswordTextBox();
	
	final Image imageField = new Image();
	final Button getRandomPictButton = new Button("GetRandomPict");
	final Button getNextPictButton = new Button("GetNextPict");
	final Button getPrevPictButton = new Button("GetPrevPict");
	final Button getFirstPictButton = new Button("GetFirstPict");
	final Button getLastPictButton = new Button("GetLastPict");

	
	private String token = "";
	
	class GetImageClickHandler  implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			randomPictService.getRandomPictUrl(token, 
					new AsyncCallback<String>() {
						public void onFailure(Throwable caught) {
						}
						public void onSuccess(String result) {
							if (result != null) {
								imageField.setUrl(result);
								statusLabel.setText(result);
							}							
						}
					});				
		}		
	}
	
	class GetNextPictClickHandler  implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			randomPictService.getNextPictUrl(token, 
				new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
					}
					public void onSuccess(String result) {
						if (result != null) {
							imageField.setUrl(result);
							statusLabel.setText(result);
						}							
					}
				});				
		}		
	}
	
	class GetPrevPictClickHandler  implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			randomPictService.getPrevPictUrl(token, 
				new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
					}
					public void onSuccess(String result) {
						if (result != null) {
							imageField.setUrl(result);
							statusLabel.setText(result);
						}							
					}
				});				
		}		
	}
	
	class GetFirstPictClickHandler  implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			randomPictService.getFirstPictUrl(token, 
				new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
					}
					public void onSuccess(String result) {
						if (result != null) {
							imageField.setUrl(result);
							statusLabel.setText(result);
						}							
					}
				});				
		}		
	}
	
	class GetLastPictClickHandler  implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			randomPictService.getLastPictUrl(token, 
				new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
					}
					public void onSuccess(String result) {
						if (result != null) {
							imageField.setUrl(result);
							statusLabel.setText(result);
						}							
					}
				});				
		}		
	}
		
	
	class SendButtonHandler implements ClickHandler, KeyUpHandler {
		void send() {
			greetingService.isValidUserPassword(userField.getText(),passField.getText(),
				new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						setLoggedIn(false);
					}
					public void onSuccess(String result) {
						if (result != null)
						if (result.length() > 0) {
							setLoggedIn(true);
							setToken(result);
						} else {
							setLoggedIn(false);
							setToken(result);
						}						
					}
				});		
		}		
		
		@Override
		public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				send();
			}
		}

		@Override
		public void onClick(ClickEvent event) {
			send();
		}
	}
	
	class SendPictUrlHandler implements ClickHandler, KeyUpHandler {
		void send() {
			randomPictService.sendPictUrl(sendPictField.getText(),
				new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
					}
					public void onSuccess(String result) {
						imageField.setUrl(result);						
						statusLabel.setText(result);						
					}
				});		
		}		
		
		@Override
		public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				send();
			}
		}

		@Override
		public void onClick(ClickEvent event) {
			send();
		}
	}
	
	
	public void onModuleLoad() {
		VerticalPanel holder = new VerticalPanel();
		holder.getElement().setId("holder");
		holder.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		RootPanel.get("main").add(holder);
		holder.add(userField);
		holder.add(passField);
		holder.add(sendButton);	
		
		holder.add(statusLabel);
		//holder.add(imageField);
		
		VerticalPanel holderPict = new VerticalPanel();
		holderPict.getElement().setId("holderPict");
		holderPict.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		RootPanel.get("main").add(holderPict);
		holderPict.add(imageField);		
		
		HorizontalPanel holder2 = new HorizontalPanel();
		holder2.getElement().setId("holder2");
		holder2.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		RootPanel.get("main").add(holder2);		
		holder2.add(getRandomPictButton);
		holder2.add(getNextPictButton);
		holder2.add(getPrevPictButton);
		holder2.add(getFirstPictButton);
		holder2.add(getLastPictButton);	
	
		// Focus the cursor on the name field when the app loads
		userField.setFocus(true);
		userField.selectAll();

		// Add a handler to send the name to the server
		SendButtonHandler handler = new SendButtonHandler();
		
		sendButton.addClickHandler(handler);
		userField.addKeyUpHandler(handler);
		passField.addKeyUpHandler(handler);
		
		GetImageClickHandler handlerRandom = new GetImageClickHandler();
		getRandomPictButton.addClickHandler(handlerRandom);
		
		GetNextPictClickHandler handlerNext = new GetNextPictClickHandler();
		getNextPictButton.addClickHandler(handlerNext);
		
		GetPrevPictClickHandler handlerPrev = new GetPrevPictClickHandler();
		getPrevPictButton.addClickHandler(handlerPrev);
		
		GetFirstPictClickHandler handlerFirst = new GetFirstPictClickHandler();
		getFirstPictButton.addClickHandler(handlerFirst);
		
		GetLastPictClickHandler handlerLast = new GetLastPictClickHandler();
		getLastPictButton.addClickHandler(handlerLast);
		
		SendPictUrlHandler handlerSendPict = new SendPictUrlHandler();
		sendPictButton.addClickHandler(handlerSendPict);
		sendPictButton.getElement().setId("sendPictButton");
		sendPictField.addKeyUpHandler(handlerSendPict);
		
	}
	
	public void setSendPictBox() {
		VerticalPanel holder3 = new VerticalPanel();
		holder3.getElement().setId("holder3");
		holder3.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		RootPanel.get("main").add(holder3);
		holder3.add(pictLabel);
		holder3.add(sendPictField);
		holder3.add(sendPictButton);
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		if (loggedIn) {
			statusLabel.setText("Zalogowany");
			setSendPictBox();
		}
		else statusLabel.setText("Nie Zalogowany");
		this.loggedIn = loggedIn;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {		
		this.token = token;
	}
}