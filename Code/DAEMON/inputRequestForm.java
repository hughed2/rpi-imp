//DAEMON
//inputRequestForm.java
//Written by The Fightin' Mongooses
//Sorry for eclectic conventions... Most of the code was GUI generated and disallows edits.
//I've fixed it up as best as I can to look nice, but there might still be occasional oddities

package daemon;
import java.io.*; //For file input/output
import javax.swing.DefaultListModel;


public class inputRequestForm extends javax.swing.JFrame {

    String username = "";
    public DefaultListModel algModel = new DefaultListModel();

    public void loginCheck() { //If the login button is pressed, make sure logins are working
        try {
            FileInputStream fin = new FileInputStream("list.usr"); //We need to check to make sure the user exists
            BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
            String line = null;

            while((line = reader.readLine()) != null) {
                if(jTextField1.getText().equals(line)) { //We've found the user
                    line = reader.readLine();
                    if(jPasswordField1.getText().toString().equals(line)) { //If the password is correct, then move to welcome screen, set username
                        jButton1.setEnabled(true);
                        jButton3.setEnabled(true);
                        jButton7.setEnabled(true);
                        username = jTextField1.getText();
                        jDialog1.setVisible(false);
                        fin.close();

                        fin = new FileInputStream(username + ".usr");
                        reader = new BufferedReader(new InputStreamReader(fin));
                        while(reader.ready()) {
                            algModel.add(algModel.size(),reader.readLine());
                        }


                        return;
                    }
                    else { //If the password is wrong, show warning
                        jLabel7.setText("Error! Bad password!");
                        jDialog2.setVisible(true);
                        jDialog1.setVisible(false);
                        fin.close();
                        return;
                    }
                }
                else reader.readLine(); //Get rid of the extra password line
            }
            jLabel7.setText("Error! User doesn't exist.");
            jDialog2.setVisible(true);
            jDialog1.setVisible(false);
            fin.close(); //Make sure to close the file
        }
        catch (IOException e) {
            System.err.println ("Error opening list.usr"); //Some mistake... crash gently
            System.exit(-1);

        }
    }

    public void createAccount() { //If the account creation button is pressed, then create an account
        if(jTextField1.getText().equals("list")) { //Make sure it's not list... We don't want to overwrite list.usr
            jDialog1.setVisible(false);
            jLabel7.setText("Error! Name already in use!");
            jDialog2.setVisible(true);
            return;
        }
        if(jPasswordField1.getText().toString().length() < 7) { //Make sure the password's not too short
            jDialog1.setVisible(false);
            jLabel7.setText("Error! Password too short!");
            jDialog2.setVisible(true);
            return;
        }

        try { //Now we're making sure there's no duplicate user
            FileInputStream fin = new FileInputStream("list.usr");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
            String line = null;

            while((line = reader.readLine()) != null) {
                if(jTextField1.getText().equals(line)) {
                    line = reader.readLine();
                    jDialog1.setVisible(false);
                    jLabel7.setText("Error! Name already in use!");
                    jDialog2.setVisible(true);
                    fin.close();
                    return;
                }
                else reader.readLine(); //Get rid of the extra password line
            }
            fin.close(); //Make sure to close the file
        }
        catch (IOException e) {
            System.err.println ("Error opening list.usr"); //Some mistake... crash gently
            System.exit(-1);

        }


        try {
            FileWriter file = new FileWriter("list.usr",true); //Open up the list.usr file and write out the username
            BufferedWriter fout = new BufferedWriter(file);
            fout.write(jTextField1.getText());
            fout.newLine();

            fout.write(jPasswordField1.getText().toString());
            fout.newLine();
            fout.close();
            file.close();

            new File(jTextField1.getText() + ".usr").createNewFile(); //Create the user's file

            jDialog1.setVisible(false); //Everything's all set, so let's go to the welcome screen
            jButton1.setEnabled(true);
            jButton3.setEnabled(true);
            username = jTextField1.getText();

            return;
        }
        catch (IOException e) {
            System.err.println ("Error opening usr file"); //Some mistake... crash gently
            System.exit(-1);
        }
    }

    public void loadAlgorithm() { //If load algorithm is selected, we need to copy it into the tmp file
        try { //Grab the appropriate algorithm file
            FileInputStream fin = new FileInputStream(jList1.getSelectedValue().toString() + ".alg");
            FileOutputStream fout = new FileOutputStream("tmp.alg1");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
            while(reader.ready()) { //Copy everything out
                new PrintStream(fout).println(reader.readLine());
            }
            fin.close();
            fout.close();
        }
        catch (IOException e) { //If it doesn't then no problem, catch the exception and move on.
            System.err.println("Error copying algorithm file!");
        }
        this.setVisible(false); //With the file grabbed, let's  boogy on over to the getOptions
        new getOptionsForm(username).setVisible(true);
    }

    /** Creates new form Daemon1 */
    public inputRequestForm() {
        initComponents();
        this.setLocationRelativeTo( null );
        if(!new File("list.usr").exists()){
            try {
                new File("list.usr").createNewFile();
            } catch (IOException ex) {
                System.err.println("Error creating list.usr!");
                System.exit(-1);
            }
        }
        jDialog1.setVisible(true);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jDialog2 = new javax.swing.JDialog();
        jLabel7 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        jDialog1.setLocationRelativeTo( null );
        jDialog1.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        jDialog1.setAlwaysOnTop(true);
        jDialog1.setMinimumSize(new java.awt.Dimension(350, 195));
        jDialog1.setModal(true);
        jDialog1.setResizable(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel4.setText("Please login to use DAEMON!");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel5.setText("User name:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel6.setText("Password:");

        jButton2.setText("Login");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Create Account");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText("Cancel");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDialog1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton2)
                    .addComponent(jButton6))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jDialog2.setLocationRelativeTo( null );
        jDialog2.setAlwaysOnTop(true);
        jDialog2.setMinimumSize(new java.awt.Dimension(325, 150));
        jDialog2.setResizable(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel7.setText("That name's already in use!");

        jButton5.setText("Okay");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog2Layout.createSequentialGroup()
                .addContainerGap(131, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(128, 128, 128))
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                center(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36));
        jLabel1.setText("Welcome to DAEMON!");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel2.setText("DAEMON is an interactive module allowing for algorithm");

        jButton1.setText("Use wizard");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Cancel");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel3.setText("development or modification. Enter \"Use wizard\" to begin!");

        jButton7.setText("Load Algorithm");
        jButton7.setEnabled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jList1.setModel(algModel);
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 427, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton7)
                    .addComponent(jButton3))
                .addGap(35, 35, 35))
        );

        getAccessibleContext().setAccessibleParent(jDialog1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        new getOptionsForm(username).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void center(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_center
        this.setLocationRelativeTo( null ); //Make sure it's centered
    }//GEN-LAST:event_center

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new File("tmp.arg1").delete();
        System.exit(1); //This is the cancel button
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        loginCheck(); //This is the login button
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jDialog2.setVisible(false); //For the error dialog box... Make it disappear, and the login dialog box reappear
        jDialog1.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        new File("tmp.arg1").delete(); //This is the cancel button
        System.exit(1);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        createAccount(); //This is the Create Account button
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        loadAlgorithm(); //Load the appropriate algorithm
    }//GEN-LAST:event_jButton7ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inputRequestForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList jList1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
