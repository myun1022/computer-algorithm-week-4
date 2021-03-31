//03.29(MON) : 랜덤 N개의 점 X좌표 오름차순 정렬 구현 (합병 정렬)

import java.util.Random;


public class ClosestPairOfPoints {

    static void mergeSortX(int[][] A, int p, int q) {
        if (p >= q) return;       //배열의 원소의 수가 2개 이상이여야 함
        int k = (p + q) / 2;      //중간

        mergeSortX(A, p, k);         //앞 부분
        mergeSortX(A, k + 1, q);  //뒷 부분
        mergeX(A, p, k, q);          //합병
    }

    static void mergeX(int[][] A, int p, int k, int q) {
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

    static void mergeSortY(int[][] A, int p, int q) {
        if (p >= q) return;       //배열의 원소의 수가 2개 이상이여야 함
        int k = (p + q) / 2;      //중간

        mergeSortY(A, p, k);         //앞 부분
        mergeSortY(A, k + 1, q);  //뒷 부분
        Ymerge(A, p, k, q);          //합병
    }

    static void Ymerge(int[][] A, int p, int k, int q) {
        int[][] C = new int[q - p + 1][2];
        int i = p, j = k + 1, n = 0;

        while (i <= k && j <= q) {
            C[n][0] = A[i][1] < A[j][1] ? A[i][0] : A[j][0];          // x 좌표가 더 작은 값 대입
            C[n++][1] = A[i][1] < A[j][1] ? A[i++][1] : A[j++][1];    // y 좌표 대입
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


    static double getDistance(int[][] arr, int a, int b) {
        return Math.sqrt(Math.pow(arr[a][0] - arr[b][0], 2) + Math.pow(arr[a][1] - arr[b][1], 2));

    }


    static double div_con(int[][] arr, int left, int right) {

        mergeSortX(arr, left, right);
        // 좌표가 3개 이하이면 그냥 거리를 구한다

        if(arr.length == 3){
            double distance = getDistance(arr,0,1);;
            for(int i = 0; i < arr.length; i++) {
                for(int j = i+1; j < arr.length; j++) {
                    double tmp = getDistance(arr,i,j);
                    if(tmp < distance)	distance = tmp;
                }
            }
            return distance;

        }else if(arr.length == 0){
            System.out.println("점이 없음.");
            return 0;
        }else if(arr.length==1){
            System.out.println("점이 1개밖에 없음");
            return 0;
        }else if(arr.length == 2){
            return getDistance(arr,0,1);
        }else {

            // 분할 정복
            if (left < right) {
                int mid = (left + right) / 2;
                double CPL = div_con(arr, left, mid);
                double CPR = div_con(arr, mid + 1, right);
                double d = Math.min(CPL, CPR); // 짧은 거리 찾기

                int[][] part_arr = new int[right + 1][2];    // 중간으로부터 x 축 기준 d 이내에 있는 점들만 배열에 저장
                int j = 0;


                for (int i = 0; i < right + 1; i++) { // arr를 n/2 의 x값 기준으로 d 이내에 있는 점들만 part_arr에 저장
                    if (Math.abs(arr[i][0] - arr[mid][0]) < d) {
                        part_arr[j][0] = arr[i][0];
                        part_arr[j][1] = arr[i][1];
                        j++;
                    }
                }
                return Math.min(d, part(part_arr, j, d));
            }
        }
        return Double.MAX_VALUE;
    }


    static double part(int[][] part_arr, int size, double d) { // 아까 part_arr을 y기준으로 정렬
        mergeSortY(part_arr, 0, size-1);

        double min = d;

        // 좌표들의 y기준으로 d보다 작은 거리에 있는 값들만 서로 거리를 비교
        for(int i = 0; i < size; i++) {
            for(int j = i+1; j < size; j++) {
                if((part_arr[j][1] - part_arr[i][1]) < min){
                    double temp = getDistance(part_arr,i,j);
                    if(temp < min)	min = temp;
                }
            }
        }
        return min;
    }

    public static void main(String[] args) {
        Random r = new Random();
        int[][] arr = new int[r.nextInt(10)][2];
        for (int i = 0; i < arr.length; i++) {
            arr[i][0] = r.nextInt(10); //x좌표
            arr[i][1] = r.nextInt(10); //y좌표
            System.out.println(arr[i][0] + " " + arr[i][1]); //정렬 전 출력
        }

        ClosestPairOfPoints sorter = new ClosestPairOfPoints();
        sorter.mergeSortX(arr, 0, arr.length - 1);

        // 입력상태 출력
        System.out.println("-정렬된 값-");
        for(int i = 0 ; i < arr.length; i++) {
            System.out.println(arr[i][0] + " " + arr[i][1]);
        }
        // 결과 출력
        System.out.println("-최소거리는-");
        System.out.println(div_con(arr, 0, arr.length-1));



    }
}