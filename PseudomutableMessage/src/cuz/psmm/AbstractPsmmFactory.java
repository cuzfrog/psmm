package cuz.psmm;

import java.util.HashMap;
import java.util.Map;

import cuz.psmm.exceptions.PsmmFactoryNotClosedException;
import cuz.psmm.exceptions.PsmmFactoryNotOpenException;
import cuz.psmm.exceptions.PsmmUnsupportedOperationException;

abstract class  AbstractPsmmFactory implements PsmmFactory, RawMessage {

	private Map<String,Object> data;
	private Message messageBeingWrapped;
	private State state=State.CLOSED;
	
	private enum State{
		CLOSED,OPEN;
	}
	private void checkClosed() {
		if(state!=State.CLOSED) throw new PsmmFactoryNotClosedException();
	}
	private void checkOpen() {
		if(state!=State.OPEN) throw new PsmmFactoryNotOpenException();
	}
	
	
	@Override
	public PsmmFactory wrap(Message message)  {
		// TODO Auto-generated method stub
		checkClosed();
		this.state=State.OPEN;
		messageBeingWrapped=message;
		data=new HashMap<>();
		return this;
	}
	public PsmmFactory wrap(){
		return wrap(RootMessage.getInstance());
	}

	private void checkedSet(String valueName, Object datum) {
		checkOpen();
		
		data.put(valueName, datum);
	}
	@Override
	public RawMessage set(String valueName, String datum)  {
		// TODO Auto-generated method stub
		checkedSet(valueName, datum);
		return this;
	}


	@Override
	public RawMessage set(String valueName, int datum)  {
		// TODO Auto-generated method stub
		checkedSet(valueName, datum);
		return this;
	}

	@Override
	public RawMessage set(String valueName, short datum)  {
		// TODO Auto-generated method stub
		checkedSet(valueName, datum);
		return this;
	}

	@Override
	public RawMessage set(String valueName, long datum)  {
		// TODO Auto-generated method stub
		checkedSet(valueName, datum);
		return this;
	}

	@Override
	public RawMessage set(String valueName, byte datum)  {
		// TODO Auto-generated method stub
		checkedSet(valueName, datum);
		return this;
	}

	@Override
	public RawMessage set(String valueName, float datum)  {
		// TODO Auto-generated method stub
		checkedSet(valueName, datum);
		return this;
	}

	@Override
	public RawMessage set(String valueName, double datum)  {
		// TODO Auto-generated method stub
		checkedSet(valueName, datum);
		return this;
	}

	@Override
	public RawMessage setObject(String valueName, Object object)   {
		// TODO Auto-generated method stub
		throw new PsmmUnsupportedOperationException();
	}

	abstract protected Message createMessage(Message messageBeingModified,Map<String,Object> data);
	
	@Override
	public Message done()  {
		// TODO Auto-generated method stub
		checkOpen();
		Message newMessage=this.createMessage(messageBeingWrapped, data);
		data=null;
		messageBeingWrapped=null;
		state=State.CLOSED;
		return newMessage;
	}

}
