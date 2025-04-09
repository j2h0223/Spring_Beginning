package p3;

import java.io.DataInputStream;
import java.net.*;

public class ServerApp {
    public static void main(String[] args) throws Exception{
        // 네트워크 쪽 API는 모두 Try-Catch 해줘야한다
        // 클라이언트의 접속 대기를 해야하므로 서버쪽을 먼저 실행

        System.out.println("Server program start");

        ServerSocket serverSocket = new ServerSocket(7777);
            // 하나의 시스템에서 여러 프로그램이 동시에 같은 포트번호를 사용할 수 없다
            // 소켓은 연결 통로를 받는 무언가...
        Socket clientsocket = serverSocket.accept();
            // 접속 대기
                // 다른 시스템이 접속하면 이후 로직이 실행
            // 접속하면 소켓을 넘겨주는데
                    // 소켓은 API
                    // 소켓 내에는 Input, Output Stream 이 있다
        System.out.println("Someone connect!");

        // 접속 후 데이터의 송수신
            // Input, Output Stream 과 같다
            // 프로토콜을 이용해
                // 파일 규칙과 같이
                // 네트워크 규칙이다

        // 클라는 스캐너를 통해 데이터를 저장후 데이터 송신
        // 데이터를 받을 땐 input stream
        DataInputStream dataInputStream = new DataInputStream(clientsocket.getInputStream());
        System.out.println("Waiting a message from client");
        String message = dataInputStream.readUTF();
            // 클라이언트가 서버에 접속후 클라이언트가 데이터 송신 전까지 해당 줄에서 대기

        System.out.println("Message from client = " + message);

        System.out.println("Server program end");

        // 접속을 위해 양쪽 모두 소켓을 생성
            // 통로를 위한 소켓
            // 소켓을 이용해 데이터 양방향 송수신 가능

        // 서버쪽이 접속자마다 소켓을 따로 관리해야함
            // 접속자별 소켓

                // 사실 Thread가 핵심이

        // 메시지의 송수신
            // 클라이언트 -> 서버 -(브로드캐스팅)-> 클라이언트

        // 기능
            // 클라
                // 사용할 아이디 생성
            // 서버
                // 아이디 송신
                // 수신 아이디 배열 저장
            // 클라
                // 메시지 송신
            // 서버
                // 아이디, 메시지 표시

        // 연결 중단
            // socket.close();
                // 반대편에서는 socket 연결이 끊어졌는지 확인 불가
                // Exception, Thread 핸들링 난이도가 높다


    }
}
