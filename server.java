import java.io.*;
import java.net.*;


public class server {

    public static void main(String[] args) {
        try {
            ServerSocket ss = null;
            ss = new ServerSocket(1234);
            Socket s = null;
            s = ss.accept();

            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String msgin = "", msgout = "";

            while (!msgin.equals("end")) {
                msgin = din.readUTF();
                System.out.println(msgin);
                msgout = br.readLine();
                dout.writeUTF(msgout);
                dout.flush();
            }
            s.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
