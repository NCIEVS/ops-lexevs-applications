LexEVSCompare
=============
A matching program to determine existing NCI PTs and codes for lexically equivalent candidate
terms (using a text list as input).  The algorithm uses a case insensitive exact match.

Requires Java 8.

Usage: LexEVSCompare [REQUIRED OPTIONS]

  -I, --input           path to input file
  -O, --output          path to output file
  -U, --url             LexEVS API url*
  --help                print this help
  
* Valid LexEVS urls
data-qa tier (VPN required)
https://lexevsapi65-data-qa.nci.nih.gov/lexevsapi65/

STAGE tier (VPN required)
https://lexevsapi65-stage.nci.nih.gov/lexevsapi65/

Production tier
https://lexevsapi65.nci.nih.gov/lexevsapi65/

Example ways to run.

C:\Apps\LexEVSCompare> LexEVSCompare.bat -I C:\path\to\input.txt -O C:\path\to\output.txt -U https://lexevsapi65-data-qa.nci.nih.gov/lexevsapi65/

C:\Apps\LexEVSCompare> LexEVSCompare.bat -I input.txt -O output.txt -u https://lexevsapi65-data-qa.nci.nih.gov/lexevsapi65/

C:\Users\wynner\Desktop\LexEVSCompare>java -jar ".\lib\CompareLexEVS-1.0-jar-with-dependencies.jar" -I input.txt -O output.txt -u https://lexevsapi65-data-qa.nci.nih.gov/lexevsapi65/
Server being tested: https://lexevsapi65-data-qa.nci.nih.gov/lexevsapi65/
50 of 699 processed.
100 of 699 processed.
150 of 699 processed.
200 of 699 processed.
250 of 699 processed.
300 of 699 processed.
350 of 699 processed.
400 of 699 processed.
450 of 699 processed.
500 of 699 processed.
550 of 699 processed.
600 of 699 processed.
650 of 699 processed.
699 of 699 processed.
Matching complete.
Finished compare in 54 seconds.

