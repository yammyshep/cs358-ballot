package com.jbuelow.cs358.ballot;

import java.util.List;

import com.jbuelow.cs358.ballot.data.Candidate;
import com.jbuelow.cs358.ballot.data.Voter;

public class Election {
	
	private final List<Voter> voters;
	
	public Election(List<Voter> voters) {
		this.voters = voters;
	}

}
