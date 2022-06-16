package com.example.deutschea1alla;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SoundActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Button> buttonList = new ArrayList<Button>();
    public LinearLayout show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        Intent intent = getIntent();
        String cursInfo = intent.getStringExtra("cursInfo");
        //System.out.println("intent full Dinamic Main Activity: "+cursInfo);
        //String[] cursInfoData = cursInfo.split("/");

        show = (LinearLayout) findViewById(R.id.show);

        String TableName = "words1000";

        Db1000Adapter helper1000 = new Db1000Adapter(this);

        String[][] twoDimArray = helper1000.getFullDataArray();
        //String[] oneW = twoDimArray[0];
        //System.out.println(oneW[3].toString());

        ArrayList<LinearLayout> Layouts = new ArrayList<LinearLayout>();
        int countWordInOneLesson = 25;
        int cursLentsh = twoDimArray.length;

        //cursLentsh = 2; //test

        int allElements = 0;
        int fakeNumber = 1;
        for (int i=0; i<cursLentsh; i++){

            String[] oneWordsSound = twoDimArray[i];
            //String[] objArray = (String[]) oneWordsSound.get(i);
            //String[] ary = oneWordsSound.split(" ");
            String DE = oneWordsSound[1];
            String SoundName = oneWordsSound[4];

            //String DE = "";
            //String SoundName = "oneWordsSound[3]";

            //System.out.println(oneWordsSound);

            LinearLayout oneLauout = new LinearLayout(this);

            oneLauout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

            Layouts.add(oneLauout);

            Button FirstButtonRiw = new Button(this);
            FirstButtonRiw.setBackgroundResource(R.drawable.word_button);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f
            );
            params.setMargins(40, 40, 40, 40);
            FirstButtonRiw.setLayoutParams(params);

            FirstButtonRiw.setText(DE);
            //FirstButtonRiw.setTag(fakeNumber+"/"+1+"/"+TableName);
            //FirstButtonRiw.setOnClickListener(this);


            Layouts.get(i).addView(FirstButtonRiw);

            //if (allElements < twoDimArray.length-1) {
            //    allElements++;
                Button SecondButtonRiw = new Button(this);
                SecondButtonRiw.setBackgroundResource(R.drawable.reverse_button);
                //String [] secondKurs = Kurses.get([allElements]);
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1.0f
                );
                params2.setMargins(40, 40, 40, 40);
                SecondButtonRiw.setLayoutParams(params2);

                SecondButtonRiw.setText(SoundName);
                SecondButtonRiw.setTag(SoundName);
                SecondButtonRiw.setOnClickListener(this);

                Layouts.get(i).addView(SecondButtonRiw);

           // }

            show.addView(Layouts.get(i));
            if (allElements < twoDimArray.length) {
                allElements++;
                fakeNumber++;
            }
        }
        //*/
    }

    @Override
    public void onClick(View view) {
        String CursNumber = view.getTag().toString();
        //TextView soundValue = findViewById(R.id.soundValue);
        MediaPlayer mp = new MediaPlayer();
        String filename = "android.resource://" + this.getPackageName() + "/raw/"+CursNumber;
        try { mp.setDataSource(this, Uri.parse(filename)); } catch (Exception e) {}
        try { mp.prepare(); } catch (Exception e) {}
        mp.start();
        //System.out.println(CursNumber);
    }
}