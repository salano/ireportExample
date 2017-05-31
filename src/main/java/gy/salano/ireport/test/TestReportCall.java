/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gy.salano.ireport.test;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author csullivan
 */
public class TestReportCall {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        String reportSrcFile = "C:\\Users\\csullivan\\Documents\\LABWORK\\Valuation_report_Increase.jrxml";
        String sourceFileName = "C:\\Users\\csullivan\\Documents\\LABWORK\\Valuation_report_Increase.jasper";
        try {
            // First, compile jrxml file.
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
            Connection conn = ConnectionUtils.getConnection();
            // Parameters for report
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("p_ref", "CLIQHO/C4/17/C16632");
            parameters.put("imagesDir", "C:\\Users\\csullivan\\Documents\\LABWORK\\");
            String printFileName = JasperFillManager.fillReportToFile( 
            sourceFileName, parameters, conn);

            JasperPrint print = JasperFillManager.fillReport(jasperReport,
                    parameters, conn);

            // Make sure the output directory exists.
            File outDir = new File("C:/jasperoutput");
            outDir.mkdirs();

            // PDF Exportor.
            JRPdfExporter exporter = new JRPdfExporter();

            ExporterInput exporterInput = new SimpleExporterInput(print);
            // ExporterInput
            exporter.setExporterInput(exporterInput);

            // ExporterOutput
            OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                    "C:/jasperoutput/FirstJasperReport.pdf");
            // Output
            exporter.setExporterOutput(exporterOutput);

            //
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            if(printFileName != null){
            JasperViewer viewer = new JasperViewer(printFileName, false);
            viewer.setVisible(true);
         }

            System.out.print("Done!");
        } catch (JRException | ClassNotFoundException ex) {
            Logger.getLogger(TestReportCall.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
