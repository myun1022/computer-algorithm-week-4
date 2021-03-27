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



}
