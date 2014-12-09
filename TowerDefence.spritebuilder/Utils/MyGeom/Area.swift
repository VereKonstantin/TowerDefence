//
//  Area.swift
//  TowerDefence
//
//  Created by koveres on 08.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation



public class Area {
    public init( ) {}
    
    var shapes:[CGRect] = [CGRect]();
    
    
    public init(p:CGRect) {
        shapes.append(p);
    }
    
    
    public func contains( rect:CGRect) -> (Bool) {
        for shape in shapes  {
        if(shape.contains(rect))
        {
            return true;
        }
    }
    return false;
    }
    
    public func add(shp:CGRect) {
        shapes.append(shp);
    }
    
    public func add(  area:Area) {
        for shape in area.shapes
        {
            shapes.append(shape);
        }
    }
    
    public func intersects(  rect:CGRect) -> (Bool)
    {
    
        for  shape in shapes
        {
        if(shape.intersects( rect))
        {
            return true;
        }
        }
    return false;
    
    }
    
    
    
    
    
    
    
}
