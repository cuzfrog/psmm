package cuz.my.psmm;

import java.util.Arrays;

import cuz.my.psmm.accessories.StateLess;

@StateLess
final class Signature {
	private final byte[] signature;

	Signature(byte[] signature) {
		super();
		this.signature = signature;
	}

	/**
	 * @return the signature
	 */
	byte[] getSignature() {
		return signature;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(signature);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Signature other = (Signature) obj;
		if (!Arrays.equals(signature, other.signature))
			return false;
		return true;
	}

}
