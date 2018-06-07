package gef.tutorial.step.command;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import gef.tutorial.step.model.AbstractConnectionModel;

public class MoveBendpointCommand extends Command
{
    private AbstractConnectionModel conn;
    private Point newLocation,oldLocation;
    private int index;

    @Override
    public void execute()
    {
        oldLocation = conn.getBendpoints().get(index);
        conn.replaceBendpoint(index, newLocation);
    }
    
    public void setConnection(Object object)
    {
        conn = (AbstractConnectionModel) object;
    }
    public void setIndex(int i)
    {
        index = i;
    }
    public void setNewLocation(Point loc)
    {
        newLocation = loc;
    }
    @Override
    public void undo()
    {
        conn.replaceBendpoint(index, oldLocation);
    }   
}
