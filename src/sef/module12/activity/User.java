package sef.module12.activity;

import java.io.*;

public class User implements Runnable {
	
	private String name;
	
	private InputStreamReader streamIn;
	private PrintWriter streamOut;
	
	public User(String name, InputStream streamIn, OutputStream streamOut) {
		this.streamIn = new InputStreamReader(streamIn);
		this.name = name;
		this.streamOut = new PrintWriter(new OutputStreamWriter(streamOut), true);
	}

	@Override
	public void run() {
		this.start();
	}
	
	public void start() {
		try {
			BufferedReader in = new BufferedReader(this.streamIn);
			try {
				String line = "";

				while ((line = in.readLine()) != null) {
					Chat.CHAT.addMessage(this, line);
					for(User u: Chat.CHAT.getUsers()) {
						u.getStreamOut().println(this.name + ":" + line);
					}
					if (line.equalsIgnoreCase("exit"))
						break;
				}
			}catch(IOException ex){
				ex.printStackTrace();
			}

		}finally {
			System.out.println("User disconnect");
		}
	}

	public String getName() {
		return name;
	}
	public PrintWriter getStreamOut(){
		return this.streamOut;
	}

}
