# ChipCloud
[![Build Status](https://travis-ci.org/fiskurgit/ChipCloud.svg?branch=master)](https://travis-ci.org/fiskurgit/ChipCloud)

ChipCloud is an Android view (very) quickly knocked up for a larger hackathon project, it creates a wrapping cloud of single choice '[Chips](https://www.google.com/design/spec/components/chips.html)' (see screenshot below).

## Usage

Add to your Android layout xml:
```xml
<eu.fiskur.chipcloud.ChipCloud
    android:id="@+id/chip_cloud"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

Configure in xml:  
```xml
<eu.fiskur.chipcloud.ChipCloud
    android:id="@+id/chip_cloud"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    custom:deselectedColor="@color/deselected_color"
    custom:deselectedFontColor="@color/deselected_font_color"
    custom:selectedColor="@color/selected_color"
    custom:selectedFontColor="@color/selected_font_color"
    custom:deselectTransitionMS="500"
    custom:selectTransitionMS="750"/>
```
or in code:  
```java
ChipCloud chipCloud = (ChipCloud) findViewById(R.id.chip_cloud);

new ChipCloud.ChipCloudBuilder()
        .chipCloud(chipCloud)
        .selectedColor(Color.parseColor("#ff00cc"))
        .selectedFontColor(Color.parseColor("#ffffff"))
        .unselectedColor(Color.parseColor("#e1e1e1"))
        .unselectedFontColor(Color.parseColor("#333333"))
        .selectTransitionMS(500)
        .deselectTransitionMS(250)
        .chipListener(new ChipListener() {
            @Override
            public void chipSelected(Object object) {
                SomeObj selectedObj = (SomeObj) object;
                //...
            }
        })
        .build();
```

Then add your items:
```java
chipCloud.addObject("Foo", fooObject);
chipCloud.addObject("Bar", barObject);
```

Which produces:  
![Chip Cloud](images/foo_bar.png)

Real-world example for shoe sizes:  
![Shoe Sizes](images/wrapping_example.png)

##Dependency

Add jitpack.io to your root build.gradle, eg:

```groovy
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

then add the dependency to your project build.gradle:

```groovy
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.github.fiskurgit:ChipCloud:1.8'
}
```
You can find the latest version in the releases tab above: https://github.com/fiskurgit/ChipCloud/releases

More options at jitpack.io: https://jitpack.io/#fiskurgit/ChipCloud

##Screenshots
![Trainer Sizes](images/trainer_sizes.png)

##Licence

Full licence here: https://github.com/fiskurgit/ChipCloud/blob/master/LICENSE.md

In short:

> The MIT License is a permissive license that is short and to the point. It lets people do anything they want with your code as long as they provide attribution back to you and don’t hold you liable.
