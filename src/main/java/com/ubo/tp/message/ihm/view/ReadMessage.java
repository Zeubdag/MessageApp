package main.java.com.ubo.tp.message.ihm.view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.ihm.MessageAppMainView;

public class ReadMessage {
	
	public void viewReadMessage(Message msg, JFrame mainFrame, MessageAppMainView view) {
		mainFrame.remove(view.jPanel);
		view.jPanel = new JPanel();
		view.jPanel.setLayout(new GridBagLayout());
		mainFrame.add(view.jPanel);
		Container c = new Container();
		c.setLayout(new GridBagLayout());
		c.add(new Label("User : " + msg.getSender().getName()), new GridBagConstraints(1, 1, 3, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 2, 1));
		c.add(new Label(msg.getText()), new GridBagConstraints(1, 2, 3, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 2, 1));
		view.jPanel.add(c, new GridBagConstraints(1, 1, 3, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 2, 1));
		mainFrame.revalidate();
		mainFrame.repaint();
	}

}
