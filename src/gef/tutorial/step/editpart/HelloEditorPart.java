package gef.tutorial.step.editpart;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.viewers.TextCellEditor;

import gef.tutorial.step.model.HelloModel;
import gef.tutorial.step.policy.CustomComponentEditPolicy;
import gef.tutorial.step.policy.CustomDirectEditPolicy;
import gef.tutorial.step.policy.CustomGraphicalNodeEditPolicy;

public class HelloEditorPart extends EditPartWithListener implements
		NodeEditPart {

	private CustomDirectEditManager directManager = null;

	@Override
	protected IFigure createFigure() {
		HelloModel model = (HelloModel) getModel();

		Label label = new Label();
		label.setText(model.getText());

		label.setBorder(new CompoundBorder(new LineBorder(),
				new MarginBorder(3)));
		label.setBackgroundColor(ColorConstants.orange);
		label.setOpaque(true);
		return label;
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new CustomComponentEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new CustomDirectEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new CustomGraphicalNodeEditPolicy());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if (propertyName.equals(HelloModel.P_CONSTRAINT)) {
			refreshVisuals();
		} else if (propertyName.equals(HelloModel.P_TEXT)) {
			Label label = (Label) getFigure();
			label.setText((String) evt.getNewValue());
		}else if(propertyName.equals(HelloModel.P_SOURCE_CONNECTION))
		{
			refreshSourceConnections();
		}else if(propertyName.equals(HelloModel.P_TARGET_CONNECTION))
		{
			refreshTargetConnections();
		}
	}

	@Override
	protected void refreshVisuals() {
		Rectangle constraint = ((HelloModel) getModel()).getConstraint();
		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), constraint);
	}

	@Override
	public void performRequest(Request req) {
		// 如果Request是REQ_DIRECT_EDIT,则执行直接编辑属性的辅助函数performDirectEdit
		if (req.getType().equals(RequestConstants.REQ_DIRECT_EDIT)) {
			performDirectEdit();
			return;
		}
	}

	private void performDirectEdit() {
		if (directManager == null) {
			// 如果还没有directManager,则创建
			directManager = new CustomDirectEditManager(this,
					TextCellEditor.class, new CustomCellEditorLocator(
							getFigure()));
		}
		directManager.show();
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

	protected List<Object> getModelSourceConnections() {
		return ((HelloModel) getModel()).getModelSourceConnection();
	}

	public List<Object> getModelTargetConnections() {
		return ((HelloModel) getModel()).getModelTargetConnection();
	}
}
