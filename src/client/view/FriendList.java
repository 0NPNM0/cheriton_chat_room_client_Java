package client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FriendList extends JFrame implements ActionListener{
	
	
	
	//card 1
	JPanel myFriendPanel;
	JButton myFriendButton;
	
	//middle
	JScrollPane jscrollpane;
	JPanel myFriendListPanel;
	final int MYFRIENDCOUNT = 50;
	JLabel myFriendLabel[] = new JLabel[MYFRIENDCOUNT];
	
	//south part
	JButton mySouthButton1;
	JButton myBlankButton1;
	JPanel south_blank_panel;
	
	//card 2
	JPanel myStrangerPanel;
	JPanel friend_stranger_panel;
	JButton myFriendButton1;
	JButton myStrangerButton;
	
	//middle
	JScrollPane jscrollpane12;
	JPanel myStrangerListPanel;
	final int MYSTRANGERCOUNT = 20;
	JLabel myStrangerLabel[] = new JLabel[MYSTRANGERCOUNT];
	
	//south part
	JButton myBlankButton2;
	
	
	CardLayout cardLayout;
	
	
	public FriendList(String userNumber) {
		
		//grass card
		myFriendPanel = new JPanel(new BorderLayout());
		
		myFriendButton = new JButton();
		myFriendButton.setFocusPainted(false);
		myFriendButton.setText("HERBIVORE");
		myFriendButton.setBorderPainted(false);
		
		myFriendPanel.add(myFriendButton,"North");
		
		int Width = 45; 
        int Height = 45; 
        ImageIcon imageIcon = new ImageIcon("images/legoshi.png"); 
        Image image = imageIcon.getImage();
        Image resizedImage = image.getScaledInstance(Width, Height, Image.SCALE_SMOOTH);
        ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
        

		//middle
		myFriendListPanel = new JPanel(new GridLayout(MYFRIENDCOUNT,1));
		for(int i = 0; i < MYFRIENDCOUNT; i++) {
			myFriendLabel[i] = new JLabel("Friend "+i, resizedImageIcon, JLabel.LEFT);
			myFriendListPanel.add(myFriendLabel[i]);
		}
		jscrollpane = new JScrollPane(myFriendListPanel);
		myFriendPanel.add(jscrollpane,"Center");
		
		
		//south part
		mySouthButton1 = new JButton();
		mySouthButton1.addActionListener(this);
		mySouthButton1.setFocusPainted(false);
		mySouthButton1.setText("CARNIVORE");
		mySouthButton1.setBorderPainted(false);
		
		myBlankButton1 = new JButton();
		myBlankButton1.setFocusPainted(false);
		myBlankButton1.setText("BEASTARS");
		myBlankButton1.setBorderPainted(false);
		
		south_blank_panel = new JPanel(new GridLayout(2,1));
		south_blank_panel.add(mySouthButton1);
		south_blank_panel.add(myBlankButton1);
		
		myFriendPanel.add(south_blank_panel,"South");
		
		
		//stranger card
		myStrangerPanel = new JPanel(new BorderLayout());
		friend_stranger_panel = new JPanel(new GridLayout(2,1));
		myFriendButton1 = new JButton("HERBIVORE");
		myFriendButton1.addActionListener(this);
		myFriendButton1.setFocusPainted(false);
		myFriendButton1.setBorderPainted(false);
		
		myStrangerButton = new JButton("CARNIVORE");
		myStrangerButton.addActionListener(this);
		myStrangerButton.setFocusPainted(false);
		myStrangerButton.setBorderPainted(false);
		friend_stranger_panel.add(myFriendButton1);
		friend_stranger_panel.add(myStrangerButton);
		myStrangerPanel.add(friend_stranger_panel,"North");
		
		
		//middle
		myStrangerListPanel = new JPanel(new GridLayout(MYSTRANGERCOUNT,1));
		for(int i = 0; i < MYSTRANGERCOUNT; i++) {
			myStrangerLabel[i] = new JLabel("Stranger "+i, resizedImageIcon, JLabel.LEFT);
			myStrangerListPanel.add(myStrangerLabel[i]);
		}
		jscrollpane12 = new JScrollPane(myStrangerListPanel);
		myStrangerPanel.add(jscrollpane12,"Center");
		
		//south
		myBlankButton2 = new JButton();
		myBlankButton2.setFocusPainted(false);
		myBlankButton2.setText("BEASTARS");
		myBlankButton2.setBorderPainted(false);
		
		myStrangerPanel.add(myBlankButton2,"South");
				
		
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		this.add(myFriendPanel,"1");
		this.add(myStrangerPanel,"2");
		

		
		this.setSize(new Dimension(210,338));
		this.setTitle(userNumber);
		this.setIconImage(resizedImage);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
	}
	
	public static void main(String args[]) {
//		FriendList friendList = new FriendList();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mySouthButton1) {
			cardLayout.show(this.getContentPane(), "2");
		}
		if(e.getSource() == myFriendButton1) {
			cardLayout.show(this.getContentPane(), "1");
		}
	}
}
