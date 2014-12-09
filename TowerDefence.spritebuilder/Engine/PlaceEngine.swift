//
//  PlaceEngine.swift
//  TowerDefence
//
//  Created by koveres on 08.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation



public class PlaceEngine {
    
    
    // List<Double> placesToSetTower;
    var areaToSetTower:Area;
    var areaSetEdTower:Area;
    
    
    
    public init( placesToSetTower:[CGPoint]) {
        areaToSetTower =    Area();
        areaSetEdTower =    Area();
        calcArea(placesToSetTower);
    }
    
    
    func canAddTower( newTower:Tower) -> (Bool) {
    if(!testOnEmptyPlace(newTower))
    {return  false;}
    if(!testOnNearTower(newTower))
    {return false;}
    return true;
    }
    
       func testOnEmptyPlace( newTower:Tower) -> (Bool) {
    
        var rect:CGRect = newTower.getNeedPlace();
        return areaToSetTower.contains(rect);
    
    }
    
    private func calcArea(  placesToSetTower:[CGPoint]) {
        var sizePlacePoint:CDouble = StaticSettings.sizePlacePoint;
    
    
    for    double1  in placesToSetTower  {
        var xx:Double = Double(Double(double1.x) - Double(sizePlacePoint/2.0));
        var xy:Double = Double(Double(double1.y) - Double(sizePlacePoint/2.0));
        
        var rect:CGRect = CGRect(x: xx, y: xy, width: Double(sizePlacePoint), height: Double(sizePlacePoint));
    //    CGRect( Double(double1.x - sizePlacePoint/2),  Double(double1.y - sizePlacePoint/2), 
        //Double(sizePlacePoint), Double(sizePlacePoint));
        var newarea:Area =    Area(p: rect);
    areaToSetTower.add(newarea);
    }
    }
    
    func addTower( newTower:Tower) { 
        var newarea:Area =    Area(
            p: newTower.getNeedPlace());
        areaSetEdTower.add(newarea);
    }
    
    private func testOnNearTower( newTower: Tower) -> (Bool) {
        var rect:CGRect = newTower.getNeedPlace();
        return !areaSetEdTower.intersects(rect);
    }
    
    
    
    
    
    
    
}
