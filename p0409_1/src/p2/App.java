package p2;

public class App {
    public static void main(String[] args) {
        // 네트워크
            // 데이터의 송수신
            // 네트워크적으로 연결 필요

            // 서버 - 클라이언트
                // 서버
                    // 먼저 실행되는 시스템
                // 클라이언트
                    // 접속을 시도하는 시스템

            // 주소
                // 시스템 개별 주소
            // 포트
                // 시스템 내 프로그램 개별 포트 번호

        // www - world wide web
            // 트리 구조
            // 단말 - 허브 - ISP(Internet Service Provider)
        // IP 주소 + 포트 번호
            // 무조건 IP주소로 연결
            // 하나의 프로그램은 여러개의 포트번호를 가질 수 있음
        // 도메인 네임
            // 웹브라우저 - 클라이언트 역할
            // 네이버 - 서버
                // www.naver.com:443
                    // https 포트 번호
            // Domain Name Service
                // 문자 <-> Ip 주소
                // 문자 -> ISP(IP-DNS 맵핑) -> Ip 주소 -> 접속
                    // 네트워크 트래픽으로 송신
                    // DNS서버 변경 = ISP 변경
        // 방화벽 firewall
            // 접속 허용, 차단 등 역할
            // 포트번호를 이용해 가동
            // 인바운드 규칙
                // 들어오는 트래픽 제어
            // 아웃바운드 규칙
                // 나가는 트래픽 제어
    }
}
