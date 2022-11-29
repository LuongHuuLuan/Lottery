package load;

public class GetCode {
	public static char[] VIETNAMESE_ALPHABET = { 'a', 'ă', 'â', 'b', 'c', 'd', 'đ', 'e', 'ê', 'g', 'h', 'i', 'k', 'l',
			'm', 'n', 'o', 'ô', 'ơ', 'p', 'q', 'r', 's', 't', 'u', 'ư', 'v', 'x', 'y', '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9' };
	public static char[] UNIKEY_A = { 'á', 'à', 'ả', 'ã', 'ạ', 'ă', 'ắ', 'ằ', 'ẳ', 'ẵ', 'ặ', 'â', 'ấ', 'ầ', 'ẩ', 'ẫ',
			'ậ' };
	public static char[] UNIKEY_E = { 'é', 'è', 'ẻ', 'ẽ', 'ẹ', 'ê', 'ề', 'ế', 'ể', 'ễ', 'ệ' };
	public static char[] UNIKEY_I = { 'í', 'ì', 'ỉ', 'ĩ', 'ị' };
	public static char[] UNIKEY_O = { 'ó', 'ò', 'ỏ', 'õ', 'ọ', 'ơ', 'ớ', 'ờ', 'ở', 'ỡ', 'ợ', 'ô', 'ố', 'ồ', 'ổ', 'ỗ',
			'ộ' };
	public static char[] UNIKEY_U = { 'ú', 'ù', 'ủ', 'ũ', 'ụ', 'ư', 'ứ', 'ừ', 'ử', 'ữ', 'ự' };

	public static String filterInput(String input) {
		String result = input;
		result = result.toLowerCase();
		// a
		for (int i = 0; i < UNIKEY_A.length; i++) {
			result = result.replaceAll(UNIKEY_A[i] + "", "a");
		}
		// e
		for (int i = 0; i < UNIKEY_E.length; i++) {
			result = result.replaceAll(UNIKEY_E[i] + "", "e");
		}
		// i
		for (int i = 0; i < UNIKEY_I.length; i++) {
			result = result.replaceAll(UNIKEY_I[i] + "", "i");
		}
		// o
		for (int i = 0; i < UNIKEY_O.length; i++) {
			result = result.replaceAll(UNIKEY_O[i] + "", "o");
		}
		// u
		for (int i = 0; i < UNIKEY_U.length; i++) {
			result = result.replaceAll(UNIKEY_U[i] + "", "u");
		}
		result = result.replaceAll("đ", "d");
		return result;
	}

	public static boolean include(char c) {
		for (int i = 0; i < VIETNAMESE_ALPHABET.length; i++) {
			if (VIETNAMESE_ALPHABET[i] == c) {
				return true;
			}
		}
		return false;
	}

	public static String getCode(String input) {
		String filterInput = filterInput(input).trim();
		char[] inputChars = filterInput.toCharArray();
		String result = "";
		for (char c : inputChars) {
			if (!include(c)) {
				result += "-";
			} else {
				result += c;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(GetCode.getCode("xổ xố đại phát").replaceAll("-", ""));
	}
}
