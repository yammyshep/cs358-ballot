package com.jbuelow.cs358.ballot.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserFactory {
	
	private static Pattern extensionPattern = Pattern.compile("([^.]+)$");
	
	private enum FileType {
		CSV
	}

	public static Parser getParserByFileExtension(String filename) throws FileNotFoundException, UnsupportedOperationException {
		File f = new File(filename);
		if (!f.canRead()) {
			throw new FileNotFoundException();
		}
		
		Matcher m = extensionPattern.matcher(filename);
		if (m.find()) {
			FileType ft;
			try {
				ft = FileType.valueOf(m.group(0).toUpperCase());
			} catch (IllegalArgumentException e) {
				throw new UnsupportedOperationException();
			}
			
			switch (ft) {
			case CSV:
				return new CsvFileParser(filename);
			default:
				throw new UnsupportedOperationException();
			}
		} else {
			throw new UnsupportedOperationException("Could not determine filetype");
		}
	}
	
}
