package nishimoto.yoshiken;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private String path;
	private JRadioButton[] radio;
	private int mode;

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

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);

		radio = new JRadioButton[2];
		radio[0] = new JRadioButton("LEAバインディング", true);
		radio[1] = new JRadioButton("CO判定");

		ButtonGroup group = new ButtonGroup();
		group.add(radio[0]);
		group.add(radio[1]);

		panel_2.add(radio[0]);
		panel_2.add(radio[1]);
	}

	public void actionPerformed(ActionEvent e){
		String cmd = e.getActionCommand();
		String path_a = null;
		if(cmd.equals("SelectFile")){
			JFileChooser fc = new JFileChooser();
			FileFilter filter1 = new FileNameExtensionFilter("スケジューリング済みのDFG(*.dfg)", "dfg");
			FileFilter filter2 = new FileNameExtensionFilter("DATファイル(*.dat)", "dat");
			fc.addChoosableFileFilter(filter1);
			fc.addChoosableFileFilter(filter2);
			fc.setAcceptAllFileFilterUsed(false);
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
				if(radio[0].isSelected()){
					mode = 0;
				}
				else if(radio[1].isSelected()){
					mode = 1;
				}
				goBinding();
			}
		}
	}

	public void goBinding(){
		String outname = null;
		JFileChooser fc1 = new JFileChooser();
		FileFilter filter3 = new FileNameExtensionFilter("DATファイル(*.dat)", "dat");
		fc1.addChoosableFileFilter(filter3);
		fc1.setAcceptAllFileFilterUsed(false);
		int selected1 = fc1.showSaveDialog(this);
		if(selected1 == JFileChooser.APPROVE_OPTION){
			File file1 = fc1.getSelectedFile();
			outname = file1.getAbsolutePath();
			if(!outname.toString().substring(outname.toString().length() -4).equals(".dat")){
				outname = outname + ".dat";
			}
		}
		FileRead.input(getPath());
		FileRead.dataArrange();

		int[] ei = FileRead.getEdge();
		int[] v1 = FileRead.getVer1();
		int[] v2 = FileRead.getVer2();
		int[] vt = FileRead.getVertex();
		String[] ty = FileRead.getType();
		int[] lf = FileRead.getLife();
		int a = FileRead.getAdd();
		int s = FileRead.getSub();
		int m = FileRead.getMult();
		int d = FileRead.getDiv();

		LifetimeAnalysis.Basic(ei, v1, v2, ei, ty, lf);
		int[] st = LifetimeAnalysis.getStart();
		int[] ed = LifetimeAnalysis.getEnd();
		int[] ch = LifetimeAnalysis.getKab();
		int mt = LifetimeAnalysis.getMaxtime();

		if(mode == 0){
			OperationLEA.Basic(a, s, m, d, vt, ty, lf);
			RegisterLEA.Basic(ei, st, ed, ch, mt);
			FileWrite.output(outname);
			FileRead.resetRC();
		}
		else if(mode == 1){
			FindCOs.Basic(v1, v2, ty);
		}
	}
}
