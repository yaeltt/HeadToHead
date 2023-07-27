/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.HeadlessException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import project.classes.Client;
import project.classes.Message;
import project.classes.Play;
import project.classes.Question;
import project.classes.QuestionWhileGame;
import project.classes.Request;
import project.classes.TypeOfRequest;
import project.classes.User;

/**
 *
 * @author Yael_Tubul
 */
public class Game extends javax.swing.JFrame {

    private Client client;
    private User thisUser;
    private User other;
    private Question myQuestion;
    private List<Message> listMessages;
    private int counter;
    Color color1 = new Color(252, 158, 0);
    Color color2 = new Color(0, 172, 124);

    public Game(Client c, User thisUser, User other) {
        client = c;
        this.thisUser = thisUser;
        this.other = other;
        listMessages = new ArrayList<Message>();
        initComponents();
        jTextArea2.setEditable(false);
        jTextArea2.setBorder(null);
        jButton1.setBackground(new Color(0, 0, 0, 0));
        jButton1.setIcon((new ImageIcon(getClass().getResource("/images/send.png"))));
        jTextField1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jLabel14.setText(thisUser.getUserName());
        jLabel4.setText(other.getUserName());
        jLabel4.setForeground(color1);
        jLabel1.setText(thisUser.getUserName());
        jLabel1.setForeground(color2);
        jTextArea2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        myQuestion = (Question) client.read();
        writeQuestion();
        listenToMessages();
    }

    private void listenToMessages() {

        Thread t = new Thread(() -> {
            while (true) {
                Object obj = client.read();
                if (obj instanceof String) {
                    JOptionPane.showMessageDialog(this, obj);
                    jTextField1.setVisible(false);
                    jButton1.setVisible(false);
                    jLabel13.setText("המשחק נגמר");
                    jTextArea2.setBackground(null);
                    client.sendMassage(new Request(TypeOfRequest.StopConnection, null));
                } else if (obj instanceof Message) {
                    Message m = (Message) obj;
                   
                    if (m.getAnsCode() == thisUser.getId()) {
                        jTextArea2.append(thisUser.getUserName() + "\n");
                        jTextArea2.setBackground(color1);
                         jLabel5.setText(m.getPointsToU1()+"");
                        jLabel6.setText(m.getPointsToU2()+"");
                        jTextArea2.setFont(new Font("Arial", Font.PLAIN, 18));
                        jTextArea2.append(m.getAns());
                        if (m.GetIsGoodAnswer()) {
                            jTextArea2.append("     V");
                        } else {
                            jTextArea2.append("     X");
                        }

                        jTextArea2.append("\n" + "\n");

                    } else {
                        //  jTextArea2.setForeground(color2);
                        jLabel5.setText(m.getPointsToU2()+"");
                        jLabel6.setText(m.getPointsToU1()+"");
                        jTextArea2.setFont(new Font("Arial", Font.PLAIN, 18));
                        jTextArea2.setBackground(color2);
                        jTextArea2.append("                   " + other.getUserName() + "\n");
                        jTextArea2.append("                   " + m.getAns());
                        if (m.GetIsGoodAnswer()) {
                            jTextArea2.append("     V");
                        } else {
                            jTextArea2.append("     X");
                        }
                        jTextArea2.append("\n" + "\n");
                    }
                    
                    if (m.getAnsCode() == thisUser.getId()) {
                        jTextField1.setEditable(false);
                        jTextField1.setText("");
                    } else {
                        jTextField1.setEditable(true);
                        jTextField1.setText("הקלד תשובה");
                    }

                } else {
                    myQuestion = (Question) obj;
                    jLabel13.setText("שים לב הוגרלה שאלה חדשה");

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jTextArea2.setBackground(null);
                    jTextArea2.setText("");
                    writeQuestion();
                }
            }
        }
        );
        t.start();

    }

    public void writeQuestion() {

        if (myQuestion.getCodeStarter() != thisUser.getId()) {
            jTextArea2.setBackground(color1);
            jTextField1.setEditable(false);
            jTextField1.setText("");
        } else {
            jTextArea2.setBackground(color2);
            jTextField1.setEditable(true);
            jTextField1.setText("הקלד תשובה");
        }
        jLabel13.setText(myQuestion.getValue());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 480, 69, 47));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField1.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField1.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 470, 620, 60));

        jLabel13.setBackground(new java.awt.Color(0, 0, 0));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 29)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("jLabel13");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("jLabel14");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setBorder(null);
        jScrollPane2.setViewportView(jTextArea2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 330, 370));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\יעל טובול\\Documents\\Studies\\B class\\Java\\netBeans\\homeWork\\project\\src\\images\\icon.png")); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel1.setBackground(new java.awt.Color(252, 158, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("me");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 290, 130, 30));

        jLabel4.setBackground(new java.awt.Color(0, 172, 124));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("otherPlayer");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 520, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 320, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 550, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\יעל טובול\\Documents\\Studies\\B class\\Java\\netBeans\\homeWork\\project\\src\\images\\rekka2.jpg")); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 0, 950, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTextField1.getText() != "") {
            Request r = new Request(TypeOfRequest.Answer, jTextField1.getText());
            client.sendMassageWithoutRead(r);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        client.sendMassage(new Request(TypeOfRequest.StopConnection, null));
    }//GEN-LAST:event_formWindowClosed

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        jTextField1.setText("");
    }//GEN-LAST:event_jTextField1MouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Game().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
