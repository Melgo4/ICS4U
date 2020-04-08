package employeet;

import employeet.data.*;
import employeet.data.entry.Entry;
import employeet.data.entry.FloatEntry;
import employeet.data.entry.IntEntry;
import employeet.data.entry.LiteralEntry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * EmployeeFile.java
 *
 * Purpose: Wrapper for an employee's information.
 *
 * void setFirstName - Sets the first name.
 * void setLastName - Sets the last name.
 * void setEmployeeNo - Sets the employee number.
 * void setAddress - Sets the address.
 * void setCity - Sets the city.
 * void setPayRate - Sets the pay rate.
 * void setPosition - Sets the position.
 * String serialize - Serializes the employee file.
 * void deserialize - Deserializes the employee file.
 * */
public class EmployeeFile implements ISerializable<String>, Serializable {

    private List<Entry<?>> entries = new ArrayList<>();

    private LiteralEntry firstName = new LiteralEntry();
    private LiteralEntry lastName = new LiteralEntry();
    private IntEntry employeeNo = new IntEntry();
    private LiteralEntry address = new LiteralEntry();
    private LiteralEntry city = new LiteralEntry();
    private FloatEntry payRate = new FloatEntry(14.0F);
    private LiteralEntry position = new LiteralEntry();

    private String serializationCache = null;
    private boolean dirty = true;

    public EmployeeFile() {
        this.entries.add(this.firstName);
        this.entries.add(this.lastName);
        this.entries.add(this.employeeNo);
        this.entries.add(this.address);
        this.entries.add(this.city);
        this.entries.add(this.payRate);
        this.entries.add(this.position);
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
        this.dirty = true;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
        this.dirty = true;
    }

    public void setEmployeeNo(int employeeNo) {
        this.employeeNo.set(employeeNo);
        this.dirty = true;
    }

    public void setAddress(String address) {
        this.address.set(address);
        this.dirty = true;
    }

    public void setCity(String city) {
        this.city.set(city);
        this.dirty = true;
    }

    public void setPayRate(float payRate) {
        this.payRate.set(payRate);
        this.dirty = true;
    }

    public void setPosition(String jobDescription) {
        this.position.set(jobDescription);
        this.dirty = true;
    }

    @Override
    public String serialize() {
        if(!this.dirty && this.serializationCache != null)return this.serializationCache;

        StringBuilder sb = new StringBuilder();
        Iterator<Entry<?>> iterator = this.entries.iterator();

        while(iterator.hasNext()) {
            Entry<?> e = iterator.next();
            sb.append(e.serialize());

            if(iterator.hasNext()) {
                sb.append(",");
            }
        }

        this.dirty = false;
        this.serializationCache = sb.toString();
        return this.serializationCache;
    }

    @Override
    public void deserialize(String raw) {
        String[] data = raw.split(Pattern.quote(","));

        if(data.length != this.entries.size()) {
            System.err.format("Malformed line [%s]-- was expecting %d argument%s, found %d.\n",
                    raw, data.length, data.length > 1 ? "s" : "", this.entries.size());

            return;
        }

        for(int i = 0; i < data.length; i++) {
            this.entries.get(i).deserialize(data[i]);
        }
    }

    @Override
    public String toString() {
        return this.serialize();
    }

}
