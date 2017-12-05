# TwoLineSeekBar

android library TwoLineSeekBar

# Demo

# Project

 [<img src="/store/ChPlay.png">](https://play.google.com/store/apps/details?id=com.hoanganhtuan01101995.camdoc)

# Download

* Step 1. Add it in your root build.gradle at the end of repositories:
```java
    allprojects {
        repositories {
          ...
          maven { url 'https://jitpack.io' }
        }
    }
```
* Step 2. Add the dependency
```java
    dependencies {
        compile 'com.github.hoanganhtuan95ptit:TwoLineSeekBar:1.0'
    }
```

# Using

* xml

```java
    
    <com.hoanganhtuan95ptit.library.TwoLineSeekBar
        android:id="@+id/twoLine"
        android:layout_width="200dp"
        android:layout_height="200dp"
        app:high_color="#FFE325"
        app:line_color="#FFFFFF"
        app:nail_color="#FFE325"
        app:thumb_color="#FFE325"
        app:line_width="4dp"
        app:nail_radius="3dp"
        app:nail_stroke_width="6dp"
        app:thumb_radius="6dp"
        />
```

* java

```java
    TwoLineSeekBar twoLineSeekBar = findViewById(R.id.twoLine);
    twoLineSeekBar.setOnSeekChangeListener(new TwoLineSeekBar.OnSeekChangeListener() {
            @Override
            public void onSeekChanged(float value, float step) {

            }

            @Override
            public void onSeekStopped(float value, float step) {

            }
        });
```


