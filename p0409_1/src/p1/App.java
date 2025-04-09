package p1;

public class App {
    public static void main(String[] args) {
//        1부터 100까지의 합을 구하되, 3과 5의 배수는 제외하고 합을 구하자. (배점:100)

        int answer = new Solution(1, 100).calcSum();
        System.out.println(answer);
    }
}

class Solution {
    private int startNum;
    private int endNum;

    public Solution(int startNum, int endNum){
        this.startNum = startNum;
        this.endNum = endNum;
    }

    public int calcSum(){
        int sum = 0;

        for (int number = startNum; number <= endNum; number++) {
            if (check(number)){
                continue;
            }
            System.out.println(number);
            sum += number;
        }

        return sum;
    }

    private boolean check(int number){
        if (number % 3 == 0 && number % 5 ==0){
            return true;
        } else {
            return false;
        }
    }
}
