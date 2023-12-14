package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LoginRegisterApp {
    
static JFrame frame;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Login/Register");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));

        // 添加Logo
        ImageIcon logoIcon = new ImageIcon("/src/DaysCare/Img/logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        mainPanel.add(logoLabel, getConstraints(0, 0, 2, 1, GridBagConstraints.CENTER));
        // 添加用户名和密码输入框
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        mainPanel.add(new JLabel("username:"), getConstraints(0, 1, 1, 1, GridBagConstraints.EAST));
        mainPanel.add(usernameField, getConstraints(1, 1, 1, 1, GridBagConstraints.WEST));
        mainPanel.add(new JLabel("password:"), getConstraints(0, 2, 1, 1, GridBagConstraints.EAST));
        mainPanel.add(passwordField, getConstraints(1, 2, 1, 1, GridBagConstraints.WEST));

    
        // 添加注册按钮
        JButton registerButton = new JButton("Register");
        mainPanel.add(registerButton, getConstraints(0, 4, 2, 1, GridBagConstraints.CENTER));

        // 添加登录按钮
        JButton loginButton = new JButton("Login");
        mainPanel.add(loginButton, getConstraints(0, 5, 2, 1, GridBagConstraints.CENTER));

        frame.add(mainPanel, BorderLayout.CENTER);

        // 设置背景图
        // ImageIcon backgroundIcon = new ImageIcon("/6200Final/src/DaysCare/Img/logo.png");
        ImageIcon backgroundIcon = new ImageIcon(LoginRegisterApp.class.getResource("/DaysCare/Img/logo.png"));
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        frame.add(backgroundLabel, BorderLayout.NORTH);


        // 注册按钮点击事件
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 打开注册页面
                openRegisterPage();
            }
        });
        // // 注册按钮点击事件
        // registerButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         // 在这里添加处理注册按钮点击事件的代码
        //         JOptionPane.showMessageDialog(frame, "注册功能尚未实现！");
        //     }
        // });

        // 登录按钮点击事件
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                // 在这里添加连接到本地MySQL数据库和进行账号密码校验的代码
                boolean loginSuccessful = authenticateUser(username, password);

                if (loginSuccessful) {
                    MainJFrame mainframe=new MainJFrame();
                    mainframe.setVisible(true);
                    frame.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(frame, "Login Failed, please check your username and password！");
                }
            }
        });

        frame.setVisible(true);
    }

    private static GridBagConstraints getConstraints(int gridx, int gridy, int gridwidth, int gridheight, int anchor) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridwidth;
        constraints.gridheight = gridheight;
        constraints.anchor = anchor;
        constraints.insets = new Insets(5, 5, 5, 5);
        return constraints;
    }
    

// 在 openRegisterPage 方法中的注册页面添加图像
private static void openRegisterPage() {
    JFrame registerFrame = new JFrame("Register");
    registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    registerFrame.setSize(800, 800);
    registerFrame.setLayout(new BorderLayout());

    JPanel registerPanel = new JPanel();
    registerPanel.setLayout(new GridBagLayout());

    // 添加Logo
    ImageIcon logoIcon = new ImageIcon(LoginRegisterApp.class.getResource("/DaysCare/Img/logo.png"));
    JLabel logoLabel = new JLabel(logoIcon);
    registerPanel.add(logoLabel, getConstraints(0, 0, 2, 1, GridBagConstraints.CENTER));

    // 添加用户名、密码和确认密码输入框
    JTextField usernameField = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);
    JPasswordField confirmPasswordField = new JPasswordField(20);
    registerPanel.add(new JLabel("Username:"), getConstraints(0, 1, 1, 1, GridBagConstraints.EAST));
    registerPanel.add(usernameField, getConstraints(1, 1, 1, 1, GridBagConstraints.WEST));
    registerPanel.add(new JLabel("Password:"), getConstraints(0, 2, 1, 1, GridBagConstraints.EAST));
    registerPanel.add(passwordField, getConstraints(1, 2, 1, 1, GridBagConstraints.WEST));
    registerPanel.add(new JLabel("Confirm Password:"), getConstraints(0, 3, 1, 1, GridBagConstraints.EAST));
    registerPanel.add(confirmPasswordField, getConstraints(1, 3, 1, 1, GridBagConstraints.WEST));

    // 添加注册和返回登录按钮
    JButton registerButton = new JButton("Register");
    registerPanel.add(registerButton, getConstraints(0, 5, 2, 1, GridBagConstraints.CENTER));

    JButton returnLoginButton = new JButton("Return to Login");
    registerPanel.add(returnLoginButton, getConstraints(0, 6, 2, 1, GridBagConstraints.CENTER));

    registerFrame.add(registerPanel, BorderLayout.CENTER);

    // 注册按钮点击事件
    registerButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            char[] confirmPasswordChars = confirmPasswordField.getPassword();
            String password = new String(passwordChars);
            String confirmPassword = new String(confirmPasswordChars);
    
            // 在这里添加注册用户的代码
            if (registerUser(username, password, confirmPassword)) {
                JOptionPane.showMessageDialog(registerFrame, "Registration Successful");
                // 注册成功后可以关闭注册窗口，或者进行其他操作
            } else {
                JOptionPane.showMessageDialog(registerFrame, "Registration Failed, please check your input!");
            }
        }
    });

    // 返回登录按钮点击事件
    returnLoginButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 返回登录页面
            registerFrame.dispose(); // 关闭注册页面
            createAndShowGUI(); // 打开登录页面
        }
    });

    registerFrame.setVisible(true);
}

private static boolean registerUser(String username, String password, String confirmPassword) {
    if (!password.equals(confirmPassword)) {
        System.out.println("密码和确认密码不匹配。");
        return false;
    }

    String resourcePath = "/DaysCare/Data/user.txt";

    try {
        // 读取原文件内容
        List<String> lines = Files.readAllLines(Paths.get(LoginRegisterApp.class.getResource(resourcePath).toURI()), StandardCharsets.UTF_8);

        // 在列表末尾添加新用户信息
        lines.add(username + "," + password);

        // 使用绝对路径获取文件
        Path filePath = Paths.get(LoginRegisterApp.class.getResource(resourcePath).toURI());

        // 将更新后的内容写回文件（覆盖整个文件）
        Files.write(filePath, lines, StandardCharsets.UTF_8);

        System.out.println("用户注册成功：" + username);
        return true;

    } catch (IOException | URISyntaxException e) {
        e.printStackTrace();
        return false;
    }
}


    private static boolean authenticateUser(String username, String password) {
    // Replace these values with your MySQL database connection details
    // String url = "jdbc:mysql://localhost:3306/test";
    // String user = "root";
    // String dbPassword = "Liuxuanli123";
    // String csvFilePath = "/Users/xuanliliu/Documents/GitHub/6200FinalGroupF/src/DaysCare/Data/user.txt";
    InputStream inputStream = LoginRegisterApp.class.getResourceAsStream("/DaysCare/Data/user.txt");
     try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String csvUsername = values[0];
                String csvPassword = values[1];
                System.out.println(csvUsername+csvPassword);
                if (username.equals(csvUsername) && password.equals(csvPassword)) {
                    System.out.println("Login successful for user: " + username);
                    return true;
                }
            }
            System.out.println("Login failed for user: " + username);
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //     try {
    //         Class.forName("com.mysql.cj.jdbc.Driver");
    //     } catch (ClassNotFoundException e) {
    //         e.printStackTrace();
    //         return false; // 如果无法加载驱动程序，登录失败
    //     }
        
    // try {
    //     Connection connection = DriverManager.getConnection(url, user, dbPassword);
    //     System.out.println("Database connection established.");

    //     String query = "SELECT * FROM users WHERE Username=? AND Password=?";
    //     try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    //         preparedStatement.setString(1, username);
    //         preparedStatement.setString(2, password);

    //         ResultSet resultSet = preparedStatement.executeQuery();

    //         if (resultSet.next()) {
    //             System.out.println("Login successful for user: " + username);
    //             return true; // If there is a match, login is successful
    //         } else {
    //             System.out.println("Login failed for user: " + username);
    //             return false;
    //         }
    //     }
    // } catch (SQLException ex) {
    //     ex.printStackTrace();
    //     return false;
    //}
}
