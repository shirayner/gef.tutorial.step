package gef.tutorial.step.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import gef.tutorial.step.command.CreateConnectionCommand;
import gef.tutorial.step.command.ReconnectConnectionCommand;
//ide中对应类： GraphicalNodePolicy
public class CustomGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy
{

    @Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request)
	{
	    CreateConnectionCommand command = new CreateConnectionCommand();
	    command.setConnection(request.getNewObject());
	    command.setSource(getHost().getModel());
	    request.setStartCommand(command);
	    return command;
	}

	@Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest request)
    {
        CreateConnectionCommand command =(CreateConnectionCommand) request.getStartCommand();
        command.setTarget(getHost().getModel());
        return command;
    }


    @Override
    protected Command getReconnectSourceCommand(ReconnectRequest request)
    {
        ReconnectConnectionCommand command = new ReconnectConnectionCommand();
        command.setConnectionModel(request.getConnectionEditPart().getModel());
        command.setNewSource(getHost().getModel());
        return command;
    }

    @Override
    protected Command getReconnectTargetCommand(ReconnectRequest request)
    {
        ReconnectConnectionCommand command = new ReconnectConnectionCommand();
        command.setConnectionModel(request.getConnectionEditPart().getModel());
        command.setNewTarget(getHost().getModel());
        return command;
    }

}
