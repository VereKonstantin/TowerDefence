//
//  ShellInternal.swift
//  TowerDefence
//
//  Created by koveres on 02.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation


public protocol ShellInternal:ObjectWithID {
    func  getTarget() -> (CGPoint?);
    func  getCurrent() -> (CGPoint?);
    func getIsBlowedOut()-> (Bool);
}