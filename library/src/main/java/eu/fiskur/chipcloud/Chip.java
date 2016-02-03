package eu.fiskur.chipcloud;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class Chip extends TextView implements View.OnClickListener{

    private Context context;
    private int index = -1;
    private boolean selected = false;
    private ChipListener listener = null;
    private int sizeSelectedColor = -1;


    public void setChipListener(ChipListener listener){
        this.listener = listener;
    }

    public Chip(Context context) {
        super(context);
        init();
    }

    public Chip(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Chip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void initChip(Context context, int index, String label, int sizeSelectedColor){
        this.context = context;
        this.index = index;
        this.sizeSelectedColor = sizeSelectedColor;
        setText(label);
    }

    private void init(){
        setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(selected){
            //set as unselected
            setBackgroundResource(R.drawable.chip);
            setTextColor(ContextCompat.getColor(context, R.color.chip));
        }else{
            //set as selected
            Drawable selected = ContextCompat.getDrawable(context, R.drawable.chip_selected);
            if(sizeSelectedColor == -1){
                sizeSelectedColor = ContextCompat.getColor(context, R.color.dark_grey);
            }
            selected.setColorFilter(new PorterDuffColorFilter(sizeSelectedColor, PorterDuff.Mode.MULTIPLY));

            setBackgroundCompat(selected);

            setTextColor(ContextCompat.getColor(context, R.color.white));
            if(listener != null){
                listener.chipSelected(index);
            }
        }

        selected = !selected;
    }

    @SuppressWarnings("deprecation")
    private void setBackgroundCompat(Drawable background){
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(background);
        } else {
            setBackground(background);
        }
    }

    public void deselect(){
        setBackgroundResource(R.drawable.chip);
        setTextColor(ContextCompat.getColor(context, R.color.chip));
        selected = false;
    }

    public interface ChipListener{
        void chipSelected(int index);
    }
}

