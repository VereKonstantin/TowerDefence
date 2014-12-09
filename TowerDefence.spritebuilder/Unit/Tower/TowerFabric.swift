//
//  TowerFabric.swift
//  TowerDefence
//
//  Created by koveres on 08.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation


/**
*
* @author vereshchagin
*/
public class TowerFabric : StackObjectAbstractFabric{
    
    
    public  override init(intlist:[CInt] )
    {
       
        super.init(intlist: intlist);
        
    }
    
    public func getTower(  idTower:TowerType,  position1:CGPoint, solderFinder1:TargetFinder)   -> (Tower)
    {
        var newobj :Tower ;
        position = position1;
        solderFinder = solderFinder1;
        newobj =    getObject( idTower.rawValue ) as Tower;
        return newobj;
    
    }
    
    var position:CGPoint?;
    var solderFinder:TargetFinder?;
 
    override func getNewObject( idObject:CInt, grObj:AnyObject) ->(StackObject) {
        var idTower:TowerType? = TowerType(rawValue: idObject);
        if(idTower != nil){
        switch idTower!
        {
        case TowerType.Tower1:
            return   Tower1(position: position!,solderFinder: solderFinder!,grObj: grObj);
        case TowerType.Tower2:
            return   Tower2(position: position!,solderFinder: solderFinder!,grObj: grObj);
        case TowerType.Tower3:
            return   Tower3(position: position!,solderFinder: solderFinder!,grObj: grObj);
            }
        }
        return Tower1(position: position!,solderFinder: solderFinder!,grObj: grObj);
    }
}
