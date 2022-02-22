package com.jbuelow.cs358.ballot;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import com.jbuelow.cs358.ballot.data.Voter;
import com.jbuelow.cs358.ballot.parser.Parser;
import com.jbuelow.cs358.ballot.parser.ParserFactory;
import com.jbuelow.cs358.ballot.ui.ConsoleInterface;

/**
 * Main class, handles running and cli argument  parsing
 *
 */
class BallotCounter {

	public static void main(String[] args) {		
		// Give the startup message
		System.out.println("Runoff Election Ballot Counter");
		System.out.println();
		
		// if arguments are present, dont load the interactive prompt and just attempt to calculate results
		if (args.length > 0) {
			System.out.println("Arguments are present, running in non-interactive mode.");
			
			boolean electionValid = true;
			Election election = new Election();
			
			// Import each cli argument as a file
			for (String argument : args) {
				System.out.println("Importing " + argument + "...");
				try {
					Parser p = ParserFactory.getParserByFileExtension(argument);
					List<Voter> voters = p.parseVoters();
					election.addVoters(voters);
					System.out.println("    > " + voters.size() + " votes imported.");
				} catch (FileNotFoundException e) {
					System.out.println("WARNING: Could not find file on disk: " + argument);
					electionValid = false;
				} catch (UnsupportedOperationException e) {
					System.out.println("WARNING: No parser implemented for file: " + argument);
					electionValid = false;
				}
			}
			
			// Compute election
			System.out.println("Computing election...");
			election.compute();
			
			if (!electionValid) {
				System.out.println();
				System.out.println("=====  WARNING: There were errors in calculating this election!   =====");
				System.out.println("===== Check the console for errors and retry before using results =====");
			}
		} else {
			// No arguments were given, simply hand off to the interactive console prompt
			ConsoleInterface ui = new ConsoleInterface();
			ui.start();
		}
		
	}

}

;