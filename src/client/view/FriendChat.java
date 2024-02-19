package client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import client.control.ClientConnection;
import model.Message;

public class FriendChat extends JFrame implements ActionListener{
	
	public static JTextPane je;
	JScrollPane jscrollpane;
	JTextArea jtextarea;
	
	JTextField jtextfield;
	JButton jbutton;
	public static JPanel chat;
	public static JPanel jpanel;
	
	String userName;
	String receiver;
	
	
	
	public FriendChat(String sender, String receiver){
		
		chat = new JPanel(new BorderLayout());
	    chat.setPreferredSize(new Dimension(425, 338));

	    je = new JTextPane();	    
	    je.setEditable(false);
	    
//	    if(isSend) {
//			je.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
//		}else {
//			je.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
//		}
	    
	    JScrollPane jscrollpane = new JScrollPane(je);
	    chat.add(jscrollpane, BorderLayout.CENTER);
	    
	    JTextField jtextfield = new JTextField(30);
	    JButton jbutton = new JButton("SEND");
	    jbutton.setFocusPainted(false);
	    jbutton.setBorderPainted(false);
	    
	    StyledDocument doc = je.getStyledDocument();
	    
	    StyleContext styleContext = new StyleContext();
	    Style imageStyle = styleContext.addStyle("ImageStyle", null);
	    
	    SimpleAttributeSet regularStyle = new SimpleAttributeSet();
	    
	    
	    ImageIcon imageIcon = new ImageIcon("images/legoshi.png"); 
	    Image image = imageIcon.getImage();
	    Image resizedImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	    ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
		
	    StyleConstants.setFontSize(regularStyle, 12);
	    StyleConstants.setForeground(regularStyle, Color.black);
	    StyleConstants.setBold(regularStyle, true);
        
        jbutton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (e.getSource() == jbutton) {
//	                je.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	                
	                try {

	                    StyleConstants.setIcon(imageStyle, resizedImageIcon);

	                    int spaceLength = 100;
	                    
	                    int size = jtextfield.getText().length();
	                    if(size != 4) {
	                    	if(size>4) {
	                    		spaceLength -= 4*(size-4);
	                    		System.out.println(spaceLength);
	                    	}else if(size<4){
	                    		spaceLength += 4*(4-size);
	                    		System.out.println(spaceLength);
	                    	}

	                    }
	                    if(size ==4) {
                    		System.out.println(spaceLength);
                    	}
	                    
	                    String spaces = String.format("%" + spaceLength + "s", "");
	                    String spaces1 = String.format("%" + 75 + "s", "");
	                 
	                    doc.insertString(doc.getLength(), spaces1, regularStyle);
	                    doc.insertString(doc.getLength(), "   "+new java.util.Date().toLocaleString()+"\r\n", regularStyle);
	                    doc.insertString(doc.getLength(), spaces, regularStyle);
	                    doc.insertString(doc.getLength(), jtextfield.getText(), regularStyle);
	                    doc.insertString(doc.getLength(), "\n",imageStyle);
	                    doc.insertString(doc.getLength(), "\n",regularStyle);
	                    

	                } catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                
	                Message m = new Message();
	                m.setSender(sender);
	                m.setReceiver(receiver);
	                m.setChatContent(jtextfield.getText());
	                m.setMessageType(Message.COMMON_CHAT_MESSAGE);
	                
	                try {
						ObjectOutputStream oos = new ObjectOutputStream(ClientConnection.s.getOutputStream());
						oos.writeObject(m);
	                
	                
	                } catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                
	                
	                jtextfield.setText(null);

	            }
	        }
	    });
		
		
		jpanel = new JPanel();
		jpanel.add(jtextfield);
		jpanel.add(jbutton);
		
		chat.add(jpanel, BorderLayout.SOUTH);

		this.setSize(new Dimension(435,338));
		this.setTitle("send message to --------> "+receiver);
	}
	
	public void append(Message m, boolean isSend) {
//		if(isSend) {
//			je.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
//		}else {
//			je.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
//		}
		
		StyledDocument doc = je.getStyledDocument();
	    
	    StyleContext styleContext = new StyleContext();
	    Style imageStyle = styleContext.addStyle("ImageStyle", null);
	    
	    SimpleAttributeSet regularStyle = new SimpleAttributeSet();
	    
	    
	    ImageIcon imageIcon = new ImageIcon("images/legoshi.png"); 
	    Image image = imageIcon.getImage();
	    Image resizedImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	    ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
		
	    StyleConstants.setFontSize(regularStyle, 12);
	    StyleConstants.setForeground(regularStyle, Color.black);
	    StyleConstants.setBold(regularStyle, true);
	
	    StyleConstants.setIcon(imageStyle, resizedImageIcon);

        try {
        	doc.insertString(doc.getLength(), m.getSendTime().toLocaleString()+"\r\n", regularStyle);
			doc.insertString(doc.getLength(), " ", imageStyle);
			doc.insertString(doc.getLength(), m.getChatContent()+"\n\n", regularStyle);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String args[]) {
//		FriendChat fc = new FriendChat();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
