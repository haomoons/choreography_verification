package IO;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import Automata.CFSMs;

public class WriteCFSMsToMCRL2 {
	public static void buildCFSMs(CFSMs cfsm) {
		FileWriter fw = null;
		try {
			File f=new File(cfsm.getName()+".fsm");
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println("---");
		pw.println("---");
		Iterator<String> itTransitions = cfsm.getSetOfTransitions().iterator();
		while(itTransitions.hasNext()){
		   String str= itTransitions.next();
		   pw.println(str.split("\\|")[0]+" "+str.split("\\|")[1]+" \""+str.split("\\|")[2]+"\"");
		}
		
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
