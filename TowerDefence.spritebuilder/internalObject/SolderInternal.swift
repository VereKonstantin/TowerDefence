//
//  SolderInternal.swift
//  TowerDefence
//
//  Created by koveres on 02.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation


public enum SolderType: CInt{ case Solder1 = 11,Solder2 = 12,Solder3 = 13

public static let allValues = [Solder1, Solder2, Solder3]
}

public protocol SolderInternal : ObjectWithID {
    func  getType() -> (SolderType);
    func  getCurrentState() -> (CGPoint?);
    func  getHealth() -> (CDouble);
    func getIsDead() -> (Bool);
}