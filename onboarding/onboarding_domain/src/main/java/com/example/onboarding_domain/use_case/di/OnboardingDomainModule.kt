package com.example.onboarding_domain.use_case.di

import com.example.onboarding_domain.use_case.ValidateNutrients
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
object OnboardingDomainModule {
    @Provides
    @ViewModelScoped
    fun provideValidateNutrientUseCase():ValidateNutrients{
        return ValidateNutrients()
    }
}