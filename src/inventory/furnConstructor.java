/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

/**
 *
 * @author Ernest
 */
public class furnConstructor {
    private int id;
    private String name;
    private String location;
    private String status;
    private String type;
    private String remarks;
    private String prefix;
    
    public furnConstructor(int fid, String fname,String flocation, String fstatus, String ftype, String fremarks, String fprefix)
    {
        this.id = fid;
        this.name = fname;
        this.location = flocation;
        this.status = fstatus;
        this.type = ftype;
        this.remarks = fremarks;
        this.prefix = fprefix;     
    }
    
    public int getId()
        {                  
            return id;
        }
    public String getName()
        {
            return name;
        }
    public String getLocation()
    {
        return location;
    }
    public String getStatus()
        {
            return status;
        }
    public String getType()
        {
            return type;
        }
    public String getRemarks()
        {
            return remarks;
        }
    public String getPrefix()
    {
        return prefix;
    }
}
