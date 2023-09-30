package com.group4.unicon;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {


    LinearLayout l1;
    LinearLayout l2;
    LinearLayout l3;
    LinearLayout l4;
    LinearLayout l5;
    LinearLayout l6;
    LinearLayout l7;
    LinearLayout l8;
    LinearLayout l9;
    LinearLayout l10;
    ImageView appLogo;

    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    ImageView img6;
    ImageView img7;
    ImageView img8;
    ImageView img9;
    ImageView img10;

    TextView txt1;
    TextView txt2;
    TextView txt3;
    TextView txt4;
    TextView txt5;
    TextView txt6;
    TextView txt7;
    TextView txt8;
    TextView txt9;
    TextView txt10;

    Boolean isLongPressed1 = false;
    Boolean isLongPressed2 = false;
    Boolean isLongPressed3 = false;
    Boolean isLongPressed4 = false;
    Boolean isLongPressed5 = false;
    Boolean isLongPressed6 = false;
    Boolean isLongPressed7 = false;
    Boolean isLongPressed8 = false;
    Boolean isLongPressed9 = false;
    Boolean isLongPressed10 = false;

    SharedPreferences data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVars();
        setOnClicks();
        data = getSharedPreferences("data", Activity.MODE_PRIVATE);
        data.edit().putString("full","").apply();
        getHTML();

    }

    private void getHTML(){
        new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    Document doc = Jsoup.connect("https://api.exchangeratesapi.io/latest?base=USD").ignoreContentType(true).get();
                    String datas = doc.outerHtml();
                    datas = datas.substring(42,datas.length()-52)+",";
                    data.edit().putString("full",datas).apply();
                    Log.d("currrdata",data.getString("full",""));

                } catch (IOException e) {
                    Log.d("err",e.toString());
                }
            }
        }).start();
    }

    private void setVars(){
        l1 = findViewById(R.id.lay1);
        l2 = findViewById(R.id.lay2);
        l3 = findViewById(R.id.lay3);
        l4 = findViewById(R.id.lay4);
        l5 = findViewById(R.id.lay5);
        l6 = findViewById(R.id.lay6);
        l7 = findViewById(R.id.lay7);
        l8 = findViewById(R.id.lay8);
        l9 = findViewById(R.id.lay9);
        l10 = findViewById(R.id.lay10);

        appLogo = findViewById(R.id.appLogoMain);

        img1 = findViewById(R.id.image1);
        img2 = findViewById(R.id.image2);
        img3 = findViewById(R.id.image3);
        img4 = findViewById(R.id.image4);
        img5 = findViewById(R.id.image5);
        img6 = findViewById(R.id.image6);
        img7 = findViewById(R.id.image7);
        img8 = findViewById(R.id.image8);
        img9 = findViewById(R.id.image9);
        img10 = findViewById(R.id.image10);

        txt1 = findViewById(R.id.text1);
        txt2 = findViewById(R.id.text2);
        txt3 = findViewById(R.id.text3);
        txt4 = findViewById(R.id.text4);
        txt5 = findViewById(R.id.text5);
        txt6 = findViewById(R.id.text6);
        txt7 = findViewById(R.id.text7);
        txt8 = findViewById(R.id.text8);
        txt9 = findViewById(R.id.text9);
        txt10 = findViewById(R.id.text10);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setOnClicks(){
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newlay = new Intent(MainActivity.this,TempConv.class);
                startActivity(newlay);
            }
        });

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newlay = new Intent(MainActivity.this,LengthConv.class);
                startActivity(newlay);
            }
        });

        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newlay = new Intent(MainActivity.this,AreaConv.class);
                startActivity(newlay);
            }
        });

        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newlay = new Intent(MainActivity.this,VolumeConv.class);
                startActivity(newlay);
            }
        });

        l5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newlay = new Intent(MainActivity.this,SpeedConv.class);
                startActivity(newlay);
            }
        });

        l6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newlay = new Intent(MainActivity.this,EnergyConv.class);
                startActivity(newlay);
            }
        });

        l7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newlay = new Intent(MainActivity.this,ForceConv.class);
                startActivity(newlay);
            }
        });
        l8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newlay = new Intent(MainActivity.this,WeightConv.class);
                startActivity(newlay);
            }
        });
        l9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newlay = new Intent(MainActivity.this,TimeConv.class);
                startActivity(newlay);
            }
        });
        l10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.getString("full","").equals("")){
                    Toast.makeText(MainActivity.this,"Internet not available",Toast.LENGTH_SHORT).show();
                    getHTML();
                }
                else{
                    Log.d("currdata",data.getString("full from main",""));
                    Intent newlay = new Intent(MainActivity.this,CurrConv.class);
                    startActivity(newlay);
                }
            }
        });

        appLogo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ObjectAnimator animation = ObjectAnimator.ofFloat(appLogo, "rotation", 0f,360f);
                animation.setDuration(200);
                animation.start();
                return  false;
            }
        });

        l1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ObjectAnimator anim = ObjectAnimator.ofFloat(img1,"alpha",0.2f);
                anim.setDuration(1000);
                ObjectAnimator translate = ObjectAnimator.ofFloat(txt1,"translationY",-120);
                translate.setDuration(1000);
                ValueAnimator animator = ObjectAnimator.ofFloat(txt1, "textSize", 14,16);
                animator.setDuration(1000);

                animator.start();
                anim.start();
                translate.start();
                isLongPressed1 = true;
                return true;
            }
        });
        l1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.onTouchEvent(motionEvent);
                // We're only interested in when the button is released.
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    // We're only interested in anything if our speak button is currently pressed.
                    if (isLongPressed1) {
                        ObjectAnimator anim = ObjectAnimator.ofFloat(img1,"alpha",1f);
                        anim.setDuration(1000);
                        ObjectAnimator translate = ObjectAnimator.ofFloat(txt1,"translationY",0);
                        translate.setDuration(1000);
                        ValueAnimator animator = ObjectAnimator.ofFloat(txt1, "textSize", 16,14);
                        animator.setDuration(1000);

                        animator.start();
                        anim.start();
                        translate.start();
                        isLongPressed1 = false;
                    }
                }
                return false;
            }
        });
        l2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ObjectAnimator anim = ObjectAnimator.ofFloat(img2,"alpha",0.2f);
                anim.setDuration(1000);
                ObjectAnimator translate = ObjectAnimator.ofFloat(txt2,"translationY",-120);
                translate.setDuration(1000);
                ValueAnimator animator = ObjectAnimator.ofFloat(txt2, "textSize", 14,16);
                animator.setDuration(1000);

                animator.start();
                anim.start();
                translate.start();
                isLongPressed2 = true;
                return true;
            }
        });
        l2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.onTouchEvent(motionEvent);
                // We're only interested in when the button is released.
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    // We're only interested in anything if our speak button is currently pressed.
                    if (isLongPressed2) {
                        ObjectAnimator anim = ObjectAnimator.ofFloat(img2,"alpha",1f);
                        anim.setDuration(1000);
                        ObjectAnimator translate = ObjectAnimator.ofFloat(txt2,"translationY",0);
                        translate.setDuration(1000);
                        ValueAnimator animator = ObjectAnimator.ofFloat(txt2, "textSize", 16,14);
                        animator.setDuration(1000);

                        animator.start();
                        anim.start();
                        translate.start();
                        isLongPressed2 = false;
                    }
                }
                return false;
            }
        });
        l3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ObjectAnimator anim = ObjectAnimator.ofFloat(img3,"alpha",0.2f);
                anim.setDuration(1000);
                ObjectAnimator translate = ObjectAnimator.ofFloat(txt3,"translationY",-120);
                translate.setDuration(1000);
                ValueAnimator animator = ObjectAnimator.ofFloat(txt3, "textSize", 14,16);
                animator.setDuration(1000);

                animator.start();
                anim.start();
                translate.start();
                isLongPressed3 = true;
                return true;
            }
        });
        l3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.onTouchEvent(motionEvent);
                // We're only interested in when the button is released.
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    // We're only interested in anything if our speak button is currently pressed.
                    if (isLongPressed3) {
                        ObjectAnimator anim = ObjectAnimator.ofFloat(img3,"alpha",1f);
                        anim.setDuration(1000);
                        ObjectAnimator translate = ObjectAnimator.ofFloat(txt3,"translationY",0);
                        translate.setDuration(1000);
                        ValueAnimator animator = ObjectAnimator.ofFloat(txt3, "textSize", 16,14);
                        animator.setDuration(1000);

                        animator.start();
                        anim.start();
                        translate.start();
                        isLongPressed3 = false;
                    }
                }
                return false;
            }
        });
        l4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ObjectAnimator anim = ObjectAnimator.ofFloat(img4,"alpha",0.2f);
                anim.setDuration(1000);
                ObjectAnimator translate = ObjectAnimator.ofFloat(txt4,"translationY",-120);
                translate.setDuration(1000);
                ValueAnimator animator = ObjectAnimator.ofFloat(txt4, "textSize", 14,16);
                animator.setDuration(1000);

                animator.start();
                anim.start();
                translate.start();
                isLongPressed4 = true;
                return true;
            }
        });
        l4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.onTouchEvent(motionEvent);
                // We're only interested in when the button is released.
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    // We're only interested in anything if our speak button is currently pressed.
                    if (isLongPressed4) {
                        ObjectAnimator anim = ObjectAnimator.ofFloat(img4,"alpha",1f);
                        anim.setDuration(1000);
                        ObjectAnimator translate = ObjectAnimator.ofFloat(txt4,"translationY",0);
                        translate.setDuration(1000);
                        ValueAnimator animator = ObjectAnimator.ofFloat(txt4, "textSize", 16,14);
                        animator.setDuration(1000);

                        animator.start();
                        anim.start();
                        translate.start();
                        isLongPressed4 = false;
                    }
                }
                return false;
            }
        });
        l5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ObjectAnimator anim = ObjectAnimator.ofFloat(img5,"alpha",0.2f);
                anim.setDuration(1000);
                ObjectAnimator translate = ObjectAnimator.ofFloat(txt5,"translationY",-120);
                translate.setDuration(1000);
                ValueAnimator animator = ObjectAnimator.ofFloat(txt5, "textSize", 14,16);
                animator.setDuration(1000);

                animator.start();
                anim.start();
                translate.start();
                isLongPressed5 = true;
                return true;
            }
        });
        l5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.onTouchEvent(motionEvent);
                // We're only interested in when the button is released.
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    // We're only interested in anything if our speak button is currently pressed.
                    if (isLongPressed5) {
                        ObjectAnimator anim = ObjectAnimator.ofFloat(img5,"alpha",1f);
                        anim.setDuration(1000);
                        ObjectAnimator translate = ObjectAnimator.ofFloat(txt5,"translationY",0);
                        translate.setDuration(1000);
                        ValueAnimator animator = ObjectAnimator.ofFloat(txt5, "textSize", 16,14);
                        animator.setDuration(1000);

                        animator.start();
                        anim.start();
                        translate.start();
                        isLongPressed5 = false;
                    }
                }
                return false;
            }
        });
        l6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ObjectAnimator anim = ObjectAnimator.ofFloat(img6,"alpha",0.2f);
                anim.setDuration(1000);
                ObjectAnimator translate = ObjectAnimator.ofFloat(txt6,"translationY",-120);
                translate.setDuration(1000);
                ValueAnimator animator = ObjectAnimator.ofFloat(txt6, "textSize", 14,16);
                animator.setDuration(1000);

                animator.start();
                anim.start();
                translate.start();
                isLongPressed6 = true;
                return true;
            }
        });
        l6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.onTouchEvent(motionEvent);
                // We're only interested in when the button is released.
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    // We're only interested in anything if our speak button is currently pressed.
                    if (isLongPressed6) {
                        ObjectAnimator anim = ObjectAnimator.ofFloat(img6,"alpha",1f);
                        anim.setDuration(1000);
                        ObjectAnimator translate = ObjectAnimator.ofFloat(txt6,"translationY",0);
                        translate.setDuration(1000);
                        ValueAnimator animator = ObjectAnimator.ofFloat(txt6, "textSize", 16,14);
                        animator.setDuration(1000);

                        animator.start();
                        anim.start();
                        translate.start();
                        isLongPressed6 = false;
                    }
                }
                return false;
            }
        });
        l7.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ObjectAnimator anim = ObjectAnimator.ofFloat(img7,"alpha",0.2f);
                anim.setDuration(1000);
                ObjectAnimator translate = ObjectAnimator.ofFloat(txt7,"translationY",-120);
                translate.setDuration(1000);
                ValueAnimator animator = ObjectAnimator.ofFloat(txt7, "textSize", 14,16);
                animator.setDuration(1000);

                animator.start();
                anim.start();
                translate.start();
                isLongPressed7 = true;
                return true;
            }
        });
        l7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.onTouchEvent(motionEvent);
                // We're only interested in when the button is released.
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    // We're only interested in anything if our speak button is currently pressed.
                    if (isLongPressed7) {
                        ObjectAnimator anim = ObjectAnimator.ofFloat(img7,"alpha",1f);
                        anim.setDuration(1000);
                        ObjectAnimator translate = ObjectAnimator.ofFloat(txt7,"translationY",0);
                        translate.setDuration(1000);
                        ValueAnimator animator = ObjectAnimator.ofFloat(txt7, "textSize", 16,14);
                        animator.setDuration(1000);

                        animator.start();
                        anim.start();
                        translate.start();
                        isLongPressed7 = false;
                    }
                }
                return false;
            }
        });
        l8.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ObjectAnimator anim = ObjectAnimator.ofFloat(img8,"alpha",0.2f);
                anim.setDuration(1000);
                ObjectAnimator translate = ObjectAnimator.ofFloat(txt8,"translationY",-120);
                translate.setDuration(1000);
                ValueAnimator animator = ObjectAnimator.ofFloat(txt8, "textSize", 14,16);
                animator.setDuration(1000);

                animator.start();
                anim.start();
                translate.start();
                isLongPressed8 = true;
                return true;
            }
        });
        l8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.onTouchEvent(motionEvent);
                // We're only interested in when the button is released.
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    // We're only interested in anything if our speak button is currently pressed.
                    if (isLongPressed8) {
                        ObjectAnimator anim = ObjectAnimator.ofFloat(img8,"alpha",1f);
                        anim.setDuration(1000);
                        ObjectAnimator translate = ObjectAnimator.ofFloat(txt8,"translationY",0);
                        translate.setDuration(1000);
                        ValueAnimator animator = ObjectAnimator.ofFloat(txt8, "textSize", 16,14);
                        animator.setDuration(1000);

                        animator.start();
                        anim.start();
                        translate.start();
                        isLongPressed8 = false;
                    }
                }
                return false;
            }
        });
        l9.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ObjectAnimator anim = ObjectAnimator.ofFloat(img9,"alpha",0.2f);
                anim.setDuration(1000);
                ObjectAnimator translate = ObjectAnimator.ofFloat(txt9,"translationY",-120);
                translate.setDuration(1000);
                ValueAnimator animator = ObjectAnimator.ofFloat(txt9, "textSize", 14,16);
                animator.setDuration(1000);

                animator.start();
                anim.start();
                translate.start();
                isLongPressed9 = true;
                return true;
            }
        });
        l9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.onTouchEvent(motionEvent);
                // We're only interested in when the button is released.
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    // We're only interested in anything if our speak button is currently pressed.
                    if (isLongPressed9) {
                        ObjectAnimator anim = ObjectAnimator.ofFloat(img9,"alpha",1f);
                        anim.setDuration(1000);
                        ObjectAnimator translate = ObjectAnimator.ofFloat(txt9,"translationY",0);
                        translate.setDuration(1000);
                        ValueAnimator animator = ObjectAnimator.ofFloat(txt9, "textSize", 16,14);
                        animator.setDuration(1000);

                        animator.start();
                        anim.start();
                        translate.start();
                        isLongPressed9 = false;
                    }
                }
                return false;
            }
        });

        l10.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ObjectAnimator anim = ObjectAnimator.ofFloat(img10,"alpha",0.2f);
                anim.setDuration(1000);
                ObjectAnimator translate = ObjectAnimator.ofFloat(txt10,"translationY",-120);
                translate.setDuration(1000);
                ValueAnimator animator = ObjectAnimator.ofFloat(txt10, "textSize", 14,16);
                animator.setDuration(1000);

                animator.start();
                anim.start();
                translate.start();
                isLongPressed10 = true;
                return true;
            }
        });
        l10.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.onTouchEvent(motionEvent);
                // We're only interested in when the button is released.
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    // We're only interested in anything if our speak button is currently pressed.
                    if (isLongPressed10) {
                        ObjectAnimator anim = ObjectAnimator.ofFloat(img10,"alpha",1f);
                        anim.setDuration(1000);
                        ObjectAnimator translate = ObjectAnimator.ofFloat(txt10,"translationY",0);
                        translate.setDuration(1000);
                        ValueAnimator animator = ObjectAnimator.ofFloat(txt10, "textSize", 16,14);
                        animator.setDuration(1000);

                        animator.start();
                        anim.start();
                        translate.start();
                        isLongPressed10 = false;
                    }
                }
                return false;
            }
        });

    }
}

