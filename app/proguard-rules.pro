# WebView + JavaScript 인터페이스를 쓰는 경우를 대비한 기본 보존 규칙
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
