package com.example.deutschea1alla;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DinamicActivity extends AppCompatActivity implements View.OnClickListener{

    private String [][] allCurrentLessonFull;

    private ArrayList<DinamicActivity.CurrentNode> allCurrentLesson;

    private ArrayList<DinamicActivity.FakeNode> fakeLessonArray;

    private ArrayList<DinamicActivity.ErrorNode> errorLessonArray;

    private String userMastSelected;

    private String userMastSelectedSecond;

    private String userSelected;

    private String errorString = "";

    private int vibral = 0;

    private int countsWordShow = 0;

    private int progress = 0;

    private int pravilnie = 0;

    private int nePravilnie = 0;

    private int pravilnijOtvetButton;

    private int[] typeWords = {2,1};

    private String Location = "ua";

    private String currentType;

    public String[][] getAllCurrentLessonFull() {

        return allCurrentLessonFull;
    }

    public void setAllCurrentLessonFull(String [][] nodes) {
        this.allCurrentLessonFull = nodes;
    }

    public ArrayList<DinamicActivity.CurrentNode> getAllCurrentLesson() {
        return allCurrentLesson;
    }
    public void setAllCurrentLesson(ArrayList<DinamicActivity.CurrentNode> nodes) {
        this.allCurrentLesson = nodes;
    }

    public ArrayList<DinamicActivity.FakeNode> getFakeLessonArray() {
        return fakeLessonArray;
    }
    public void setFakeLessonArray(ArrayList<DinamicActivity.FakeNode> nodes) {
        this.fakeLessonArray = nodes;
    }

    public ArrayList<DinamicActivity.ErrorNode> getErrorLessonArray() {
        return errorLessonArray;
    }
    public void setErrorLessonArray(ArrayList<DinamicActivity.ErrorNode> nodes) {
        this.errorLessonArray = nodes;
    }

    public String getUserMastSelected() {
        return userMastSelected;
    }

    public void setUserMastSelected(String userSelected) {
        this.userMastSelected = userSelected;
    }

    public String getUserMastSelectedSecond() {
        return userMastSelectedSecond;
    }

    public void setUserMastSelectedSecond(String userSelected) {
        this.userMastSelectedSecond = userSelected;
    }

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    public String getUserSelected() {
        return userSelected;
    }

    public void setUserSelected(String userSelected) {
        this.userSelected = userSelected;
    }

    public int getVibral() {
        return vibral;
    }

    public void setVibral(int vibral) {
        this.vibral = vibral;
    }

    public int getPravilnijOtvetButton() {
        return pravilnijOtvetButton;
    }

    public void setPravilnijOtvetButton(int numberButt) {
        this.pravilnijOtvetButton = numberButt;
    }

    public void setTypeWords(int [] typeWords) {
        this.typeWords = typeWords;
    }

    public int [] getTypeWords() {
        return this.typeWords;
    }

    public void setCurrentType(String currentType) {
        this.currentType = currentType;
    }

    public String getCurrentType() {
        return this.currentType;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    public void playSound(View view) {
        TextView soundValue = findViewById(R.id.soundValue);
        mp = new MediaPlayer();
        String filename = "android.resource://" + this.getPackageName() + "/raw/"+soundValue.getText();
        try { mp.setDataSource(this, Uri.parse(filename)); } catch (Exception e) {}
        try { mp.prepare(); } catch (Exception e) {}
        mp.start();
    }

    private class Node {

        private String [] nodeData;

        public Node(String [] nodeData) {
            this.nodeData = nodeData;
        }

        public String getNodeData(int nodeIndex) {
            return nodeData[nodeIndex];
        }

        @Override
        public String toString() {
            String st = "";
            for (int i = 0; i < nodeData.length; i++) {
                st += nodeData[i]+" ";
            }
            return st;
        }

    }

    private class CurrentNode {

        private String [] nodeData;

        public CurrentNode(String [] nodeData) {
            this.nodeData = nodeData;
        }

        public String getNodeData(int nodeIndex) {
            return nodeData[nodeIndex];
        }

        @Override
        public String toString() {
            String st = "";
            for (int i = 0; i < nodeData.length; i++) {
                st += nodeData[i]+" ";
            }
            return st;
        }

    }

    private class FakeNode {

        private String [] nodeData;

        public FakeNode(String [] nodeData) {
            this.nodeData = nodeData;
        }

        public String getNodeData(int nodeIndex) {
            return nodeData[nodeIndex];
        }

        @Override
        public String toString() {
            String st = "";
            for (int i = 0; i < nodeData.length; i++) {
                st += nodeData[i]+" ";
            }
            return st;
        }

    }

    private class ErrorNode {

        private String [] nodeData;

        public ErrorNode(String [] nodeData) {
            this.nodeData = nodeData;
        }

        public String getNodeData(int nodeIndex) {
            return nodeData[nodeIndex];
        }

        @Override
        public String toString() {
            String st = "";
            for (int i = 0; i < nodeData.length; i++) {
                st += nodeData[i]+" ";
            }
            return st;
        }

    }

    DbLocation locationDB;
    //Db200Adapter helper200;
    Db1000Adapter helper1000;
    DbFinishLesson finishDB;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinamic);

        locationDB = new DbLocation(this);
        //helper200 = new Db200Adapter(this);
        helper1000 = new Db1000Adapter(this);

        Intent intent = getIntent();
        String lessonNumber = intent.getStringExtra("lesson");
        String[] lessonIntent = lessonNumber.split("/");

        TextView lessonNumberTitle = findViewById(R.id.lessonNumber);
        String lesTitleCustom = "Урок "+lessonIntent[0];

        setCurrentType(lessonIntent[1]);

        String Location = locationDB.getLocation();

        //System.out.println(Location);

        if(Location.trim().equals("ua")) {
            setTypeWords(new int[]{5, 1});
        }

        if(lessonIntent[1].trim().equals("2")){
            setTypeWords(new int[]{1, 2});
            lesTitleCustom += " (reverse)";

            ImageButton mainSound = findViewById(R.id.mainSound);
            mainSound.setVisibility(View.INVISIBLE);

            if(Location.trim().equals("ua")) {
                setTypeWords(new int[]{1, 5});
            }
        }

        if(lessonIntent[1].trim().equals("3")){
            //setTypeWords(new int[]{5, 1});
            TextView currentWord = findViewById(R.id.currentWord);
            currentWord.setVisibility(View.INVISIBLE);
            lesTitleCustom += " (FINAL)";
        }

        lessonNumberTitle.setText(lesTitleCustom);

        TextView realLessonNumber = findViewById(R.id.realLessonNumber);
        realLessonNumber.setText(lessonIntent[0]);

        //System.out.println("real lesson Number: "+lessonIntent[0]);


        String TableName = lessonIntent[2];

        TextView tableNameTextView = findViewById(R.id.tableName);
        tableNameTextView.setText(TableName);

        String[][] allCurrentLesson =  helper1000.getDataArrayLesson(lessonIntent[0]);
        /*
        switch(TableName) {
            case "words1000":
                allCurrentLesson =  helper1000.getDataArrayLesson(lessonIntent[0]);
                break;
            default:
        }
         */

        ProgressBar simpleProgressBar=(ProgressBar)findViewById(R.id.progressBar); // initiate the progress bar
        simpleProgressBar.setMax(allCurrentLesson.length); // 100 maximum value for the progress value
        //simpleProgressBar.setMax(100);

        ArrayList<DinamicActivity.CurrentNode> Currentnodes = new ArrayList<>();

        for (int i = 0; i < allCurrentLesson.length; i++) {
            String [] begermod = allCurrentLesson[i];
            Currentnodes.add(new DinamicActivity.CurrentNode(begermod));
        }

        setAllCurrentLessonFull(allCurrentLesson);

        setAllCurrentLesson(Currentnodes);

        showOneScene(this);

        //*/
    }

    private int rnd(int min, int max){
        int x = min + (int)(Math.random() * ((max -min) + 1));
        return  x;
    }

    @Override
    public void onClick(View view) {

        ArrayList<DinamicActivity.CurrentNode> currentLessonWords = getAllCurrentLesson();

        //ArrayList<Words200Activity.ErrorNode> errorNode = new ArrayList<Words200Activity.ErrorNode>();

        String userSelected = getUserSelected();
        int vibral = getVibral();

        int nextButton = 0;

        switch (view.getId()) {
            case R.id.answerButton1:
                setVibral(vibral+1);
                Button answerButton1 = findViewById(R.id.answerButton1);
                userSelected = (String) answerButton1.getText();
                if(vibral == 1) {
                    setUserSelected(userSelected);
                }
                break;
            case R.id.answerButton2:
                setVibral(vibral+1);
                Button answerButton2 = findViewById(R.id.answerButton2);
                userSelected = (String) answerButton2.getText();
                if(vibral == 1) {
                    setUserSelected(userSelected);
                }
                break;

            case R.id.answerButton3:
                setVibral(vibral+1);
                Button answerButton3 = findViewById(R.id.answerButton3);
                userSelected = (String) answerButton3.getText();
                if(vibral == 1) {
                    setUserSelected(userSelected);
                }
                break;

            case R.id.answerButton4:
                setVibral(vibral+1);
                Button answerButton4 = findViewById(R.id.answerButton4);
                userSelected = (String) answerButton4.getText();
                if(vibral == 1) {
                    setUserSelected(userSelected);
                }
                break;

            case R.id.nextButton:
                nextButton = 1;
                break;

        }

        vibral = getVibral();

        //if(vibral == )

        //System.out.println("next button: "+nextButton);
        //System.out.println("user selected: "+userSelected);
        //System.out.println("vibral raz: "+vibral);

        if(nextButton == 0) {
            String message = "";
            String userMastSelected = getUserMastSelected();
            String userMastSelectedSecond = getUserMastSelectedSecond();
            Button CorrectButton = findViewById(view.getId());
            if(userSelected == userMastSelected) {
                CorrectButton.setTextColor(Color.GREEN);
                message = ":)";
                this.pravilnie++;
            }else{
                String errorString = getErrorString();
                String testError = userMastSelectedSecond+" - "+userMastSelected+"\n";
                String NewErrorString = errorString+testError;
                setErrorString(NewErrorString);
                CorrectButton.setTextColor(Color.RED);
                int pravilnijOtvetButton = getPravilnijOtvetButton();
                switch (pravilnijOtvetButton) {
                    case 1:
                        Button answerButton1 = findViewById(R.id.answerButton1);
                        answerButton1.setTextColor(Color.GREEN);
                        break;
                    case 2:
                        Button answerButton2 = findViewById(R.id.answerButton2);
                        answerButton2.setTextColor(Color.GREEN);
                        break;
                    case 3:
                        Button answerButton3 = findViewById(R.id.answerButton3);
                        answerButton3.setTextColor(Color.GREEN);
                        break;
                    case 4:
                        Button answerButton4 = findViewById(R.id.answerButton4);
                        answerButton4.setTextColor(Color.GREEN);
                        break;
                }
                message = ":(";
                this.nePravilnie++;
            }
            showMessage(this, message);
            //Message.message(this,message);

            Button nextButtonAfter = findViewById(R.id.nextButton);
            nextButtonAfter.setEnabled(true);
            nextButtonAfter.setClickable(true);

            Button answerButton1final = findViewById(R.id.answerButton1);
            answerButton1final.setClickable(false);

            Button answerButton2final = findViewById(R.id.answerButton2);
            answerButton2final.setClickable(false);

            Button answerButton3final = findViewById(R.id.answerButton3);
            answerButton3final.setClickable(false);

            Button answerButton4final = findViewById(R.id.answerButton4);
            answerButton4final.setClickable(false);

        }else{
            setUserSelected(null);
            setVibral(0);
            showOneScene(this);
        }

    }

    private void showOneScene(DinamicActivity lessonActivity) {

        ArrayList<DinamicActivity.CurrentNode> currentLessonWords = getAllCurrentLesson();
        String [][] allCurrentLessonFull = getAllCurrentLessonFull();
        ArrayList<DinamicActivity.FakeNode> Fakenodes = new ArrayList<DinamicActivity.FakeNode>();

        int [] rev = getTypeWords();

        for (int i = 0; i < allCurrentLessonFull.length; i++) {
            Fakenodes.add(new DinamicActivity.FakeNode(allCurrentLessonFull[i]));
        }

        if(currentLessonWords.size() == 0){
            String printerrorString = getErrorString();
            //System.out.println(printerrorString);
            showModal(printerrorString);
            if(printerrorString.length() == 0) {
                finishDB = new DbFinishLesson(this);

                TextView tableNameTextView = findViewById(R.id.tableName);
                String TableNameFromTextView = tableNameTextView.getText().toString();

                TextView realLessonNumber = findViewById(R.id.realLessonNumber);
                String realLessonNumberView = realLessonNumber.getText().toString();
                //System.out.println("real lesson number: "+realLessonNumberView);
                int intRealNum = Integer.parseInt(realLessonNumberView);
                //int normalCurrentLessonNumber = intRealNum * 2 - 1;
                //realLessonNumber.setText(normalCurrentLessonNumber.toString());
                //if(rev[0] == 1) {
                //    normalCurrentLessonNumber++;
                //}

                String finishCurs =  finishDB.getFinishCurs(TableNameFromTextView);


                String[] finishLessonData = finishCurs.split("/");
                System.out.println(finishLessonData[0]);
                String finishKursString = finishLessonData[0];
                String finishTypeString = finishLessonData[1];


                int numberfinishCurs = Integer.parseInt(finishKursString);

                String currentType = getCurrentType();

                Message.message(this,"normalCurrentLessonNumber: "+intRealNum+" numberfinishCurs: "+numberfinishCurs);
                Message.message(this,"CurrentLessonType: "+currentType+" finishType: "+finishTypeString);

                if(numberfinishCurs == intRealNum && Integer.parseInt(currentType) == Integer.parseInt(finishTypeString)) {
                    int lessonType = Integer.parseInt(currentType);
                    lessonType++;
                    if(lessonType == 4) {
                        numberfinishCurs = numberfinishCurs+1;
                        lessonType=1;
                    }
                    Message.message(this,"Super "+intRealNum);
                    finishDB.ubdateFinishLesson(TableNameFromTextView, numberfinishCurs, lessonType);
                }

                Intent refresh = new Intent(this, DinamicMainActivity.class);
                startActivity(refresh);
                this.finish();

            }
        }else{
            //System.out.println(currentLessonWords.size());

            int randomNumberFromWordArray = (int)Math.floor(Math.random() * currentLessonWords.size());
            TextView currentWord = findViewById(R.id.currentWord);

            //System.out.println(currentLessonWords);

            DinamicActivity.CurrentNode mastSelected = currentLessonWords.get(randomNumberFromWordArray);

            // gdeto tut
            //System.out.println(mastSelected.toString());
            String SoundWord = mastSelected.getNodeData(4);
            TextView soundValue = findViewById(R.id.soundValue);
            soundValue.setText(SoundWord);

            setUserMastSelected(mastSelected.getNodeData(rev[0]));
            setUserMastSelectedSecond(mastSelected.getNodeData(rev[1]));
            currentWord.setText(mastSelected.getNodeData(rev[1]));

            currentLessonWords.remove(randomNumberFromWordArray);

            //System.out.println(mastSelected.toString());
            //System.out.println(Fakenodes.toString());
            for(int ii=0;ii<Fakenodes.size();ii++){
                if(Fakenodes.get(ii).getNodeData(rev[0]) == mastSelected.getNodeData(rev[0])){
                    Fakenodes.remove(ii);
                }
            }
            //System.out.println(Fakenodes.toString());

            int randomFake = (int)Math.floor(Math.random() * Fakenodes.size());
            DinamicActivity.FakeNode fakeNode1 = Fakenodes.get(randomFake);
            String fakeElement1 = fakeNode1.getNodeData(rev[0]);
            Fakenodes.remove(randomFake);

            int randomFake2 = (int)Math.floor(Math.random() * Fakenodes.size());
            DinamicActivity.FakeNode fakeNode2 = Fakenodes.get(randomFake2);
            String fakeElement2 = fakeNode2.getNodeData(rev[0]);
            Fakenodes.remove(randomFake2);

            int randomFake3 = (int)Math.floor(Math.random() * Fakenodes.size());
            DinamicActivity.FakeNode fakeNode3 = Fakenodes.get(randomFake3);
            String fakeElement3 = fakeNode3.getNodeData(rev[0]);
            Fakenodes.remove(randomFake3);
            //System.out.println(Fakenodes.toString());

            int randomWord = rnd(1,4);

            setPravilnijOtvetButton(randomWord);
            String userMastSElect = getUserMastSelected();

            switch (randomWord) {
                case 1:
                    Button answerButton1 = findViewById(R.id.answerButton1);
                    answerButton1.setText(userMastSElect);

                    Button answerButton2 = findViewById(R.id.answerButton2);
                    answerButton2.setText(fakeElement1);

                    Button answerButton3 = findViewById(R.id.answerButton3);
                    answerButton3.setText(fakeElement2);

                    Button answerButton4 = findViewById(R.id.answerButton4);
                    answerButton4.setText(fakeElement3);

                    break;
                case 2:
                    Button answerButton22 = findViewById(R.id.answerButton2);
                    answerButton22.setText(userMastSElect);

                    Button answerButton12 = findViewById(R.id.answerButton1);
                    answerButton12.setText(fakeElement1);

                    Button answerButton42 = findViewById(R.id.answerButton4);
                    answerButton42.setText(fakeElement2);

                    Button answerButton32 = findViewById(R.id.answerButton3);
                    answerButton32.setText(fakeElement3);

                    break;
                case 3:
                    Button answerButton33 = findViewById(R.id.answerButton3);
                    answerButton33.setText(userMastSElect);

                    Button answerButton43 = findViewById(R.id.answerButton4);
                    answerButton43.setText(fakeElement1);

                    Button answerButton13 = findViewById(R.id.answerButton1);
                    answerButton13.setText(fakeElement2);

                    Button answerButton23 = findViewById(R.id.answerButton2);
                    answerButton23.setText(fakeElement3);

                    break;
                case 4:
                    Button answerButton44 = findViewById(R.id.answerButton4);
                    answerButton44.setText(userMastSElect);

                    Button answerButton34 = findViewById(R.id.answerButton3);
                    answerButton34.setText(fakeElement1);

                    Button answerButton24 = findViewById(R.id.answerButton2);
                    answerButton24.setText(fakeElement2);

                    Button answerButton14 = findViewById(R.id.answerButton1);
                    answerButton14.setText(fakeElement3);

                    break;
            }

            Button answerButton1 = findViewById(R.id.answerButton1);
            answerButton1.setTextColor(Color.BLACK);
            answerButton1.setOnClickListener(lessonActivity);

            Button answerButton2 = findViewById(R.id.answerButton2);
            answerButton2.setTextColor(Color.BLACK);
            answerButton2.setOnClickListener(lessonActivity);

            Button answerButton3 = findViewById(R.id.answerButton3);
            answerButton3.setTextColor(Color.BLACK);
            answerButton3.setOnClickListener(lessonActivity);

            Button answerButton4 = findViewById(R.id.answerButton4);
            answerButton4.setTextColor(Color.BLACK);
            answerButton4.setOnClickListener(lessonActivity);

            Button nextButton = findViewById(R.id.nextButton);
            nextButton.setEnabled(false);
            nextButton.setClickable(false);
            nextButton.setOnClickListener(lessonActivity);

            this.countsWordShow++;

            ProgressBar simpleProgressBar=(ProgressBar)findViewById(R.id.progressBar);

            simpleProgressBar.setProgress(countsWordShow);

            updateStatistic(this);
        }
    }

    private void showMessage(DinamicActivity lessonActivity, String message) {
        Toast toast = Toast.makeText(lessonActivity, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void updateStatistic(DinamicActivity lessonActivity) {

        String [][] allWordsInLessonArray = getAllCurrentLessonFull();

        int allWordsInLesson = allWordsInLessonArray.length;

        //TextView allWords = findViewById(R.id.countWordsTitleTop);
        //allWords.setText(""+allWordsInLesson+"");
        TextView currentWordTitle = findViewById(R.id.statistikcountWordsTitleTop);
        currentWordTitle.setText(""+this.pravilnie+"");

        TextView leftWord = findViewById(R.id.statistik2countWordsTitleTop);
        leftWord.setText(""+this.nePravilnie+"");

    }

    public class Cat {

        private String [] name;

        public Cat(String [] name) {
            this.name = name;
        }

        @Override
        public String toString() {
            String st = "";
            for (int i = 0; i < name.length; i++) {
                st += name[i]+" ";
            }
            return st;
        }
    }

    public void showModal(String modalMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(DinamicActivity.this);
        builder.setTitle("Nepravilnie slova!!!");
        builder.setMessage(modalMessage);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //System.out.println("nagal ok");
            }
        });
        builder.create().show();
    }

}