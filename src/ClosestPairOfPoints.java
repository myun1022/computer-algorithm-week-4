//03.29(Mon) : 랜덤 N개의 점 X좌표 오름차순 정렬 구현 (합병 정렬)
//03.30(Tue) : PPT p.68에서 Line 1, 3~4 구현(분할 정복) -> 점 3개 이하일 경우까지만.. 일단은 ㅎ

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

    static double distance(int[][] A, int i, int j) { // 두 점 사이의 거리 계산
        return Math.sqrt(Math.pow(A[i][0] - A[j][0],2) + Math.pow(A[i][1] - A[j][1],2));
    }

    static double points(int [][]A){
        int size = A.length;
        double close = distance(A, 0, 1);
        if (size == 2) {                                  //점이 2개일 경우 그대로 점 출력
            System.out.println(A[0][0] + " " + A[0][1]);
            System.out.println(A[1][0] + " " + A[1][1]);
            return 0;
        }
        else if (size == 3) {                              //점이 3개일 경우 그 중에 가장 근접한 2개의 점 출력(동일한 거리가 존재하면 하나의 쌍만 출력됩니다.)
            double d;
            int a = 0, b = 0;
            for (int i = 0; i < size; i++) {
                for (int j = i+1; j < size; j++) {
                    d = distance(A, i, j);
                    if (d < close) { close = d; a = i; b = j; }
                }
            }
            System.out.println(A[a][0] + " " + A[a][1]);
            System.out.println(A[b][0] + " " + A[b][1]);
        }
        return close;
    }

    static double div_con(int[][] A, int left, int right) {  // 아직 중앙부분 추가 전이라 점 3개 초과일 경우 오류 발생합니다.
        int size = A.length;
        if (size <= 3)
            return points(A);

        int mid = (left + right) / 2;
        double CP_left = div_con(A, left ,mid);
        double CP_right = div_con(A, mid + 1, right);
        double min_dist = Math.min(CP_left, CP_right);



        return min_dist;
    }

    public static void main(String[] args) {

        Random r = new Random();
        int[][] arr = new int[r.nextInt(10)][2];    //랜덤한 n개의 점

        for (int i = 0; i < arr.length; i++) {
            arr[i][0] = r.nextInt(10);    //x좌표
            arr[i][1] = r.nextInt(10);    //y좌표
        }

        ClosestPairOfPoints sorter = new ClosestPairOfPoints();
        sorter.mergeSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i][0] + " " + arr[i][1]);
        }
        System.out.println("--");
        div_con(arr, 0, arr.length -1);
    }
}
