package p3;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args) throws Exception {
        System.out.println("Client start.");

        // 접속시 접속주소와 포트 번호가 필요

        Scanner scanner = new Scanner(System.in);

        Socket socket = new Socket("172.30.1.82", 7777);
//        Socket socket = new Socket("172.30.1.56", 7777);
//        System.out.println(socket);
//        System.out.println(socket.getOutputStream());
        // 소켓을 생성하는 순간 접속을 시도함
            // 단 소켓에 접속주소와 포트 번호를 넘겨줘야한다
            // 접속실패시 Exception

        System.out.println("Connection success");

        System.out.print("Enter send message > ");
        String message = scanner.nextLine();

        // 보낼 때는 OutPutStream
            // 네트워크는 Buffered Stream 을 사용하지 않고
                // 버퍼에 저장할 시간 없다 네트워크는.. 바로바로
            // Data Stream 을 바로 쓴다
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            // 파일 입출력과 동일하다
                // File - Data - Buffer
        dataOutputStream.writeUTF(message);

        System.out.println("Client end.");
    }
}
