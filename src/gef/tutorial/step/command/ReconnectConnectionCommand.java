package gef.tutorial.step.command;

import org.eclipse.gef.commands.Command;

import gef.tutorial.step.model.AbstractConnectionModel;
import gef.tutorial.step.model.HelloModel;

public class ReconnectConnectionCommand extends Command {
	private AbstractConnectionModel conn;
	private HelloModel newSource;
	private HelloModel newTarget;
	private HelloModel oldSource;
	private HelloModel oldTarget;
	private boolean flagUndo=false;

	@Override
	public boolean canExecute() {
		if (newSource != null && !newSource.equals(oldTarget)) {
			return true;
		} else if (newTarget != null && !newTarget.equals(oldSource)) {
			return true;
		} else
			return false;
	}

	@Override
	public void execute() {
		flagUndo=true;
		// ÐÂÆðµã
		if (newSource != null) {
			conn.detachSource();
			conn.setSource(newSource);
			conn.attachSource();
		} else if (newTarget != null) {
			conn.detachTarget();
			conn.setTarget(newTarget);
			conn.attachTarget();
		}
	}

	public void setConnectionModel(Object model) {
		conn = (AbstractConnectionModel) model;
		oldSource = conn.getSource();
		oldTarget = conn.getTarget();
	}

	public void setNewSource(Object model) {
		newSource = (HelloModel) model;
	}

	public void setNewTarget(Object model) {
		newTarget = (HelloModel) model;
	}

	@Override
	public void undo() {
		if(flagUndo !=true)
		{
			return;
		}
		flagUndo = false;
		if (newSource != null) {
			conn.detachSource();
			conn.setSource(oldSource);
			conn.attachSource();
		} else if (newTarget != null) {
			conn.detachTarget();
			conn.setTarget(oldTarget);
			conn.attachTarget();
		}
	}
}
