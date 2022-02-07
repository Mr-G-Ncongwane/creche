package com.ncongwanegeorgecoding.creche.controller;

import com.ncongwanegeorgecoding.creche.model.Dataclass;
import java.io.FileNotFoundException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public interface Controller {
public void saveLearner(Dataclass dataclass);
public void updateLearner(Dataclass dataclass);
public Dataclass searchLearner(Dataclass dataclass,JTable table);
public void deleteLearner(Dataclass dataclass);
public void viewLearner(JTable table);
public List<Dataclass> list();
public List<Dataclass> listSearch();        
public void saveClasses(Dataclass dataclass);
public void savePrices(Dataclass dataclass);
public void showClasses(JComboBox comboBox);
public void showPrices(JComboBox comboBox);
public void deleteClasses();
public void deletePrices();
public void savePayment(Dataclass dataclass);
public void viewPayment(JTable table);
public void createInvoice(Dataclass dataclass) throws FileNotFoundException;
public void createChart( JPanel chartPanel);
public List<Dataclass> listPayment();
public void logIn(Dataclass dataclass, JFrame mainForm, JButton staff);
}
