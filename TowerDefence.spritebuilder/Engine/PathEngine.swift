//
//  PathEngine.swift
//  TowerDefence
//
//  Created by koveres on 07.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation


public class PathEngine {
    
    public func  getStartPoint() -> (CGPoint){
        return  path[0];
    }
    
    func  getEnd(  x:CGPoint) -> (CGPoint?)
    {
        
        for var i = 0; i < 3; ++i {
 
            var p1:CGPoint = path[i];
    var p2:CGPoint = path[i+1];
    if((p1.x <= x.x) &&  (p2.x >=  x.x))
    {
    if((p1.y <= x.y) &&  (p2.y >=  x.y))
    {
        if(p2.x == x.x){
            if(p2.y == x.y){
                continue;}}
    return p2;
    }
    }
    }
    return nil;
    }
    
    func   getVec(  start:CGPoint,   end:CGPoint, dist:CDouble) -> (CGPoint) {
        var dx:CGFloat=(end.x-start.x);
    var dy:CGFloat = (end.y-start.y);
        
    var dxy:CGFloat =    sqrt(dx*dx + dy*dy);
    var x1:CGFloat = CGFloat(dist) * CGFloat(dx/dxy);
    var x2:CGFloat = CGFloat(dist) * CGFloat(dy/dxy);
        return CGPoint(x:x1,y:x2);
    }
    
    public func  getNextDistance(   pointStart:CGPoint,   distanse:CDouble) -> (CGPoint?)
    {
    if(ccpDistance(path[path.count-1],pointStart) <= 0.0001)
    { return nil;}
    
    var d1:(CGPoint?)=getEnd(pointStart );
    
    
        if(d1 == nil){
            return nil;}
    
    
        var  vector:CGPoint = getVec(pointStart,end: d1!,dist: distanse);
    
        var dist:CGFloat = ccpDistance(d1!,pointStart);
    if( dist <  sqrt(vector.x*vector.x + vector.y*vector.y))
    {return d1;}
    
        return   CGPoint(x:
    (pointStart.x + vector.x),
            y:  (pointStart.y + vector.y)
    );
    }
    
    public   init( path1 :[CGPoint]) {
       path = path1;
    }
    var path:[CGPoint];
    
    public func calcNextDistanceForShell(  currentPlace:CGPoint,   targetPoint:CGPoint,   speedShell:CDouble) -> (CGPoint){
        var  vector:CGPoint = getVec(currentPlace,end: targetPoint,dist: speedShell);
    return   CGPoint (
        x: (currentPlace.x + vector.x),
        y:(  currentPlace.y + vector.y)
    );
    }
    
    
    
}
