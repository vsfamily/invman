package vsfam.ss.invMan.common;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJson {

	public String getJsonString(Object obj) {
		
		String str = "";
		
		ObjectMapper objMap = new ObjectMapper();
		 
        try {
 
        	str = objMap.writeValueAsString(obj);
        	
 
        } catch (Exception e) {
            System.out.println("JSON Conversion: " + e.getMessage());
        }
        
        return str;
	}
}
