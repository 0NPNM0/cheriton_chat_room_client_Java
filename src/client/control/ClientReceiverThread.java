package client.control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.view.ClientLogin;
import client.view.FriendChat;
import client.view.FriendPad;
import model.Message;

public class ClientReceiverThread extends Thread{
	Socket s;

	public ClientReceiverThread(Socket s) {
		this.s = s;
	}
	
	public void run() {
		ObjectInputStream ois;
		try {
			
			while(true) {
				ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				
				if(m.getMessageType().equals(Message.ADD_NEW_FRIEND_FAILURE_NO_USER)) {
					JOptionPane.showMessageDialog(null, "the user is not exist");
				}
				
				if(m.getMessageType().equals(Message.ADD_NEW_FRIEND_FAILURE_ALREADY_FRIEND)) {
					JOptionPane.showMessageDialog(null, "the user is already friend");
				}
				
				if(m.getMessageType().equals(Message.ADD_NEW_FRIEND_SUCCESS)) {
					JOptionPane.showMessageDialog(null, "successfully add");
					
					FriendPad fp = (FriendPad)ClientLogin.friendList.get(m.getSender());
					String allFriend = m.getChatContent();
					fp.updateFriendList(allFriend);
				}
				
				
				if(m.getMessageType().equals(Message.NEW_ONLINE_FRIEND_TO_CLIENT)) {
					FriendPad fp = (FriendPad)ClientLogin.friendList.get(m.getReceiver());
					fp.setEnableNewOnlineFriendIcon(m.getSender());
				}
				
				
				if(m.getMessageType().equals(Message.REPONSE_ONLINE_FRIEND)) {
					System.out.println(m.getSender()+" friend online: "+m.getOnLineFriend());
					
					FriendPad fp = (FriendPad)ClientLogin.friendList.get(m.getSender());
					System.out.println(ClientLogin.friendList);
					fp.setEnableOnlineFriendIcon(m.getOnLineFriend());
				}
				
				
				
				if(m.getMessageType().equals(Message.COMMON_CHAT_MESSAGE)){
					System.out.println(m.getReceiver()+" to "+m.getSender()+" : "+m.getChatContent());
					
					String sender = m.getSender();
					String receiver = m.getReceiver();
					FriendChat chat = (FriendChat)FriendPad.hmChat.get(receiver+" to "+sender);
					chat.append(m,false);
				}
			}
		
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
