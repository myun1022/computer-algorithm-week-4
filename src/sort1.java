//3.29(월) : 랜덤 n개의 점 배치 후, 좌, 우 분할을 위해 정렬해주기.
//2차원 배열, 합병 정렬 이용
// -> 완성 x..ㅠㅠ

import java.util.Random;

public class sort1 {

    public static void mergeSort(int[][] A, int p, int q) {
        if (p >= q) return;  //배열의 원소의 수가 2개 이상이여야 함
        int k = (p + q) / 2; //중간 값
        mergeSort(A, p, k);  //앞부분
        mergeSort(A, k + 1, q); //뒷부분
        merge(A, p, k, q); //합병
    }

    public static void merge(int[][] A, int p, int k, int q) { //합병
        int[][] C = new int[q - p + 1][2];

        int i = p, j = k + 1, n = 0;
        while (i <= k && j <= q) {
            C[n][0] = A[i][0] < A[j][0] ? A[i][0] : A[j][0]; //작은 값 대입
            C[n][1] = A[i][0] < A[j][0] ? A[i++][1] : A[j++][1];
            n++;
        }
        while (i <= k) {
            C[n][0] = A[i][0];
            C[n][1] = A[i][1];
            n++;
            i++;
        }
        while (j <= q) {
            C[n][0] = A[j][0]; //남은 값 대입
            C[n][1] = A[j][1];
            n++;
            j++;
        }

        for (int a = p, b = 0; a <= q; a++) {
            A[a][0] = C[b][0]; //기존 배열 위치 변경
            A[a][1] = C[b][1];
            b++;
        }

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
        sort1 sorter = new sort1();
        sorter.mergeSort(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i][0] + " " + arr[i][1]);
        }
    }
}
