import java.net.*;
import java.io.*;

public class Network {
	Socket clnt = null;
	ServerSocket srv = null;
	
	boolean hosting = false;
	
	public void join(String addr, int port) {
		try {
			clnt = new Socket(addr, port);
		}
		catch (IOException e) {
			System.out.println("Connection failed.");
			Helper.exit();
		}
	}
	
	public void host(int port) {
		hosting = true;
		
		try {
			srv = new ServerSocket(port);
		}
		catch (IOException e) {
			System.out.println("Server creation failed.");
			Helper.exit();
		}
		
		System.out.println("Esperando pelo seu amigo...");
		
		try {
			clnt = srv.accept();
		}
		catch (IOException e) {
			System.out.println("Client acceptance failed.");
			Helper.exit();
		}
	}
	
	public String getInfo() {
		DataInputStream in = null;
		String result = null;
		
		try {
			in = new DataInputStream(clnt.getInputStream());
			result = in.readUTF();
		}
		catch (IOException e) {
			System.out.println("Output stream error.");
			Helper.exit();
		}
		
		return result;
	}
	
	public void sendInfo(String info) {
		DataOutputStream out;
		
		try {
			out = new DataOutputStream(clnt.getOutputStream());
			out.writeUTF(info);
		}
		catch (IOException e) {
			System.out.println("Output stream error.");
			Helper.exit();
		}
	}
}
