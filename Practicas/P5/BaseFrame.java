import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.w3c.dom.Text;

import java.util.concurrent.*;

/**
 *
 * @author guillermogirongarcia
 * Clase principal que implementa
 * la gui del programa del cifrado
 */
public class BaseFrame extends javax.swing.JFrame 
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates new form BaseFrame
     */
    public BaseFrame() 
    {
        initComponents();
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() 
    {

        StringCipherPanel = new javax.swing.JPanel();
        ScrollPanelNoCipher = new javax.swing.JScrollPane();
        TextToCipher = new javax.swing.JTextPane();
        ScrollPanelCiphered = new javax.swing.JScrollPane();
        CipheredText = new javax.swing.JTextPane();
        CipheredTextLabel = new javax.swing.JLabel();
        TextToCipherLabel = new javax.swing.JLabel();
        KeyLabel = new javax.swing.JLabel();
        KeyText = new javax.swing.JTextField();
        CipherButton = new javax.swing.JButton();
        CleanButton = new javax.swing.JButton();
        LoadFileButton = new javax.swing.JButton();
        SaveFileButton = new javax.swing.JButton();
        LoadedFileText = new javax.swing.JTextField();
        FileCipherPanel = new javax.swing.JPanel();
        MenuBar = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        OpenFile = new javax.swing.JMenuItem();
        Close = new javax.swing.JMenuItem();
        Tools = new javax.swing.JMenu();
        About = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Text Cipher");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMaximumSize(new java.awt.Dimension(1920, 1080));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setName("Text Cipher"); // NOI18N

        StringCipherPanel.setToolTipText("StringCipher");
        StringCipherPanel.setName("String Cipher"); // NOI18N

        ScrollPanelNoCipher.setViewportView(TextToCipher);

        ScrollPanelCiphered.setViewportView(CipheredText);

        CipheredTextLabel.setText("Ciphered Text:");

        TextToCipherLabel.setText("Text To Cipher:");

        KeyLabel.setText("Key:");

        CipherButton.setText("Apply Cipher");
        CipherButton.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                CipherButtonActionPerformed(evt);
            }
        });

        CleanButton.setText("Clean Window");
        CleanButton.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                CleanButtonActionPerformed(evt);
            }
        });

        LoadFileButton.setText("Load File");
        LoadFileButton.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                LoadFileButtonActionPerformed(evt);
            }
        });

        SaveFileButton.setText("Save to File");
        SaveFileButton.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                SaveFileButtonActionPerformed(evt);
            }
        });

        LoadedFileText.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                LoadedFileTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout StringCipherPanelLayout = new javax.swing.GroupLayout(StringCipherPanel);
        StringCipherPanel.setLayout(StringCipherPanelLayout);
        StringCipherPanelLayout.setHorizontalGroup(
            StringCipherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StringCipherPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(StringCipherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextToCipherLabel)
                    .addComponent(ScrollPanelNoCipher, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CipheredTextLabel)
                    .addComponent(ScrollPanelCiphered, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addGroup(StringCipherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StringCipherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(StringCipherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(StringCipherPanelLayout.createSequentialGroup()
                                .addComponent(CipherButton)
                                .addGap(54, 54, 54)
                                .addComponent(CleanButton))
                            .addComponent(LoadFileButton)
                            .addComponent(SaveFileButton)
                            .addComponent(LoadedFileText, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(StringCipherPanelLayout.createSequentialGroup()
                            .addComponent(KeyText, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)))
                    .addComponent(KeyLabel))
                .addGap(50, 50, 50))
        );
        StringCipherPanelLayout.setVerticalGroup(
            StringCipherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StringCipherPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(TextToCipherLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(StringCipherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ScrollPanelNoCipher, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(StringCipherPanelLayout.createSequentialGroup()
                        .addComponent(LoadedFileText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LoadFileButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(KeyLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(KeyText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(StringCipherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CipherButton)
                    .addComponent(CleanButton))
                .addGap(3, 3, 3)
                .addComponent(CipheredTextLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(StringCipherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollPanelCiphered, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SaveFileButton))
                .addContainerGap(132, Short.MAX_VALUE))
        );

        FileCipherPanel.setToolTipText("FileCipherPanel");
        FileCipherPanel.setName("FileCipherPanel"); // NOI18N

        javax.swing.GroupLayout FileCipherPanelLayout = new javax.swing.GroupLayout(FileCipherPanel);
        FileCipherPanel.setLayout(FileCipherPanelLayout);
        FileCipherPanelLayout.setHorizontalGroup(
            FileCipherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 911, Short.MAX_VALUE)
        );
        FileCipherPanelLayout.setVerticalGroup(
            FileCipherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );

        File.setText("File");

        OpenFile.setText("Open File");
        File.add(OpenFile);

        Close.setText("Close");
        File.add(Close);

        MenuBar.add(File);

        Tools.setText("Tools");

        About.setText("About");
        Tools.add(About);

        MenuBar.add(Tools);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 911, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 43, Short.MAX_VALUE)
                    .addComponent(StringCipherPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 44, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(FileCipherPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 735, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 18, Short.MAX_VALUE)
                    .addComponent(StringCipherPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 19, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 18, Short.MAX_VALUE)
                    .addComponent(FileCipherPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 19, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CipherButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_CipherButtonActionPerformed
        // Key :
        // W49QS8M6ugTd5Scn3rQuqLGm4CXbz98N7YY44ZJeZcNTpA6psuS9r8WLe44vER37gf2HkqBd76a236V54yY9LEuq6rb78cC68pHJ6rKbn9y4r74qWf4TS5TaF8Uv7Kp32Ft7Z8Wcw6r28sG7iTv4Rd

        bc = new BinaryConverter(KeyText.getText());
        int nThreads = Runtime.getRuntime().availableProcessors();

        if(TextToCipher.getText().length() < nThreads)
            nThreads = TextToCipher.getText().length() / 4;
        else if(TextToCipher.getText().length() == 1)
            nThreads = 1;
        
        int start = 0, frame = bc.automaton().length / nThreads, end = frame;
        tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(nThreads);
        ca1DSimulator.setCA(bc.automaton(), 2, 1, false, 55, TextToCipher.getText().length(), nThreads);

        for(int i = 0; i < nThreads; ++i)
        {
            tpe.execute(new ca1DSimulator(start, end));
            start = end + 1;
            end += frame;
        }

        tpe.shutdown();
        try
        {
            if(!tpe.awaitTermination(10, TimeUnit.SECONDS))
                tpe.shutdownNow();
        } catch(InterruptedException e) {}

        int[][] autoStatus = ca1DSimulator.status();
        int[] midCellStatus = new int[autoStatus.length];

        for(int i = 0; i < autoStatus.length; ++i)
            for(int j = 0; j < autoStatus[i].length; ++j)
                midCellStatus[i] = autoStatus[i][499];

        bc = new BinaryConverter(TextToCipher.getText(), midCellStatus);
        bc.XOR();
        
        CipheredText.setText(bc.toString());
            
    }//GEN-LAST:event_CipherButtonActionPerformed

    private void CleanButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_CleanButtonActionPerformed
        CipheredText.setText("");
        TextToCipher.setText("");
        KeyText.setText("");
        LoadedFileText.setText("");
    }//GEN-LAST:event_CleanButtonActionPerformed

    private void LoadFileButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_LoadFileButtonActionPerformed
  
    JFileChooser load = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
    
    load.setFileFilter(filter);
    
    int selection = load.showOpenDialog(this);
        
        if(selection == JFileChooser.APPROVE_OPTION)
        {
            File file = load.getSelectedFile();
            LoadedFileText.setText(file.getAbsolutePath());
     
            try(FileReader fr = new FileReader(file))
            {
                String text = "";
                int value = fr.read();
                
                do
                {
                    text = text + (char)value;
                    value = fr.read();
                }while(value != -1);
                
                TextToCipher.setText(text);
                text = null;
                fr.close();
            } catch (FileNotFoundException ex) {} 
            catch (IOException ex) {}
        }
    }//GEN-LAST:event_LoadFileButtonActionPerformed

    private void LoadedFileTextActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_LoadedFileTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LoadedFileTextActionPerformed

    private void SaveFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveFileButtonActionPerformed
    JFileChooser load = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
    
    load.setFileFilter(filter);
    
    int selection = load.showSaveDialog(this);
        
        if(selection == JFileChooser.APPROVE_OPTION)
        {
            File file = load.getSelectedFile();
     
            try(FileWriter fw = new FileWriter(file))
            {
                fw.write(CipheredText.getText());
                fw.close();
            } catch (FileNotFoundException ex) {} 
            catch (IOException ex) {}
        }
    }//GEN-LAST:event_SaveFileButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BaseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new BaseFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem About;
    private javax.swing.JButton CipherButton;
    private javax.swing.JTextPane CipheredText;
    private javax.swing.JLabel CipheredTextLabel;
    private javax.swing.JButton CleanButton;
    private javax.swing.JMenuItem Close;
    private javax.swing.JMenu File;
    private javax.swing.JPanel FileCipherPanel;
    private javax.swing.JLabel KeyLabel;
    private javax.swing.JTextField KeyText;
    private javax.swing.JButton LoadFileButton;
    private javax.swing.JTextField LoadedFileText;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenuItem OpenFile;
    private javax.swing.JButton SaveFileButton;
    private javax.swing.JScrollPane ScrollPanelCiphered;
    private javax.swing.JScrollPane ScrollPanelNoCipher;
    private javax.swing.JPanel StringCipherPanel;
    private javax.swing.JTextPane TextToCipher;
    private javax.swing.JLabel TextToCipherLabel;
    private javax.swing.JMenu Tools;
    private BinaryConverter bc;
    private ca1DSimulator automaton;
    private ThreadPoolExecutor tpe;
    // End of variables declaration//GEN-END:variables
}
