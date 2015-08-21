package cuz.psmm;

import java.util.Map;

public class CommonPsmmFactory extends AbstractPsmmFactory {

	@Override
	protected Message createMessage(Message messageBeingWrapped,
			Map<String, Object> data) {
		// TODO Auto-generated method stub
		return new CommonMessage(messageBeingWrapped, data);
	}

}
