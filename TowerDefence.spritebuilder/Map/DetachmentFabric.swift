//
//  DetachmentFabric.swift
//  TowerDefence
//
//  Created by koveres on 06.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation


public struct  DetachmentFabric {
    
    public static var detachments:[Detachment] = [Detachment]();
    public static  func  create( type__: SolderType, size: SizeGroupe ) -> (Detachment)
    {
        var newd:Detachment =  Detachment();
        newd.size = size;
        newd.type = type__;
        return newd;
    }
    public static func getDetachment( size_:SizeGroupe,  type_: SolderType) -> (Detachment)
    {
        for det in detachments
        {
    
        if(det.type == type_)
        {
            if(det.size ==  size_)
            {
            return det;
            }
        }
        }
        let newd:Detachment =  DetachmentFabric.create( type_,size:  size_);
        detachments.append(newd);
        return newd;
    }
}

