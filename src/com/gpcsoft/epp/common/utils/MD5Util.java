package com.gpcsoft.epp.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	/**
	 */
	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	/**
	 * 从同步机制，改为并发机制
	 * @author dengxj 2011-9-5
	 */
	protected static MessageDigest getMessagedigest() {
		try {
			return MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsaex) {
			throw new IllegalArgumentException(nsaex);
		}
	}

	public static void main(String[] args) throws IOException {
		long begin = System.currentTimeMillis();

		File big = new File("E:/software/VMware/VMware-workstation-full-7.1.1-282343.rar");

		String md5 = getFileMD5String(big);

		long end = System.currentTimeMillis();
		System.out.println("md5:" + md5 + " time:" + ((end - begin) / 1000) + "s");
	}

	/**
	 * 计算文件的MD5
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getFileMD5String(File file) throws IOException {
		MessageDigest messagedigest = getMessagedigest();
		FileInputStream in = new FileInputStream(file);
		FileChannel ch = in.getChannel();
		ByteBuffer bb = ByteBuffer.allocate(4096);
		/*
		 * MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY,
		 * 0, file.length()); messagedigest.update(byteBuffer);
		 */
		while (ch.read(bb) != -1) {
			bb.flip();
			// ch.read(bb);
			messagedigest.update(bb);
			bb.clear();
		}

		String md5 = bufferToHex(messagedigest.digest());
		// byteBuffer.clear();
		System.gc();
		System.runFinalization();
		ch.close();
		in.close();
		return md5;
	}

	/**
	 * 计算指定文件片段的MD5
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getFileMD5String(File file, int start, int end) throws IOException {
		MessageDigest messagedigest = getMessagedigest();
		FileInputStream in = new FileInputStream(file);
		FileChannel ch = in.getChannel();
		MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, start, end - start);
		messagedigest.update(byteBuffer);
		String md5 = bufferToHex(messagedigest.digest());
		byteBuffer.clear();
		System.gc();
		System.runFinalization();
		ch.close();
		in.close();
		return md5;
	}

	/**
	 * 计算指定文件片段的MD5
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getFileMD5String(MappedByteBuffer byteBuffer) throws IOException {
		MessageDigest messagedigest = getMessagedigest();
		messagedigest.update(byteBuffer);
		String md5 = bufferToHex(messagedigest.digest());
		return md5;
	}

	public static String getMD5String(String s) {
		return getMD5String(s.getBytes());
	}

	public static String getMD5String(byte[] bytes) {
		MessageDigest messagedigest = getMessagedigest();
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}

	protected static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	public static boolean checkPassword(String password, String md5PwdStr) {
		String s = getMD5String(password);
		return s.equals(md5PwdStr);
	}

}
