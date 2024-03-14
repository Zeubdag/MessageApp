package main.java.com.ubo.tp.message.ihm.view;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import main.java.com.ubo.tp.message.ihm.MessageAppMainView;

public class Menu {
	
	protected JMenuBar upperMenuBar;
	
	public Menu() {
		this.upperMenuBar = new JMenuBar();
	}
	
	public void viewMenuBar(JFrame mainFrame, MessageAppMainView view) {
		
		JMenu compteMenu = new JMenu();
		compteMenu.setText("Compte");

		if (!view.isConnected) {
			JMenuItem connexion = new JMenuItem("Connexion");
			connexion.addActionListener(new SwitchSignup(view, mainFrame));
			compteMenu.add(connexion);

			JMenuItem creationCompte = new JMenuItem("Créer un compte");
			creationCompte.addActionListener(new SwitchRegister(view, mainFrame));
			compteMenu.add(creationCompte);
			
			upperMenuBar.add(compteMenu);
		}
		else { 
			JMenuItem connexion = new JMenuItem("Se déconnecter");
			connexion.addActionListener(new SwitchDisconnection(view, mainFrame));
			compteMenu.add(connexion);
			
			JMenu messagerie = new JMenu("Messagerie");
			
			JMenuItem ecrireMessage = new JMenuItem("Ecrire message");
			ecrireMessage.addActionListener(new SwitchWriteMessage(view, mainFrame));
			messagerie.add(ecrireMessage);
			
			upperMenuBar.add(compteMenu);
			upperMenuBar.add(messagerie);
		}

		/*JMenu aProposMenu = new JMenu();
		aProposMenu.setText("Menu");

		JMenuItem item = new JMenuItem("A propos");
		aProposMenu.add(item);*/

		JMenu otherMenu = new JMenu();
		otherMenu.setText("?");

		JMenuItem leave = new JMenuItem("Quitter");
		leave.addActionListener(new Leave());
		otherMenu.add(leave);

		
		//upperMenuBar.add(aProposMenu);
		upperMenuBar.add(otherMenu);

		mainFrame.setJMenuBar(upperMenuBar);
		mainFrame.revalidate();
		mainFrame.repaint();
	}
	
	public void refreshMenuBar(JFrame mainFrame, MessageAppMainView view) {
		this.upperMenuBar = new JMenuBar();
		this.viewMenuBar(mainFrame, view);
	}
	
	
	static class Leave extends AbstractAction {
		
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	}
	
	class SwitchRegister extends AbstractAction {
		
		private static final long serialVersionUID = -442894757381385349L;
		
		JFrame mainFrame;
		MessageAppMainView view;

		public SwitchRegister(MessageAppMainView view, JFrame mainFrame) {
			this.view = view;
			this.mainFrame = mainFrame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			view.viewRegister.viewRegisterUser(mainFrame, view);
		}
	}

	class SwitchSignup extends AbstractAction {
		
		private static final long serialVersionUID = 1574743513104191369L;
		
		JFrame mainFrame;
		MessageAppMainView view;

		public SwitchSignup(MessageAppMainView view, JFrame mainFrame) {
			this.view = view;
			this.mainFrame = mainFrame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			view.viewSignIn.viewSignIn(mainFrame, view);
		}
	}
	
	class SwitchDisconnection extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1520308407101539843L;
		
		JFrame mainFrame;
		MessageAppMainView view;

		public SwitchDisconnection(MessageAppMainView view, JFrame mainFrame) {
			this.view = view;
			this.mainFrame = mainFrame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			view.session.disconnect();
			view.viewSignIn.viewSignIn(mainFrame, view);
			view.isConnected = false;
			view.viewMenuBar.refreshMenuBar(mainFrame, view);
		}
	}
	
	class SwitchWriteMessage extends AbstractAction {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -5201247616541146418L;
		MessageAppMainView view;
		JFrame mainFrame;
		
		public SwitchWriteMessage(MessageAppMainView view, JFrame mainFrame) {
			this.view = view;
			this.mainFrame = mainFrame;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			this.view.viewCreateMessage.viewCreateMessage(mainFrame, view);
		}
	}

}
