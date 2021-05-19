package org.haier.shop.util;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;

public class LockUtil implements Runnable{
    private static JFrame chooseFrame=new JFrame("选择类型");
    private static JFrame lockFrame = new JFrame("系统锁");
    private static JButton pswButton=new JButton("密码锁");
    private static JButton uButton=new JButton("U盘锁");
    private static JButton okButton=new JButton("确定");
    private static JButton refreshButton=new JButton("刷新");
    private static JLabel unlockLabel=new JLabel("解锁");
    private static Toolkit tool = Toolkit.getDefaultToolkit();
    private static Robot robot = null;
    private static final int width = (int) tool.getScreenSize().getWidth();
    private static final int height = (int) tool.getScreenSize().getHeight();
    private static int intMark = 0;
    private static String keyChar = null;
    private static String[] lowerUnLockValue = null;
    private static String type="";
    private static String filepath="";
    private static String lockpsw;
    static Thread checkThread;

    public void init(){
        chooseFrame.setLayout(null);
        chooseFrame.setSize(300, 150);
        chooseFrame.setResizable(false);
        chooseFrame.setLocationRelativeTo(null);
        chooseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pswButton.setBounds(75, 20, 150, 30);
        uButton.setBounds(75, 70, 150, 30);
        chooseFrame.add(pswButton);
        chooseFrame.setVisible(true);
        lockFrame.setLayout(null);
        lockFrame.setSize(width, height);
        lockFrame.setUndecorated(true);
        lockFrame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        lockFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkThread=new Thread(this);
        chooseListener();
    }
    
    public void run() {
        while(true){
            try {
                Thread.sleep(50);
                if(!lockFrame.isMaximumSizeSet() || !lockFrame.isActive()){
                    lockFrame.toFront();
                    lockFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void chooseListener(){
                    chooseFrame.setVisible(false);
                    try {
                        type="psw";
                        pswLock();
                        checkThread.start();
                    } catch (AWTException e1) {
                        e1.printStackTrace();
                    }
    }
    
    public void pswLock() throws AWTException{
    	String message="";
		String remessage="";
		do{
			message = JOptionPane.showInputDialog(lockFrame, "请输入解锁码:", "设置解锁码",
					JOptionPane.INFORMATION_MESSAGE);
			if (message==null || message.equals("")) {
				System.exit(0);
			}
			remessage = JOptionPane.showInputDialog(lockFrame, "请重复输入解锁码:", "设置解锁码",
					JOptionPane.INFORMATION_MESSAGE);
			if (remessage==null) {
				System.exit(0);
			}
			if(!message.equals(remessage)){
				JOptionPane.showMessageDialog(lockFrame, "两次输入解锁码不一致,请重新输入!", "错误", JOptionPane.WARNING_MESSAGE);
			}else {
				lowerUnLockValue = new String[message.length()];
				for (int i = 0; i < message.length(); i++) {
					lowerUnLockValue[i] = String.valueOf(message
							.toLowerCase().charAt(i));
				}
			}
		}while(!message.equals(remessage));
        lock();
    }
    
    public void ULock(){
        okButton.setBounds(25, 50, 60, 25);
        refreshButton.setBounds(110, 50, 60, 25);
        unlockLabel.setBounds(width/2, (int)(height*(1f/8f)+20), 60, 30);
        unlockLabel.setForeground(Color.black);
        refreshDisk();
        okButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1){
                    Random rnd=new Random();
                    lockpsw=String.valueOf(rnd.nextInt(1000000)+1000);
                    try {
                        FileWriter fw=new FileWriter(filepath);
                        fw.write(lockpsw);
                        fw.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    lock();
                    
                }
            }
        });
        refreshButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1){
                    refreshDisk();
                }
            }
        });
        unlockLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1){
                    if (new File(filepath).exists()) {
                        String psw = "";
                        try {
                            FileReader fr = new FileReader(filepath);
                            char byt[] = new char[7];
                            int len = fr.read(byt);
                            psw = new String(byt, 0, len);
                            fr.close();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        if (psw.equals(lockpsw)) {
                            new File(filepath).delete();
                            System.exit(0);
                        }
                    }
                }
            }
        });
        unlockLabel.addMouseMotionListener(new MouseMotionListener() {
            
            public void mouseMoved(MouseEvent e) {
                unlockLabel.setForeground(Color.white);
            }
            public void mouseDragged(MouseEvent e) {
            }
        });
    }

    public void refreshDisk(){
        File[] roots = File.listRoots();
        for (int i = 0; i < roots.length; i++) {
            if(roots[i].canRead()&&roots[i].canWrite()){
            }
        }
    }
    
    public void lock(){
        lockFrame.setContentPane(new DrawPanel());
        lockFrame.setVisible(true);
        lockMouse();
        try {
            robot = new Robot();
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
        lockFrame.addKeyListener(new KeyListener() {

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 524) {
                    robot.keyRelease(e.getKeyCode());
                    robot.keyPress(e.getKeyCode());
                    robot.keyRelease(e.getKeyCode());
                    robot.keyPress(27);
                    robot.keyRelease(27);
                } else if (e.getKeyCode() == 18) {
                    robot.keyRelease(e.getKeyCode());
                    robot.keyPress(524);
                    robot.keyRelease(524);
                    robot.keyPress(17);
                } else {
                    robot.keyRelease(e.getKeyCode());
                }
                lockFrame.toFront();
            }

            public void keyReleased(KeyEvent e) {
                robot.keyRelease(17);
            }

            public void keyTyped(KeyEvent e) {
                if(type.equals("psw")){
                    keyChar = String.valueOf(e.getKeyChar()).toLowerCase();
                    if (keyChar.equals(lowerUnLockValue[intMark])) {
                        intMark++;
                    } else {
                        intMark = 0;
                    }
                    if (intMark == (lowerUnLockValue.length)) {
                        System.exit(0);
                    }
                }
            }

        });
        lockFrame.addWindowFocusListener(new WindowFocusListener() {

            public void windowGainedFocus(WindowEvent e) {
                
            }

            public void windowLostFocus(WindowEvent e) {
                lockFrame.toFront();
            }

        });
        lockFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                lockFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
    }

    public void lockMouse() {
        lockFrame.addMouseMotionListener(new MouseMotionListener() {
            public void mouseMoved(MouseEvent e) {
                unlockLabel.setForeground(Color.black);
                Point mousepoint = MouseInfo.getPointerInfo().getLocation();
                int x=mousepoint.x,y=mousepoint.y;
                if(x>(width/2+100)){
                    robot.mouseMove((width/2-100), y);
                }else if(x<(width/2-100)){
                    robot.mouseMove((width/2+100), y);
                }else if(y>(height*(2f/8f))){
                    robot.mouseMove(x, (int)(height*(1f/8f)));
                }else if(y<(height*(1f/8f))){
                    robot.mouseMove(x, (int)(height*(2f/8f)));
                }
            }
            public void mouseDragged(MouseEvent e) {
            }
        });
    }

    class DrawPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        public void paint(Graphics g) {
            super.paint(g);
            setLayout(null);
            String[] about = { "系统锁", "版本: 2.1"};
                this.setBackground(Color.BLACK);
                g.setColor(Color.WHITE);
            g.setFont(new Font("幼圆", Font.BOLD, 200));
            g.drawString("锁", width/2-110, height/2+70);
            g.drawOval(width / 2 - 135, height / 2 - 135, 270, 270);
            g.setFont(new Font("幼圆", Font.PLAIN, 18));
            g.drawString(about[0], (int)(width*(3f/4f)), (int)(height*(3f/4f)));
            g.drawString(about[1], (int)(width*(3f/4f)), (int)(height*(3f/4f))+30);
            if(type.equals("u")){
                add(unlockLabel);
            }
        }
    }

    public static void main(String[] args) throws AWTException {
        new LockUtil().init();
    }
}

