##BadgeHelper
a light badgeHelper by add drawable to view's overlay

An email <yiflandre@gmail.com> link

##setup
gradle

```
compile 'com.lianggekeji.yhj:badgehelper:1.0.0'
	
```

##usage
TextView subclass:

![badge1](http://i1.piimg.com/4851/2aa21ebded460652.png) 

ImageView subclass:

![badge2](http://i1.piimg.com/4851/a31ae36729d40a0e.png)

 just in java
 
```
    new BadgeImageHelper.Builder(imageView).create().setBadgeText("1");
    new BadgeTextHelper.Builder(textView).create().setBadgeText("99+");

```

##feature

 * friendly show badge
 * badge view auto follow content 
 * have default config you also can custom:
 
 ```
   new BadgeTextHelper.Builder(textView)
                .padding(15).leftMargin(-10).textColor(0xff000000).bottomMargin(-30).create(); 
                               
 ```
 
##attr
  * badgeTextSize 
  * leftMargin (badge to real content X disdance  )
  * bottomMargin (badge to real content Y disdance  )
  * padding   (badge padding)
  * textColor  (badge text color)
  * backColor (badge background color)
  
> because use view overlay so api need >17