# ChipCloud
[![Build Status](https://travis-ci.org/fiskurgit/ChipCloud.svg?branch=master)](https://travis-ci.org/fiskurgit/ChipCloud)

ChipCloud is an Android view (very) quickly knocked up for a larger hackathon project, it creates a wrapping cloud of single choice '[Chips](https://www.google.com/design/spec/components/chips.html)' (see screenshot below).  
  
Basic demo available on the Play Store:  
<a href="https://play.google.com/store/apps/details?id=eu.fiskur.chipclouddemo&utm_source=global_co&utm_medium=prtnr&utm_content=Mar2515&utm_campaign=PartBadge&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1"><img alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png" width=175 /></a>

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
    xmlns:chipcloud="http://schemas.android.com/apk/res-auto"
    android:id="@+id/chip_cloud"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    chipcloud:deselectedColor="@color/deselected_color"
    chipcloud:deselectedFontColor="@color/deselected_font_color"
    chipcloud:selectedColor="@color/selected_color"
    chipcloud:selectedFontColor="@color/selected_font_color"
    chipcloud:deselectTransitionMS="500"
    chipcloud:selectTransitionMS="750"/>
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

Set the selected index using ```chipCloud.setSelectedChip(2)```

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
    compile 'com.github.fiskurgit:ChipCloud:1.9'
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
