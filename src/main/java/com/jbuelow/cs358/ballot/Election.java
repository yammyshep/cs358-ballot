package com.jbuelow.cs358.ballot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jbuelow.cs358.ballot.data.Candidate;
import com.jbuelow.cs358.ballot.data.Voter;

public class Election {
	
	private final List<Voter> voters;
	private final Map<String, Candidate> candidates;
	
	public Election() {
		this(new ArrayList<Voter>());
	}
	
	public Election(List<Voter> voters) {
		this.voters = voters;
		this.candidates = new HashMap<String, Candidate>();
	}

	public void addVoters(List<Voter> voters) {
		for (Voter vote : voters) {
			// Consolidate candidate objects
			// Ensures that there is only one candidate object per candidate instead of 100s of identical objects
			List<Candidate> ch = vote.choices();			
			for (int i = 0; i < ch.size(); i++) {
				Candidate c = ch.get(i);
				if (this.candidates.containsKey(c.name().toUpperCase())) {
					ch.set(i, candidates.get(c.name().toUpperCase()));
				} else {
					candidates.put(c.name().toUpperCase(), c);
				}
			}
			
			this.voters.add(vote);
		}
	}
	
	public void compute() {
		List<Candidate> remaining = new ArrayList<>(candidates.values());
		
		for (int round = 0; round < 3; round++) {
			System.out.println();
			System.out.println("== Round " + (round+1) + " ==");
			if (remaining.size() <= 1) { break; }
			
			Map<Candidate, Integer> votes = new HashMap<>();
			remaining.forEach((candidate) -> {
				votes.put(candidate, 0);
			});
			
			for (Voter v : voters) {
				votes.compute(v.choices().get(round), (candidate, voteCount) -> {
					if (voteCount == null) { return null; }
					return voteCount + 1;
				});
			}
			
			votes.forEach((candidate, voteCount) -> {
				System.out.println(candidate.name() + ": " + voteCount);
			});
			
			//TODO: if majority (50%)
			
			if (round >= 2) {
				// Eliminate all but max
				final Candidate[] highestVotes = new Candidate[1]; // this needs to be final to use in lambda
				votes.forEach((candidate, voteCount) -> {
					if (highestVotes[0] == null || votes.get(highestVotes[0]) > voteCount) {
						remaining.remove(highestVotes[0]);
						highestVotes[0] = candidate;
					}
				});
			} else {
				// Eliminate 0 votes and lowest voted
				final Candidate[] lowestVotes = new Candidate[1]; // this needs to be final to use in lambda
				votes.forEach((candidate, voteCount) -> {
					if (voteCount == 0) {
						remaining.remove(candidate);
					} else {
						if (lowestVotes[0] == null || votes.get(lowestVotes[0]) > voteCount) {
							lowestVotes[0] = candidate;
						}
					}
				});
				remaining.remove(lowestVotes[0]);
			}
		}
	}

}
