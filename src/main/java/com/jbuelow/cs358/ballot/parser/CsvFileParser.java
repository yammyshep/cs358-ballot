package com.jbuelow.cs358.ballot.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jbuelow.cs358.ballot.Election;
import com.jbuelow.cs358.ballot.data.Candidate;
import com.jbuelow.cs358.ballot.data.Voter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

/**
 * Parses CSV formatted files into a list of voter objects
 *
 */
public class CsvFileParser extends FileParser {

	public CsvFileParser(String filename) {
		super(filename);
	}

	@Override
	public Election parseElection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Voter> parseVoters() {
		List<Voter> voters = new ArrayList<Voter>();
		
		CSVReader r;
		try {
			r = new CSVReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return voters;
		}
		
		try {
			int lineNum = 0;
			String[] line;
			while ((line = r.readNext()) != null) {
				int tokenNum = 0;
				int id = 0;
				List<Candidate> choices = new ArrayList<Candidate>();
				for (String token : line) {
					if (lineNum == 0) { continue; }
					
					if (tokenNum == 0) {
						id = Integer.parseInt(token);
					} else {
						choices.add(new Candidate(token));
					}
					
					tokenNum++;
				}
				
				if (lineNum != 0) {
					voters.add(new Voter(id, choices));
				}
				lineNum++;
			}
		} catch (CsvValidationException | IOException e) {
			e.printStackTrace();
			return new ArrayList<Voter>();
		}
			
		return voters;
	}

}
