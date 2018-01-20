/*
 *******************************************************************************
 * Copyright (c) 2013 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************
*/
package com.whizzosoftware.wzwave.node.specific;

import com.whizzosoftware.wzwave.controller.ZWaveControllerContext;
import com.whizzosoftware.wzwave.node.NodeInfo;
import com.whizzosoftware.wzwave.node.NodeListener;
import com.whizzosoftware.wzwave.node.generic.StaticController;
import com.whizzosoftware.wzwave.persist.PersistenceContext;

/**
 * A PC Controller node.
 *
 * @author Dan Noguerol
 */
public class PCController extends StaticController {
    static public final byte ID = 0x01;

    public PCController(NodeInfo info, NodeListener listener) {
        super(info, listener);
    }

    public PCController(PersistenceContext pctx, Byte nodeId, NodeListener listener) {
        super(pctx, nodeId, listener);
    }

    @Override
    protected void refresh(ZWaveControllerContext context, boolean deferIfNotListening) {

    }
}
