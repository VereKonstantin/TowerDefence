/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;


import Utils.Point2D;
import internalObject.SolderInternal.SolderType;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author ver
 */
public class MapDAOmemoryImpl extends MapDAO {

    @Override
    public LevelDTO getMap(int ID_LEVEL) {
        
       List<Point2D > pathPoint= new ArrayList<Point2D  >();
       
        pathPoint.add(new Point2D  (0,0));
        pathPoint.add(new Point2D  (40,40));
        pathPoint.add(new Point2D  (40,50));
        pathPoint.add(new Point2D  (100,100));
      
      
        
         List<Point2D  > places= new ArrayList<Point2D  >();
          places.add(new Point2D (18,50));
        places.add(new Point2D (20,50));
        places.add(new Point2D (20,60));
        places.add(new Point2D (20,70)); 
       places.add(new Point2D (30,50));
       
       return new LevelDTO(pathPoint,places,getSolders(1));
    }

    public LevelDTO getMapWithMassivLoad( ) {
           List<Point2D > pathPoint= new ArrayList<Point2D  >();
       
        pathPoint.add(new Point2D  (0,0));
        pathPoint.add(new Point2D  (40,40));
        pathPoint.add(new Point2D  (40,50));
        pathPoint.add(new Point2D  (1000,1000));
        pathPoint.add(new Point2D  (2000,2000));
 

     
        
        
        
        List<Point2D  > places= new ArrayList<Point2D  >();
        places.add(new Point2D (18,50));
        places.add(new Point2D (20,50));
        places.add(new Point2D (20,60));
        places.add(new Point2D (20,70)); 
        places.add(new Point2D (30,50));
        places.add(new Point2D (90,90));
        places.add(new Point2D (900,1000));
        places.add(new Point2D (1000,1100));
        places.add(new Point2D (1100,1200));
       
       return new LevelDTO(pathPoint,places,getSolders(1000));
    }

    private List<UnitGroup> getSolders(int i) {
       List<UnitGroup> lst = new ArrayList<UnitGroup> ();
       
        for (int j = 0; j < i; j++) { 
             if(j>1)   
                 lst.add(getUnitGroupe2());
             else
                 lst.add(getUnitGroupe()); 
        }
       
       
       return lst; 
    }
    
    
    private UnitGroup getUnitGroupe2( ) {
        UnitGroup un = new UnitGroup();
        un.deatachments.add(DetachmentFabric.getDetachment(Detachment.SizeGroupe.big, SolderType.Solder1));
        un.deatachments.add(DetachmentFabric.getDetachment(Detachment.SizeGroupe.big, SolderType.Solder2));
        un.deatachments.add(DetachmentFabric.getDetachment(Detachment.SizeGroupe.big, SolderType.Solder3));
        un.deatachments.add(DetachmentFabric.getDetachment(Detachment.SizeGroupe.big, SolderType.Solder1));
        un.deatachments.add(DetachmentFabric.getDetachment(Detachment.SizeGroupe.big, SolderType.Solder2));
        un.deatachments.add(DetachmentFabric.getDetachment(Detachment.SizeGroupe.big, SolderType.Solder3));
        return un;
    }
    private UnitGroup getUnitGroupe( ) {
        UnitGroup un = new UnitGroup();
        un.deatachments.add(DetachmentFabric.getDetachment(Detachment.SizeGroupe.big, SolderType.Solder1));
        un.deatachments.add(DetachmentFabric.getDetachment(Detachment.SizeGroupe.big, SolderType.Solder1));
        un.deatachments.add(DetachmentFabric.getDetachment(Detachment.SizeGroupe.big, SolderType.Solder1));
        return un;
    }
    
    
}
