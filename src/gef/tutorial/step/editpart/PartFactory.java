package gef.tutorial.step.editpart;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import gef.tutorial.step.model.ArrowConnectionModel;
import gef.tutorial.step.model.ContentsModel;
import gef.tutorial.step.model.HelloModel;
import gef.tutorial.step.model.LineConnectionModel;

//ide:GraphicalControlFactory
public class PartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		// get EditPart for model element
		EditPart part = getPartForElement(model);
		// store model element in editPart
		part.setModel(model);
		return part;
	}

	private EditPart getPartForElement(Object model) {
		if (model instanceof HelloModel) {
			return new HelloEditorPart();
		} else if (model instanceof ContentsModel) {
			return new ContentsEditPart();
		} else if(model instanceof LineConnectionModel)
		{
			return new LineConnectionEditPart();
		}else if(model instanceof ArrowConnectionModel)
		{
			return new ArrowConnectionEditPart();
		}
		throw new RuntimeException("Can't create part for model element:"
				+ ((model != null) ? model.getClass().getName() : "null"));
	}

}
