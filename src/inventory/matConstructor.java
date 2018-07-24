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
public class matConstructor {
    private int id;
    private String name;
    private String location;
    private double quantity;
    private String unit;
    private String remarks;
    private String prefix;
    
    public matConstructor(int mid, String mname,String mlocation, double mquantity, String munit, String mremarks, String mprefix)
    {
        this.id = mid;
        this.name = mname;
        this.location = mlocation;
        this.quantity = mquantity;
        this.unit = munit;
        this.remarks = mremarks;
        this.prefix = mprefix;
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
    public double getQuantity()
        {
            return quantity;
        }
    public String getUnit()
        {
            return unit;
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
