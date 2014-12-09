/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import internalObject.SolderInternal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Константин
 */
public final class DetachmentFabric {
     
       public static List<Detachment> detachments = new ArrayList<Detachment>(); 
       public static  Detachment  create(SolderInternal.SolderType type, Detachment.SizeGroupe size)
        {
           Detachment newd = new Detachment();
           newd = new Detachment();
           newd.size = size;
           newd.type = type; 
           return newd;  
        }
       public static Detachment getDetachment(Detachment.SizeGroupe size,SolderInternal.SolderType type)
       {
           for (Detachment det : detachments) {
               if(det.type == type)
                   if(det.size ==  size)
                    return det;
           }
           
           Detachment newd =  create(type, size);
           detachments.add(newd); 
           return newd;  
       } 
    }
 
