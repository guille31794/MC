/**
 *
 * @author guillermogirongarcia
*/

import java.awt.Color;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BaseFrame extends javax.swing.JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates new form BaseFrame
     */
    public BaseFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ConfigOptionMenu = new javax.swing.ButtonGroup();
        SimulationPanel = new javax.swing.JPanel();
        DimensionLabel = new javax.swing.JLabel();
        DimensionText = new javax.swing.JTextField();
        SimulationLabel = new javax.swing.JLabel();
        GeneratorMenu = new javax.swing.JComboBox<>();
        GenerationLabel = new javax.swing.JLabel();
        GenerationText = new javax.swing.JTextField();
        RunButton = new javax.swing.JButton();
        NextButton = new javax.swing.JButton();
        StopButton = new javax.swing.JButton();
        PauseButton = new javax.swing.JButton();
        FramelLabel = new javax.swing.JLabel();
        FrameText = new javax.swing.JTextField();
        PopulationLabel = new javax.swing.JLabel();
        PopulationText = new javax.swing.JTextField();
        SpeedSlider = new javax.swing.JSlider();
        SlowLabel = new javax.swing.JLabel();
        NormalLabel = new javax.swing.JLabel();
        FastLabel = new javax.swing.JLabel();
        RandomConfig = new javax.swing.JRadioButton();
        IslandConfig = new javax.swing.JRadioButton();
        CannonConfig = new javax.swing.JRadioButton();
        ConfigText = new javax.swing.JTextField();
        ConfigLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Life Game");
        setMaximumSize(new java.awt.Dimension(1920, 1080));
        setMinimumSize(new java.awt.Dimension(600, 480));
        setName("BaseFrame");

        SimulationPanel.setMaximumSize(new java.awt.Dimension(800, 800));
        SimulationPanel.setMinimumSize(new java.awt.Dimension(200, 200));
        SimulationPanel.setPreferredSize(new java.awt.Dimension(800, 800));

        javax.swing.GroupLayout SimulationPanelLayout = new javax.swing.GroupLayout(SimulationPanel);
        SimulationPanel.setLayout(SimulationPanelLayout);
        SimulationPanelLayout.setHorizontalGroup(
            SimulationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        SimulationPanelLayout.setVerticalGroup(
            SimulationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        DimensionLabel.setText("Dimension:");

        SimulationLabel.setText("Simulation Frame");

        ConfigLabel.setVisible(false);
        ConfigText.setVisible(false);

        GeneratorMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Von Newman", "Moore"}));

        GenerationLabel.setText("Generations:");

        RunButton.setText("Run");
        RunButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RunButtonActionPerformed(evt);
            }
        });

        NextButton.setText("Next Gen.");
        NextButton.setEnabled(false);
        NextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextButtonActionPerformed(evt);
            }
        });

        StopButton.setText("Stop");
        StopButton.setEnabled(false);
        StopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopButtonActionPerformed(evt);
            }
        });

        PauseButton.setText("Pause");
        PauseButton.setEnabled(false);
        PauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PauseButtonActionPerformed(evt);
            }
        });

        FramelLabel.setText("Frame:");

        FrameText.setEditable(false);
        FrameText.setText("0");

        PopulationLabel.setText("Population:");

        PopulationText.setEditable(false);
        PopulationText.setText("0");

        SpeedSlider.setMaximum(2);
        SpeedSlider.setPaintLabels(true);
        SpeedSlider.setPaintTicks(true);
        SpeedSlider.setSnapToTicks(true);
        SpeedSlider.setToolTipText("");
        SpeedSlider.setValue(1);
        SpeedSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpeedSliderStateChanged(evt);
            }
        });

        SlowLabel.setText("Slow");

        NormalLabel.setText("Normal");

        FastLabel.setText("Fast");

        RandomConfig.setText("Random");
        RandomConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RandomConfigActionPerformed(evt);
            }
        });

        IslandConfig.setText("Island");
        IslandConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IslandConfigActionPerformed(evt);
            }
        });

        CannonConfig.setText("Cannon");
        CannonConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CannonConfigActionPerformed(evt);
            }
        });

        ConfigOptionMenu.add(RandomConfig);
        ConfigOptionMenu.add(IslandConfig);
        ConfigOptionMenu.add(CannonConfig);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SimulationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 878, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(GenerationLabel)
                            .addComponent(DimensionLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(DimensionText, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(GenerationText)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(SlowLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NormalLabel)
                            .addGap(48, 48, 48)
                            .addComponent(FastLabel))
                        .addComponent(SpeedSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(RandomConfig)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(GeneratorMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NextButton)
                                    .addComponent(RunButton)
                                    .addComponent(FramelLabel)
                                    .addComponent(PopulationLabel))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(FrameText)
                                    .addComponent(PopulationText)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(StopButton)
                                            .addComponent(PauseButton))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(ConfigText, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(IslandConfig)
                        .addComponent(CannonConfig)
                        .addComponent(ConfigLabel)))
                .addGap(25, 25, 25))
            .addGroup(layout.createSequentialGroup()
                .addGap(308, 308, 308)
                .addComponent(SimulationLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(SimulationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SimulationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DimensionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DimensionLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(GenerationLabel)
                            .addComponent(GenerationText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(GeneratorMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(RandomConfig)
                        .addGap(18, 18, 18)
                        .addComponent(IslandConfig)
                        .addGap(18, 18, 18)
                        .addComponent(CannonConfig)
                        .addGap(21, 21, 21)
                        .addComponent(ConfigLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ConfigText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RunButton)
                            .addComponent(StopButton))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NextButton)
                            .addComponent(PauseButton))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FrameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FramelLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PopulationLabel)
                            .addComponent(PopulationText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(SpeedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SlowLabel)
                            .addComponent(NormalLabel)
                            .addComponent(FastLabel))))
                .addContainerGap(223, Short.MAX_VALUE))
        );

        pack();
    }

    private void RunButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {   
        int dim = Integer.parseInt(DimensionText.getText());
        //dim = dim < 200 ? 200 : dim;
        //dim = dim > 800 ? 800 : dim;

        int mode = 0;

        if(islandFlag || cannonFlag)
            mode = Integer.parseInt(ConfigText.getText());

        board = new chessBoard(dim);
        generations = Integer.parseInt(GenerationText.getText());
        int nThreads = Runtime.getRuntime().availableProcessors(),
        frame = board.getLength() / nThreads, 
        start = 0, 
        end = frame;
        boundCondition = GeneratorMenu.getSelectedIndex();

        tpe = (ThreadPoolExecutor)Executors.newFixedThreadPool(nThreads);

        if(islandFlag)
        {
            for(int i = 0; i < nThreads; ++i)
            {
                tpe.execute(new LifeGameIsland(start, end, board, mode));
                start = end;
                end += frame;
            }

            tpe.execute(new LifeGameIsland(start, board.getLength(), board, mode));
        }
        else if(cannonFlag)
        {
            
        }
        else
        {
            for (int i = 0; i < nThreads; ++i) 
            {
                tpe.execute(new LifeGameRandom(start, end, board));
                start = end;
                end += frame;
            }

            tpe.execute(new LifeGameRandom(start, board.getLength(), board));
        }
        
        tpe.shutdown();
        try 
        {
            if(!tpe.awaitTermination(10, TimeUnit.SECONDS));
                tpe.shutdownNow();
        } catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        
        /*for(int i = 0; i < board.getLength(); ++i)
        {
            for (int j = 0; j < board.getLength(); ++j)
                System.out.print(board.board[i][j] + " ");

            System.out.println();
        }*/
            

        SimulationPanel.add(board);
        board.repaint();

        StopButton.setEnabled(true);
        NextButton.setEnabled(true);
        PauseButton.setEnabled(true);
    }

    private void StopButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {
        DimensionText.setText("");
        GenerationText.setText("");
        FrameText.setText("0");
        PopulationText.setText("0");
        StopButton.setEnabled(false);
        NextButton.setEnabled(false);
        PauseButton.setEnabled(false);
        SimulationPanel.remove(board);
        SimulationPanel.repaint();
    }

    private void NextButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void PauseButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void RandomConfigActionPerformed(java.awt.event.ActionEvent evt) 
    {
        ConfigText.setVisible(false);
        ConfigLabel.setVisible(false);
        cannonFlag = false;
        islandFlag = false;
    }

    private void IslandConfigActionPerformed(java.awt.event.ActionEvent evt) 
    {
        ConfigText.setVisible(true);
        ConfigLabel.setVisible(true);
        ConfigLabel.setText("Islands:");
        cannonFlag = false;
        islandFlag = true;
    }

    private void CannonConfigActionPerformed(java.awt.event.ActionEvent evt) 
    {
        ConfigText.setVisible(true);
        ConfigLabel.setVisible(true);
        ConfigLabel.setText("Cannons:");
        islandFlag = false;
        cannonFlag = true;
    }

    private void SpeedSliderStateChanged(javax.swing.event.ChangeEvent evt) {
        // TODO add your handling code here:
    }//GEN-LAST:event_SpeedSliderStateChanged

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BaseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BaseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BaseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BaseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BaseFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JRadioButton CannonConfig;
    private javax.swing.JLabel ConfigLabel;
    private javax.swing.ButtonGroup ConfigOptionMenu;
    private javax.swing.JTextField ConfigText;
    private javax.swing.JLabel DimensionLabel;
    private javax.swing.JTextField DimensionText;
    private javax.swing.JLabel FastLabel;
    private javax.swing.JTextField FrameText;
    private javax.swing.JLabel FramelLabel;
    private javax.swing.JLabel GenerationLabel;
    private javax.swing.JTextField GenerationText;
    private javax.swing.JComboBox<String> GeneratorMenu;
    private javax.swing.JRadioButton IslandConfig;
    private javax.swing.JButton NextButton;
    private javax.swing.JLabel NormalLabel;
    private javax.swing.JButton PauseButton;
    private javax.swing.JLabel PopulationLabel;
    private javax.swing.JTextField PopulationText;
    private javax.swing.JRadioButton RandomConfig;
    private javax.swing.JButton RunButton;
    private javax.swing.JLabel SimulationLabel;
    private javax.swing.JPanel SimulationPanel;
    private javax.swing.JLabel SlowLabel;
    private javax.swing.JSlider SpeedSlider;
    private javax.swing.JButton StopButton;
    private ThreadPoolExecutor tpe;
    private int generations, config, boundCondition;
    private randomGenerator rg;
    private boolean islandFlag, cannonFlag;
    private chessBoard board;
}
