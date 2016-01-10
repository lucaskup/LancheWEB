package com.lanche.utils;
import java.math.BigInteger;
import java.security.SecureRandom;

public final class TokenGen {
	private SecureRandom random = new SecureRandom();

	public String nextToken() {
	  return new BigInteger(260, random).toString(32);
	}
	
}
