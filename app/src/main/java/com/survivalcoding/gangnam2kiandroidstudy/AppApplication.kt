package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.survivalcoding.gangnam2kiandroidstudy.core.di.appModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.datasourceModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.repositoryModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.useCaseModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger() // koin 주입할 때 Log 나오게 하는 옵션 (없어도 된다.)
            androidContext(this@AppApplication) //data layer에 context가 필요할 때 굉장히 쉽게 전달 가능하게 해줌.
            modules(
                appModule,
                datasourceModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }

}