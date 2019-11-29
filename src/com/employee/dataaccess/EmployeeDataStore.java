package com.employee.dataaccess;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.employee.model.EmployeeData;

public class EmployeeDataStore {

	
	public String filePath = "C:\\Users\\PG066149\\debugging-workspace\\EmployeePortalService\\employees.json";
	
	public boolean postEmployeeData(EmployeeData employeeData) {
		JSONArray employeeList = new JSONArray();
		
		JSONObject employeeDetails = new JSONObject();
		JSONObject employeeObject = new JSONObject(); 
		
        employeeDetails.put("firstName", employeeData.getFirstName());
        employeeDetails.put("lastName", employeeData.getLastName());
        employeeDetails.put("gender", employeeData.getGender());
        employeeDetails.put("dob", employeeData.getDob());
        employeeDetails.put("department", employeeData.getDepartment());
         
        
        employeeObject.put("employee", employeeDetails);
         
        employeeList.add(employeeObject);
         
        try (FileWriter file = new FileWriter(filePath)) {
 
            file.write(employeeList.toJSONString());
            file.flush();
            
           return true;
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return false;
	}
	
	public JSONArray getEmployeesData() {
		
		
		JSONParser jsonParser = new JSONParser();
        
        try (FileReader reader = new FileReader(filePath))
        {
            Object empObj = jsonParser.parse(reader);
 
            JSONArray employeeList = (JSONArray) empObj;
             
            return employeeList;
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return new JSONArray();
		
	}
	
}
