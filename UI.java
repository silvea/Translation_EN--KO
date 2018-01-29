package trans;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class UI extends JFrame implements ActionListener {

	private JPanel contentPane;
	final JTextArea[] textArea = {new JTextArea(),new JTextArea(),new JTextArea()};
	JScrollPane[] scrollPane = {new JScrollPane(),new JScrollPane(),new JScrollPane()};

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UI() {
		
		setTitle("Translator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.JFrame_Set(0, 12, 10, 686, 166);
		this.JFrame_Set(1, 12, 235, 686, 151);
		this.JFrame_Set(2, 12, 396, 686, 151);
		
		JLabel lblNewLabel = new JLabel("PaPago API Translator Study | English to Korean | made by Silvea");
		
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setEnabled(false);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(12, 186, 502, 39);
		
		contentPane.add(lblNewLabel);
	
		JButton btnNewButton = new JButton("번역하기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Search_word1 word_trans = new Search_word1();
				ArrayList Result_list = new ArrayList();
				HashMap map;
				ArrayList list;
				Papago trans = new Papago();
				String sentence = textArea[0].getText();
				String trns_sentence = "";
				String[] sentence_split = sentence.split("\\r?\\n");						//줄바꿈 기준으로 문장 분리
				String[] word_split;
				String word_result ;
				
				sentence = sentence.toLowerCase();
				
				for(int x=0 ; x < sentence_split.length ; x++) {
					
					list = new ArrayList();
					word_split = sentence_split[x].split(" ");						//띄어쓰기 기준으로 단어 분리
					
					for(int y=0 ; y < word_split.length ; y++) {
						map = new HashMap();
						
						try {
							word_trans.search_data(word_split[y]);							//단어 검색
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							word_result = word_trans.print_word();								//단어 검색 결과 저장
						} catch (Exception e2) {
							word_result = "Word Not Found";
						}
						map.put(word_split[y], word_result);
						list.add(map);
					}
					Result_list.add(list);
					trans.Trans_data(sentence_split[x]);
					trns_sentence += (trans.Trans_result_get()) + System.lineSeparator();
				}
		
				String list_data = "";						
				ArrayList a;
				for(int i=0 ; i < Result_list.size() ; i++) {								//List에 저장된 단어 문자열로 이어붙이기
					a =  (ArrayList) Result_list.get(i);
					for(int x=0 ; x < a.size() ; x++) {
						list_data = list_data + a.get(x) + " ";
					}
					list_data+=System.lineSeparator();
				}
				list_data.replaceAll("\\{\\}","");
				textArea[1].setText(trns_sentence);
				textArea[2].setText(list_data);
            }
		});
		
		btnNewButton.setBounds(526, 186, 172, 39);
		contentPane.add(btnNewButton);
	}
	
	public void JFrame_Set(int check_num, int a,int b,int c,int d) {				//화면 구성 메소드
			
		scrollPane[check_num].setBounds(a, b, c, d);
		textArea[check_num].setBounds(a, b, c, d);
		textArea[check_num].setLineWrap(true);
		scrollPane[check_num].setViewportView(textArea[check_num]);
			
		if ( 1 <= check_num) {
			textArea[check_num].setEditable(false);
		}
		contentPane.add(scrollPane[check_num]);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
