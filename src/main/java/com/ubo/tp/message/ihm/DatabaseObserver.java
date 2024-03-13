package main.java.com.ubo.tp.message.ihm;

import main.java.com.ubo.tp.message.core.database.IDatabaseObserver;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;

public class DatabaseObserver implements IDatabaseObserver {

	@Override
	public void notifyMessageAdded(Message addedMessage) {
		System.out.println("Message added");

	}

	@Override
	public void notifyMessageDeleted(Message deletedMessage) {
		System.out.println("Message deleted");

	}

	@Override
	public void notifyMessageModified(Message modifiedMessage) {
		System.out.println("Message modified");

	}

	@Override
	public void notifyUserAdded(User addedUser) {
		System.out.println("User added");

	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		System.out.println("User deleted");

	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		System.out.println("User midified");

	}

}
