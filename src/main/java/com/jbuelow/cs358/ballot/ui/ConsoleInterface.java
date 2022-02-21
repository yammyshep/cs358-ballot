package com.jbuelow.cs358.ballot.ui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.jbuelow.cs358.ballot.Election;
import com.jbuelow.cs358.ballot.data.Voter;
import com.jbuelow.cs358.ballot.parser.Parser;
import com.jbuelow.cs358.ballot.parser.ParserFactory;

public class ConsoleInterface {
	
	public void start() {
		System.out.println("Running in interactive mode.\n");
		
		boolean running = true;
		while (running) {
			Election election = new Election();
			
			boolean fileSelecting = true;
			while (fileSelecting) {
				System.out.print("Selecting files. Choose option [(A)dd file, (C)ompute Election, (E)xit]: ");
		        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		        String line;
		        try {
					line = reader.readLine();
				} catch (IOException e) {
					e.printStackTrace();
					continue;
				}
		        
		        char letter = line.toLowerCase().charAt(0);
		        switch (letter) {
		        case 'a':
		        	System.out.print("Type filename of ballot: ");
			        try {
						line = reader.readLine();
					} catch (IOException e) {
						e.printStackTrace();
						continue;
					}			        
			        System.out.println("Importing " + line + "...");
					try {
						Parser p = ParserFactory.getParserByFileExtension(line);
						List<Voter> voters = p.parseVoters();
						election.addVoters(voters);
						System.out.println("    > " + voters.size() + " votes imported.");
					} catch (FileNotFoundException e) {
						System.out.println("WARNING: Could not find file on disk: " + line);
					} catch (UnsupportedOperationException e) {
						System.out.println("WARNING: No parser implemented for file: " + line);
					}
		        	break;
		        case 'c':
		        	fileSelecting = false;
		        	break;
		        case 'e':
		        	return;
		        default:
		        	System.out.println("Unrecognised option '"+letter+"'. Try again.");
		        }
			}
			
			System.out.println("Computing election...");
			election.compute();
			System.out.println();
			
			boolean postCompute = true;
			while (postCompute) {
				System.out.print("Post-Compute. Choose option [Show (W)inner, (S)ave to disk, (N)ew Election, (E)xit]: ");
		        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		        String line;
		        try {
					line = reader.readLine();
				} catch (IOException e) {
					e.printStackTrace();
					continue;
				}
		        
		        char letter = line.toLowerCase().charAt(0);
		        switch (letter) {
		        case 'w':
		        	System.out.println("Winning candidate: " + election.getWinner().name());
		        	break;
		        case 's':
		        	break;
		        case 'n':
		        	postCompute = false;
		        	break;
		        case 'e':
		        	return;
		        default:
		        	System.out.println("Unrecognised option '"+letter+"'. Try again.");
		        }
			}
		}
	}

}
