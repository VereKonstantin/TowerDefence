//
//  SolderFabric.swift
//  TowerDefence
//
//  Created by koveres on 07.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation



public class SolderFabric: StackObjectAbstractFabric {
    
    public override  init(intlist:[CInt] )
    {
        super.init(intlist: intlist);
        
    }
    
    public func getUnit( type:SolderType,   startPoint1:CGPoint) -> (Solder)
    {
        var newobj:Solder;
        startPoint = startPoint1;
        newobj =    getObject(type.rawValue) as Solder ;
        return newobj; 
    }
    
    
    var  startPoint:CGPoint?;
   
    override func  getNewObject(  idTypeObject:CInt, grObj:AnyObject) -> (StackObject) {
        var type:SolderType?;
        type = SolderType(rawValue: idTypeObject);
        if(type != nil)
        {
            switch type!
            {
        case SolderType.Solder1:
            return   Solder1(p1: startPoint!,gobj: grObj);
        case SolderType.Solder2:
            return   Solder2(p1: startPoint!,gobj: grObj);
        case SolderType.Solder3:
            return   Solder3(p1: startPoint!,gobj: grObj);
            }
        }
        return   Solder1(p1: startPoint!,gobj: grObj);
    }
}
