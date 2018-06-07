package gef.tutorial.step.command;

import org.eclipse.gef.commands.Command;

import gef.tutorial.step.model.AbstractConnectionModel;
import gef.tutorial.step.model.HelloModel;

public class CreateConnectionCommand extends Command {
	private HelloModel source,target;
	private AbstractConnectionModel conn;
	@Override
	public boolean canExecute() {
		if(source==null||target==null)
		{
			return false;
		}else if(source.equals(target))
		{
			return false;
		}
		return true;
	}
	@Override
	public void execute() {
		conn.attachSource();
		conn.attachTarget();
	}
	public void setConnection(Object model)
	{
		conn=(AbstractConnectionModel) model;
	}
	public void setSource(Object model)
	{
		source = (HelloModel) model;
		conn.setSource(source);
	}
	public void setTarget(Object model)
	{
		target = (HelloModel) model;
		conn.setTarget(target);
	}
	@Override
	public void undo() {
		conn.detachSource();
		conn.detachTarget();
	}
}
