package com.jjvcorporation.listaexchanges.di

import com.jjvcorporation.listaexchanges.data.ExchangeApi
import com.jjvcorporation.listaexchanges.data.repository.exchangeRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi{
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideExchangeApi(moshi: Moshi) : ExchangeApi{
        return Retrofit.Builder()
            .baseUrl("https://api.coinpaprika.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ExchangeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideExchangeRepository(exchangeApi: ExchangeApi):exchangeRepository{
        return exchangeRepository(exchangeApi)
    }

}