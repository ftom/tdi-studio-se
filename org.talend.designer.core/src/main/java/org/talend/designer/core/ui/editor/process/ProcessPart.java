// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.designer.core.ui.editor.process;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;
import org.eclipse.gef.rulers.RulerProvider;
import org.talend.designer.core.ui.editor.TalendScalableFreeformRootEditPart;
import org.talend.designer.core.ui.editor.nodes.NodePart;

/**
 * EditPart of the Diagram that will set the main layer, and the differents kinds of rulers. <br/>
 * 
 * $Id$
 * 
 */
public class ProcessPart extends AbstractGraphicalEditPart implements PropertyChangeListener {

    private FreeformLayer fig2;

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
     */
    protected List getModelChildren() {
        return ((Process) this.getModel()).getElements();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#activate()
     */
    public void activate() {
        if (isActive()) {
            return;
        }
        super.activate();
        ((Process) getModel()).addPropertyChangeListener(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#deactivate()
     */
    public void deactivate() {
        if (!isActive()) {
            return;
        }
        super.deactivate();
        ((Process) getModel()).removePropertyChangeListener(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        Figure figure = new FreeformLayer();
        figure.setLayoutManager(new FreeformLayout());

        fig2 = new FreeformLayer();
        getLayer(TalendScalableFreeformRootEditPart.PROCESS_BACKGROUND_LAYER).add(fig2);
        ajustReadOnly();

        return figure;
    }

    /**
     * DOC smallet Comment method "ajustReadOnly".
     */
    public void ajustReadOnly() {
        if (((Process) getModel()).isReadOnly()) {
            fig2.setBackgroundColor(Process.READ_ONLY_COLOR);
            fig2.setOpaque(true);
        } else {
            fig2.setBackgroundColor(Process.READ_WRITE_COLOR);
            fig2.setOpaque(true);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getAdapter(java.lang.Class)
     */
    public Object getAdapter(final Class adapter) {
        if (adapter == SnapToHelper.class) {
            List<Object> snapStrategies = new ArrayList<Object>();
            Boolean val = (Boolean) getViewer().getProperty(RulerProvider.PROPERTY_RULER_VISIBILITY);

            val = (Boolean) getViewer().getProperty(ProcessSnapToGeometry.PROPERTY_SNAP_ENABLED);
            if (val != null && val.booleanValue()) {
                snapStrategies.add(new ProcessSnapToGeometry(this));
            }

            val = (Boolean) getViewer().getProperty(SnapToGrid.PROPERTY_GRID_ENABLED);
            if (val != null && val.booleanValue()) {
                snapStrategies.add(new SnapToGrid(this));
            }

            if (snapStrategies.size() == 0) {
                return null;
            }
            if (snapStrategies.size() == 1) {
                return (SnapToHelper) snapStrategies.get(0);
            }

            SnapToHelper[] ss = new SnapToHelper[snapStrategies.size()];
            for (int i = 0; i < snapStrategies.size(); i++) {
                ss[i] = (SnapToHelper) snapStrategies.get(i);
            }
            return new CompoundSnapToHelper(ss);
        }

        return super.getAdapter(adapter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new ProcessLayoutEditPolicy());
        installEditPolicy("Snap Feedback", new SnapFeedbackPolicy()); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(final PropertyChangeEvent evt) {
        String prop = evt.getPropertyName();
        if (Process.NODES.equals(prop)) {
            refreshChildren();
        }
    }

    /**
     * Class that allows to use SnapToGeometry on NodePart only, not on NodeLabelEditPart. <br/>
     * 
     * $Id$
     * 
     */
    public class ProcessSnapToGeometry extends SnapToGeometry {

        public ProcessSnapToGeometry(ProcessPart container) {
            super(container);
            List<EditPart> exl = new ArrayList<EditPart>();
            List children = container.getChildren();
            // List<EditPart> nodePartList = new ArrayList<EditPart>();
            for (int i = 0; i < children.size(); i++) {
                if (!(children.get(i) instanceof NodePart)) {
                    exl.add((EditPart) children.get(i));
                }
            }
            populateRowsAndCols(generateSnapPartsList(exl));
        }

    }

    @Override
    public List getChildren() {
        return super.getChildren();
    }
}
