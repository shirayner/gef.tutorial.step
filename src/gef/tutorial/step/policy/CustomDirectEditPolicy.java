package gef.tutorial.step.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;

import gef.tutorial.step.command.DirectEditCommand;

public class CustomDirectEditPolicy extends DirectEditPolicy {

	@Override
	//当当选中cell editor,修改文本,cell editor失去焦点之前getDirectEditCoommand方法
	protected Command getDirectEditCommand(DirectEditRequest request) {
		DirectEditCommand command = new DirectEditCommand();
		command.setModel(getHost().getModel());
		command.setText((String) request.getCellEditor().getValue());
		return command;
	}

	@Override
	//showCurrentEditValue方法用于显示Figure中的当前直接编辑值
	//虽然CellEditor可能盖住了图形对该值的显示,但是更新图形会使其最优尺寸适应新值
	protected void showCurrentEditValue(DirectEditRequest request) {
		// TODO Auto-generated method stub

	}

}
