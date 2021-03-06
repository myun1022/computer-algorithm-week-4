# 최근접 점의 쌍 찾기

2차원 평면상의 n개의 점이 입력으로 주어질 때, 거리가 가장 가까운 한 쌍의 점을 찾는 문제이다.




### 랜덤한 x, y 좌표 생성

```
 Random r = new Random();
 int[][] arr = new int[r.nextInt(20)][2];
```

Random 클래스를 활용하여 랜덤 객체를 생성한 후 점의 개수(배열의 크기)를 랜덤한 값(0~20)으로 받고, 배열의 각 원소의 x좌표와 y좌표의 값도 랜덤한 값을 받아 대입한다.




### x좌표를 기준으로 오름차순 정렬

합병정렬을 이용하여 x좌표를 정렬. (y좌표를 기준으로 한 정렬도 같은 방법을 사용.)

합병정렬은 입력이 2개의 부분문제로 분할되고, 부분문제의 크기가 1/2로 감소하는 분할 정복 알고리즘이다. 즉, n개의 숫자들을 n/2개씩 2개의 부분문제로 분할하고, 각각의 부분문제를 재귀적으로 합병 정렬한 후, 2개의 정렬된 부분을 합병하여 정렬한다.

```
public static void XmergeSort(int[][] A, int p, int q)
```

배열의 원소의 수가 2개 이상일 경우에만 다음 단계가 수행되도록 한다. 

중간 값인 k를 구하고 k를 기준으로 앞부분과 뒷부분으로 나누어 다시 Xmergesort를 재귀 호출한 뒤 합병한다.

```
public static void Xmerge(int[][] A, int p, int k, int q)
```

합병할 배열의 원소의 수에 맞는 임시 배열 c를 생성한다. 

분할되었던 배열의 원소의 x 좌표 크기를 비교하며 배열 c에 작은 값부터 대입한다. 

앞부분 배열 또는 뒷부분 배열의 값을 모두 대입하고 난 후 남은 원소가 있는 배열에 있는 값을 순서대로 대입한다. 

마지막 과정에 임시 배열에 있는 합병된 원소들을 원래 배열로 복사한다.




### 두 점 사이의 거리 계산

```
return Math.sqrt(Math.pow(A[i][0] - A[j][0], 2) + Math.pow(A[i][1] - A[j][1], 2));
```

제곱근을 구하는 메소드인 Math.sqrt()와 거듭제곱의 값을 구하는 Math.pow를 사용하여 두 점 사이의 거리를 계산한다.




### 좌우의 근접거리 계산

```
static double SearchDist(int[][] A, int left, int right)
```

최근접 점을 구할 배열 영역에 있는 점의 개수가 2개 미만일 경우 return 한다. 

영역에 있는 점들 중 맨 왼쪽에 있는 점과 맨 오른쪽에 있는 점 사이의 거리를 구한 다음에 최근접거리(close)의 초기값으로 설정한다. 

그 후 영역 안에 있는 점의 거리를 구하여 close와 비교하며 더 작은 값을 찾으면 그 값을 close에 대입하는 과정을 모든 점의 거리를 비교할 때까지 반복한다. 

배열 A의 크기가 3일 경우에는 최근접 점 쌍의 인덱스 번호를 구한것을 이용하여 점의 좌표와 함께 출력한다. 

반복을 통해 구한 최근접 거리의 값을 return한다.




### 최근접 점의 쌍 분할 정복 알고리즘

```
static double ClosestPair(int[][] A, int left, int right)
```

최근접 점을 구할 배열 영역에 있는 점의 개수(size)가 3개 이하인 경우에  SearchDist메소드를 호출하여 최근접 거리의 값을 구한다.  

정렬을 위해 사용하였던  XmergeSort와 비슷한 방식으로 size가 4이상일 경우 중간값인 mid를 구한 후 mid를 기준으로 왼쪽과 오른쪽으로 나누어 재귀 호출한다.  

앞의 과정에서 구한 왼쪽 영역의 최근접 거리와 오른쪽 영역의 최근접 거리를 비교하여 더 작은 값을 최근접 거리(min_dist)에 대입한다. 

왼쪽 영역과 오른쪽 영역으로 나누어진 중간 영역에 더 가까운 거리의 점 쌍이 있을 수 있으므로 중간영역을 구한다. 중간 영역은 mid를 기준으로 x좌표의 차가 절댓값 min_dist보다 작은 값인 x좌표를 가진 점들로 구성된다.  

구한 중간영역을 새로운 배열 B에 대입해 준 후 y좌표를 기준으로 오름차순으로 정렬한다.  아래에서부터 y좌표의 거리를 서로 비교하며 min_dist보다 작다면 거리를 계산한다.  

계산한 거리가 min_dist보다 작으면 그 값을 min_dist에 대입한다. 최종적으로 구한 최근접 점의 거리를 return한다.

