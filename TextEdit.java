
package texteditor_assignment_3;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author averykushner
 */

public class TextEdit implements ActionListener {
    
    JTextArea jta;
    JTextField jtf;
    JEditorPane jep;
    JButton openButton, findButton, helpButton, wordCountButton, charCountButton;
    JLabel test;
    String contents = "";
    JTextPane jpn;
    JToolBar jtbr;
    JScrollPane jscrl;
    JMenuBar jmenu;
    //JMenu file
    //JMenuItem open, help, find, wordCount, charCount;
    
    String content[];
   

   TextEdit(){
       
       
       JFrame jfrm = new JFrame("Avery's Text Editor");
       
       jfrm.setSize(1080, 720);
       
       jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       //GridLayout glayout = new GridLayout(0,1,0,0);
       //BorderLayout blayout = new BorderLayout();
       //jfrm.setLayout(glayout);//FlowLayout comes from the java.awt pckg
       
       jfrm.getContentPane().setBackground(Color.white);
       
       JPanel content = new JPanel();
       
       content.setBackground(Color.white);
       
       jmenu = new JMenuBar();
      
       test = new JLabel("Default");
       
       openButton = new JButton("Open");
       findButton = new JButton("Find");
       helpButton = new JButton("Help");
       wordCountButton = new JButton("word count");
       charCountButton = new JButton("character count");
       
       openButton.addActionListener(this);
       findButton.addActionListener(this);
       helpButton.addActionListener(this);
       wordCountButton.addActionListener(this);
       charCountButton.addActionListener(this);
       
       jmenu.add(openButton);
       jmenu.add(findButton);
       jmenu.add(helpButton);
       jmenu.add(wordCountButton);
       jmenu.add(charCountButton);
       
       jep = new JEditorPane();
       
       jep.setPreferredSize(new Dimension(1000, 800));
       jep.setText(" ");
       
       
       jscrl = new JScrollPane(content);
       
       jscrl.setPreferredSize(new Dimension(1000, 800));
       
       jfrm.setJMenuBar(jmenu);
       jfrm.add(jscrl);
      
       
       jfrm.setVisible(true);
       
       content.add(jep);
       
       
   }
    
      @Override
    public void actionPerformed(ActionEvent e) {
     
        /*
        
        OPEN ACTION
        
        */
        if(e.getActionCommand().equals("Open")){
      File selectedFile;
      
      JFileChooser fileChooser = new JFileChooser();
          fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
          fileChooser.setAcceptAllFileFilterUsed(false);
          FileNameExtensionFilter filterTxt = new FileNameExtensionFilter(".txt", "txt");
          FileNameExtensionFilter filterJava = new FileNameExtensionFilter(".java", "java");

                  
          fileChooser.addChoosableFileFilter(filterTxt);
          fileChooser.addChoosableFileFilter(filterJava);

          
      int returnValue = fileChooser.showOpenDialog(null);
      
      if(returnValue == JFileChooser.APPROVE_OPTION){
           selectedFile = fileChooser.getSelectedFile();

      }else selectedFile = null;
      try{
           Scanner sc = new Scanner(selectedFile); 
       
        do{
          String str = sc.next();
          contents +=" " + str;
          
          }while(sc.hasNext());
          
          jep.setText(contents);
      }catch(IOException exc){
         JOptionPane.showMessageDialog(openButton, "FILE NOT FOUND");
      }
        }
        
        /*
        
        FIND ACTION
        
        */
        
      if(e.getActionCommand().equals("Find")){
          
      int choice = 0;
      int idx=0;
      
      String f = JOptionPane.showInputDialog(findButton, "Find:");
        
        String t = jep.getText();
        content = t.split("\\s");
        
        for(String s : content){
        
        if(s.equals(f)){
          choice = JOptionPane.showConfirmDialog(findButton, "found " + f + "(" +idx + ")" + " Find next? ");
            idx ++;     
          }
          
          if(choice != 0) return;
         }
       
        JOptionPane.showMessageDialog(findButton, f + " not found");
        }
      
      /*
        
        WORD COUNT ACTION
        
        */
      
      if(e.getActionCommand().equals("word count")){
          String t = jep.getText();
          content = t.split("\\s");
          if(content.length == 0 ) JOptionPane.showMessageDialog(wordCountButton, 0);
          else JOptionPane.showMessageDialog(wordCountButton, content.length-1);

          
      }
      
      /*
        
        CHAR COUNT ACTION
        
        */
      
      if(e.getActionCommand().equals("character count")){
       String t = jep.getText();
       if(t.length()==0) JOptionPane.showMessageDialog(charCountButton, 0);
       else JOptionPane.showMessageDialog(charCountButton, t.length()-1);

      }
      

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
        
            public void run(){
                new TextEdit();
            }
        });
    }

}
