import java.util.*;

public class CodonProfiler {

	/**
	 * Count how many times each codon in an array of codons occurs
	 * in a strand of DNA. Return int[] such that int[k] is number
	 * of occurrences of codons[k] in strand. Strand codons can start
	 * at all valid indexes that are multiples of 3: 0, 3, 6, 9, 12, ...
	 * @param strand is DNA to be analyzed for codon occurrences.
	 * @param codons is an array of strings, each has three characters
	 * @return int[] such that int[k] is number of occurrences of codons[k] in
	 * strand.
	 */
	public int[] getCodonProfile(IDnaStrand strand, String[] codons) {

		HashMap <String,Integer> map = new HashMap<>();
		int[] ret = new int[codons.length];
		Iterator <Character> iterator = strand.iterator();
		while(iterator.hasNext()) {
			char a = iterator.next();
			char b = 'z';
			char c = 'z';
			if(iterator.hasNext())
				b = iterator.next();
			if(iterator.hasNext())
				c = iterator.next();
			String cod = "" + a + b + c;
			if(map.containsKey(cod))
				map.put(cod, map.get(cod)+1);
			else
				map.put(cod, 1);
		}
		for(int i = 0; i < codons.length; i++)
		{
			if(map.containsKey(codons[i]))
				ret[i] = map.get(codons[i]);
			else
				ret[i] = 0;
		}
		return ret;

	}



}
