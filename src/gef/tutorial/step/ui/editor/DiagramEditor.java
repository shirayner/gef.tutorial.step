package gef.tutorial.step.ui.editor;

import java.util.ArrayList;
import java.util.EventObject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.AlignmentAction;
import org.eclipse.gef.ui.actions.DirectEditAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import gef.tutorial.step.app.Application;
import gef.tutorial.step.constant.IImageKeys;
import gef.tutorial.step.editpart.PartFactory;
import gef.tutorial.step.editpart.tree.TreeEditPartFactory;
import gef.tutorial.step.model.ArrowConnectionModel;
import gef.tutorial.step.model.ContentsModel;
import gef.tutorial.step.model.HelloModel;
import gef.tutorial.step.model.LineConnectionModel;

public class DiagramEditor extends GraphicalEditorWithPalette {

	// Editor ID
	public static final String ID = "gef.tutorial.step.ui.DiagramEditor";
	// an EditDomain is a "session" of editing which contains things like the
	// CommandStack
	GraphicalViewer viewer;
	private ContentsModel contentsModel = null;

	public DiagramEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		viewer = getGraphicalViewer();
		// 具有缩放能力的ScalableRootEditPart
		ScalableRootEditPart rootEditPart = new ScalableRootEditPart();
		viewer.setRootEditPart(rootEditPart);
		ZoomManager manager = rootEditPart.getZoomManager();
		// 放大比例数组
		double[] zoomLevels = new double[] {
				// 缩放比例是从25％－2000％
				0.25, 0.5, 0.75, 1.0, 1.5, 2.0, 2.5, 3.0, 4.0, 5.0, 10.0, 20.0 };
		manager.setZoomLevels(zoomLevels); // 添加放大比例
		// 设置非百分比缩放
		ArrayList<String> zoomContributions = new ArrayList<String>();
		zoomContributions.add(ZoomManager.FIT_ALL);
		zoomContributions.add(ZoomManager.FIT_HEIGHT);
		zoomContributions.add(ZoomManager.FIT_WIDTH);
		manager.setZoomLevelContributions(zoomContributions);
		// 注册放大Action
		IAction action = new ZoomInAction(manager);
		getActionRegistry().registerAction(action);
		// 注册缩小Action
		action = new ZoomOutAction(manager);
		getActionRegistry().registerAction(action);

		viewer.setEditPartFactory(new PartFactory());
		// 创建键盘句柄keyHander
		KeyHandler keyHandler = new KeyHandler();

		// 按DEL键时执行删除Action
		keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0),
				getActionRegistry().getAction(GEFActionConstants.DELETE));
		// 按F2键时执行直接编辑Action
		keyHandler.put(KeyStroke.getPressed(SWT.F2, 0), getActionRegistry()
				.getAction(GEFActionConstants.DIRECT_EDIT));
		getGraphicalViewer().setKeyHandler(keyHandler);
	}

	@Override
	protected void initializeGraphicalViewer() {
		// set the contents of this editor
		contentsModel = new ContentsModel();
		HelloModel child1 = new HelloModel();
		child1.setConstraint(new Rectangle(0, 0, -1, -1));
		contentsModel.addChild(child1);

		HelloModel child2 = new HelloModel();
		child2.setConstraint(new Rectangle(30, 30, -1, -1));
		contentsModel.addChild(child2);
		HelloModel child3 = new HelloModel();
		child3.setConstraint(new Rectangle(10, 80, 80, 50));
		contentsModel.addChild(child3);
		viewer.setContents(contentsModel);
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		getCommandStack().markSaveLocation();
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		// 1.0 创建一个palette的route
		PaletteRoot root = new PaletteRoot();
		// 2.0 创建一个工具组用于防止常规Tools
		PaletteGroup toolGroup = new PaletteGroup("工具");

		// 3.0 创建一个GEF提供的"selection"工具 并将其放到toolGroup中
		ToolEntry tool = new SelectionToolEntry();
		toolGroup.add(tool);

		// 3.1 该(选择)工具为缺省被选择的工具
		root.setDefaultEntry(tool);

		// 4.0 创建一个GEF提供的 "Marquee多选"工具并将其放到toolGroup中
		tool = new MarqueeToolEntry();
		toolGroup.add(tool);

		// 5.0 创建一个Drawer(抽屉)放置绘图工具,该抽屉名称为"画图"
		PaletteDrawer drawer = new PaletteDrawer("画图");

		// 指定"创建HelloModel模型"工具所对应的图标
		ImageDescriptor descriptor = AbstractUIPlugin
				.imageDescriptorFromPlugin(Application.PLUGIN_ID,
						"icons//alt_window16.gif");

		// 6.0 创建"创建HelloModel模型"工具
		CreationToolEntry creationToolEntry = new CreationToolEntry(
				"绘制HelloModel", "创建HelloModel模型", new SimpleFactory(
						HelloModel.class), descriptor, descriptor);
		drawer.add(creationToolEntry);

		// 连线
		PaletteDrawer connectionDrawer = new PaletteDrawer("连线");
		ImageDescriptor newConnectionDescriptor = AbstractUIPlugin
				.imageDescriptorFromPlugin(Application.PLUGIN_ID,
						IImageKeys.jiantou);
		ConnectionCreationToolEntry connCreationEntry = new ConnectionCreationToolEntry(
				"简单链接", "创建简单连接", new SimpleFactory(LineConnectionModel.class),
				newConnectionDescriptor, newConnectionDescriptor);
		connectionDrawer.add(connCreationEntry);

		// 箭头连线
		PaletteDrawer ArrowConnectionDrawer = new PaletteDrawer("箭头连线");
		ImageDescriptor newArrowConnectionDescriptor = AbstractUIPlugin
				.imageDescriptorFromPlugin(Application.PLUGIN_ID,
						IImageKeys.model);
		ConnectionCreationToolEntry arrowConnCreationEntry = new ConnectionCreationToolEntry(
				"箭头链接", "创建箭头连接",
				new SimpleFactory(ArrowConnectionModel.class),
				newArrowConnectionDescriptor, newArrowConnectionDescriptor);
		ArrowConnectionDrawer.add(arrowConnCreationEntry);

		// 7.0 最后将创建的两组工具加到root上
		root.add(toolGroup);
		root.add(drawer);
		root.add(connectionDrawer);
		root.add(ArrowConnectionDrawer);
		return root;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void createActions() {
		super.createActions();
		ActionRegistry registry = getActionRegistry();

		// 创建并注册一个DirectEditAction
		IAction action = new DirectEditAction((IWorkbenchPart) this);
		registry.registerAction(action);

		// 当一个action需要由选择对象更新时,需要注册其ID ??
		getSelectionActions().add(action.getId());
		// 水平方向对齐
		action = new AlignmentAction((IWorkbenchPart) this,
				PositionConstants.LEFT);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		action = new AlignmentAction((IWorkbenchPart) this,
				PositionConstants.CENTER);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		action = new AlignmentAction((IWorkbenchPart) this,
				PositionConstants.RIGHT);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		// 垂直方向对齐
		action = new AlignmentAction((IWorkbenchPart) this,
				PositionConstants.TOP);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		action = new AlignmentAction((IWorkbenchPart) this,
				PositionConstants.MIDDLE);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		action = new AlignmentAction((IWorkbenchPart) this,
				PositionConstants.BOTTOM);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
	}

	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class type) {
		if (type == ZoomManager.class) {
			return ((ScalableRootEditPart) getGraphicalViewer()
					.getRootEditPart()).getZoomManager();
		}// 如果是IContentOutlinePage 类型，则返回该ContentOutlinePage
		if (type == IContentOutlinePage.class) {
			return new CustomContentOutlinePage();
		}
		return super.getAdapter(type);
	}

	@Override
	public boolean isDirty() {
		return getCommandStack().isDirty();
	}

	@Override
	public void commandStackChanged(EventObject event) {
		firePropertyChange(IEditorPart.PROP_DIRTY);
		super.commandStackChanged(event);
	}

	/****************************** inner class *****************************/
	class CustomContentOutlinePage extends ContentOutlinePage {
		// 使用SashForm把Outline视图分为两部分,显示大纲和显示鹰眼
		private SashForm sash;

		// 实现鹰眼的图形
		private ScrollableThumbnail thumbnail;
		private DisposeListener disposeListener;

		public CustomContentOutlinePage() {
			super(new TreeViewer());
		}

		@SuppressWarnings("deprecation")
		@Override
		public void init(IPageSite pageSite) {
			super.init(pageSite);
			// 获得注册给graphical editor的Action
			ActionRegistry registry = getActionRegistry();
			// 使这些Action在大纲视图中也有效
			IActionBars bars = pageSite.getActionBars();
			String id = IWorkbenchActionConstants.UNDO;
			bars.setGlobalActionHandler(id, registry.getAction(id));
			id = IWorkbenchActionConstants.REDO;
			bars.setGlobalActionHandler(id, registry.getAction(id));
			id = IWorkbenchActionConstants.DELETE;
			bars.setGlobalActionHandler(id, registry.getAction(id));
			bars.updateActionBars();
		}

		@Override
		public void createControl(Composite parent) {
			// 创建SashForm
			sash = new SashForm(parent, SWT.VERTICAL);
			// 增加分割线
			getViewer().createControl(sash);
			// 设置Edit Domain
			getViewer().setEditDomain(getEditDomain());
			// 设置EditPartFactory
			getViewer().setEditPartFactory(new TreeEditPartFactory());
			// 本视图中应用于ContentsModel
			getViewer().setContents(contentsModel);
			getSelectionSynchronizer().addViewer(getViewer());
			Canvas canvas = new Canvas(sash, SWT.BORDER);
			// 使用LightweightSystem 绘制小图形
			LightweightSystem lws = new LightweightSystem(canvas);
			// 获得RootEditPart,议会制图形
			ScalableRootEditPart rootEditPart = (ScalableRootEditPart) getGraphicalViewer()
					.getRootEditPart();
			thumbnail = new ScrollableThumbnail(
					(Viewport) rootEditPart.getFigure());
			thumbnail.setSource(rootEditPart
					.getLayer(LayerConstants.PRINTABLE_LAYERS));
			lws.setContents(thumbnail);
			disposeListener = new DisposeListener() {

				@Override
				public void widgetDisposed(DisposeEvent e) {
					if (thumbnail != null) {
						thumbnail.deactivate();
						thumbnail = null;
					}
				}
			};
			// 当Graphical Viewer 删除时,删除thumbnail图形
			getGraphicalViewer().getControl().addDisposeListener(
					disposeListener);
		}

		@Override
		public Control getControl() {
			// TODO Auto-generated method stub
			return sash;
		}

		@Override
		public void dispose() {
			getSelectionSynchronizer().removeViewer(getViewer());

			Control control = getGraphicalViewer().getControl();
			if (control != null
					&& !control.isDisposed())
			{
				control.removeDisposeListener(disposeListener);
				
			}
			super.dispose();
		}
	}
}
