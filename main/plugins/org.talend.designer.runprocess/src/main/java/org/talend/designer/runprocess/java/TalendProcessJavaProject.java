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
package org.talend.designer.runprocess.java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.IJavaProject;
import org.osgi.framework.FrameworkUtil;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.utils.generation.JavaUtils;
import org.talend.core.CorePlugin;
import org.talend.core.ILibraryManagerService;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.runtime.process.ITalendProcessJavaProject;
import org.talend.designer.core.ui.views.problems.Problems;
import org.talend.designer.maven.launch.TalendMavenLauncher;
import org.talend.designer.maven.model.MavenConstants;
import org.talend.designer.maven.model.MavenSystemFolders;
import org.talend.designer.maven.model.TalendMavenContants;
import org.talend.designer.maven.template.MavenPomSynchronizer;
import org.talend.librariesmanager.model.ModulesNeededProvider;
import org.talend.utils.io.FilesUtils;

/**
 * created by ggu on 26 Jan 2015 Detailled comment
 *
 */
public class TalendProcessJavaProject implements ITalendProcessJavaProject {

    private IJavaProject javaProject;

    private final MavenPomSynchronizer synchronizer;

    public TalendProcessJavaProject(IJavaProject javaProject) {
        super();
        this.javaProject = javaProject;
        this.synchronizer = new MavenPomSynchronizer(this);

    }

    @Override
    public IJavaProject getJavaProject() {
        try {// try to open project.
            tryOpenProject(null, this.javaProject.getProject());
        } catch (CoreException e) {
            //
        }
        return this.javaProject;
    }

    @Override
    public IProject getProject() {
        return getJavaProject().getProject();
    }

    @Override
    public IFolder getSrcFolder() {
        IProject p = getProject();
        if (p != null) {
            try {
                IFolder folder = p.getFolder(MavenSystemFolders.JAVA.getPath());
                createSubFolder(null, folder);
                return folder;
            } catch (CoreException e) {
                ExceptionHandler.process(e);
            }
        }
        return null;
    }

    @Override
    public IFolder getTestSrcFolder() {
        IProject p = getProject();
        if (p != null) {
            try {
                IFolder folder = p.getFolder(MavenSystemFolders.JAVA_TEST.getPath());
                createSubFolder(null, folder);
                return folder;
            } catch (CoreException e) {
                ExceptionHandler.process(e);
            }
        }
        return null;
    }

    @Override
    public IFolder getResourcesFolder() {
        IProject p = getProject();
        if (p != null) {
            try {
                IFolder folder = p.getFolder(MavenSystemFolders.RESOURCES.getPath());
                createSubFolder(null, folder);
                return folder;
            } catch (CoreException e) {
                ExceptionHandler.process(e);
            }
        }
        return null;
    }

    @Override
    public IFolder getTestResourcesFolder() {
        IProject p = getProject();
        if (p != null) {
            try {
                IFolder folder = p.getFolder(MavenSystemFolders.RESOURCES_TEST.getPath());
                createSubFolder(null, folder);
                return folder;
            } catch (CoreException e) {
                ExceptionHandler.process(e);
            }
        }
        return null;
    }

    @Override
    public IFolder getLibFolder() {
        IProject p = getProject();
        if (p != null) {
            try {
                IFolder folder = p.getFolder(new Path(JavaUtils.JAVA_LIB_DIRECTORY));
                createSubFolder(null, folder);
                return folder;
            } catch (CoreException e) {
                ExceptionHandler.process(e);
            }
        }
        return null;
    }

    @Override
    public IFolder getOutputFolder() {
        IJavaProject jProject = getJavaProject();
        if (jProject != null) {
            try {
                IFolder folder = jProject.getProject().getFolder(MavenSystemFolders.JAVA.getOutputPath());
                // IFolder folder = jProject.getProject().getParent().getFolder(jProject.getOutputLocation());
                createSubFolder(null, folder);
                return folder;
            } catch (CoreException e) {
                ExceptionHandler.process(e);
            }
        }
        return null;
    }

    @Override
    public IFolder getTestOutputFolder() {
        IJavaProject jProject = getJavaProject();
        if (jProject != null) {
            try {
                IFolder folder = jProject.getProject().getFolder(MavenSystemFolders.JAVA_TEST.getOutputPath());
                createSubFolder(null, folder);
                return folder;
            } catch (CoreException e) {
                ExceptionHandler.process(e);
            }
        }
        return null;
    }

    @Override
    public IFolder getSrcSubFolder(IProgressMonitor monitor, String path) {
        return createSubFolder(monitor, getSrcFolder(), path);
    }

    @Override
    public IFolder getResourceSubFolder(IProgressMonitor monitor, String path) {
        return createSubFolder(monitor, getResourcesFolder(), path);
    }

    /**
     * 
     * will try to create the sub folders which under the base folder.
     */
    @Override
    public IFolder createSubFolder(IProgressMonitor monitor, IFolder baseFolder, String path) {
        if (baseFolder != null && path != null && path.length() > 0) {
            try {
                IFolder subFolder = baseFolder.getFolder(path);
                createSubFolder(monitor, subFolder);
                return subFolder;
            } catch (CoreException e) {
                ExceptionHandler.process(e);
                return null;
            }
        }
        return null;
    }

    /**
     * 
     * will try to create the sub folders hierarchically.
     */
    private void createSubFolder(IProgressMonitor monitor, IFolder subFolder) throws CoreException {
        if (subFolder != null) {
            tryOpenProject(monitor, subFolder); // before creating, try to open project.

            IContainer parent = subFolder.getParent();
            if (parent != null && parent.getType() == IResource.FOLDER) {
                IFolder parentFolder = (IFolder) parent;
                if (!parentFolder.exists()) {
                    createSubFolder(monitor, parentFolder);
                }
            }
            if (!subFolder.exists()) {
                subFolder.create(true, true, monitor);
            }
        }
    }

    @Override
    public boolean cleanFolder(IProgressMonitor monitor, IFolder folder) throws CoreException {
        if (folder != null && folder.exists()) {
            tryOpenProject(monitor, folder); // before clean, try to open project.

            IResource[] childrenRecs = folder.members();
            if (childrenRecs.length > 0) {
                for (IResource child : childrenRecs) {
                    child.delete(true, monitor);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 
     * try to open the container's project.
     */
    private void tryOpenProject(IProgressMonitor monitor, IContainer container) throws CoreException {
        IProject project = container.getProject();
        if (!project.isOpen()) { // try to open project.
            project.open(monitor);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.runtime.process.ITalendProcessJavaProject#addChildModule(java.lang.String[])
     */
    @Override
    public void addChildModules(boolean removeOld, String... childModules) {
        try {
            synchronizer.addChildModules(removeOld, childModules);
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.runtime.process.ITalendProcessJavaProject#buildModules(String,java.lang.String[])
     */
    @Override
    public void buildModules(String goals, String[] childrenModules) {
        if (childrenModules == null) {
            if (goals != null && goals.trim().length() > 0) {
                mavenBuildCodeProjectPom(goals, TalendMavenContants.CURRENT_PATH);
            } else { // JDT build
                buildWholeCodeProject();
            }
        } else if (childrenModules.length > 0) {
            for (String module : childrenModules) {

                IPath modulePath = new Path(module);
                // remove pom.xml
                if (modulePath.lastSegment().equals(MavenConstants.POM_FILE_NAME)) {
                    modulePath = modulePath.removeLastSegments(1);
                }

                // clean before classes for current job.
                String newModule = modulePath.toString();
                mavenBuildCodeProjectPom(goals, newModule);
            }
        } else { // ==0
            // nothing do for empty modules.
        }
    }

    private void mavenBuildCodeProjectPom(String goals, String module) {
        // cleanBeforeBuilds(module);

        IFile childModulePomFile;
        if (TalendMavenContants.CURRENT_PATH.equals(module)) {
            childModulePomFile = this.getProject().getFile(MavenConstants.POM_FILE_NAME);
        } else {
            IFolder moduleFolder = this.getProject().getFolder(module);
            childModulePomFile = moduleFolder.getFile(MavenConstants.POM_FILE_NAME);

        }
        if (childModulePomFile.getLocation().toFile().exists()) { // existed
//            TalendMavenLauncher mavenLauncher = null;
//            if (goals == null || goals.trim().length() == 0) { // by default is compile
//                mavenLauncher = new TalendMavenLauncher(childModulePomFile);
//            } else {
//                mavenLauncher = new TalendMavenLauncher(childModulePomFile, goals);
//            }
//            mavenLauncher.execute();

            /*
             * FIXME, because the marker issue, we have to build whole project to get the markers.
             * 
             * Even thought can use the API Problems.computeCompilationUnit to get the code problems. but can't get the
             * markers still and the status is not good still.
             * 
             * Later, will try to use another way to fix this, maybe need change the problem view, because it based on
             * markers.
             */
            if (Problems.buildWholeProject) {
                // call 2 times for now. Seems if call only once, it will not get all the errors up to date
                // and call 2 times seems doesn't take much more time.
                buildWholeCodeProject();
                buildWholeCodeProject();
            }
        } else {
            throw new RuntimeException("The pom.xml is not existed. Can't build the job: " + module); //$NON-NLS-1$
        }
    }

    /**
     * DOC ggu Comment method "buildWholeCodeProject".
     */
    private void buildWholeCodeProject() {
        // build whole project
        try {
            NullProgressMonitor monitor = new NullProgressMonitor();
            IProject project = getProject();
            if (!project.isSynchronized(IResource.DEPTH_INFINITE)) {
                project.refreshLocal(IResource.DEPTH_INFINITE, monitor);
            }
            // project.build(IncrementalProjectBuilder.AUTO_BUILD, null);
            project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, monitor);
        } catch (CoreException e) {
            ExceptionHandler.process(e);
        }
    }

    private void cleanBeforeBuilds(String module) {
        IPath srcPath = this.getSrcFolder().getProjectRelativePath();
        IContainer outputContainer;
        if (TalendMavenContants.CURRENT_PATH.equals(module)) {
            outputContainer = this.getOutputFolder();
        } else {
            IFolder moduleFolder = this.getProject().getFolder(module);
            IPath modulePath = moduleFolder.getProjectRelativePath().makeRelativeTo(srcPath);
            outputContainer = this.getOutputFolder().getFolder(modulePath);
        }
        try {
            File jobFolder = outputContainer.getLocation().toFile();
            if (jobFolder.exists()) {
                FilesUtils.deleteFile(jobFolder, true);
            }
            outputContainer.getParent().refreshLocal(IResource.DEPTH_INFINITE, null);
        } catch (CoreException e) {
            ExceptionHandler.process(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.runtime.process.ITalendProcessJavaProject#updateRoutinesPom(boolean,boolean)
     */
    @Override
    public void updateRoutinesPom(final boolean withBuild, boolean inBackgroud) {
        if (true) {
            // disable code for now, to review later.
            return;
        }
        // should process the pig udf and bean also.
        if (inBackgroud) {
            // work in backgroud or not?
            Job job = new Job("updating routines pom") { //$NON-NLS-1$

                @Override
                public IStatus run(IProgressMonitor monitor) {
                    return processRoutinesPom(withBuild);
                }
            };
            job.schedule();
        } else {
            processRoutinesPom(withBuild);
        }

    }

    private IStatus processRoutinesPom(boolean withBuild) {
        if (true) {
         // disable code for now, to review later.
            return Status.OK_STATUS;
        }
        try {
            // update routines pom, so true here
            this.synchronizer.syncRoutinesPom(true);

            if (withBuild) {
                // synch the routines jar first
                List<String> modules = new ArrayList<String>();
                Set<ModuleNeeded> runningModules = ModulesNeededProvider.getRunningModules();
                for (ModuleNeeded m : runningModules) {
                    modules.add(m.getModuleName());
                }
                File libDir = JavaProcessorUtilities.getJavaProjectLibFolder();
                ILibraryManagerService repositoryBundleService = CorePlugin.getDefault().getRepositoryBundleService();
                repositoryBundleService.retrieve(modules, libDir.getAbsolutePath());

                // install routines
                IFolder routinesSrcFolder = getSrcFolder().getFolder(JavaUtils.JAVA_ROUTINES_DIRECTORY);
                if (routinesSrcFolder.getLocation().toFile().exists()) {
                    String routineModule = routinesSrcFolder.getProjectRelativePath().toString();
                    buildModules(MavenConstants.GOAL_INSTALL, new String[] { routineModule });
                }
            }

            return Status.OK_STATUS;
        } catch (Exception e) {
            ExceptionHandler.process(e);
            return new Status(Status.ERROR, FrameworkUtil.getBundle(getClass()).getSymbolicName(), Status.ERROR, e.getMessage(),
                    e);
        }
    }

}