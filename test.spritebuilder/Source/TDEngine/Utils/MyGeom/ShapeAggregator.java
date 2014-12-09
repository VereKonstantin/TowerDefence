/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.MyGeom;

import Utils.Point2D;


import java.util.*;


/**
 *
 * @author Константин
 */
 public class ShapeAggregator {
    Shape shape1;
    Shape shape2;
    
    public ShapeAggregator(Shape shape1,Shape shape2)
    {
        this.shape1 = shape1;
        this.shape2 = shape2; 
    }
    
    public Shape connect()
    {
      double sign = getRotateVector(shape1);
      List<Point2D> shape1Values = shape1.perimetr;
      double sign2 = getRotateVector(shape2);
       List<Point2D> shape2Values;
      if(Math.signum(sign) != Math.signum(sign2))
      {
          shape2Values = new ArrayList<Point2D>();
          for (int i = shape2.perimetr.size()-1; i >= 0 ; i--) {
             shape2Values.add(shape2.perimetr.get(i)); 
          }
      } 
      else
          shape2Values =  shape2.perimetr;
      
       ListOfPoint2D  firstPoint =  getListAllPoint(shape1Values,shape2Values);
      
       Shape sp = new Shape(getPoints(firstPoint,sign));
        
       return sp;
      
    }

    ListOfPoint2DDAO lpdao = new        ListOfPoint2DDAO();
    class ListOfPoint2DDAO
    {
        List<ListOfPoint2D>  listp = new ArrayList<ListOfPoint2D>();
        public  ListOfPoint2D getLPForPoint(Point2D pp,int firstSecond)
        {
            for(ListOfPoint2D lp: listp)
            {
                if(lp.current.equals(pp))
                {   
                    lp.setFirstSecond(firstSecond);
                    return lp;
                }
            }
            ListOfPoint2D newp = new ListOfPoint2D(pp,firstSecond);
            listp.add(newp);
            return newp;
        }

    }


    private ListOfPoint2D getListAllPoint(List<Point2D> shape1Values, List<Point2D> shape2Values) {


        List<ListOfPoint2D> crosspoints = new ArrayList<ListOfPoint2D>();
        for (int i = 0; i < shape1Values.size(); i++) 
        {
            Point2D p1 = shape1Values.get(i);
            Point2D p2 = shape1Values.get(i+1 < shape1Values.size()?i+1:0); 
            for (int j = 0; j < shape2Values.size(); j++) {
                Point2D g1 = shape2Values.get(j);
                Point2D g2 = shape2Values.get(j+1 < shape2Values.size()?j+1:0); 
                if(Point2D.intersect(p1, p2, g1, g2))
                {
                    Point2D[] pointsin=     Point2D.getIntersectPoint(p1, p2, g1, g2);
                    if(pointsin.length == 1)
                    {
                    ListOfPoint2D  lp = lpdao.getLPForPoint(pointsin[0],3);

                        if(!p1.equals(lp.current))
                            lp.prevFirst = lpdao.getLPForPoint(p1,1);
                        else
                            lp.prevFirst = lpdao.getLPForPoint(shape1Values.get(i-1 >=0?i-1:shape1Values.size()-1),1);
                        if(!p2.equals(lp.current))
                            lp.nextFirst = lpdao.getLPForPoint(p2,1);
                        else
                            lp.nextFirst = lpdao.getLPForPoint(shape1Values.get(((i+2) % shape1Values.size()) ),1);


                        if(!g1.equals(lp.current))
                            lp.prevSecond = lpdao.getLPForPoint(g1,2);
                        else
                            lp.prevSecond = lpdao.getLPForPoint(shape2Values.get(j-1 >=0?j-1:shape2Values.size()-1),2);

                        if(!g2.equals(lp.current))
                            lp.nextSecond = lpdao.getLPForPoint(g2,2);
                        else
                            lp.nextSecond = lpdao.getLPForPoint(shape2Values.get(((j+2) % shape2Values.size())),2);


                    crosspoints.add(lp);
                    }
                    else
                    {
                        ListOfPoint2D  lp = lpdao.getLPForPoint(pointsin[0],3);

                        if(!p1.equals(lp.current))
                         lp.prevFirst = lpdao.getLPForPoint(p1,1);
                        else
                          lp.prevFirst = lpdao.getLPForPoint(shape1Values.get(i-1 >=0?i-1:shape1Values.size()-1),1);
                        if(!p2.equals(lp.current))
                            lp.nextFirst = lpdao.getLPForPoint(p2,1);
                        else
                            lp.nextFirst = lpdao.getLPForPoint(shape1Values.get(((i+2) % shape1Values.size())),1);


                        if(!g1.equals(lp.current))
                            lp.prevSecond = lpdao.getLPForPoint(g1,2);
                        else
                            lp.prevSecond = lpdao.getLPForPoint(shape2Values.get(j-1 >=0?j-1:shape2Values.size()-1),2);

                        if(!g2.equals(lp.current))
                             lp.nextSecond = lpdao.getLPForPoint(g2,2);
                        else
                            lp.nextSecond = lpdao.getLPForPoint(shape2Values.get(((j+2) % shape2Values.size())),2);



                        crosspoints.add(lp);

                        ListOfPoint2D lp2 = lpdao.getLPForPoint(pointsin[1],3);
                        if(!p1.equals(lp2.current))
                            lp2.prevFirst = lpdao.getLPForPoint(p1,1);
                        else
                            lp2.prevFirst = lpdao.getLPForPoint(shape1Values.get(i-1 >=0?i-1:shape1Values.size()-1),1);
                        if(!p2.equals(lp2.current))
                            lp2.nextFirst = lpdao.getLPForPoint(p2,1);
                        else
                            lp2.nextFirst = lpdao.getLPForPoint(shape1Values.get(((i+2) % shape1Values.size())),1);


                        if(!g1.equals(lp2.current))
                            lp2.prevSecond = lpdao.getLPForPoint(g1,2);
                        else
                            lp2.prevSecond = lpdao.getLPForPoint(shape2Values.get(j-1 >=0?j-1:shape2Values.size()-1),2);

                        if(!g2.equals(lp2.current))
                            lp2.nextSecond = lpdao.getLPForPoint(g2,2);
                        else
                            lp2.nextSecond = lpdao.getLPForPoint(shape2Values.get(((j+2) % shape2Values.size())),2);
                        crosspoints.add(lp2);
                    }

                }     
            }   
        }

         correctCorossPoint(crosspoints);
        
         ListOfPoint2D nextPoint = crosspoints.get(0).nextFirst;
         while((nextPoint.nextFirst == null)
                 || ((nextPoint.nextSecond != null) && (nextPoint.nextFirst != null))
                 )
           nextPoint = FillFirstPoint(nextPoint,shape1Values,crosspoints);
         
           nextPoint = crosspoints.get(0).nextSecond;
         while((nextPoint.nextSecond == null)
                         || ((nextPoint.nextSecond != null) && (nextPoint.nextFirst != null)))
           nextPoint = FillSecondPoint(nextPoint,shape2Values,crosspoints);
          
          return nextPoint; 
        }

    private void correctCorossPoint(List<ListOfPoint2D> crosspoints) {

        List<ListOfPoint2D> cleared = new ArrayList<ListOfPoint2D>();
        for (ListOfPoint2D listOfPoint2D : crosspoints)
        {
            if(!cleared.contains(listOfPoint2D))
                cleared.add(listOfPoint2D);
        }

        crosspoints.clear();
        crosspoints.addAll(cleared);

        for (ListOfPoint2D listOfPoint2D : crosspoints)
        {

            List<ListOfPoint2D> similarPointFirst = findSimmilarFirstByEnd(crosspoints, listOfPoint2D);
            if(similarPointFirst != null)
                connectSimilarFirstByEnd(similarPointFirst );

            similarPointFirst = findSimmilarFirstByStart(crosspoints, listOfPoint2D);
            if(similarPointFirst != null)
                connectSimilarFirstByStart(similarPointFirst );

            List<ListOfPoint2D> findSimmilarSecond = findSimmilarSecondByStart(crosspoints, listOfPoint2D);
            if(findSimmilarSecond != null)
                connectSimilarSecondByStart(findSimmilarSecond );

            findSimmilarSecond = findSimmilarSecondByEnd(crosspoints, listOfPoint2D);
            if(findSimmilarSecond != null)
                connectSimilarSecondByEnd(findSimmilarSecond );
        }


    }




    class ListOfPoint2DSorter  implements Comparator<ListOfPoint2D>
    {
        Point2D startP;
        boolean desc;
        public ListOfPoint2DSorter(Point2D startP,boolean desc)
        {
            this.startP = startP;
            this.desc = desc;
        }

        /* Этот метод выполняет сравнение имен двух файлов.
        * Возвращает:
        *     1 если первый параметр (о1) больше второго (о2),
        *    -1 если первый параметр (о1) меньше второго (о2),
        *     0 если они равны.
        * Имя первого файла считается больше второго имени, если
        * первый файл находится ближе к корню дерева папок.
        * Если файлы находятся в одной папке, то больше то имя,
        * которое идет первым по алфавиту.
        */
        @Override
        public int compare(ListOfPoint2D o1, ListOfPoint2D o2) {

            if(startP.distance(o1.current) < startP.distance(o2.current)   )
            {
                if(desc) return 1;
                else return -1;
            }
            else
            {
                if(desc)   return -1;
                else return 1;
            }
        }


    }

    private void connectSimilarFirstByEnd( List<ListOfPoint2D> similarPointFirst) {
        Point2D nextfisrtPoint = similarPointFirst.get(0).nextFirst.current;
        java.util.Collections.sort(similarPointFirst,new ListOfPoint2DSorter(nextfisrtPoint,true));
        for (int i = 0; (i+1) < similarPointFirst.size(); i++) {
            similarPointFirst.get(i).nextFirst = similarPointFirst.get(i+1);
            similarPointFirst.get(i+1).prevFirst = similarPointFirst.get(i);
        }
    }
    private void connectSimilarSecondByEnd( List<ListOfPoint2D> similarPointFirst) {
        Point2D nextfisrtPoint = similarPointFirst.get(0).nextSecond.current;
        java.util.Collections.sort(similarPointFirst,new ListOfPoint2DSorter(nextfisrtPoint,true));
        for (int i = 0; (i+1) < similarPointFirst.size(); i++) {
            similarPointFirst.get(i).nextSecond = similarPointFirst.get(i+1);
            similarPointFirst.get(i+1).prevSecond = similarPointFirst.get(i);
        }
    }

    private void connectSimilarFirstByStart( List<ListOfPoint2D> similarPointFirst) {
        Point2D nextfisrtPoint = similarPointFirst.get(0).prevFirst.current;
        java.util.Collections.sort(similarPointFirst,new ListOfPoint2DSorter(nextfisrtPoint,false));
        for (int i = 0; (i+1) < similarPointFirst.size(); i++) {
            similarPointFirst.get(i).nextFirst = similarPointFirst.get(i+1);
            similarPointFirst.get(i+1).prevFirst = similarPointFirst.get(i);
        }
    }
    private void connectSimilarSecondByStart( List<ListOfPoint2D> similarPointFirst) {
        Point2D nextfisrtPoint = similarPointFirst.get(0).prevSecond.current;
        java.util.Collections.sort(similarPointFirst,new ListOfPoint2DSorter(nextfisrtPoint,false));
        for (int i = 0; (i+1) < similarPointFirst.size(); i++) {
            similarPointFirst.get(i).nextSecond = similarPointFirst.get(i+1);
            similarPointFirst.get(i+1).prevSecond = similarPointFirst.get(i);
        }
    }
    private List<ListOfPoint2D> findSimmilarSecondByEnd(List<ListOfPoint2D> crosspoints, ListOfPoint2D listOfPoint2D) {
        List<ListOfPoint2D> answ = null;
        for (ListOfPoint2D lp : crosspoints)
        {
            if(listOfPoint2D != lp)
            {
                if(lp.nextSecond == listOfPoint2D.nextSecond)
                    if(lp.SecondVertex || (lp.prevSecond == listOfPoint2D.prevSecond))
                    {
                       if(answ == null)
                          answ = new ArrayList<ListOfPoint2D>();
                        answ.add(lp);
                          
                    }
            }
        }
        if(answ != null)answ.add(listOfPoint2D);
          return answ;
    }
    private List<ListOfPoint2D> findSimmilarFirstByEnd(List<ListOfPoint2D> crosspoints, ListOfPoint2D listOfPoint2D) {
        List<ListOfPoint2D> answ = null;
        for (ListOfPoint2D lp : crosspoints)
        {
            if(listOfPoint2D != lp)
            {
                if(lp.nextFirst == listOfPoint2D.nextFirst)
                    if(lp.FirstVertex || (lp.prevFirst == listOfPoint2D.prevFirst))
                    {
                        if(answ == null)
                            answ = new ArrayList<ListOfPoint2D>();
                        answ.add(lp);

                    }
            }
        }
        if(answ != null)answ.add(listOfPoint2D);
        return answ;
    }

    private List<ListOfPoint2D> findSimmilarSecondByStart(List<ListOfPoint2D> crosspoints, ListOfPoint2D listOfPoint2D) {
        List<ListOfPoint2D> answ = null;
        for (ListOfPoint2D lp : crosspoints)
        {
            if(listOfPoint2D != lp)
            {
                if(lp.prevSecond == listOfPoint2D.prevSecond)
                    if(lp.SecondVertex || (lp.nextSecond == listOfPoint2D.nextSecond))
                    {
                        if(answ == null)
                            answ = new ArrayList<ListOfPoint2D>();
                        answ.add(lp);

                    }
            }
        }
        if(answ != null)answ.add(listOfPoint2D);
        return answ;
    }
    private List<ListOfPoint2D> findSimmilarFirstByStart(List<ListOfPoint2D> crosspoints, ListOfPoint2D listOfPoint2D) {
        List<ListOfPoint2D> answ = null;
        for (ListOfPoint2D lp : crosspoints)
        {
            if(listOfPoint2D != lp)
            {
                if(lp.prevFirst == listOfPoint2D.prevFirst)
                    if(lp.FirstVertex || (lp.nextFirst == listOfPoint2D.nextFirst))
                    {
                        if(answ == null)
                            answ = new ArrayList<ListOfPoint2D>();
                        answ.add(lp);

                    }
            }
        }
        if(answ != null)answ.add(listOfPoint2D);
        return answ;
    }

    private ListOfPoint2D getCrossPrev(List<ListOfPoint2D> crosspoint,Point2D p)
    {
        for (ListOfPoint2D listOfPoint2D : crosspoint)
        {
            if(listOfPoint2D.prevFirst.current == p) return listOfPoint2D;
            if(listOfPoint2D.prevSecond.current == p) return listOfPoint2D;
        }
        return null;
    }
    private ListOfPoint2D getCrossNext(List<ListOfPoint2D> crosspoint,Point2D p)
    {
        for (ListOfPoint2D listOfPoint2D : crosspoint) 
        {
            if(listOfPoint2D.nextFirst.current == p) return listOfPoint2D;
            if(listOfPoint2D.nextSecond.current == p) return listOfPoint2D; 
        }
        return null;
    }
 
    private ListOfPoint2D FillSecondPoint(ListOfPoint2D nextPoint, List<Point2D> shapeValues, List<ListOfPoint2D> crosspoint) {
         if(nextPoint.nextSecond != null )
            return nextPoint.nextSecond; 
        
        for (int i = 0; i < shapeValues.size(); i++) {
            if(shapeValues.get(i) == nextPoint.current) {
                nextPoint.nextSecond = getCrossPrev(crosspoint,nextPoint.current);
                if(nextPoint.nextSecond != null )
                   break;
                nextPoint.nextSecond = lpdao.getLPForPoint(shapeValues.get((i+1) % shapeValues.size()),2);
                nextPoint.nextSecond.prevSecond = nextPoint;
               break;
            }
        }
        return nextPoint.nextSecond;
    }
    private ListOfPoint2D FillFirstPoint(ListOfPoint2D currentLPPoint, List<Point2D> shapeValues, List<ListOfPoint2D> crosspoint) {
         if(currentLPPoint.nextFirst != null )
            return currentLPPoint.nextFirst;
        
        for (int i = 0; i < shapeValues.size(); i++) {
            if(shapeValues.get(i) == currentLPPoint.current) {
                currentLPPoint.nextFirst = getCrossPrev(crosspoint,currentLPPoint.current);
                if(currentLPPoint.nextFirst != null )
                   break;
                currentLPPoint.nextFirst = lpdao.getLPForPoint(shapeValues.get((i+1) % shapeValues.size()),1);
                currentLPPoint.nextFirst.prevFirst = currentLPPoint;
               break;
            }
        }
        return currentLPPoint.nextFirst;
    }

    private List<Point2D> getPoints(ListOfPoint2D firstPoint,double napravlenieObhoda) {
         List<Point2D> points = new ArrayList<Point2D>();


        while(!firstPoint.isCrossPoint())
            firstPoint = firstPoint.nextFirst != null ? firstPoint.nextFirst : firstPoint.nextSecond;

        ListOfPoint2D iterPoint = firstPoint;
        boolean first = true;
        double signNaprCommon =  Math.signum(napravlenieObhoda);
        if(signNaprCommon ==
                iterPoint.getSignOfCross() )
            first = true;
        else
            first = false;
         do
         {
            points.add(iterPoint.current);

            if(first)
             iterPoint = iterPoint.nextFirst;
            else
             iterPoint = iterPoint.nextSecond;
             
             
             
             if(iterPoint.isCrossPoint())
             {
                 if((iterPoint.FirstVertex == false) && (iterPoint.SecondVertex == false))
                    first = !first;
                 else
                 { if(signNaprCommon ==
                      iterPoint.getSignOfCross() )
                     first = true;
                 else
                     first = false;
                 }
             }

         }
         while(firstPoint != iterPoint);

        clearUnnesessaryPoint(points);
        rightOrder(points, napravlenieObhoda);
         return points;
    }

    private void rightOrder(List<Point2D> points, double napravlenieObhoda) {
        if(Math.signum(napravlenieObhoda) ==
                Math.signum( Point2D.rotate(points.get(0), points.get(1),
                        points.get(2))))
            return;
        Collections.reverse(points);
    }

    private void clearUnnesessaryPoint(List<Point2D> points) {
        List<Point2D> notneed = new ArrayList<Point2D>();
        for (int i = 0; i < points.size(); i++) {
            Point2D p1 = points.get(i);
            Point2D p2 = points.get((i+1) % points.size());
            Point2D p3 = points.get((i+2) % points.size());
            if(Point2D.onOneLine(p1,p2,p3))
                notneed.add(p2);
        }
        for(Point2D pp:notneed)
            points.remove(pp);
    }

    public class ListOfPoint2D
    {
        public boolean FirstVertex;
        public boolean SecondVertex;
        public ListOfPoint2D nextFirst;
        public ListOfPoint2D nextSecond;
        public ListOfPoint2D prevFirst;
        public ListOfPoint2D prevSecond;
        public Point2D current; 

        public void setFirstSecond(int firstSecond)
        {        if(firstSecond == 1)
            FirstVertex = true;
            if(firstSecond == 2)
                SecondVertex = true;

        }
        public ListOfPoint2D(Point2D p2,int firstSecond) {
            current = p2;
            setFirstSecond(firstSecond);
        }

        private boolean isCrossPoint() {
            if(nextFirst != null)
                if(nextSecond != null) 
                   return true;
              return false;      
        }


        public double getSignOfCross() {
            double sign =  Math.signum( Point2D.rotate( current,  nextFirst.current,
                    nextSecond.current));

            if(sign < 0.001)
            {
                sign =  Math.signum( Point2D.rotate( current,  prevFirst.current,
                        prevSecond.current));

            }
            return sign;
        }
    }
    
    double getRotateVector(Shape shape )
    { 
        int x1 = 0; 
        int x2 = 1;
       int n = shape.perimetr.size();
        double sign = 0;
        while((x2 != 0) && (sign == 0))
        { 
            sign = testOneSign(x1,x2,shape) ;
          x1++;
          x2++;
          if(x2 == (n-1)) x2 = 0;
          if(x1 == (n-1)) break;
        }
        
        return sign;
    }
 
    private double testOneSign(int x1, int x2, Shape shape) {
        int n  = shape.perimetr.size();
        double sign = 0,old = 0;
        for (int i = 0; i < n; i++) {
           if(x1 != i && x2 != i)
                sign = Point2D.rotate(shape.perimetr.get(x1), shape.perimetr.get(x2) ,shape.perimetr.get(i) );
           if((old != 0) && (Math.signum(old) != Math.signum(sign)) ) 
               return 0;
           old = sign;    
        }
        return sign;  
    }
    
    
    
    
}
