package com.jbuelow.cs358.ballot.parser;

import java.io.File;

public abstract class FileParser implements Parser {
	
	protected final File file;
	
	public FileParser(String filename) {
		this.file = new File(filename);
	}

}
