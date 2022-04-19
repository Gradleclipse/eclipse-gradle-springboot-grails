package en.example.util;

public class StringsUtils {
	
	/**
	 * @param array
	 * @return a string representation of the array
	 */
	public static String toString(String[] array) {
		if ( array == null ) { return "null"; }
		int arrayLength = array.length;
		if ( arrayLength == 0 ) { return "{}"; }
		StringBuffer buf = new StringBuffer( "{ " );
		for ( int i=0; i < arrayLength; i++ ) {
			buf.append(array[i]).append(", ");
		}
		String result = buf.toString();
		int resultLength = result.length();
		result = result.substring(0, resultLength-2);
		result = result + " }";
		return result;
	}

}
