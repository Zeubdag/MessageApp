package main.java.com.ubo.tp.message.ihm;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.com.ubo.tp.message.ihm.session.Session;
import main.java.com.ubo.tp.message.ihm.view.CreateMessage;
import main.java.com.ubo.tp.message.ihm.view.Menu;
import main.java.com.ubo.tp.message.ihm.view.ReadMessage;
import main.java.com.ubo.tp.message.ihm.view.RegisterUser;
import main.java.com.ubo.tp.message.ihm.view.SignIn;

/**
 * Classe de la vue principale de l'application.
 */
public class MessageAppMainView {

	protected Set<ViewObserver> observer;
	
	protected Set<MessagesViewObserver> messagesObserver;

	public RegisterUser viewRegister;
	
	public SignIn viewSignIn;
	
	public Menu viewMenuBar;
	
	public ReadMessage viewReadMessage;
	
	public CreateMessage viewCreateMessage;
	
	public JPanel jPanel;
	
	public Boolean isConnected;
	
	public Session session;

	public MessageAppMainView(Session session) {
		this.observer = new HashSet<>();
		this.messagesObserver = new HashSet<>();
		this.viewRegister = new RegisterUser();
		this.viewSignIn = new SignIn();
		this.viewReadMessage = new ReadMessage();
		this.viewMenuBar = new Menu();
		this.viewCreateMessage = new CreateMessage();
		this.isConnected = false;
		this.session = session;
	}

	public void initialisation(JFrame mainFrame) {
		Image image;
		try {
			image = ImageIO.read(new File("Babau.png"));
			jPanel = new JPanel();
			mainFrame.add(jPanel);
			jPanel.setLayout(new GridBagLayout());

			Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			mainFrame.setIconImage(image);
			mainFrame.setTitle("Message App");

			mainFrame.setMinimumSize(screenSize);
			
			this.viewMenuBar.viewMenuBar(mainFrame, this);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void refreshMenu (JFrame mainFrame) {
		this.viewMenuBar.refreshMenuBar(mainFrame, this);
	}
	
	public void blankPanel (JFrame mainFrame) {
		mainFrame.remove(this.jPanel);
		this.jPanel = new JPanel();
		this.jPanel.setLayout(new GridBagLayout());
		mainFrame.add(this.jPanel);
		mainFrame.revalidate();
		mainFrame.repaint();
	}

	public File fileChooser() {
		JFrame frame = new JFrame();
		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.showOpenDialog(frame);
		return fc.getSelectedFile();
	}

	public void addObserver(ViewObserver obs) {
		this.observer.add(obs);
	}
	
	public void addMessageObserver(MessagesViewObserver obs) {
		this.messagesObserver.add(obs);
	}

	public void notifyObserverRegister(JTextField nom, JTextField tag, JTextField mdp, JFrame mainFrame) {
		for (ViewObserver obs : this.observer) {
			obs.notifyRegister(nom.getText(), tag.getText(), mdp.getText());
		}
		this.viewRegister.viewRegisterUser(mainFrame, this);
	}

	public void notifyObserverSignup(JTextField tag, JTextField mdp) {
		for (ViewObserver obs : this.observer) {
			obs.notifySignup(tag.getText(), mdp.getText());
		}
	}

	public void notifyObserverMessageSend(JTextField text) {
		for (MessagesViewObserver obs : this.messagesObserver) {
			obs.notifyCreateMessage(text.getText());
		}
		
		
	}
}
