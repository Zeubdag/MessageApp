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

public class SignIn {

	public void viewSignIn(JFrame mainFrame, MessageAppMainView view) {
		mainFrame.remove(view.jPanel);
		view.jPanel = new JPanel();
		view.jPanel.setLayout(new GridBagLayout());
		mainFrame.add(view.jPanel);
		Container c = new Container();
		c.setLayout(new GridBagLayout());
		c.add(new Label("Connexion"), new GridBagConstraints(1, 1, 3, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 2, 1));
		JTextField tag = new JTextField(20);
		c.add(tag, new GridBagConstraints(2, 3, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 2, 1));
		JTextField mdp = new JTextField(20);
		c.add(mdp, new GridBagConstraints(2, 4, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 2, 1));
		c.add(new Label("Tag : "), new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 2, 1));
		c.add(new Label("MDP : "), new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 2, 1));
		view.jPanel.add(c, new GridBagConstraints(1, 1, 3, 1, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 2, 1));
		JButton b = new JButton("Valider");
		b.addActionListener(new ButtonSignup(view, tag, mdp));
		view.jPanel.add(b, new GridBagConstraints(1, 2, 3, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 2, 1));
		mainFrame.revalidate();
		mainFrame.repaint();
	}

	class ButtonSignup extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 623290644400010296L;
		protected MessageAppMainView view;
		protected JTextField tag;
		protected JTextField mdp;

		public ButtonSignup(MessageAppMainView view, JTextField tag, JTextField mdp) {
			this.view = view;
			this.tag = tag;
			this.mdp = mdp;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.view.notifyObserverSignup(tag, mdp);
		}

	}

}
