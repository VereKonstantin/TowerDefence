//
//  LineABS.swift
//  TowerDefenece
//
//  Created by koveres on 01.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation


public class LineABC
{
    var a:CGFloat;
    var b:CGFloat;
    var c:CGFloat;
    public init( p1:CGPoint,p2:CGPoint)
    {
        a = p1.y - p2.y;
        b = p2.x - p1.x;
        c = p1.x*p2.y - p2.x*p1.y;
    }
    
}
