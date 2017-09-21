package udpserver;

/**
 *
 * @author Faisal
 */

import java.net.*;
import java.io.*;

public class UDPServer {
    public static void main(String[] args) {
        
        DatagramSocket aSocket = null;
        
        if(args.length < 1){
            System.out.println("Usage: Java UDPServer <Port Number>");
            System.exit(1);
        }
        
        try{
            int socketNo = Integer.valueOf(args[0]).intValue();
            aSocket = new DatagramSocket(socketNo);
            byte[] buffer = new byte[1000];
            
            while(true){
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                
                DatagramPacket reply = new DatagramPacket(request.getData(),
                    request.getLength(), request.getAddress(), request.getPort());
                aSocket.send(reply);
            }
        }
        catch(SocketException e){
            System.out.println("Socket: " + e.getMessage());
        }
        catch(IOException e){
            System.out.println("IO: " + e.getMessage());
        }
        finally {
            if(aSocket != null)
                aSocket.close();
        }
        
    }
    
}
