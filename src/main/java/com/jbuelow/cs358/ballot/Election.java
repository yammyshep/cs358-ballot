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
	
	private final List<Map<Candidate, Integer>> resultVotes;
	private Candidate resultWinner;
	
	public Election() {
		this(new ArrayList<Voter>());
	}
	
	public Election(List<Voter> voters) {
		this.voters = voters;
		this.candidates = new HashMap<String, Candidate>();
		this.resultVotes = new ArrayList<Map<Candidate, Integer>>();
	}
	
	public Candidate getWinner() {
		if (resultWinner != null) {
			return resultWinner;
		} else {
			throw new IllegalStateException("Election must be computed first.");
		}
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
			if (remaining.size() <= 1) {
				resultWinner = remaining.get(0);
				break;
			}
			
			Map<Candidate, Integer> votes = new HashMap<>();
			remaining.forEach((candidate) -> {
				votes.put(candidate, 0);
			});
			
			final int[] totalVotesThisRound = new int[1]; // lambda below can only work with final values...
			for (Voter v : voters) {
				for (int i = 0; i < round+1; i++) {
					if (remaining.contains(v.choices().get(i))) {
						totalVotesThisRound[0]++;
						int cvotes = votes.get(v.choices().get(i));
						votes.put(v.choices().get(i), cvotes + 1);
						break;
					}
				}
			}
			
			resultVotes.add(round, votes);
			
			votes.forEach((candidate, voteCount) -> {
				System.out.println(candidate.name() + ": " + voteCount);
			});
			
			votes.forEach((candidate, voteCount) -> {
				if (voteCount >= (totalVotesThisRound[0] / 2)) {
					resultWinner = candidate;
					System.out.println("Candidate won by majority vote: " + candidate.name());
					return;
				}
			});			
			
			if (round >= 2) {
				// Eliminate all but max
				final Candidate[] highestVotes = new Candidate[1]; // this needs to be final to use in lambda
				votes.forEach((candidate, voteCount) -> {
					if (highestVotes[0] == null || votes.get(highestVotes[0]) > voteCount) {
						remaining.remove(highestVotes[0]);
						highestVotes[0] = candidate;
						resultWinner = candidate;
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
