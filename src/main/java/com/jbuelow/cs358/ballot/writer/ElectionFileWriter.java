package com.jbuelow.cs358.ballot.writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.jbuelow.cs358.ballot.Election;
import com.jbuelow.cs358.ballot.data.Candidate;

public class ElectionFileWriter {
	
	private final Election election;
	private final File file;
	
	public ElectionFileWriter(Election e, File f) {
		this.election = e;
		this.file = f;
	}
	
	public void write() throws IOException {
		FileWriter fw = new FileWriter(file);
		List<Map<Candidate, Integer>> results = election.getResults();
		
		fw.write("winner," + election.getWinner().name()+"\n");
		
		for (Map<Candidate, Integer> round : results) {
			fw.write("Round " + results.indexOf(round)+1 + ",\n");
			round.forEach((candidate, votes) -> {
				try {
					fw.write(candidate.name() + "," + votes + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		
		fw.close();
	}

}
