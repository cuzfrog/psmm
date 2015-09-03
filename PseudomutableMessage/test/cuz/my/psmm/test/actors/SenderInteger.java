package cuz.my.psmm.test.actors;

class SenderInteger extends Sender {
	@Override
	public void onReceive(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		if (arg0 instanceof Parcel && working) {

			@SuppressWarnings("unchecked")
			Parcel<Integer> receivedVp = (Parcel<Integer>) arg0;
			if (receivedVp.verify()) {
				Parcel<Integer> vp = randomGenerateMessage(receivedVp);
				randomSend(vp);

			} else {
				context().actorSelection("../listener").tell(receivedVp,
						getSender()); // when
										// error
			}
		} else if (arg0 instanceof SharedReadOnlyData) {
			dataSource = (SharedReadOnlyData) arg0; // when initiating
			module=dataSource.getModule();
			working = true;
		} else if (arg0 instanceof InstructionStop) {
			working = false;
			context().actorSelection("../listener").tell(
					new InstructionStopConfirmed(), getSender());
			// terminate self:
		} else {
			unhandled(arg0);
		}
	}
}
