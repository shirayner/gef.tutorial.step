package gef.tutorial.step.editpart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Text;

public class CustomCellEditorLocator implements CellEditorLocator {

	private IFigure figure; //Text控件要处于Figure所在的位置
	
	public CustomCellEditorLocator(IFigure figure) {
		this.figure = figure;
	}

	@Override
	public void relocate(CellEditor celleditor) {
		Text text = (Text) celleditor.getControl();
		//Text控件尺寸和Figure一样
		Rectangle rect = figure.getBounds().getCopy();
		figure.translateToAbsolute(rect);
		text.setBounds(rect.x,rect.y,rect.width,rect.height);

	}

}
