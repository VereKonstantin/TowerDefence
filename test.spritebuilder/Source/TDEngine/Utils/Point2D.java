/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.List;

/**
 *
 * @author Константин
 */

public final class  Point2D
{
        @Override
        public boolean equals(Object obj) {
            Point2D onp = (Point2D) obj;
            if((this.x== onp.x) &&
                 (this.y== onp.y))
                    return true;
            return false;

        }

  public Point2D(double x,double y)
    {
     this.x = (float) x;
     this.y = (float) y;
    } public Point2D(float x,float y)
    {
     this.x = x;
     this.y = y;
    } 
    public Point2D(int x,int y)
    {
     this.x = x;
     this.y = y;
    }
    public float x;
    public float y;

    public double distance(Point2D pointStart) {
        double dx=pointStart.x - this.x;
        double dy=pointStart.y - this.y;
        return Math.sqrt(
                dx*dx+dy*dy
                );
    }

    static public double NOD(double x,double y, double z)
    {
        double n1 = NOD(x,y);
        return NOD(n1,z);
    }
    static public double NOD(double x,double y)
    {
        x= Math.abs(x);
        y= Math.abs(y);
        if(x==0)return y;
        if(y==0) return x;
        if(x==1) return 1;
        if(y==1) return 1;
        while ( x != y)
        {
            if(x>y) x-=y;
            else y-=x;
        }
        return x;

    }
    
    static public boolean intersect(Point2D a,Point2D b,Point2D c,Point2D localizationPoint)  
    {
        Point2D p1 = a,p2=b,p3=c,p4=localizationPoint;
        double A1 = p1.y - p2.y; // Ax+By+c = 0;
        double B1 = p2.x - p1.x;
        double C1 = p1.x*p2.y - p2.x*p1.y;
        double A2 = p3.y - p4.y; // Ax+By+c = 0;
        double B2 = p4.x - p3.x;
        double C2 = p3.x*p4.y - p4.x*p3.y;

        // double k1 = -A1/B1;
        // double k2 = -A2/B2;
        double node = NOD(A1,B1,C1);
        A1 /= node;
        B1 /= node;
        C1 /= node;
        node = NOD(A2,B2,C2);
        A2 /= node;
        B2 /= node;
        C2 /= node;



        if((A1 == A2) &&  (B1== B2)   &&   (C1 == C2) ||
        (A1 == -A2) &&  (B1== -B2)   &&   (C1 == -C2))
                {
                    if(p1.distance(p2) >= p1.distance(p3))
                     return true;
                    if(p1.distance(p2) >= p2.distance(p3))
                        return true;
                }


        return (Point2D.rotate(a, b, c)* Point2D.rotate(a, b, localizationPoint) <= 0) &&
                    (Point2D.rotate(c, localizationPoint, a)*Point2D.rotate(c, localizationPoint, b) < 0) ;
    }


    public static double[] getABCNormilized(Point2D p1, Point2D p2)
    {
        double[] abc= new double[3];
        abc[0] = p1.y - p2.y; // Ax+By+c = 0;
        abc[1] = p2.x - p1.x;
        abc[2] = p1.x*p2.y - p2.x*p1.y;
        double node = NOD(abc[0] ,abc[1] ,abc[2] );
        abc[0] /= node;
        abc[1] /= node;
        abc[2] /= node;
        return abc;
        
    }

    private static boolean onLine(double[] abc1, double[] abc2) {
        if((abc1[0] == abc2[0]) && (abc1[1]== abc2[1]) && (abc1[2] == abc2[2]) ||
                (abc1[0] == -abc2[0]) && (abc1[1]== -abc2[1]) && (abc1[2] == -abc2[2]))
        return true;
       return false;

    }

    public static  Point2D[]  getIntersectPoint(Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
        Point2D[] answ;

        double A1 = p1.y - p2.y; // Ax+By+c = 0;
        double B1 = p2.x - p1.x;
        double C1 = p1.x*p2.y - p2.x*p1.y;
        double A2 = p3.y - p4.y; // Ax+By+c = 0;
        double B2 = p4.x - p3.x;
        double C2 = p3.x*p4.y - p4.x*p3.y;
        double node = NOD(A1,B1,C1);
        A1 /= node;
        B1 /= node;
        C1 /= node;
        node = NOD(A2,B2,C2);
        A2 /= node;
        B2 /= node;
        C2 /= node;

        if((A1 == A2) &&  (B1== B2)   &&   (C1 == C2) ||
                (A1 == -A2) &&  (B1== -B2)   &&   (C1 == -C2))
                {
                    double length1 =  p1.distance(p2);
                    answ = new Point2D[2];
                    if(length1 >= p1.distance(p3) && ( p2.distance(p3) <= length1))
                    {
                        answ[0] = p3;
                        if(p4.distance(p1) < p4.distance(p2))
                            answ[1] = p1;
                        else
                            answ[1] = p2;
                    }
                    else
                    {
                        answ [0] = p4;
                        if(p3.distance(p1) < p3.distance(p2))
                            answ[1] = p1;
                        else
                            answ[1] = p2;
                    }
                    return answ;
                }

        answ = new Point2D[1];



        if (p3.x == p4.x)   // вертикаль
        {
            double y = p1.y + ((p2.y - p1.y) * (p3.x - p1.x)) / (p2.x - p1.x);
            answ[0] =     new Point2D(p3.x, y);
            return answ;
        }
        else if (p4.y == p3.y)          // горизонталь
        {
            double x = p1.x + ((p2.x - p1.x) * (p3.y - p1.y)) / (p2.y - p1.y);
            answ[0] = new Point2D( x, p3.y);
            return answ;
        }

        double x=((p1.x*p2.y-p2.x*p1.y)*(p4.x-p3.x)-(p3.x*p4.y-p4.x*p3.y)*(p2.x-p1.x))/((p1.y-p2.y)*(p4.x-p3.x)-(p3.y-p4.y)*(p2.x-p1.x));
        double y=((p3.y-p4.y)*x-(p3.x*p4.y-p4.x*p3.y))/(p4.x-p3.x);
        x*=-1;
        answ[0] =  new Point2D(x, y);
        return answ;
    }
    static public double rotate(Point2D a,Point2D b, Point2D c)
    {
        return (b.x - a.x)*(c.y-b.y) - (b.y-a.y)*(c.x-b.x);
    }

    // c<-b->a
    public static double scalyar(Point2D a, Point2D b, Point2D c) {

        double x1 = b.x - a.x;
        double x2 =c.x-b.x;
        double y1 = b.y-a.y;
        double y2 = c.y-b.y;
        return (x1*x2 + y1*y2);
    }

    public static boolean intersectRayHorizontalRightFromLocPoint(Point2D point1, Point2D point2, Point2D locPoint) {
       if(point1.x < locPoint.x)
           if(point2.x < locPoint.x)
               return false;
        if((point1.y > locPoint.y) && (point2.y < locPoint.y))
            return true;
        if((point1.y < locPoint.y) && (point2.y > locPoint.y))
            return true;

        return false;
    }

    public static boolean onOneLine(Point2D p1, Point2D p2, Point2D p3) {
        double[] ABC1 = getABCNormilized(p1,p2);
        double[] ABC2 = getABCNormilized(p2,p3);
        if(onLine(ABC1,ABC2))
            return true;
                 return false;


    }


}