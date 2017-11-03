/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

/**
 *
 * @author gerson
 * @param <T>
 */
public class ClassesOutput<T> {
    private T output = null;
    private int errorNumber = 0;
    private String errorMessage = "No error";
    
    public ClassesOutput() {}

    public ClassesOutput(T output)
    {
        this.output=output;
    }
    public ClassesOutput(int errorNumber, String errorMessage)    
    {
        this.errorNumber=errorNumber;        
        this.errorMessage=errorMessage;        
    }        
    
    public ClassesOutput(T output, int errorNumber, String errorMessage)    
    {
        this.output=output;
        this.errorNumber=errorNumber;        
        this.errorMessage=errorMessage;        
    }
    
    public int getErrorNumber()
    {    
        return this.errorNumber;
    }        
    
    public String getErrorMessage()
    {    
        return this.errorMessage;
    }
    
    public T getOutput()
    {
        return this.output;
    }
    
    public void print()
    {
        System.out.printf("Error:\nError Number:%d\nError Message:%s\n",this.errorNumber,this.errorMessage);
    }    
}
