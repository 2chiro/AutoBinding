package nishimoto.yoshiken;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private String path;

	public String getPath(){
		return path;
	}

	public void setPath(String path){
		this.path = path;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("Auto Binding");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 30));

		JButton Go = new JButton("実行");
		Go.addActionListener(this);
		Go.setActionCommand("Go");
		panel.add(Go);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(16);
		contentPane.add(panel_1, BorderLayout.NORTH);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(textField);
		textField.setColumns(30);

		JButton SelectFile = new JButton("参照");
		SelectFile.addActionListener(this);
		SelectFile.setActionCommand("SelectFile");
		panel_1.add(SelectFile);
	}

	public void actionPerformed(ActionEvent e){
		String cmd = e.getActionCommand();
		String path_a = null;
		if(cmd.equals("SelectFile")){
			JFileChooser fc = new JFileChooser();
			int selected = fc.showOpenDialog(this);
			if(selected == JFileChooser.APPROVE_OPTION){
				File file = fc.getSelectedFile();
				path_a = file.getAbsolutePath();
				textField.setText(path_a);
			}
		}else if(cmd.equals("Go")){
			String path_b = textField.getText();
			setPath(path_b);
			if(getPath() == null){
				JLabel label1 = new JLabel("ファイル参照から絶対パスを指定してください");
				JOptionPane.showMessageDialog(this, label1);
			}
			else{
				goBinding();
			}
		}
	}

	public void goBinding(){
		String outname = null;
		JFileChooser fc1 = new JFileChooser();
		int selected1 = fc1.showSaveDialog(this);
		if(selected1 == JFileChooser.APPROVE_OPTION){
			File file1 = fc1.getSelectedFile();
			outname = file1.getAbsolutePath();
		}
		TestFileRead.input(getPath());
		TestFileRead.dataArrange();

		int[] ei = TestFileRead.getEdge();
		int[] st = TestFileRead.getStart();
		int[] ed = TestFileRead.getEnd();
		int[] vt = TestFileRead.getVertex();
		String[] ty = TestFileRead.getType();
		int[] lf = TestFileRead.getLife();
		int a = TestFileRead.getAdd();
		int s = TestFileRead.getSub();
		int m = TestFileRead.getMult();
		int c = TestFileRead.getComp();

		OperationLEA.Basic(a, s, m, c, vt, ty, lf);
		RegisterLEA.Basic(ei, st, ed);
		TestFileWrite.output(outname);
		TestFileRead.resetRC();
	}
}
