/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import internalObject.ObjectWithID;

/**
 *
 * @author Константин
 */

public abstract class  StackObject implements ObjectWithID{
    private int ID_Object;
    static int lastId = 0;
    
    Object userData;
    @Override
    public Object getUserData() {
        return this.userData;
    }

    @Override
    public void setUserData(Object data) {
          this.userData = data;
    }
    @Override
    public int getIdObject()
    {
        return this.ID_Object;
    }
    private boolean isEngaged = false;

    private int nextID() {
        lastId++;
        if(lastId == Integer.MAX_VALUE) 
            lastId = 0;
        return lastId;
    }

    public class StackObjectAlreadyEngaged extends  Exception{};
   
    
    public void UseStackObject() throws StackObjectAlreadyEngaged {
        if(isEngaged)
            throw new StackObjectAlreadyEngaged();
        isEngaged = true;
      
        this.ID_Object = nextID();
    }

    public boolean isFree()
    {
        return !isEngaged;
    }

    public abstract void  clearObject();

    public void FreeStackObject()
    {
        clearObject();
        isEngaged = false;
    }

}
