import java.util.Random; //난수를 발생시키기 위한 클래스

public class Sorter {

    private void merge(int[] A, int p, int k, int q) { //합병
        int[] C = new int[q - p + 1];

        int i = p, j = k + 1, n = 0;
        while (i <= k && j <= q) C[n++] = A[i] < A[j] ? A[i++] : A[j++]; //작은 값 대입
        while (i <= k) C[n++] = A[i++];
        while (j <= q) C[n++] = A[j++]; //남은 값 대입

        for (int a = p, b = 0; a <= q; a++) A[a] = C[b++]; //기존 배열 위치 변경
    }

    public void mergeSort(int[] A, int p, int q) {
        if (p >= q) return;  //배열의 원소의 수가 2개 이상이여야 함
        int k = (p + q) / 2; //중간 값
        mergeSort(A, p, k);  //앞부분
        mergeSort(A, k + 1, q); //뒷부분
        merge(A, p, k, q); //합병
    }

    public static void main(String[] args) {
        Random r = new Random();                  //현재 시스템 시간을 seed(난수 발생의 기준값)로 가지는 Random 객체 생성
        int[] A = new int[r.nextInt(10)]; //int형 난수 발생 (0 <= r < 100)만큼 길이의 배열 생성
        for (int i = 0; i < A.length; i++) {
            A[i] = r.nextInt(10);  //배열의 크기만큼 인덱스 값에 난수 넣어주기
            System.out.printf("%d ", A[i]);  //배열 A 인덱스 값 출력
        }
        System.out.println();

        Sorter sorter = new Sorter(); //Sorter 객체 생성
        sorter.mergeSort(A, 0,  A.length - 1); //mergeSort라는 함수 변수로 배열, 시작 수, 끝 수 대입

        for (int i : A) { //for-each : A의 값을 변수 i에 담고 중괄호 안으로 전달
            System.out.printf("%d ", i);
        }
    }
}

