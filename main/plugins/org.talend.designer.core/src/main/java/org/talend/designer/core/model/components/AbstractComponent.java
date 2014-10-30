// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.core.model.components;

import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.talend.core.model.components.ComponentCategory;
import org.talend.core.model.components.IComponent;

/**
 * ggu class global comment. Detailled comment
 */
public abstract class AbstractComponent implements IComponent {

    private String paletteType;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.getName() == null) ? 0 : this.getName().hashCode())
                + ((this.getPaletteType() == null) ? 0 : this.getPaletteType().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof IComponent)) {
            return false;
        }
        final IComponent other = (IComponent) obj;
        String thisName = this.getName();
        String otherName = other.getName();
        if (thisName == null) {
            if (otherName != null) {
                return false;
            }
        } else if (!thisName.equals(otherName)) {
            return false;
        } else if (paletteType == null) {
            if (other.getPaletteType() != null) {
                return false;
            }
        } else if (!paletteType.equals(other.getPaletteType())) {
            return false;
        }
        return true;
    }

    @Override
    public String getPaletteType() {
        return paletteType;
    }

    @Override
    public void setPaletteType(String paletteType) {
        this.paletteType = paletteType;
    }

    @Override
    public String getRepositoryType() {
        return null;
    }

    /**
     * This is just added in <code>HEADER</code>, it is used to present the component belongs to common process, M/R
     * process and etc. The {@link ComponentCategory#CATEGORY_4_DI} is as the default value. Added by Marvin Wang on Jan
     * 11, 2013.
     * 
     */
    @Override
    public String getType() {
        return ComponentCategory.CATEGORY_4_DI.getName();
    }

    @Override
    public boolean isReduce() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#getPartitioning()
     */
    @Override
    public String getPartitioning() {
        return "AUTO"; //$NON-NLS-1$
    }

    @Override
    public boolean isSupportDbType() {
        return false;
    }

    @Override
    public Map<String, ImageDescriptor> getImageRegistry() {
        return null;
    }

    @Override
    public boolean isLog4JEnabled() {
        return false;
    }

}
