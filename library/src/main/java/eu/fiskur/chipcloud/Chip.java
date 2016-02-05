package eu.fiskur.chipcloud;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class Chip extends TextView implements View.OnClickListener{

    private Context context;
    private int index = -1;
    private boolean selected = false;
    private ChipListener listener = null;
    private int selectedColor = -1;
    private int selectedFontColor = -1;
    private int unselectedColor = -1;
    private int unselectedFontColor = -1;
    private Drawable selectedDrawable;
    private Drawable unselectedDrawable;
    private TransitionDrawable crossfader;
    private int selectTransitionMS = 750;
    private int deselectTransitionMS = 500;

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

    public void initChip(Context context,
                         int index,
                         String label,
                         int selectedColor,
                         int selectedFontColor,
                         int unselectedColor,
                         int unselectedFontColor){

        this.context = context;
        this.index = index;
        this.selectedColor = selectedColor;
        this.selectedFontColor = selectedFontColor;
        this.unselectedColor = unselectedColor;
        this.unselectedFontColor = unselectedFontColor;

        selectedDrawable = ContextCompat.getDrawable(context, R.drawable.chip_selected);
        if(selectedColor == -1){
            selectedColor = ContextCompat.getColor(context, R.color.dark_grey);
        }
        selectedDrawable.setColorFilter(new PorterDuffColorFilter(selectedColor, PorterDuff.Mode.MULTIPLY));

        if(selectedFontColor == -1){
            selectedFontColor = ContextCompat.getColor(context, R.color.white);
        }

        unselectedDrawable = ContextCompat.getDrawable(context, R.drawable.chip_selected);
        if(unselectedColor == -1){
            unselectedColor = ContextCompat.getColor(context, R.color.light_grey);
        }
        unselectedDrawable.setColorFilter(new PorterDuffColorFilter(unselectedColor, PorterDuff.Mode.MULTIPLY));

        if(unselectedFontColor == -1){
            unselectedFontColor = ContextCompat.getColor(context, R.color.chip);
        }

        Drawable backgrounds[] = new Drawable[2];
        backgrounds[0] = unselectedDrawable;
        backgrounds[1] = selectedDrawable;

        crossfader = new TransitionDrawable(backgrounds);
        setBackgroundCompat(crossfader);

        setText(label);
        unselect();
    }

    public void setSelectTransitionMS(int selectTransitionMS){
        this.selectTransitionMS = selectTransitionMS;
    }

    public void setDeselectTransitionMS(int deselectTransitionMS){
        this.deselectTransitionMS = deselectTransitionMS;
    }

    private void init(){
        setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(selected){
            //set as unselected
            unselect();
        }else{
            //set as selected
            crossfader.startTransition(selectTransitionMS);

            setTextColor(selectedFontColor);
            if(listener != null){
                listener.chipSelected(index);
            }
        }

        selected = !selected;
    }

    public void select(){
        crossfader.startTransition(selectTransitionMS);

        setTextColor(selectedFontColor);
        if(listener != null){
            listener.chipSelected(index);
        }
    }

    private void unselect(){
        if(selected){
            crossfader.reverseTransition(deselectTransitionMS);
        }else{
            crossfader.resetTransition();
        }

        setTextColor(unselectedFontColor);
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
        unselect();
        selected = false;
    }

    public interface ChipListener{
        void chipSelected(int index);
    }


    public static class ChipBuilder{
        int index;
        String label;
        int selectedColor;
        int selectedFontColor;
        int unselectedColor;
        int unselectedFontColor;
        int chipHeight;
        int selectTransitionMS = 750;
        int deselectTransitionMS = 500;

        ChipListener chipListener;

        public ChipBuilder index(int index){
            this.index = index;
            return this;
        }

        public ChipBuilder selectedColor(int selectedColor){
            this.selectedColor = selectedColor;
            return this;
        }

        public ChipBuilder selectedFontColor(int selectedFontColor){
            this.selectedFontColor = selectedFontColor;
            return this;
        }

        public ChipBuilder unselectedColor(int unselectedColor){
            this.unselectedColor = unselectedColor;
            return this;
        }

        public ChipBuilder unselectedFontColor(int unselectedFontColor){
            this.unselectedFontColor = unselectedFontColor;
            return this;
        }

        public ChipBuilder label(String label){
            this.label = label;
            return this;
        }

        public ChipBuilder chipHeight(int chipHeight){
            this.chipHeight = chipHeight;
            return this;
        }

        public ChipBuilder chipListener(ChipListener chipListener){
            this.chipListener = chipListener;
            return this;
        }

        public ChipBuilder selectTransitionMS(int selectTransitionMS){
            this.selectTransitionMS = selectTransitionMS;
            return this;
        }

        public ChipBuilder deselectTransitionMS(int deselectTransitionMS){
            this.deselectTransitionMS = deselectTransitionMS;
            return this;
        }

        public Chip build(Context context){
            Chip chip = (Chip) LayoutInflater.from(context).inflate(R.layout.chip, null);
            chip.initChip(context, index, label, selectedColor, selectedFontColor, unselectedColor, unselectedFontColor);
            chip.setSelectTransitionMS(selectTransitionMS);
            chip.setDeselectTransitionMS(deselectTransitionMS);
            chip.setChipListener(chipListener);
            chip.setHeight(chipHeight);
            return chip;
        }
    }
}

