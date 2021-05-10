package a5;

import java.io.IOException;
import java.net.*;

public class Cliente {

    public static void main(String[] args) throws IOException {
        String ip = "localhost";
        int port = 5353;
        MulticastSocket s = new MulticastSocket(port);
        InetAddress i = InetAddress.getByName(ip);
        InetSocketAddress group = new InetSocketAddress(i, port);
        NetworkInterface ni = NetworkInterface.getByInetAddress(i);
        s.joinGroup(group, ni);
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        int[] speeds = new int[5];
        int average = 0;

        while (true){
            for (int j = 0; j < speeds.length; j++) {
                s.receive(packet);
                speeds[j]= packet.getData()[3];
            }
            average = (speeds[0]+speeds[1]+speeds[2]+speeds[3]+speeds[4])/5;
            System.out.println("average: " + average);
        }
    }
}
