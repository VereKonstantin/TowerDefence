/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Solder;

import Unit.Solder.Solder;
import Utils.Point2D;
import Utils.StackObject;
import Utils.StackObjectAbstractFabric;
import internalObject.SolderInternal.SolderType;
 
 
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ver
 */
public class SolderFabric extends StackObjectAbstractFabric {
   
    public SolderFabric(List<Integer> intlist)
    {
        super(intlist);
        
    }
    public Solder getUnit(SolderType type,Point2D  startPoint) throws Exception
    {
        Solder newobj;
        this.startPoint = startPoint;
        newobj = (Solder) this.getObject( type.ordinal() );
       newobj.init(startPoint);
        return newobj;
      
    }

    
    Point2D  startPoint;
    @Override
    protected StackObject getNewObject(Integer idObject) {
        SolderType type;
       type = SolderType.values()[idObject];
        switch(type)
        {
            case Solder1:
                return new Solder1(startPoint);
            case Solder2:
                return new Solder2(startPoint);
            case Solder3:
                return new Solder3(startPoint);
        }
        return new Solder1(startPoint);
    }
}
