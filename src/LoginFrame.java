import  javax.swing. *;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LoginFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("Reset");
    JCheckBox showPassword = new JCheckBox("Show Password");
    LoginFrame(){

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public  void  setLayoutManager(){
        container.setLayout(null);
    }
    public  void  setLocationAndSize(){
        userLabel.setBounds(50,150,100,30);
        passwordLabel.setBounds(50,220,100,30);
        userTextField.setBounds(150,150,150,30);
        passwordField.setBounds(150,220,150,30);
        showPassword.setBounds(150,250,150,30);
        loginButton.setBounds(50,300,100,30);
        resetButton.setBounds(200,300,100,30);

    }

    public  void  addComponentsToContainer()
    {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
    }

    public  void  addActionEvent(){
loginButton.addActionListener(this);
resetButton.addActionListener(this);
showPassword.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==loginButton)
        {
            String userText;
            String passwordText;
            userText = userTextField.getText();
            passwordText = passwordField.getText();



            try {

                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javalogin","root","");
                String sql = "select * from logindatabase where username =? and password =?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, userText);
                pst.setString(2,passwordText);
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "Username and Password Matched");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Username and password donot match");
                    userTextField.setText("");
                    passwordField.setText("");
                }



            }
            catch (Exception a) {

                JOptionPane.showMessageDialog(null, a);


            }
//            if(userText.equalsIgnoreCase("dipesh")&&passwordText.equalsIgnoreCase("12345"))
//            {
//                JOptionPane.showMessageDialog(this, "Login Successful");
//
//            }
//            else{
//                JOptionPane.showMessageDialog(this, "Invalid Username and Password");
//            }


        }
        if(e.getSource()==resetButton){
            userTextField.setText("");
            passwordField.setText("");
        }
        if (e.getSource()==showPassword){
            if(showPassword.isSelected()){
                passwordField.setEchoChar((char) 0);
            }
            else
            {
            passwordField.setEchoChar('*');

            }
        }




    }
}
