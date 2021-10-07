object Hilt {

    const val hiltVersion = "2.38.1"

    const val android = "com.google.dagger:hilt-android:$hiltVersion"
    const val androidCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"



    private const val navigationViewmodelVersion = "1.0.0-alpha03"

    const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$navigationViewmodelVersion"
    const val navigation = "androidx.hilt:hilt-navigation-compose:$navigationViewmodelVersion"


    private const val compilerVersion = "1.0.0-beta01"
    const val compiler = "androidx.hilt:hilt-compiler:$compilerVersion"

    //--implementation "com.google.dagger:hilt-android:$dagger_hilt_version"
    //--kapt "com.google.dagger:hilt-android-compiler:$dagger_hilt_version"
//    implementation "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
//    kapt "androidx.hilt:hilt-compiler:1.0.0-beta01"
//    implementation 'androidx.hilt:hilt-navigation-compose:1.0.0-alpha03'

}