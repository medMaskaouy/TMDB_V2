package com.maskaouy.moviesviewer.data.di

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.maskaouy.moviesviewer.data.api.MoviesApi
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    companion object{
        private  val HTPP_TIMEOUT = 20L

    }

    @Singleton
    @Provides
    fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, tokenInterceptor : Interceptor,@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient()
                .newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(ChuckInterceptor(context))
                .addInterceptor(tokenInterceptor)
                .readTimeout(HTPP_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(HTPP_TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    @Singleton
    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor { message -> Log.i("LoggingInterceptor\n",message) }
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun providesAccessTokenInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()

            request = request.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Accept", "*/*")
                    .build()
            chain.proceed(request)
        }
    }


    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
            .baseUrl(MoviesApi.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()


    @Singleton
    @Provides
    fun providesMoviesApi(retrofit: Retrofit) : MoviesApi = retrofit.create(MoviesApi::class.java)

}

