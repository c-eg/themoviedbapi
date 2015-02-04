package info.movito.themoviedbapi.model.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import info.movito.themoviedbapi.tools.MovieDbException;

/** Container for timezone collections. 
 * 
 * For the TMDB a timezone is a ID (two letter code like "DE") and a set of synonyms
 * e.g. "Europe/Berlin". The JSON looks like [ {"DE":["Europe/Berlin","Europe/Busingen"]} ].
 * 
 * The closest data structure would be an Array of Pairs of String and String Array but
 * the most efficient structure is a simple hash of String Arrays which is represented
 * here.
 * 
 * Since standard JAVA doesn't know anything about a Pair<A,B> we use a 
 * HashMap<String,ArrayList<String>>[] to do the primary parsing and stuff
 * the array into a target TreeMap<String,String[]>   
 * 
 */

public class TmdbTimezones implements java.lang.Iterable<String> {
	static private ObjectMapper jsonmapper = new ObjectMapper();
	
	private Map<String,TreeSet<String>> countryMap  = new TreeMap<String, TreeSet<String>>(String.CASE_INSENSITIVE_ORDER);
	private Map<String,String>      timezoneMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
	
	private Set<String>             timezoneSet = null;
	private Set<String>             countrySet  = null;
	
	public TmdbTimezones(String webpage) {
		
		HashMap<String,ArrayList<String>>[] hash_array=new HashMap[]{};
		
		try {
			hash_array = jsonmapper.readValue(webpage,hash_array.getClass());
		} catch(Exception ex) {
            throw new MovieDbException("mapping failed: bad JSON '"+ex.getCause()+"'\n" + webpage);
		}
		
		// here we hold an array of hashs which each only hold one
		// key referring to an array. We transform it to two hashs
		// county=>[timezones] and timezone=>countries
		
		for(HashMap<String,ArrayList<String>> h : hash_array) {
			if(h.size()!=1)
	            throw new MovieDbException("mapping failed: too many keys\n" + webpage);

			String k = (String) h.keySet().toArray()[0];
			
			if(countryMap.containsKey(k))
	            throw new MovieDbException("mapping failed: country '"+k+"' already defined\n" + webpage);
			
			ArrayList<String> sl = h.get(k);
			TreeSet<String> sa = new TreeSet();
			
			int i=0;
			
			for(String s : sl) {
				if(timezoneMap.containsKey(s))
		            throw new MovieDbException("mapping failed: synonym '" + s + "' already defined\n" + webpage);
				timezoneMap.put(s, k);
				sa.add(s);
			}
			countryMap.put(k,sa);
		}
		timezoneSet = timezoneMap.keySet();
		countrySet  = countryMap.keySet();
	}
	
	public int size() {
		return timezoneSet.size();
	}
	public Iterator<String> iterator() {
		return timezoneSet.iterator();
	}
	public String get(int ix) {
		return (String) timezoneSet.toArray()[ix]; 
	}
	public String getCountry(String tz) {
		return timezoneMap.get(tz);
	}
	public Set<String> getCountries() {
		return countrySet;
	}
	public Set<String> getTimezonesOfCountry(String country) {
		return  countryMap.get(country);
	}
}
