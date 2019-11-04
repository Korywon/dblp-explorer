
# Design Document

## Summary

The program is mainly designed to work with JSON files in the dblp format. In particular, this software is geared more towards using dblp papers version 11 (e.g. dblp_papers_v11.txt).

## Parsing

Each line inside of the text file is read in through Java Stream which calls a function to parse the line into a JSON object. For tier one, the titles are matched against a regular expression to check to see if the title contains or has similar wording to the keyword.

For tier two and tier three papers, it retrieves the references from the previous papers and adds them into their respective tiers.
