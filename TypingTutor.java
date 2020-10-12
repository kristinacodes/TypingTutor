package grah;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.BadLocationException;

//typing class 


public class TypingTutor extends JFrame {

    final int WIDTH_ = 772, HEIGHT_ = 630;

    // define key board x and y location
    final int X_KEYBOARD = 17;
    final int Y_KEYBOARD = 342;

    // define height of any button
    final int HEIGHT_BUTTONS = 48;

    // define width of small buttons
    final int WIDTH_SMALL_BUTTONS = 48;

    // define the width of tab button
    final int WIDTH_TAB_BUTTON = 98;

    // define the width of capslock button
    final int WIDTH_CAPS_BUTTON = 98;

    // define the width of shift button
    final int WIDTH_SHIFT_BUTTON = 108;

    // define the width of back space button
    final int WIDTH_BACK_BUTTON = 98;

    // define the width of space button
    final int WIDTH_SPACE_BUTTON = 320;

    // define the width of enter button
    final int WIDTH_ENTER_BUTTON = 96;

    // storing all the keycodes of the keyboard from the first row first button to last row last button 
    int[] keyCodes = {
        KeyEvent.VK_BACK_QUOTE, KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3,
        KeyEvent.VK_4, KeyEvent.VK_5, KeyEvent.VK_6, KeyEvent.VK_7, KeyEvent.VK_8,
        KeyEvent.VK_9, KeyEvent.VK_0, KeyEvent.VK_MINUS, KeyEvent.VK_EQUALS,
        KeyEvent.VK_BACK_SPACE, KeyEvent.VK_TAB, KeyEvent.VK_Q, KeyEvent.VK_W,
        KeyEvent.VK_E, KeyEvent.VK_R, KeyEvent.VK_T, KeyEvent.VK_Y, KeyEvent.VK_U,
        KeyEvent.VK_I, KeyEvent.VK_O, KeyEvent.VK_P, KeyEvent.VK_OPEN_BRACKET,
        KeyEvent.VK_CLOSE_BRACKET, KeyEvent.VK_BACK_SLASH, KeyEvent.VK_CAPS_LOCK,
        KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_F, KeyEvent.VK_G,
        KeyEvent.VK_H, KeyEvent.VK_J, KeyEvent.VK_K, KeyEvent.VK_L, KeyEvent.VK_SEMICOLON,
        KeyEvent.VK_QUOTE, KeyEvent.VK_ENTER, KeyEvent.VK_SHIFT, KeyEvent.VK_Z,
        KeyEvent.VK_X, KeyEvent.VK_C, KeyEvent.VK_V, KeyEvent.VK_B, KeyEvent.VK_N,
        KeyEvent.VK_M, KeyEvent.VK_COMMA, KeyEvent.VK_PERIOD, KeyEvent.VK_SLASH,
        KeyEvent.VK_UP, KeyEvent.VK_SPACE, KeyEvent.VK_LEFT, KeyEvent.VK_DOWN,
        KeyEvent.VK_RIGHT
    };

    // store all the button texts corresponding to keycodes of the keyboard
    String[] keyStrings = {
        "~", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "+", "Backspace",
        "Tab", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "[", "]", "\\", "Caps",
        "A", "S", "D", "F", "G", "H", "J", "K", "L", ":", "\"", "Enter", "Shift",
        "Z", "X", "C", "V", "B", "N", "M", ",", ".", "?", "^",
        "", "<", "V", ">",};

    String[] buttonTypingCharactersWithoutShift = {
        "`", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=", "",
        "\t", "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]", "\\", "",
        "a", "s", "d", "f", "g", "h", "j", "k", "l", ";", "'", "\n", "",
        "z", "x", "c", "v", "b", "n", "m", ",", ".", "/", "",
        "", "", "", "",};
    String[] buttonTypingCharactersWithShift = {
        "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "+", "",
        "\t", "Q", "W", "E", "R", "T", "y", "U", "I", "O", "P", "{", "}", "|", "",
        "A", "S", "D", "F", "G", "H", "J", "K", "L", ":", "\"", "\n", "",
        "Z", "X", "C", "V", "B", "N", "M", "<", ">", "?", "",
        " ", "", "", "",};

    // to store all the buttons mapped to the key
    private JButton[] buttons;

    // get the default colour of keyboard buttons
    private Color defaultColor;

    // typing text area
    private JTextArea textArea;

    // checking is caps
    private boolean capsLockPressed = false, shiftPressed = false;

    
    
    
   //making the app
    public TypingTutor() {

        buttons = new JButton[keyCodes.length];

        // name
        setTitle("Typing Tutor");
        
        // frame size
        setSize(WIDTH_, HEIGHT_);
        
        // exit on close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // set layout to null(no any specific layout)
        setLayout(null);
        
        // setting the display when popping up to the middle
        setLocationRelativeTo(null);

        // add other components to the frame
        initComponents();

        // getting the default colour of keyboard buttons
        defaultColor = buttons[0].getBackground();

    }

    // add action listener for given button index
    void addActionListner(int bi) {
        JButton button = buttons[bi];
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int caretPosition = textArea.getCaretPosition();

                // if the key is caps highlight it
                if (keyCodes[bi] == KeyEvent.VK_CAPS_LOCK) {
                    capsLockPressed = !capsLockPressed;
                    
                    
                    //  make it to the same wished colour
                    button.setBackground(capsLockPressed ? Color.GREEN : defaultColor);
                } else if (keyCodes[bi] == KeyEvent.VK_BACK_SPACE) {

                    if (caretPosition != 0) {
                        textArea.replaceRange("", caretPosition - 1, caretPosition);
                    }
                } else if (keyCodes[bi] == KeyEvent.VK_LEFT) {

                    if (caretPosition > 0) {
                        textArea.setCaretPosition(caretPosition - 1);
                    }

                } else if (keyCodes[bi] == KeyEvent.VK_RIGHT) {

                    if (caretPosition < textArea.getText().length()) {
                        textArea.setCaretPosition(caretPosition + 1);
                    }

                } else if (keyCodes[bi] == KeyEvent.VK_UP) {

                    try {

                        // current line number
                        int linenum = textArea.getLineOfOffset(caretPosition);
                        // previous line index
                        int prevLinenum = linenum - 1;

                        
                        int column = caretPosition - textArea.getLineStartOffset(linenum);

                       
                        int col1 = caretPosition - textArea.getLineStartOffset(prevLinenum);
                        
                       
                        int col2 = caretPosition - textArea.getLineEndOffset(prevLinenum);

                        // place the caret to the new position
                        if(col1 - col2 == 0) {
                            textArea.setCaretPosition(caretPosition - col1 - 1);
                        } else if(col1 - col2 >= column) {
                            textArea.setCaretPosition(caretPosition - col1 + column);
                        } else if(col1 - col2 < column) {
                            textArea.setCaretPosition(caretPosition - col2 - 1);
                        }
                        

                    } 
                    catch (BadLocationException ex) {
                    }

                } else if (keyCodes[bi] == KeyEvent.VK_DOWN) {

                    try {

                        // current line number
                        int linenum = textArea.getLineOfOffset(caretPosition);
                        // previous line index
                        int nextLinenum = linenum + 1;

                        // offset from start to the current caret position
                        int column = caretPosition - textArea.getLineStartOffset(linenum);

                        // offset from start of nex line to the current caret position
                        int col1 = textArea.getLineStartOffset(nextLinenum) - caretPosition;
                        
                        // offset from end of next line to the current caret position
                        int col2 = textArea.getLineEndOffset(nextLinenum) - caretPosition - 1;
                        

                        // place the caret to the new position 
                        if(col2 - col1 == 0) {
                            textArea.setCaretPosition(caretPosition + col1);
                        } else if(col2 - col1 >= column) {
                            textArea.setCaretPosition(caretPosition + col1 + column);
                        } else if(col2 - col1 < column) {
                            textArea.setCaretPosition(caretPosition + col2);
                        }
                        

                    } catch (Exception ex) {}

                } else {
                    if (shiftPressed) {
                        if (capsLockPressed) {
                            String str = buttonTypingCharactersWithShift[bi];
                            textArea.append(str.toLowerCase());
                        } else {
                            textArea.append(buttonTypingCharactersWithShift[bi]);
                        }

                    } else {
                        if (capsLockPressed) {
                            String str = buttonTypingCharactersWithoutShift[bi];
                            textArea.append(str.toUpperCase());
                        } else {
                            textArea.append(buttonTypingCharactersWithoutShift[bi]);
                        }
                    }
                }
            }
        });
    }

    //getting the button for the given keycode
    private JButton getButtonByKeyCode(int code) {
        int index = -1;
        for (int i = 0; i < keyCodes.length; i++) {
            if (keyCodes[i] == code) {
                index = i;
                break;
            }
        }
        return index < 0 ? null : buttons[index];
    }



    
    /////////////////////making the frame--------------------------------------------------------------------
    
    
    private void initComponents() {

        // create a JLabel to display what the user should to do
        JLabel msg = new JLabel("<html>Type what you would like"
                + "<br>Note: clicking the buttons with your mouse should work :) </html>");

        // set dimensions for JLabel
        msg.setBounds(X_KEYBOARD, 11, 650, 45);

        // add the created JLabel to frame
        getContentPane().add(msg);

        
        
        //button index, row index and column index
        
        int bi = 0, ri = 0, ci;

        ////////////////////////////////// first row of the keyboard------------------------------------
        
        
        for (ci = 0; ci < 13; ci++) {
            // create a button
            JButton button = new JButton(keyStrings[bi]);
            // set the size of the button
            button.setBounds(X_KEYBOARD + WIDTH_SMALL_BUTTONS * ci,
                    Y_KEYBOARD + HEIGHT_BUTTONS * ri, WIDTH_SMALL_BUTTONS, HEIGHT_BUTTONS);

            // off focus on this button
            button.setFocusable(false);
            
            // add button to the frame
            getContentPane().add(button);
            
            // map button to the relevant keycode 
            buttons[bi] = button;
            
            // add action listener
            addActionListner(bi);
            
            // increase the button index or keys will mess up, need it for every row!!!
            bi++;
        }

        // making the backspace button
        JButton backspaceBtn = new JButton(keyStrings[bi]);
        
        // set the size of the back space button
        backspaceBtn.setBounds(X_KEYBOARD + WIDTH_SMALL_BUTTONS * ci,
                Y_KEYBOARD + HEIGHT_BUTTONS * ri, WIDTH_BACK_BUTTON, HEIGHT_BUTTONS);
        
        // off focus on this button
        backspaceBtn.setFocusable(false);
        
        // add button to the frame
        getContentPane().add(backspaceBtn);
        
        // map button to the relevant keycode 
        buttons[bi] = backspaceBtn;
        
        // add action listener
        addActionListner(bi);
        
        // increase bi
        bi++;
        
        // increase the next row
        ri++;

        
        
        ////////////////////////////// 2nd row of the keyboard --------------------------------------------
        // create the tab button
        JButton btnTab = new JButton(keyStrings[bi]);
        
        // set the size of the tab button
        btnTab.setBounds(X_KEYBOARD, Y_KEYBOARD + HEIGHT_BUTTONS * ri, WIDTH_TAB_BUTTON, HEIGHT_BUTTONS);
        
        // off focus on this button
        btnTab.setFocusable(false);
        
        // add tab button to the frame
        getContentPane().add(btnTab);
        
        // map button to the relevant keycode 
        buttons[bi] = btnTab;
        
        // add action listener
        addActionListner(bi);
        
        
        bi++;

        for (ci = 0; ci < 13; ci++) {
        	
            // make the button
            JButton button = new JButton(keyStrings[bi]);
            
            // set the size of the button
            button.setBounds(X_KEYBOARD + WIDTH_TAB_BUTTON + WIDTH_SMALL_BUTTONS * ci,
                    Y_KEYBOARD + HEIGHT_BUTTONS * ri, WIDTH_SMALL_BUTTONS, HEIGHT_BUTTONS);
          
            // off focus on this button
            button.setFocusable(false);
            
            // add button to the frame
            getContentPane().add(button);
            buttons[bi] = button;
            
            // add action listener
            addActionListner(bi);
            
            
            
            
            bi++;
        }
       
        ri++;

        /////////////////////////////////// 3rd row of the keyboard ------------------------------------------
       
        
        JButton btnCaps = new JButton(keyStrings[bi]);
        
        
        
        // set the size of the button
        btnCaps.setBounds(X_KEYBOARD, Y_KEYBOARD + HEIGHT_BUTTONS * ri, WIDTH_CAPS_BUTTON, HEIGHT_BUTTONS);
        
        // off focus on this button
        btnCaps.setFocusable(false);
        
        // add button to the frame
        getContentPane().add(btnCaps);
        buttons[bi] = btnCaps;
        
        // add action listener
        addActionListner(bi);
        bi++;

        for (ci = 0; ci < 11; ci++) {
        
        	
            JButton button = new JButton(keyStrings[bi]);
            
            // set the size of the button
            button.setBounds(X_KEYBOARD + WIDTH_CAPS_BUTTON + WIDTH_SMALL_BUTTONS * ci,
                    Y_KEYBOARD + HEIGHT_BUTTONS * ri, WIDTH_SMALL_BUTTONS, HEIGHT_BUTTONS);
            
            button.setFocusable(false);
           
            // add button to frame
            getContentPane().add(button);
            buttons[bi] = button;
            
            
            
            // add action listener
            addActionListner(bi);
            bi++;
        }

       
        
        JButton enterBtn = new JButton(keyStrings[bi]);
        
        // set the size of the button
        enterBtn.setBounds(X_KEYBOARD + WIDTH_CAPS_BUTTON + WIDTH_SMALL_BUTTONS * ci,
                Y_KEYBOARD + HEIGHT_BUTTONS * ri, WIDTH_ENTER_BUTTON, HEIGHT_BUTTONS);
       
        
        
        enterBtn.setFocusable(false);
        
        //Add button to the frame
        getContentPane().add(enterBtn);
        buttons[bi] = enterBtn;
       
        
        // add action listener
        addActionListner(bi);
        bi++;
        ri++;

        ///////////////////////////////////////4th row of the keyboard---------------------------------------
   
        
        
        JButton shiftTab = new JButton(keyStrings[bi]);
     
        
        shiftTab.setBounds(X_KEYBOARD, Y_KEYBOARD + HEIGHT_BUTTONS * ri, WIDTH_SHIFT_BUTTON, HEIGHT_BUTTONS);
       
        
        
        shiftTab.setFocusable(false);
       
        //Add button to frame
        getContentPane().add(shiftTab);
        buttons[bi] = shiftTab;
       
        // add action listener
        addActionListner(bi);
        bi++;

        for (ci = 0; ci < 10; ci++) {
            // create a button
            JButton button = new JButton(keyStrings[bi]);
            // set the size of the button
            button.setBounds(X_KEYBOARD + WIDTH_SHIFT_BUTTON + WIDTH_SMALL_BUTTONS * ci,
                    Y_KEYBOARD + HEIGHT_BUTTONS * ri, WIDTH_SMALL_BUTTONS, HEIGHT_BUTTONS);
            // off focus on this button
            button.setFocusable(false);
            // add button to the frame
            getContentPane().add(button);
            // map button to the relevant keycode 
            buttons[bi] = button;
            // add action listener
            addActionListner(bi);
            // increase the button index
            bi++;
        }

       
        JButton upArrowBtn = new JButton(keyStrings[bi]);
        
        
        // set the size of the button
        upArrowBtn.setBounds(X_KEYBOARD + WIDTH_SHIFT_BUTTON + WIDTH_SMALL_BUTTONS * ci + 20,
                Y_KEYBOARD + HEIGHT_BUTTONS * ri, WIDTH_SMALL_BUTTONS, HEIGHT_BUTTONS);
        
        
        // off focus on this button
        upArrowBtn.setFocusable(false);
        // add button to the frame
        getContentPane().add(upArrowBtn);
        
        
        // map button to the relevant keycode 
        buttons[bi] = upArrowBtn;
        
        
        // add action listener
        addActionListner(bi);
        bi++;
        ri++;

        // ================================ 5 row of the keyboard ============================================
        
        
        
        JButton spaceBtn = new JButton(keyStrings[bi]);
        
        
        // set the size of the button
        spaceBtn.setBounds(X_KEYBOARD + 180, Y_KEYBOARD + HEIGHT_BUTTONS * ri, WIDTH_SPACE_BUTTON, HEIGHT_BUTTONS);
        
        
        // off focus on this button
        spaceBtn.setFocusable(false);
        
        // add button to frame
        getContentPane().add(spaceBtn);
        
        // map button to the relevant keycode 
        buttons[bi] = spaceBtn;
       
       
       // add action listener
        addActionListner(bi);
        bi++;

        for (ci = 0; ci < 3; ci++) {
           
        	
        	
            JButton button = new JButton(keyStrings[bi]);
           
            // set the size of the button
            button.setBounds(X_KEYBOARD + WIDTH_SPACE_BUTTON + 240 + WIDTH_SMALL_BUTTONS * ci,
                    Y_KEYBOARD + HEIGHT_BUTTONS * ri, WIDTH_SMALL_BUTTONS, HEIGHT_BUTTONS);
           
            // off focus on this button
            button.setFocusable(false);
            
            // add button to the frame
            getContentPane().add(button);
            
            // map button to the relevant keycode 
            buttons[bi] = button;
            
            // add action listener
            addActionListner(bi);
            bi++;
        }

        // create textArea
        textArea = new JTextArea();

        // add key listener to the textArea
        textArea.addKeyListener(new KeyAdapter() {

            //colour change when pressed
            @Override
            public void keyPressed(KeyEvent e) {
                // get the key
                int keyCode = e.getKeyCode();

                JButton button = getButtonByKeyCode(keyCode);
                if (button == null) {
                    return;
                }

                // shift key pressed
                if (keyCode == KeyEvent.VK_SHIFT) {
                    shiftPressed = true;
                }

                // if the key is caps toggle it
                if (keyCode == KeyEvent.VK_CAPS_LOCK) {
                    capsLockPressed = !capsLockPressed;
                    
                    // change the key to green when pressed
                    
                    button.setBackground(capsLockPressed ? Color.GREEN : defaultColor);
                } else {
                	
                    //
                    button.setBackground(Color.GREEN);
                }
            }

            // changing back to original colour after pressed 
            
            @Override
            public void keyReleased(KeyEvent e) {
                // get the key code
                int keyCode = e.getKeyCode();
                JButton button = getButtonByKeyCode(keyCode);
                if (button == null) {
                    return;
                }

                // shift key released
                if (keyCode == KeyEvent.VK_SHIFT) {
                    shiftPressed = false;
                }

                // skipped the caps button
                if (keyCode != KeyEvent.VK_CAPS_LOCK) {
                    
                	// making the colour defualt
                    button.setBackground(defaultColor);
                }

            }

        });

        //word wrap 
        
        textArea.setLineWrap(true);
        
        
        // scroll in txt area
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        
        // wrap on word boundries
       
        textArea.setWrapStyleWord(true);
        
        //to only show the scroll when i have it reaached the visible limit
        
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        // set the size and the location for text area
        
        scrollPane.setBounds(X_KEYBOARD, 60, 723, 268);
        
        // add the scroll pane with text area to the frame
        getContentPane().add(scrollPane);

    }

    public static void main(String[] args) {

        // create typing tutor application
        TypingTutor typingTutor = new TypingTutor();
      
        
        // set visible the application
        typingTutor.setVisible(true);

    }

}
