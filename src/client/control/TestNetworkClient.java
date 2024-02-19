package client.control;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import model.User;

public class TestNetworkClient {
	public static void main(String args[]) {
		try {
			Socket s = new Socket("127.0.0.1",3456);
			System.out.println("successfully link with server!" +s);
			
			User u = new User();
			u.setUserName("legoshi");
			u.setPassword("123456");
			
			//send to server
			OutputStream os = s.getOutputStream();//字节流
			ObjectOutputStream oos = new ObjectOutputStream(os);//封装流
			oos.writeObject(u);
			
			s.close();
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
