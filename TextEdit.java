
package texteditor_assignment_3;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;


import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;



public class TextEdit implements ActionListener {
    
    JTextArea jta;
    JTextField jtf;
    JEditorPane jep;
    JButton openButton, findButton, replaceButton, helpButton, wordCountButton, charCountButton, sanSerifButton,
            dialogButton;
    JLabel test;
    String contents = "";
    JTextField setFontSize;
    JToolBar jtbr;
    JScrollPane jscrl;
    JMenuBar jmenu;
    JMenu fontMenu, fontSize;
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
       
       content.setBackground(Color.lightGray);
       
       jmenu = new JMenuBar();
       fontMenu = new JMenu("Font");
      
       
       JButton fontSize = new JButton("Font size");
       
       /* CHANGE FONT SIZE */
       fontSize.addActionListener(new ActionListener(){ 
           
           @Override
           public void actionPerformed(ActionEvent ae) {
               String s = JOptionPane.showInputDialog(fontMenu, "Enter a text size");
               int size = 0;
           try{
           Integer i = Integer.parseInt(s);
            size = i;
           }catch(NumberFormatException e){
               JOptionPane.showMessageDialog(fontSize, "Please enter an integer value");
               return;
           }
           jep.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,size));
           }
       });
       
       openButton = new JButton("Open");
       findButton = new JButton("Find");
       wordCountButton = new JButton("word count");
       charCountButton = new JButton("character count");
       replaceButton = new JButton("Replace");
       helpButton = new JButton("Help");
       sanSerifButton = new JButton("sanserif");
       dialogButton = new JButton("bold");
       
       
       openButton.addActionListener(this);
       findButton.addActionListener(this);
       helpButton.addActionListener(this);
       wordCountButton.addActionListener(this);
       charCountButton.addActionListener(this);
       replaceButton.addActionListener(this);
       
      // fontMenu.add(sanSerifButton);
       //fontMenu.add(dialogButton);
       
       sanSerifButton.addActionListener((ae) ->{
       jep.setFont(new Font(Font.SANS_SERIF,3,20));
   });
       dialogButton.addActionListener((ae) ->{
       jep.setFont(new Font(Font.SERIF,1,10));
   });
       
       jmenu.add(openButton);
       jmenu.add(findButton);
       jmenu.add(replaceButton);      
       jmenu.add(wordCountButton);
       jmenu.add(charCountButton);
      // jmenu.add(fontMenu);
       jmenu.add(fontSize);
       jmenu.add(helpButton);
       
       jep = new JEditorPane();
       
       jep.setPreferredSize(new Dimension(700, 800));
       jep.setText(" ");
       
       
       jscrl = new JScrollPane(content);
       
       jscrl.setPreferredSize(new Dimension(1000, 800));
       
       jfrm.setJMenuBar(jmenu);
       jfrm.add(jscrl);
      
       
       jfrm.setVisible(true);
       
       content.add(jep);
       
        /* SET TEXT CURSOR */
       jep.addMouseMotionListener(new MouseMotionListener(){
           @Override
           public void mouseDragged(MouseEvent e) {
           }

           @Override
           public void mouseMoved(MouseEvent e) {
               jep.setCursor(new Cursor(Cursor.TEXT_CURSOR));
               
           }
           
       
       });
      
       
       /* SET MENU CURSOR */
       jmenu.addMouseMotionListener(new MouseMotionListener(){
           @Override
           public void mouseDragged(MouseEvent e) {
           }

           @Override
           public void mouseMoved(MouseEvent e) {
               jmenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
           }
       
       });
       
       
       
       
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
          choice = JOptionPane.showConfirmDialog(findButton, "found " + f + "(" +idx+1 + ")" + " Find next? ");
            idx ++;     
          }
          
          if(choice != 0) return;
         }
       
        JOptionPane.showMessageDialog(findButton, f + " not found");
        }
      
      /*
      
      REPLACE
      
      */
      
       if(e.getActionCommand().equals("Replace")){
           int choice = 0;
           int idx=0;
           String update = "";
           
          String temp = JOptionPane.showInputDialog(replaceButton, "Replace:");
          String repl = JOptionPane.showInputDialog(replaceButton, "With:");
          int i = 0;
          String jeptext = "";
          do{
          
          jeptext = jep.getText();
          System.out.println(jeptext);
          i = jeptext.indexOf(temp);
          if(i==-1){ JOptionPane.showMessageDialog(replaceButton, "Not Found");
          break;
          }
          
          
            
          Document docContent = jep.getDocument();
           
            try {
                docContent.remove(i, temp.length());
                System.out.println("the length of the string = " + temp.length());
                
                } catch (BadLocationException ex) {
                Logger.getLogger(TextEdit.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                docContent.insertString(i, repl, null);
            } catch (BadLocationException ex) {
                Logger.getLogger(TextEdit.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           choice = JOptionPane.showConfirmDialog(replaceButton, " Replace next? ");
           if(choice==1) break;
            i = 0;
            
          }while(true);
           
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
      
      /*
      
      HELP 
      
      */
      
      if(e.getActionCommand().equals("Help")){
          JOptionPane.showMessageDialog(helpButton, "Open: select a .txt or a .java file to open in editor \n"
                  + "Find: Enter a word that will be found \n"
                  + "Replace: Enter first word as the word you would like to be replaced, and the second word as the "
                  + "word you would like to replace the first word with \n"
                  + "Word count: Retrieves the number of words in document"
                  + "Character count: Retreives the number of characters in the document"
                  , "HELP", 1
                  );
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
