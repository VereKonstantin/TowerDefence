/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Tower;

import Utils.Point2D;
import Utils.StackObject;
import Utils.StackObject.StackObjectAlreadyEngaged;
import Utils.StackObjectAbstractFabric;
import internalObject.TowerInternal;
import internalObject.TowerInternal.TowerType;
import java.util.List;
 

/**
 *
 * @author vereshchagin
 */
public class TowerFabric extends StackObjectAbstractFabric{

    public TowerFabric(List<Integer> intlist)
    {
        super(intlist); 
    }
    
    public Tower getTower(TowerInternal.TowerType idTower,Point2D position,TargetFinder solderFinder) throws StackObjectAlreadyEngaged 
    {
        Tower newobj;
        this.position = position;
        this.solderFinder = solderFinder;
        newobj = (Tower) this.getObject( idTower.ordinal() );
        newobj.init(position,solderFinder);
        return newobj;
        
         
    }
        Point2D position;
        TargetFinder solderFinder;
    @Override
    protected StackObject getNewObject(Integer idObject) {
         TowerType idTower;
       idTower = TowerType.values()[idObject]; 
       switch(idTower)
        {
            case Tower1:
                return new Tower1(position,solderFinder);
            case Tower2:
                return new Tower2(position,solderFinder);
            case Tower3:
                return new Tower3(position,solderFinder);
        }
        return new Tower1(position,solderFinder);
    }
}
