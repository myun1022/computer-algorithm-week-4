//03.29(MON) : 랜덤 N개의 점 X좌표 오름차순 정렬 구현 (합병 정렬)

//03.30 나머지부분 다 짜봤는데 역시나 오류....

import java.util.*;

public class ClosestPairOfPoints {

    public static void mergeSort(int[][] A, int p, int q) {
        if (p >= q) return;       //배열의 원소의 수가 2개 이상이여야 함
        int k = (p + q) / 2;      //중간

        mergeSort(A, p, k);         //앞 부분
        mergeSort(A, k + 1, q);  //뒷 부분
        merge(A, p, k, q);          //합병
    }

    public static void merge(int[][] A, int p, int k, int q) {
        int[][] C = new int[q - p + 1][2];
        int i = p, j = k + 1, n = 0;

        while (i <= k && j <= q) {
            C[n][0] = A[i][0] < A[j][0] ? A[i][0] : A[j][0];          // x좌표가 더 작은 값 대입
            C[n++][1] = A[i][0] < A[j][0] ? A[i++][1] : A[j++][1];    // y좌표 대입
        }
        while (i <= k) {
            C[n][0] = A[i][0];
            C[n++][1] = A[i++][1];
        }
        while (j <= q) {
            C[n][0] = A[j][0];
            C[n++][1] = A[j++][1];
        }

        for (int a = p, b = 0; a <= q; a++) {    //기존 배열(A)에 반영
            A[a][0] = C[b][0];
            A[a][1] = C[b++][1];
        }
    }

    static int dist(int[][] A, int i, int j) {     //점과 점 사이의 거리
        return (A[i][0]-A[j][0])*(A[i][0]-A[j][0]) + (A[i][1]-A[j][1])*(A[i][1]-A[j][1]);
    }

    static int ClosestPair(int[][] A, int p, int q) { //최근접 점 구하기
        int closest = dist(A, p, q);
        for(int i = p; i < q; i++)
            for(int j = p+1; j <= q; j++) {
                int distance = dist(A, i, j);
                if(distance < closest) closest = distance;
            }
        return closest;         //최근접 점의 거리 리턴
    }

    static int searchClosest(int[][] A, int p, int q) {
        int number = q-p+1;
        if(number <= 3)                               //점의 개수가 3이하면 그 점들 중 최근접 점 구하기
            return ClosestPair(A, p, q);

        int k = (p+q)/2;                              // 1/2로 나누어 최근접 점 찾기
        int left = searchClosest(A, p, k);
        int right = searchClosest(A, k+1, q);

        int closest = left < right ? left : right;   //왼쪽과 오른쪽 영역 중 더 작은 거리 찾기

        int i;                                         //중간영역 구하기
        for(i = p; i < k;i++ ) {
            if(A[i][0] > A[k][0] - closest ) break;
        }
        int j;
        for(j = q; i > k+1; i-- ) {
            if(A[j][0] < A[k][0] + closest ) break;
        }
        int mid = ClosestPair(A, i, j);      //중간 영역의 최근접 점 찾기


        if(mid < closest) closest = mid;    //왼쪽과 오른쪽 영역중 더 작은 거리와 중간 영역에 있는 거리 중 더 작은 거리 찾기

        return closest;
    }


    public static void main(String[] args) {

        Random r = new Random();
        int[][] arr = new int[r.nextInt(10)][2];    //랜덤한 n개의 점

        for (int i = 0; i < arr.length; i++) {
            arr[i][0] = r.nextInt(10);    //x좌표
            arr[i][1] = r.nextInt(10);    //y좌표
        }

        /*
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i][0] + " " + arr[i][1]);
        }
        System.out.println();
        */

        ClosestPairOfPoints sorter = new ClosestPairOfPoints();
        sorter.mergeSort(arr, 0, arr.length - 1);
/*
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i][0] + " " + arr[i][1]);
        }
 */
        int closestdistance = searchClosest(arr, 0, arr.length);
        System.out.println(closestdistance);
    }
}