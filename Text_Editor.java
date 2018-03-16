import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;
import java.awt.datatransfer.*;
class Notepad extends JFrame implements ActionListener {
	JTextArea textArea=new JTextArea();
	private MenuBar menuBar = new MenuBar(); // first, create a //MenuBar item
	private Menu file = new Menu(); // our File menu
	private Menu Edit = new Menu();
	private Menu View = new Menu();
	private Menu Help = new Menu();
	// what's going in File? let's see...
	private MenuItem ne=new MenuItem();
	private MenuItem openFile = new MenuItem();  // an open option
	private MenuItem saveFile = new MenuItem(); // a save option
	private MenuItem close = new MenuItem(); // and a close option!
	private MenuItem cut = new MenuItem();
	private MenuItem copy = new MenuItem();
	private MenuItem paste = new MenuItem();
	private MenuItem fullscreen = new MenuItem();
        private MenuItem zoomin = new MenuItem();
        private MenuItem zoomout = new MenuItem();
	private MenuItem about = new MenuItem();
	public Notepad() {
		this.setSize(500, 300); // set the initial size of the window
		this.setTitle("Java Notepad Tutorial"); // set the title of the //window
		setDefaultCloseOperation(EXIT_ON_CLOSE); // set the //default close operation (exit when it gets closed)
		this.textArea.setFont(new Font("Times New Roman", Font.BOLD, 20)); // set a default font for the TextArea
		// this is why we didn't have to worry about the size of the //TextArea!
		this.setLayout(new BorderLayout()); // the BorderLayout //bit makes it fill it automatically
		this.add(textArea);

		// add our menu bar into the GUI
		this.setMenuBar(this.menuBar);
		this.menuBar.add(this.file); // we'll configure this later
		this.menuBar.add(this.Edit);
		this.menuBar.add(this.View);
		this.menuBar.add(this.Help);
		// first off, the design of the menuBar itself. Pretty simple, //all we need to do
		// is add a couple of menus, which will be populated later //on
		this.file.setLabel("File");
		this.Edit.setLabel("Edit");
		this.View.setLabel("View");
		this.Help.setLabel("Help");

 this.ne.setLabel("New"); // set the label of the menu item
		this.ne.addActionListener(this); // add an action listener (so //we know when it's been clicked
		this.ne.setShortcut(new MenuShortcut(KeyEvent.VK_F, false)); // set a keyboard shortcut
		this.file.add(this.ne);
		// now it's time to work with the menu. I'm only going to //add a basic File menu
		// but you could add more!

		// now we can start working on the content of the menu~ this gets a little repetitive,
		// so please bare with me!

		// time for the repetitive stuff. let's add the "Open" option
		this.openFile.setLabel("Open"); // set the label of the menu //item
		this.openFile.addActionListener(this); // add an action //listener (so we know when it's been clicked
		this.openFile.setShortcut(new MenuShortcut(KeyEvent.VK_O, false)); // set a keyboard shortcut
		this.file.add(this.openFile); // add it to the "File" menu

		// and the save...
		this.saveFile.setLabel("Save");
		this.saveFile.addActionListener(this);
		this.saveFile.setShortcut(new MenuShortcut(KeyEvent.VK_S, false));
		this.file.add(this.saveFile);

		// and finally, the close option
		this.close.setLabel("Close");
		// along with our "CTRL+F4" shortcut to close the window, //we also have
		// the default closer, as stated at the beginning of this //tutorial.
		// this means that we actually have TWO shortcuts to close:
		// 1) the default close operation (example, Alt+F4 on //Windows)
		// 2) CTRL+F4, which we are about to define now: (this one //will appear in the label)
		this.close.setShortcut(new MenuShortcut(KeyEvent.VK_F4, false));
		this.close.addActionListener(this);
		this.file.add(this.close);


		this.cut.setLabel("Cut "); // set the label of the menu item
                this.cut.addActionListener(this); // add an action listener (so //we know when it's been clicked
                this.cut.setShortcut(new MenuShortcut(KeyEvent.VK_X, false)); // set a keyboard shortcut
                this.Edit.add(this.cut); // add it to the "File" menu

		this.copy.setLabel("Copy "); // set the label of the menu item
                this.copy.addActionListener(this); // add an action listener (so //we know when it's been clicked
                this.copy.setShortcut(new MenuShortcut(KeyEvent.VK_C, false)); // set a keyboard shortcut
                this.Edit.add(this.copy); // add it to the "File" menu


		this.paste.setLabel("Paste "); // set the label of the menu item
                this.paste.addActionListener(this); // add an action listener (so //we know when it's been clicked
                this.paste.setShortcut(new MenuShortcut(KeyEvent.VK_V, false)); // set a keyboard shortcut
                this.Edit.add(this.paste); // add it to the "File" menu


		this.fullscreen.setLabel("FullScreen"); // set the label of the //menu item
                this.fullscreen.addActionListener(this); // add an action //listener (so we know when it's been clicked
                this.fullscreen.setShortcut(new MenuShortcut(KeyEvent.VK_F11, false)); // set a keyboard shortcut
                this.View.add(this.fullscreen);

		this.zoomin.setLabel("ZoomIn"); // set the label of the menu //item
                this.zoomin.addActionListener(this); // add an action listener //(so we know when it's been clicked
//                this.zoomin.setShortcut(new MenuShortcut(KeyEvent.VK_INC, false)); // set a keyboard shortcut
                this.View.add(this.zoomin);


		this.zoomout.setLabel("ZoomOut"); // set the label of the //menu item
                this.zoomout.addActionListener(this); // add an action listener //(so we know when it's been clicked
   //             this.zoomout.setShortcut(new MenuShortcut(KeyEvent.VK_DEC, false)); // set a keyboard shortcut
                this.View.add(this.zoomout);


		this.about.setLabel("About"); // set the label of the menu //item
                this.about.addActionListener(this); // add an action listener //(so we know when it's been clicked
                //this.about.setShortcut(new MenuShortcut(KeyEvent.VK_F11, false)); // set a keyboard shortcut
                this.Help.add(this.about);



	}

	public void actionPerformed (ActionEvent e) {
		// if the source of the event was our "close" option
		if (e.getSource() == this.close)
			this.dispose(); // dispose all resources and close the //application

		//cut command
		else if(e.getSource()== this.cut){

            String selection=textArea.getSelectedText();

            if(selection==null){
                return;
            }
            StringSelection clipString=new StringSelection(selection);
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(clipString,clipString);
            textArea.replaceSelection("");
		}
		//copy command
		if(e.getSource() == this.copy){

            String selection =textArea.getSelectedText();

            if(selection==null){
                return;
            }
            StringSelection clipString=new StringSelection(selection);
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(clipString, clipString);
        }

		//paste command
		if(e.getSource() == this.paste){
         Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable clip_data=clipboard.getContents(this);

            try{
                String clip_string=(String)clip_data.getTransferData(DataFlavor.stringFlavor);
                textArea.replaceSelection(clip_string);

            }catch(Exception excpt){

            }
        }


		// if the source was the "open" option
		else if (e.getSource() == this.openFile) {
			JFileChooser open = new JFileChooser(); // open up a //file chooser (a dialog for the user to browse files to open)
			int option = open.showOpenDialog(this); // get the //option that the user selected (approve or cancel)
			// NOTE: because we are OPENing a file, we call //showOpenDialog~
			// if the user clicked OK, we have //"APPROVE_OPTION"
			// so we want to open the file
			if (option == JFileChooser.APPROVE_OPTION) {
				this.textArea.setText(""); // clear the TextArea //before applying the file contents
				try {
					// create a scanner to read the file //(getSelectedFile().getPath() will get the path to the file)
					Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
					while (scan.hasNext()) // while there's still //something to read
						this.textArea.append(scan.nextLine() + "\n"); // append the line to the TextArea
				} catch (Exception ex) { // catch any exceptions, //and...
					// ...write to the debug console
					System.out.println(ex.getMessage());
				}
			}
		}

		// and lastly, if the source of the event was the "save" option
		else if (e.getSource() == this.saveFile) {
			JFileChooser save = new JFileChooser(); // again, open //a file chooser
			int option = save.showSaveDialog(this); // similar to //the open file, only this time we call
			// showSaveDialog instead of showOpenDialog
			// if the user clicked OK (and not cancel)
			if (option == JFileChooser.APPROVE_OPTION) {
				try {
					// create a buffered writer to write to a file
					BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
					out.write(this.textArea.getText());
                    // write the contents of the TextArea to the file
					out.close(); // close the file stream
				} catch (Exception ex) { // again, catch any //exception
					System.out.println(ex.getMessage());
}
}
}
}
			public static void main(String[] args)
			{
			Notepad app=new Notepad();
			app.setVisible(true);
			}
			}
