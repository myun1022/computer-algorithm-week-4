//3.29(월) : 랜덤 n개의 점 배치 후, 좌, 우 분할을 위해 정렬해주기.
//참고
//2차원 배열, comparator 이용
//Comparator interface, compare() 메서드를 오버라이드


import java.util.Random; //난수 발생에 필요한 클래스
import java.util.Comparator; //for sort
import java.util.Arrays; //for sort

public class sort2 {

    public static void main(String[] args) {
        Random r = new Random();        //현재 시스템 시간을 seed(난수 발생의 기준값)로 가지는 Random 객체 생성

        int[][] A = new int[r.nextInt(10)][2];
        for (int i = 0; i < A.length; i++) {
            A[i][0] = r.nextInt(10); //x좌표
            A[i][1] = r.nextInt(10); //y좌표
            System.out.println(A[i][0] + " " + A[i][1]); //정렬 전 출력
        }
        System.out.println("정렬해보자!");

        Arrays.sort(A, new Comparator<int[]>() { //Comparator 클래스 : 원하는 조건 설정(기본 정렬과 다른 조건을 원할 때 사용.)
            @Override
            public int compare(int[] o1, int[] o2) { //compare 메서드
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < A.length; i++) {
            System.out.println(A[i][0] + " " + A[i][1]);
        }
    }
}


