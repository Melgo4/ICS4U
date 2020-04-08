package employeet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FileIO.java
 *
 * Purpose: Provide utility functions for writing and reading employee files to disk.
 *
 * void fileReadMethod - Read to the record from file.
 * void writeFileMethod - Write the record to file.
 * void setEmployeeRecord - Creates a new employee file and caches it.
 * EmployeeFile getEmployeeRecord - Gets the cached employee file.
 * int getCounter - Gets the buffer position.
 * EmployeeFile getSaveObjRecord - Gets the file at the given position.
 * void setIncreaseCount - Increases the buffer position.
 * void writeObjectMethod - Writes the buffer to file.
 * void objectInputMethod - Read to the buffer from file.
 * */
public class FileIO {

    private List<EmployeeFile> saveRecord = new ArrayList<>();
    private List<EmployeeFile> saveObjRecord = new ArrayList<>();
    private EmployeeFile cache;

    public void fileReadMethod(File file) {
        if(!file.exists()) {
            file = new File("Assignment 3\\" + file.getName());
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            while(reader.ready()) {
                String rawLine = reader.readLine();
                EmployeeFile employeeFile = new EmployeeFile();
                employeeFile.deserialize(rawLine);
                this.saveRecord.add(employeeFile);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFileMethod(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for(EmployeeFile employeeFile: this.saveRecord) {
                writer.write(employeeFile.serialize() + "\n");
            }

            writer.flush();
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setEmployeeRecord() {
        this.cache = new EmployeeFile();
        this.saveRecord.add(this.cache);
    }

    public EmployeeFile getEmployeeRecord() {
        return this.cache;
    }

    public int getCounter() {
        return this.saveObjRecord.size();
    }

    public EmployeeFile getSaveObjRecord(int c) {
        return this.saveObjRecord.get(c);
    }

    //Those methods are straight up useless.
    public void setIncreaseCount() {
    
    }

    public void writeObjectMethod(File file) {
        try {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file));
            writer.writeInt(this.saveRecord.size());

            for(EmployeeFile employeeFile: this.saveRecord) {
                writer.writeObject(employeeFile);
            }

            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void objectInputMethod(File file) {
        if(!file.exists()) {
            file = new File("Assignment 3\\" + file.getName());
        }

        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file));
            int size = reader.readInt();

            for(int i = 0; i < size; i++) {
                this.saveObjRecord.add((EmployeeFile)reader.readObject());
            }

            reader.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================================\n");
        this.saveRecord.forEach(e -> sb.append(e).append("\n"));
        sb.append("========================================================");
        return sb.toString();
    }

}
