/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import internalObject.SolderInternal.SolderType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Константин
 */
 public final class  UnitGroup  { 
    

     
     public List<Detachment> deatachments = new ArrayList<Detachment>();  

     int currDet = 0;
     int[] countses_;
     int[] getCountses()
     {
         if(countses_ == null) initCountses();
         return countses_;
     }
     void initCountses()
     {
         countses_ = new  int[deatachments.size()];
        
         int i = 0;
         for (Detachment detachment : deatachments) 
         {
            countses_[i] =  detachment.getCount() ;
            i++;
         } 
     }
           
     
     private int getCurrentCount() {
        
       return getCountses()[currDet];
    }
     
    private void minusCurrentCount() {
       getCountses()[currDet]--;
    }
     public SolderType getNextUnit()
     {
         Detachment det =  getCurrDet();   
         nextCurrent(); 
         return det.type; 
     }
        
        
    public boolean isEnd() {
        for (int i = 0; i < deatachments.size(); i++) {
            if(getCountses()[i] != 0) 
                return false;
        }
          return true;
    }

    private Detachment getCurrDet() { 
        return deatachments.get(currDet);
    } 
    
 

    private void nextCurrent() {
        
        minusCurrentCount();
        currDet ++;
        if(currDet == this.deatachments.size())currDet  = 0; 
        if(getCurrentCount() == 0) 
            if(!isEnd())
                nextCurrent();
            
      
    }

   

 
}
