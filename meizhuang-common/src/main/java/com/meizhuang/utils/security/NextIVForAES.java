package com.meizhuang.utils.security;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class NextIVForAES {

	private byte keyTable[] = new byte[256];
	private static final int AES_KEYSIZE = 512;
	private long currTime;

	public NextIVForAES(long currTime) {
		this.currTime = currTime;
		this.initKeyTable();
	}

	private void initKeyTable() {
		try {
			byte longByte[] = longToByte(currTime);
			for (int i = 0; i < 256; i++) {
				keyTable[i] = (byte) (longByte[i % 8] + (byte) i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public byte[] getNewKey() {
		byte aesKey[] = new byte[NextIVForAES.AES_KEYSIZE];
		byte tmpKey[] = new byte[4];
		byte resultKey[] = new byte[4];
		nextIV(resultKey, 0, this.getKeyTable(), longToByte(this.currTime));
		for (int i = 0; i < (NextIVForAES.AES_KEYSIZE / 4); i++) {
			nextIV(resultKey, 0, this.getKeyTable(), tmpKey);
			System.arraycopy(resultKey, 0, aesKey, i * 4, 4);
			System.arraycopy(resultKey, 0, tmpKey, 0, 4);
		}
		return aesKey;
	}

	public static byte[] longToByte(long _input) {
		ByteArrayOutputStream bos = null;
		try {
			bos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(bos);
			dos.writeLong(_input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bos.toByteArray();
	}

	public byte[] getKeyTable() {
		return keyTable;
	}

	public void setKeyTable(byte[] keyTable) {
		this.keyTable = keyTable;
	}

	private static void nextIV(byte vector[], int v_index, byte values[], byte x[]) {
		byte localX[] = new byte[4];
		System.arraycopy(x, 0, localX, 0, 4);
		for (int i = 0; i < 4; i++) {
			byte a = localX[1];
			byte b = a;
			b = values[(int) converIntPostiveValue(b)];
			b -= vector[i];
			localX[0] += b;
			b = localX[2];
			b ^= values[(int) converIntPostiveValue(vector[v_index + i])];
			a -= b;
			localX[1] = a;
			a = localX[3];
			b = a;
			a -= localX[0];
			b = values[(int) converIntPostiveValue(b)];
			b += vector[v_index + i];
			b ^= localX[2];
			localX[2] = b;
			a += values[(int) converIntPostiveValue(vector[i])];
			localX[3] = a;
			long c = 0L;
			long d = 0L;
			for (int j = 0; j < 4; j++) {
				c = converIntPostiveValue(localX[0]) + converIntPostiveValue(localX[1]) * 256L + converIntPostiveValue(localX[2]) * 256L * 256L + converIntPostiveValue(localX[3]) * 256L * 256L * 256L;
				d = c;
			}
			c >>= 29;
			d = d << 3 & 0xffffffffL;
			c |= d;
			localX[0] = (byte) (int) (c % 256L);
			c /= 256L;
			localX[1] = (byte) (int) (c % 256L);
			c /= 256L;
			localX[2] = (byte) (int) (c % 256L);
			localX[3] = (byte) (int) (c / 256L);
		}
		for (int i = 0; i < 4; i++) {
			vector[v_index + i] = localX[i];
		}
	}

	public static long converIntPostiveValue(byte _input) {
		long result = _input & 0xff;
		return result;
	}

}
