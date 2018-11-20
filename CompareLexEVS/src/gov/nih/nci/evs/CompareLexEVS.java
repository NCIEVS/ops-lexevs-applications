// Rob Wynne, MSC

package gov.nih.nci.evs;

import java.io.*;
import java.util.*;

import org.LexGrid.LexBIG.DataModel.Collections.LocalNameList;
import org.LexGrid.LexBIG.DataModel.Collections.ResolvedConceptReferenceList;
import org.LexGrid.LexBIG.DataModel.Core.ResolvedConceptReference;
import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.LexBIG.Exceptions.LBInvocationException;
import org.LexGrid.LexBIG.Exceptions.LBParameterException;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeSet;
import org.LexGrid.LexBIG.Utility.LBConstants;
import org.LexGrid.LexBIG.caCore.interfaces.LexEVSService;
import org.LexGrid.concepts.Entity;
import org.LexGrid.concepts.Presentation;

public class CompareLexEVS {
	
	static LexEVSService lbSvc;
	String serviceUrl = null;
	Vector<String> allInput = new Vector<String>();
	PrintWriter pw = null;
	boolean includeRetired = false;
	String matchAlgorithm = "exact";
	
	public static void main (String[] args) {
		CompareLexEVS compare = new CompareLexEVS();
		long start = System.currentTimeMillis();
		compare.configure(args);
		compare.runCompare();
		System.out.println("Finished compare in "
				+ (System.currentTimeMillis() - start) / 1000 + " seconds.");
	}
	
	public void configure (String args[]) {
		if( args.length > 0 ) {
//			lbSvc = RemoteServerUtil.createLexEVSService(args[0]);
//			setVectorFromFile(matchFile);

			boolean urlSet = false;
			boolean inputSet = false;
			boolean outputSet = false;
			for (int i = 0; i < args.length; i++) {
				String option = args[i];
				if (option.equalsIgnoreCase("--help")) {
					printHelp();
				}
				else if (option.equalsIgnoreCase("-U")
				        || option.equalsIgnoreCase("--url")) {
					lbSvc = RemoteServerUtil.createLexEVSService(args[++i]);
					urlSet = true;
				}
				else if (option.equalsIgnoreCase("-I")
				        || option.equalsIgnoreCase("--input")) {
					setVectorFromFile(args[++i]);
					inputSet = true;
				}
				else if (option.equalsIgnoreCase("-O")
				        || option.equalsIgnoreCase("--output")) {
					setOutputFile(args[++i]);
					outputSet = true;
				}
				else if(option.equalsIgnoreCase("-R")
						|| option.equalsIgnoreCase("--includeRetired")) {
					includeRetired = true;
				}
				else if(option.equalsIgnoreCase("-C")
						|| option.equalsIgnoreCase("--contains")) {
					matchAlgorithm = "contains";
				}
				else {
					System.out.println("Unrecognized option: " + args[i]);
					printHelp();
				}
			}			
			
		}
		else {
			System.out.println("No arguments provided. Exiting.");
			printHelp();
			System.exit(0);
		}
	}
	
	public void setOutputFile( String filename ) {
		try {
			File fil = new File(filename);
			pw = new PrintWriter(fil);
		} catch(Exception e) {
			System.out.println("Couldn't create output file.");
			System.exit(0);
		}		
	}
	
	public void printHelp() {
		System.out.println("");
		System.out
		        .println("Usage: LexEVSCompare [REQUIRED OPTIONS]");
		System.out.println(" ");
		System.out.println("  -I, --input\t\tpath to input file");
		System.out.println("  -O, --output\t\tpath to output file");
		System.out.println("  -U, --url\t\tLexEVS API url");
		System.out.println("  --help\t\tprint this help");
 		//more to come maybe
		System.exit(1);
	}
	
	
	
	public void runCompare() {
		try {
//			String searchTerm; 
			int codescount = 0;	
			int matchedTerms = 0;
			int matchProgress = 0;
			for(String searchTerm : allInput ) {
				matchProgress++;
				if( matchProgress % 50 == 0 ) {
					System.out.println(matchProgress + " of " + allInput.size() + " processed.");
				}
				
				CodedNodeSet nodeSet = lbSvc.getNodeSet("NCI_Thesaurus", null, null);

				//Tell the api that you want to search only the PRESENTATION type properties                  
				CodedNodeSet.PropertyType[] types = new CodedNodeSet.PropertyType[1];               
				types[0] = CodedNodeSet.PropertyType.PRESENTATION;

				//Only want to search properties with the property name of "FULL_SYN"
				LocalNameList propLnL = new LocalNameList();
				propLnL.addEntry("FULL_SYN");
				
//				System.out.print(searchTerm);
				if( matchAlgorithm.equals("exact")) {
					nodeSet = nodeSet.restrictToMatchingProperties(propLnL,types,null,null, null,searchTerm,LBConstants.MatchAlgorithms.exactMatch.name(),null);
				}
				else if( matchAlgorithm.equals("contains")) {
					nodeSet = nodeSet.restrictToMatchingProperties(propLnL,types,null,null, null,searchTerm,LBConstants.MatchAlgorithms.contains.name(),null);
				}
	
				ResolvedConceptReferenceList rcl = nodeSet.resolveToList(null, null, null, 100);
				int count = rcl.getResolvedConceptReferenceCount();

	
				//Now iterate through the returned entities and display the FULL_SYN PT property with source=CAHUB
				pw.print(searchTerm);
				boolean match = false;				
				for (int i=0; i < rcl.getResolvedConceptReferenceCount(); i++){
					ResolvedConceptReference rcr = rcl.getResolvedConceptReference(i);
					Entity entity = rcr.getReferencedEntry();
					boolean isActive = entity.getIsActive();
					boolean getMatches = true;
					if( !isActive && !includeRetired ) {
						getMatches = false;
					}
					Presentation[] presProps = entity.getPresentation();
//					boolean ctepExists = false;
					String preferredTerm = "";
					String conceptCode = "";
					if( getMatches ) {
						for(int y=0;y<presProps.length;y++){
							Presentation pres = presProps[y];
							if(pres.getPropertyName().equals("FULL_SYN")) {
								preferredTerm = entity.getEntityDescription().getContent();
								conceptCode = entity.getEntityCode();
								//System.out.print("\t" + pres.getValue().getContent() + "\t" + entity.getEntityCode()); 
								codescount++;
								match = true;
							}
	//						if(pres.getPropertyName().equals("FULL_SYN") && (pres.getRepresentationalForm().equals("PT")) && pres.getSource(0).getContent().equals("CTEP")) {
	//							ctepExists = true;
	//						}
						}
					}
//					System.out.print("\t" + String.valueOf(ctepExists) + "\t" + preferredTerm + "\t" + conceptCode );
					pw.print("\t" + preferredTerm + "\t" + conceptCode );
				}
				pw.print("\n");
				pw.flush();
				if(match) {
					matchedTerms++;
				}
			}
			System.out.println(matchProgress + " of " + allInput.size() + " processed.");
			pw.println("Number of matched terms: " + matchedTerms);
			pw.println("Number of match results: " + codescount);
			pw.close();
			System.out.println("Matching complete.");

		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LBInvocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LBParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	// Modified Kim's method readConfigFile
	public void setVectorFromFile(String filename) {
		FileReader configFile = null;
		BufferedReader buff = null;
		try {
			configFile = new FileReader(filename);
			buff = new BufferedReader(configFile);
			boolean eof = false;
			while (!eof) {
				String line = buff.readLine();
				if (line == null)
					eof = true;
				else {
					allInput.add(line.trim());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Closing the streams
			try {
				buff.close();
				configFile.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	
	
}
