package gef.tutorial.step.command;

import org.eclipse.gef.commands.Command;

import gef.tutorial.step.model.ContentsModel;
import gef.tutorial.step.model.HelloModel;

public class CreateCommand extends Command {
	private ContentsModel contentsModel;
	private HelloModel helloModel;

	@Override
	public void execute() {
		contentsModel.addChild(helloModel);
	}

	public void setContentsModel(Object model) {
		contentsModel = (ContentsModel) model;
	}

	public void setHelloModel(Object model) {
		helloModel = (HelloModel) model;
	}

	@Override
	public void undo() {
		contentsModel.removeChild(helloModel);
	}
}
