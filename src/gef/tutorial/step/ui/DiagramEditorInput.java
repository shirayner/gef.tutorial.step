package gef.tutorial.step.ui;
//内容提供者
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPersistableElement;

public class DiagramEditorInput implements IPathEditorInput
{
    private IPath path;
    
    public DiagramEditorInput(IPath path)
    {
        this.path = path;
    }

    @Override
    public boolean exists()
    {
        // TODO Auto-generated method stub
        return path.toFile().exists();
    }

    @Override
    public ImageDescriptor getImageDescriptor()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName()
    {
        // TODO Auto-generated method stub
        return path.toString();
    }

    @Override
    public IPersistableElement getPersistable()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getToolTipText()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @SuppressWarnings("rawtypes")
	@Override
    public Object getAdapter(Class adapter)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IPath getPath()
    {
        // TODO Auto-generated method stub
        return path;
    }
    @Override
    public int hashCode()
    {
        // TODO Auto-generated method stub
        return path.hashCode();
    }
}
