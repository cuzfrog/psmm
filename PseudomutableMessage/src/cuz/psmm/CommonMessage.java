package cuz.psmm;

import java.util.Map;

public class CommonMessage extends AbstractMessage {

	CommonMessage(Message parent, Map<String, Object> data) {
		super(Message.Type.COMMON, parent, data);
		// TODO Auto-generated constructor stub
	}

}
