import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class client {

    public static void main(String[] args) {
        try {
            Socket s = null;
            s = new Socket("itkomsrv.fotonik.dtu.dk", 1102);
            Scanner scanner = new Scanner(System.in);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            s.getInputStream()
                    )
            );
            PrintWriter pw = new PrintWriter(s.getOutputStream());


            boolean serverActive = true;
            boolean b = true;
            while (serverActive) {
                while (b) {
                    String textline = br.readLine();
                    if (textline.equals("SERVER WINS") || textline.equals("PLAYER WINS") || textline.equals("NOBODY WINS")) {
                        System.out.print(textline + "\r\n");
                        s.close();
                        System.exit(0);
                    }
                    System.out.print(textline + "\r\n");

                    if (textline.contains("BOARD IS")) {
                        String substr = textline.substring(9);
                        String board =
                                "+---+---+---+\r\n"+
                                "|1  |2  |3  |\r\n"+
                                "|   |   |   |\r\n"+
                                "|   |   |   |\r\n"+
                                "+---+---+---+\r\n"+
                                "|4  |5  |6  |\r\n"+
                                "|   |   |   |\r\n"+
                                "|   |   |   |\r\n"+
                                "+---+---+---+\r\n"+
                                "|7  |8  |9  |\r\n"+
                                "|   |   |   |\r\n"+
                                "|   |   |   |\r\n"+
                                "+---+---+---+";
                        Map<Integer, Integer> hm = new HashMap<Integer, Integer>() {
                            {
                                put(0,32);
                                put(1,36);
                                put(2,40);
                                put(3,92);
                                put(4,96);
                                put(5,100);
                                put(6,152);
                                put(7,156);
                                put(8,160);
                            }
                        };
                        /*for (int i = 0; i < board.length(); i++) {
                            char c = board.charAt(i);
                            System.out.println(i + " | " + c + " STOP");
                        }*/


                        for (int i = 0; i < substr.length(); i++) {
                            char c = substr.charAt(i);
                            if (c == '.') {
                                continue;
                            } else {
                                board = board.substring(0, hm.get(i)) + c + board.substring(hm.get(i) + 1);
                            }

                        }
                        System.out.print(board + "\r\n");
                    }



                    if (textline.equals("YOUR TURN")) b = false;
                    }

                pw.print(scanner.nextLine() + "\r\n");
                pw.flush();

                b = true;




            }
            s.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
