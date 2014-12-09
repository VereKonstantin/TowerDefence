/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author Константин
 */
import Utils.StackObject.StackObjectAlreadyEngaged;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: K.Vereschagin
 * Date: 25.10.12
 * Time: 16:04
 * To change this template use File | Settings | File Templates.
 */
public abstract class StackObjectAbstractFabric  {
      public static int countOfStartObject = 200;
    Dictionary<Integer,ArrayList<StackObject>> objects;
         
         public StackObjectAbstractFabric( )
    {
            List<Integer> types=  new ArrayList<Integer>();
            types.add(0);   
            objects = new Hashtable<Integer, ArrayList<StackObject>> ();
           initializeStartObject(types);
    }
    public StackObjectAbstractFabric(List<Integer> types)
    {
        objects = new Hashtable<Integer, ArrayList<StackObject>> ();
        initializeStartObject(types);
    }

    protected void initializeStartObject(List<Integer> types)
    {
         for (int i = 0; i< types.size(); i++)
         {
             int currentID = types.get(i);
             ArrayList<StackObject> currentList =   new ArrayList<StackObject>();
             for(int j =0;j<countOfStartObject;j++)
                 currentList.add(getNewObject(currentID));
             objects.put(currentID,   currentList   );
         }
    }

    protected StackObject getObject(Integer idObject) throws StackObjectAlreadyEngaged
    {
        StackObject obj =  findFreeObject(objects.get(idObject));
        if(obj == null)
            obj = getNewObject(idObject);
        obj.UseStackObject();
        return obj;
        
    }

    protected  abstract StackObject getNewObject(Integer idObject);


    StackObject  findFreeObject(ArrayList<StackObject> objectsType)
    {
        for(int i = 0; i < objectsType.size();i++)
        {
           if( objectsType.get(i).isFree())
               return objectsType.get(i);
        
        }

        return null;
    }


}

