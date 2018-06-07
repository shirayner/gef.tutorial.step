package gef.tutorial.step.action;


import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import gef.tutorial.step.app.Application;
import gef.tutorial.step.ui.editor.DiagramEditor;
import gef.tutorial.step.ui.editor.input.DiagramEditorInput;

public class DiagramAction extends Action implements IWorkbenchAction,
        ISelectionListener
{
    private final IWorkbenchWindow window;
    public final static String ID = "wei.learn.gef.DiagramAction";
    @SuppressWarnings("unused")
	private IStructuredSelection selection;
    public DiagramAction()
    {
       window = null;
    }
    public DiagramAction(IWorkbenchWindow window)
    {
        this.window = window;
        setId(ID);
        setText("&Diagram");
        setToolTipText("Draw the GEF diagram.");
        setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(
                Application.PLUGIN_ID, "/icons/alt_window16.gif"));
        window.getSelectionService().addSelectionListener(this);
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose()
    {
        window.getSelectionService().removeSelectionListener(this);
    }

    @Override
    public void run()
    {
        String path = openFileDialog();
        if(path!=null)
        {
            IEditorInput input = new DiagramEditorInput(new Path(path));
            IWorkbenchPage page = window.getActivePage();
            try{
                page.openEditor(input, DiagramEditor.ID,true);
            }catch(Exception e){
                System.err.println(e);
            }
        }
        
    }
    private String openFileDialog()
    {
        FileDialog dialog = new FileDialog(window.getShell(),SWT.OPEN);
        dialog.setText("GEFÎÄ¼þ");
       // dialog.setFilterExtensions(new String[]{".pss"});
        return dialog.open();
    }
}
