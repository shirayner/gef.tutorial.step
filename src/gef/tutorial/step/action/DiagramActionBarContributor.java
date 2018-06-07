package gef.tutorial.step.action;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.AlignmentRetargetAction;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.actions.ActionFactory;

public class DiagramActionBarContributor extends ActionBarContributor {

	@Override
	protected void buildActions() {
		addRetargetAction(new UndoRetargetAction());
		addRetargetAction(new RedoRetargetAction());
		addRetargetAction(new DeleteRetargetAction());
		// Retarget 缩放Action
		addRetargetAction(new ZoomInRetargetAction());
		addRetargetAction(new ZoomOutRetargetAction());
		// 水平方向对齐
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.LEFT));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.CENTER));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.RIGHT));
		// 垂直方向对齐
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.TOP));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.MIDDLE));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.BOTTOM));
	}

	@Override
	protected void declareGlobalActionKeys() {
		// TODO Auto-generated method stub

	}

	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager) {
		toolBarManager.add(getAction(ActionFactory.UNDO.getId()));
		toolBarManager.add(getAction(ActionFactory.REDO.getId()));
		toolBarManager.add(getAction(ActionFactory.DELETE.getId()));
		// 加上分割条
		toolBarManager.add(new Separator());
		// 水平方向对齐按钮
		toolBarManager.add(getActionRegistry().getAction(
				GEFActionConstants.ALIGN_LEFT));
		toolBarManager.add(getActionRegistry().getAction(
				GEFActionConstants.ALIGN_CENTER));
		toolBarManager.add(getActionRegistry().getAction(
				GEFActionConstants.ALIGN_RIGHT));
		toolBarManager.add(new Separator());
		// 垂直方向对齐按钮
		toolBarManager.add(getActionRegistry().getAction(
				GEFActionConstants.ALIGN_TOP));
		toolBarManager.add(getActionRegistry().getAction(
				GEFActionConstants.ALIGN_MIDDLE));
		toolBarManager.add(getActionRegistry().getAction(
				GEFActionConstants.ALIGN_BOTTOM));
		// 加上分割条
		toolBarManager.add(new Separator());
		// 加上缩放按钮，注意这里的缩放Action 的ID 在GEF 中已经定义了一些常数
		toolBarManager.add(getActionRegistry().getAction(
				GEFActionConstants.ZOOM_IN));
		toolBarManager.add(getActionRegistry().getAction(
				GEFActionConstants.ZOOM_OUT));
		// 在工具条上加上组合框(如果需要在工具条上加其他组合框的可以研究一下ZoomComboContributionItem的源码)
		toolBarManager.add(new ZoomComboContributionItem(getPage()));
	}
}
