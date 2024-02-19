package client.control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import model.Message;
import model.User;

public class ClientConnection {

	public static Socket s;
	
	public ClientConnection() {
		
		try {
			s = new Socket("127.0.0.1",3456);
			System.out.println("successfully link with server!" +s);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public Message loginValidate(User u) {
		
		boolean loginValidate = false;
		
		OutputStream os;
		Message m = null;
		try {
			os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);//封装流
			oos.writeObject(u);
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			m = (Message)ois.readObject();
			if(m.getMessageType().equals(Message.LOGIN_VALIDATE_SUCCESS)) {
				loginValidate = true;
				new ClientReceiverThread(s).start();
			}
			
//			s.close();
		} catch (IOException | ClassNotFoundException e) {		
			e.printStackTrace();
		}
//		return loginValidate;
		return m;
	}
	
	public static boolean registerUser(User u) {
		boolean registerSuccess = false;
		
		OutputStream os;
		Message m = null;
		try {
			os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);//封装流
			oos.writeObject(u);
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			m = (Message)ois.readObject();
			if(m.getMessageType().equals(Message.USER_REGISTER_SUCCESS)) {
				registerSuccess = true;
			}
			
			s.close();
		} catch (IOException | ClassNotFoundException e) {		
			e.printStackTrace();
		}
		
		return registerSuccess;
	}
	
}
