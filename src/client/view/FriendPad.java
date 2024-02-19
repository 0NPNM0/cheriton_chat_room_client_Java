package client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import client.control.ClientConnection;
import model.Message;

public class FriendPad extends JFrame implements ActionListener, MouseListener{
	public static JFrame frame;
	
	public static JPanel currentPanel;
	JLabel currentLabel;
	JLabel clfont;
	
	//list
	JPanel list;
	JPanel card;
	
	//card 1
	JPanel top1;
	
	JButton top11;
	JButton addFriendButton;
	JPanel northpart;
	
	JScrollPane jscrollpane;
	JPanel glist;
	final int MYGCOUNT = 50;//草食动物
	JLabel myGLabel[] = new JLabel[MYGCOUNT];
	
	JPanel top1bottom;
	JButton top12;
	JButton top13;
	
	
	
	//card 2
	JPanel top2;
		
	JPanel top2p;
	JButton top21;
	JButton top22;
		
	JScrollPane jscrollpane2;
	JPanel glist2;
	final int MYMCOUNT = 20;//肉食动物
	JLabel myMLabel[] = new JLabel[MYMCOUNT];
		
	JButton top23;
	
	//card
	CardLayout cardLayout;
	
	
	//chat
	JPanel chat;
	
	JScrollPane cjscrollpane;
//	public static JTextPane je;
	JTextArea ja;
	
	JTextField jtextfield;
	JButton jbutton;
	JPanel jpanel;
	FriendChat fc;
	
	String sender;
	String receiver;
	
	//list
	int Width = 45; 
	int Height = 45; 
	ImageIcon imageIcon = new ImageIcon("images/legoshi.png"); 
	Image image = imageIcon.getImage();
	Image resizedImage = image.getScaledInstance(Width, Height, Image.SCALE_SMOOTH);
	ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
	
	public static HashMap hmChat = new HashMap<String,FriendChat>();
	
	public FriendPad() {
		
	}
	
	public FriendPad(String sender, String allFriend) {
		
		frame = new JFrame("Cheriton School Chat Room");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.sender = sender;
		this.receiver = receiver;

		
		//card1
		top1 = new JPanel(new BorderLayout());
		
		northpart = new JPanel(new GridLayout(2,1));
		
		addFriendButton = new JButton();
		addFriendButton.setFocusPainted(false);
		addFriendButton.setText("ADD FRIEND");
		addFriendButton.setBorderPainted(false);
		addFriendButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	if(e.getSource()==addFriendButton) {
					String newFriendName = JOptionPane.showInputDialog("Please enter the friend's name :");
				
					Message m = new Message();
					m.setSender(sender);
					m.setReceiver("Server");
					m.setChatContent(newFriendName);
					m.setMessageType(Message.ADD_NEW_FRIEND);
					
					ObjectOutputStream oos;
					try {
						oos = new ObjectOutputStream(ClientConnection.s.getOutputStream());
						oos.writeObject(m);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	}
		    }
		});
		
		northpart.add(addFriendButton);
		
		//top
		top11 = new JButton();
		top11.setFocusPainted(false);
		top11.setText("HERBIVORE");
		top11.setBorderPainted(false);
		
		northpart.add(top11);
		
		top1.add(northpart,"North");
		
		
//		//middle
		glist = new JPanel();
		updateFriendList(allFriend);
		jscrollpane = new JScrollPane(glist);
		top1.add(jscrollpane,"Center");
		
		//bottom
		top12 = new JButton();
		top12.setFocusPainted(false);
		top12.setText("CARNIVORE");
		top12.setBorderPainted(false);
		
		top12.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	if(e.getSource()==top12) {
					cardLayout.show(list, "2");
				}
		    }
		});
		
		top13 = new JButton();
		top13.setFocusPainted(false);
		top13.setText("QUIT");
		top13.setBorderPainted(false);
		
		top13.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	new ClientLogin();
		    	frame.dispose();
		    }
		});
		
		top1bottom = new JPanel(new GridLayout(2,1));
		top1bottom.add(top12);
		top1bottom.add(top13);
		top1.add(top1bottom,"South");
		
		
		//card2
		top2 = new JPanel(new BorderLayout());
		
		//top
		top21 = new JButton();
		top21.setFocusPainted(false);
		top21.setText("HERBIVORE");
		top21.setBorderPainted(false);
		
		top21.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	if(e.getSource()==top21) {
					cardLayout.show(list, "1");
				}
		    }
		});
		
		top22 = new JButton();
		top22.setFocusPainted(false);
		top22.setText("CARNIVORE");
		top22.setBorderPainted(false);
		top2p = new JPanel(new GridLayout(2,1));
		top2p.add(top21);
		top2p.add(top22);
		top2.add(top2p,"North");
		
		//middle
		glist2 = new JPanel(new GridLayout(MYMCOUNT,1));
		for(int i = 0; i<MYMCOUNT; i++) {
			int mlabel = i;
			myMLabel[i] = new JLabel(""+i, resizedImageIcon, JLabel.LEFT);
			myMLabel[i].addMouseListener(new MouseListener() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
		            if (e.getClickCount() == 2) {
		            	String receiver = myMLabel[mlabel].getText();
		            	
		            	if (currentLabel != myMLabel[mlabel] && currentPanel != null) {
		            	    frame.getContentPane().remove(currentPanel);
		            	    currentLabel.setForeground(Color.black);
		            	}

		                if (currentLabel != myMLabel[mlabel]) {
		                	
		                	myMLabel[mlabel].setForeground(new Color(119, 149, 203));
		                	fc = new FriendChat(sender,receiver);
		                	frame.getContentPane().add(fc.chat, BorderLayout.EAST);
		                    currentPanel = fc.chat;
		                    currentLabel = myMLabel[mlabel];
		                    frame.revalidate();
		                    frame.repaint();
		                }
//		                receiver = currentLabel.getText();
		            }
		        }

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					
				}
			});
			glist2.add(myMLabel[i]);
		}
		jscrollpane2 = new JScrollPane(glist2);
		top2.add(jscrollpane2,"Center");
		
		//bottom
		top23 = new JButton();
		top23.setFocusPainted(false);
		top23.setText("QUIT");
		top23.setBorderPainted(false);
		
		top23.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	new ClientLogin();
		    	frame.dispose();
		    }
		});
		
		top2.add(top23,"South");
		
		//card choose
		list = new JPanel();
		list.setPreferredSize(new Dimension(190, 338));
		cardLayout = new CardLayout();
		list.setLayout(cardLayout);
		list.add(top1,"1");
		list.add(top2,"2");

		frame.add(list, BorderLayout.WEST);

		
		frame.setSize(new Dimension(630,338));
		frame.setIconImage(resizedImage);
		frame.setTitle("Welcome "+ sender+ " !");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void updateFriendList(String allFriend) {
		
		glist.removeAll();
		
		String [] friendName = allFriend.split(" ");
		int friendCount = friendName.length;
		glist.setLayout(new GridLayout(friendCount, 1));
		for(int i = 1; i<friendCount; i++) {
			int label = i;
			myGLabel[i] = new JLabel(friendName[i], resizedImageIcon, JLabel.LEFT);
			
//			if(i!=Integer.parseInt(sender)) {
//				myGLabel[i].setEnabled(false);
//			}
			
			myGLabel[i].addMouseListener(new MouseListener() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			    	
			    	if (e.getClickCount() == 2) {
			    		String receiver = myGLabel[label].getText();
			    		
		            	if (currentLabel != myGLabel[label] && currentPanel != null) {
		            	    frame.getContentPane().remove(currentPanel);
		            	    currentLabel.setForeground(Color.black);
		            	}

		                if (currentLabel != myGLabel[label]) {
		                	myGLabel[label].setForeground(new Color(119, 149, 203));
		                	fc = new FriendChat(sender,receiver);
		                	frame.getContentPane().add(fc.chat, BorderLayout.EAST);
		                    currentPanel = fc.chat;
		                    currentLabel = myGLabel[label];
		                    frame.revalidate();
		                    frame.repaint();
		                }
		                

		                hmChat.put(sender+" to "+receiver, fc);
		            }
				}

				@Override
				public void mousePressed(MouseEvent e) {
	
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
	
				}
			});
			glist.add(myGLabel[i]);
			
			glist.revalidate();
		}
	}
	
	
	
	public static void main(String args[]) {
//		FriendPad f = new FriendPad();
		
	}
	
	public void setEnableOnlineFriendIcon(String onlineFriend){
		String [] friendName = onlineFriend.split(" ");
		int count = friendName.length;
		
		for(int i=0; i<count; i++) {
			if (!friendName[i].isEmpty()) {
//				myGLabel[Integer.valueOf(friendName[i])].setEnabled(true);
			}
		}
	}
	
	public void setEnableNewOnlineFriendIcon(String newOnlineFriend){
//		myGLabel[Integer.valueOf(newOnlineFriend)].setEnabled(true);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
