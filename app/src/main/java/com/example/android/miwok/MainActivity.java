package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView numbers = (TextView) findViewById(R.id.numbers);
        final TextView colors = (TextView) findViewById(R.id.colors);
        final TextView family = (TextView) findViewById(R.id.family);
        final TextView phrases = (TextView) findViewById(R.id.phrases);

        //NumbersClickListeners numbersClickListeners = new NumbersClickListeners();

        numbers.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Toast.makeText(view.getContext(), "Open the list of numbers", Toast.LENGTH_SHORT).show();
                openNumbersList(numbers);
            }
        });

        colors.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Toast.makeText(view.getContext(), "Open the list of numbers", Toast.LENGTH_SHORT).show();
                openColorsList(colors);
            }
        });

        family.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Toast.makeText(view.getContext(), "Open the list of numbers", Toast.LENGTH_SHORT).show();
              openFamilyList(family);
            }
        });

        phrases.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                    openPhrasesList(phrases);
            }
        });

    }

    public void openNumbersList(View view)
    {
        Intent i = new Intent(this,NumbersActivity.class);
        startActivity(i);
    }
    public void openPhrasesList(View view)
    {
        Intent i = new Intent(this,PhrasesActivity.class);
        startActivity(i);
    }
    public void openColorsList(View view)
    {
        Intent i = new Intent(this,ColorsActivity.class);
        startActivity(i);
    }
        public void openFamilyList(View view)
    {
        Intent i = new Intent(this,FamilyActivity.class);
        startActivity(i);
    }
}