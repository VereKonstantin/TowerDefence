//
//  MapDAOmemoryImpl.swift
//  TowerDefence
//
//  Created by koveres on 06.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation


public class MapDAOmemoryImpl:MapDAO {
    
    
    public func getMap( ID_LEVEL:CInt) -> (LevelDTO) {
        
        var pathPoint:[CGPoint] = [CGPoint]();
        
        
        pathPoint.append(CGPoint(x:0,y:0));
        pathPoint.append(CGPoint(x:40,y:40));
        pathPoint.append(CGPoint(x:40,y:50));
        pathPoint.append(CGPoint(x:100,y:100));
        
        
        
        var places:[CGPoint] = [CGPoint]();
        places.append(CGPoint(x:18,y:50));
        places.append(CGPoint(x:20,y:50));
        places.append(CGPoint(x:20,y:60));
        places.append(CGPoint(x:20,y:70));
        places.append(CGPoint(x:30,y:50));
  
        return  LevelDTO(path1:pathPoint,placesToSetTower1:places,solders1:getSolders(1));
    }
    
    public func getMapWithMassivLoad( ) -> (LevelDTO) {
        var pathPoint:[CGPoint] = [CGPoint]();
        
        pathPoint.append(CGPoint(x:0,y:0));
        pathPoint.append(CGPoint(x:40,y:40));
        pathPoint.append(CGPoint(x:40,y:50));
        pathPoint.append(CGPoint(x:1000,y:1000));
        pathPoint.append(CGPoint(x:2000,y:2000));
        
        
        var places:[CGPoint] = [CGPoint]();
        places.append(CGPoint(x:18,y:50));
        places.append(CGPoint(x:20,y:50));
        places.append(CGPoint(x:20,y:60));
        places.append(CGPoint(x:20,y:70));
        places.append(CGPoint(x:30,y:50));
        places.append(CGPoint(x:90,y:90));
        places.append(CGPoint(x:900,y:1000));
        places.append(CGPoint(x:1000,y:1100));
        places.append(CGPoint(x:1100,y:1200));
        
        return  LevelDTO(path1:pathPoint,placesToSetTower1:places,solders1:getSolders(1000));
 
    }
    
    private func getSolders( i:Int) -> ([UnitGroup]) {
        var lst = [UnitGroup]();
        
        for var j = 0; j < i; ++j {
            if(j>1)
            {
                lst.append(getUnitGroupe2());
            }
            else
            {
                lst.append(getUnitGroupe());
            }
        }
        
       
        return lst;
    }
    
    
    private func getUnitGroupe2( ) -> (UnitGroup) {
      
        var deatachments:[Detachment] = [Detachment]();
        
        
        deatachments.append(DetachmentFabric.getDetachment(SizeGroupe.big, type_:SolderType.Solder1));
        deatachments.append(DetachmentFabric.getDetachment(SizeGroupe.big, type_:SolderType.Solder2));
        deatachments.append(DetachmentFabric.getDetachment(SizeGroupe.big, type_:SolderType.Solder3));
        deatachments.append(DetachmentFabric.getDetachment(SizeGroupe.big, type_:SolderType.Solder1));
        deatachments.append(DetachmentFabric.getDetachment(SizeGroupe.big, type_:SolderType.Solder2));
        deatachments.append(DetachmentFabric.getDetachment(SizeGroupe.big, type_:SolderType.Solder3));
    
        var un:UnitGroup =   UnitGroup(dtchs:deatachments);
        return un;
    }
    
    private func getUnitGroupe( ) -> (UnitGroup) {
       
        var deatachments:[Detachment] = [Detachment]();
        
        deatachments.append(DetachmentFabric.getDetachment(SizeGroupe.big, type_:SolderType.Solder1));
        deatachments.append(DetachmentFabric.getDetachment(SizeGroupe.big, type_:SolderType.Solder1));
        deatachments.append(DetachmentFabric.getDetachment(SizeGroupe.big, type_:SolderType.Solder1));
        
        
        var un:UnitGroup =   UnitGroup(dtchs:deatachments);
        return un;
    }
    
    
}
