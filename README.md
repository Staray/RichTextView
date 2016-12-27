# 扩展TextView：显示图片并能设置图片大小控件

---

能设置图片大小和显示方向的扩展控件。
支持xml和代码设置。
使用方法：

gradle：
在Application根build.gradle中添加

     allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

添加引用：

    dependencies {
	        compile 'com.github.Staray:RichTextView:v1.0.1'
	}

在xml中使用：

        <io.github.staray.library.RichTextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerInParent="true"
            android:gravity="center"
            android:text="a"
            app:drawable_height="100dp"
            app:drawable_location="top"
            app:drawable_src="@mipmap/ic_launcher"
            app:drawable_width="100dp"/>

在java代码中使用：

    RichTextView richTextView = new RichTextView(this);
        richTextView.setmHeight(200);
        richTextView.setmWidth(200);
        richTextView.setmLocation(RichTextView.RIGHT);
        richTextView.drawDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        richTextView.setText("test");
        richTextView.setGravity(Gravity.CENTER);
        mainLayout.addView(richTextView);

注意：
java代码中设置图片宽高默认使用dp为单位。