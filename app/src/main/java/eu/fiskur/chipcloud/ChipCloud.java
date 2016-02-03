package eu.fiskur.chipcloud;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

public class ChipCloud extends FlowLayout implements Chip.ChipListener {

    private Context context;
    private int chipHeight;
    private int selectedColor;
    private List<Object> objects = new ArrayList<>();
    private ChipListener chipListener;


    public ChipCloud(Context context) {
        super(context);
        this.context = context;
    }

    public ChipCloud(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    private void setup(int selectedColor, ChipListener chipListener){
        setup(selectedColor);
        setChipListener(chipListener);
    }

    private void setup(int selectedColor){
        final float scale = getResources().getDisplayMetrics().density;
        chipHeight = (int) (28 * scale + 0.5f);
        this.selectedColor = selectedColor;
    }

    public void setChipListener(ChipListener chipListener){
        this.chipListener = chipListener;
    }

    private void addObject(String label, Object object){
        objects.add(object);

        Chip chip = (Chip) LayoutInflater.from(context).inflate(R.layout.chip, null);
        chip.initChip(context, objects.size(), label, selectedColor);
        chip.setHeight(chipHeight);
        chip.setChipListener(this);
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
}
