//
//  StackObjectAbstractFabric.swift
//  TowerDefence
//
//  Created by koveres on 02.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation


public  class StackObjectAbstractFabric  {
   
    var objects:[CInt:[StackObject]];
    
    
  public  init(intlist:[CInt] )
    { 
        objects = [CInt:[StackObject]]();
        initializeStartObject(intlist);
    }
    
    
    public func StackObjectAbstractFabric( types : [CInt])
    {
        objects = [CInt:[StackObject]]();
        initializeStartObject(types);
    }
    
    
    func initializeStartObject(types:[CInt])
    {
        
        for currentID in types {
            var currentList:[StackObject] = [StackObject]();
           
        }
        
    }
        
        
    
    
    func  getObject( idObject: CInt) -> (StackObject)
    {
        var obj:StackObject? =  findFreeObject(objects[idObject]);
        if(obj == nil)
        {
            obj = getNewObject(idObject,grObj: EngineOptions.instance_.value!.graphObjCreator.getGrObjectUnit(idObject)());
        }

        obj!.UseStackObject();
        
        return obj!;
    
    }
    
        
    func     getNewObject( idTypeObject: CInt, grObj:AnyObject) -> (StackObject){fatalError("Calling abstract function.");}
    
    
        
        
    func  findFreeObject( objectsType :[StackObject]?) -> (StackObject?)
    {
        
        if(objectsType == nil) {return nil;}
        
        
        for object in objectsType!
        {
            if( object.isFree()){
                return object;
            }
        
        }
        
        return nil;
    }
    
    
}

