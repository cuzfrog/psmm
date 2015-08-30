package cuz.my.psmm;

import java.util.Arrays;

public class SHA1Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] B1=new byte[]{Integer.valueOf(0).byteValue()};
		byte[] B2=new byte[]{Integer.valueOf(0).byteValue()};
		Signature s1=new Signature(B1);
		Signature s2=new Signature(B2);
		System.out.println(s1.equals(s2));
	}

}
