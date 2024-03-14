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
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

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
		//text.setDocument(new TextLimit(50));
		
		c.add(text, new GridBagConstraints(2, 2, 3, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 2, 1));
		view.jPanel.add(c, new GridBagConstraints(1, 1, 3, 1, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 2, 1));
		JButton b = new JButton("Envoyer");
		b.addActionListener(new SendMessage(view, text, mainFrame));
		view.jPanel.add(b, new GridBagConstraints(1, 2, 3, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 2, 1));
		mainFrame.revalidate();
		mainFrame.repaint();
	}
	
	/*class TextLimit extends PlainDocument {

		private static final long serialVersionUID = 1510641560348575042L;
		protected int limit;
		
		public TextLimit (int limit) {
			super();
			this.limit = limit;
		}
		
		public void checkLength(int offset, String text, AttributeSet attr) throws BadLocationException {
			if (text == null) return;
			
			if ((getLength() + text.length()) <= this.limit) {
				super.insertString(offset, text, attr);
			}
		}
	}*/
	
	class SendMessage extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 3254860579307628699L;
		protected MessageAppMainView view;
		protected JTextField text;
		protected JFrame mainFrame;

		public SendMessage(MessageAppMainView view, JTextField text, JFrame mainFrame) {
			this.view = view;
			this.text = text;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (this.text.getText().length() <= 200) {
				this.view.notifyObserverMessageSend(text);
			}
			else {
				JOptionPane.showMessageDialog(mainFrame, "Message trop long (200 caractères max)", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
}
