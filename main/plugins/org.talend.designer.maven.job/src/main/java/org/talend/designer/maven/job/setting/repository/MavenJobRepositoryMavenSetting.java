// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.maven.job.setting.repository;

import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.viewers.ILabelProvider;
import org.talend.designer.maven.ui.setting.repository.RepositoryMavenSetting;
import org.talend.designer.maven.ui.utils.DesignerMavenUiHelper;
import org.talend.repository.model.RepositoryNode;

/**
 * DOC ggu class global comment. Detailled comment
 */
public abstract class MavenJobRepositoryMavenSetting extends RepositoryMavenSetting {

    private static final String FAKE_NODE_SUFFIX = "_fake"; //$NON-NLS-1$

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.designer.maven.ui.setting.repository.RepositoryMavenSetting#needMavenScripts(org.talend.repository
     * .model.RepositoryNode)
     */
    @Override
    public boolean needMavenScripts(RepositoryNode node) {
        if (DesignerMavenUiHelper.isFakeProcessRootNode(node)) {// fake process root node
            return false;
        }
        return super.needMavenScripts(node);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.designer.maven.ui.setting.repository.RepositoryMavenSetting#buildFolderNodeId(org.eclipse.jface.preference
     * .IPreferenceNode, org.eclipse.jface.viewers.ILabelProvider, org.talend.repository.model.RepositoryNode)
     */
    @Override
    protected String buildFolderNodeId(IPreferenceNode parentNode, ILabelProvider labelProvider, RepositoryNode node) {
        String buildNodeId = super.buildFolderNodeId(parentNode, labelProvider, node);
        if (DesignerMavenUiHelper.isFakeProcessRootNode(node)) {// fake process root node
            buildNodeId += FAKE_NODE_SUFFIX;
        }
        return buildNodeId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.maven.ui.setting.repository.RepositoryMavenSetting#createFolderNode(java.lang.String,
     * org.eclipse.jface.viewers.ILabelProvider, org.talend.repository.model.RepositoryNode)
     */
    @Override
    protected IPreferenceNode createFolderNode(String id, ILabelProvider labelProvider, RepositoryNode node) {
        // if (DesignerMavenUiHelper.isFakeProcessRootNode(node)) {// fake process root node
        // ImageDescriptor imageDesc = null;
        // Image image = labelProvider.getImage(node);
        // if (image != null) {
        // imageDesc = ImageDescriptor.createFromImageData(image.getImageData());
        // }
        // String label = labelProvider.getText(node);
        // // label= node.getLabel(); //there is on bug for this label, so use provider directly.
        //            return new RepositoryPreferenceNode(id, label, imageDesc, node); //$NON-NLS-1$
        // }
        return super.createFolderNode(id, labelProvider, node);
    }

}