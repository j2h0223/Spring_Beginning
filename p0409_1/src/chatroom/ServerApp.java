package chatroom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class ServerApp {
    public static void main(String[] args) throws Exception{
        System.out.println("Server Start");


        ServerSocket serverSocket = new ServerSocket(8888);
            // 서버 소켓을 열어야 얘가 소켓이 됨
                // 서버는 포트 번호만 필요.

        // client의 접속을 모두 받기 위한 무한 반복문
        while (true){
            Socket socket = serverSocket.accept();
                // 접속이 계속되면 socket 에 계속 저장(접속연결)
                    // 배열 컨트롤 필요

            // 서버 쪽에서는 클라이언트가 접속되자마자 클라이언트 닉네임이 바로 수신된다
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String nickname = dataInputStream.readUTF();

            // 닉네임과 소켓을 연결해 DTO를 만듦
                // 사실 소켓이 핵심이다
            ClientInfo clientInfo = new ClientInfo();
            clientInfo.nickname = nickname;
            clientInfo.socket = socket;
                // Thread 관리가 굉장히 중요
                    // 접속자마다 하나씩 Thread가 생길것이고 일반 자료구조는 비동기식이 동기식되는 순간 임계영역이 되므로 굉장히 문제가 생긴다
                    // 따라서 안전한 Vector를 이용해 ClientInfo를 관리
            ClientInfoList.list.add(clientInfo);


            ClientInfoList.broadCast(nickname + " is enter the chatroom.");

            ReceiveThreadForServer serverReceiveThread = new ReceiveThreadForServer();
            serverReceiveThread.clientInfo = clientInfo;
            serverReceiveThread.start();

            // Thread 의 개수
                // main - accept 하나
                // 접속자마다 Thread가 계속 생성
                    // 받자 마자 브로드 케스팅

        }   // 연결은 완성
        // 문제점 발생
            // 소켓별 모두 리드해야하는데
                // 누가 먼저 메세지를 보낼지 모름
                // 누가 보냈는지도 모름
                // 누가 보낼지도 모름
            // 따라서 리드 코드를 Thread를 이용해 구현
                // Thread를 이용해 대기
                    // 클라이언트별 Tread가 필요


//        System.out.println("Server End");
    }
}
class ClientInfoList {
    public static List<ClientInfo> list = new Vector<>();

    // 브로드캐스팅 API 추가
        // 너무 지저분해질것 같아서
    public static void broadCast(String message){

        System.out.println("[Server log] : " + message);

        for (ClientInfo clientInfo : list){
            System.out.println("Message BroadCasting Start");

            try {
                DataOutputStream dataOutputStream = new DataOutputStream(clientInfo.socket.getOutputStream());
                dataOutputStream.writeUTF(message);
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
    }
}

class ClientInfo {
    public String nickname;
    public Socket socket;
}

class ReceiveThreadForServer extends Thread{
    // 클라이언트별 개별 Thread
    public ClientInfo clientInfo;
        // 클라이언트별 소켓을 위한 필드

    @Override
    public void run(){
        try {
            DataInputStream dataInputStream = new DataInputStream(clientInfo.socket.getInputStream());

            while (true) {
                String message = dataInputStream.readUTF();
                // 받고 모든 클라이언트에게 브로드캐스팅
                        ///  클라이언트 접속이 끊기면 Exception이 발생하는 지점이다

                // ClientInfoList에서 리스트를 돌며 브로드캐스팅하는 방식도 있다
                // 하지만 강의에서는 이방식하다가 다시 리스트로 했음
                    // 입장했습니다가 필요해서...
                String sendMessage = clientInfo.nickname + " : " + message;
                    // 누가 보냈는지 알아야하는까 닉네임을 엮어서 보냄
//                System.out.println("[Server log] : " + sendMessage);
//                    // 단순한 테스팅용 서버로그
//                for (ClientInfo clientInfo : ClientInfoList.list){
//
//                }
                ClientInfoList.broadCast(sendMessage);

            }

        } catch (Exception e){
            // 클라이언트 접속이 끊기면 처리해주는 catch
            ClientInfoList.list.remove(clientInfo);
//            e.printStackTrace();
            // 절대적으로 네트워크는 Try-catch를 잘해야한다
            ClientInfoList.broadCast(clientInfo.nickname + " is disconnection.");
        }

    }
}