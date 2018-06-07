package gef.tutorial.step.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;

public class AbstractConnectionModel extends AbstractModel {
	private HelloModel source, target;
	// bending点的位置
	private List<Point> bendpoints = new ArrayList<Point>();
	// bending点位置改变的ID
	public static final String P_BEND_POINT = "_bend_point";

	public void attachSource() {
		if (!source.getModelSourceConnection().contains(this)) {
			source.addSourceConnection(this);
		}
	}

	public void attachTarget() {
		if (!target.getModelTargetConnection().contains(this)) {
			target.addTargetConnection(this);
		}
	}

	public void detachSource() {
		source.removeSourceConnection(this);
	}

	public void detachTarget() {
		target.removeTargetConnection(this);
	}

	public HelloModel getSource() {
		return source;
	}

	public void setSource(HelloModel model) {
		source = model;
	}

	public HelloModel getTarget() {
		return target;
	}

	public void setTarget(HelloModel model) {
		target = model;
	}

	public void addBendpoint(int index, Point point) {
		bendpoints.add(index, point);
		firePropertyChange(P_BEND_POINT, null, null);
	}

	public void removeBendpoint(int index) {
		bendpoints.remove(index);
		firePropertyChange(P_BEND_POINT, null, null);
	}

	public void replaceBendpoint(int index, Point point) {
		bendpoints.set(index, point);
		firePropertyChange(P_BEND_POINT, null, null);
	}

	public List<Point> getBendpoints() {
		return bendpoints;
	}
}
