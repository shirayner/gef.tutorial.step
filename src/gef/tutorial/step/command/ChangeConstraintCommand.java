package gef.tutorial.step.command;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import gef.tutorial.step.model.HelloModel;

public class ChangeConstraintCommand extends Command {
	private HelloModel helloModel;
	private Rectangle constraint;
	private Rectangle oldConstraint;

	@Override
	public void execute() {
		helloModel.setConstraint(constraint);
	}

	public void setConstraint(Rectangle rect) {
		this.constraint = rect;
	}

	public void setModel(Object model) {
		helloModel = (HelloModel) model;
		oldConstraint = helloModel.getConstraint();

	}
	@Override
	public void undo() {
		helloModel.setConstraint(oldConstraint);
	}
}
