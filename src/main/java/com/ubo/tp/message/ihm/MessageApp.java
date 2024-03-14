package main.java.com.ubo.tp.message.ihm;

import java.io.File;
import java.util.HashSet;
import java.util.UUID;

import javax.swing.JFrame;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.core.directory.IWatchableDirectory;
import main.java.com.ubo.tp.message.core.directory.WatchableDirectory;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISessionObserver;
import main.java.com.ubo.tp.message.ihm.session.Session;

/**
 * Classe principale l'application.
 *
 * @author S.Lucas
 */
public class MessageApp implements ViewObserver, ISessionObserver, MessagesViewObserver{
	/**
	 * Base de données.
	 */
	protected IDatabase mDatabase;

	/**
	 * Gestionnaire des entités contenu de la base de données.
	 */
	protected EntityManager mEntityManager;

	/**
	 * Vue principale de l'application.
	 */
	protected MessageAppMainView mMainView;

	/**
	 * Classe de surveillance de répertoire
	 */
	protected IWatchableDirectory mWatchableDirectory;

	/**
	 * Répertoire d'échange de l'application.
	 */
	protected String mExchangeDirectoryPath;

	/**
	 * Nom de la classe de l'UI.
	 */
	protected String mUiClassName;
	

	protected JFrame mainFrame;
	
	protected Session session;
	

	/**
	 * Constructeur.
	 *
	 * @param entityManager
	 * @param database
	 */
	public MessageApp(IDatabase database, EntityManager entityManager) {
		this.mDatabase = database;
		this.mEntityManager = entityManager;
		this.session = new Session();
		this.mMainView = new MessageAppMainView(this.session);
		this.mainFrame = new JFrame("MessageApp");
		this.mMainView.addObserver(this);
		this.mMainView.addMessageObserver(this);
		this.session.addObserver(this);
	}

	/**
	 * Initialisation de l'application.
	 */
	public void init() {
		// Init du look and feel de l'application
		this.initLookAndFeel();

		// Initialisation du répertoire d'échange
		this.initDirectory();

		// Initialisation de l'IHM
		this.initGui();
	}

	/**
	 * Initialisation du look and feel de l'application.
	 */
	protected void initLookAndFeel() {
	}

	/**
	 * Initialisation de l'interface graphique.
	 */
	protected void initGui() {
		this.mMainView.initialisation(mainFrame);
	}

	/**
	 * Initialisation du répertoire d'échange (depuis la conf ou depuis un file
	 * chooser). <br/>
	 * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de
	 * pouvoir utiliser l'application</b>
	 */
	protected void initDirectory() {
		this.mMainView.fileChooser();
	}

	/**
	 * Indique si le fichier donné est valide pour servir de répertoire d'échange
	 *
	 * @param directory , Répertoire à tester.
	 */
	protected boolean isValideExchangeDirectory(File directory) {
		// Valide si répertoire disponible en lecture et écriture
		return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
				&& directory.canWrite();
	}

	/**
	 * Initialisation du répertoire d'échange.
	 *
	 * @param directoryPath
	 */
	protected void initDirectory(String directoryPath) {
		mExchangeDirectoryPath = directoryPath;
		mWatchableDirectory = new WatchableDirectory(directoryPath);
		mEntityManager.setExchangeDirectory(directoryPath);

		mWatchableDirectory.initWatching();
		mWatchableDirectory.addObserver(mEntityManager);
	}

	public void show() {
		this.mainFrame.setVisible(true);
	}
	
	

	@Override
	public void notifyRegister(String name, String tag, String mdp) {
		User u = new User(UUID.randomUUID(), tag, name, mdp, new HashSet<>(), "");
		this.mDatabase.addUser(u);
	}

	@Override
	public void notifySignup(String tag, String mdp) {
		for(User u : this.mDatabase.getUsers()) {
			if (u.getUserTag().equals(tag)) {
				if (u.getUserPassword().equals(mdp)) {
					this.mMainView.session.connect(u);
					this.mMainView.refreshMenu(mainFrame);
					this.mMainView.blankPanel(mainFrame);
				}
			}
		}
		
	}

	@Override
	public void notifyLogin(User connectedUser) {
		this.mMainView.isConnected = true;
	}

	@Override
	public void notifyLogout() {
		this.mMainView.isConnected = false;
	}

	@Override
	public void notifyCreateMessage(String text) {
		Message m = new Message(this.session.getConnectedUser(), text);
		this.mDatabase.addMessage(m);
		this.mMainView.viewReadMessage.viewReadMessage(m, this.mainFrame, this.mMainView);
	}
}
