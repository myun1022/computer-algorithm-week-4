//03.29(MON) : 랜덤 N개의 점 X좌표 오름차순 정렬 구현 (합병 정렬)

//03.30 나머지부분 다 짜봤는데 역시나 오류....

//03.31 오류 메세지는 안뜨는데 왜 점이 3개 이상일떄 거리가 0이 나오는거지..?
//오류 조금 더 수정 근데 아직도 점이 3개 이상일떄 거리 0...

import java.util.*;

public class ClosestPairOfPoints {

    public static void x_mergeSort(int[][] A, int p, int q) {
        if (p >= q) return;       //배열의 원소의 수가 2개 이상이여야 함
        int k = (p + q) / 2;      //중간

        x_mergeSort(A, p, k);         //앞 부분
        x_mergeSort(A, k + 1, q);  //뒷 부분
        x_merge(A, p, k, q);          //합병
    }

    public static void x_merge(int[][] A, int p, int k, int q) {
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

    public static void y_mergeSort(int[][] A, int p, int q) {
        if (p >= q) return;       //배열의 원소의 수가 2개 이상이여야 함
        int k = (p + q) / 2;      //중간

        y_mergeSort(A, p, k);         //앞 부분
        y_mergeSort(A, k + 1, q);  //뒷 부분
        y_merge(A, p, k, q);          //합병
    }

    public static void y_merge(int[][] A, int p, int k, int q) {
        int[][] C = new int[q - p + 1][2];
        int i = p, j = k + 1, n = 0;

        while (i <= k && j <= q) {
            C[n][1] = A[i][1] < A[j][1] ? A[i][1] : A[j][1];          // y좌표가 더 작은 값 대입
            C[n++][0] = A[i][1] < A[j][1] ? A[i++][0] : A[j++][0];    // x좌표 대입
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

    static double dist(int[][] A, int i, int j) {     //점과 점 사이의 거리
        return Math.sqrt(Math.pow(A[i][0]-A[j][0], 2)+Math.pow(A[i][1]-A[j][1], 2));

    }

    static double ClosestPair(int[][] A, int p, int q) { //최근접 점 구하기
        double closest = dist(A, p, q);
        for(int i = p; i < q; i++)
            for(int j = p+1; j <= q; j++) {
                double distance = dist(A, i, j);
                if(distance < closest) closest = distance;
            }
        return closest;         //최근접 점의 거리 리턴
    }

    static double searchClosest(int[][] A, int p, int q) {
        int number = q-p+1;
        if(number <= 3)                               //점의 개수가 3이하면 그 점들 중 최근접 점 구하기
            return ClosestPair(A, p, q);

        int k = (p+q)/2;                              // 1/2로 나누어 최근접 점 찾기
        double left = searchClosest(A, p, k);
        double right = searchClosest(A, k+1, q);

        double closest = left < right ? left : right;   //왼쪽과 오른쪽 영역 중 더 작은 거리 찾기


        int i;                                         //중간영역 구하기
        for(i = p; i < k;i++ ) {
            if(A[i][0] > (A[k][0] - closest )) break;
        }
        int j;
        for(j = q; j > k+1; j-- ) {
            if(A[j][0] < (A[k+1][0] + closest )) break;
        }

        int[][]Y=new int[j-i+1][2];             //y좌표를 기준으로 정렬할 중간 영역 배열 생성
        for(int l=i; l<=j; l++) {
            int m=0;
            Y[m][0]=A[l][0];
            Y[m][1]=A[l][1];
            m++;
        }
/*
        System.out.println("정렬 전");
        for (int s = 0; s < Y.length; s++) {
            System.out.println("Y : ("+Y[s][0] + ", " + Y[s][1]+")");
        }

 */

        y_mergeSort(Y,0, Y.length-1);     // y좌표를 기준으로 중간 영역 정렬

        /*
        System.out.println("정렬 후");
        for (int s = 0; s < Y.length; s++) {
            System.out.println("Y : ("+Y[s][0] + ", " + Y[s][1]+")");
        }

         */

        for(int l = 0; l < Y.length-1; l++)
            for(int m = 1; m <= Y.length-1; m++){
                if(Math.abs(Y[l][1]-Y[m][1]) <closest) {
                    double t = dist(Y, l, m);
                    if (t < closest)
                        closest = t;
                }
            }

        return closest;
    }


    public static void main(String[] args) {

        Random r = new Random();
        int[][] arr = new int[r.nextInt(10)][2];    //랜덤한 n개의 점

        for (int i = 0; i < arr.length; i++) {
            arr[i][0] = r.nextInt(10);    //x좌표
            arr[i][1] = r.nextInt(10);    //y좌표
        }


        for (int i = 0; i < arr.length; i++) {
            System.out.println("("+arr[i][0] + ", " + arr[i][1]+")");
        }
        System.out.println();


        ClosestPairOfPoints sorter = new ClosestPairOfPoints();
        sorter.x_mergeSort(arr, 0, arr.length - 1);

        /*
        System.out.println("정렬 후");
        for (int i = 0; i < arr.length; i++) {
            System.out.println("("+arr[i][0] + ", " + arr[i][1]+")");
        }
         */

        System.out.println();

        if(arr.length==0)
            System.out.println("점의 개수가 0개이다.");
        else if(arr.length==1)
            System.out.println("점의 개수가 1개이다.");
        else {
            double closestdistance = searchClosest(arr, 0, arr.length - 1);
            System.out.println("최근접 점 쌍의 거리 : " + closestdistance);
        }
    }
}