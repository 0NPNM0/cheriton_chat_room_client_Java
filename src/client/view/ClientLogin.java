package client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ClientLogin extends JFrame{
	JPanel panel;
	JButton login_button;
	JButton register_button;
	JButton cancel_button;
	int buttonWidth;
    int buttonHeight;
    JTabbedPane jtp;
    JTabbedPane jtp_north;
    JTabbedPane jtp_east;
    JTabbedPane jtp_west;
    JPanel jp_password;
    JPanel jp_phone;
    JPanel jp_email;
	
	public ClientLogin() {
		JFrame frame = new JFrame("Cheriton School Chat Room");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
//		panel = new JPanel();
		//background
		panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                ImageIcon imageIcon = new ImageIcon("images/login page.png");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                
            }
        };
        
        
    
        
        
        //middle
        jtp_north = new JTabbedPane();
        jtp_north.setPreferredSize(new Dimension(500, 50));
        panel.add(jtp_north,BorderLayout.NORTH);
        jtp_north.setBorder(BorderFactory.createEmptyBorder());
        jtp_north.setUI(new BasicTabbedPaneUI() {
     	   @Override
     	   protected void installDefaults() {
     	       super.installDefaults();
     	       highlight = new Color(255,255,255,0);
     	       lightHighlight = new Color(255,255,255,0);
     	       shadow = new Color(255,255,255,0);
     	       darkShadow = new Color(255,255,255,0);
     	       focus = new Color(255,255,255,0);
     	   }
     	});
        
        jtp_east = new JTabbedPane();
        jtp_east.setPreferredSize(new Dimension(150, 150));
        panel.add(jtp_east,BorderLayout.EAST);
        jtp_east.setBorder(BorderFactory.createEmptyBorder());
        jtp_east.setUI(new BasicTabbedPaneUI() {
     	   @Override
     	   protected void installDefaults() {
     	       super.installDefaults();
     	       highlight = new Color(255,255,255,0);
     	       lightHighlight = new Color(255,255,255,0);
     	       shadow = new Color(255,255,255,0);
     	       darkShadow = new Color(255,255,255,0);
     	       focus = new Color(255,255,255,0);
     	   }
     	});
        
        jtp_west = new JTabbedPane();
        jtp_west.setPreferredSize(new Dimension(150, 150));
        panel.add(jtp_west,BorderLayout.WEST);
        jtp_west.setBorder(BorderFactory.createEmptyBorder());
        jtp_west.setUI(new BasicTabbedPaneUI() {
     	   @Override
     	   protected void installDefaults() {
     	       super.installDefaults();
     	       highlight = new Color(255,255,255,0);
     	       lightHighlight = new Color(255,255,255,0);
     	       shadow = new Color(255,255,255,0);
     	       darkShadow = new Color(255,255,255,0);
     	       focus = new Color(255,255,255,0);
     	   }
     	});
        
        jtp = new JTabbedPane();
        jp_password = new JPanel();
        jp_phone = new JPanel();
        jp_email = new JPanel();
        jtp.add("Password",jp_password);
        jtp.add("Phone",jp_phone);
        jtp.add("Email",jp_email);
        jtp.setBorder(BorderFactory.createEmptyBorder());
        jtp.setBackground(new Color(119,149,203,0));
        
        jtp.setUI(new BasicTabbedPaneUI() {
        	   @Override
        	   protected void installDefaults() {
        	       super.installDefaults();
        	       highlight = new Color(255,255,255,0);
        	       lightHighlight = new Color(255,255,255,0);
        	       shadow = new Color(255,255,255,0);
        	       darkShadow = new Color(255,255,255,0);
        	       focus = new Color(255,255,255,0);
        	   }
        	});
        
        panel.add(jtp,BorderLayout.CENTER);
        
       
        
        
        
        
        //buttons
        login_button = new JButton();
        login_button.setFocusPainted(false);
        login_button.setText("LOGIN");
        login_button.setBorderPainted(false);
        
        register_button = new JButton();
        register_button.setFocusPainted(false);
        register_button.setText("REGISTER");
        register_button.setBorderPainted(false);
        
        cancel_button = new JButton();
        cancel_button.setFocusPainted(false);
        cancel_button.setText("CANCEL");
        cancel_button.setBorderPainted(false);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(login_button);
        buttonPanel.add(Box.createRigidArea(new Dimension(75, 10)), BorderLayout.CENTER);
        buttonPanel.add(register_button);
        buttonPanel.add(Box.createRigidArea(new Dimension(75, 10)), BorderLayout.CENTER);
        buttonPanel.add(cancel_button);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));//上左下右
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        frame.setContentPane(panel);
		frame.setSize(new Dimension(630,338));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
	}
	
	public static void main(String args[]) {
		ClientLogin clientLogin = new ClientLogin();
	}
}
