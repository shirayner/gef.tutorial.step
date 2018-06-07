package gef.tutorial.step.command;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import gef.tutorial.step.model.AbstractConnectionModel;

public class DeleteBendpointCommand extends Command
{
    private AbstractConnectionModel conn;
    private Point  oldLocation;
    private int index;

    @Override
    public void execute()
    {
        oldLocation = conn.getBendpoints().get(index);
        conn.removeBendpoint(index);
    }

    public void setConnection(Object object)
    {
        conn = (AbstractConnectionModel) object;
    }

    public void setIndex(int i)
    {
        index = i;
    }

    @Override
    public void undo()
    {
        conn.addBendpoint(index, oldLocation);
    }
}
