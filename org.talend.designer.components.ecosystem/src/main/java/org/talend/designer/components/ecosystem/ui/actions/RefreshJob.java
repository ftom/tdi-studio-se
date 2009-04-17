// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.components.ecosystem.ui.actions;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.designer.components.ecosystem.EcosystemUtils;
import org.talend.designer.components.ecosystem.i18n.Messages;
import org.talend.designer.components.ecosystem.jobs.ComponentSearcher;
import org.talend.designer.components.ecosystem.model.ComponentExtension;

/**
 * DOC hcw class global comment. Detailled comment
 */
public class RefreshJob extends Job {

    public RefreshJob() {
        super(Messages.getString("RefreshJob.FindExtensions.Title")); //$NON-NLS-1$
    }

    private List<ComponentExtension> fAvailableExtensions;

    @Override
    public IStatus run(IProgressMonitor monitor) {
        final String versionFilter = EcosystemUtils.getTosVersionFilter();
        monitor.beginTask(Messages.getString("RefreshJob.FindExtensions.Message", versionFilter), IProgressMonitor.UNKNOWN); //$NON-NLS-1$

        // run in another thread, make it possible to stop the remote procedure call when user press cancel
        // button
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<ComponentExtension>> task = executor.submit(new Callable<List<ComponentExtension>>() {

            public List<ComponentExtension> call() throws Exception {
                return ComponentSearcher.getAvailableComponentExtensions(versionFilter, EcosystemUtils.getCurrentLanguage());
            }

        });

        while (true) {
            try {
                if (monitor.isCanceled()) {
                    // stop the web service call
                    task.cancel(true);
                    return Status.CANCEL_STATUS;
                } else if (task.isDone()) {
                    // web service call complete
                    fAvailableExtensions = task.get();
                    break;
                }
            } catch (Exception e) {
                ExceptionHandler.process(e);
                return Status.CANCEL_STATUS;
            } finally {
                executor.shutdown();
            }
        }
        monitor.done();
        return Status.OK_STATUS;
    }

    public List<ComponentExtension> getAvailableExtensions() {
        return fAvailableExtensions;
    }

}
