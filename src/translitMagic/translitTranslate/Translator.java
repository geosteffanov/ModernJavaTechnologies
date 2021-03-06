package translitMagic.translitTranslate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Translator {
	private Map<Character, Character> symbolTable;

	private void setUp(BufferedReader reader) {
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				String[] pair = line.split(":");
				symbolTable.put(pair[0].charAt(0), pair[1].charAt(0));
			}

			symbolTable.put(' ', ' ');
			// symbolTable.put(System.lineSeparator().charAt(0),
			// System.lineSeparator().charAt(0));
			symbolTable.put('\n', '\n');
			symbolTable.put('\r', '\r');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Character translate(Character key) {
		return symbolTable.get(key);
	}

	public char[] translate(char[] keys) {
		char[] result = new char[keys.length];
		for (int i = 0; i < keys.length; i++) {
			System.out.println("KEY:: " + keys[i]);
			result[i] = translate(keys[i]);
		}
		return result;
	}

	public boolean correctCharacter(Character key) {
		return symbolTable.containsKey(key);
	}

	public Translator(File configFile) {
		try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
			symbolTable = new LinkedHashMap<Character, Character>();
			setUp(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String tableString() {
		StringBuilder result = new StringBuilder();
		Set<Character> keys = symbolTable.keySet();
		for (Character key : keys) {
			result.append("\n" + key + ":" + symbolTable.get(key));
		}

		return result.toString();
	}

	public static void main(String args[]) {
		File file = new File("src/translitMagic/translitTranslate/latin-ciryllic.config");
		Translator translater = new Translator(file);
		System.out.println(translater.tableString());
	}

}
