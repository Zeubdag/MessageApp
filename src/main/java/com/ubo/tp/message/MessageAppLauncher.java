package main.java.com.ubo.tp.message;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.Database;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.ihm.DatabaseObserver;
import main.java.com.ubo.tp.message.ihm.MessageApp;

/**
 * Classe de lancement de l'application.
 *
 * @author S.Lucas
 */
public class MessageAppLauncher {


	/**
	 * Launcher.
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		IDatabase database = new Database();
		EntityManager entityManager = new EntityManager(database);
	
		DatabaseObserver logObserver = new DatabaseObserver();
		database.addObserver(logObserver);

		MessageApp messageApp = new MessageApp(database, entityManager);
		messageApp.init();
		messageApp.show();
	}
}
