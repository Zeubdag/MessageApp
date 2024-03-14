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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.com.ubo.tp.message.ihm.MessageAppMainView;

public class RegisterUser {

	public void viewRegisterUser(JFrame mainFrame, MessageAppMainView view) {
		mainFrame.remove(view.jPanel);
		view.jPanel = new JPanel();
		view.jPanel.setLayout(new GridBagLayout());
		mainFrame.add(view.jPanel);
		Container c = new Container();
		c.setLayout(new GridBagLayout());
		c.add(new Label("Enregistrer un compte"), new GridBagConstraints(1, 1, 3, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 2, 1));
		JTextField nom = new JTextField(20);
		c.add(nom, new GridBagConstraints(2, 2, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 2, 1));
		JTextField tag = new JTextField(20);
		c.add(tag, new GridBagConstraints(2, 3, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 2, 1));
		JTextField mdp = new JTextField(20);
		c.add(mdp, new GridBagConstraints(2, 4, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 2, 1));
		c.add(new Label("Nom : "), new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 2, 1));
		c.add(new Label("Tag : "), new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 2, 1));
		c.add(new Label("MDP : "), new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 2, 1));
		view.jPanel.add(c, new GridBagConstraints(1, 1, 3, 1, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 2, 1));
		JButton b = new JButton("Valider");
		b.addActionListener(new ButtonRegister(view, nom, tag, mdp, mainFrame));
		view.jPanel.add(b, new GridBagConstraints(1, 2, 3, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 2, 1));
		mainFrame.revalidate();
		mainFrame.repaint();

	}

	class ButtonRegister extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2405796532980624028L;
		protected MessageAppMainView view;
		protected JTextField nom;
		protected JTextField tag;
		protected JTextField mdp;
		protected JFrame mainFrame;

		public ButtonRegister(MessageAppMainView view, JTextField nom, JTextField tag, JTextField mdp, JFrame mainFrame) {
			this.view = view;
			this.nom = nom;
			this.tag = tag;
			this.mdp = mdp;
			this.mainFrame = mainFrame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (nom.getText().length() > 0 && tag.getText().length() > 0 && mdp.getText().length() > 0) {
				this.view.notifyObserverRegister(nom, tag, mdp, mainFrame);
			}
			else {
				JOptionPane.showMessageDialog(mainFrame, "Remplissez tout les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

}
