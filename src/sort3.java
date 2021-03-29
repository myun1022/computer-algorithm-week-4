//3.29(월) : 랜덤 n개의 점 배치 후, 좌, 우 분할을 위해 x좌표 정렬해주기.
//참고
//객체, Comparable을 이용한 정렬
//정렬할 객체에 Comparable interface, compareTo() 오버라이드


import java.util.Random; //난수 발생에 필요한 클래스
import java.util.Arrays; //sort 메서드를 이용하기 위한 클래스

class XYPoint implements Comparable<XYPoint> { //x,y좌표 객체
    int x;
    int y;

    XYPoint(int x, int y) { //x좌표, y좌표. 생성자
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(XYPoint k) { //x좌표 오름차순(x좌표가 같을 시 y좌표 오름차순)
        if (this.x < k.x) return -1;
        else if (this.x == k.x) {
            if (this.y < k.y) return -1;
            else if (this.y == k.y) return 0;
            else return 1;
        } else return 1;
    }

}


public class sort3 {


    public static void main(String[] args) {
        Random r = new Random();        //현재 시스템 시간을 seed(난수 발생의 기준값)로 가지는 Random 객체 생성

        XYPoint[] A = new XYPoint[r.nextInt(10)]; //랜덤 n(0 <= r < 100)개의 점 생성.
        for (int i = 0; i < A.length; i++) {
            A[i] = new XYPoint(r.nextInt(10), r.nextInt(10)); //x좌표, y좌표를 난수로 받아줍니다.
            System.out.println(A[i].x + " " + A[i].y); //정렬하기 전 한 번 출력해주기
        }

        Arrays.sort(A); //정렬!
        System.out.println("정렬해준 후!");

        for (int i = 0; i < A.length; i++) {
            System.out.println(A[i].x + " " + A[i].y);
        }
    }
}


