import java.net.*;
import java.io.*;
public class TextServer extends Thread {
	Socket so=null;
	public TextServer(Socket s){
		so=s;
	}
	public void run() {
		try {
			BufferedReader in=new  BufferedReader(new InputStreamReader(so.getInputStream()));
			PrintWriter out =new  PrintWriter(so.getOutputStream(),true);
			StringBuffer sb = new StringBuffer();
			String s=in.readLine();
			 for(;s!=null;s=in.readLine())
				  sb.append(s);
			 out.println(sb.reverse().toString());
			 in.close();
			 out.close();
			 so.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		  ServerSocket sso =new ServerSocket(3333);
		  while(true){
			  Socket soo=sso.accept();
			  TextServer ts=new TextServer(soo);
			  ts.start();
		  }
	}
}
