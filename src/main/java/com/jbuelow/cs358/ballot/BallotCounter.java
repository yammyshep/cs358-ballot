package com.jbuelow.cs358.ballot;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class BallotCounter {
	
	public final static Logger log = LoggerFactory.getLogger(BallotCounter.class);

	public static void main(String[] args) {
		// Give the startup log
		log.info("\n\nRanked Ballot Election Counter\nby Jacob Buelow\n\nStarting...\n");
		
		String filename = null;
		
		// Parse command line arguments and gather filename of dataset
		if (Arrays.asList(args).indexOf("-i") >= 0) {
			//Interactive mode
			log.info("Interractive flag set");
			//TODO
		} else if (Arrays.asList(args).indexOf("-f") >= 0) {
			//File mode
			log.info("File mode flag set");
			try {
				filename = args[Arrays.asList(args).indexOf("-f") + 1];
			} catch (ArrayIndexOutOfBoundsException e) {
				log.error("No filename given! - exiting!");
				return;
			}
		} else {
			//No mode specified or given. quit and error
			log.error("File nor interractive mode specified. run with -i to enter interractive mode, or use -f <filename> - exiting!");
			return;
		}
		
		//edgecase that shouldnt happen if i did my job right
		if (filename == null) {
			log.error("Unable to parse filename! - exiting!");
			return;
		}
		
		
	}

}

