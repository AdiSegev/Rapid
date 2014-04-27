package com.example.rapid;
import android.app.ProgressDialog;
import android.content.Context;
    

    /**
     * Responsible on creating different progressDialogs.
     * The different types are:
     * 1 = waiting for verifing driver id
     * @author isr
     *
     */
    public class ProgressDialogActivity extends ProgressDialog {
            
      
		/**
		 * Constructs progressDialog according to different type in the constructor
		 * 
		 * @param context - the context of calling activity
		 * @param type - dialog's type need to be created
		 */
		public ProgressDialogActivity(Context context, int type) {
			super(context);
			
			switch (type){
			
			case 1:
				setCancelable(true);
				setTitle("Tnuva");
				setMessage("Verifing Id...");
				break;
			}
		}

		
    }