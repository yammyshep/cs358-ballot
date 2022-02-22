package com.jbuelow.cs358.ballot.data;

import java.util.List;

/**
 * Data record to represent a voter, with its id and list of choices
 *
 */
public record Voter(int id, List<Candidate> choices) { }
