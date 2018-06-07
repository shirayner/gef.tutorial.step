package gef.tutorial.step.command;

import org.eclipse.gef.commands.Command;

import gef.tutorial.step.model.AbstractConnectionModel;
//ide: UpdateWireCommand
public class DeleteConnectionCommand extends Command
{
    private AbstractConnectionModel conn;
    @Override
    public void execute()
    {
        conn.detachSource();
        conn.detachTarget();
    }
    
    public void setConnectionModel(Object model)
    {
        conn = (AbstractConnectionModel) model;
    }
    
    @Override
    public void undo()
    {
        conn.attachSource();
        conn.attachTarget();
    }
}
