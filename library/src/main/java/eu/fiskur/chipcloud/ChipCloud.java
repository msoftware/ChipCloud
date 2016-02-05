package eu.fiskur.chipcloud;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

public class ChipCloud extends FlowLayout implements Chip.ChipListener {

    private Context context;
    private int chipHeight;
    private int selectedColor = -1;
    private int selectedFontColor = -1;
    private int unselectedColor = -1;
    private int unselectedFontColor = -1;
    private int selectTransitionMS = 750;
    private int deselectTransitionMS = 500;

    private List<Object> objects = new ArrayList<>();
    private ChipListener chipListener;

    public ChipCloud(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ChipCloud(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ChipCloud, 0, 0);

        try {
            selectedColor = a.getColor(R.styleable.ChipCloud_selectedColor, -1);
            selectedFontColor = a.getColor(R.styleable.ChipCloud_selectedFontColor, -1);
            unselectedColor = a.getColor(R.styleable.ChipCloud_deselectedColor, -1);
            unselectedFontColor = a.getColor(R.styleable.ChipCloud_deselectedFontColor, -1);
            selectTransitionMS = a.getInt(R.styleable.ChipCloud_selectTransitionMS, 750);
            deselectTransitionMS = a.getInt(R.styleable.ChipCloud_deselectTransitionMS, 500);
        } finally {
            a.recycle();
        }

        init();
    }

    private void init(){
        chipHeight = (int) (28 * getResources().getDisplayMetrics().density + 0.5f);
    }

    public void setSelectedColor(int selectedColor){
        this.selectedColor = selectedColor;
    }

    public void setSelectedFontColor(int selectedFontColor){
        this.selectedFontColor = selectedFontColor;
    }

    public void setUnselectedColor(int unselectedColor){
        this.unselectedColor = unselectedColor;
    }

    public void setUnselectedFontColor(int unselectedFontColor){
        this.unselectedFontColor = unselectedFontColor;
    }

    public void setSelectTransitionMS(int selectTransitionMS){
        this.selectTransitionMS = selectTransitionMS;
    }

    public void setDeselectTransitionMS(int deselectTransitionMS){
        this.deselectTransitionMS = deselectTransitionMS;
    }

    public void setChipListener(ChipListener chipListener){
        this.chipListener = chipListener;
    }

    public void addObject(String label, Object object){
        objects.add(object);

        Chip chip = new Chip.ChipBuilder()
                .index(objects.size()-1)
                .label(label)
                .selectedColor(selectedColor)
                .selectedFontColor(selectedFontColor)
                .unselectedColor(unselectedColor)
                .unselectedFontColor(unselectedFontColor)
                .selectTransitionMS(selectTransitionMS)
                .deselectTransitionMS(deselectTransitionMS)
                .chipHeight(chipHeight)
                .chipListener(this)
                .build(context);

        addView(chip);
    }

    public void setSelectedChip(int index){
        Chip chip = (Chip) getChildAt(index);
        chip.select();
    }

    @Override
    public void chipSelected(int index) {

        for(int i = 0 ; i < objects.size() ; i++){
            Chip chip = (Chip) getChildAt(i);
            if(i != index){
                chip.deselect();
            }
        }

        if(chipListener != null){
            chipListener.chipSelected(objects.get(index));
        }
    }

    public static class ChipCloudBuilder{
        ChipCloud chipCloud;
        int selectedColor = -1;
        int selectedFontColor = -1;
        int deselectedColor = -1;
        int deselectedFontColor = -1;
        int selectTransitionMS = 750;
        int deselectTransitionMS = 500;
        ChipListener chipListener;

        public ChipCloudBuilder chipCloud(ChipCloud chipCloud) {
            this.chipCloud = chipCloud;
            return this;
        }

        public ChipCloudBuilder selectedColor(int selectedColor) {
            this.selectedColor = selectedColor;
            return this;
        }

        public ChipCloudBuilder selectedFontColor(int selectedFontColor) {
            this.selectedFontColor = selectedFontColor;
            return this;
        }

        public ChipCloudBuilder deselectedColor(int deselectedColor) {
            this.deselectedColor = deselectedColor;
            return this;
        }

        public ChipCloudBuilder deselectedFontColor(int deselectedFontColor) {
            this.deselectedFontColor = deselectedFontColor;
            return this;
        }

        public ChipCloudBuilder selectTransitionMS(int selectTransitionMS) {
            this.selectTransitionMS = selectTransitionMS;
            return this;
        }

        public ChipCloudBuilder deselectTransitionMS(int deselectTransitionMS) {
            this.deselectTransitionMS = deselectTransitionMS;
            return this;
        }

        public ChipCloudBuilder chipListener(ChipListener chipListener) {
            this.chipListener = chipListener;
            return this;
        }

        public void build(){
            chipCloud.setSelectedColor(selectedColor);
            chipCloud.setSelectedFontColor(selectedFontColor);
            chipCloud.setUnselectedColor(deselectedColor);
            chipCloud.setUnselectedFontColor(deselectedFontColor);
            chipCloud.setSelectTransitionMS(selectTransitionMS);
            chipCloud.setDeselectTransitionMS(deselectTransitionMS);
            chipCloud.setChipListener(chipListener);
        }
    }
}
