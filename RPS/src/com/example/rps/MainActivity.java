package com.example.rps;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
 
public class MainActivity extends Activity implements OnClickListener {
  
 private enum Option {
  ROCK, PAPER, SCISSORS
 }
  
 private enum Result {
  WIN, LOSS, DRAW  
 }  
 private Option userSelection;
 private Result gameResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	         
	        Button buttonRock = (Button) findViewById(R.id.buttonRock);
	        Button buttonpaper = (Button) findViewById(R.id.buttonPaper);
	        Button buttonScissors = (Button) findViewById(R.id.buttonScissors);
	         
	        // Set click listening event for all buttons.
	        buttonRock.setOnClickListener(this);
	        buttonpaper.setOnClickListener(this);
	        buttonScissors.setOnClickListener(this);
	 }
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    	getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	}
	 
	 @Override
	 public void onClick(View v) {
	   
		  TextView textView = (TextView) findViewById(R.id.UserAnswer);
		  
		  switch (v.getId()) {
		   case R.id.buttonRock:
		    userSelection = Option.ROCK;
		    textView.setText("ROCK");
		    break; 
		   case R.id.buttonPaper:
		    userSelection = Option.PAPER;
		    textView.setText("PAPER");
		    break;
		   case R.id.buttonScissors:
		    userSelection = Option.SCISSORS;
		    textView.setText("SCISSORS");
		    break;
		  }
		  play();
	 }
	 
	 private void showResults() {
	  AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);     
	     builder.setCancelable(false);     
	     builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {   
	   @Override
	   public void onClick(DialogInterface dialog, int which) {}
	  });
	      
	     // Sets the right message according to result.
	     if(gameResult == Result.WIN)
	    	 builder.setMessage("You Win!");
	     else if(gameResult == Result.LOSS)
	    	 builder.setMessage("You Lost!");
	     else
	    	 builder.setMessage("It's a draw!");
	     AlertDialog alert = builder.create();
	     alert.show();  
	 } 
	 
	 private void play() {
		  int rand =  (int)((Math.random()) * 20) % 3;
		  Option androidSelection = null;
		  TextView textView = (TextView) findViewById(R.id.ComputerAnswer);
		   
		  switch (rand) {
		   case 0:
		    androidSelection = Option.ROCK;
		    textView.setText("ROCK");
		    break;
		   case 1:
		    androidSelection = Option.PAPER;
		    textView.setText("PAPER");
		    break;
		   case 2:
		    androidSelection = Option.SCISSORS;
		    textView.setText("SCISSORS");
		    break;
		  }
		 
		  if(androidSelection == userSelection)
			  gameResult = Result.DRAW;
		  else if(androidSelection == Option.ROCK && userSelection == Option.SCISSORS)
			  gameResult = Result.LOSS;
		  else if(androidSelection == Option.PAPER && userSelection == Option.ROCK)
			  gameResult = Result.LOSS;
		  else if(androidSelection == Option.SCISSORS && userSelection == Option.PAPER)
			  gameResult = Result.LOSS;
		  else
			  gameResult = Result.WIN;
		  
		  showResults();
	 }    
}