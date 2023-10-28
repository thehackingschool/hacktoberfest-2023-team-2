package com.hacktivators.mentalhealth.Journal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hacktivators.mentalhealth.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class JournalAddActivity extends AppCompatActivity {


    AppCompatButton close,save;

    TextView generatedQuestion;

    EditText journal_box;

    boolean QuestionBol = false;
    boolean color = false;

    FirebaseUser firebaseUser;

    String JournalID;

    String Question = "Random Question";

    LinearLayout colors;



    CardView red,blue,green,purple,yellow;

    String ColorSelected = "null";
    ImageView redCheck,blueCheck,greenCheck,purpleCheck,yellowCheck;

    ImageView refreshQuestion,cancelQuestion;

    private Random randomGenerator;
    ArrayList<String> Questions = new ArrayList<>();

    ImageButton newQuestion;
    RelativeLayout generateQuesLayout;

    @Override
    protected void onStart() {
        super.onStart();

        randomGenerator = new Random();

        Questions.add("What's been on your mind lately?");
        Questions.add("What's been bothering you?");
        Questions.add("What are you grateful for today?");
        Questions.add("What are you grateful for in your life?");
        Questions.add("Describe a recent experience that had an impact on you.");
        Questions.add("What are your short-term goals?");
        Questions.add("What are your long-term goals?");
        Questions.add("Reflect on something you learned recently");
        Questions.add("What's something you'd like to change in your life?");
        Questions.add("Share your favorite quote and its significance");


    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_add);




        close = findViewById(R.id.close);

        save = findViewById(R.id.save);

        newQuestion = findViewById(R.id.add_icon);
        refreshQuestion = findViewById(R.id.refresh_icon);
        cancelQuestion = findViewById(R.id.remove_icon);

        generatedQuestion = findViewById(R.id.generatedQuestion);
        generateQuesLayout = findViewById(R.id.genQuesLayout);


        journal_box = findViewById(R.id.journal_box);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        colors = findViewById(R.id.color);


        red = findViewById(R.id.red);
        blue = findViewById(R.id.blue);
        green = findViewById(R.id.green);
        purple = findViewById(R.id.purple);
        yellow = findViewById(R.id.yellow);

        redCheck = findViewById(R.id.red_check);
        blueCheck = findViewById(R.id.blue_check);
        greenCheck = findViewById(R.id.green_check);
        purpleCheck = findViewById(R.id.purple_check);
        yellowCheck = findViewById(R.id.yellow_check);





        generateQuesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cancelQuestion.getVisibility() == View.INVISIBLE){
                    newQuestion.setVisibility(View.INVISIBLE);
                    cancelQuestion.setVisibility(View.VISIBLE);
                    refreshQuestion.setVisibility(View.VISIBLE);

                    Toast.makeText(getApplicationContext(),"New clicked",Toast.LENGTH_SHORT).show();
                    int index = randomGenerator.nextInt(Questions.size());
                    String question = Questions.get(index);


                    generatedQuestion.setText(question);

                    QuestionBol = true;

                }



            }


        });

        refreshQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                refreshQuestionFunc();


            }
        });

        cancelQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Cancel clicked",Toast.LENGTH_SHORT).show();
                newQuestion.setVisibility(View.VISIBLE);
                cancelQuestion.setVisibility(View.INVISIBLE);
                refreshQuestion.setVisibility(View.INVISIBLE);

                generatedQuestion.setText("Cant think of any? Add these questions!!");

                QuestionBol = false;
            }

        });


        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ColorSelected.equals("red")) {
                    redCheck.setVisibility(View.GONE);
                    color = false;
                    ColorSelected = "null";
                }else {
                    redCheck.setVisibility(View.VISIBLE);
                    blueCheck.setVisibility(View.GONE);
                    greenCheck.setVisibility(View.GONE);
                    purpleCheck.setVisibility(View.GONE);
                    yellowCheck.setVisibility(View.GONE);



                    color = true;
                    ColorSelected = "red";
                }






            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ColorSelected.equals("blue")) {
                    blueCheck.setVisibility(View.GONE);
                    color = false;
                    ColorSelected = "null";
                }else {
                    redCheck.setVisibility(View.GONE);
                    blueCheck.setVisibility(View.VISIBLE);
                    greenCheck.setVisibility(View.GONE);
                    purpleCheck.setVisibility(View.GONE);
                    yellowCheck.setVisibility(View.GONE);



                    color = true;
                    ColorSelected = "blue";

                }






            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(ColorSelected.equals("green")) {
                    greenCheck.setVisibility(View.GONE);
                    color = false;
                    ColorSelected = "null";
                }else {
                    redCheck.setVisibility(View.GONE);
                    blueCheck.setVisibility(View.GONE);
                    greenCheck.setVisibility(View.VISIBLE);
                    purpleCheck.setVisibility(View.GONE);
                    yellowCheck.setVisibility(View.GONE);



                    color = true;
                    ColorSelected = "green";
                }





            }
        });

        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ColorSelected.equals("purple")) {
                    purpleCheck.setVisibility(View.GONE);
                    color = false;
                    ColorSelected = "null";
                }else {
                    redCheck.setVisibility(View.GONE);
                    blueCheck.setVisibility(View.GONE);
                    greenCheck.setVisibility(View.GONE);
                    purpleCheck.setVisibility(View.VISIBLE);
                    yellowCheck.setVisibility(View.GONE);



                    color = true;
                    ColorSelected = "purple";
                }






            }
        });

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ColorSelected.equals("yellow")) {
                    yellowCheck.setVisibility(View.GONE);
                    color = false;
                    ColorSelected = "null";
                }else {
                    redCheck.setVisibility(View.GONE);
                    blueCheck.setVisibility(View.GONE);
                    greenCheck.setVisibility(View.GONE);
                    purpleCheck.setVisibility(View.GONE);
                    yellowCheck.setVisibility(View.VISIBLE);

                    color = true;
                    ColorSelected = "yellow";

                }

            }
        });

        newQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionBol = true;
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String JournalTXT = journal_box.getText().toString();

                saveJournal(JournalTXT);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });






    }

    private void refreshQuestionFunc() {

        Toast.makeText(getApplicationContext(), "Refresh clicked", Toast.LENGTH_SHORT).show();
        int index = randomGenerator.nextInt(Questions.size());
        String question = Questions.get(index);
        if (generatedQuestion.getText().toString().equals(Question)){
            refreshQuestionFunc();
        }else {
            generatedQuestion.setText(question);
        }

    }

    private void saveJournal(String Journal) {

        Date date = new Date();

        long date_ = date.getTime();




        JournalID = GenJournalID();

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        HashMap hashMap = new HashMap();
        hashMap.put("id",JournalID);
        hashMap.put("journal",Journal);
        hashMap.put("date",date_);
        if(QuestionBol){
            hashMap.put("question",true);
            hashMap.put("questionTxt",Question);
        }
        if(color){
            hashMap.put("color",true);
            hashMap.put("colorTxt",ColorSelected);
        }


        firebaseFirestore.collection("users").document(firebaseUser.getUid()).collection("journals").document(JournalID).set(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Journal saved!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(JournalAddActivity.this, JournalViewActivity.class));
                }else {
                    Toast.makeText(getApplicationContext(),"Something went wrong!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private String GenJournalID(){
        Random random = new Random();
        String letters = "abcdefghijklmnopqrstuvwxyz1234567890";
        String result = "";
        for (int i = 0; i < 8; i++) {
            result += letters.charAt(random.nextInt(letters.length()));
        }

        return result;

    }
}