package gef.tutorial.step.locator;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.geometry.Point;

public class MidpointOffsetLocator extends MidpointLocator {
	private Point offset;

	public MidpointOffsetLocator(Connection c, int i) {
		super(c, i);
		offset = new Point(0, 0);
	}

	@Override
	protected Point getReferencePoint() {
		Point point = super.getReferencePoint();
		return point.getTranslated(offset);
	}

	public Point getOffset() {
		return offset;
	}

	public void setOffset(Point offset) {
		this.offset = offset;
	}
}
