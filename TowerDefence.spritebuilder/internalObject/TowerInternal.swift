//
//  TowerInternal.swift
//  TowerDefence
//
//  Created by koveres on 02.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation

public enum TowerType : CInt{case Tower1 = 21 ,Tower2 = 22,Tower3 = 23

static let allValues = [Tower1, Tower2, Tower3]

}
public protocol TowerInternal : ObjectWithID {
    
    func  getBulidProgress() -> (CDouble);
    func  getIsBuild() -> (Bool);
    func  getType() -> (TowerType);
    func    getPosition() -> (CGPoint);
    func  getAttackPoint() -> (CGPoint?);
    
}
