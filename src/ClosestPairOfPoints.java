import java.util.Arrays;
import java.util.Scanner;

public class ClosestPairOfPoints {
    public static Point p[];

    public static class Point {
        int x;
        int y;
        Point( int x, int y ) {
            this.x = x;
            this.y = y;
        }
    }
    static int getDistancejegob( Point p1, Point p2 ) {
        return (p1.x-p2.x) * (p1.x-p2.x) + (p1.y-p2.y) * (p1.y-p2.y);
    }

    static int divideConquer( int left, int right ) {

        int mid = ( left + right ) / 2;
        int dleftsec = divideConquer( left, mid );		// left section distance
        int drightsec = divideConquer( mid+1, right );	// right section distance

        int dMin = dleftsec < drightsec ? dleftsec : drightsec;

        return dMin;
    }

    //팀플



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        p = new Point[n];
        for( int i = 0; i < n; i++ ) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            p[i] = new Point(a,b);
        }


        Arrays.sort( p, ( p1,p2 ) -> 
        {
            return (p1.x - p2.x);
        });


        for(int i = 0 ; i<n; i++){
            System.out.printf("%d %d\n",p[i].x,p[i].y);
        }


    }

    //21.03.28 우선은 X축 정렬 까지만 작업





}
