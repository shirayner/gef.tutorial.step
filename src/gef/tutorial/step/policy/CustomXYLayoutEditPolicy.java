package gef.tutorial.step.policy;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import gef.tutorial.step.command.ChangeConstraintCommand;
import gef.tutorial.step.command.CreateCommand;
import gef.tutorial.step.model.HelloModel;

public class CustomXYLayoutEditPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		CreateCommand command = new CreateCommand();
		//产生创建图形的尺寸和位置
		Rectangle constraint = (Rectangle) getConstraintFor(request);
		HelloModel model = (HelloModel) request.getNewObject();
		model.setConstraint(constraint);
		command.setContentsModel(getHost().getModel());
		command.setHelloModel(model);
		return command;
	}

	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		ChangeConstraintCommand command = new ChangeConstraintCommand();
		command.setModel(child.getModel());
		command.setConstraint((Rectangle) constraint);
		return command;
	};

	@Override
	public Command getCommand(Request request) {
		// System.out.println(request.getType());
		return super.getCommand(request);
	}
}
