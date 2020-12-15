package users.ICONS;
import java.net.*;
import java.util.Vector;
import java.io.*;

public class Server implements Runnable {
		
	Socket socket;
	public static Vector client = new Vector();
	public Server(Socket socket) {
		
		try {
			this.socket = socket;
		}catch(Exception e) {}
	}
	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			client.add(writer);
			
			
			while(true) {
				String data = reader.readLine().trim();
				
				for(int i=0;i<client.size(); i++) {
					try {
						BufferedWriter bw = (BufferedWriter) client.get(i);
						bw.write(data);
						bw.write("\r\n");
						bw.flush();
					}catch(Exception e) {}
				}
			}
			
		}catch(Exception e) {
			
		}
	}
	
	public static void main(String[] args) throws Exception {
	
		ServerSocket ss = new ServerSocket(2001);
		
		System.out.println("Waiting for client to connect");		
		while(true) {
			Socket socket = ss.accept();
			Server server = new Server(socket);
			Thread thread = new Thread(server);
			thread.start();	
			//ss.close();
		}
	}
}
