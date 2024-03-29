package pavUI;

import org.eclipse.swt.layout.FillLayout;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.cloudgarden.resource.SWTResourceManager;

import global.Consts;
import pavBallot.*;

public class GUI2 extends org.eclipse.swt.widgets.Composite {
	private static Button Rchose;
	private static Button Bvote;
	private static Button Bverify;
	private static Label LTcand;
	private static Label Ltenc;
    private static Label Lplease;
    private static int NumCand=global.Consts.PARTIES_AMOUNT;
    private static Vote vote;
	private static String TheChosen;
	private static Ballot ballot;
	private static Shell shell = null;
	private static int iChosen;
	private static int i=0;
	private static String strI;
	private static String cndName;
    private static String str ;
    
   
	public static void doSelection(Button button) {
        if (button.getSelection()){
                strI = button.getData() + "";                       
               } 
        }
	
	public  static void showGUI2() {
		if (InitialGUI.getswStart() == 1){
		    ballot = new Ballot();
        }
		InitialGUI.setsw(0);
		Display display = Display.getDefault();
		shell = new Shell(display, SWT.DIALOG_TRIM);		
		shell.setText("Pret-A-Voter");
		final ScrolledComposite sc1 = new ScrolledComposite(shell, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		final Composite c1 = new Composite(sc1, SWT.NONE);
		c1.setBackground(SWTResourceManager.getColor(223, 255, 255));
		sc1.setBackground(SWTResourceManager.getColor(223, 255, 255));
		sc1.setContent(c1);
	    Point size = c1.getSize();
		shell.setLayout(new FillLayout());
    	shell.layout();
		
		Listener listener = new Listener() {
		        public void handleEvent (Event e) {
		        	doSelection((Button)e.widget);
		        }
		};		
		
	    Listener listener1 = new Listener() {
	      public void handleEvent(Event event) {
	   //Case [Vote] was pressed 
	        if (event.widget == Bvote) {
	        	shell.close();
	        	InitialGUI.setsw(1);;
	        	InitialGUI.setezer(3);
	        } else 
	   //Case [verify Ballot] was pressed
	       	if (event.widget == Bverify) {
	               	shell.close();
	            	InitialGUI.setsw(1);
	            	InitialGUI.setezer(5);	            	
	       	}
	        InitialGUI.setswStart(0); //keep the same ballot
		    iChosen = Integer.parseInt(strI); //keeping the i of the chosen 
		    vote = ballot.getVote(iChosen);    //the chosen vote
		    TheChosen = vote.getCandidateName();//the chosen candidate name
 		    str = vote.getEncryptionBase64();//the encryption of the chosen
		    
	      }
	    };
	                LTcand = new Label(c1, SWT.NONE);
	                LTcand.setBounds(6,120,130,17);
		            LTcand.setText("Candidate Name");
		            LTcand.setBackground(SWTResourceManager.getColor(223, 255, 255));
		            LTcand.setFont(SWTResourceManager.getFont("Arial", 10, 3, false, false));
				
					Bverify = new Button(c1, SWT.PUSH | SWT.CENTER | SWT.FLAT | SWT.BORDER);
					Bverify.setBounds(140, 240+NumCand*220, 100, 50);
					Bverify.setText("Verify Ballot");
					Bverify.addListener(SWT.Selection, listener1);
					Bverify.setFont(SWTResourceManager.getFont("Arial", 8, 3, false, false));
								
         			Ltenc = new Label(c1, SWT.NONE);
					Ltenc.setBounds(210, 120, 100, 17);
					Ltenc.setText("Encryption");
					Ltenc.setBackground(SWTResourceManager.getColor(223, 255, 255));
					Ltenc.setFont(SWTResourceManager.getFont("Arial", 10, 3, false, false));
						
					Bvote = new Button(c1, SWT.PUSH | SWT.CENTER |SWT.FLAT | SWT.BORDER);
					Bvote.setBounds(300, 240+NumCand*220, 90, 50);
					Bvote.setText("Vote");
					Bvote.setBackground(SWTResourceManager.getColor(223, 255, 255));
					Bvote.addListener(SWT.Selection, listener1);
					Bvote.setFont(SWTResourceManager.getFont("Arial", 8, 3, false, false));
											
					Lplease = new Label(c1, SWT.BOLD);
					Lplease.setBounds(19,30,340,40);
					Lplease.setText("Please Make Your Selection:");
					Lplease.setBackground(SWTResourceManager.getColor(223, 255, 255));
					Lplease.setFont(SWTResourceManager.getFont("Tahoma", 13, 1, false, false));
         //Printing the candidates					
	           for (i=0; i<NumCand; i++){
	        	   
	        	    vote = ballot.getVote(i);
				
					Rchose = new Button(c1, SWT.RADIO | SWT.LEFT );
					Rchose.setText("Candidate "+i);
					Rchose.setData(i);
					Rchose.setBackground(SWTResourceManager.getColor(223, 255, 255));
					Rchose.setFont(SWTResourceManager.getFont("Arial", 8, 3, false, false));
					cndName = vote.getCandidateName();
					Rchose.setText(cndName);
					if((InitialGUI.getswStart() == 0) && (i == iChosen))
						Rchose.setSelection(true);
					Rchose.setBounds(6, 200+i*220, 98, 20);
					Rchose.addListener(SWT.Selection, listener);
				
                    
					    List list = new List(c1, SWT.NONE);
						list.setBounds(103, 200+i*220, 290, 190);
						
					    str=vote.getEncryptionBase64();
						int mana = (str.length())/35;
						for (int j=0;j<mana;j++  ){
							 list.add(str.substring(j*35,(j+1)*35));    
							 }
							 list.add(str.substring(mana*35));
			           }
	           
	           
	           if(size.x == 0 && size.y == 0) {
	   			c1.pack();
	   			shell.pack();
	   		} else {
	   			sc1.setSize(430,650);
	   		}				   
        shell.setLocation(100, 30);

  		shell.setSize(430,650);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
	
	public static Vote getVote(){
		return vote;
	}
	
	public static String getTheChosen(){
		return TheChosen;
	}
	
	public static int getiCosen(){
		return iChosen;
	}
	public static String getstr(){
		return str;
	}

	public GUI2(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		}
}
	

        
