# OKHttpLogger

AndroidOffilineLibrar is a library to store json data in file at private location of project and
later on parse it again when there is no network available. place a json file with same name if
there is no respnse from server then offline library look for same file in assets folder.

:information_source: AndroidOffilineLibrar is currently in maintenance mode; existing bugs will be
fixed, but large new features will likely not be added. If you want to add a new feature, please
first search for existing GitHub issues, or create a new one to discuss the feature and get
feedback.

### Library Version

[![](https://jitpack.io/v/SmartAppsDevelopment/OkHttpRequestLogger.svg)](https://jitpack.io/#SmartAppsDevelopment/OkHttpRequestLogger)

### Goals

* Provide convenient way for offline storage
* also deal with showing and hiding retrofit response for DEBUG or RELEASE app
* Extensive support of Java Generics
* Allow custom representations for objects
* Support arbitrarily complex objects (with deep inheritance hierarchies and extensive use of
  generic types)

### Example

Enable Shake Listener At application level

```
 class MainApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        LoggerApiSetting.enableShakeListener(this)
    }
}


```

Attach interceptor to your existing network library

```

            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(ApiLogInterceptor).build()
            retrofit = Retrofit.Builder()
                .baseUrl("https://catfact.ninja/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

```

### Download

Gradle:

```
maven { url 'https://jitpack.io' }

gradle
dependencies {
  	        implementation 'com.github.SmartAppsDevelopment:OkHttpRequestLogger:1.0.0'
}
```

Maven:

```xml

<dependency>
    <groupId>com.github.SmartAppsDevelopment</groupId>
    <artifactId>OkHttpRequestLogger</artifactId>
    <version>1.0.0</version>
</dependency>
```

