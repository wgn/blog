package com.zhuani21.blog.util.integrity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CRC {
	static public int[] Table = new int[256];

	static {
		for (int i = 0; i < 256; i++) {
			int r = i;
			for (int j = 0; j < 8; j++)
				if ((r & 1) != 0)
					r = (r >>> 1) ^ 0xEDB88320;
				else
					r >>>= 1;
			Table[i] = r;
		}
	}

	int _value = -1;

	public void Init() {
		_value = -1;
	}

	public void Update(byte[] data, int offset, int size) {
		for (int i = 0; i < size; i++)
			_value = Table[(_value ^ data[offset + i]) & 0xFF] ^ (_value >>> 8);
	}

	public void Update(byte[] data) {
		int size = data.length;
		for (int i = 0; i < size; i++)
			_value = Table[(_value ^ data[i]) & 0xFF] ^ (_value >>> 8);
	}

	public void UpdateByte(int b) {
		_value = Table[(_value ^ b) & 0xFF] ^ (_value >>> 8);
	}

	public int GetDigest() {
		return _value ^ (-1);
	}

	public static int CalCRC(String strFile) {
		int nResult = 0;
		File file = new File(strFile);
		if (!file.exists()) {
			System.out.println("文件：" + strFile + "不存在！");
			return nResult;
		}
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			byte[] buff = new byte[1024];
			CRC crc = new CRC();
			crc.Init();
			while (true) {
				int nRead = fis.read(buff);
				if (nRead > 0)
					crc.Update(buff, 0, nRead);
				if (nRead < 1024)
					break;
			}
			nResult = crc.GetDigest();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return nResult;
	}

	// 10进制转16进制
	public static String IntToHex(int n, boolean bUseFlag) {
		char[] ch = new char[20];
		int nIndex = 0;
		while (true) {
			int m = n / 16;
			int k = n % 16;
			if (k == 15)
				ch[nIndex] = 'F';
			else if (k == 14)
				ch[nIndex] = 'E';
			else if (k == 13)
				ch[nIndex] = 'D';
			else if (k == 12)
				ch[nIndex] = 'C';
			else if (k == 11)
				ch[nIndex] = 'B';
			else if (k == 10)
				ch[nIndex] = 'A';
			else
				ch[nIndex] = (char) ('0' + k);
			nIndex++;
			if (m == 0)
				break;
			n = m;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(ch, 0, nIndex);
		sb.reverse();
		String strHex = null;
		if (bUseFlag)
			strHex = new String("0x");
		else
			strHex = new String();
		strHex += sb.toString();
		return strHex;
	}

}