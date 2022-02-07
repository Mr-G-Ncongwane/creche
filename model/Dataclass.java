/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncongwanegeorgecoding.creche.model;

import com.ncongwanegeorgecoding.creche.database.Connection;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;

public class Dataclass {

    public String createinvoiceFolder() {
        String feedback;
        File fName = new File("C:\\Tsoha O Iketsetse Invoice");
        if (!fName.exists()) {
            fName.mkdir();
            feedback = "Invoice Folder Created";
        } else {
            feedback = "Invoice Folder Exist";
        }
        return feedback;
    }

    public void logs(String nameObject) throws SQLException {
        try {
            Path path = Paths.get("C:\\Creche\\crecheLogs.txt");
            String conStatus;
            File fName = new File("C:\\Creche");
            if (!fName.exists()) {
                fName.mkdir();
                System.out.println("Logs Folder Created");

            } else if (!path.toFile().isFile()) {
                path.toFile().createNewFile();
                System.out.println("New logs file created!");
            } else {
                Connection con = new Connection();
                if (con.connection() != null) {
                    conStatus = "[**Conected**]\t Date: " + "[-" + Date.from(Instant.now()) + "-]";
                } else {
                    conStatus = "[**Not Conected**]\t Date" + "[-" + Date.from(Instant.now()) + "-]";
                }

                String logs = "[NGC] " +"Username: @"+getUsername() + conStatus + ": " + nameObject + "\n";
                byte[] data = logs.getBytes();
                OutputStream output;

                output = new BufferedOutputStream(Files.newOutputStream(path, StandardOpenOption.APPEND));
                output.write(data);
                output.flush();
                output.close();
            }
        } catch (SQLException | IOException e) {
        }
    }
    String male, female, position;
    int invoiceNum;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }
    String username, user, password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public String getFemale() {
        return female;
    }

    public void setFemale(String female) {
        this.female = female;
    }

    public String getBabiesClass() {
        return babiesClass;
    }

    public void setBabiesClass(String babiesClass) {
        this.babiesClass = babiesClass;
    }

    public String getToddlersClass() {
        return toddlersClass;
    }

    public void setToddlersClass(String toddlersClass) {
        this.toddlersClass = toddlersClass;
    }

    public String get3To4Years() {
        return _3To4Years;
    }

    public void set3To4Years(String _3To4Years) {
        this._3To4Years = _3To4Years;
    }

    public String get4To5Years() {
        return _4To5Years;
    }

    //classRoom category
    public void set4To5Years(String _4To5Years) {
        this._4To5Years = _4To5Years;
    }

    public String getlNameSurname() {
        return lNameSurname;
    }

    public void setlNameSurname(String lNameSurname) {
        this.lNameSurname = lNameSurname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getParentGuardianName() {
        return parentGuardianName;
    }

    public void setParentGuardianName(String parentGuardianName) {
        this.parentGuardianName = parentGuardianName;
    }

    public String getHomeAddress1() {
        return homeAddress1;
    }

    public void setHomeAddress1(String homeAddress1) {
        this.homeAddress1 = homeAddress1;
    }

    public String getHomeAddress2() {
        return homeAddress2;
    }

    public void setHomeAddress2(String homeAddress2) {
        this.homeAddress2 = homeAddress2;
    }

    public long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(long idNumber) {
        this.idNumber = idNumber;
    }

    public long getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(long phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public long getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(long phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getMenuCat() {
        return menuCat;
    }

    public void setMenuCat(String menuCat) {
        this.menuCat = menuCat;
    }

    public String getServeTime() {
        return serveTime;
    }

    public void setServeTime(String serveTime) {
        this.serveTime = serveTime;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
    private String babiesClass, toddlersClass, _3To4Years, _4To5Years;
    //registration datafields
    private String searchLeaner, lNameSurname, gender, classRoom, parentGuardianName, homeAddress1, homeAddress2;

    public String getSearchLeaner() {
        return searchLeaner;
    }

    public void setSearchLeaner(String searchLeaner) {
        this.searchLeaner = searchLeaner;
    }
    private Byte[] birthCert, parentID, clinicFile;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Byte[] getBirthCert() {
        return birthCert;
    }

    public void setBirthCert(Byte[] birthCert) {
        this.birthCert = birthCert;
    }

    public Byte[] getParentID() {
        return parentID;
    }

    public void setParentID(Byte[] parentID) {
        this.parentID = parentID;
    }

    public Byte[] getClinicFile() {
        return clinicFile;
    }

    public void setClinicFile(Byte[] clinicFile) {
        this.clinicFile = clinicFile;
    }
    private long idNumber, phoneNumber1, phoneNumber2;
    //menu datafields
    private String menuDescription, weekDay, menuCat, serveTime;
//daily program datafields
    private String activityName, activityTime, resources;
//payment datafields
    private double amount;
    String month;

    public Dataclass() {

    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
    String other;

}
