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
public class toolConstructor {
    private int id;
    private String name;
    private String location;
    private String dop;
    private double amount;
    private String history;
    private String status;
    private String remarks;
    private String prefix;
    
    public toolConstructor(int tid, String tname, String tlocation, String tdop, double tamount, String thistory, String tstatus, String tremarks, String tprefix)
    {
        this.id = tid;
        this.name = tname;
        this.location = tlocation;
        this.dop = tdop;
        this.amount = tamount;
        this.history = thistory;
        this.status = tstatus;
        this.remarks = tremarks;
        this.prefix = tprefix;
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
    public String getDop()
        {
            return dop;
        }
    public double getAmount()
        {
            return amount;
        }
    public String getHistory()
        {
            return history;
        }
    public String getStatus()
        {
            return status;
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
