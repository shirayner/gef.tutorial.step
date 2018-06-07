package gef.tutorial.step.editpart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;

import gef.tutorial.step.locator.MidpointOffsetLocator;

public class ArrowConnectionEditPart extends CustomAbstractConnectionEditPart {
	int anchorX, anchorY, dx, dy;

	protected IFigure createFigure() {
		// 还是多义线连接
		final PolylineConnection connection = (PolylineConnection) super
				.createFigure();
		// 不过这里加上了修饰
		connection.setTargetDecoration(new PolygonDecoration());
		// add a label
		final Label label = new Label("Label");
		label.setOpaque(true);
		connection.add(label, new MidpointOffsetLocator(connection, 0));
		label.addMouseListener(new MouseListener() {
			public void mouseDoubleClicked(MouseEvent me) {
			}

			public void mousePressed(MouseEvent me) {
				getViewer().appendSelection(ArrowConnectionEditPart.this);
				anchorX = me.x;
				anchorY = me.y;
				me.consume();
			}

			public void mouseReleased(MouseEvent me) {
				me.consume();
			}
		});
		label.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent me) {
				dx += me.x - anchorX;
				dy += me.y - anchorY;
				anchorX = me.x;
				anchorY = me.y;
				Object constraint = connection.getLayoutManager()
						.getConstraint(label);
				if (constraint instanceof MidpointOffsetLocator) {
					((MidpointOffsetLocator) constraint).setOffset(new Point(
							dx, dy));
					label.revalidate();
				}
				me.consume();
			}

			@Override
			public void mouseEntered(MouseEvent me) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent me) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseHover(MouseEvent me) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseMoved(MouseEvent me) {
				// TODO Auto-generated method stub

			}

			// 省略其它无关代码
		});
		return connection;
	}
}
