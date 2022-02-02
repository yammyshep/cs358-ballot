package com.jbuelow.cs358.ballot.data;

import java.util.List;

public record Voter(int id, List<Candidate> choices) { }
