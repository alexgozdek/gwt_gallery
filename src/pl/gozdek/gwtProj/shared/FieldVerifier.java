package pl.gozdek.gwtProj.shared;

public class FieldVerifier {

	public static boolean isValidName(String name) {
		if (name == null) {
			return false;
		}
		return name.length() > 3;
	}
}
