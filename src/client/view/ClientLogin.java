package client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import client.control.ClientConnection;
import model.Message;
import model.User;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class ClientLogin extends JFrame implements ActionListener{
	public static HashMap friendList = new HashMap<String, FriendPad>();
	
	JPanel panel;
	JFrame frame;
	
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
    
    //jp_password
    JLabel jl_number;
    JLabel jl_password;
    JTextField jTextField;
    JPasswordField jPasswordField;
    JButton forget_password_button;
    JCheckBox jCheckBox;
    
  //jp_phone
    JLabel pjl_number;
    JLabel pjl_phone;
    JTextField pjTextField;
    JTextField pjTextField1;
    JButton pforget_phone_button;
    JCheckBox pjCheckBox;
    
  //jp_email
    JLabel ejl_number;
    JLabel ejl_email;
    JTextField ejTextField;
    JTextField ejTextField1;
    JButton eforget_email_button;
    JCheckBox ejCheckBox;
	
	public ClientLogin() {
		frame = new JFrame("Cheriton School Chat Room");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
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
       
      //jp_password
        jp_password = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
      //jp_phone
        jp_phone = new JPanel(new GridBagLayout());
        GridBagConstraints constraints1 = new GridBagConstraints();
        
      //jp_email
        jp_email = new JPanel(new GridBagLayout());
        GridBagConstraints constraints2 = new GridBagConstraints();
        
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
        
        
        
      
      //jp_password inside TabbedPane 
        jl_number = new JLabel("Name: ",JLabel.CENTER);
        constraints.gridx = 0; // 列索引为0
        constraints.gridy = 0; // 行索引为0
        constraints.fill = GridBagConstraints.HORIZONTAL; // 设置水平填充
        constraints.weightx = 1.5; // 设置水平权重
        jp_password.add(jl_number, constraints);
        
        jl_password = new JLabel("Password : ",JLabel.CENTER);
        constraints.gridx = 0; // 列索引为0
        constraints.gridy = 1; // 行索引为0
        constraints.fill = GridBagConstraints.HORIZONTAL; // 设置水平填充
        constraints.weightx = 1.0; // 设置水平权重
        jp_password.add(jl_password, constraints);
        
        jTextField = new JTextField();
        constraints.gridx = 1; // 列索引为0
        constraints.gridy = 0; // 行索引为0
        constraints.gridwidth = 2; // 横跨两列
        constraints.fill = GridBagConstraints.HORIZONTAL; // 设置水平填充
        constraints.weightx = 2.0; // 设置水平权重
        jp_password.add(jTextField, constraints);
        
        jPasswordField = new JPasswordField();
        constraints.gridx = 1; // 列索引为0
        constraints.gridy = 1; // 行索引为0
        constraints.gridwidth = 2; // 横跨两列
        constraints.fill = GridBagConstraints.HORIZONTAL; // 设置水平填充
        constraints.weightx = 2.5; // 设置水平权重
        jp_password.add(jPasswordField, constraints);
        
        jCheckBox = new JCheckBox("Remember Password");
        constraints.gridx = 0; // 列索引为0
        constraints.gridy = 4; // 行索引为0
        constraints.fill = GridBagConstraints.HORIZONTAL; // 设置水平填充
        constraints.weightx = 2.0; // 设置水平权重
        constraints.anchor = GridBagConstraints.CENTER;
        jp_password.add(jCheckBox, constraints);

        forget_password_button = new JButton("Forget Password");
        constraints.gridx = 1; // 列索引为0
        constraints.gridy = 4; // 行索引为0
        jp_password.add(forget_password_button, constraints);
        
        
        
        
        
      
      //jp_phone inside TabbedPane
        pjl_number = new JLabel("Name: ",JLabel.CENTER);
        constraints1.gridx = 0; // 列索引为0
        constraints1.gridy = 0; // 行索引为0
        constraints1.fill = GridBagConstraints.HORIZONTAL; // 设置水平填充
        constraints1.weightx = 1.5; // 设置水平权重
        jp_phone.add(pjl_number, constraints1);
        
        pjl_phone = new JLabel("PhoneNumber: ",JLabel.CENTER);
        constraints1.gridx = 0; // 列索引为0
        constraints1.gridy = 1; // 行索引为0
        constraints1.fill = GridBagConstraints.HORIZONTAL; // 设置水平填充
        constraints1.weightx = 1.0; // 设置水平权重
        jp_phone.add(pjl_phone, constraints1);
        
        
        pjTextField = new JTextField();
        constraints1.gridx = 1; // 列索引为0
        constraints1.gridy = 0; // 行索引为0
        constraints1.gridwidth = 2; // 横跨两列
        constraints1.fill = GridBagConstraints.HORIZONTAL; // 设置水平填充
        constraints1.weightx = 2.0; // 设置水平权重
        jp_phone.add(pjTextField, constraints1);
        
        pjTextField1 = new JTextField();
        constraints1.gridx = 1; // 列索引为0
        constraints1.gridy = 1; // 行索引为0
        constraints1.gridwidth = 2; // 横跨两列
        constraints1.fill = GridBagConstraints.HORIZONTAL; // 设置水平填充
        constraints1.weightx = 2.5; // 设置水平权重
        jp_phone.add(pjTextField1, constraints1);
        
        pjCheckBox = new JCheckBox("Remember Phone Number");
        constraints1.gridx = 0; // 列索引为0
        constraints1.gridy = 4; // 行索引为0
        constraints1.fill = GridBagConstraints.HORIZONTAL; // 设置水平填充
        constraints1.weightx = 2.0; // 设置水平权重
        constraints1.anchor = GridBagConstraints.CENTER;
        jp_phone.add(pjCheckBox, constraints1);

        pforget_phone_button = new JButton("Forget Phone");
        constraints1.gridx = 1; // 列索引为0
        constraints1.gridy = 4; // 行索引为0
        jp_phone.add(pforget_phone_button, constraints1);
        
        
        
        
          
        
        
      //jp_email inside TabbedPane
        ejl_number = new JLabel("Name: ",JLabel.CENTER);
        constraints2.gridx = 0; // 列索引为0
        constraints2.gridy = 0; // 行索引为0
        constraints2.fill = GridBagConstraints.HORIZONTAL; // 设置水平填充
        constraints2.weightx = 1.5; // 设置水平权重
        jp_email.add(ejl_number, constraints2);
        
        ejl_email = new JLabel("EmailAddress: ",JLabel.CENTER);
        constraints2.gridx = 0; // 列索引为0
        constraints2.gridy = 1; // 行索引为0
        constraints2.fill = GridBagConstraints.HORIZONTAL; // 设置水平填充
        constraints2.weightx = 1.0; // 设置水平权重
        jp_email.add(ejl_email, constraints2);
        
        
        ejTextField = new JTextField();
        constraints2.gridx = 1; // 列索引为0
        constraints2.gridy = 0; // 行索引为0
        constraints2.gridwidth = 2; // 横跨两列
        constraints2.fill = GridBagConstraints.HORIZONTAL; // 设置水平填充
        constraints2.weightx = 2.0; // 设置水平权重
        jp_email.add(ejTextField, constraints2);
        
        ejTextField1 = new JTextField();
        constraints2.gridx = 1; // 列索引为0
        constraints2.gridy = 1; // 行索引为0
        constraints2.gridwidth = 2; // 横跨两列
        constraints2.fill = GridBagConstraints.HORIZONTAL; // 设置水平填充
        constraints2.weightx = 2.5; // 设置水平权重
        jp_email.add(ejTextField1, constraints2);
        
        ejCheckBox = new JCheckBox("Remember Email Address");
        constraints2.gridx = 0; // 列索引为0
        constraints2.gridy = 4; // 行索引为0
        constraints2.fill = GridBagConstraints.HORIZONTAL; // 设置水平填充
        constraints2.weightx = 2.0; // 设置水平权重
        constraints2.anchor = GridBagConstraints.CENTER;
        jp_email.add(ejCheckBox, constraints2);

        eforget_email_button = new JButton("Forget Email");
        constraints2.gridx = 1; // 列索引为0
        constraints2.gridy = 4; // 行索引为0
        jp_email.add(eforget_email_button, constraints2);
        
        

        
        
        
        //buttons
        login_button = new JButton();
        login_button.setFocusPainted(false);
        login_button.setText("LOGIN");
        login_button.setBorderPainted(false);
        login_button.addActionListener(this);
        
        register_button = new JButton();
        register_button.setFocusPainted(false);
        register_button.setText("REGISTER");
        register_button.setBorderPainted(false);
        register_button.addActionListener(this);
        
        cancel_button = new JButton();
        cancel_button.setFocusPainted(false);
        cancel_button.setText("CANCEL");
        cancel_button.setBorderPainted(false);
        cancel_button.addActionListener(e->{
        	try {
        		new ClientEnter();
        		frame.dispose();
        	}catch(Exception ex) {
        		ex.printStackTrace();
        	}
        });
        
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
	
	public void sendMessage(Socket s, Message m) {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(m);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == login_button) {
			String userName = jTextField.getText().trim();
			String password = new String(jPasswordField.getPassword());
			User u = new User();
			u.setUserName(userName);
			u.setPassword(password);
			
			u.setUserType(User.USER_LOGIN_VALIDATE);
			
			Message m = new ClientConnection().loginValidate(u);
			if(m.getMessageType().equals(Message.LOGIN_VALIDATE_SUCCESS)){
//			if(new ClientConnection().loginValidate(u)){
				
				String allFriend = m.getChatContent();
		
				FriendPad fp = new FriendPad(userName, allFriend);
				ClientLogin.friendList.put(userName, fp);
				System.out.println(userName+" friendList"+friendList);
				
				m.setSender(userName);
				m.setReceiver("Server");
				m.setMessageType(Message.REQUEST_ONLINE_FRIEND);
			
				
				sendMessage(ClientConnection.s,m);
				
				m.setMessageType(Message.NEW_ONLINE_FRIEND_TO_SERVER);
				sendMessage(ClientConnection.s,m);
				
				
				frame.dispose();
			}else {
				JOptionPane.showMessageDialog(this, "Password is not correct!\n    Please login again!");
			}
			
			
		}
		
		
		if(e.getSource() == register_button) {
			String userName = jTextField.getText().trim();
			String password = new String(jPasswordField.getPassword());
			User u = new User();
			u.setUserName(userName);
			u.setPassword(password);
			
			
			u.setUserType(User.USER_REGISTER);
			
			
			boolean registerSuccess= new ClientConnection().registerUser(u);
			if(registerSuccess) {
				JOptionPane.showMessageDialog(this, userName + " register success!");
			}else {
				JOptionPane.showMessageDialog(this, userName + " register failure!");
			}
			
		}
		
	}
}
