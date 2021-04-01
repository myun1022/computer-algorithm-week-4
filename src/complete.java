//03.29(Mon) : 랜덤 N개의 점 X좌표 오름차순 정렬 구현 (합병 정렬)
//03.30(Tue) : 좌우로 분할하며 각 근접거리를 찾고, 중간으로부터 그 근접거리보다 가까운 x좌표를 가진 점들을 모아 y좌표 정렬을 해준 후 더 근접한지 확인 구현

import java.util.Random;

public class complete {

    //x좌표 오름차순 정렬
    static void XmergeSort(int[][] A, int p, int q) {
        if (p >= q) return;       //배열의 원소의 수가 2개 이상이여야 함
        int k = (p + q) / 2;      //중간

        XmergeSort(A, p, k);         //앞 부분
        XmergeSort(A, k + 1, q);  //뒷 부분
        Xmerge(A, p, k, q);          //합병
    }

    static void Xmerge(int[][] A, int p, int k, int q) {
        int[][] C = new int[q - p + 1][2];
        int i = p, j = k + 1, n = 0;

        while (i <= k && j <= q) {
            C[n][0] = A[i][0] < A[j][0] ? A[i][0] : A[j][0];          // y좌표가 더 작은 x좌표 대입
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

    //y좌표 오름차순 정렬
    static void YmergeSort(int[][] A, int p, int q) {
        if (p >= q) return;       //배열의 원소의 수가 2개 이상이여야 함
        int k = (p + q) / 2;      //중간

        YmergeSort(A, p, k);         //앞 부분
        YmergeSort(A, k + 1, q);  //뒷 부분
        Ymerge(A, p, k, q);          //합병
    }

    static void Ymerge(int[][] A, int p, int k, int q) {
        int[][] C = new int[q - p + 1][2];
        int i = p, j = k + 1, n = 0;

        while (i <= k && j <= q) {
            C[n][0] = A[i][1] < A[j][1] ? A[i][0] : A[j][0];          // x좌표가 더 작은 값 대입
            C[n++][1] = A[i][1] < A[j][1] ? A[i++][1] : A[j++][1];    // y좌표 대입
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
        return Math.sqrt(Math.pow(A[i][0] - A[j][0], 2) + Math.pow(A[i][1] - A[j][1], 2));
    }

    //좌우의 근접거리 계산
    static double points(int[][] A, int left, int right) {
        int size = (right - left) + 1;

        if (size < 2) return 0;

        double close = distance(A, left, right);

        if (size == 2 && A.length == 2) {                                  //주어진 점이 2개일 경우 그대로 점 출력
            System.out.println(A[0][0] + " " + A[0][1]);
            System.out.println(A[1][0] + " " + A[1][1]);
        } else if (size <= 3) {
            double d;
            int a = 0, b = 1;
            for (int i = left; i < size; i++) {           //가장 짧은 거리를 찾는다
                for (int j = i + 1; j < size; j++) {
                    d = distance(A, i, j);
                    if (d < close) { close = d; a = i; b = j; }
                }
            }
            if (A.length == 3) {                     //주어진 점이 3개일 경우 그 중에 가장 근접한 2개의 점 출력(동일한 거리가 존재하면 하나의 쌍만 출력됩니다.)
                System.out.println(A[a][0] + " " + A[a][1]);
                System.out.println(A[b][0] + " " + A[b][1]);
            }
        }
        return close;   //가장 짧은 거리 리턴
    }

    static double div_con(int[][] A, int left, int right) {       //분할
        int size = (right - left) + 1;
        if (size <= 3)
            return points(A, left, right);

        int mid = (left + right) / 2;
        double CP_left = div_con(A, left, mid);                     //중간을 기준으로 왼쪽과 오른쪽에서의 최근접 점 거리를 구함
        double CP_right = div_con(A, mid + 1, right);
        double min_dist = Math.min(CP_left, CP_right);              //좌우를 비교하여 더 짧은 거리 선택

        //중앙 부분에 더 가까운 거리가 있을 수 있다
        int[][] B = new int[size][2];
        int n = 0;
        for (int i = left; i <= right; i++) {                        //1. 중간을 기준으로 min_dist보다 가까운 x좌표를 가진 점들을 모아준다.
            int CPC_x = Math.abs(A[i][0] - A[mid][0]);
            if (CPC_x < min_dist) {
                B[n][0] = A[i][0];
                B[n++][1] = A[i][1];
            }
        }
        complete sorter = new complete();
        sorter.YmergeSort(B, 0, n - 1);                      //2. y좌표 오른차순 정렬

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {                      //3. y좌표를 아래서부터 거리를 서로 비교하며 min_dist보다 작다면, 거리 계산
                int CPC_y = Math.abs(B[j][1] - B[i][1]);
                if (CPC_y < min_dist) {
                    double d = distance(B, i, j);
                    if (d < min_dist) min_dist = d;
                }
            }
        }
        return min_dist;
    }

    public static void main(String[] args) {

        Random r = new Random();
        int[][] arr = new int[r.nextInt(10)][2];    //랜덤한 n개의 점     난수 범위 : r.nextInt(num) : (0 <= r <= num - 1)

        for (int i = 0; i < arr.length; i++) {
            arr[i][0] = r.nextInt(10 - (-10) + 1) - 10;    //x좌표       난수 범위 : r.nextInt(max - min + 1) - min : (min <= r <= max)
            arr[i][1] = r.nextInt(10 - (-10) + 1) - 10;    //y좌표
        }

        complete sorter = new complete();
        sorter.XmergeSort(arr, 0, arr.length - 1);          //x좌표 오름차순 정렬
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i][0] + " " + arr[i][1]);      //주어진 점의 좌표 확인
        }
        System.out.println("--");
        System.out.println(div_con(arr, 0, arr.length - 1));    //최근접 점의 거리 출력
    }
}
