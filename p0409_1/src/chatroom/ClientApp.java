package chatroom;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientApp {
    public static void main(String[] args) throws Exception {
        System.out.println("Client Start");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a nickname > ");
        String nickname = scanner.nextLine();

        System.out.println("Attempting to connect to server");
//        Socket socket = new Socket("172.30.1.82", 8888);
        Socket socket = new Socket("172.30.1.56", 8888);

        // 프로토콜(약속)
            // int로 시작하는 커멘드가 무조건 보내게 할 수도 있고
            // 여기서는 접속 처음하면 무조건 닉네임을 보낸다
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(nickname);

        // 대기 구간.
            // scanner
            // 서버 측에서 송신하는 메시지
                // 언제 보낼지 모르기 때문에 대기

        ReceiveThreadForClient receiveThread = new ReceiveThreadForClient();
        receiveThread.socket = socket;
            // 브로드캐스팅 메시지를 받기 위한 쓰레드 생성 밑 소켓 초기화
        receiveThread.start();
            // 쓰레드 시작


        while (true){
            System.out.print("Message for sending (finish = q) > ");
            String message = scanner.nextLine();
                // 여기 멈춰져있는 상태에서도 서버쪽에서 송신하는 브로드캐스팅 메시지를 수신할 수 있어야한다
                    // 따라서 Thread 필요

            // while 내부 쓰레드 하나 - 메인 쓰레드
            // receiveThread 쪽 하나
                // 두개가 번갈아 가면서 실행

            if(message.equals("q")){
                break;
            }
            dataOutputStream.writeUTF(message);
                // 계속 output stream 이다

        }

        System.out.println("Client socket close");
        socket.close();
            // 실제로는 try-catch 문을 이용해 닫는다
            // 클라이언트쪽에서 연결을 끊는다는 뜻
                // 여기서 소켓이 끊기면 receiveThread 쪽도 exception이 발생되어 run이 종료

        System.out.println("Client End");
    }
}


class ReceiveThreadForClient extends Thread{
    public Socket socket;

    @Override
    public void run() {
        try {
            // 이 코드는 q로 탈출해도 run이 종료되지 않는다
                // 어떻게 끝낼것인가?
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            while (true){
                String message = dataInputStream.readUTF();
                    // 여기서 run을 끝낼것이다
                        // socket 연결을 끊으면 Exception이 발생되어
                        // catch로 넘어가서 run이 종료되는 방식
                System.out.println("Receiving message = " + message);
            }
        } catch (Exception e){
            System.out.println("ReceiveThread end.");
//            e.printStackTrace();
        }
    }
}