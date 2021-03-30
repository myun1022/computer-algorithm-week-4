

//03.29(MON) : 랜덤 N개의 점 X좌표 오름차순 정렬 구현 (합병 정렬)

import java.util.Random;

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
/*
    static int divideConquer(int left, int right) {

        int mid = (left + right) / 2;
        int dleftsec = divideConquer(left, mid);        // 왼쪽부터 중간
        int drightsec = divideConquer(mid + 1, right);    // 중간부터 오른쪽

        int dMin = dleftsec < drightsec ? dleftsec : drightsec;

        return dMin;
    }
*/
    static int getDistanceJegob(int[][] A, int a, int b) {
        return (A[a][0] - A[b][0]) * (A[a][0] - A[b][0]) + (A[a][1] - A[b][1]) * (A[a][1] - A[b][1]);
    }


    public static void main(String[] args) {
        Random r = new Random();
        int[][] arr = new int[r.nextInt(10)][2];
        for (int i = 0; i < arr.length; i++) {
            arr[i][0] = r.nextInt(10); //x좌표
            arr[i][1] = r.nextInt(10); //y좌표
            System.out.println(arr[i][0] + " " + arr[i][1]); //정렬 전 출력
        }


        System.out.println("정렬해보자!");
        ClosestPairOfPoints sorter = new ClosestPairOfPoints();
        sorter.mergeSort(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i][0] + " " + arr[i][1] +" ");
           // if(i!= arr.length){
           //     System.out.println("점사이 거리 제곱은 "+getDistanceJegob(arr,i,i+1));//제곱거리 함수확인코드
           // }

        }

    }
}


// 출력확인코드-> 아예 안나오네?..
/*+ getDistanceJegob(arr,i,i+1 )
 *
 *
 * */