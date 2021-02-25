package com.example.opera20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class PageAccueil extends AppCompatActivity {

    private AlphaAnimation buttonAnimationClick = new AlphaAnimation(1F, 0.8F);//pour animer le bouton lorsque l'on clique dessus;

    private ImageView m_image_filtre;
    TextView opera;
    Button qrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_accueil);

        //Gestion de la vidéo d'ouverture pour faire un effet ouverture rideau;
        //on utilise cette variable car on veut dans un 1er temps afficher uniquement la vidéo puis après les boutons;*/

        opera=findViewById(R.id.opera);
        opera.animate().alpha(1f).setDuration(500);


        qrcode=findViewById(R.id.qrcode);

        m_image_filtre=findViewById(R.id.filtre);
        m_image_filtre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonAnimationClick);//animation du bouton;
                Intent filtre = new Intent(PageAccueil.this, FaceFilterActivity.class);
                startActivity(filtre);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);//transition entre les fenêtres;
                PageAccueil.this.finish();
            }
        });



        opera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonAnimationClick);//animation du bouton;
                Intent HomePage = new Intent(PageAccueil.this, HomePage.class);
                startActivity(HomePage);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);//transition entre les fenêtres;
                PageAccueil.this.finish();
            }

        });

        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator=new IntentIntegrator(PageAccueil.this);
                intentIntegrator.initiateScan();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    //To destroy the activity if the user clicks on the back button;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            PageAccueil.this.finish();
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Receive the analyse result.
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "scan annulé", Toast.LENGTH_LONG).show();
            } else {
                if(result.getContents().equals("1"))
                {
                    Intent intent1= new Intent(PageAccueil.this,Lorchestre.class);
                    startActivity(intent1);
                    PageAccueil.this.finish();
                }

                if(result.getContents().equals("2"))
                {
                    Intent intent1= new Intent(PageAccueil.this,Latechnique.class);
                    startActivity(intent1);
                    PageAccueil.this.finish();
                }

                if(result.getContents().equals("3"))
                {
                    Intent intent1= new Intent(PageAccueil.this,Lascene.class);
                    startActivity(intent1);
                    PageAccueil.this.finish();
                }
                if(result.getContents().equals("4"))
                {
                    Intent intent1= new Intent(PageAccueil.this,Lescoulisses.class);
                    startActivity(intent1);
                    PageAccueil.this.finish();
                }
                if(result.getContents().equals("5"))
                {
                    Intent intent1= new Intent(PageAccueil.this,ActuOpera.class);
                    startActivity(intent1);
                    PageAccueil.this.finish();
                }
                if(result.getContents().equals("6"))
                {
                    Intent intent1= new Intent(PageAccueil.this,Lassociation.class);
                    startActivity(intent1);
                    PageAccueil.this.finish();
                }
                if(result.getContents().equals("7"))
                {
                    Intent intent1= new Intent(PageAccueil.this,NosPartenaires.class);
                    startActivity(intent1);
                    PageAccueil.this.finish();
                }
                if(result.getContents().equals("8"))
                {
                    Intent intent1= new Intent(PageAccueil.this,Billetterie.class);
                    startActivity(intent1);
                    PageAccueil.this.finish();
                }
                if(result.getContents().equals("9"))
                {
                    Intent intent1= new Intent(PageAccueil.this, QuizActivity.class);
                    startActivity(intent1);
                    PageAccueil.this.finish();
                }
                if(result.getContents().equals("10"))
                {
                    Intent intent1= new Intent(PageAccueil.this,Lesprecedentesproductions.class);
                    startActivity(intent1);
                    PageAccueil.this.finish();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



}
