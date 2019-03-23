
package com.loanassessment;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import org.w3c.dom.Element;

public class JavaEmbeddingSupport extends com.collaxa.cube.engine.ext.bpel.v1.nodes.BPELXExecLet  {
    public JavaEmbeddingSupport() {
        super();
    }
    
    public void execute() {
        org.w3c.dom.Element element;            
                 
        try {         
            element = (org.w3c.dom.Element)getVariableData("inputVariable","payload","/ns1:LoanApplication/ns1:application/ns2:identification/ns3:firstName");              
            String firstName = element.getFirstChild().getNodeValue();         
                  
            addAuditTrailEntry("element First name: " + element.getTextContent());      
            addAuditTrailEntry("First name = " + firstName);      
                         
            element = (org.w3c.dom.Element)getVariableData("inputVariable","payload","/ns1:LoanApplication/ns1:application/ns2:identification/ns3:lastName");              
            String lastName = element.getFirstChild().getNodeValue();           
              
            addAuditTrailEntry("element Last name: " + element.getTextContent());      
            addAuditTrailEntry("Last name = " + lastName);   
          
            String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";   
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);  
            Matcher matcher;  
            element = (org.w3c.dom.Element)getVariableData("inputVariable","payload","/ns1:LoanApplication/ns1:application/ns2:contact/ns4:email");              
            String email = element.getFirstChild().getNodeValue();  
            boolean emailValid = matcher.matches();  
         
            String errorMsg = ""; 
         
            if (firstName.equals("")) { 
                errorMsg += "First Name is empty; "; 
            } 
         
            if (lastName.equals("")) { 
                errorMsg += "Last Name is empty; "; 
            } 
         
            if (!emailValid) { 
                errorMsg += "Email address is invalid; "; 
            } 
                 
            setVariableData("outputVariable","payload", "/client:processResponse/client:errorMessage", errorMsg);

            if (!errorMsg.equals("")) {             
                setVariableData("outputVariable","payload", "/client:processResponse/client:result","incomplete");             
            } else {             
                setVariableData("outputVariable","payload", "/client:processResponse/client:result","complete");             
            }         
        } catch (Exception ex) {         
            addAuditTrailEntry(ex.getMessage());         
        }
    }
}