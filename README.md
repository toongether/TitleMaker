# TitleMaker
TitleMaker is easy Text-to-SVG tool for any Comic Thumbnails.

## Installation
### via Maven
```xml
<dependency>
    <groupId>kr.toongether.titlemaker</groupId>
    <artifactId>titlemaker</artifactId>
    <version>0.0.6</version>
</dependency>
```
### via Gradle
```kts
repositories {
    mavenCentral()
}

dependencies {
    implementation("kr.toongether.titlemaker:titlemaker:0.0.6")
}
```

## Usage
```kotlin
import kr.toongether.titlemaker.TitleMaker
import kr.toongether.titlemaker.Font

fun main() {
    val titleMaker = TitleMaker(title="Hello World", font=Font.GANGWONEDUPOWER)
    val svg = titleMaker.make(alignLeft=true)
}
```