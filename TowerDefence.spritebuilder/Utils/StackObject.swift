//
//  StackObject.swift
//  TowerDefence
//
//  Created by koveres on 02.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation


public class StackObjectAlreadyEngaged:  NSException{}


public  class  StackObject: ObjectWithID{

    
    init(data :AnyObject){ userData = data;}
    
    var ID_Object:CInt = 0;
    var userData:AnyObject;
    
    var isEngaged = false;
    
    
    public  func getUserData() -> (AnyObject) {
        return self.userData;
    }
    
    public  func setUserData(data :AnyObject) {
         userData = data;
    }

    public func getIdObject() -> (CInt)
    {
        return ID_Object;
    }
    

    
    private struct lastId { static var id: CInt = 0 }
    
    private func nextID() -> (CInt){
        
        lastId.id++;
        if(lastId.id == CInt.max){
            lastId.id = 0;}
        return lastId.id;
    }
    
    
    
   public func UseStackObject()
   {
    if(isEngaged){
        StackObjectAlreadyEngaged().raise();
    }
    isEngaged = true;
        ID_Object = nextID();
    }
    
    
    
    public func isFree() -> (Bool)
    {
        return !isEngaged;
    }
    
    public func clearObject(){}
    
    public func FreeStackObject()
    {
        clearObject();
        isEngaged = false;
    }
    
}
