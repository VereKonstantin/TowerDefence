/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import internalObject.SolderInternal;

/**
 *
 * @author Константин
 */
 public final  class  Detachment 
    {  
     public final int BIG_COUNT = 30;
     public final int MEDIUM_COUNT = 20;
     public final int SMALL_COUNT = 10;   
     public int getCount()
     {
             if(size == SizeGroupe.small) return SMALL_COUNT;
             if(size == SizeGroupe.medium) return MEDIUM_COUNT;
             if(size == SizeGroupe.big) return BIG_COUNT; 
             return 0;
     }
        public enum SizeGroupe{small,medium,big}  
        public  SizeGroupe size; 
        public SolderInternal.SolderType type;
       
       
 }