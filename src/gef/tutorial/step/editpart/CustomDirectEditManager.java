package gef.tutorial.step.editpart;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.swt.widgets.Text;

import gef.tutorial.step.model.HelloModel;

public class CustomDirectEditManager extends DirectEditManager {

	private HelloModel helloModel; // 想要修改该模型的文本属性

	@SuppressWarnings("rawtypes")
	public CustomDirectEditManager(GraphicalEditPart source, Class editorType,
			CellEditorLocator locator) {
		super(source, editorType, locator);
		helloModel = (HelloModel) source.getModel();
	}

	@Override
	protected void initCellEditor() {
		// 在显示一个cell editor之前,先给他设置一个值
		// 这里的值时获得图形模型的文本
		getCellEditor().setValue(helloModel.getText());
		// 在所选中的TextCellEditor的Text空间中的所有文本都显示为选择状态
		Text text = (Text) getCellEditor().getControl();
		text.selectAll();

	}

}
