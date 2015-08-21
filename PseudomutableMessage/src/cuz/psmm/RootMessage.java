package cuz.psmm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


public class RootMessage implements Message {
	
	private static RootMessage instance=new RootMessage();
	private static volatile AtomicLong messageCount=new AtomicLong(0);
	private static volatile AtomicInteger factoryCount=new AtomicInteger(0);
	private static volatile List<PsmmFactory> factoryEntries=new ArrayList<>();
	
	private RootMessage(){}
	
	//-------------------utility behavior:
	static RootMessage getInstance(){
		return instance;
	}

	static Integer storeFactory(PsmmFactory psmmFactory){
		factoryEntries.add(psmmFactory);
		return factoryCount.incrementAndGet();
	}
	
	static PsmmFactory seekFactory(Integer id){
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
	public PsmmFactory set(String valueName, String datum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PsmmFactory set(String valueName, int datum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PsmmFactory set(String valueName, short datum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PsmmFactory set(String valueName, long datum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PsmmFactory set(String valueName, byte datum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PsmmFactory set(String valueName, float datum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PsmmFactory set(String valueName, double datum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PsmmFactory setObject(String valueName, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}
