package splett.criptografia;

public class Conversion
{
	private static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public static String byteToHexString(byte b)
	{
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String byteArrayToHexString(byte[] b)
	{
		String result = "";
		for (int i = 0; i < b.length; ++i)
			result += byteToHexString(b[i]);
		return result;
	}

	public static String byteArrayToBase64String(byte[] b, int len)
	{
		String s = "";
		int n = len / 3;
		int m = len % 3;
		for (int i = 0; i < n; ++i)
		{
			int j = i * 3;
			s += toBase64(b[j], b[j + 1], b[j + 2]);
		}
		if (m == 1)
			s += toBase64(b[len - 1]);
		else if (m == 2)
			s += toBase64(b[len - 2], b[len - 1]);
		String result = "";
		len = s.length();
		n = len / 64;
		m = len % 64;
		for (int i = 0; i < n; ++i)
		{
			result += s.substring(i * 64, (i + 1) * 64) + "\n";
		}
		if (m > 0)
			result += s.substring(n * 64, len) + "\n";
		return result;
	}

	public static String byteArrayToBase64String(byte[] b)
	{
		return byteArrayToBase64String(b, b.length);
	}

	private static String toBase64(byte b1, byte b2, byte b3)
	{
		int[] digit = new int[4];
		digit[0] = (b1 & 0xFC) >>> 2;
		digit[1] = (b1 & 0x03) << 4;
		digit[1] |= (b2 & 0xF0) >> 4;
		digit[2] = (b2 & 0x0F) << 2;
		digit[2] |= (b3 & 0xC0) >> 6;
		digit[3] = (b3 & 0x3F);
		String result = "";
		for (int i = 0; i < digit.length; ++i)
			result += base64Digit(digit[i]);
		return result;
	}

	private static String toBase64(byte b1, byte b2)
	{
		int[] digit = new int[3];
		digit[0] = (b1 & 0xFC) >>> 2;
		digit[1] = (b1 & 0x03) << 4;
		digit[1] |= (b2 & 0xF0) >> 4;
		digit[2] = (b2 & 0x0F) << 2;
		String result = "";
		for (int i = 0; i < digit.length; ++i)
			result += base64Digit(digit[i]);
		result += "=";
		return result;
	}

	private static String toBase64(byte b1)
	{
		int[] digit = new int[2];
		digit[0] = (b1 & 0xFC) >>> 2;
		digit[1] = (b1 & 0x03) << 4;
		String result = "";
		for (int i = 0; i < digit.length; ++i)
			result += base64Digit(digit[i]);
		result += "==";
		return result;
	}

	private static char base64Digit(int i)
	{
		if (i < 26)
			return (char) ('A' + i);
		if (i < 52)
			return (char) ('a' + (i - 26));
		if (i < 62)
			return (char) ('0' + (i - 52));
		if (i == 62)
			return '+';
		else
			return '/';
	}
}