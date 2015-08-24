package cuz.psmm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


@SuppressWarnings("rawtypes")
public class RootMessage implements Message {
	
	private static RootMessage instance=new RootMessage();
	private static volatile AtomicLong messageCount=new AtomicLong(0);
	private static volatile AtomicInteger factoryCount=new AtomicInteger(0);
	private static volatile List<PsmmFactory<?>> factoryEntries=new ArrayList<>();
	
	private RootMessage(){}
	
	//-------------------utility behavior:
	public static RootMessage getInstance(){
		messageCount.incrementAndGet();
		return instance;
	}

	static Integer storeFactory(PsmmFactory<?> psmmFactory){
		factoryEntries.add(psmmFactory);
		return factoryCount.incrementAndGet();
	}
	
	static PsmmFactory<?> seekFactory(Integer id){
		return factoryEntries.get(id);
	}
	
	//-------------------message behavior:
	@Override
	public Object get(String valuePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer depth() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public byte[] getSignature() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String,Object> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	//factory behaviors:

	@Override
	public RawMessage<Object> set(String key, Object datum) {
		// TODO Auto-generated method stub
		return null;
	}


}
