/*
 * Sample module ingest job settings panel in the public domain.  
 * Feel free to use this as a template for your module ingest job settings
 * panels.
 * 
 *  Contact: Brian Carrier [carrier <at> sleuthkit [dot] org]
 *
 *  This is free and unencumbered software released into the public domain.
 *  
 *  Anyone is free to copy, modify, publish, use, compile, sell, or
 *  distribute this software, either in source code form or as a compiled
 *  binary, for any purpose, commercial or non-commercial, and by any
 *  means.
 *  
 *  In jurisdictions that recognize copyright laws, the author or authors
 *  of this software dedicate any and all copyright interest in the
 *  software to the public domain. We make this dedication for the benefit
 *  of the public at large and to the detriment of our heirs and
 *  successors. We intend this dedication to be an overt act of
 *  relinquishment in perpetuity of all present and future rights to this
 *  software under copyright law.
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 *  IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 *  OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 *  ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE. 
 */
package pt.ipleiria.estg.dei;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.sleuthkit.autopsy.ingest.IngestModuleIngestJobSettings;
import org.sleuthkit.autopsy.ingest.IngestModuleIngestJobSettingsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * UI component used to make per ingest job settings for sample ingest modules.
 */
@SuppressWarnings("PMD.SingularField") // UI widgets cause lots of false positives
public class BrowserHistoryModuleIngestJobSettingsPanel extends IngestModuleIngestJobSettingsPanel  {

    private String fileChoosed;

    /**
     * Creates new form BrowserHistoryModuleIngestJobSettings
     */
    public BrowserHistoryModuleIngestJobSettingsPanel(BrowserHistoryModuleIngestJobSettings settings) {
        initComponents();
        customizeComponents(settings);

        defaultOptionRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooserButton.setEnabled(false);
                pathText.setEnabled(false);
            }
        });

        customFileRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooserButton.setEnabled(true);
                pathText.setEnabled(true);
            }
        });
    }

    private void customizeComponents(BrowserHistoryModuleIngestJobSettings settings) {
    }

    /**
     * Gets the ingest job settings for an ingest module.
     *
     * @return The ingest settings.
     */
    @Override
    public IngestModuleIngestJobSettings getSettings() {
        return fileChooserButton.isEnabled() ? new BrowserHistoryModuleIngestJobSettings(fileChoosed) : new BrowserHistoryModuleIngestJobSettings() ;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        labelBrowserHistorySettigs = new javax.swing.JLabel();
        panel1 = new java.awt.Panel();
        fileChooserButton = new javax.swing.JButton();
        pathText = new javax.swing.JTextField();
        labelLocationOfFile = new javax.swing.JLabel();
        defaultOptionRadioButton = new javax.swing.JRadioButton();
        customFileRadioButton = new javax.swing.JRadioButton();

        labelBrowserHistorySettigs.setText(org.openide.util.NbBundle.getMessage(BrowserHistoryModuleIngestJobSettingsPanel.class, "BrowserHistoryModuleIngestJobSettingsPanel.labelBrowserHistorySettigs.text")); // NOI18N

        fileChooserButton.setText(org.openide.util.NbBundle.getMessage(BrowserHistoryModuleIngestJobSettingsPanel.class, "BrowserHistoryModuleIngestJobSettingsPanel.fileChooserButton.text")); // NOI18N
        fileChooserButton.setEnabled(false);
        fileChooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserButtonActionPerformed(evt);
            }
        });

        pathText.setEditable(false);
        pathText.setText(org.openide.util.NbBundle.getMessage(BrowserHistoryModuleIngestJobSettingsPanel.class, "BrowserHistoryModuleIngestJobSettingsPanel.pathText.text")); // NOI18N
        pathText.setEnabled(false);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addComponent(pathText, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fileChooserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileChooserButton)
                    .addComponent(pathText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );

        labelLocationOfFile.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        labelLocationOfFile.setText(org.openide.util.NbBundle.getMessage(BrowserHistoryModuleIngestJobSettingsPanel.class, "BrowserHistoryModuleIngestJobSettingsPanel.labelLocationOfFile.text")); // NOI18N

        buttonGroup1.add(defaultOptionRadioButton);
        defaultOptionRadioButton.setSelected(true);
        defaultOptionRadioButton.setText(org.openide.util.NbBundle.getMessage(BrowserHistoryModuleIngestJobSettingsPanel.class, "BrowserHistoryModuleIngestJobSettingsPanel.defaultOptionRadioButton.text")); // NOI18N

        buttonGroup1.add(customFileRadioButton);
        customFileRadioButton.setText(org.openide.util.NbBundle.getMessage(BrowserHistoryModuleIngestJobSettingsPanel.class, "BrowserHistoryModuleIngestJobSettingsPanel.customFileRadioButton.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelBrowserHistorySettigs)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(customFileRadioButton)
                                    .addComponent(defaultOptionRadioButton)))
                            .addComponent(labelLocationOfFile))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(labelBrowserHistorySettigs)
                .addGap(18, 18, 18)
                .addComponent(labelLocationOfFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(defaultOptionRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customFileRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fileChooserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChooserButtonActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(this);

        if(returnVal == JFileChooser.APPROVE_OPTION) {
            fileChoosed = chooser.getSelectedFile().getAbsolutePath();
        }
        pathText.setText(fileChoosed);

    }//GEN-LAST:event_fileChooserButtonActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JRadioButton customFileRadioButton;
    private javax.swing.JRadioButton defaultOptionRadioButton;
    private javax.swing.JButton fileChooserButton;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel labelBrowserHistorySettigs;
    private javax.swing.JLabel labelLocationOfFile;
    private java.awt.Panel panel1;
    private javax.swing.JTextField pathText;
    // End of variables declaration//GEN-END:variables
}
