package gef.tutorial.step.command;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import gef.tutorial.step.model.AbstractConnectionModel;

public class CreateBendpointCommand extends Command {
	private AbstractConnectionModel conn;
	private Point location; // bend点的位置
	private int index; // bend点的索引

	@Override
	public void execute() {
		conn.addBendpoint(index, location);
	}

	public void setConnection(Object conn) {
		this.conn = (AbstractConnectionModel) conn;
	}

	public void setIndex(int i) {
		this.index = i;
	}

	public void setLocation(Point loc) {
		this.location = loc;
	}
	@Override
	public void undo() {
		conn.removeBendpoint(index);
	}
}
