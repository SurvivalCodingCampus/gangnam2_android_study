package com.survivalcoding.gangnam2kiandroidstudy

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class DelegatedPropertiesActivity : ComponentActivity() {

    private val userSettings by lazy {
        UserSettings(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        userSettings.userName = "John"

        println(userSettings.userName)
    }
}

class PreferenceLoader(private val default: String) {
    operator fun provideDelegate(
        thisRef: PreferenceModel,
        property: KProperty<*>,
    ): ReadWriteProperty<PreferenceModel, String> {
        return PreferenceDelegate(
            thisRef.context.getSharedPreferences(
                thisRef.javaClass.simpleName,
                Context.MODE_PRIVATE
            ),
            property.name,
            default
        )
    }
}


interface PreferenceModel {
    val context: Context
}

class UserSettings(override val context: Context) : PreferenceModel {
    private val fileName = "user_settings"

    //    var userName by PreferenceDelegate("")
    var userName by PreferenceLoader("")
}


class PreferenceDelegate(
    private val preference: SharedPreferences,
    private val key: String,
    private val default: String,
) : ReadWriteProperty<PreferenceModel, String> {

    private fun getPreferenceForClass(thisRef: PreferenceModel) =
        thisRef.context.getSharedPreferences(key, Context.MODE_PRIVATE)

    override fun getValue(
        thisRef: PreferenceModel,
        property: KProperty<*>,
    ): String {
        return preference.getString(key, default)!!
    }

    override fun setValue(
        thisRef: PreferenceModel,
        property: KProperty<*>,
        value: String,
    ) {
        getPreferenceForClass(thisRef).edit().putString(key, value).apply()
    }

}
