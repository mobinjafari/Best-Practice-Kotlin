package org.lotka.bp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import org.lotka.bp.cache.RecipeDao
import org.lotka.bp.cache.model.RecipeEntityMapper
import org.lotka.bp.interactors.recipe.GetRecipe
import org.lotka.bp.interactors.recipe_list.RestoreRecipes
import org.lotka.bp.interactors.recipe_list.SearchRecipes
import org.lotka.bp.network.RecipeService
import org.lotka.bp.network.model.RecipeDtoMapper

@Module
@InstallIn(ViewModelComponent::class)
object InteractorsModule {

    @ViewModelScoped
    @Provides
    fun provideSearchRecipe(
        recipeService: RecipeService,
        recipeDao: RecipeDao,
        recipeEntityMapper: RecipeEntityMapper,
        recipeDtoMapper: RecipeDtoMapper,
    ): SearchRecipes {
        return SearchRecipes(
            recipeService = recipeService,
            recipeDao = recipeDao,
            entityMapper = recipeEntityMapper,
            dtoMapper = recipeDtoMapper,
        )
    }

    @ViewModelScoped
    @Provides
    fun provideRestoreRecipes(
        recipeDao: RecipeDao,
        recipeEntityMapper: RecipeEntityMapper,
    ): RestoreRecipes {
        return RestoreRecipes(
            recipeDao = recipeDao,
            entityMapper = recipeEntityMapper,
        )
    }

    @ViewModelScoped
    @Provides
    fun provideGetRecipe(
        recipeDao: RecipeDao,
        recipeEntityMapper: RecipeEntityMapper,
        recipeService: RecipeService,
        recipeDtoMapper: RecipeDtoMapper,
    ): GetRecipe {
        return GetRecipe(
            recipeDao = recipeDao,
            entityMapper = recipeEntityMapper,
            recipeService = recipeService,
            recipeDtoMapper = recipeDtoMapper,
        )
    }

}











