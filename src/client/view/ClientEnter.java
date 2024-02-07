package client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ClientEnter {
	JPanel panel;
	JButton enter_button;
	int buttonWidth;
    int buttonHeight;
	
	
	public ClientEnter() {
		JFrame frame = new JFrame("Cheriton School Chat Room");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		//background
		panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                ImageIcon imageIcon = new ImageIcon("images/cheriton academy.png");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                
            }
        };
        
        
        enter_button = new JButton();
//        Border roundedBorder = BorderFactory.createRoundRectBorder(10, 10);
//        enter_button.setBorder(roundedBorder);
        enter_button.setFocusPainted(false);
        enter_button.setBorderPainted(false);
        enter_button.setText("Click here to start your journey at Cheriton School !    (  •  ω  •  ) ⁄ ♡");
        enter_button.addActionListener(e->{
        	try {
        		new ClientLogin();
        	}catch(Exception ex) {
        		ex.printStackTrace();
        	}
        });
     
        panel.add(enter_button, BorderLayout.SOUTH);
        
        
        frame.setContentPane(panel);
		frame.setSize(new Dimension(630,338));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void main(String args[]) {
		ClientEnter e = new ClientEnter();
	}
}
