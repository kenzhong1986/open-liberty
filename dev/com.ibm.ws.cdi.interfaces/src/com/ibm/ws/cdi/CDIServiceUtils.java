/*******************************************************************************
 * Copyright (c) 2015 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.cdi;

import com.ibm.ws.container.service.app.deploy.ModuleInfo;
import com.ibm.ws.container.service.app.deploy.extended.ExtendedApplicationInfo;
import com.ibm.ws.container.service.app.deploy.extended.ExtendedModuleInfo;
import com.ibm.ws.runtime.metadata.ApplicationMetaData;
import com.ibm.ws.runtime.metadata.ModuleMetaData;
import com.ibm.wsspi.adaptable.module.Container;
import com.ibm.wsspi.adaptable.module.NonPersistentCache;
import com.ibm.wsspi.adaptable.module.UnableToAdaptException;

/**
 *
 */
public class CDIServiceUtils {

    public static ApplicationMetaData getApplicationMetaData(Container moduleContainer) throws CDIException {
        ApplicationMetaData appMetaData = null;
        ModuleInfo moduleInfo = getModuleInfo(moduleContainer);
        if (moduleInfo != null) {
            ExtendedApplicationInfo applicationInfo = (ExtendedApplicationInfo) moduleInfo.getApplicationInfo();
            appMetaData = applicationInfo.getMetaData();
        }

        return appMetaData;
    }

    public static ModuleMetaData getModuleMetaData(Container moduleContainer) throws CDIException {
        ModuleMetaData moduleMetaData = null;
        ExtendedModuleInfo moduleInfo = (ExtendedModuleInfo) getModuleInfo(moduleContainer);
        if (moduleInfo != null) {
            moduleMetaData = moduleInfo.getMetaData();
        }

        return moduleMetaData;
    }

    private static ModuleInfo getModuleInfo(Container container) throws CDIException {
        ModuleInfo moduleInfo = null;

        try {
            NonPersistentCache cache = container.adapt(NonPersistentCache.class);
            moduleInfo = (ModuleInfo) cache.getFromCache(ModuleInfo.class);
        } catch (UnableToAdaptException e) {
            throw new CDIException(e);
        }
        return moduleInfo;
    }

}
