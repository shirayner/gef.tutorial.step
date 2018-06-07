package gef.tutorial.step.editpart;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.gef.EditPolicy;

import gef.tutorial.step.model.ContentsModel;
import gef.tutorial.step.policy.CustomXYLayoutEditPolicy;

public class ContentsEditPart extends EditPartWithListener {

	@Override
	protected IFigure createFigure() {
		Layer figure = new Layer();
		figure.setLayoutManager(new XYLayout());
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new CustomXYLayoutEditPolicy());
	}
	@Override
	protected List<Object> getModelChildren() {
		return ((ContentsModel)getModel()).getChildren();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if(propertyName.equals(ContentsModel.P_CHILDREN))
		{
			refreshChildren();
		}
	}
}
