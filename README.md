# ChipCloud

ChipCloud is a UI element (very) quickly created as part of a larger hackathon project, it creates a wrapping cloud of Material Design '[Chips](https://www.google.com/design/spec/components/chips.html)'. This project is not actively maintained.

## Usage

Add to your Android layout xml:
```xml
<eu.fiskur.chipcloud.ChipCloud
    android:id="@+id/chip_cloud"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

Then add your items:
```java
ChipCloud chipCloud = new ChipCloud(context);

chipCloud.setup(Color.parseColor("#ff00cc"), new eu.fiskur.chipcloud.ChipListener() {
    @Override
    public void chipSelected(Object object) {
        //...
    }
});

chipCloud.addObject("foo", fooObject);
chipCloud.addObject("bar!", barObject);
```

![Chip Cloud](screenshot.png)
