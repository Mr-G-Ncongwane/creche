package com.ncongwanegeorgecoding.creche.controller;

import com.ncongwanegeorgecoding.creche.database.Connection;
import com.ncongwanegeorgecoding.creche.model.Dataclass;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.kernel.colors.DeviceRgb;
import java.util.HashMap;
import java.awt.Color;
import java.util.Iterator;
import java.util.Set;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.*;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ControllerImplementation {
    
    public Controller controller = new Controller() {
        Connection con = new Connection();
        CallableStatement callableStatement;
        
        @Override
        public void saveLearner(Dataclass dataclass) {
            try {

                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                callableStatement = con.connection().prepareCall("{call registerLearner(?,?,?,?,?,?,?,?,?,?)}");
                callableStatement.setInt("_idregistration", dataclass.getId());
                callableStatement.setString("_lnamesurname", dataclass.getlNameSurname());
                callableStatement.setLong("_idnumber", dataclass.getIdNumber());
                callableStatement.setString("_gender", dataclass.getGender());
                callableStatement.setString("_classroom", dataclass.getClassRoom());
                callableStatement.setString("_parentGuardianName", dataclass.getParentGuardianName());
                callableStatement.setLong("_phone1", dataclass.getPhoneNumber1());
                callableStatement.setLong("_phone2", dataclass.getPhoneNumber2());
                callableStatement.setString("_homeAddress1", dataclass.getHomeAddress1());
                callableStatement.setString("_homeAddress2", dataclass.getHomeAddress2());
                callableStatement.executeQuery();
                JOptionPane.showMessageDialog(null, "Learner Saved Successfully", "Registered Successfully", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    dataclass.logs("Save a learner: " + ex);
                } catch (SQLException ex1) {
                    Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        
        @Override
        public void updateLearner(Dataclass dataclass) {
            try {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                callableStatement = con.connection().prepareCall("{call registerLearner(?,?,?,?,?,?,?,?,?,?)}");
                callableStatement.setInt("_idregistration", dataclass.getId());
                callableStatement.setString("_lnamesurname", dataclass.getlNameSurname());
                callableStatement.setLong("_idnumber", dataclass.getIdNumber());
                callableStatement.setString("_gender", dataclass.getGender());
                callableStatement.setString("_classroom", dataclass.getClassRoom());
                callableStatement.setString("_parentGuardianName", dataclass.getParentGuardianName());
                callableStatement.setLong("_phone1", dataclass.getPhoneNumber1());
                callableStatement.setLong("_phone2", dataclass.getPhoneNumber2());
                callableStatement.setString("_homeAddress1", dataclass.getHomeAddress1());
                callableStatement.setString("_homeAddress2", dataclass.getHomeAddress2());
                callableStatement.executeQuery();
                JOptionPane.showMessageDialog(null, "Learner Updated Successfully", "Updated Successfully", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    dataclass.logs("Update a learner: " + ex);
                } catch (SQLException ex1) {
                    Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        
        @Override
        public Dataclass searchLearner(Dataclass dataclass, JTable table) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            try {
                ControllerImplementation implementation = new ControllerImplementation();
                List<Dataclass> list = implementation.controller.listSearch();
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                tableModel.setNumRows(0);
                list.stream().forEach((data) -> {
                    tableModel.addRow(new Object[]{data.getlNameSurname(), data.getIdNumber(),
                        data.getGender(), data.getClassRoom(), data.getParentGuardianName(), data.getPhoneNumber1(),
                        data.getPhoneNumber2(), data.getHomeAddress1(), data.getHomeAddress2(), data.getAmount(),
                        data.getMonth()});
                });
                
            } catch (Exception e) {
                try {
                    dataclass.logs("Search a learner: " + e);
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return dataclass;
        }
        
        @Override
        public List<Dataclass> listSearch() {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            List<Dataclass> list = new ArrayList<>();
            
            try {
                Dataclass dataclass = new Dataclass();
                callableStatement = con.connection().prepareCall("{call searchLearner(?)}");
                callableStatement.setString("_search", dataclass.getSearchLeaner());
                ResultSet resultSet = callableStatement.executeQuery();
                while (resultSet.next()) {
                    dataclass.setlNameSurname(resultSet.getString("registration.lnamesurname"));
                    dataclass.setIdNumber(resultSet.getLong("registration.idnumber"));
                    dataclass.setGender(resultSet.getString("registration.gender"));
                    dataclass.setParentGuardianName(resultSet.getString("registration.parentGuardianName"));
                    dataclass.setClassRoom(resultSet.getString("registration.classroom"));
                    dataclass.setHomeAddress1(resultSet.getString("registration.homeAddress1"));
                    dataclass.setHomeAddress2(resultSet.getString("registration.homeAddress2"));
                    dataclass.setPhoneNumber1(resultSet.getLong("registration.phone1"));
                    dataclass.setPhoneNumber2(resultSet.getLong("registration.phone2"));
                    dataclass.setAmount(resultSet.getDouble("payments.amount"));
                    dataclass.setMonth(resultSet.getString("payments.month"));
                    list.add(dataclass);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    Dataclass dataclass = new Dataclass();
                    dataclass.logs("List Learners: " + ex);
                } catch (SQLException ex1) {
                    Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            return list;
        }
        
        @Override
        public void deleteLearner(Dataclass dataclass) {
            try {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                callableStatement = con.connection().prepareCall("{call deleteLearner(?)}");
                callableStatement.setLong("_idnumber", dataclass.getIdNumber());
                callableStatement.executeQuery();
                JOptionPane.showMessageDialog(null, "Learner Deleted Successfully", "Deleted Successfully", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    dataclass.logs("Delete a learner: " + ex);
                } catch (SQLException ex1) {
                    Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex1);
                }
                
            }
        }
        
        @Override
        public void viewLearner(JTable table) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            try {
                ControllerImplementation implementation = new ControllerImplementation();
                List<Dataclass> list = implementation.controller.list();
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                tableModel.setNumRows(0);
                list.stream().forEach((dataclass) -> {
                    tableModel.addRow(new Object[]{dataclass.getId(), dataclass.getlNameSurname(), dataclass.getIdNumber(),
                        dataclass.getGender(), dataclass.getClassRoom(), dataclass.getParentGuardianName(),
                        dataclass.getPhoneNumber1(), dataclass.getPhoneNumber2(), dataclass.getHomeAddress1(), dataclass.getHomeAddress2(),
                        dataclass.getBirthCert(), dataclass.getParentID(), dataclass.getParentID()});
                });
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR);
                Dataclass dataclass = new Dataclass();
                try {
                    dataclass.logs("View learners: " + e);
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        @Override
        public List<Dataclass> list() {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            List<Dataclass> list = new ArrayList<>();
            try {
                callableStatement = con.connection().prepareCall("{call views()}");
                ResultSet resultSet = callableStatement.executeQuery();
                while (resultSet.next()) {
                    Dataclass dataclass = new Dataclass();
                    dataclass.setId(resultSet.getInt("idregistration"));
                    dataclass.setlNameSurname(resultSet.getString("lnamesurname"));
                    dataclass.setIdNumber(resultSet.getLong("idnumber"));
                    dataclass.setGender(resultSet.getString("gender"));
                    dataclass.setParentGuardianName(resultSet.getString("parentGuardianName"));
                    dataclass.setClassRoom(resultSet.getString("classroom"));
                    dataclass.setHomeAddress1(resultSet.getString("homeAddress1"));
                    dataclass.setHomeAddress2(resultSet.getString("homeAddress2"));
                    dataclass.setPhoneNumber1(resultSet.getLong("phone1"));
                    dataclass.setPhoneNumber2(resultSet.getLong("phone2"));
                    list.add(dataclass);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    Dataclass dataclass = new Dataclass();
                    dataclass.logs("List Learners: " + ex);
                } catch (SQLException ex1) {
                    Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            return list;
        }
        
        @Override
        public void saveClasses(Dataclass dataclass) {
            
            try {
                callableStatement = con.connection().prepareCall("{call addClass(?)}");
                callableStatement.setString("_classes", dataclass.getClassRoom());
                callableStatement.executeQuery();
                System.out.println("Class added successfully");
            } catch (Exception e) {
                try {
                    dataclass.logs("Adding Classes: " + e);
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        @Override
        public void showClasses(JComboBox comboBox) {
            try {
                callableStatement = con.connection().prepareCall("{call classes()}");
                ResultSet results = callableStatement.executeQuery();
                while (results.next()) {
                    comboBox.addItem(results.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    Dataclass dataclass = new Dataclass();
                    dataclass.logs("Adding Classes: " + ex);
                } catch (SQLException e) {
                    Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        @Override
        public void deleteClasses() {
            try {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                callableStatement = con.connection().prepareCall("{call deleteClass()}");
                callableStatement.executeQuery();
                //JOptionPane.showMessageDialog(null, "Learner Deleted Successfully", "Deleted Successfully", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Classes Deleted Successfully!");
            } catch (SQLException ex) {
                Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    Dataclass dataclass = new Dataclass();
                    dataclass.logs("Deleting Classes: " + ex);
                } catch (SQLException e) {
                    Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        @Override
        public void savePrices(Dataclass dataclass) {
            
            try {
                callableStatement = con.connection().prepareCall("{call addPrices(?)}");
                callableStatement.setDouble("_prices", dataclass.getAmount());
                callableStatement.executeQuery();
                System.out.println("Price added successfully");
            } catch (Exception e) {
                try {
                    
                    dataclass.logs("Adding Prices: " + e);
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        @Override
        public void showPrices(JComboBox comboBox) {
            try {
                callableStatement = con.connection().prepareCall("{call prices()}");
                ResultSet results = callableStatement.executeQuery();
                while (results.next()) {
                    comboBox.addItem(results.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    Dataclass dataclass = new Dataclass();
                    dataclass.logs("Show Prices: " + ex);
                } catch (SQLException e) {
                    Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        @Override
        public void deletePrices() {
            try {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                callableStatement = con.connection().prepareCall("{call deletePrices()}");
                callableStatement.executeQuery();
                //JOptionPane.showMessageDialog(null, "Learner Deleted Successfully", "Deleted Successfully", JOptionPane.INFORMATION_MESSAGE);
                System.out.print("Prices Deleted Successfully!");
            } catch (SQLException ex) {
                Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                Dataclass dataclass = new Dataclass();
                try {
                    dataclass.logs("Delete Prices: " + ex);
                } catch (SQLException ex1) {
                    Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        
        @Override
        public void savePayment(Dataclass dataclass) {
            try {
                
                callableStatement = con.connection().prepareCall("{call pay(?,?,?,?,?)}");
                callableStatement.setInt("_paymentsid", dataclass.getId());
                callableStatement.setString("_namesurname", dataclass.getlNameSurname());
                callableStatement.setDouble("_amount", dataclass.getAmount());
                callableStatement.setInt("_invoice", dataclass.getInvoiceNum());
                callableStatement.setString("_month", dataclass.getMonth());
                callableStatement.executeQuery();
                JOptionPane.showMessageDialog(null, "Payment Saved Successfully", "Paid Successfully", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    dataclass.logs("Save Payment: " + ex);
                } catch (SQLException ex1) {
                    Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        
        @Override
        public List<Dataclass> listPayment() {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            List<Dataclass> list = new ArrayList<>();
            
            try {
                
                callableStatement = con.connection().prepareCall("{call viewPayments()}");
                ResultSet resultSet = callableStatement.executeQuery();
                while (resultSet.next()) {
                    Dataclass dataclass = new Dataclass();
                    dataclass.setId(resultSet.getInt("paymentsid"));
                    dataclass.setlNameSurname(resultSet.getString("namesurname"));
                    dataclass.setAmount(resultSet.getDouble("amount"));
                    dataclass.setInvoiceNum(resultSet.getInt("invoice"));
                    dataclass.setMonth(resultSet.getString("month"));
                    list.add(dataclass);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    Dataclass dataclass = new Dataclass();
                    dataclass.logs("List Payments: " + ex);
                } catch (SQLException ex1) {
                    Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            return list;
        }
        
        @Override
        public void viewPayment(JTable table) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            try {
                ControllerImplementation implementation = new ControllerImplementation();
                List<Dataclass> listPayment = implementation.controller.listPayment();
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                tableModel.setNumRows(0);
                listPayment.stream().forEach((dataclass) -> {
                    tableModel.addRow(new Object[]{dataclass.getId(), dataclass.getlNameSurname(), dataclass.getAmount(), dataclass.getInvoiceNum(),
                        dataclass.getMonth()});
                });
            } catch (Exception e) {
                Dataclass dataclass = new Dataclass();
                try {
                    dataclass.logs("View Payment: " + e);
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        @Override
        public void createInvoice(Dataclass dataclass) throws FileNotFoundException {
            try {
                System.out.println(dataclass.createinvoiceFolder());
                SimpleDateFormat format = new SimpleDateFormat("dd-MM");
                java.util.Date date = new java.util.Date();
                String month = format.format(date);
                SimpleDateFormat formatNow = new SimpleDateFormat("dd-MMMM-yyyy");
                java.util.Date dateNow = new java.util.Date();
                String monthNow = formatNow.format(dateNow);
                callableStatement = con.connection().prepareCall("{call Quotation(?)}");
                callableStatement.setString("_Child", dataclass.getSearchLeaner());
                ResultSet results = callableStatement.executeQuery();
                while (results.next()) {
                    dataclass.setlNameSurname(results.getString(1));
                    dataclass.setlNameSurname(results.getString(1));
                    dataclass.setGender(results.getString(3));
                    dataclass.setIdNumber(results.getLong(2));
                    String path = (String) "C:\\Tsoha O Iketsetse Invoice\\" + month + " " + dataclass.getlNameSurname() + ".pdf";
                    PdfWriter pdfWriter = new PdfWriter(path);
                    PdfDocument pdfdocument = new PdfDocument(pdfWriter);
                    try (Document document = new Document(pdfdocument)) {
                        pdfdocument.setDefaultPageSize(PageSize.A4);
                        float col = 280f;
                        float columnWidth[] = {col, col};
                        Table table = new Table(columnWidth);
                        table.setBackgroundColor(new DeviceRgb(255, 51, 102))
                                .setBold();
                        table.addCell(new Cell().add(new Paragraph("Tsoha O Iketsetse Creche\nFees Form."))
                                .setTextAlignment(TextAlignment.CENTER)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                .setMarginTop(50f)
                                .setMarginBottom(50f)
                                .setFontSize(20f)
                                .setBorder(Border.NO_BORDER));
                        table.addCell(new Cell().add(new Paragraph("\t\t\nP.O Box 6146\n\t\tPhuthaditjhaba, Qwa Qwa.\nCall: (+27)84 370 4768\n"))
                                .setTextAlignment(TextAlignment.CENTER)
                                .setMarginTop(50f)
                                .setMarginBottom(50f)
                                .setMarginRight(10f)
                                .setBorder(Border.NO_BORDER));
                        float colwidth[] = {190, 300, 90, 80};
                        Table cTable = new Table(colwidth);
                        cTable.addCell(new Cell(0, 4).add(new Paragraph("Child Information"))
                                .setBold()
                                .setUnderline()
                                .setTextAlignment(TextAlignment.CENTER)
                        );
                        cTable.addCell(new Cell().add(new Paragraph("Name and Surname")).setBold());
                        cTable.addCell(new Cell().add(new Paragraph(dataclass.getlNameSurname())));
                        
                        cTable.addCell(new Cell().add(new Paragraph("Gender")).setBold());
                        cTable.addCell(new Cell().add(new Paragraph(dataclass.getGender())));
                        
                        cTable.addCell(new Cell().add(new Paragraph("ID Number")).setBold());
                        cTable.addCell(new Cell().add(new Paragraph("" + dataclass.getIdNumber())));
                        
                        cTable.addCell(new Cell().add(new Paragraph("")).setBackgroundColor(new DeviceRgb(255, 51, 102)));
                        cTable.addCell(new Cell().add(new Paragraph("")).setBackgroundColor(new DeviceRgb(255, 51, 102)));
                        
                        cTable.addCell(new Cell().add(new Paragraph("Date")).setBold());
                        cTable.addCell(new Cell().add(new Paragraph(monthNow)));
                        dataclass.setClassRoom(results.getString(4));
                        cTable.addCell(new Cell().add(new Paragraph("Classroom")).setBold());
                        cTable.addCell(new Cell().add(new Paragraph(dataclass.getClassRoom())));
                        
                        float dWidth[] = {70, 50, 50, 190, 50, 80};
                        Table dTable = new Table(dWidth);
                        dTable.addCell(new Cell().add(new Paragraph("")).setBackgroundColor(new DeviceRgb(255, 51, 102)).setBold());
                        dTable.addCell(new Cell().add(new Paragraph("Amount")).setBackgroundColor(new DeviceRgb(255, 51, 102)).setBold().setTextAlignment(TextAlignment.CENTER));
                        dTable.addCell(new Cell().add(new Paragraph("Concert")).setBackgroundColor(new DeviceRgb(255, 51, 102)).setBold().setTextAlignment(TextAlignment.CENTER));
                        dTable.addCell(new Cell().add(new Paragraph("Parent Name")).setBackgroundColor(new DeviceRgb(255, 51, 102)).setBold().setTextAlignment(TextAlignment.CENTER));
                        dTable.addCell(new Cell().add(new Paragraph("Receipt No.")).setBackgroundColor(new DeviceRgb(255, 51, 102)).setBold().setTextAlignment(TextAlignment.CENTER));
                        dTable.addCell(new Cell().add(new Paragraph("Date")).setBackgroundColor(new DeviceRgb(255, 51, 102)).setBold().setTextAlignment(TextAlignment.CENTER));
                        
                        dTable.addCell(new Cell().add(new Paragraph("Reg. Amnt.")));
                        dTable.addCell(new Cell().add(new Paragraph("R 50")));
                        dTable.addCell(new Cell().add(new Paragraph("-")).setTextAlignment(TextAlignment.CENTER));
                        dTable.addCell(new Cell().add(new Paragraph("-")).setTextAlignment(TextAlignment.CENTER));
                        dTable.addCell(new Cell().add(new Paragraph("").setBackgroundColor(new DeviceRgb(255, 51, 102))));
                        dTable.addCell(new Cell().add(new Paragraph("Jan")));
                        while (results.next()) {
                            dataclass.setInvoiceNum(results.getInt(7));
                            dataclass.setParentGuardianName(results.getString(5).toUpperCase());
                            dataclass.setMonth(results.getString(8));
                            dataclass.setAmount(results.getDouble(6));
                            dTable.addCell(new Cell().add(new Paragraph("")).setBackgroundColor(new DeviceRgb(255, 51, 102)));
                            dTable.addCell(new Cell().add(new Paragraph("R " + dataclass.getAmount())));
                            dTable.addCell(new Cell().add(new Paragraph("")));
                            dTable.addCell(new Cell().add(new Paragraph(dataclass.getParentGuardianName())));
                            dTable.addCell(new Cell().add(new Paragraph("#" + dataclass.getInvoiceNum())));
                            dTable.addCell(new Cell().add(new Paragraph(dataclass.getMonth())));
                            
                        }
                        document.add(table);
                        document.add(new Paragraph("\n"));
                        document.add(cTable);
                        document.add(new Paragraph("\n"));
                        document.add(dTable);
                        document.add(new Paragraph("\nRecipient Signature: ..........................................................................................").setBold());
                        document.add(new Paragraph("\n"));
                        document.add(new Paragraph("Parent/Guardian Signature: ....................................................................................").setBold());
                        document.add(new Paragraph("\n\tNovember and December fees must be paid by October 31. Next year registrations will start from 01 to 31 November.\n").setFontSize(10f).setBold().setTextAlignment(TextAlignment.JUSTIFIED));
                        document.add(new Paragraph("\n\tNOTE: We do not take responsibility for any loss of money given to a child").setFontSize(10f).setBold().setTextAlignment(TextAlignment.JUSTIFIED));
                        document.add(new Paragraph("______**Thank you**______").setTextAlignment(TextAlignment.CENTER).setBold());
                    }
                    
                }
                System.out.println("Document Created");
                JOptionPane.showMessageDialog(null, "Invoice for " + dataclass.getlNameSurname() + " Successfully Created.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    dataclass.logs("Invoice ERROR Exception" + ex.getMessage());
                } catch (SQLException ex1) {
                    Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (FileNotFoundException | HeadlessException e) {
                try {
                    dataclass.logs("Invoice ERROR Exception" + e.getMessage());
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        @Override
        public void createChart(JPanel chartPanel) {
            Dataclass dataclass = new Dataclass();
            HashMap datahash = new HashMap();
            try {
                datahash.clear();
                callableStatement = con.connection().prepareCall("{call gender()}");
                ResultSet resultSet = callableStatement.executeQuery();
                while (resultSet.next()) {
                    dataclass.setMale(resultSet.getString(1));
                    dataclass.setFemale(resultSet.getString(2));
                    dataclass.setOther(resultSet.getString(3));
                    datahash.put("Boys", dataclass.getMale());
                    datahash.put("Girls", dataclass.getFemale());
                    datahash.put("Others", dataclass.getOther());
                }
                DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
                Set keys = datahash.keySet();
                Iterator productInfo = keys.iterator();
                while (productInfo.hasNext()) {
                    String names = (String) productInfo.next();
                    Integer quantity = Integer.parseUnsignedInt((String) datahash.get(names));
                    defaultCategoryDataset.setValue(quantity, "Gender", names);
                }
                JFreeChart chart = ChartFactory.createBarChart("Gender Stats", "Genders", "Total number of Genders", defaultCategoryDataset, PlotOrientation.VERTICAL, true, true, true);
                CategoryPlot plot = chart.getCategoryPlot();
                plot.setRangeGridlinePaint(Color.GREEN);
                ChartPanel chartPanl = new ChartPanel(chart);
                chartPanel.removeAll();
                chartPanel.add(chartPanl);
                chartPanel.updateUI();
            } catch (SQLException ex) {
                try {
                    //Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                    dataclass.logs("Chart Exception: " + ex);
                } catch (SQLException ex1) {
                    Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        
        @Override
        
        public void logIn(Dataclass dataclass, JFrame mainForm, JButton staff) {
            try {
                int log = 1;
                callableStatement = con.connection().prepareCall("{call callUsers()}");
                ResultSet resultSet = callableStatement.executeQuery();
                while (resultSet.next()) {
                    
                    if ((resultSet.getString(6)).equals(dataclass.getUsername()) && (resultSet.getString(5)).equals(dataclass.getPassword())) {
                        log = 0;
                        dataclass.setUser(resultSet.getString(2).toUpperCase());
                        dataclass.setPosition(resultSet.getString(3));
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect Password/Username", "Invalid Credentials", JOptionPane.ERROR_MESSAGE);
                    }
                    dataclass.logs("Error Log In by: " + dataclass.getUsername());
                }
                if (log == 0) {
                    if (dataclass.getPosition().equalsIgnoreCase("Admin") || dataclass.getPosition().equalsIgnoreCase("Manager")) {
                        staff.setEnabled(true);
                    }
                    mainForm.setVisible(true);
                }
            } catch (SQLException ex) {
                //Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    dataclass.logs("Log In Exception Error: " + ex);
                } catch (SQLException ex1) {
                    Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex1);
                    try {
                        dataclass.logs("Log In: " + ex);
                    } catch (SQLException e) {
                        Logger.getLogger(ControllerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    };
    
}
