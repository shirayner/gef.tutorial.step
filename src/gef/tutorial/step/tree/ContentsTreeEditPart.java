package gef.tutorial.step.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import gef.tutorial.step.model.ContentsModel;

public class ContentsTreeEditPart extends CustomTreeEditPart {
	@Override
	protected List<Object> getModelChildren() {
		return ((ContentsModel) getModel()).getChildren();
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(ContentsModel.P_CHILDREN))
		{
			refreshChildren();
		}
	}
}
