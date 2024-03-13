package main.java.com.ubo.tp.message.ihm.view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.com.ubo.tp.message.ihm.MessageAppMainView;

public class CreateMessage {
	
	public void viewCreateMessage(JFrame mainFrame, MessageAppMainView view) {
		mainFrame.remove(view.jPanel);
		view.jPanel = new JPanel();
		view.jPanel.setLayout(new GridBagLayout());
		mainFrame.add(view.jPanel);
		Container c = new Container();
		c.setLayout(new GridBagLayout());
		c.add(new Label("Envoyer un message"), new GridBagConstraints(1, 1, 3, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 2, 1));
		JTextField text = new JTextField(50);
		c.add(text, new GridBagConstraints(2, 2, 3, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 2, 1));
		view.jPanel.add(c, new GridBagConstraints(1, 1, 3, 1, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 2, 1));
		JButton b = new JButton("Envoyer");
		
		view.jPanel.add(b, new GridBagConstraints(1, 2, 3, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 2, 1));
		mainFrame.revalidate();
		mainFrame.repaint();
	}
	
	class SendMessage extends AbstractAction {

		protected MessageAppMainView view;
		protected JTextField text;

		public SendMessage(MessageAppMainView view, JTextField text) {
			this.view = view;
			this.text = text;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.view.notifyObserverMessageSend(text);
		}

	}
}
