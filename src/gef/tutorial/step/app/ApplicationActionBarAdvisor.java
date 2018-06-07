package gef.tutorial.step.app;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import gef.tutorial.step.action.DiagramAction;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IWorkbenchAction exitAction;
	private IWorkbenchAction aboutAction;
	// private IAction undoAction;
	// private IAction redoAction;
	// private IAction deleteAction;
	private DiagramAction diagramAction;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	protected void makeActions(IWorkbenchWindow window) {
		exitAction = ActionFactory.QUIT.create(window);
		register(exitAction);
		aboutAction = ActionFactory.ABOUT.create(window);
		register(aboutAction);
		diagramAction = new DiagramAction(window);
		register(diagramAction);
		// undoAction = ActionFactory.UNDO.create(window);
		// register(undoAction);
		// redoAction = ActionFactory.REDO.create(window);
		// register(redoAction);
		// deleteAction = ActionFactory.DELETE.create(window);
		// register(deleteAction);
	}

	protected void fillMenuBar(IMenuManager menuBar) {
		MenuManager fileMenu = new MenuManager("&File", "file");
		fileMenu.add(diagramAction);
		fileMenu.add(new Separator());
		fileMenu.add(exitAction);
		MenuManager helpMenu = new MenuManager("&Help", "help");
		helpMenu.add(aboutAction);
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
	}

	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		ToolBarManager toolBarManager = new ToolBarManager();
		coolBar.add(toolBarManager);
		// toolBarManager.add(undoAction);
		// toolBarManager.add(redoAction);
		// toolBarManager.add(deleteAction);
	}

}
