package com.jbuelow.cs358.ballot.parser;

import java.util.List;

import com.jbuelow.cs358.ballot.Election;
import com.jbuelow.cs358.ballot.data.Voter;

public interface Parser {

	Election parseElection();
	List<Voter> parseVoters();
	
}
