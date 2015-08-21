package cuz.psmm;

import java.util.HashMap;
import java.util.Map;

import cuz.psmm.utility.ValuePathParser;

abstract class AbstractMessage implements Message {

	private final Message parent;
	private final Map<String, Object> data;
	private final Integer depth;
	private final Message.Type type;

	protected AbstractMessage(Message.Type type,Message parent, Map<String, Object> data) {
		super();
		this.parent = parent;
		this.data = data;
		this.depth = parent.depth() + 1;
		this.type=type;
	}

	private Object simpleGet(String valueName){
		Object result = null;
		if ((result = data.get(valueName)) == null) {
			result = parent.get(valueName);
		}
		return result;
	}
	private Object mapGet(){
		Map<String, Object> result=new HashMap<>(data.size());
		result.putAll(this.data);
		return result;
	}
	
	@Override
	public Object get(String valuePath) {
		// TODO Auto-generated method stub
		ValuePathParser.validate(valuePath);
		return null;
	}

	@Override
	public Integer depth() {
		// TODO Auto-generated method stub
		return depth;
	}

	
	
	//------------factory behaviors:
	private PsmmFactory getFactory(Message.Type type){
		
		return PsmmFactory.seekFactory(type,this);
	}
	
	@Override
	public RawMessage set(String valueName, String datum) {
		// TODO Auto-generated method stub
		return getFactory(type).set(valueName, datum);
	}

	@Override
	public RawMessage set(String valueName, int datum) {
		// TODO Auto-generated method stub
		return getFactory(type).set(valueName, datum);
	}

	@Override
	public RawMessage set(String valueName, short datum) {
		// TODO Auto-generated method stub
		return getFactory(type).set(valueName, datum);
	}

	@Override
	public RawMessage set(String valueName, long datum) {
		// TODO Auto-generated method stub
		return getFactory(type).set(valueName, datum);
	}

	@Override
	public RawMessage set(String valueName, byte datum) {
		// TODO Auto-generated method stub
		return getFactory(type).set(valueName, datum);
	}

	@Override
	public RawMessage set(String valueName, float datum) {
		// TODO Auto-generated method stub
		return getFactory(type).set(valueName, datum);
	}

	@Override
	public RawMessage set(String valueName, double datum) {
		// TODO Auto-generated method stub
		return getFactory(type).set(valueName, datum);
	}

	@Override
	public RawMessage setObject(String valueName, Object object) {
		// TODO Auto-generated method stub
		return getFactory(type).setObject(valueName, object);
	}

}
