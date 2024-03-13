package main.java.com.ubo.tp.message.ihm;

import javax.swing.JFrame;

public interface ViewObserver {
	
	void notifyRegister (String name, String tag, String mdp);
	
	void notifySignup (String tag, String mdp);

}
